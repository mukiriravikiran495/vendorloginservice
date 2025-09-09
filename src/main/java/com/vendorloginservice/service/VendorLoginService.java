package com.vendorloginservice.service;

import java.util.List;

import com.vendorloginservice.domain.SendOTPRequest;
import com.vendorloginservice.domain.SendOTPResponse;
import com.vendorloginservice.domain.VendorDetailsDTO;
import com.vendorloginservice.domain.VerifyOTPRequest;
import com.vendorloginservice.domain.VerifyOTPResponse;
import com.vendorloginservice.entity.VendorAuth;
import com.vendorloginservice.exceptions.StatusHandler;

public interface VendorLoginService {

	SendOTPResponse sendOTP(SendOTPRequest sendOTPRequest, SendOTPResponse sendOTPResponse, String appId,
			StatusHandler statusHandler);

	VerifyOTPResponse verifyOTP(VerifyOTPRequest verifyOTPRequest, String token, String appId, VerifyOTPResponse verifyOTPResponse,
			StatusHandler statusHandler);

	List<VendorAuth> getall();

	List<VendorDetailsDTO> getdetails();

}
