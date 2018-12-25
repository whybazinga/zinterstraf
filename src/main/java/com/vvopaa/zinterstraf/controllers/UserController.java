package com.vvopaa.zinterstraf.controllers;

import com.vvopaa.zinterstraf.model.json.JsonMessage;
import com.vvopaa.zinterstraf.service.converters.jackson.JsonMessageCreator;
import com.vvopaa.zinterstraf.service.impl.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController("/user")
public class UserController {
  private static final String USERNAME_PARAM = "username";
  private static final String PASSWORD_PARAM = "password";
  private static final String TYPE_PARAM = "type";
  private static final String VK_TYPE = "vk";
  private static final String TWITTER_TYPE = "twitter";
  private static final String FACEBOOK_TYPE = "facebook";
  private static final String GOOGLE_TYPE = "google";
  private static final String SYSTEM_TYPE = "system";

  @Autowired
  private SecurityService securityService;


  @RequestMapping(value = "/main", produces = {"application/json"}, method = {RequestMethod.GET})
  public JsonMessage loginUser(@RequestParam("access_token") String data) {

    JsonMessage jsonReponse = JsonMessageCreator.createSimpleJsonMessage(data + " ez DONE");
    return jsonReponse;
  }

}
