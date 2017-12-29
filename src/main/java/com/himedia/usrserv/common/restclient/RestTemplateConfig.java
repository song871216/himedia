/** 
 * File Name:RestTemplateConfig.java 
 * Package Name:com.hisense.hitv.entrust.pay.rest 
 * Date:Aug 15, 201710:32:42 AM 
 * Copyright (c) 2017, All Rights Reserved. 
 * 
*/  
  
package com.himedia.usrserv.common.restclient;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.http.Header;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/** 
 * ClassName:RestTemplateConfig <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     Aug 15, 2017 10:32:42 AM <br/> 
 * @author   songjiqing 
 * @version   
 * @see       
 */
@Configuration
public class RestTemplateConfig {
	
	@Value("${http.connect-timeout:10}")
	int connectTimeout;
	
	@Value("${http.read-timeout:35}")
	int readTimeout;
	
	@Value("${pooling.timeToLive:30}")
	long time2live; 
	
	@Value("${pooling.maxTotal:1000}")
	int maxTotal;
	
	@Value("${pooling.maxPerRoute:1000}")
	int maxPerRoute;
	
	@Autowired
	ClientHttpRequestFactory factory;
	
	@Bean
	public RestTemplate restTemplate () {
		
		RestTemplate restTemplate = new RestTemplate(factory);
		FormHttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
		formHttpMessageConverter.setCharset(Charset.forName("UTF-8"));
		
		restTemplate.setMessageConverters(Arrays.asList(
				formHttpMessageConverter,
				new StringHttpMessageConverter(Charset.forName("UTF-8"))
				));
		return  restTemplate;
	}
	
	@Bean
	public ClientHttpRequestFactory requestConfig(){
		RequestConfig  config = RequestConfig
									.custom()
									.setConnectTimeout(connectTimeout * 1000)
									.setConnectionRequestTimeout(readTimeout * 1000)
									.build();
		
		// 长连接保持30秒
        PoolingHttpClientConnectionManager pollingConnectionManager = new PoolingHttpClientConnectionManager(time2live, TimeUnit.SECONDS);
        // 总连接数
        pollingConnectionManager.setMaxTotal(maxTotal);
        // 同路由的并发数
        pollingConnectionManager.setDefaultMaxPerRoute(maxPerRoute);
        
        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.16 Safari/537.36"));
        headers.add(new BasicHeader("Accept-Encoding", "gzip,deflate"));
        headers.add(new BasicHeader("Accept-Language", "zh-CN"));
        headers.add(new BasicHeader("Connection", "Keep-Alive"));
		
		HttpClientBuilder builder = HttpClientBuilder.create()
				.setDefaultRequestConfig(config)
				.setConnectionManager(pollingConnectionManager)
				.setRetryHandler(new DefaultHttpRequestRetryHandler(3, false))
				.setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
				.setMaxConnPerRoute(this.maxPerRoute)
				.setMaxConnTotal(this.maxTotal)
				.setDefaultHeaders(headers);
        HttpClient httpClient = builder.build();
        //使用httpClient创建一个ClientHttpRequestFactory的实现
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        requestFactory.setConnectTimeout(3000);
        requestFactory.setReadTimeout(3000);
        requestFactory.setConnectionRequestTimeout(3000);
        requestFactory.setConnectionRequestTimeout(200);
        
        return requestFactory;
	}
}
  