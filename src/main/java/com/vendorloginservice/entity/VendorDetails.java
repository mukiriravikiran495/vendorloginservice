package com.vendorloginservice.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "VENDOR_DETAILS", schema = "VENDOR")
public class VendorDetails implements Serializable {

	@Id
	@Column(name = "VENDORID", nullable = false)
	private Long vendorId;

	@Column(name = "COMPANYNAME", length = 255)
	private String companyName;

	@Column(name = "OWNERNAME", length = 255)
	private String ownerName;

	@Column(name = "VMOBILE", nullable = false, length = 255)
	private String vMobile;

	@Column(name = "VEMAIL", length = 225)
	private String vEmail;

	@Column(name = "VADDRESS1", length = 50)
	private String vAddress1;

	@Column(name = "VADDRESS2", length = 255)
	private String vAddress2;

	@Column(name = "VCITY", length = 100)
	private String vCity;

	@Column(name = "VSTATE", length = 100)
	private String vState;

	@Column(name = "VZIPCODE", length = 10)
	private String vZipCode;

	@Column(name = "CREATEDAT")
	@JsonProperty("createdAt")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy HH:mm:ss.SSS")
	private LocalDateTime createdAt;

	@Column(name = "CREATEDBY")
	private Long createdBy;

	@Column(name = "UPDATEDAT")
	@JsonProperty("updatedAt")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy HH:mm:ss.SSS")
	private LocalDateTime updatedAt;

	@Column(name = "UPDATEDBY")
	private Long updatedBy;

	@Column(name = "VPICKUPLATTITUDE")
	private Double vPickupLatitude;

	@Column(name = "VPICKUPLONGITUDE")
	private Double vPickupLongitude;

	@Column(name = "VPIMAGE", length = 255)
	private String vpImage;

	@Column(name = "VPIMAGE_URL", length = 255)
	private String vpImageUrl;

	@Column(name = "VPIMAGE_FILENAME", length = 100)
	private String vpImageFileName;

	@Column(name = "VPIMAGE_FILEEXTENSION", length = 100)
	private String vpImageFileExtension;

	public Long getVendorId() {
		return vendorId;
	}

	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}

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

	public String getvAddress2() {
		return vAddress2;
	}

	public void setvAddress2(String vAddress2) {
		this.vAddress2 = vAddress2;
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

	public String getvZipCode() {
		return vZipCode;
	}

	public void setvZipCode(String vZipCode) {
		this.vZipCode = vZipCode;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Double getvPickupLatitude() {
		return vPickupLatitude;
	}

	public void setvPickupLatitude(Double vPickupLatitude) {
		this.vPickupLatitude = vPickupLatitude;
	}

	public Double getvPickupLongitude() {
		return vPickupLongitude;
	}

	public void setvPickupLongitude(Double vPickupLongitude) {
		this.vPickupLongitude = vPickupLongitude;
	}

	public String getVpImage() {
		return vpImage;
	}

	public void setVpImage(String vpImage) {
		this.vpImage = vpImage;
	}

	public String getVpImageUrl() {
		return vpImageUrl;
	}

	public void setVpImageUrl(String vpImageUrl) {
		this.vpImageUrl = vpImageUrl;
	}

	public String getVpImageFileName() {
		return vpImageFileName;
	}

	public void setVpImageFileName(String vpImageFileName) {
		this.vpImageFileName = vpImageFileName;
	}

	public String getVpImageFileExtension() {
		return vpImageFileExtension;
	}

	public void setVpImageFileExtension(String vpImageFileExtension) {
		this.vpImageFileExtension = vpImageFileExtension;
	}

	public VendorDetails(Long vendorId, String companyName, String ownerName, String vMobile, String vEmail,
			String vAddress1, String vAddress2, String vCity, String vState, String vZipCode, LocalDateTime createdAt,
			Long createdBy, LocalDateTime updatedAt, Long updatedBy, Double vPickupLatitude, Double vPickupLongitude,
			String vpImage, String vpImageUrl, String vpImageFileName, String vpImageFileExtension) {
		super();
		this.vendorId = vendorId;
		this.companyName = companyName;
		this.ownerName = ownerName;
		this.vMobile = vMobile;
		this.vEmail = vEmail;
		this.vAddress1 = vAddress1;
		this.vAddress2 = vAddress2;
		this.vCity = vCity;
		this.vState = vState;
		this.vZipCode = vZipCode;
		this.createdAt = createdAt;
		this.createdBy = createdBy;
		this.updatedAt = updatedAt;
		this.updatedBy = updatedBy;
		this.vPickupLatitude = vPickupLatitude;
		this.vPickupLongitude = vPickupLongitude;
		this.vpImage = vpImage;
		this.vpImageUrl = vpImageUrl;
		this.vpImageFileName = vpImageFileName;
		this.vpImageFileExtension = vpImageFileExtension;
	}

	public VendorDetails() {
		super();
	}

	@Override
	public String toString() {
		return "VendorDetails [vendorId=" + vendorId + ", companyName=" + companyName + ", ownerName=" + ownerName
				+ ", vMobile=" + vMobile + ", vEmail=" + vEmail + ", vAddress1=" + vAddress1 + ", vAddress2="
				+ vAddress2 + ", vCity=" + vCity + ", vState=" + vState + ", vZipCode=" + vZipCode + ", createdAt="
				+ createdAt + ", createdBy=" + createdBy + ", updatedAt=" + updatedAt + ", updatedBy=" + updatedBy
				+ ", vPickupLatitude=" + vPickupLatitude + ", vPickupLongitude=" + vPickupLongitude + ", vpImage="
				+ vpImage + ", vpImageUrl=" + vpImageUrl + ", vpImageFileName=" + vpImageFileName
				+ ", vpImageFileExtension=" + vpImageFileExtension + "]";
	}
	
	
	
}
