/** 
 * Project Name:UserService 
 * File Name:Pager.java 
 * Package Name:com.himedia.usrserv.basedao 
 * Date:Dec 29, 20172:41:43 PM 
 * Copyright (c) 2017, All Rights Reserved. 
 * 
*/  
  
package com.himedia.usrserv.basedao;

import java.util.Collections;

/** 
 * ClassName:Pager <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     Dec 29, 2017 2:41:43 PM <br/> 
 * @author   songjiqing 
 * @version   
 * @see       
 */
public class Pager {
	
	int pageSize;
	
	int pageNo;
	
	long rowCount;
	
	Object data;
	
	public static Pager emptyPager() {
		return new Pager(10, 1, 0, Collections.emptyList());
	}
	
	public Pager() {
		super();
	}

	public Pager(int pageSize, int pageNo, long rowCount, Object data) {
		super();
		this.pageSize = pageSize;
		this.pageNo = pageNo;
		this.rowCount = rowCount;
		this.data = data;
	}



	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public long getRowCount() {
		return rowCount;
	}

	public void setRowCount(long rowCount) {
		this.rowCount = rowCount;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
  