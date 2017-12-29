/** 
 * Project Name:UserService 
 * File Name:ThirdpartyController.java 
 * Package Name:com.himedia.usrserv.thirdparty 
 * Date:Dec 29, 201711:31:27 AM 
 * Copyright (c) 2017, All Rights Reserved. 
 * 
*/  
  
package com.himedia.usrserv.thirdparty;

import java.util.Map;

import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.himedia.usrserv.common.CommonHelper;
import com.himedia.usrserv.common.CommonResp;
import com.himedia.usrserv.common.ErrorCode;
import com.himedia.usrserv.common.HiMediaException;

/** 
 * ClassName:ThirdpartyController <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     Dec 29, 2017 11:31:27 AM <br/> 
 * @author   songjiqing 
 * @version   
 * @see       
 */
@RestController
@RequestMapping(value="/third/weather")
public class ThirdpartyController {
	
	@Autowired
	WeatherService weatherService;

	@RequestMapping(value="current", method = RequestMethod.GET)
	public Object getWeatherInfo(@RequestParam("cityName") @NotEmpty String cityName) throws HiMediaException {
		try {
			Map<?, ?> data = weatherService.getCurrentWeather(cityName);
			if( CommonHelper.isNullOrEmpty(data) ) {
				return CommonResp.failed("unkonw error");
			}
			
			return CommonResp.succeed(data);
		}
		catch (Exception e) {
			throw new HiMediaException(ErrorCode.SYS_ERROR.code(), ErrorCode.SYS_ERROR.desc(e.getMessage()));
		}
	}
	
	@RequestMapping(value="recommend", method = RequestMethod.GET)
	public Object getWeatherRecommendInfo(@RequestParam("cityName") @NotEmpty String cityName) throws HiMediaException {
		try {
			Map<?, ?> data = weatherService.getWeatherRecommend(cityName);
			if( CommonHelper.isNullOrEmpty(data) ) {
				return CommonResp.failed("unkonw error");
			}
			
			return CommonResp.succeed(data);
		}
		catch (Exception e) {
			throw new HiMediaException(ErrorCode.SYS_ERROR.code(), ErrorCode.SYS_ERROR.desc(e.getMessage()));
		}
	}
	
	@RequestMapping(value="city", method = RequestMethod.GET)
	public Object queryCityInfo(@RequestParam(name = "cityName", required=true) @NotEmpty String cityName, 
			@RequestParam(name = "pageNo", required=true) int pageNo, @RequestParam(name = "pageSize", required=true) int pageSize) throws HiMediaException {
		try {
			return CommonResp.succeed(weatherService.findCitiesByName(cityName, pageNo, pageSize));
		}
		catch (Exception e) {
			throw new HiMediaException(ErrorCode.SYS_ERROR.code(), ErrorCode.SYS_ERROR.desc(e.getMessage()));
		}
	}
}
  