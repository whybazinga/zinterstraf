package com.vvopaa.universalsite.service.converters.jackson;

import com.vvopaa.universalsite.model.json.JsonMessage;

public class JsonMessageCreator {

	public static JsonMessage createSimpleJsonMessage(String response) {
		JsonMessage msg = new JsonMessage();
		msg.setResponse(response);
		return msg;
	}
}
