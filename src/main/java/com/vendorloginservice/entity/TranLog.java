package com.vendorloginservice.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TRANLOG", schema = "LOG")
public class TranLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Oracle identity (works if column has IDENTITY)
    @Column(name = "TRANLOGID", nullable = false)
    private Long tranlogId;

    @Column(name = "CORRELATIONID", nullable = false, length = 100)
    private String correlationId;

    @Column(name = "TOKENUUID", length = 100)
    private String tokenUuid;

    @Column(name = "CUSTID")
    private Long custId;
    
    @Column(name = "VENDORID")
    private Long vendorId;
    
    @Column(name = "BOOKINGID")
    private Long bookingId;

    @Column(name = "ACTION", nullable = false, length = 50)
    private String action;

    @Lob
    @Column(name = "REQUEST_PAYLOAD")
    private String requestPayload;

    @Lob
    @Column(name = "RESPONSE_PAYLOAD")
    private String responsePayload;

    @Column(name = "STATUS", nullable = false, length = 20)
    private String status;

    @Lob
    @Column(name = "ERROR_MESSAGE")
    private String errorMessage;

    @Column(name = "CREATEDAT")
	private LocalDateTime createdAt = LocalDateTime.now();

	public TranLog(Long tranlogId, String correlationId, String tokenUuid, Long custId, Long vendorId, Long bookingId,
			String action, String requestPayload, String responsePayload, String status, String errorMessage,
			LocalDateTime createdAt) {
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
	}

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public Long getVendorId() {
		return vendorId;
	}

	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
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

	public Long getCustId() {
		return custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
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

	public TranLog(Long tranlogId, String correlationId, String tokenUuid, Long custId, String action,
			String requestPayload, String responsePayload, String status, String errorMessage,
			LocalDateTime createdAt) {
		super();
		this.tranlogId = tranlogId;
		this.correlationId = correlationId;
		this.tokenUuid = tokenUuid;
		this.custId = custId;
		this.action = action;
		this.requestPayload = requestPayload;
		this.responsePayload = responsePayload;
		this.status = status;
		this.errorMessage = errorMessage;
		this.createdAt = createdAt;
	}

	public TranLog() {
		super();
	}

	@Override
	public String toString() {
		return "TranLog [tranlogId=" + tranlogId + ", correlationId=" + correlationId + ", tokenUuid=" + tokenUuid
				+ ", custId=" + custId + ", action=" + action + ", requestPayload=" + requestPayload
				+ ", responsePayload=" + responsePayload + ", status=" + status + ", errorMessage=" + errorMessage
				+ ", createdAt=" + createdAt + "]";
	}
    
    
    
}
