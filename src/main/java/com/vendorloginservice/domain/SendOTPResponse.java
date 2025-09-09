package com.vendorloginservice.domain;

import java.io.Serial;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.vendorloginservice.exceptions.StatusHandler;

public class SendOTPResponse {

	@Serial
    private static final long serialVersionUID = 1L;
	
	private String mobile;
	private String otp;
	private String message;
	private long vendorId;
	private String companyName;
	private String ownerName;
	private String accessToken;   // JWT string
    private String tokenUuid;     // UUID of DB row (cust_tokens table)
    private String correlationId; // same as JWT "jti"
    private String tokenType = "Bearer";
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime issuedAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime expiresAt;
    private String isActive;  // 'Y' or 'N'
	private boolean otpVerified = false;
	private StatusHandler statusHandler;
	private String appId;
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public long getVendorId() {
		return vendorId;
	}
	public void setVendorId(long vendorId) {
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
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getTokenUuid() {
		return tokenUuid;
	}
	public void setTokenUuid(String tokenUuid) {
		this.tokenUuid = tokenUuid;
	}
	public String getCorrelationId() {
		return correlationId;
	}
	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}
	public String getTokenType() {
		return tokenType;
	}
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
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
	public boolean isOtpVerified() {
		return otpVerified;
	}
	public void setOtpVerified(boolean otpVerified) {
		this.otpVerified = otpVerified;
	}
	public StatusHandler getStatusHandler() {
		return statusHandler;
	}
	public void setStatusHandler(StatusHandler statusHandler) {
		this.statusHandler = statusHandler;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public SendOTPResponse(String mobile, String otp, String message, long vendorId, String companyName,
			String ownerName, String accessToken, String tokenUuid, String correlationId, String tokenType,
			LocalDateTime issuedAt, LocalDateTime expiresAt, String isActive, boolean otpVerified,
			StatusHandler statusHandler, String appId) {
		super();
		this.mobile = mobile;
		this.otp = otp;
		this.message = message;
		this.vendorId = vendorId;
		this.companyName = companyName;
		this.ownerName = ownerName;
		this.accessToken = accessToken;
		this.tokenUuid = tokenUuid;
		this.correlationId = correlationId;
		this.tokenType = tokenType;
		this.issuedAt = issuedAt;
		this.expiresAt = expiresAt;
		this.isActive = isActive;
		this.otpVerified = otpVerified;
		this.statusHandler = statusHandler;
		this.appId = appId;
	}
	public SendOTPResponse() {
		super();
	}
	@Override
	public String toString() {
		return "SendOTPResponse [mobile=" + mobile + ", otp=" + otp + ", message=" + message + ", vendorId=" + vendorId
				+ ", companyName=" + companyName + ", ownerName=" + ownerName + ", accessToken=" + accessToken
				+ ", tokenUuid=" + tokenUuid + ", correlationId=" + correlationId + ", tokenType=" + tokenType
				+ ", issuedAt=" + issuedAt + ", expiresAt=" + expiresAt + ", isActive=" + isActive + ", otpVerified="
				+ otpVerified + ", statusHandler=" + statusHandler + ", appId=" + appId + "]";
	}
	
	
}
