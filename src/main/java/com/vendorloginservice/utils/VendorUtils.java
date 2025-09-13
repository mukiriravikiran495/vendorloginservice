package com.vendorloginservice.utils;

import java.lang.invoke.MethodHandles;
import java.util.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import com.vendorloginservice.domain.TokenID;
import com.vendorloginservice.domain.VendorDetailsDTO;
import com.vendorloginservice.domain.VendorResponse;

@Component
public class VendorUtils {
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	private  RestTemplate restTemplate;
	
	@Value("${vendor.service.url}")
    private String vendorServiceUrl;

	
	public VendorUtils() {
		
	}
	@Autowired
	public VendorUtils(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	public VendorResponse createVendor(VendorDetailsDTO vendorDetailsDTO) {
		logger.info("Start : create vendor utils : "+vendorDetailsDTO);
		
		String url = vendorServiceUrl+"/vendor/create";
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+vendorDetailsDTO.getAccessToken());
		System.out.println("token : "+vendorDetailsDTO.getAccessToken());
		System.out.println(" URL : "+url);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<VendorDetailsDTO> entity = new HttpEntity<>(vendorDetailsDTO, headers);

        try {
            ResponseEntity<VendorResponse> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                VendorResponse.class
            );
            logger.info("Response: " + response.getBody());
            logger.info("End : create vendor utils : ");
            return response.getBody();
        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            logger.error("HTTP Error: " + ex.getStatusCode() + " - " + ex.getResponseBodyAsString());
            System.out.println(ex.getMessage());
            throw ex;
        } catch (Exception e) {
            logger.error("Request failed: ", e);
            throw e;
        }
		
	}
	
	private TokenID getToken() {
		logger.info("Start get token vendor-service : ");
		String url = vendorServiceUrl+"/auth/token";
		HttpHeaders headers = new HttpHeaders();
		System.out.println(" URL : "+url);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<TokenID> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                TokenID.class
        );
        
		logger.info("End : get token customer-service : ");
		return response.getBody();
	}
//	public VendorResponse getVendor(Long vendorId) {
//		logger.info("Start : get vendor details by custId : "+vendorId);
//		String url = vendorServiceUrl+"/get/"+vendorId;
//		
//		TokenID token = getToken();
//		
//		HttpHeaders headers = new HttpHeaders();
//		headers.set("Authorization", "Bearer "+token.getToken());
//		System.out.println("token : "+token.getToken());
//		System.out.println(" URL : "+url);
//        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        HttpEntity<Void> entity = new HttpEntity<>(headers);
//
//        try {
//            ResponseEntity<VendorResponse> response = restTemplate.exchange(
//                url,
//                HttpMethod.GET,
//                entity,
//                VendorResponse.class
//            );
//            logger.info("Response: " + response.getBody());
//            return response.getBody();
//        } catch (HttpClientErrorException | HttpServerErrorException ex) {
//            logger.error("HTTP Error: " + ex.getStatusCode() + " - " + ex.getResponseBodyAsString());
//            System.out.println(ex.getMessage());
//            throw ex;
//        } catch (Exception e) {
//            logger.error("Request failed: ", e);
//            throw e;
//        }
//	}
	
	
}
