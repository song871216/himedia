package com.himedia.usrserv.enums;

public enum CustomerType {
	ADMIN(0, "管理员"),
	
	USER(1, "普通用户");
	
	int type;
	
	String desc;
	
	private CustomerType(int type, String desc) {
		this.type = type;
		this.desc = desc;
	}
	
	public int type() {
		return this.type;
	}
	
	public String desc() {
		return desc;
	}
	
	public static CustomerType parseof(int type) {
		for( CustomerType t : CustomerType.values() ) {
			if( t.type() == type ) {
				return t;
			}
		}
		
		return null;
	}
}
