package com.himedia.usrserv.customer.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.himedia.usrserv.common.HiMediaException;
import com.himedia.usrserv.customer.pojo.LoginById;
import com.himedia.usrserv.customer.service.CustomerService;

@RestController
@RequestMapping(value="/customer")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;

	/**
	 * 通过身份证登陆，前端读取身份证信息模式
	 * @throws HiMediaException 
	 */
	@RequestMapping(value="login_by_id", method=RequestMethod.POST)
	public Object loginByIdentify( @RequestBody @Valid LoginById loginById ) throws HiMediaException {
		return customerService.processLoginByIdentify(loginById);
	}
	
	/**
	 * 验证身份证是否合法
	 * @param identify
	 * @return
	 */
	@RequestMapping(value="verify_identify", method = RequestMethod.GET)
	public Object verifyIdentify( @RequestParam("identify") @NotNull String identify ) {
		return customerService.verifyIdentify(identify);
	}
}
