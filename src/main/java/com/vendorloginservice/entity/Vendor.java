package com.vendorloginservice.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table( name = "VENDOR", schema = "VENDOR")
public class Vendor {
	
	@Id
	@Column(name = "VENDORID")
    private Long vendorId;

    @Column(name = "VFIRSTNAME")
    private String vfirstname;

    @Column(name = "VLASTNAME")
    private String vLastname;

    @Column(name = "VMOBILE", nullable = false)
    private String vMobile;

    @Column(name = "VEMAIL")
    private String vEmail;

    @Column(name = "VADDRESS1")
    private String vAddress1;

    @Column(name = "VCITY")
    private String vCity;

    @Column(name = "VSTATE")
    private String vState;

    @Column(name = "VZIPCODE")
    private String vZipcode;

    @Column(name = "BASEPRICEPERKM")
    private int basePricePerKM;

    @Column(name = "PRICEPERKG")
    private int pricePerKG;

    @Column(name = "AVGDELIVERYTIMEINDAYS")
    private int avgDeliveryTimeInDays;

    @Column(name = "ESTIMATEDPRICE")
    private int estimatedPrice;

    @Column(name = "ESTIMATEDDELIVERYDATE")
    private String estimatedDeliveryDate;
    
    private LocalDateTime createdAt = LocalDateTime.now();
    private String createdBy;
    
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
	public int getBasePricePerKM() {
		return basePricePerKM;
	}
	public void setBasePricePerKM(int basePricePerKM) {
		this.basePricePerKM = basePricePerKM;
	}
	public int getPricePerKG() {
		return pricePerKG;
	}
	public void setPricePerKG(int pricePerKG) {
		this.pricePerKG = pricePerKG;
	}
	public int getAvgDeliveryTimeInDays() {
		return avgDeliveryTimeInDays;
	}
	public void setAvgDeliveryTimeInDays(int avgDeliveryTimeInDays) {
		this.avgDeliveryTimeInDays = avgDeliveryTimeInDays;
	}
	public int getEstimatedPrice() {
		return estimatedPrice;
	}
	public void setEstimatedPrice(int estimatedPrice) {
		this.estimatedPrice = estimatedPrice;
	}
	public String getEstimatedDeliveryDate() {
		return estimatedDeliveryDate;
	}
	public void setEstimatedDeliveryDate(String estimatedDeliveryDate) {
		this.estimatedDeliveryDate = estimatedDeliveryDate;
	}
	@Override
	public String toString() {
		return "Vendor [vendorId=" + vendorId + ", vfirstname=" + vfirstname + ", vLastname=" + vLastname + ", vMobile="
				+ vMobile + ", vEmail=" + vEmail + ", vAddress1=" + vAddress1 + ", vCity=" + vCity + ", vState="
				+ vState + ", vZipcode=" + vZipcode + ", basePricePerKM=" + basePricePerKM + ", pricePerKG="
				+ pricePerKG + ", avgDeliveryTimeInDays=" + avgDeliveryTimeInDays + ", estimatedPrice=" + estimatedPrice
				+ ", estimatedDeliveryDate=" + estimatedDeliveryDate + "]";
	}
	
	
}
