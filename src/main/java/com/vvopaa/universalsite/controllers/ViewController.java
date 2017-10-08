package com.vvopaa.universalsite.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ViewController {
	private static final String BASIC_URL = "http://localhost:8080/universalsite";
	
	@RequestMapping(value="/login", method = {RequestMethod.GET})
	public String showLoginPage(HttpServletRequest request) {
		return "login";
	}
	
	@RequestMapping(value="/welcome", method=RequestMethod.POST)
	public ModelAndView handleLoginRequest(HttpServletRequest req, HttpServletResponse res, ModelMap model) {
		
		return new ModelAndView("welcome", model);
	}
	
	@RequestMapping(value="/")
	public ModelAndView startPage(HttpServletRequest req, HttpServletResponse res, ModelMap model) {
		model.put("basicUrl", BASIC_URL);
		
		return new ModelAndView("entrypage", model);
	}
	
	
}
