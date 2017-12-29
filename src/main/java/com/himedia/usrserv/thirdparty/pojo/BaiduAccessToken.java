/** 
 * Project Name:UserService 
 * File Name:BaiduAccessToken.java 
 * Package Name:com.himedia.usrserv.thirdparty.pojo 
 * Date:Dec 29, 20174:04:19 PM 
 * Copyright (c) 2017, All Rights Reserved. 
 * 
*/  
  
package com.himedia.usrserv.thirdparty.pojo;

import com.google.gson.annotations.SerializedName;

/** 
 * ClassName:BaiduAccessToken <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     Dec 29, 2017 4:04:19 PM <br/> 
 * @author   songjiqing 
 * @version   
 * @see       
 */
public class BaiduAccessToken {
	
	@SerializedName("access_token")
	String accessToken;
	
	@SerializedName("expires_in")
	long expiresIn;
	
	@SerializedName("refresh_token")
	String refreshToken;
	
	String scope;
	
	@SerializedName("session_key")
	String sessionKey;
	
	@SerializedName("session_secret")
	String sessionSecret;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public long getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(long expiresIn) {
		this.expiresIn = expiresIn;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

	public String getSessionSecret() {
		return sessionSecret;
	}

	public void setSessionSecret(String sessionSecret) {
		this.sessionSecret = sessionSecret;
	}

	@Override
	public String toString() {
		return "BaiduAccessToken [accessToken=" + accessToken + ", expiresIn=" + expiresIn + ", refreshToken="
		        + refreshToken + ", scope=" + scope + ", sessionKey=" + sessionKey + ", sessionSecret=" + sessionSecret
		        + "]";
	}
}
  