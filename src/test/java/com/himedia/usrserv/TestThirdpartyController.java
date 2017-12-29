/** 
 * Project Name:UserService 
 * File Name:TestThirdpartyController.java 
 * Package Name:com.himedia.usrserv 
 * Date:Dec 29, 201711:39:38 AM 
 * Copyright (c) 2017, All Rights Reserved. 
 * 
*/  
  
package com.himedia.usrserv;

import java.io.UnsupportedEncodingException;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

/** 
 * ClassName:TestThirdpartyController <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     Dec 29, 2017 11:39:38 AM <br/> 
 * @author   songjiqing 
 * @version   
 * @see       
 */
public class TestThirdpartyController extends AbstractTestRunner {

	@Test
	public void testGetCurrentWeather() throws UnsupportedEncodingException, Exception {
		for(int i=0;i<2;i++) {
			String uri = "/third/weather/current";
			
			String cityCode = "青岛";
			
			String respStr = mockMvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_UTF8).param("cityName", cityCode))
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andReturn().getResponse().getContentAsString();
			
			JsonElement resp = new Gson().fromJson(respStr, JsonElement.class);
			
			Assert.assertEquals(0, resp.getAsJsonObject().get("resultCode").getAsInt());
		}
	}
	
	@Test
	public void testGetWeatherRecommend() throws UnsupportedEncodingException, Exception {
		String uri = "/third/weather/recommend";
		
		String cityCode = "青岛";
		
		String respStr = mockMvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_UTF8).param("cityName", cityCode))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn().getResponse().getContentAsString();
		
		JsonElement resp = new Gson().fromJson(respStr, JsonElement.class);
		
		Assert.assertEquals(0, resp.getAsJsonObject().get("resultCode").getAsInt());
	}
	
	@Test
	public void testQueryCity() throws UnsupportedEncodingException, Exception {
		String uri = "/third/weather/city";
		
		String cityName = "青";
		int pageNo = 1;
		int pageSize = 10;
		
		String respStr = mockMvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_UTF8)
				.param("cityName", cityName)
				.param("pageNo", pageNo+"")
				.param("pageSize", pageSize+""))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn().getResponse().getContentAsString();
		
		JsonElement resp = new Gson().fromJson(respStr, JsonElement.class);
		
		Assert.assertEquals(0, resp.getAsJsonObject().get("resultCode").getAsInt());
	}
}
  