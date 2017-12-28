package com.himedia.usrserv.thirdparty;

import java.text.MessageFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.himedia.usrserv.thirdparty.dao.WeatherDao;
import com.himedia.usrserv.thirdparty.domain.WeatherCityCode;

@Service
public class WeatherService {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${weather.current.url}")
	String getCurrentWeatherUrl;
	
	@Value("${weather.activity.url}")
	String getActivityWeatherUrl;
	
	@Autowired
	WeatherDao weatherDao;
	
	/**
	 * 按照城市名称模糊查询
	 * @param cityName
	 * @return
	 */
	public List<WeatherCityCode> findCitiesByName(String cityName) {
		List<WeatherCityCode> weatherCityCodes = weatherDao.matchByCityName(cityName);
		return weatherCityCodes;
	}
	
	
	public Object getCurrentWeather(String cityCode) {
		String reqUrl = createReqUrl(getCurrentWeatherUrl, cityCode, System.currentTimeMillis());
		
	}
	
	private String createReqUrl(String src, String cityCode, long ts) {
		
		return MessageFormat.format(src, cityCode, ts);
	}


	public Object getActivityWeather(String cityCode) {
		
	}
	
	private HttpEntity<String> getDefaultHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "text/html; charset=utf-8");
		headers.add("Accept", "text/html");
		headers.add("Referer", "http://mobile.weather.com.cn");
		
		return new HttpEntity<String>(headers);
	}
}
