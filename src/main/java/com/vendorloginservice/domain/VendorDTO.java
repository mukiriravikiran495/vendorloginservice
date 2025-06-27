package com.vendorloginservice.domain;

import java.time.LocalDateTime;

public class VendorDTO {

	private Long vendorId;
	private String vfirstname;
	private String vLastname;
	private String vMobile;
	private String vEmail;
	private String vAddress1;
	private String vCity;
	private String vState;
	private String vZipcode;
	private int BasePricePerKM;
	private int pricePerKG;
	private int EstimatedPrice;
	private int AvgDeliveryTimeInDays;
	private String EstimatedDeliveryDate;
	private LocalDateTime createdAt = LocalDateTime.now();
    private String createdBy; 
	
    

	public int getBasePricePerKM() {
		return BasePricePerKM;
	}

	public void setBasePricePerKM(int basePricePerKM) {
		BasePricePerKM = basePricePerKM;
	}

	public int getPricePerKG() {
		return pricePerKG;
	}

	public void setPricePerKG(int pricePerKG) {
		this.pricePerKG = pricePerKG;
	}

	public int getEstimatedPrice() {
		return EstimatedPrice;
	}

	public void setEstimatedPrice(int estimatedPrice) {
		EstimatedPrice = estimatedPrice;
	}

	public int getAvgDeliveryTimeInDays() {
		return AvgDeliveryTimeInDays;
	}

	public void setAvgDeliveryTimeInDays(int avgDeliveryTimeInDays) {
		AvgDeliveryTimeInDays = avgDeliveryTimeInDays;
	}

	public String getEstimatedDeliveryDate() {
		return EstimatedDeliveryDate;
	}

	public void setEstimatedDeliveryDate(String estimatedDeliveryDate) {
		EstimatedDeliveryDate = estimatedDeliveryDate;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getvAddress1() {
		return vAddress1;
	}

	public void setvAddress1(String vAddress1) {
		this.vAddress1 = vAddress1;
	}

	public String getvCity() {
		return vCity;
	}

	public void setvCity(String vCity) {
		this.vCity = vCity;
	}

	public String getvState() {
		return vState;
	}

	public void setvState(String vState) {
		this.vState = vState;
	}

	public String getvZipcode() {
		return vZipcode;
	}

	public void setvZipcode(String vZipcode) {
		this.vZipcode = vZipcode;
	}

	
	public Long getVendorId() {
		return vendorId;
	}

	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}

	public String getVfirstname() {
		return vfirstname;
	}

	public void setVfirstname(String vfirstname) {
		this.vfirstname = vfirstname;
	}

	public String getvLastname() {
		return vLastname;
	}

	public void setvLastname(String vLastname) {
		this.vLastname = vLastname;
	}

	public String getvMobile() {
		return vMobile;
	}

	public void setvMobile(String vMobile) {
		this.vMobile = vMobile;
	}

	public String getvEmail() {
		return vEmail;
	}

	public void setvEmail(String vEmail) {
		this.vEmail = vEmail;
	}

	@Override
	public String toString() {
		return "VendorDTO [vendorId=" + vendorId + ", vfirstname=" + vfirstname + ", vLastname=" + vLastname
				+ ", vMobile=" + vMobile + ", vEmail=" + vEmail + ", vAddress1=" + vAddress1 + ", vCity=" + vCity
				+ ", vState=" + vState + ", vZipcode=" + vZipcode + ", BasePricePerKM=" + BasePricePerKM
				+ ", pricePerKG=" + pricePerKG + ", EstimatedPrice=" + EstimatedPrice + ", AvgDeliveryTimeInDays="
				+ AvgDeliveryTimeInDays + ", EstimatedDeliveryDate=" + EstimatedDeliveryDate + ", createdAt="
				+ createdAt + ", createdBy=" + createdBy + "]";
	}

    
}
