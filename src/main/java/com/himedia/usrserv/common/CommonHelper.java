package com.himedia.usrserv.common;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collection;
import java.util.Map;

public class CommonHelper {
	
	public static final String LOCAL_MAC_ADDR = getLocalMac();

	public static boolean isNullOrEmpty(Collection<?> customers) {
		return customers == null || customers.isEmpty();
	}

	public static boolean isNullOrEmpty(Map<?, ?> data) {
		return data == null || data.isEmpty();
	}

	public static String getLocalMac() {
		StringBuffer sb = new StringBuffer("");
		try {
			InetAddress ia = InetAddress.getLocalHost();
			byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
			System.out.println("mac数组长度："+mac.length);
			for(int i=0; i<mac.length; i++) {
				if(i!=0) {
					sb.append("-");
				}
				int temp = mac[i]&0xff;
				String str = Integer.toHexString(temp);
				if(str.length()==1) {
					sb.append("0"+str);
				}else {
					sb.append(str);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public static boolean isNullOrEmpty(byte[] data) {
		return data == null || data.length == 0;
	}
	
	public static boolean isNullOrEmpty(Object[] data) {
		return data == null || data.length == 0;
	}
}
