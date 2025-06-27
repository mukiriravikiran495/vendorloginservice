package com.vendorloginservice.service;


import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vendorloginservice.constants.AppConstants;
import com.vendorloginservice.domain.SendOTPRequest;
import com.vendorloginservice.domain.SendOTPResponse;
import com.vendorloginservice.domain.VendorDTO;
import com.vendorloginservice.domain.VendorDetailsDTO;
import com.vendorloginservice.domain.VendorResponse;
import com.vendorloginservice.entity.Vendor;
import com.vendorloginservice.entity.VendorCredentials;
import com.vendorloginservice.exceptions.InvalidRequestException;
import com.vendorloginservice.exceptions.StatusHandler;
import com.vendorloginservice.mapper.VendorMapper;
import com.vendorloginservice.repository.VendorLoginRepository;
import com.vendorloginservice.repository.VendorRepository;
import com.vendorloginservice.utils.VendorUtils;

@Service("VendorLoginService")
public class VendorLoginServiceImpl implements VendorLoginService{
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	private final VendorLoginRepository vendorLoginRepository;
	private final VendorRepository vendorRepository;
	private final VendorMapper mapper;
	private final VendorUtils vendorUtils;
	
	@Autowired
	public VendorLoginServiceImpl(VendorLoginRepository vendorLoginRepository, VendorRepository vendorRepository,
			VendorMapper mapper, VendorUtils vendorUtils) {
		this.vendorLoginRepository = vendorLoginRepository;
		this.vendorRepository = vendorRepository;
		this.mapper = mapper;
		this.vendorUtils = vendorUtils;
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
			sendOTPResponse.setvFirstname(optional.get().getVendor_name());
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
				if(sendOTPRequest.getMessage().equals(AppConstants.ACCOUNT_ALREADY_EXISTS)) {
					sendOTPResponse.setVendorId(vendorCredentials.get().getVendorId());
					sendOTPResponse.setvFirstname(vendorCredentials.get().getVendor_name());
					sendOTPResponse.setMobile(vendorCredentials.get().getMobile());
					sendOTPResponse.setOtp(sendOTPRequest.getOtp());
					sendOTPResponse.setEmail(vendorCredentials.get().getEmail());
					sendOTPResponse.setMessage(AppConstants.OTP_VERIFICATION_IS_SUCCESSFULL);
					statusHandler.setStatusCode("200");
					statusHandler.setMessage(AppConstants.OTP_VERIFICATION_IS_SUCCESSFULL);
					sendOTPResponse.setStatusHandler(statusHandler);
					logger.info("END : Verify OTP Request Service : "+AppConstants.OTP_VERIFICATION_IS_SUCCESSFULL);
				}else {
					VendorDTO vendorDTO = new VendorDTO();
					vendorDTO.setVendorId(vendorCredentials.get().getVendorId());
					vendorDTO.setVfirstname(vendorCredentials.get().getVendor_name());
					vendorDTO.setvMobile(vendorCredentials.get().getMobile());
					vendorDTO.setvEmail(vendorCredentials.get().getEmail());
					vendorDTO.setBasePricePerKM(0);
					vendorDTO.setEstimatedPrice(0);
					vendorDTO.setPricePerKG(0);
					vendorDTO.setAvgDeliveryTimeInDays(0);
					VendorResponse vendorResponse = vendorUtils.createVendor(vendorDTO);
					
					sendOTPResponse.setVendorId(vendorResponse.getVendorDTO().getVendorId());
					sendOTPResponse.setvFirstname(vendorResponse.getVendorDTO().getVfirstname());
					sendOTPResponse.setMobile(vendorResponse.getVendorDTO().getvMobile());
					sendOTPResponse.setOtp(sendOTPRequest.getOtp());
					sendOTPResponse.setEmail(vendorResponse.getVendorDTO().getvEmail());
					sendOTPResponse.setMessage(AppConstants.OTP_VERIFICATION_IS_SUCCESSFULL);
					statusHandler.setStatusCode("200");
					statusHandler.setMessage(AppConstants.OTP_VERIFICATION_IS_SUCCESSFULL);
					sendOTPResponse.setStatusHandler(statusHandler);
					
					
				}
			}
			
//			if(vendorCredentials.get().getOtp().equals(sendOTPRequest.getOtp())  && null == sendOTPRequest.getVendorId()) {
//				
//				Vendor vendor = new Vendor();
//				vendor.setVendorId(vendorCredentials.get().getVendorId());
//				vendor.setVfirstname(vendorCredentials.get().getVendor_name());
//				vendor.setvMobile(vendorCredentials.get().getMobile());
//				vendor.setvEmail(vendorCredentials.get().getEmail());
//				vendor.setBasePricePerKM(0);
//				vendor.setEstimatedPrice(0);
//				vendor.setPricePerKG(0);
//				vendor.setAvgDeliveryTimeInDays(0);
//				VendorDTO vendorDTO = vendorUtils.createVendor(vendor);
////				vendorRepository.save(vendor);
//				
//				sendOTPResponse.setVendorId(vendorCredentials.get().getVendorId());
//				sendOTPResponse.setvFirstname(vendorCredentials.get().getVendor_name());
//				sendOTPResponse.setMobile(vendorCredentials.get().getMobile());
//				sendOTPResponse.setOtp(sendOTPRequest.getOtp());
//				sendOTPResponse.setEmail(vendorCredentials.get().getEmail());
//				sendOTPResponse.setMessage(AppConstants.VENDOR_CREATED_SUCCESSFULLY);
//				statusHandler.setStatusCode("200");
//				statusHandler.setMessage(AppConstants.OTP_VERIFICATION_IS_SUCCESSFULL);
//				sendOTPResponse.setStatusHandler(statusHandler);
//				logger.info("END : Verify OTP Request Service : "+AppConstants.OTP_VERIFICATION_IS_SUCCESSFULL);
//				
//				
//			}else {
//				VendorResponse vendorDetails = vendorUtils.getVendor(sendOTPRequest.getVendorId());
//				
//				System.out.println(vendorDetails.getVendorDTO());
//				if( null == vendorDetails.getVendorDTO().getVendorId()) {
//					throw new RuntimeException(AppConstants.VENDORID_DOES_NOT_EXISTS);
//				}
//				
//				VendorDTO vendorDTO = vendorDetails.getVendorDTO();
//				sendOTPResponse.setVendorId(vendorDTO.getVendorId());
//				sendOTPResponse.setvFirstname(vendorDTO.getVfirstname());
//				sendOTPResponse.setvLastname(vendorDTO.getvLastname());
//				sendOTPResponse.setMobile(vendorDTO.getvMobile());
//				sendOTPResponse.setOtp(sendOTPRequest.getOtp());
//				sendOTPResponse.setEmail(vendorDTO.getvEmail());
//				sendOTPResponse.setMessage(AppConstants.VENDOR_ALREADY_EXISTS);
//				statusHandler.setStatusCode("200");
//				statusHandler.setMessage(AppConstants.OTP_VERIFICATION_IS_SUCCESSFULL);
//				sendOTPResponse.setStatusHandler(statusHandler);
//			}
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
		
		
		return null;
	}
	
}




















