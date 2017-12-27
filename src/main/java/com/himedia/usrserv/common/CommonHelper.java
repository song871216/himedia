package com.himedia.usrserv.common;

import java.util.Collection;

public class CommonHelper {

	public static boolean isNullOrEmpty(Collection<?> customers) {
		return customers == null || customers.isEmpty();
	}

}
