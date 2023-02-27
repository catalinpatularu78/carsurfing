package com.tus.user.response;

public class ErrorResponse extends Response {

	String errorCause;

	public ErrorResponse(ResponseCode responseCode, String responseMessage, String reason) {
		super(responseCode, responseMessage);
		this.errorCause = reason;
	}

	public String getReason() {
		return errorCause;
	}

	public void setReason(String reason) {
		this.errorCause = reason;
	}

}
