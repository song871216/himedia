/** 
 * Project Name:UserService 
 * File Name:AudioService.java 
 * Package Name:com.himedia.usrserv.thirdparty 
 * Date:Dec 29, 20173:50:01 PM 
 * Copyright (c) 2017, All Rights Reserved. 
 * 
*/  
  
package com.himedia.usrserv.thirdparty;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.gson.Gson;
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
	
	public BaiduAccessToken getBaiduAccessToken() throws HiMediaException, ExecutionException {
		if( loadingCache == null ) {
			logger.info("get new token from baidu.");
			return createBaiduAccessToken();
		}
		
		logger.info("try to load token from cache.");
		return loadingCache.get("");
	}
	
	
	private BaiduAccessToken createBaiduAccessToken() throws HiMediaException {
		StringBuilder reqUrl = new StringBuilder(authBaseUrl);
		reqUrl.append("?")
		.append("grant_type=client_credentiale")
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
  