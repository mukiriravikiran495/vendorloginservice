package com.vendorloginservice.entity;

import java.io.Serializable;

public class VendorCredentials_PK implements Serializable{
	
	private static final long serialVersionUID = 2074033012373985128L;
	private long vendorId;
	private String mobile;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	
	public VendorCredentials_PK(long vendorId, String mobile) {
		super();
		this.vendorId = vendorId;
		this.mobile = mobile;
	}
	
	@Override
	public String toString() {
		return "VendorCredentials_PK [vendorId=" + vendorId + ", mobile=" + mobile + "]";
	}
	public VendorCredentials_PK() {
		
	}
	
	
}
