package com.vvopaa.zinterstraf.controllers;

import java.util.Locale;

import com.vvopaa.zinterstraf.exception.UsernameAlreadyExistsException;
import com.vvopaa.zinterstraf.model.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import com.vvopaa.zinterstraf.model.json.JsonMessage;
import com.vvopaa.zinterstraf.service.UserService;
import com.vvopaa.zinterstraf.service.converters.jackson.JsonMessageCreator;
import org.springframework.web.client.RestTemplate;


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
	private UserService userService;

	@Qualifier("appMessages")
	@Autowired
	private MessageSource msg;

	@RequestMapping(value = "/register", produces={"application/json"}, method={RequestMethod.POST})
	@ApiOperation(value = "register new User", nickname = "register new User")
	public JsonMessage registerUser(
			@RequestParam(value = USERNAME_PARAM, required = false) String username,
			@RequestParam(value = PASSWORD_PARAM, required = false) String password,
			@RequestParam(value = TYPE_PARAM, required = false) String type) {
		String message = "ez DONE";

		switch (type) {
			case SYSTEM_TYPE:
				try {
					User user = userService.saveUser(username, password);
					message = user != null
						? String.format(msg.getMessage("user.save.success", null, Locale.getDefault()), username)
						: String.format(msg.getMessage("user.save.exists", null, Locale.getDefault()), username);
				} catch (UsernameAlreadyExistsException e) {
					message = msg.getMessage("user.save.invalid", null, Locale.getDefault());
				}

			//register JSON MESSAGE
		}

		JsonMessage jsonReponse = JsonMessageCreator.createSimpleJsonMessage(message);
		return jsonReponse;
	}


	@RequestMapping(value = "/main", produces={"application/json"}, method={RequestMethod.GET})
	public JsonMessage loginUser(@RequestParam("access_token") String data) {

		JsonMessage jsonReponse = JsonMessageCreator.createSimpleJsonMessage(data + " ez DONE");
		return jsonReponse;
	}

	@RequestMapping(value = "/test1", produces={"application/json"}, method={RequestMethod.GET})
	public JsonMessage testScheduler() {
		RestTemplate template = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		/*
		headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
		headers.add("Accept", MediaType.APPLICATION_JSON.toString());
	*/
		MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
		/*
		requestBody.add("message_id", "msgid");
		requestBody.add("message", "qwerty");
		requestBody.add("client_id", "111");
		requestBody.add("secret_key", "222");
	*/
		HttpEntity formEntity = new HttpEntity<MultiValueMap<String, String>>(requestBody, headers);

		ResponseEntity<String> response = template.exchange("http://www.dota2.com/procircuit", HttpMethod.GET, formEntity, String.class);

		return JsonMessageCreator.createSimpleJsonMessage(response.toString());
	}

}
