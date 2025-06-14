package com.vendorloginservice.service;


import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vendorloginservice.constants.AppConstants;
import com.vendorloginservice.domain.SendOTPRequest;
import com.vendorloginservice.domain.SendOTPResponse;
import com.vendorloginservice.domain.VendorDetailsDTO;
import com.vendorloginservice.entity.VendorCredentials;
import com.vendorloginservice.entity.VendorDetails;
import com.vendorloginservice.exceptions.InvalidRequestException;
import com.vendorloginservice.exceptions.StatusHandler;
import com.vendorloginservice.mapper.VendorMapper;
import com.vendorloginservice.repository.VendorDetailsRepository;
import com.vendorloginservice.repository.VendorLoginRepository;

@Service("VendorLoginService")
public class VendorLoginServiceImpl implements VendorLoginService{
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	private final VendorLoginRepository vendorLoginRepository;
	private final VendorDetailsRepository vendorDetailsRepository;
	private final VendorMapper mapper;
	
	public VendorLoginServiceImpl(VendorLoginRepository vendorLoginRepository, VendorDetailsRepository vendorDetailsRepository,
			VendorMapper mapper) {
		this.vendorLoginRepository = vendorLoginRepository;
		this.vendorDetailsRepository = vendorDetailsRepository;
		this.mapper = mapper;
	}

	@Override
	@Transactional
	public SendOTPResponse sendOTP(SendOTPRequest sendOTPRequest, SendOTPResponse sendOTPResponse,
			StatusHandler statusHandler) {
		logger.info("START : SEND OTP Request Service : "+sendOTPRequest);
		
		Optional<VendorCredentials> optional = vendorLoginRepository.findByMobile(sendOTPRequest.getMobile());
		String otp = generateOTP();
		
		if(optional.isEmpty() || null == optional) {
			VendorCredentials vendorCredentials = new VendorCredentials();
			vendorCredentials.setMobile(sendOTPRequest.getMobile());
			vendorCredentials.setOtp(otp);
			sendOTPResponse.setMessage(AppConstants.OTP_SENT_SUCCESSFULLY);
			vendorLoginRepository.save(vendorCredentials);
		}else {
			VendorCredentials vendor = optional.get();
			vendor.setOtp(otp);
			vendorLoginRepository.save(vendor);
			
			sendOTPResponse.setVendorId(optional.get().getVendorId());
			sendOTPResponse.setVendor_name(optional.get().getVendor_name());
			sendOTPResponse.setEmail(optional.get().getEmail());
			sendOTPResponse.setMessage(AppConstants.ACCOUNT_ALREADY_EXISTS);
		}
		sendOTPResponse.setOtp(otp);
		sendOTPResponse.setMobile(sendOTPRequest.getMobile());
		statusHandler.setStatusCode("200");
		statusHandler.setMessage("OTP Sent Successfully");
		sendOTPResponse.setStatusHandler(statusHandler);
		logger.info("END : SEND OTP Request Service "+sendOTPResponse);
		return sendOTPResponse;
	}

	private String generateOTP() {
        Random random = new Random();
        int otp = 1000 + random.nextInt(9000); // ensures 4-digit OTP
        return String.valueOf(otp);
    }

	@Override
	@Transactional
	public SendOTPResponse verifyOTP(SendOTPRequest sendOTPRequest, SendOTPResponse sendOTPResponse,
			StatusHandler statusHandler) {
		logger.info("START : Verify OTP Request Service : "+sendOTPRequest);
		
		
		try {
			
			if(null == sendOTPRequest.getOtp() || sendOTPRequest.getOtp().isEmpty() 
					|| null == sendOTPRequest.getMobile() || sendOTPRequest.getMobile().isEmpty() ) {
				throw new InvalidRequestException(AppConstants.INVALID_REQUEST);
			}
			
			Optional<VendorCredentials> vendorCredentials = vendorLoginRepository.findByMobile(sendOTPRequest.getMobile());
			if(null == vendorCredentials || vendorCredentials.isEmpty()) {
				throw new RuntimeException(AppConstants.INVALID_MOBILENUMBER);
			}
			
			if(vendorCredentials.get().getOtp().equals(sendOTPRequest.getOtp())) {
				
				VendorDetails vendorDetails = new VendorDetails();
				vendorDetails.setVendorId(vendorCredentials.get().getVendorId());
				vendorDetails.setV_firstName(vendorCredentials.get().getVendor_name());
				vendorDetails.setV_mobile(vendorCredentials.get().getMobile());
				vendorDetails.setV_email(vendorCredentials.get().getEmail());
				vendorDetailsRepository.save(vendorDetails);
				
				sendOTPResponse.setVendorId(vendorCredentials.get().getVendorId());
				sendOTPResponse.setVendor_name(vendorCredentials.get().getVendor_name());
				sendOTPResponse.setMobile(vendorCredentials.get().getMobile());
				sendOTPResponse.setOtp(sendOTPRequest.getOtp());
				sendOTPResponse.setEmail(vendorCredentials.get().getEmail());
				sendOTPResponse.setMessage(AppConstants.OTP_VERIFICATION_IS_SUCCESSFULL);
				statusHandler.setStatusCode("200");
				statusHandler.setMessage(AppConstants.OTP_VERIFICATION_IS_SUCCESSFULL);
				sendOTPResponse.setStatusHandler(statusHandler);
				logger.info("END : Verify OTP Request Service : "+AppConstants.OTP_VERIFICATION_IS_SUCCESSFULL);
				
				
			}
		}catch(InvalidRequestException ex) {
			statusHandler.setErrorCode("400");
			statusHandler.setErrorMessage(ex.getMessage());
			sendOTPResponse.setStatusHandler(statusHandler);
			logger.error("Error : Invalid Request : ");
			return sendOTPResponse;
		}catch(RuntimeException ex) {
			statusHandler.setErrorCode("400");
			statusHandler.setErrorMessage(ex.getMessage());
			sendOTPResponse.setStatusHandler(statusHandler);
			logger.error("Error : VendorCredentials returns null : ");
			return sendOTPResponse;
		}catch(Exception ex) {
			statusHandler.setErrorCode("500");
			statusHandler.setMessage(AppConstants.INTERNAL_SERVER_ERROR);
			sendOTPResponse.setStatusHandler(statusHandler);
			logger.error("Error : Exception : "+AppConstants.INTERNAL_SERVER_ERROR);
			return sendOTPResponse;
		}
		return sendOTPResponse;
		
	}

	@Override
	public List<VendorCredentials> getall() {
		
		return vendorLoginRepository.findAll();
	}

	@Override
	public List<VendorDetailsDTO> getdetails() {
		
		List<VendorDetails> list =  vendorDetailsRepository.findAll();
		return mapper.toDtoList(list);
	}
	
}




















