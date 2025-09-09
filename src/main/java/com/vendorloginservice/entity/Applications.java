package com.vendorloginservice.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "APPLICATIONS")
public class Applications {

    @Id
    @Column(name = "APPID", nullable = false, length = 50)
    private String appId;   // Example: "SHFTYNG-MOBILE", "SHFTYNG-WEB"

    @Column(name = "APPNAME", nullable = false, length = 100)
    private String appName; // Example: "Shiftyng Mobile Application"

    @Column(name = "APPSECRET", length = 255)
    private String appSecret; // Optional, can store secret/hashed key

    @Column(name = "ISACTIVE", length = 1)
    private String isActive; // 'Y' or 'N'

    @CreationTimestamp
    @Column(name = "CREATEDAT", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "UPDATEDAT")
    private LocalDateTime updatedAt;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Applications(String appId, String appName, String appSecret, String isActive, LocalDateTime createdAt,
			LocalDateTime updatedAt) {
		super();
		this.appId = appId;
		this.appName = appName;
		this.appSecret = appSecret;
		this.isActive = isActive;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Applications() {
		super();
	}

    
}
