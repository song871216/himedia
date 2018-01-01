package com.himedia.usrserv.common;

public class CommonResp {

	protected int resultCode;
	
	protected String errorDesc;
	
	protected Object response;
	
	public static CommonResp failed(String errorDesc) {
		return new CommonResp(1, errorDesc);
	}
	
	public static CommonResp failed(int code, String errorDesc) {
		return new CommonResp(1, errorDesc);
	}
	
	public static CommonResp failed(HiMediaException exception) {
		return new CommonResp(exception.errorCode, exception.errorDesc);
	}
	
	public static CommonResp succeed() {
		return new CommonResp();
	}
	
	public static CommonResp succeed(Object response) {
		return new CommonResp(response);
	}
	
	public CommonResp(Object response) {
		super();
		this.response = response;
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

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}

	@Override
	public String toString() {
		return "CommonResp [resultCode=" + resultCode + ", errorDesc=" + errorDesc + ", response=" + response + "]";
	}
}
