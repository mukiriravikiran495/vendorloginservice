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
import com.vendorloginservice.domain.TranLogRequest;
import com.vendorloginservice.domain.TranLogResponse;

@Component
public class TransactionUtils {
private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
    private  RestTemplate restTemplate;
	
	@Value("${transaction.service.url}")
    private String transactionServiceUrl;

	
	public TransactionUtils() {
		
	}
	@Autowired
	public TransactionUtils(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	

	public TranLogResponse createTransaction(TranLogRequest tranLogRequest, long vendorId, String token) {
		logger.info("Start : create transaction utils : "+tranLogRequest);
		
		String url = transactionServiceUrl+"/create";
		
		System.out.println(tranLogRequest.getTranLogDTO().getVendorId());
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+token);
		System.out.println("token : "+token);
		System.out.println(" URL : "+url);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<TranLogRequest> entity = new HttpEntity<>(tranLogRequest, headers);

        try {
            ResponseEntity<TranLogResponse> tranResponse = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                TranLogResponse.class
            );
            logger.info("Response: " + tranResponse.getBody());
            logger.info("End : create customer utils : ");
            return tranResponse.getBody();
        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            logger.error("HTTP Error: " + ex.getStatusCode() + " - " + ex.getResponseBodyAsString());
            System.out.println(ex.getMessage());
            throw ex;
        } catch (Exception e) {
            logger.error("Request failed: ", e);
            throw e;
        }
	}
	
}
