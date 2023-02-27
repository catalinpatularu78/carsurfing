package com.tus.ride.response;

public class ErrorResponse extends Response {
	String errorCause;

	public ErrorResponse(ResponseCode responseCode, String responseMessage, String reason) {
		super(responseCode, reason);
		this.errorCause = reason;
	}

	public String getReason() {
		return errorCause;
	}

	public void setReason(String reason) {
		this.errorCause = reason;
	}

}
