package com.himedia.usrserv;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.codec.digest.Md5Crypt;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.himedia.usrserv.customer.pojo.LoginById;

public class TestCustomerController extends AbstractTestRunner {


	@Test
	public void testLoginByIdentify() throws UnsupportedEncodingException, Exception {
		String uri = "/customer/login_by_id";
		LoginById loginById = new LoginById();
		loginById.setBirthday(new Date());
		loginById.setIdentify("370288198702108899");
		loginById.setLoginPsw(Md5Crypt.md5Crypt("108899".getBytes()));
		loginById.setRealName("灰帽子");
		loginById.setSex(1);
		
		JsonElement content = new Gson().toJsonTree(loginById);
		content.getAsJsonObject().add("birthday", new JsonPrimitive(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())));
		
		String respStr = mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_UTF8).content(content.toString()))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andReturn()
		.getResponse().getContentAsString();
		
		Assert.assertNotNull(respStr);
	}

}
