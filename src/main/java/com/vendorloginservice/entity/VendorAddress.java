package com.vendorloginservice.entity;


import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table( name = "VENDOR_ADDRESS", schema = "VENDOR")
public class VendorAddress implements Serializable{
	
	private static final long serialVersionUID = 7820591758908747440L;
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private long v_address_id;
	private String v_address1;
	private String v_city;
	private String v_state;
	private String v_zipcode;

	@OneToOne
    @JoinColumn(name = "vendorId") // FK in CUST_ADDRESS table
	@JsonBackReference
    private VendorDetails vendor;

	public long getV_address_id() {
		return v_address_id;
	}

	public void setV_address_id(long v_address_id) {
		this.v_address_id = v_address_id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getV_address1() {
		return v_address1;
	}

	public void setV_address1(String v_address1) {
		this.v_address1 = v_address1;
	}

	public String getV_city() {
		return v_city;
	}

	public void setV_city(String v_city) {
		this.v_city = v_city;
	}

	public String getV_state() {
		return v_state;
	}

	public void setV_state(String v_state) {
		this.v_state = v_state;
	}

	public String getV_zipcode() {
		return v_zipcode;
	}

	public void setV_zipcode(String v_zipcode) {
		this.v_zipcode = v_zipcode;
	}

	public VendorDetails getVendor() {
		return vendor;
	}

	public void setVendor(VendorDetails vendor) {
		this.vendor = vendor;
	}

	
	public VendorAddress() {
		
	}

	@Override
	public String toString() {
		return "VendorAddress [v_address_id=" + v_address_id + ", v_address1=" + v_address1 + ", v_city=" + v_city
				+ ", v_state=" + v_state + ", v_zipcode=" + v_zipcode + ", vendor=" + vendor + "]";
	}

	public VendorAddress(long v_address_id, String v_address1, String v_city, String v_state, String v_zipcode,
			VendorDetails vendor) {
		super();
		this.v_address_id = v_address_id;
		this.v_address1 = v_address1;
		this.v_city = v_city;
		this.v_state = v_state;
		this.v_zipcode = v_zipcode;
		this.vendor = vendor;
	}

	
	
}
