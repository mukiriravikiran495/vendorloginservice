package com.vendorloginservice.domain;

import java.time.LocalDateTime;
import com.vendorloginservice.exceptions.StatusHandler;

public class TranLogResponse {
	
    private Long tranlogId;
    private String correlationId;
    private String tokenUuid;
    private Long vendorId;
    private String action;
    private String requestPayload;
    private String responsePayload;
    private String status;
    private String errorMessage;
    private LocalDateTime createdAt;
    private StatusHandler statusHandler;
    
	public StatusHandler getStatusHandler() {
		return statusHandler;
	}
	public void setStatusHandler(StatusHandler statusHandler) {
		this.statusHandler = statusHandler;
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
    
}
