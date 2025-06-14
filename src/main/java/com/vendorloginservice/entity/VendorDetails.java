package com.vendorloginservice.entity;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table( name = "VENDOR_DETAILS", schema = "VENDOR")
public class VendorDetails implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	private long vendorId;
	private String v_firstName;
	private String v_lastName;
	private String v_mobile;
	private String v_email;
	
	@OneToOne(mappedBy = "vendor", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private VendorAddress vendorAddress;
	
	public long getVendorId() {
		return vendorId;
	}
	public void setVendorId(long vendorId) {
		this.vendorId = vendorId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getV_firstName() {
		return v_firstName;
	}
	public void setV_firstName(String v_firstName) {
		this.v_firstName = v_firstName;
	}
	public String getV_lastName() {
		return v_lastName;
	}
	public void setV_lastName(String v_lastName) {
		this.v_lastName = v_lastName;
	}
	public String getV_mobile() {
		return v_mobile;
	}
	public void setV_mobile(String v_mobile) {
		this.v_mobile = v_mobile;
	}
	public String getV_email() {
		return v_email;
	}
	public void setV_email(String v_email) {
		this.v_email = v_email;
	}
	
	public VendorDetails(long vendorId, String v_firstName, String v_lastName, String v_mobile, String v_email,
			VendorAddress vendorAddress) {
		super();
		this.vendorId = vendorId;
		this.v_firstName = v_firstName;
		this.v_lastName = v_lastName;
		this.v_mobile = v_mobile;
		this.v_email = v_email;
		this.vendorAddress = vendorAddress;
	}
	
	@Override
	public String toString() {
		return "VendorDetails [vendorId=" + vendorId + ", v_firstName=" + v_firstName + ", v_lastName=" + v_lastName
				+ ", v_mobile=" + v_mobile + ", v_email=" + v_email + ", vendorAddress=" + vendorAddress + "]";
	}
	public VendorDetails() {
		
	}
	public VendorAddress getVendorAddress() {
		return vendorAddress;
	}
	public void setVendorAddress(VendorAddress vendorAddress) {
		this.vendorAddress = vendorAddress;
	}
	
	
}
