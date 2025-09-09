package com.vendorloginservice.domain;

import java.io.Serial;
import java.io.Serializable;

import com.vendorloginservice.exceptions.StatusHandler;

public class VerifyOTPResponse implements Serializable{

    @Serial
    private static final long serialVersionUID = 1L;
	
	private String mobile;
	private String otp;
	private String message;
	private long vendorId;
	private String companyName;
	private String ownerName;
	
	private StatusHandler statusHandler;

	
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

	public StatusHandler getStatusHandler() {
		return statusHandler;
	}

	public void setStatusHandler(StatusHandler statusHandler) {
		this.statusHandler = statusHandler;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
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

	public long getVendorId() {
		return vendorId;
	}

	public void setVendorId(long vendorId) {
		this.vendorId = vendorId;
	}

}
