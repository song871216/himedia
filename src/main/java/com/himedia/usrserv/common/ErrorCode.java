package com.himedia.usrserv.common;

public enum ErrorCode {

	SUCCESS(0, "成功"),
	
	ID_LOGIN_ERROR(1000, "登陆失败，默认密码已经被修改。"),
	
	ID_INVALID(1001, "身份证号码不合法"),
	
	SYS_ERROR(4000, "系统错误：%s"),
	
	GET_BAIDU_ACCESS_TOKEN_FAILED(5000, "获取token失败：%s");
	
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
