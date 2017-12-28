package com.himedia.usrserv.customer.pojo;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonFormat;

public class LoginById {
	
	@NotNull
	@Length(min=18, max=18)
	String identify;
	
	@NotNull
	@Length(min=1, max=255)
	String loginPsw;
	
	@NotNull
	String realName;
	
	@Range(min=0, max=1)
	int sex;
	
	@NotNull
	@JsonFormat(pattern="yyyyMMddHHmmss")
	Date birthday;

	public String getIdentify() {
		return identify;
	}

	public void setIdentify(String identify) {
		this.identify = identify;
	}


	public String getLoginPsw() {
		return loginPsw;
	}

	public void setLoginPsw(String loginPsw) {
		this.loginPsw = loginPsw;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}
	
	public Date getBirthday() {
		return birthday;
	}

	@Override
	public String toString() {
		return "LoginById [identify=" + identify + ", loginPsw=" + loginPsw + ", realName=" + realName + ", sex=" + sex
				+ ", birthday=" + birthday + "]";
	}
}
