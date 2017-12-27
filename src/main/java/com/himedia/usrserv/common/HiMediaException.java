package com.himedia.usrserv.common;

public class HiMediaException extends Exception {

	private static final long serialVersionUID = 5589577730336129065L;
	
	public HiMediaException(int errorCode, String errorDesc) {
		this.errorCode = errorCode;
		this.errorDesc = errorDesc;
	}
	
	public HiMediaException(ErrorCode errorCodeEnum) {
		this.errorCode = errorCodeEnum.code();
		this.errorDesc = errorCodeEnum.desc();
	}
	
	public HiMediaException(ErrorCode errorCodeEnum, String desc) {
		this.errorCode = errorCodeEnum.code();
		this.errorDesc = errorCodeEnum.desc(desc);
	}
	
	int errorCode;
	
	String errorDesc;

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorDesc() {
		return errorDesc;
	}

	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}
}
