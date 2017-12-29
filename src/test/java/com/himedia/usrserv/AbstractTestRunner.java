/** 
 * Project Name:UserService 
 * File Name:AbstractTestRunner.java 
 * Package Name:com.himedia.usrserv 
 * Date:Dec 28, 20179:02:24 AM 
 * Copyright (c) 2017, All Rights Reserved. 
 * 
*/  
  
package com.himedia.usrserv;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/** 
 * ClassName:AbstractTestRunner <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     Dec 28, 2017 9:02:24 AM <br/> 
 * @author   songjiqing 
 * @version   
 * @see       
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@EnableWebMvc
public abstract class AbstractTestRunner {
	
	@Autowired
	protected WebApplicationContext wac;
	
	protected MockMvc mockMvc;

	@Before
	public void init() {
		if (mockMvc == null) {
			mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		}
	}

}
  