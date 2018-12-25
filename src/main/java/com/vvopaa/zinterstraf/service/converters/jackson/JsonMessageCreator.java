package com.vvopaa.zinterstraf.service.converters.jackson;

import com.vvopaa.zinterstraf.model.json.JsonMessage;

public class JsonMessageCreator {

  public static JsonMessage createSimpleJsonMessage(String response) {
    JsonMessage msg = new JsonMessage();
    msg.setResponse(response);
    return msg;
  }
}
