package com.vendorloginservice.domain;

import java.time.LocalDateTime;

import com.vendorloginservice.exceptions.StatusHandler;


public class TranLogDTO {

	private Long tranlogId;
    private String correlationId;
    private String tokenUuid;
    private Long custId;
    private Long vendorId;
    private Long bookingId;
    private String action;
    private String requestPayload;
    private String responsePayload;
    private String status;
    private String errorMessage;
    private LocalDateTime createdAt;
    private StatusHandler statusHandler;
    
	public TranLogDTO(Long tranlogId, String correlationId, String tokenUuid, Long custId, Long vendorId,
			Long bookingId, String action, String requestPayload, String responsePayload, String status,
			String errorMessage, LocalDateTime createdAt, StatusHandler statusHandler) {
		super();
		this.tranlogId = tranlogId;
		this.correlationId = correlationId;
		this.tokenUuid = tokenUuid;
		this.custId = custId;
		this.vendorId = vendorId;
		this.bookingId = bookingId;
		this.action = action;
		this.requestPayload = requestPayload;
		this.responsePayload = responsePayload;
		this.status = status;
		this.errorMessage = errorMessage;
		this.createdAt = createdAt;
		this.statusHandler = statusHandler;
	}
	public Long getBookingId() {
		return bookingId;
	}
	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}
	public Long getCustId() {
		return custId;
	}
	public void setCustId(Long custId) {
		this.custId = custId;
	}
	public Long getTranlogId() {
		return tranlogId;
	}
	public void setTranlogId(Long tranlogId) {
		this.tranlogId = tranlogId;
	}
	public String getCorrelationId() {
		return correlationId;
	}
	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}
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
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getRequestPayload() {
		return requestPayload;
	}
	public void setRequestPayload(String requestPayload) {
		this.requestPayload = requestPayload;
	}
	public String getResponsePayload() {
		return responsePayload;
	}
	public void setResponsePayload(String responsePayload) {
		this.responsePayload = responsePayload;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public StatusHandler getStatusHandler() {
		return statusHandler;
	}
	public void setStatusHandler(StatusHandler statusHandler) {
		this.statusHandler = statusHandler;
	}
	
	public TranLogDTO(Long tranlogId, String correlationId, String tokenUuid, Long vendorId, String action,
			String requestPayload, String responsePayload, String status, String errorMessage, LocalDateTime createdAt,
			StatusHandler statusHandler) {
		super();
		this.tranlogId = tranlogId;
		this.correlationId = correlationId;
		this.tokenUuid = tokenUuid;
		this.vendorId = vendorId;
		this.action = action;
		this.requestPayload = requestPayload;
		this.responsePayload = responsePayload;
		this.status = status;
		this.errorMessage = errorMessage;
		this.createdAt = createdAt;
		this.statusHandler = statusHandler;
	}
	@Override
	public String toString() {
		return "TranLogDTO [tranlogId=" + tranlogId + ", correlationId=" + correlationId + ", tokenUuid=" + tokenUuid
				+ ", vendorId=" + vendorId + ", action=" + action + ", requestPayload=" + requestPayload
				+ ", responsePayload=" + responsePayload + ", status=" + status + ", errorMessage=" + errorMessage
				+ ", createdAt=" + createdAt + ", statusHandler=" + statusHandler + "]";
	}
	
	public TranLogDTO() {
		super();
	}
    
}
