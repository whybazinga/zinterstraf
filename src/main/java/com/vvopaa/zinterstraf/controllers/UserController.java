package com.vvopaa.zinterstraf.controllers;

import java.util.Locale;

import com.vvopaa.zinterstraf.exception.UsernameAlreadyExistsException;
import com.vvopaa.zinterstraf.model.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import com.vvopaa.zinterstraf.model.json.JsonMessage;
import com.vvopaa.zinterstraf.service.UserService;
import com.vvopaa.zinterstraf.service.converters.jackson.JsonMessageCreator;


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
					message = "wow";
				} catch (Exception e) {
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

}
