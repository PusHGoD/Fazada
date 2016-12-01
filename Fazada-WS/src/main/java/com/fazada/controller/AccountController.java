package com.fazada.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
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
	public ResponseEntity<String> login(@RequestBody Account account) {
		try {
			// Log in and get account information
			Entry<Integer, Account> result = service.checkLogin(account.getUserName(), account.getPassword());
			switch (result.getKey()) {
			case 0: {
				// Redirect to login page
				return new ResponseEntity<String>("Invalid username or password", HttpStatus.BAD_REQUEST);
			}
			case 1: {
				Account info = result.getValue();
				return new ResponseEntity<String>(info.getRole(), HttpStatus.OK);
			}
			case 2: {
				// Redirect to login page
				return new ResponseEntity<String>(
						"Your account is currently inactive. Please contact support for more information.",
						HttpStatus.BAD_REQUEST);
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
		return new ResponseEntity<String>("Error in processing", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * @param model
	 * @param account
	 * @param dateOfBirth
	 * @param session
	 * @return logic name of home page
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<String> updateInfo(@RequestBody Account account) {
		try {
			// Update account and get result
			boolean result = service.updateAccountInfo(account);
			// Check if update operation is successful
			if (result) {
				// Put success message to home page
				return new ResponseEntity<String>("Your information has been updated!", HttpStatus.OK);
			} else {
				// Put error message to home page
				return new ResponseEntity<String>("Error in updating account! No update processed",
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (HibernateException e) {
			// Log Hibernate error message
			logger.error("Error in processing request in DB :" + e.getMessage());
			return new ResponseEntity<String>("Error occurred in processing request!",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
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
	public ResponseEntity<String> addStaff(@RequestBody Account account) {
		try {
			// Add account and get result
			boolean result = service.addNewStaff(account, "minhhuan@test.com", account.getEmail(), 9);
			// Check if the operation is successful
			if (result) {
				// Return message
				return new ResponseEntity<String>("You have added new staff!", HttpStatus.OK);
			} else {
				// Return message
				return new ResponseEntity<String>("Error in adding staff!", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (HibernateException e) {
			// Log Hibernate error message
			logger.error("Error in processing request in DB :" + e.getMessage());
		}
		// Return message
		return new ResponseEntity<String>("Error occurred in DB processing", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * @param account
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ResponseEntity<String> editAccount(@RequestBody Account account) {
		try {
			// Update account and get result
			boolean result = service.updateAccountInfo(account);
			// Check if update operation is successful
			if (result) {
				// Return message
				return new ResponseEntity<String>("You have updated user " + account.getUserName() + "!",
						HttpStatus.OK);
			} else {
				// Return message
				return new ResponseEntity<String>("Error in updating user!", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (HibernateException e) {
			// Log Hibernate error message
			logger.error("Error in processing request in DB :" + e.getMessage());
		}
		// Return message
		return new ResponseEntity<String>("Error occurred in DB processing", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ResponseEntity<String> signupAccount(@RequestBody Account account) {
		try {
			// Add account and get result
			boolean result = service.addNewUser(account, "minhhuan@test.com", account.getEmail());
			// Check if the operation is successful
			if (result) {
				// Return message
				return new ResponseEntity<String>("You have successfully registered! Please check your email!",
						HttpStatus.OK);
			} else {
				// Return message
				return new ResponseEntity<String>("Error in adding user!", HttpStatus.BAD_REQUEST);
			}
		} catch (HibernateException e) {
			// Log Hibernate error message
			logger.error("Error in processing request in DB :" + e.getMessage());
		}
		// Return message
		return new ResponseEntity<String>("Error occurred in DB processing", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public ResponseEntity<String> changePassword(@RequestBody String jsonStr, HttpServletResponse response) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			JSONObject json = new JSONObject(jsonStr);
			String oldPassword = (String) json.getString("oldPassword");
			json.remove("oldPassword");
			Account account = mapper.readValue(json.toString(), Account.class);
			Entry<Integer, Account> checkResult = service.checkLogin(account.getUserName(), oldPassword);
			if (checkResult != null) {
				switch (checkResult.getKey()) {
				case 0:
					return new ResponseEntity<String>("Please check your password!", HttpStatus.BAD_REQUEST);
				case 1: {
					// Reset password and get result
					boolean result = service.changePassword(account);
					// Check if update operation is successful
					if (result) {
						// Return message
						return new ResponseEntity<String>("Your password has been changed!", HttpStatus.OK);
					} else {
						// Return message
						return new ResponseEntity<String>("Error in changing password!",
								HttpStatus.INTERNAL_SERVER_ERROR);
					}
				}
				case 2: {
					return new ResponseEntity<String>("Account is not active! Illegial access detected",
							HttpStatus.INTERNAL_SERVER_ERROR);
				}
				default:
					return new ResponseEntity<String>("Unknown error code detected", HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}

		} catch (HibernateException e) {
			// Log Hibernate error message
			logger.error("Error in processing request in DB :" + e.getMessage());
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			logger.error("Error in processing request in DB :" + e.getMessage());
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			logger.error("Error in processing request in DB :" + e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("Error in processing request in DB :" + e.getMessage());
		}
		// Return message
		return new ResponseEntity<String>("Error occurred in DB processing", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * @param account
	 * @return
	 */
	@RequestMapping(value = "/reset", method = RequestMethod.POST)
	public ResponseEntity<String> resetPassword(@RequestBody String email) {
		try {
			// Reset password and get result
			boolean result = service.resetPasswordByEmail(email, "minhhuan@test.com");
			// Check if update operation is successful
			if (result) {
				// Return message
				return new ResponseEntity<String>(
						"Your account has been resetted! Please check your email to get your new password!",
						HttpStatus.OK);
			} else {
				// Return message
				return new ResponseEntity<String>("No user found with this email!", HttpStatus.NOT_FOUND);
			}
		} catch (HibernateException e) {
			// Log Hibernate error message
			logger.error("Error in processing request in DB :" + e.getMessage());
		}
		// Return message
		return new ResponseEntity<String>("Error occurred in DB processing", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@RequestMapping(value = "/info/{userName}", method = RequestMethod.GET, headers = "Accept=*/*", produces = "application/json")
	public String getAccountInfo(@PathVariable("userName") String userName) throws JsonProcessingException {
		// Get list
		Account list = service.getAccountByUserName(userName);
		// Convert list into JSON type
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(list);
	}
}
