package com.vendorloginservice.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name = "VENDOR_CREDENTIALS")
@IdClass(VendorCredentials_PK.class)
public class VendorCredentials implements Serializable{

	private static final long serialVersionUID = -3172379865527131977L;
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	private long vendorId;
	private String vendor_name;
	@Id
	private String mobile;
	private String email;
	private String otp;
	private String password;


	public long getVendorId() {
		return vendorId;
	}

	public void setVendorId(long vendorId) {
		this.vendorId = vendorId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

	@Override
	public String toString() {
		return "VendorCredentials [vendorId=" + vendorId + ", vendor_name=" + vendor_name + ", mobile=" + mobile
				+ ", email=" + email + ", otp=" + otp + ", password=" + password + "]";
	}

	public VendorCredentials() {

	}

	public VendorCredentials(long vendorId, String vendor_name, String mobile, String email, String otp,
			String password) {
		super();
		this.vendorId = vendorId;
		this.vendor_name = vendor_name;
		this.mobile = mobile;
		this.email = email;
		this.otp = otp;
		this.password = password;
	}

	

}
