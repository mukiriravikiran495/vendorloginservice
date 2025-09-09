package com.vendorloginservice.domain;

import com.vendorloginservice.exceptions.StatusHandler;

public class VendorResponse {

	private VendorDetailsDTO vendorDetailsDTO;
	public VendorDetailsDTO getVendorDetailsDTO() {
		return vendorDetailsDTO;
	}
	public void setVendorDetailsDTO(VendorDetailsDTO vendorDetailsDTO) {
		this.vendorDetailsDTO = vendorDetailsDTO;
	}
	public StatusHandler getStatusHandler() {
		return statusHandler;
	}
	public void setStatusHandler(StatusHandler statusHandler) {
		this.statusHandler = statusHandler;
	}
	private StatusHandler statusHandler;
	
}
