package com.vvopaa.zinterstraf.model.json;

import com.fasterxml.jackson.annotation.JsonView;
import com.vvopaa.zinterstraf.service.converters.jackson.Views;

public class JsonMessage {
	@JsonView(Views.msgView.class)
	String response;

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
	
}
