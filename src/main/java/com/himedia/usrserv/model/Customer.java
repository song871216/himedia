package com.himedia.usrserv.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="customer")
public class Customer {
	
	@Id
	@Column(name="customer_id", nullable=false, unique=true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long customerId;
	
	@Column(name="role_id", nullable=false)
	Long roleId;
	
	@Column(name="login_name", length=64, nullable=false,unique=true)
	String loginName;
	
	@Column(name="login_psw", length=255, nullable=false)
	String loginPsw;
	
	@Column(name="identify", length=18, nullable=false)
	String identify;
	
	@Column(name="nick_name", length=255)
	String nickName;
	
	@Column(name="real_name", length=255)
	String realName;
	
	@Column(name="mobile_phone", length=11)
	String mobilePhone;
	
	@Column(name="create_date", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	Date createDate;
	
	@Column(name="update_date", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	Date updateDate;

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPsw() {
		return loginPsw;
	}

	public void setLoginPsw(String loginPsw) {
		this.loginPsw = loginPsw;
	}

	public String getIdentify() {
		return identify;
	}

	public void setIdentify(String identify) {
		this.identify = identify;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	
	
}
