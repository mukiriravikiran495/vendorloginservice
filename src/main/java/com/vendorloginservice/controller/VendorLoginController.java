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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vendorloginservice.constants.AppConstants;
import com.vendorloginservice.domain.SendOTPRequest;
import com.vendorloginservice.domain.SendOTPResponse;
import com.vendorloginservice.domain.TokenID;
import com.vendorloginservice.domain.VendorDetailsDTO;
import com.vendorloginservice.domain.VerifyOTPRequest;
import com.vendorloginservice.domain.VerifyOTPResponse;
import com.vendorloginservice.entity.VendorAuth;
import com.vendorloginservice.entity.VendorTokens;
import com.vendorloginservice.exceptions.InvalidMobileNumberException;
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
	public List<VendorAuth> getall(){
		return service.getall();
	}
	
	@GetMapping( value = "/getvendordetails")
	public List<VendorDetailsDTO> getdetails(){
		return service.getdetails();
	}
	
	@GetMapping( value = "/auth/token")
	public ResponseEntity<VendorTokens> validateAccessToken(@RequestHeader("Authorization") String authorization ) {
		logger.info("Start: generate token controller..!!");
		String accessToken = authorization.replace("Bearer ", "");
		VendorTokens response = service.getVendorTokens(accessToken);
		logger.info("End: generate token controller..!!");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping( value = "/sendotp")
	public ResponseEntity<SendOTPResponse> sendOTP(@RequestBody SendOTPRequest sendOTPRequest, @RequestHeader("APPID") String appId){
		logger.info("START : SendOTP Request Controller : "+sendOTPRequest);
		StatusHandler statusHandler = new StatusHandler();
		SendOTPResponse sendOTPResponse = new SendOTPResponse();
		try {
			if(null == sendOTPRequest.getMobile() || sendOTPRequest.getMobile().isEmpty()) {
				throw new InvalidMobileNumberException(AppConstants.MOBILENUMBER_IS_REQUIRED);
			}
			if(sendOTPRequest.getMobile().matches("\\{d}")) {
				throw new InvalidRequestException(AppConstants.ENTER_10_DIGIT_MOBILENUMBER);
			}
			sendOTPResponse = service.sendOTP(sendOTPRequest, sendOTPResponse, appId, statusHandler);
			ResponseEntity<SendOTPResponse> response = new ResponseEntity<>(sendOTPResponse, HttpStatus.OK);
			
			logger.info("END : Send OTP Controller : "+sendOTPResponse);
			logger.warn("END : Send OTP Controller : "+sendOTPResponse);
			return response;
		}catch(InvalidRequestException ex) {
			statusHandler.setStatusCode("400");
			statusHandler.setMessage(ex.getMessage());
			sendOTPResponse.setStatusHandler(statusHandler);
			return new ResponseEntity<>(sendOTPResponse, HttpStatus.BAD_REQUEST);
		}catch(Exception ex) {
			statusHandler.setStatusCode("500");
			statusHandler.setMessage(ex.getMessage());
			sendOTPResponse.setStatusHandler(statusHandler);
			return new ResponseEntity<>(sendOTPResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping( value = "/verifyotp")
	public ResponseEntity<VerifyOTPResponse> verifyOTP(@RequestBody VerifyOTPRequest verifyOTPRequest, 
														@RequestHeader("Authorization") String accessToken,
														@RequestHeader("APPID") String appId){
		logger.info("START : Verify OTP Request : "+verifyOTPRequest);
		StatusHandler statusHandler = new StatusHandler();
		VerifyOTPResponse verifyOTPResponse = new VerifyOTPResponse();
		String token = accessToken.replace("Bearer ", "");
		try {
			if( null == verifyOTPRequest.getMobile() || verifyOTPRequest.getMobile().isEmpty()) {
				throw new InvalidRequestException(AppConstants.OTP_IS_REQUIRED);
			}
			if( verifyOTPRequest.getMobile().matches("\\{d}")) {
				throw new InvalidRequestException(AppConstants.INVALID_REQUEST);
			}

			verifyOTPResponse = service.verifyOTP(verifyOTPRequest, token, appId, verifyOTPResponse, statusHandler);
			ResponseEntity<VerifyOTPResponse> response = new ResponseEntity<>(verifyOTPResponse, HttpStatus.OK);
			logger.info("END : Verify OTP Request : "+verifyOTPResponse);
			return response;
		}catch(InvalidRequestException ex) {
			statusHandler.setStatusCode("400");
			statusHandler.setMessage(ex.getMessage());
			verifyOTPResponse.setStatusHandler(statusHandler);
			return new ResponseEntity<>(verifyOTPResponse, HttpStatus.BAD_REQUEST);
		}catch(Exception ex) {
			statusHandler.setStatusCode("500");
			statusHandler.setMessage(AppConstants.INTERNAL_SERVER_ERROR);
			verifyOTPResponse.setStatusHandler(statusHandler);
			return new ResponseEntity<>(verifyOTPResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PostMapping( value = "/resendotp")
	public ResponseEntity<SendOTPResponse> resendotp(@RequestBody SendOTPRequest sendOTPRequest, @RequestHeader("APPID") String appId){
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
			sendOTPResponse = service.sendOTP(sendOTPRequest, sendOTPResponse, appId, statusHandler);
			ResponseEntity<SendOTPResponse> response = new ResponseEntity<>(sendOTPResponse, HttpStatus.OK);
			
			logger.info("END : Resend OTP Controller : "+sendOTPResponse);
			logger.warn("END : Resend OTP Controller : "+sendOTPResponse);
			return response;
		}catch(InvalidRequestException ex) {
			statusHandler.setStatusCode("400");
			statusHandler.setMessage(ex.getMessage());
			sendOTPResponse.setStatusHandler(statusHandler);
			return new ResponseEntity<>(sendOTPResponse, HttpStatus.BAD_REQUEST);
		}catch(Exception ex) {
			statusHandler.setStatusCode("500");
			statusHandler.setMessage(ex.getMessage());
			sendOTPResponse.setStatusHandler(statusHandler);
			return new ResponseEntity<>(sendOTPResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

















