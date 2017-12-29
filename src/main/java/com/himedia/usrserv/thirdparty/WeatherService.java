package com.himedia.usrserv.thirdparty;

import java.net.MalformedURLException;
import java.text.MessageFormat;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.himedia.usrserv.thirdparty.dao.WeatherDao;
import com.himedia.usrserv.thirdparty.domain.WeatherCityCode;

@Service
public class WeatherService {
	
	static final Logger logger = LoggerFactory.getLogger(WeatherService.class);
	
	static final int RESP_OK = 200;
	
	static final Gson CONVERTOR = new Gson();
	
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${weather.current.url}")
	String getCurrentWeatherUrl;
	
	@Value("${weather.activity.url}")
	String getActivityWeatherUrl;
	
	@Value("${weather.app.key}")
	String appKey;
	
	@Autowired
	WeatherDao weatherDao;
	
	/**
	 * 按照城市名称模糊查询
	 * 
	 * @param cityName
	 * @return
	 */
	public List<WeatherCityCode> findCitiesByName(String cityName) {
		logger.info("query cities by city name: {}", cityName);
		List<WeatherCityCode> weatherCityCodes = weatherDao.matchByCityName(cityName);
		return weatherCityCodes;
	}
	
	
	/** 
	 * getCurrentWeather:get current city weather. <br/> 
	 * http://www.avatardata.cn/usercenter/my/i
	 * 
	 * @author songjiqing 
	 * @param cityCode
	 * @return
	 * @throws MalformedURLException 
	 */  
	public Map<?, ?> getCurrentWeather(String cityName) throws MalformedURLException {
		JsonObject respObj = processGetReq(cityName, getCurrentWeatherUrl);
		
		if( respObj == null ) {
			return Collections.emptyMap();
		}
		
		if( respObj.has("realtime") ) {
			JsonElement realtimeWeather = respObj.get("realtime");
			
			return new Gson().fromJson(realtimeWeather, Map.class);
		}
		
		return Collections.emptyMap();
	}
	
	/** 
	 * getActivityWeather: get current city weather recommend info. <br/> 
	 * http://www.avatardata.cn/usercenter/my/i
	 * 
	 * @author songjiqing 
	 * @param cityCode
	 * @return
	 * @throws MalformedURLException 
	 */  
	public Map<?, ?> getWeatherRecommend(String cityName) throws MalformedURLException {
		JsonObject respObj =  processGetReq(cityName, getActivityWeatherUrl);
		
		if( respObj == null ) {
			return Collections.emptyMap();
		}
		
		if( respObj.has("realtime") ) {
			JsonElement realtimeWeather = respObj.get("life");
			
			return new Gson().fromJson(realtimeWeather, Map.class);
		}
		
		return Collections.emptyMap();
	}
	
	private JsonObject processGetReq(String cityName, String weatherUrl) throws MalformedURLException {
		logger.info("use city name: {} to query {}", cityName, weatherUrl);
		
		String reqUrl = createReqUrl(weatherUrl, appKey, cityName);
		logger.info("third weather query url: {}", reqUrl);
		
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(reqUrl, String.class);
		
		if( responseEntity.getStatusCodeValue() != RESP_OK ) {
			return null;
		}
		
		String respBody = responseEntity.getBody();
		logger.info("query result: {}", respBody);
		
		JsonObject respObj = CONVERTOR.fromJson(respBody, JsonObject.class);
		if( respObj.has("reason") && respObj.get("reason").getAsString().equalsIgnoreCase("Succes") ) {
			return respObj.get("result").getAsJsonObject();
		}
		return null;
	}
	
	private String createReqUrl(String src, String appkey, String cityName) {
		
		return MessageFormat.format(src, appkey, cityName);
	}

}
