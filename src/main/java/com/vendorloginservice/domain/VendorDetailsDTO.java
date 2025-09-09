package com.vendorloginservice.domain;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

public class VendorDetailsDTO {

	private Long vendorId;
	private String companyName;
	private String ownerName;
	private String vMobile;
	private String vEmail;
	private String vAddress1;
	private String vAddress2;
	private String vCity;
	private String vState;
	private String vZipCode;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy HH:mm:ss.SSS")
	private LocalDateTime createdAt;
	private Long createdBy;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy HH:mm:ss.SSS")
	private LocalDateTime updatedAt;
	private Long updatedBy;
	private Double vPickupLatitude;
	private Double vPickupLongitude;
	private String vpImage;
	private String vpImageUrl;
	private String vpImageFileName;
	private String vpImageFileExtension;
	private String accessToken;
	
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
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
	
}
