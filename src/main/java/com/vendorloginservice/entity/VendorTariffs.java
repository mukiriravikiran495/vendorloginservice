package com.vendorloginservice.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "VENDOR_TARIFFS", schema = "VENDOR")
public class VendorTariffs {

	@Id
    @Column(name = "TARIFFID", nullable = false, length = 50)
    private String tariffId;

    @Column(name = "VENDORID", nullable = false)
    private Long vendorId;

    @Column(name = "SERVICETYPE", nullable = false, length = 50)
    private String serviceType;

    @Column(name = "VEHICLETYPE", length = 50)
    private String vehicleType;

    @Column(name = "BASE_PRICE", nullable = false, precision = 10, scale = 2)
    private BigDecimal basePrice;

    @Column(name = "PRICE_PER_KM", precision = 10, scale = 2)
    private BigDecimal pricePerKm;

    @Column(name = "PRICE_PER_KG", precision = 10, scale = 2)
    private BigDecimal pricePerKg;

    @Column(name = "MIN_DISTANCE")
    private Long minDistance;

    @Column(name = "MAX_DISTANCE")
    private Long maxDistance;

    @Column(name = "EFFECTIVE_FROM")
    private LocalDate effectiveFrom;

    @Column(name = "EFFECTIVE_TO")
    private LocalDate effectiveTo;

    @Column(name = "CREATEDAT")
    private LocalDateTime createdAt;

    @Column(name = "CREATEDBY")
    private Long createdBy;

    @Column(name = "UPDATEDAT")
    private LocalDateTime updatedAt;

    @Column(name = "UPDATEDBY")
    private Long updatedBy;

	public String getTariffId() {
		return tariffId;
	}

	public void setTariffId(String tariffId) {
		this.tariffId = tariffId;
	}

	public Long getVendorId() {
		return vendorId;
	}

	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public BigDecimal getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(BigDecimal basePrice) {
		this.basePrice = basePrice;
	}

	public BigDecimal getPricePerKm() {
		return pricePerKm;
	}

	public void setPricePerKm(BigDecimal pricePerKm) {
		this.pricePerKm = pricePerKm;
	}

	public BigDecimal getPricePerKg() {
		return pricePerKg;
	}

	public void setPricePerKg(BigDecimal pricePerKg) {
		this.pricePerKg = pricePerKg;
	}

	public Long getMinDistance() {
		return minDistance;
	}

	public void setMinDistance(Long minDistance) {
		this.minDistance = minDistance;
	}

	public Long getMaxDistance() {
		return maxDistance;
	}

	public void setMaxDistance(Long maxDistance) {
		this.maxDistance = maxDistance;
	}

	public LocalDate getEffectiveFrom() {
		return effectiveFrom;
	}

	public void setEffectiveFrom(LocalDate effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
	}

	public LocalDate getEffectiveTo() {
		return effectiveTo;
	}

	public void setEffectiveTo(LocalDate effectiveTo) {
		this.effectiveTo = effectiveTo;
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

	public VendorTariffs(String tariffId, Long vendorId, String serviceType, String vehicleType, BigDecimal basePrice,
			BigDecimal pricePerKm, BigDecimal pricePerKg, Long minDistance, Long maxDistance, LocalDate effectiveFrom,
			LocalDate effectiveTo, LocalDateTime createdAt, Long createdBy, LocalDateTime updatedAt, Long updatedBy) {
		super();
		this.tariffId = tariffId;
		this.vendorId = vendorId;
		this.serviceType = serviceType;
		this.vehicleType = vehicleType;
		this.basePrice = basePrice;
		this.pricePerKm = pricePerKm;
		this.pricePerKg = pricePerKg;
		this.minDistance = minDistance;
		this.maxDistance = maxDistance;
		this.effectiveFrom = effectiveFrom;
		this.effectiveTo = effectiveTo;
		this.createdAt = createdAt;
		this.createdBy = createdBy;
		this.updatedAt = updatedAt;
		this.updatedBy = updatedBy;
	}

	public VendorTariffs() {
		super();
	}

	@Override
	public String toString() {
		return "VendorTariffs [tariffId=" + tariffId + ", vendorId=" + vendorId + ", serviceType=" + serviceType
				+ ", vehicleType=" + vehicleType + ", basePrice=" + basePrice + ", pricePerKm=" + pricePerKm
				+ ", pricePerKg=" + pricePerKg + ", minDistance=" + minDistance + ", maxDistance=" + maxDistance
				+ ", effectiveFrom=" + effectiveFrom + ", effectiveTo=" + effectiveTo + ", createdAt=" + createdAt
				+ ", createdBy=" + createdBy + ", updatedAt=" + updatedAt + ", updatedBy=" + updatedBy + "]";
	}
    
    
}
