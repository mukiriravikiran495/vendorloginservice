package com.vendorloginservice.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "VENDOR_TOKENS")
public class VendorTokens {

	@Id
    @Column(name = "TOKENUUID", nullable = false, length = 255)
    private String tokenUuid;

    @Column(name = "VENDORID")
    private Long vendorId;

    @Column(name = "MOBILE", length = 255)
    private String mobile;

    @Column(name = "ACCESSTOKEN", nullable = false, length = 255)
    private String accessToken;

    @Column(name = "ISSUEDAT")
    private LocalDateTime issuedAt;

    @Column(name = "EXPIRESAT")
    private LocalDateTime expiresAt;

    @Column(name = "ISACTIVE", length = 1)
    private String isActive;

    @Column(name = "DEVICEID", length = 255)
    private String deviceId;

    @Column(name = "DEVICENAME", length = 255)
    private String deviceName;

    @Column(name = "APPID", length = 50)
    private String appId;

    @Column(name = "CORRELATIONID", length = 255)
    private String correlationId;

	public String getTokenUuid() {
		return tokenUuid;
	}

	public void setTokenUuid(String tokenUuid) {
		this.tokenUuid = tokenUuid;
	}

	public Long getVendorId() {
		return vendorId;
	}

	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public LocalDateTime getIssuedAt() {
		return issuedAt;
	}

	public void setIssuedAt(LocalDateTime issuedAt) {
		this.issuedAt = issuedAt;
	}

	public LocalDateTime getExpiresAt() {
		return expiresAt;
	}

	public void setExpiresAt(LocalDateTime expiresAt) {
		this.expiresAt = expiresAt;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getCorrelationId() {
		return correlationId;
	}

	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}

	public VendorTokens(String tokenUuid, Long vendorId, String mobile, String accessToken, LocalDateTime issuedAt,
			LocalDateTime expiresAt, String isActive, String deviceId, String deviceName, String appId,
			String correlationId) {
		super();
		this.tokenUuid = tokenUuid;
		this.vendorId = vendorId;
		this.mobile = mobile;
		this.accessToken = accessToken;
		this.issuedAt = issuedAt;
		this.expiresAt = expiresAt;
		this.isActive = isActive;
		this.deviceId = deviceId;
		this.deviceName = deviceName;
		this.appId = appId;
		this.correlationId = correlationId;
	}

	public VendorTokens() {
		super();
	}

	@Override
	public String toString() {
		return "VendorTokens [tokenUuid=" + tokenUuid + ", vendorId=" + vendorId + ", mobile=" + mobile
				+ ", accessToken=" + accessToken + ", issuedAt=" + issuedAt + ", expiresAt=" + expiresAt + ", isActive="
				+ isActive + ", deviceId=" + deviceId + ", deviceName=" + deviceName + ", appId=" + appId
				+ ", correlationId=" + correlationId + "]";
	}
    
    
}