package com.himedia.usrserv.thirdparty;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
	
	public List<WeatherCityCode> findCitiesByName(String cityName) {
		List<WeatherCityCode> weatherCityCodes = weatherDao.matchByCityName(cityName);
		return weatherCityCodes;
	}
	
	
	public Object getCurrentWeather(String cityCode) {
		
	}
	
	public Object getActivityWeather(String cityCode) {
		
	}
}
