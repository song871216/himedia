package com.himedia.usrserv.common;

public class CommonResp {

	protected int resultCode;
	
	protected String errorDesc;
	
	public static CommonResp failed(String errorDesc) {
		return new CommonResp(1, errorDesc);
	}
	
	public static CommonResp succeed() {
		return new CommonResp();
	}
	
	public CommonResp(int resultCode, String errorDesc) {
		super();
		this.resultCode = resultCode;
		this.errorDesc = errorDesc;
	}
	
	public CommonResp() {
		super();
	}

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	public String getErrorDesc() {
		return errorDesc;
	}

	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}
	
}
