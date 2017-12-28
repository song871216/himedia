package com.himedia.usrserv.customer.model;


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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long customerId;
	
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

	@Column(name="last_login")
	@Temporal(TemporalType.TIMESTAMP)
	Date lastLogin;
	
	@Column(name="role_type", nullable=false)
	Integer roleType;
	
	@Column(name="sex", nullable=false)
	Integer sex;
	
	@Column(name="birthday")
	@Temporal(TemporalType.TIMESTAMP)
	Date birthday;
	
	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
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

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Integer getRoleType() {
		return roleType;
	}

	public void setRoleType(Integer roleType) {
		this.roleType = roleType;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", loginName=" + loginName + ", loginPsw=" + loginPsw
				+ ", identify=" + identify + ", nickName=" + nickName + ", realName=" + realName + ", mobilePhone="
				+ mobilePhone + ", createDate=" + createDate + ", updateDate=" + updateDate + ", lastLogin=" + lastLogin
				+ ", roleType=" + roleType + ", sex=" + sex + ", birthday=" + birthday + "]";
	}
}
