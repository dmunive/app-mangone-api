package com.goone.mangone.api.rest.response;

public enum StatusResponse {
	SUCCESS("success"),
	FAIL("fail"),
	ERROR("error");
	private String value;

	StatusResponse(final String value) {
		this.value = value;
	}

	public String getValue(){
		return this.value;
	}
}