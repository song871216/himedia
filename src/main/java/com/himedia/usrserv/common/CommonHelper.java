package com.himedia.usrserv.common;

import java.util.Collection;
import java.util.Map;

public class CommonHelper {

	public static boolean isNullOrEmpty(Collection<?> customers) {
		return customers == null || customers.isEmpty();
	}

	public static boolean isNullOrEmpty(Map<?, ?> data) {
		return data == null || data.isEmpty();
	}

}
