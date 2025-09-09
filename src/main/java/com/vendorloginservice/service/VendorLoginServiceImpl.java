package com.vendorloginservice.service;


import java.lang.invoke.MethodHandles;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vendorloginservice.constants.AppConstants;
import com.vendorloginservice.domain.SendOTPRequest;
import com.vendorloginservice.domain.SendOTPResponse;
import com.vendorloginservice.domain.TokenResponse;
import com.vendorloginservice.domain.TranLogDTO;
import com.vendorloginservice.domain.TranLogRequest;
import com.vendorloginservice.domain.VendorDetailsDTO;
import com.vendorloginservice.domain.VendorResponse;
import com.vendorloginservice.domain.VerifyOTPRequest;
import com.vendorloginservice.domain.VerifyOTPResponse;
import com.vendorloginservice.entity.Applications;
import com.vendorloginservice.entity.VendorAuth;
import com.vendorloginservice.entity.VendorDetails;
import com.vendorloginservice.entity.VendorTokens;
import com.vendorloginservice.exceptions.InvalidAppException;
import com.vendorloginservice.exceptions.InvalidRequestException;
import com.vendorloginservice.exceptions.StatusHandler;
import com.vendorloginservice.exceptions.TokenPersistenceException;
import com.vendorloginservice.exceptions.TokenSaveException;
import com.vendorloginservice.repository.*;
import com.vendorloginservice.utils.JwtUtil;
import com.vendorloginservice.utils.TransactionUtils;
import com.vendorloginservice.utils.VendorUtils;

@Service("VendorLoginService")
public class VendorLoginServiceImpl implements VendorLoginService{

    private final VendorRepository vendorRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());	
	private final VendorLoginRepository vendorLoginRepository;	
	private final VendorUtils vendorUtils;
	private final JwtUtil jwtUtil;
	private final ApplicationsRepository applicationsRepository;
	private final VendorTokenRepository vendorTokenRepository;
	private final VendorDetailsRepository vendorDetailsRepository;
	@Autowired
	TransactionUtils transactionUtils;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	public VendorLoginServiceImpl(VendorLoginRepository vendorLoginRepository, 
			 VendorUtils vendorUtils, JwtUtil jwtUtil, ApplicationsRepository applicationsRepository, 
			 VendorTokenRepository vendorTokenRepository, VendorDetailsRepository vendorDetailsRepository, VendorRepository vendorRepository) {
		this.vendorLoginRepository = vendorLoginRepository;
		this.vendorUtils = vendorUtils;
		this.jwtUtil = jwtUtil;
		this.applicationsRepository = applicationsRepository;
		this.vendorTokenRepository = vendorTokenRepository;
		this.vendorDetailsRepository = vendorDetailsRepository;
		this.vendorRepository = vendorRepository;
	}

	@Override
	@Transactional
	public SendOTPResponse sendOTP(SendOTPRequest sendOTPRequest, SendOTPResponse sendOTPResponse, String appId,
			StatusHandler statusHandler) {
		logger.info("START : SEND OTP Request Service : "+sendOTPRequest);
		String otp = generateOTP();
		TokenResponse response = null;
		VendorAuth vendorAuth = null;
		TranLogRequest tranLogRequest = new TranLogRequest();
		// validate app
		Applications app = applicationsRepository.findByAppIdAndIsActive(appId, "Y")
				.orElseThrow(() -> new RuntimeException("Invalid or inactive APPID: " + appId));
		try {
			Optional<VendorAuth> optionalCust = vendorLoginRepository.findByMobile(sendOTPRequest.getMobile());

			if (optionalCust.isEmpty()) {
				// new customer
				vendorAuth = vendorLoginRepository.save(new VendorAuth(sendOTPRequest.getMobile(), otp));

				

				// generate token
				response = jwtUtil.generateToken(sendOTPRequest.getMobile());
				vendorTokenRepository.deactivateTokensForDevice(vendorAuth.getVendorId(), sendOTPRequest.getDeviceName());
				System.out.println(response.getAccessToken());
				// save token
				VendorTokens saved = saveCustomerToken(new VendorTokens(response.getTokenUuid(), vendorAuth.getVendorId(), 
						vendorAuth.getMobile(), response.getAccessToken(), response.getIssuedAt(), response.getExpiresAt(),
						"Y", sendOTPRequest.getDeviceId(), sendOTPRequest.getDeviceName(), app.getAppId(), response.getCorrelationId()));

				// map token to response
				mapTokenToResponse(saved, sendOTPResponse);
				sendOTPResponse.setMessage(AppConstants.OTPSENT);

				// log success
				TranLogDTO dto = buildTranLogDTO("VENDOR-SENDOTP", AppConstants.SUCCESS, sendOTPRequest, sendOTPResponse,
						response, vendorAuth, null);
				tranLogRequest.setTranLogDTO(dto);
				transactionUtils.createTransaction(tranLogRequest, vendorAuth.getVendorId(), response.getAccessToken());

			} else {
				// existing customer
				VendorAuth vendor = optionalCust.get();
				vendor.setOtp(otp);
				vendor.setUpdatedAt(LocalDateTime.now());
				vendor.setUpdatedBy(vendor.getVendorId());
	            VendorAuth savedVendorAuth = vendorLoginRepository.save(vendor);
	            
				// generate token
				response = jwtUtil.generateToken(sendOTPRequest.getMobile());
				vendorTokenRepository.deactivateTokensForDevice(savedVendorAuth.getVendorId(), sendOTPRequest.getDeviceName());
				
				// save token
				VendorTokens saved = saveCustomerToken(new VendorTokens(response.getTokenUuid(), savedVendorAuth.getVendorId(), 
						savedVendorAuth.getMobile(), response.getAccessToken(), response.getIssuedAt(), response.getExpiresAt(),
						"Y", sendOTPRequest.getDeviceId(), sendOTPRequest.getDeviceName(), app.getAppId(), response.getCorrelationId()));

				// map token to response
				mapTokenToResponse(saved, sendOTPResponse);
				sendOTPResponse.setMessage(AppConstants.OTPSENT);

				// log success
				TranLogDTO dto = buildTranLogDTO("VENDOR-SENDOTP", AppConstants.SUCCESS, sendOTPRequest, sendOTPResponse,
						response, savedVendorAuth, null);
				tranLogRequest.setTranLogDTO(dto);
				transactionUtils.createTransaction(tranLogRequest, savedVendorAuth.getVendorId(), response.getAccessToken());
			}

			sendOTPResponse.setOtp(otp);
			sendOTPResponse.setMobile(sendOTPRequest.getMobile());
			statusHandler.setStatusCode("200");
			statusHandler.setMessage("OTP Sent Successfully");
			sendOTPResponse.setStatusHandler(statusHandler);

		} catch (InvalidAppException | TokenSaveException e) {
			logger.error("Error in sendOTP", e);
			TranLogDTO dto = buildTranLogDTO("VENDOR-SENDOTP", AppConstants.FAILED, sendOTPRequest, sendOTPResponse, response,
					vendorAuth, e.getMessage());
			tranLogRequest.setTranLogDTO(dto);
			transactionUtils.createTransaction(tranLogRequest, vendorAuth != null ? vendorAuth.getVendorId() : null,
					response != null ? response.getAccessToken() : null);
		}catch (Exception  e) {
			logger.error("Error in sendOTP", e);
			TranLogDTO dto = buildTranLogDTO("VENDOR-SENDOTP", AppConstants.FAILED, sendOTPRequest, sendOTPResponse, response,
					vendorAuth, e.getMessage());
			tranLogRequest.setTranLogDTO(dto);
			transactionUtils.createTransaction(tranLogRequest, vendorAuth != null ? vendorAuth.getVendorId() : null,
					response != null ? response.getAccessToken() : null);
		}

		logger.info("END : SEND OTP Request Service "+sendOTPResponse);
		return sendOTPResponse;
	}
	
	private TranLogDTO buildTranLogDTO(String action, String status, SendOTPRequest otpRequest, SendOTPResponse otpResponse,
			TokenResponse response, VendorAuth vendorAuth, String errorMessage) {
		TranLogDTO dto = new TranLogDTO();
		System.out.println("PayLoad: "+otpRequest.getMobile());
		System.out.println(vendorAuth.getVendorId());
		dto.setTokenUuid(response != null ? response.getTokenUuid() : null);
		dto.setCorrelationId(response != null ? response.getCorrelationId() : null);
		dto.setVendorId(vendorAuth != null ? vendorAuth.getVendorId() : null);
		dto.setAction(action);
		try {
			dto.setRequestPayload(otpRequest != null 
			        ? objectMapper.writeValueAsString(otpRequest) 
			        : "{}");
			dto.setResponsePayload(objectMapper.writeValueAsString(otpResponse));
		} catch (JsonProcessingException e) {
			logger.error("Failed to serialize request/response", e);
		}
		dto.setStatus(status);
		dto.setCreatedAt(LocalDateTime.now());
		dto.setErrorMessage(errorMessage != null ? errorMessage : "");
		return dto;
	}
	private TranLogDTO buildVerifyTranLogDTO(String action, String status, VerifyOTPRequest otpRequest, VerifyOTPResponse otpResponse,
			VendorTokens response, VendorAuth vendorAuth, String errorMessage) {
		TranLogDTO dto = new TranLogDTO();
		System.out.println("PayLoad: "+otpRequest.getMobile());
		dto.setTokenUuid(response != null ? response.getTokenUuid() : null);
		dto.setCorrelationId(response != null ? response.getCorrelationId() : null);
		dto.setVendorId(vendorAuth != null ? vendorAuth.getVendorId() : null);
		dto.setAction(action);
		try {
			dto.setRequestPayload(otpRequest != null 
			        ? objectMapper.writeValueAsString(otpRequest) 
			        : "{}");
			dto.setResponsePayload(objectMapper.writeValueAsString(otpResponse));
		} catch (JsonProcessingException e) {
			logger.error("Failed to serialize request/response", e);
		}
		dto.setStatus(status);
		dto.setCreatedAt(LocalDateTime.now());
		dto.setErrorMessage(errorMessage != null ? errorMessage : "");
		return dto;
	}

	private VendorTokens saveCustomerToken(VendorTokens token) {
		try {
			return vendorTokenRepository.saveAndFlush(token);
		} catch (DataAccessException e) {
			logger.error("Failed to insert token for customerId=" + token.getVendorId(), e);
			throw new TokenPersistenceException("Unable to save CustomerToken", e);
		}
	}

	private void mapTokenToResponse(VendorTokens saved, SendOTPResponse otpResponse) {
		otpResponse.setTokenUuid(saved.getTokenUuid());
		otpResponse.setAccessToken(saved.getAccessToken());
		otpResponse.setIssuedAt(saved.getIssuedAt());
		otpResponse.setExpiresAt(saved.getExpiresAt());
		otpResponse.setIsActive(saved.getIsActive());
		otpResponse.setAppId(saved.getAppId());
		otpResponse.setCorrelationId(saved.getCorrelationId());
	}

	private String generateOTP() {
        Random random = new Random();
        int otp = 1000 + random.nextInt(9000); // ensures 4-digit OTP
        return String.valueOf(otp);
    }
	
	private VendorTokens validateAccessToken(String accessToken) throws InvalidRequestException {
	    return vendorTokenRepository.findByAccessTokenAndIsActive(accessToken, "Y")
	            .orElseThrow(() -> new InvalidRequestException("Invalid or inactive token"));
	}
	private VendorAuth fetchVendor(Long vendorId) throws InvalidRequestException {
	    return vendorLoginRepository.findByVendorId(vendorId)
	            .orElseThrow(() -> new InvalidRequestException("Customer not found"));
	}

	@Override
	@Transactional
	public VerifyOTPResponse verifyOTP(VerifyOTPRequest verifyOTPRequest, String token, String appId, VerifyOTPResponse verifyOTPResponse,
			StatusHandler statusHandler) {
		logger.info("START : Verify OTP Request Service : "+verifyOTPRequest);
		
		VendorAuth vendorAuth = null;
	    VendorTokens vendorTokens = null;
	    TranLogRequest tranLogRequest = new TranLogRequest();
		Optional<VendorDetails> optionalVendor = vendorDetailsRepository.findByvMobile(verifyOTPRequest.getMobile());
		Optional<VendorAuth> OptionalLogin = vendorLoginRepository.findByMobile(verifyOTPRequest.getMobile());
		
		Applications app = applicationsRepository.findByAppIdAndIsActive(appId, "Y")
				.orElseThrow(() -> new RuntimeException("Invalid or inactive APPID: " + appId));
		VendorAuth vendorCred = OptionalLogin.get();
		try {
			if (optionalVendor.isPresent()) {
				System.out.println("Existing Customer");
				VendorDetails details = optionalVendor.get();
				//validate token
				vendorTokens = validateAccessToken(token);
		        vendorAuth = fetchVendor(vendorTokens.getVendorId());
				
		        // 2️⃣ Verify OTP
		        if (!verifyOTPRequest.getOtp().equals(vendorAuth.getOtp())) {
		        	throw new InvalidRequestException(AppConstants.ENTER_VALID_OTP);
		        }

		        // 3️⃣ Mark OTP verified
		        vendorAuth.setIsVerified("Y");
		        vendorLoginRepository.save(vendorCred);
		        
		        TranLogDTO dto = buildVerifyTranLogDTO("CUSTOMER-VERIFYOTP", AppConstants.SUCCESS, verifyOTPRequest, verifyOTPResponse, vendorTokens,
						vendorAuth, AppConstants.INVALID_OTP);
				tranLogRequest.setTranLogDTO(dto);
				transactionUtils.createTransaction(tranLogRequest, vendorAuth != null ? vendorAuth.getVendorId() : null,
						vendorTokens != null ? vendorTokens.getAccessToken() : null);
				verifyOTPResponse.setVendorId(details.getVendorId());
				verifyOTPResponse.setMobile(details.getvMobile());
				verifyOTPResponse.setCompanyName(details.getCompanyName());
				verifyOTPResponse.setOwnerName(details.getOwnerName());
				statusHandler.setStatusCode("200");
				statusHandler.setMessage("CUSTOMER_ALREADY_EXISTS");
				verifyOTPResponse.setStatusHandler(statusHandler);
			} else {
				System.out.println(" New Customer");
				//validate token
				vendorTokens = validateAccessToken(token);
		        vendorAuth = fetchVendor(vendorTokens.getVendorId());
		        
		        if (!verifyOTPRequest.getOtp().equals(vendorAuth.getOtp())) {
		        	throw new InvalidRequestException(AppConstants.ENTER_VALID_OTP);

		        }
		        
		        vendorAuth.setIsVerified("Y");
		        vendorLoginRepository.save(vendorCred);
		        
				VendorDetailsDTO vendorDetailsDTO = new VendorDetailsDTO();
				vendorDetailsDTO.setvMobile(vendorAuth.getMobile());
				vendorDetailsDTO.setVendorId(vendorAuth.getVendorId());
				vendorDetailsDTO.setCreatedBy(vendorAuth.getVendorId());
				vendorDetailsDTO.setAccessToken(token);
				VendorResponse savedVendor = vendorUtils.createVendor(vendorDetailsDTO);
				Optional.ofNullable(savedVendor)
						.orElseThrow(() -> new RuntimeException(AppConstants.VENDOR_DETAILS_NOT_SAVED));
				System.out.println(savedVendor.toString());
				
				TranLogDTO dto = buildVerifyTranLogDTO("VENDOR-VERIFYOTP", AppConstants.SUCCESS, verifyOTPRequest, verifyOTPResponse, vendorTokens,
						vendorAuth, AppConstants.INVALID_OTP);
				tranLogRequest.setTranLogDTO(dto);
				transactionUtils.createTransaction(tranLogRequest, vendorAuth != null ? vendorAuth.getVendorId() : null,
						vendorTokens != null ? vendorTokens.getAccessToken() : null);
				
				verifyOTPResponse.setVendorId(savedVendor.getVendorDetailsDTO().getVendorId());
				verifyOTPResponse.setMobile(savedVendor.getVendorDetailsDTO().getvMobile());
				
				statusHandler.setStatusCode("200");
				statusHandler.setMessage("VENDOR CREATED SUCCESFULLY");
				verifyOTPResponse.setStatusHandler(statusHandler);
			}
		}catch(InvalidRequestException e) {
        	TranLogDTO dto = buildVerifyTranLogDTO("VENDOR-VERIFYOTP", AppConstants.FAILED, verifyOTPRequest, verifyOTPResponse, vendorTokens,
					vendorAuth, e.getMessage());
			tranLogRequest.setTranLogDTO(dto);
			transactionUtils.createTransaction(tranLogRequest, vendorAuth != null ? vendorAuth.getVendorId() : null,
					vendorTokens != null ? vendorTokens.getAccessToken() : null);
			statusHandler.setStatusCode("400");
			statusHandler.setError(AppConstants.INVALID_OTP);
			verifyOTPResponse.setStatusHandler(statusHandler);
			
		}catch(Exception e) {
			TranLogDTO dto = buildVerifyTranLogDTO("VENDOR-VERIFYOTP", AppConstants.FAILED, verifyOTPRequest, verifyOTPResponse, vendorTokens,
					vendorAuth, e.getMessage());
			tranLogRequest.setTranLogDTO(dto);
			transactionUtils.createTransaction(tranLogRequest, vendorAuth != null ? vendorAuth.getVendorId() : null,
					vendorTokens != null ? vendorTokens.getAccessToken() : null);
			statusHandler.setStatusCode("500");
			statusHandler.setError(e.getMessage());
			verifyOTPResponse.setStatusHandler(statusHandler);
		}
		
		return verifyOTPResponse;
	}

	@Override
	public List<VendorAuth> getall() {
		
		return vendorLoginRepository.findAll();
	}

	@Override
	public List<VendorDetailsDTO> getdetails() {
		
		
		return null;
	}
	
}




















