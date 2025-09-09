package com.vendorloginservice.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "VENDOR_AUTH")
@IdClass(VendorAuth_PK.class)
public class VendorAuth implements Serializable{

	private static final long serialVersionUID = -3172379865527131977L;
	@Id
	@Column(name = "VENDORID", nullable = false, length = 255)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vendorlogin_seq_gen")
	@SequenceGenerator(name = "vendorlogin_seq_gen", sequenceName = "VENDORLOGIN_SEQ", allocationSize = 1)
	private long vendorId;
	
	@Column(name = "MOBILE", nullable = false, length = 255)
    private String mobile;

    @Column(name = "COMPANYNAME", length = 255)
    private String companyName;

    @Column(name = "OWNERNAME", length = 255)
    private String ownerName;

    @Column(name = "EMAIL", length = 100)
    private String email;

    @Column(name = "OTP", length = 50)
    private String otp;

    @Column(name = "PASSWORD", length = 255)
    private String password;

    @Column(name = "CREATEDAT")
    @JsonProperty("createdAt")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
	private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "CREATEDBY")
    private Long createdBy;

    @Column(name = "UPDATEDAT")
	@JsonProperty("updatedAt")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
	private LocalDateTime updatedAt = LocalDateTime.now();

    @Column(name = "UPDATEDBY")
    private Long updatedBy;
    
    @Column(name = "ISVERIFIED", length = 1)
    private String isVerified; // 'Y' or 'N'
    

	public String getIsVerified() {
		return isVerified;
	}

	public void setIsVerified(String isVerified) {
		this.isVerified = isVerified;
	}

	public long getVendorId() {
		return vendorId;
	}

	public void setVendorId(long vendorId) {
		this.vendorId = vendorId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public VendorAuth(long vendorId, String mobile, String companyName, String ownerName, String email,
			String otp, String password, LocalDateTime createdAt, Long createdBy, LocalDateTime updatedAt,
			Long updatedBy) {
		super();
		this.vendorId = vendorId;
		this.mobile = mobile;
		this.companyName = companyName;
		this.ownerName = ownerName;
		this.email = email;
		this.otp = otp;
		this.password = password;
		this.createdAt = createdAt;
		this.createdBy = createdBy;
		this.updatedAt = updatedAt;
		this.updatedBy = updatedBy;
	}
	
	public VendorAuth(String mobile, String otp) {
		super();
		this.mobile = mobile;
		this.otp = otp;
	}

	@Override
	public String toString() {
		return "VendorCredentials [vendorId=" + vendorId + ", mobile=" + mobile + ", companyName=" + companyName
				+ ", ownerName=" + ownerName + ", email=" + email + ", otp=" + otp + ", password=" + password
				+ ", createdAt=" + createdAt + ", createdBy=" + createdBy + ", updatedAt=" + updatedAt + ", updatedBy="
				+ updatedBy + "]";
	}

	public VendorAuth() {
		super();
	}

	
}
