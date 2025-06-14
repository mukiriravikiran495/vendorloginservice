package com.vendorloginservice.controller;

import java.lang.invoke.MethodHandles;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vendorloginservice.constants.AppConstants;
import com.vendorloginservice.domain.SendOTPRequest;
import com.vendorloginservice.domain.SendOTPResponse;
import com.vendorloginservice.domain.TokenID;
import com.vendorloginservice.domain.VendorDetailsDTO;
import com.vendorloginservice.entity.VendorCredentials;
import com.vendorloginservice.exceptions.InvalidRequestException;
import com.vendorloginservice.exceptions.StatusHandler;
import com.vendorloginservice.service.VendorLoginService;
import com.vendorloginservice.utils.JwtUtil;

@RestController
@RequestMapping( path = "/v1/api/vendor")
public class VendorLoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	private final VendorLoginService service;
	private final JwtUtil jwtUtil;
	
	@Autowired
	public VendorLoginController(VendorLoginService service, JwtUtil jwtUtil) {
		this.service = service;
		this.jwtUtil = jwtUtil;
	}
	
	@GetMapping( value = "/getvendors")
	public List<VendorCredentials> getall(){
		return service.getall();
	}
	
	@GetMapping( value = "/getvendordetails")
	public List<VendorDetailsDTO> getdetails(){
		return service.getdetails();
	}
	
	@GetMapping( value = "/auth/token")
	public ResponseEntity<TokenID> getToken() {
		String token = jwtUtil.generateTokenId();
		TokenID tokenId = new TokenID();
		tokenId.setToken(token);
		long timestamp = new Date().getTime();
		tokenId.setExpires(timestamp);
		tokenId.setStatus("200");
		tokenId.setResult(AppConstants.TOKEN_GENERATED_SUCCESSFULLY);
		return new ResponseEntity<>(tokenId, HttpStatus.OK);
	}
	
	@PostMapping( value = "/sendotp")
	public ResponseEntity<SendOTPResponse> sendOTP(@RequestBody SendOTPRequest sendOTPRequest){
		logger.info("START : SendOTP Request Controller : "+sendOTPRequest);
		StatusHandler statusHandler = new StatusHandler();
		SendOTPResponse sendOTPResponse = new SendOTPResponse();
		try {
			if(null == sendOTPRequest.getMobile() || sendOTPRequest.getMobile().isEmpty()) {
				throw new InvalidRequestException(AppConstants.MOBILENUMBER_IS_REQUIRED);
			}
			if(sendOTPRequest.getMobile().matches("\\{d}")) {
				throw new InvalidRequestException(AppConstants.INVALID_MOBILENUMBER);
			}
			sendOTPResponse = service.sendOTP(sendOTPRequest, sendOTPResponse, statusHandler);
			ResponseEntity<SendOTPResponse> response = new ResponseEntity<>(sendOTPResponse, HttpStatus.OK);
			
			logger.info("END : Send OTP Controller : "+sendOTPResponse);
			logger.warn("END : Send OTP Controller : "+sendOTPResponse);
			return response;
		}catch(InvalidRequestException ex) {
			statusHandler.setErrorCode("400");
			statusHandler.setErrorMessage(ex.getMessage());
			sendOTPResponse.setStatusHandler(statusHandler);
			return new ResponseEntity<>(sendOTPResponse, HttpStatus.BAD_REQUEST);
		}catch(Exception ex) {
			statusHandler.setErrorCode("500");
			statusHandler.setErrorMessage(ex.getMessage());
			sendOTPResponse.setStatusHandler(statusHandler);
			return new ResponseEntity<>(sendOTPResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping( value = "/verifyotp")
	public ResponseEntity<SendOTPResponse> verifyOTP(@RequestBody SendOTPRequest sendOTPRequest){
		logger.info("START : Verify OTP Request : "+sendOTPRequest);
		StatusHandler statusHandler = new StatusHandler();
		SendOTPResponse sendOTPResponse = new SendOTPResponse();
		try {
			if( null == sendOTPRequest.getMobile() || sendOTPRequest.getMobile().isEmpty()) {
				throw new InvalidRequestException(AppConstants.OTP_IS_REQUIRED);
			}
			if( sendOTPRequest.getMobile().matches("\\{d}")) {
				throw new InvalidRequestException(AppConstants.INVALID_REQUEST);
			}
			
			String token = jwtUtil.generateToken(sendOTPRequest.getMobile());
			sendOTPResponse.setToken(token);
			
			sendOTPResponse = service.verifyOTP(sendOTPRequest, sendOTPResponse, statusHandler);
			ResponseEntity<SendOTPResponse> response = new ResponseEntity<>(sendOTPResponse, HttpStatus.OK);
			logger.info("END : Verify OTP Request : "+sendOTPResponse);
			return response;
		}catch(InvalidRequestException ex) {
			statusHandler.setErrorCode("400");
			statusHandler.setErrorMessage(ex.getMessage());
			sendOTPResponse.setStatusHandler(statusHandler);
			return new ResponseEntity<>(sendOTPResponse, HttpStatus.BAD_REQUEST);
		}catch(Exception ex) {
			statusHandler.setErrorCode("500");
			statusHandler.setErrorMessage(AppConstants.INTERNAL_SERVER_ERROR);
			sendOTPResponse.setStatusHandler(statusHandler);
			return new ResponseEntity<>(sendOTPResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PostMapping( value = "/resendotp")
	public ResponseEntity<SendOTPResponse> resendotp(@RequestBody SendOTPRequest sendOTPRequest){
		logger.info("START : Resend OTP Controller "+sendOTPRequest);
		StatusHandler statusHandler = new StatusHandler();
		SendOTPResponse sendOTPResponse = new SendOTPResponse();
		try {
			if(null == sendOTPRequest.getMobile() || sendOTPRequest.getMobile().isEmpty()) {
				throw new InvalidRequestException(AppConstants.MOBILENUMBER_IS_REQUIRED);
			}
			if(sendOTPRequest.getMobile().matches("\\{d}")) {
				throw new InvalidRequestException(AppConstants.INVALID_MOBILENUMBER);
			}
			sendOTPResponse = service.sendOTP(sendOTPRequest, sendOTPResponse, statusHandler);
			ResponseEntity<SendOTPResponse> response = new ResponseEntity<>(sendOTPResponse, HttpStatus.OK);
			
			logger.info("END : Resend OTP Controller : "+sendOTPResponse);
			logger.warn("END : Resend OTP Controller : "+sendOTPResponse);
			return response;
		}catch(InvalidRequestException ex) {
			statusHandler.setErrorCode("400");
			statusHandler.setErrorMessage(ex.getMessage());
			sendOTPResponse.setStatusHandler(statusHandler);
			return new ResponseEntity<>(sendOTPResponse, HttpStatus.BAD_REQUEST);
		}catch(Exception ex) {
			statusHandler.setErrorCode("500");
			statusHandler.setErrorMessage(ex.getMessage());
			sendOTPResponse.setStatusHandler(statusHandler);
			return new ResponseEntity<>(sendOTPResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

















