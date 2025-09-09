package com.vendorloginservice.entity;

import java.io.Serializable;
import java.util.Objects;


public class VendorAuth_PK implements Serializable{
	
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
	
	
	public VendorAuth_PK(long vendorId, String mobile) {
		super();
		this.vendorId = vendorId;
		this.mobile = mobile;
	}
	
	@Override
	public String toString() {
		return "VendorCredentials_PK [vendorId=" + vendorId + ", mobile=" + mobile + "]";
	}
	public VendorAuth_PK() {
		
	}
	@Override
	public int hashCode() {
		return Objects.hash(mobile, vendorId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VendorAuth_PK other = (VendorAuth_PK) obj;
		return Objects.equals(mobile, other.mobile) && vendorId == other.vendorId;
	}
	
	
}
