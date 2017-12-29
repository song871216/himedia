/** 
 * Project Name:UserService 
 * File Name:AudioService.java 
 * Package Name:com.himedia.usrserv.thirdparty 
 * Date:Dec 29, 20173:50:01 PM 
 * Copyright (c) 2017, All Rights Reserved. 
 * 
*/  
  
package com.himedia.usrserv.thirdparty;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Range;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.io.Files;
import com.google.gson.Gson;
import com.himedia.usrserv.common.CommonHelper;
import com.himedia.usrserv.common.ErrorCode;
import com.himedia.usrserv.common.HiMediaException;
import com.himedia.usrserv.thirdparty.pojo.BaiduAccessToken;

/** 
 * ClassName:AudioService <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     Dec 29, 2017 3:50:01 PM <br/> 
 * @author   songjiqing 
 * @version   
 * @see       
 */
@Service
public class AudioService {
	
	static final Logger logger = LoggerFactory.getLogger(AudioService.class);
	
	static final String TEXT2AUDIO_PARAMS = "?tex=%s&lan=zh&cuid=%s&ctp=1&tok=%s&per=%s";
	
	static final int MAX_BYTE_SIZE = 1024;
	
	static final String FILE_SUFFIX = "mp3";

	@Value("${baidu.voice.service.app_key}")
	String appKey;
	
	@Value("${baidu.voice.service.app_secret}")
	String appSecret;
	
	@Value("${baidu.voice.service.auth.url}")
	String authBaseUrl;
	
	@Value("${baidu.voice.service.text2audio}")
	String text2audioBaseUrl;
	
	@Autowired
	RestTemplate restTemplate;
	
	LoadingCache<String,BaiduAccessToken> loadingCache;
	
	CacheLoader<String, BaiduAccessToken> cacheLoader;
	
	@PostConstruct
	protected void init() {
		cacheLoader = new CacheLoader<String, BaiduAccessToken>(){

			@Override
			public BaiduAccessToken load(String key) throws Exception {

				return getBaiduAccessToken();
			}
			
		};
	}
	
	/**
	 * get Baidu client token
	 * @return
	 * @throws HiMediaException
	 * @throws ExecutionException
	 */
	public BaiduAccessToken getBaiduAccessToken() throws HiMediaException, ExecutionException {
		if( loadingCache == null ) {
			logger.info("get new token from baidu.");
			return createBaiduAccessToken();
		}
		
		logger.info("try to load token from cache.");
		return loadingCache.get("");
	}
	
	/**
	 * convert text to mp3 audio and save to file
	 * @param text
	 * @param save2file
	 * person: 发音人选择, 0为普通女声，1为普通男生，3为情感合成-度逍遥，4为情感合成-度丫丫，默认为普通女声
	 * @return
	 */
	public File convertText2audioMp3(@Range(min=1, max=1024) String text, @NotNull File save2file, int person) {
		logger.info("save mp3 to {}", save2file);
		if( save2file.exists() ) {
			logger.error("{} has already exist.", save2file);
			return save2file;
		}
		try {
			byte[] data = text2audioMp3(text, person);
			if( CommonHelper.isNullOrEmpty(data) ) {
				return save2file;
			}
			
			String extName = Files.getFileExtension(save2file.getAbsolutePath());
			if( !FILE_SUFFIX.equalsIgnoreCase(extName) ) {
				return save2file;
			}
			
			Files.write(data, save2file);
			
			return save2file;
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		return save2file;
	}
	
	/**
	 * http://tsn.baidu.com/text2audio?tex=***&lan=zh&cuid=***&ctp=1&tok=***
	// 注意tex参数需要按照url参数标准使用url_encode UTF8编码，如合成文字为“百度你好”，
	 * url为 http://tsn.baidu.com/text2audio?tex=%e7%99%be%e5%ba%a6%e4%bd%a0%e5%a5%bd&lan=zh&cuid=***&ctp=1&tok=
	 * 
	 * @param person  发音人选择, 0为普通女声，1为普通男生，3为情感合成-度逍遥，4为情感合成-度丫丫，默认为普通女声
	 * @throws ExecutionException 
	 * @throws HiMediaException 
	 * @throws UnsupportedEncodingException ***
	 */
	public byte[] text2audioMp3(String text, int person) throws HiMediaException, ExecutionException, UnsupportedEncodingException {
		if( StringUtils.isEmpty(text) ) {
			return new byte[0];
		}
		
		byte[] data = new String(text.getBytes(Charset.forName("UTF-8")), Charset.forName("GBK")).getBytes(Charset.forName("GBK"));
		if( data.length > MAX_BYTE_SIZE ) {
			logger.error("text legtn is out of range under GBK encoding.");
			throw new HiMediaException(ErrorCode.TEXT_LEN_OUT_OF_RANGE);
		}
		BaiduAccessToken accessToken = getBaiduAccessToken();
		
		String encodedTex = URLEncoder.encode(text, "UTF-8");
		
		String uri = String.format(TEXT2AUDIO_PARAMS, encodedTex, CommonHelper.LOCAL_MAC_ADDR, accessToken.getAccessToken(), person);
		
		String reqUrl = text2audioBaseUrl + uri;
		logger.info("final text2audio url is: {}", reqUrl);
		
		ResponseEntity<byte[]> responseEntity = restTemplate.getForEntity(reqUrl, byte[].class);
		
		if( responseEntity.getStatusCodeValue() != 200 ) {
			logger.error("http request failed with code: {}", responseEntity.getStatusCodeValue());
			throw new HiMediaException(ErrorCode.SYS_ERROR);
		}
		
		MediaType mediaType = responseEntity.getHeaders().getContentType();
		if( mediaType == null ) {
			logger.error("invalid media type from the return message");
			throw new HiMediaException(ErrorCode.SYS_ERROR);
		}
		
		if( mediaType.isCompatibleWith(MediaType.APPLICATION_JSON) ) {
			logger.error("server return error: {}", new String(responseEntity.getBody()));
			throw new HiMediaException(ErrorCode.SYS_ERROR, new String(responseEntity.getBody()));
		}
		
		return responseEntity.getBody();
	}
	
	
	private BaiduAccessToken createBaiduAccessToken() throws HiMediaException {
		StringBuilder reqUrl = new StringBuilder(authBaseUrl);
		reqUrl.append("?")
		.append("grant_type=client_credentials")
		.append("&")
		.append("client_id=").append(appKey)
		.append("&")
		.append("client_secret=").append(appSecret);
		logger.info("auth url is: {}", reqUrl);
		
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(reqUrl.toString(), String.class);
		
		if( responseEntity.getStatusCodeValue() != 200 ) {
			throw new HiMediaException(ErrorCode.GET_BAIDU_ACCESS_TOKEN_FAILED.code(), ErrorCode.GET_BAIDU_ACCESS_TOKEN_FAILED.desc(responseEntity.getBody()));
		}
		
		BaiduAccessToken accessToken = new Gson().fromJson(responseEntity.getBody(), BaiduAccessToken.class);
		
		add2cache(accessToken);
		
		return accessToken;
	}

	private void add2cache(BaiduAccessToken accessToken) {
		if( loadingCache == null ) {
			loadingCache = CacheBuilder.newBuilder()
					.maximumSize(1)
					.expireAfterWrite(accessToken.getExpiresIn(), TimeUnit.SECONDS)
					.build(cacheLoader);
		}else {
			loadingCache.invalidateAll();
		}
		
		loadingCache.put("", accessToken);
	}
	
	
}
  