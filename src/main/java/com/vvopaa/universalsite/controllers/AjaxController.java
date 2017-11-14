package com.vvopaa.universalsite.controllers;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vvopaa.universalsite.model.UserEntity;
import com.vvopaa.universalsite.model.json.JsonMessage;
import com.vvopaa.universalsite.service.RoleService;
import com.vvopaa.universalsite.service.UserService;
import com.vvopaa.universalsite.service.converters.jackson.JsonMessageCreator;
import com.vvopaa.universalsite.util.StringUtil;


@RestController
public class AjaxController {
	private static final String EMAIL_PARAM = "email";
	private static final String PASS_PARAM = "pass";
	
	@Qualifier("userServiceImpl")
	@Autowired 
	private UserService userService;
	
	@Autowired
    private RoleService userProfileService;
	
	@Autowired
	private MessageSource msg;
	
	@Autowired
    private PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;
	
	@Autowired
    private AuthenticationTrustResolver authenticationTrustResolver;
	
	
	@RequestMapping(value = "/register-ajax", produces={"application/json"}, method=RequestMethod.POST)
	public JsonMessage registerUser(@RequestParam(EMAIL_PARAM) String email, @RequestParam(PASS_PARAM) String pass) {
		email = StringUtil.getProperString(email);
		String message = msg.getMessage("user.save.invalid", null, Locale.getDefault());
		if(StringUtil.isStringEmail(email) && StringUtil.isStringProperUserPass(pass)) {
			UserEntity user = userService.saveUser(email, pass);
			if(user == null) {
				message = String.format(msg.getMessage("user.save.emailexists", null, Locale.getDefault()), email);
			} else {
				message = String.format(msg.getMessage("user.save.success", null, Locale.getDefault()), email);
			}
		}
		
		JsonMessage jsonReponse = JsonMessageCreator.createSimpleJsonMessage(message);
		return jsonReponse;
	}
	
	@RequestMapping(value = "/login-ajax", produces={"application/json"}, method= {RequestMethod.POST})
	public JsonMessage loginUser(@RequestParam(EMAIL_PARAM) String email, @RequestParam(PASS_PARAM) String pass) {
		email = StringUtil.getProperString(email);
		String message = msg.getMessage("user.save.invalid", null, Locale.getDefault());
		if(StringUtil.isStringEmail(email) && StringUtil.isStringProperUserPass(pass)) {
			UserEntity user = userService.loginUser(email, pass);
			if(user != null) {
				message = String.format(msg.getMessage("user.login.success", null, Locale.getDefault()), email);
			}
		}
		
		JsonMessage jsonReponse = JsonMessageCreator.createSimpleJsonMessage(message);
		return jsonReponse;
	}
	
	/**
     * This method returns the principal[user-name] of logged-in user.
     */
    private String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
 
        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
}
