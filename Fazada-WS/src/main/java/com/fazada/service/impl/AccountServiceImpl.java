package com.fazada.service.impl;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map.Entry;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.fazada.dao.AccountDAO;
import com.fazada.mail.CustomMailHandler;
import com.fazada.model.Account;
import com.fazada.service.AccountService;

/**
 * @author HuanPM Implementation of account service
 */
@Service("/accountservice")
@Component
public class AccountServiceImpl implements AccountService {

	/**
	 * Autowired account DAO
	 */
	@Autowired
	private AccountDAO dao;

	private CustomMailHandler mail = new CustomMailHandler();

	/**
	 * @param size
	 * @return randomized password
	 */
	public String randomPassword(int size) {
		Random random = new Random();
		String pass = "";
		String randomValue = "1234567890qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
		for (int i = 0; i < size; i++) {
			int randomNum = random.nextInt(randomValue.length());
			pass += randomValue.charAt(randomNum);
		}
		return pass;
	}

	/**
	 * @param input
	 * @return MD5 encrypted string (32 characters)
	 */
	public String encryptMD5(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(input.getBytes());
			BigInteger number = new BigInteger(1, messageDigest);
			String hashtext = number.toString(16);
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			return hashtext;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spring.service.AccountService#checkLogin(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public Entry<Integer, Account> checkLogin(String username, String password) {
		Entry<Integer, Account> result = null;
		// Call to DAO and get account information
		Account accountInfo = dao.findByUserNameAndPassword(username, encryptMD5(password));
		// If result is null, return null
		if (accountInfo != null) {
			// If account is inactive, return empty result
			if (!accountInfo.isActive()) {
				result = new AbstractMap.SimpleEntry<Integer, Account>(2, null);
			} else {
				// Return account info
				result = new AbstractMap.SimpleEntry<Integer, Account>(1, accountInfo);
			}
		} else {
			result = new AbstractMap.SimpleEntry<Integer, Account>(0, null);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.spring.service.AccountService#updateAccountInfo(com.spring.entity.
	 * Account, java.lang.String)
	 */
	@Override
	public boolean updateAccountInfo(Account input) {
		if (input != null) {
			// Update and return result
			return dao.updateInfo(input);
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.fazada.service.AccountService#getAccountByUserName(java.lang.String)
	 */
	@Override
	public Account getAccountByUserName(String userName) {
		return dao.findByUsername(userName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spring.service.AccountService#getAccountList()
	 */
	@Override
	public List<Account> getAccountList() {
		return dao.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fazada.service.AccountService#getAllStaff()
	 */
	@Override
	public List<Account> getAllStaff() {
		return dao.findAllStaff();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fazada.service.AccountService#getAllUser()
	 */
	@Override
	public List<Account> getAllUser() {
		return dao.findAllUser();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.fazada.service.AccountService#addNewStaff(com.fazada.model.Account,
	 * java.lang.String, java.lang.String, int)
	 */
	@Override
	public boolean addNewStaff(Account input, String from, String to, int passSize) {
		if (input != null) {
			String enpass = randomPassword(passSize);
			input.setPassword(encryptMD5(enpass));
			input.setRole("staff");
			input.setActive(true);
			boolean result = dao.addAccount(input);
			if (result) {
				mail.sendAddStaffMail(from, to, input.getUserName(), enpass);
			}
			return result;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spring.service.AccountService#addNewAccount(com.spring.entity.
	 * Account, java.lang.String)
	 */
	@Override
	public boolean addNewUser(Account input, String from, String to) {
		if (input != null) {
			input.setPassword(encryptMD5(input.getPassword()));
			input.setRole("user");
			input.setActive(false);
			boolean result = dao.addAccount(input);
			if (result) {
				mail.sendSignupMail(from, to, input.getUserName());
			}
			return result;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fazada.service.AccountService#changePassword(com.fazada.model.
	 * Account)
	 */
	@Override
	public boolean changePassword(Account input) {
		if (input != null) {
			return dao.updatePassword(input.getUserName(), encryptMD5(input.getPassword()));
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spring.service.AccountService#resetPassword(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public boolean resetPassword(Account input, String from, String to) {
		if (input != null && from != null && to != null) {
			String password = randomPassword(9);
			boolean result = dao.updatePassword(input.getUserName(), encryptMD5(password));
			if (result) {
				mail.sendResetMail(from, to, password);
			}
			return result;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.fazada.service.AccountService#resetPasswordByEmail(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public boolean resetPasswordByEmail(String input, String from) {
		if (input != null && from != null) {
			String password = randomPassword(9);
			boolean result = dao.updatePasswordByEmail(input, encryptMD5(password));
			if (result) {
				mail.sendResetMail(from, input, password);
			}
			return result;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fazada.service.AccountService#updateStatusByUserName(java.lang.
	 * String)
	 */
	@Override
	public boolean updateStatusByUserName(String userName) {
		if (userName != null) {
			boolean result = dao.updateStatusByUserName(userName);
			return result;
		}
		return false;
	}
}
