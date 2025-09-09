package com.vendorloginservice.domain;

import java.io.Serializable;

public class SendOTPRequest implements Serializable{
	
	private String mobile;
	private String otp;
	private String email;
	private String message;
	private long vendorId;
	private String companyName;
	private String ownerName;
	private String deviceId;
	private String deviceName;
	private String appId;
	
	public SendOTPRequest() {
		
	}

	public Long getVendorId() {
		return vendorId;
	}

	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setVendorId(long vendorId) {
		this.vendorId = vendorId;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}
	
	public SendOTPRequest(Long vendorId, String companyName, String ownerName, String mobile, String email, String otp,
			String message, String deviceId, String deviceName, String appId) {
		super();
		this.vendorId = vendorId;
		this.companyName = companyName;
		this.ownerName = ownerName;
		this.mobile = mobile;
		this.email = email;
		this.otp = otp;
		this.message = message;
		this.deviceId = deviceId;
		this.deviceName = deviceName;
		this.appId = appId;
	}

	@Override
	public String toString() {
		return "SendOTPRequest [vendorId=" + vendorId + ", companyName=" + companyName + ", ownerName=" + ownerName
				+ ", mobile=" + mobile + ", email=" + email + ", otp=" + otp + ", message=" + message + ", deviceId="
				+ deviceId + ", deviceName=" + deviceName + ", appId=" + appId + "]";
	}
	
}
