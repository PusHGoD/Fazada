package com.fazada.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fazada.model.Account;
import com.fazada.service.AccountService;

@RestController
@RequestMapping("account")
public class AccountController {

	/**
	 * Log4j logger
	 */
	private static Logger logger = Logger.getLogger(AccountController.class.getName());

	/**
	 * Autowired account service
	 */
	@Autowired
	private AccountService service;

	/**
	 * @param model
	 * @param account
	 * @param session
	 * @return logic name of login page
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestBody Account account) {
		try {
			// Log in and get account information
			Entry<Integer, Account> result = service.checkLogin(account.getUserName(), account.getPassword());
			switch (result.getKey()) {
			case 0: {
				// Redirect to login page
				return "Invalid username or password";
			}
			case 1: {
				Account info = result.getValue();
				return info.getRole();
				// switch (info.getRole().toLowerCase()) {
				// case "admin":
				// return "admin";
				// case "staff":
				// return "staff";
				// default:
				// // Redirect to home page
				// return "user";
				// }
			}
			case 2: {
				// Redirect to login page
				return "Your account is currently inactive. Please contact support for more information.";
			}
			default: {
				// Log Hibernate error message
				logger.error("Unknown return code has returned" + result.getKey());
				break;
			}
			}
		} catch (HibernateException e) {
			// Log Hibernate error message
			logger.error("Error in processing request in DB :" + e.getMessage());
		}
		return "invalid";
	}

	/**
	 * @param model
	 * @param account
	 * @param dateOfBirth
	 * @param session
	 * @return logic name of home page
	 */
	@RequestMapping(value = "/home", method = RequestMethod.POST)
	public String updateInfo(ModelMap model, @RequestBody Account account, HttpSession session) {
		try {
			// Update account and get result
			boolean result = service.updateAccountInfo(account);
			// Check if update operation is successful
			if (result) {
				// Update session account attribute
				session.setAttribute("accountInfo", account);
				// Put success message to home page
				model.put("successMessage", "Your information has been updated!");
				// Redirect to home page
				return "home";
			} else {
				// Put error message to home page
				model.put("errorMessage", "Error in updating account! No update processed");
				// Redirect to home page
				return "home";
			}
		} catch (HibernateException e) {
			// Put error message to home page
			model.put("errorMessage", "Error occurred in processing request!");
			// Log Hibernate error message
			logger.error("Error in processing request in DB :" + e.getMessage());
		}
		return "home";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET, headers = "Accept=*/*", produces = "application/json")
	public String loadAccountList() throws JsonProcessingException {
		// Get list
		List<Account> list = service.getAccountList();
		// Convert list into JSON type
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(list);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addAccount(@RequestBody Account account) {
		try {
			// Add account and get result
			boolean result = service.addNewAccount(account, "minhhuan@test.com", account.getEmail(), 9);
			// Check if the operation is successful
			if (result) {
				// Return message
				return "You have added new user!";
			} else {
				// Return message
				return "Error in adding user!";
			}
		} catch (HibernateException e) {
			// Log Hibernate error message
			logger.error("Error in processing request in DB :" + e.getMessage());
		}
		// Return message
		return "Error occurred in DB processing";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signupAccount(@RequestBody Account account) {
		try {
			// Add account and get result
			boolean result = service.addNewAccount(account, "minhhuan@test.com", account.getEmail(), 9);
			// Check if the operation is successful
			if (result) {
				// Return message
				return "You have successfully registered! Please check your email!";
			} else {
				// Return message
				return "Error in adding user!";
			}
		} catch (HibernateException e) {
			// Log Hibernate error message
			logger.error("Error in processing request in DB :" + e.getMessage());
		}
		// Return message
		return "Error occurred in DB processing";
	}
	
	/**
	 * @param account
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.PUT)
	public String editAccount(@RequestBody Account account) {
		try {
			// Update account and get result
			boolean result = service.updateAccountInfo(account);
			// Check if update operation is successful
			if (result) {
				// Return message
				return "You have updated user " + account.getUserName() + "!";
			} else {
				// Return message
				return "Error in updating user!";
			}
		} catch (HibernateException e) {
			// Log Hibernate error message
			logger.error("Error in processing request in DB :" + e.getMessage());
		}
		// Return message
		return "Error occurred in DB processing";
	}

	/**
	 * @param account
	 * @return
	 */
	@RequestMapping(value = "/reset", method = RequestMethod.POST)
	public String resetPassword(@RequestBody String email) {
		try {
			// Reset password and get result
			boolean result = service.resetPasswordByEmail(email, "minhhuan@test.com");
			// Check if update operation is successful
			if (result) {
				// Return message
				return "You have resetted password!";
			} else {
				// Return message
				return "Error in resetting password!";
			}
		} catch (HibernateException e) {
			// Log Hibernate error message
			logger.error("Error in processing request in DB :" + e.getMessage());
		}
		// Return message
		return "Error occurred in DB processing";
	}

}
