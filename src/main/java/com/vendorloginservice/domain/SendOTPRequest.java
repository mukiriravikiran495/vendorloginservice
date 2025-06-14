package com.vendorloginservice.domain;

import java.io.Serializable;

public class SendOTPRequest implements Serializable{
	
	private static final long serialVersionUID = -7018178304185904643L;
	private long vendorId;
	private String vendor_name;
	private String mobile;
	private String email;
	private String otp;
//	private StatusHandler statusHandler;
	
	public String getVendor_name() {
		return vendor_name;
	}
	public long getVendorId() {
		return vendorId;
	}
	public void setVendorId(long vendorId) {
		this.vendorId = vendorId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
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
	
	public SendOTPRequest(long vendorId, String vendor_name, String mobile, String email, String otp) {
		super();
		this.vendorId = vendorId;
		this.vendor_name = vendor_name;
		this.mobile = mobile;
		this.email = email;
		this.otp = otp;
	}
	@Override
	public String toString() {
		return "SendOTPRequest [vendorId=" + vendorId + ", vendor_name=" + vendor_name + ", mobile=" + mobile
				+ ", email=" + email + ", otp=" + otp + "]";
	}
	public SendOTPRequest() {
		
	}
}
