package com.vvopaa.universalsite.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ViewController {
	
	@Autowired
    private AuthenticationTrustResolver authenticationTrustResolver;
	
	@RequestMapping(value="/login", method = {RequestMethod.GET})
	public String showLoginPage(HttpServletRequest request) {
		return "login";
	}
	
	@RequestMapping(value="/main", method=RequestMethod.GET)
	public ModelAndView handleLoginRequest(HttpServletRequest req, HttpServletResponse res, ModelMap model) {
		
		return new ModelAndView("welcome", model);
	}
	
	@RequestMapping(value="/")
	public ModelAndView startPage(HttpServletRequest req, HttpServletResponse res, ModelMap model) {
		if (isCurrentAuthenticationAnonymous()) {
			String basic_url = "http://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath();
			model.put("basicUrl", basic_url);
			
			return new ModelAndView("entrypage", model);
        } else {
            return new ModelAndView("redirect:/welcome");  
        }
	}
	
	@RequestMapping(value = "/access_denied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        
        return "errorpage";
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
    
    /**
     * This method returns true if users is already authenticated [logged-in], else false.
     */
    private boolean isCurrentAuthenticationAnonymous() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authenticationTrustResolver.isAnonymous(authentication);
    }
}
