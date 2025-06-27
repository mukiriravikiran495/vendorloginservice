package com.vendorloginservice.domain;

import com.vendorloginservice.exceptions.StatusHandler;

public class VendorResponse {

	private VendorDTO vendorDTO;
	private StatusHandler statusHandler;
	public VendorDTO getVendorDTO() {
		return vendorDTO;
	}
	public void setVendorDTO(VendorDTO vendorDTO) {
		this.vendorDTO = vendorDTO;
	}
	public StatusHandler getStatusHandler() {
		return statusHandler;
	}
	public void setStatusHandler(StatusHandler statusHandler) {
		this.statusHandler = statusHandler;
	}
	
}
