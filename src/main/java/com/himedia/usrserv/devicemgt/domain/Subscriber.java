package com.himedia.usrserv.devicemgt.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="subscriber")
public class Subscriber {

	@Id
	@Column(name="subscriber_id", nullable=false, unique=true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long subscriberId;
	
	@Column(name="mac_address", nullable=false, unique=true, length=64)
	String macAddress;
	
	@Column(name="ip_address", nullable=false, unique=true, length=128)
	String ipAddress;
	
	//位置，地址
	@Column(name="location", nullable=false, length=256)
	String location;
	
	//序号
	@Column(name="serial_no", nullable=false, length=64)
	String serialNo;
	
	@Column(name="longitude")
	Long longitude;
	
	@Column(name="latitude")
	Long latitude;
	
	//激活状态
	@Column(name="active_status")
	int activeStatus;

	String description;
	
	public Long getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(Long subscriberId) {
		this.subscriberId = subscriberId;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public Long getLongitude() {
		return longitude;
	}

	public void setLongitude(Long longitude) {
		this.longitude = longitude;
	}

	public Long getLatitude() {
		return latitude;
	}

	public void setLatitude(Long latitude) {
		this.latitude = latitude;
	}

	public int getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(int activeStatus) {
		this.activeStatus = activeStatus;
	}
	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Subscriber [subscriberId=" + subscriberId + ", macAddress=" + macAddress + ", ipAddress=" + ipAddress
				+ ", location=" + location + ", serialNo=" + serialNo + ", longitude=" + longitude + ", latitude="
				+ latitude + ", activeStatus=" + activeStatus + ", description=" + description + "]";
	}
}
