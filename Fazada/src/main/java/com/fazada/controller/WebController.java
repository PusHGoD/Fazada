package com.fazada.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("")
public class WebController {

	/**
	 * Log4j logger
	 */
	private static Logger logger = Logger.getLogger(WebController.class.getName());

	/**
	 * A thread-safe method to store SimpleDateFormat
	 */
	private static final ThreadLocal<SimpleDateFormat> tl = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			return sdf;
		}
	};

	/**
	 * register property editors
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = tl.get();
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}

	/**
	 * @param account
	 * @return logic name of login page
	 */
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String getMainPage() {
		return "main";
	}

	/**
	 * @param account
	 * @return logic name of login page
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam("userName") String username, @RequestParam("role") String role,
			HttpSession session) {
		switch (role) {
		case "admin":
		case "user":
		case "staff": {
			session.setAttribute("accountInfo", username);
			session.setAttribute("role", role);
			break;
		}
		}
		return "redirect:/main";
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String getHomePage() {
		// Redirect to home page
		return "home";
	}

	@RequestMapping(value = "/manager", method = RequestMethod.GET)
	public String getManagerPage() {
		// Redirect to home page
		return "manager";
	}

	/**
	 * @param session
	 * @return redirection to login page
	 */
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		// Remove current session from the registry
		session.invalidate();
		// Redirect to login page
		return "redirect:/main";
	}

	@RequestMapping(value = "/order", method = RequestMethod.GET)
	public String getOrderPage() {
		// Redirect to home page
		return "order";
	}
}
