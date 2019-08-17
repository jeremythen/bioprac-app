package com.bioprac.util;

public class BiopracResponse {
	
	private boolean success;
	private String message;
	private Object extraData;
	
	public BiopracResponse(boolean success, String message) {
		this.success = success;
		this.message = message;
	}
	
	public BiopracResponse(boolean success, String message, Object extraData) {
		this.success = success;
		this.message = message;
		this.extraData = extraData;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public Object getExtraData() {
		return extraData;
	}

	public void setExtraData(Object extraData) {
		this.extraData = extraData;
	}

	@Override
	public String toString() {
		return "BiopracResponse [success=" + success + ", message=" + message + "]";
	}

}
