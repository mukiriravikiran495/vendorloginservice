package com.vendorloginservice.service;

import java.util.List;

import com.vendorloginservice.domain.SendOTPRequest;
import com.vendorloginservice.domain.SendOTPResponse;
import com.vendorloginservice.domain.VendorDetailsDTO;
import com.vendorloginservice.entity.VendorCredentials;
import com.vendorloginservice.exceptions.StatusHandler;

public interface VendorLoginService {

	SendOTPResponse sendOTP(SendOTPRequest sendOTPRequest, SendOTPResponse sendOTPResponse,
			StatusHandler statusHandler);

	SendOTPResponse verifyOTP(SendOTPRequest sendOTPRequest, SendOTPResponse sendOTPResponse,
			StatusHandler statusHandler);

	List<VendorCredentials> getall();

	List<VendorDetailsDTO> getdetails();

}
