/** 
 * Project Name:UserService 
 * File Name:TestThirdpartyController.java 
 * Package Name:com.himedia.usrserv 
 * Date:Dec 29, 201711:39:38 AM 
 * Copyright (c) 2017, All Rights Reserved. 
 * 
*/  
  
package com.himedia.usrserv;

import java.io.File;
import java.io.UnsupportedEncodingException;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.himedia.usrserv.thirdparty.AudioService;

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
	
	@Autowired
	AudioService audioService;
	
	@Test
	public void testText2audio() {
		String text = "合成文本长度必须小于512个中文字或者英文数字。如果本文长度较长，请自行按照标点切割，可以采用多次请求的方式。";
		File dstFile = new File("E:\\Work\\text2audiotest.mp3");
		//发音人选择, 0为普通女声，1为普通男生，3为情感合成-度逍遥，4为情感合成-度丫丫，默认为普通女声
		File save2file = audioService.convertText2audioMp3(text, dstFile, 1);
		
		Assert.assertTrue(save2file.exists());
		Assert.assertTrue(save2file.isFile());
	}

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
  