package com.vvopaa.universalsite.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class BasicController {
	private static final String BASIC_URL = "https://localhost:8080/UniversalSite/";
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String showLoginPage() {
		return "login";
	}
	
	@RequestMapping(value="/welcome", method=RequestMethod.POST)
	public ModelAndView handleLoginRequest(HttpServletRequest req, HttpServletResponse res, ModelMap model) {
		String name = req.getParameter("name");
		String pass = req.getParameter("pass");
		if(name == null || name.trim().isEmpty()) {
			model.put("errName", "Error in name");
			if(pass == null || pass.trim().isEmpty()) {
				model.put("errPass", "Error in pass");
			}
			return new ModelAndView("redirect:/login", model);
		}
		
		model.put("name", name);
		model.put("pass", pass);
		
		return new ModelAndView("welcome", model);
	}
	
	@RequestMapping(value="/")
	public ModelAndView startPage(HttpServletRequest req, HttpServletResponse res, ModelMap model) {
		model.put("basicUrl", BASIC_URL);
		
		return new ModelAndView("entrypage", model);
	}
	
	
}
