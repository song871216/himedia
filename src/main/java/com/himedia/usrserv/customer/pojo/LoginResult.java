package com.himedia.usrserv.customer.pojo;

import java.util.Date;

import com.himedia.usrserv.common.CommonResp;

public class LoginResult extends CommonResp {
	
	public LoginResult() {
		super();
	}

	public LoginResult(String nickName, Date lastLogin) {
		super();
		this.nickName = nickName;
		this.lastLogin = lastLogin;
	}
	
	public LoginResult(String nickName, Date lastLogin, int customerType, String customerTypeDesc) {
		super();
		this.nickName = nickName;
		this.lastLogin = lastLogin;
		this.customerType = customerType;
		this.customerTypeDesc = customerTypeDesc;
	}



	String nickName;
	
	Date lastLogin;
	
	int customerType;
	
	String customerTypeDesc;
	
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public int getCustomerType() {
		return customerType;
	}

	public void setCustomerType(int customerType) {
		this.customerType = customerType;
	}

	public String getCustomerTypeDesc() {
		return customerTypeDesc;
	}

	public void setCustomerTypeDesc(String customerTypeDesc) {
		this.customerTypeDesc = customerTypeDesc;
	}
}
