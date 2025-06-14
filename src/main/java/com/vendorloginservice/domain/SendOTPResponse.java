package com.vendorloginservice.domain;

import com.vendorloginservice.exceptions.StatusHandler;

public class SendOTPResponse {

	private long vendorId;
	private String vendor_name;
	private String mobile;
	private String email;
	private String otp;
	private String message;
	private String token;
	private StatusHandler statusHandler;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	public long getVendorId() {
		return vendorId;
	}
	public void setVendorId(long vendorId) {
		this.vendorId = vendorId;
	}
	public String getVendor_name() {
		return vendor_name;
	}
	public void setVendor_name(String vendor_name) {
		this.vendor_name = vendor_name;
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
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public StatusHandler getStatusHandler() {
		return statusHandler;
	}
	public void setStatusHandler(StatusHandler statusHandler) {
		this.statusHandler = statusHandler;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public SendOTPResponse() {
		super();
	}
	public SendOTPResponse(long vendorId, String vendor_name, String mobile, String email, String otp, String message,
			String token, StatusHandler statusHandler) {
		super();
		this.vendorId = vendorId;
		this.vendor_name = vendor_name;
		this.mobile = mobile;
		this.email = email;
		this.otp = otp;
		this.message = message;
		this.token = token;
		this.statusHandler = statusHandler;
	}
	@Override
	public String toString() {
		return "SendOTPResponse [vendorId=" + vendorId + ", vendor_name=" + vendor_name + ", mobile=" + mobile
				+ ", email=" + email + ", otp=" + otp + ", message=" + message + ", token=" + token + ", statusHandler="
				+ statusHandler + "]";
	}
	
	
}
