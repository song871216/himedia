package com.himedia.usrserv.common;

public enum ErrorCode {

	SUCCESS(0, "成功"),
	
	ID_LOGIN_ERROR(1000, "登陆失败，默认密码已经被修改。");
	
	int code;
	
	String desc;
	
	private ErrorCode(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public String desc() {
		return desc;
	}
	
	public String desc(String moreInfo) {
		return String.format(desc, moreInfo);
	}
	
	public int code() {
		return code;
	}
}
