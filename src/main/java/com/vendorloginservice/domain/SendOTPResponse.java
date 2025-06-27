package com.vendorloginservice.domain;

import com.vendorloginservice.exceptions.StatusHandler;

public class SendOTPResponse {

	private Long vendorId;
	private String vFirstname;
	private String vLastname;
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
	
	
	public Long getVendorId() {
		return vendorId;
	}
	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}
	
	public String getvFirstname() {
		return vFirstname;
	}
	public void setvFirstname(String vFirstname) {
		this.vFirstname = vFirstname;
	}
	public String getvLastname() {
		return vLastname;
	}
	public void setvLastname(String vLastname) {
		this.vLastname = vLastname;
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
	
	
}
