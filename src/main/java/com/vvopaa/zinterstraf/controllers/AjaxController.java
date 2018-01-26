package com.vvopaa.zinterstraf.controllers;

import java.util.Locale;

import com.vvopaa.zinterstraf.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vvopaa.zinterstraf.model.json.JsonMessage;
import com.vvopaa.zinterstraf.service.UserService;
import com.vvopaa.zinterstraf.service.converters.jackson.JsonMessageCreator;
import com.vvopaa.zinterstraf.util.StringUtil;


@RestController
public class AjaxController {
	private static final String EMAIL_PARAM = "email";
	private static final String PASS_PARAM = "pass";

	@Autowired 
	private UserService userService;
/*
	@Qualifier("appMessages")
	@Autowired
	private MessageSource msg;

	
	
	@RequestMapping(value = "/register-ajax", produces={"application/json"}, method=RequestMethod.POST)
	public JsonMessage registerUser(@RequestParam(EMAIL_PARAM) String email, @RequestParam(PASS_PARAM) String pass) {
		email = StringUtil.getProperString(email);
		String message = msg.getMessage("user.save.invalid", null, Locale.getDefault());
		if(StringUtil.isStringEmail(email) && StringUtil.isStringProperUserPass(pass)) {
			User user = userService.saveUser(email, pass);
			if(user == null) {
				message = String.format(msg.getMessage("user.save.emailexists", null, Locale.getDefault()), email);
			} else {
				message = String.format(msg.getMessage("user.save.success", null, Locale.getDefault()), email);
			}
		}
		
		JsonMessage jsonReponse = JsonMessageCreator.createSimpleJsonMessage(message);
		return jsonReponse;
	}
*/
	@RequestMapping(value = "/main", produces={"application/json"}, method= {RequestMethod.GET})
	public JsonMessage loginUser(@RequestParam("access_token") String data) {

		JsonMessage jsonReponse = JsonMessageCreator.createSimpleJsonMessage(data + " ez DONE");
		return jsonReponse;
	}

}
