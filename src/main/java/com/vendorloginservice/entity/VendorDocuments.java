package com.vendorloginservice.entity;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "VENDOR_DOCUMENTS", schema = "VENDOR")
public class VendorDocuments {

	@Id
    @Column(name = "DOCUMENTID", nullable = false, length = 50)
    private String documentId;

    @Column(name = "VENDORID", nullable = false)
    private Long vendorId;

    @Column(name = "DOCTYPE", nullable = false, length = 50)
    private String docType;

    @Column(name = "DOCNUMBER", length = 100)
    private String docNumber;

    @Column(name = "DOCURL", nullable = false, length = 300)
    private String docUrl;

    @Column(name = "FILENAME", length = 255)
    private String fileName;

    @Column(name = "FILEEXTENSION", length = 20)
    private String fileExtension;

    @Column(name = "CREATEDAT")
    private LocalDateTime createdAt;

    @Column(name = "CREATEDBY")
    private Long createdBy;

    @Column(name = "UPDATEDAT")
    private LocalDateTime updatedAt;

    @Column(name = "UPDATEDBY")
    private Long updatedBy;

	public String getDocumentId() {
		return documentId;
	}

	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}

	public Long getVendorId() {
		return vendorId;
	}

	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public String getDocNumber() {
		return docNumber;
	}

	public void setDocNumber(String docNumber) {
		this.docNumber = docNumber;
	}

	public String getDocUrl() {
		return docUrl;
	}

	public void setDocUrl(String docUrl) {
		this.docUrl = docUrl;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileExtension() {
		return fileExtension;
	}

	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
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

	public VendorDocuments(String documentId, Long vendorId, String docType, String docNumber, String docUrl,
			String fileName, String fileExtension, LocalDateTime createdAt, Long createdBy, LocalDateTime updatedAt,
			Long updatedBy) {
		super();
		this.documentId = documentId;
		this.vendorId = vendorId;
		this.docType = docType;
		this.docNumber = docNumber;
		this.docUrl = docUrl;
		this.fileName = fileName;
		this.fileExtension = fileExtension;
		this.createdAt = createdAt;
		this.createdBy = createdBy;
		this.updatedAt = updatedAt;
		this.updatedBy = updatedBy;
	}

	public VendorDocuments() {
		super();
	}

	@Override
	public String toString() {
		return "VendorDocuments [documentId=" + documentId + ", vendorId=" + vendorId + ", docType=" + docType
				+ ", docNumber=" + docNumber + ", docUrl=" + docUrl + ", fileName=" + fileName + ", fileExtension="
				+ fileExtension + ", createdAt=" + createdAt + ", createdBy=" + createdBy + ", updatedAt=" + updatedAt
				+ ", updatedBy=" + updatedBy + "]";
	}
    
    
    
}
