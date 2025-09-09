package com.vendorloginservice.domain;

public class TranLogRequest {
	
	private TranLogDTO tranLogDTO;
	private SendOTPRequest otpRequest;
	
	public TranLogDTO getTranLogDTO() {
		return tranLogDTO;
	}
	public void setTranLogDTO(TranLogDTO tranLogDTO) {
		this.tranLogDTO = tranLogDTO;
	}
	public SendOTPRequest getOtpRequest() {
		return otpRequest;
	}
	public void setOtpRequest(SendOTPRequest otpRequest) {
		this.otpRequest = otpRequest;
	}
	@Override
	public String toString() {
		return "TranLogRequest [tranLogDTO=" + tranLogDTO + ", otpRequest=" + otpRequest + "]";
	}
	
}
