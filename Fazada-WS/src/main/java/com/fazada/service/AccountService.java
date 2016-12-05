package com.fazada.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map.Entry;

import com.fazada.model.Account;

/**
 * @author HuanPM Interface of account service
 */
public interface AccountService {

	/**
	 * @param username
	 * @param password
	 * @return key: return code and value: account information (if return code
	 *         is 1)
	 */
	public Entry<Integer, Account> checkLogin(String username, String password);

	/**
	 * @param input
	 * @param strDOB
	 *            strDOB: a string following dd/mm/yyyy format
	 * @return update result
	 * @throws ParseException
	 */
	public boolean updateAccountInfo(Account input);

	public Account getAccountByUserName(String userName);
	
	/**
	 * @return List
	 */
	public List<Account> getAccountList();

	/**
	 * @return List
	 */
	public List<Account> getAllStaff();

	/**
	 * @return List
	 */
	public List<Account> getAllUser();

	/**
	 * @param input
	 * @param from
	 * @param to
	 * @param passSize
	 * @return
	 */
	public boolean addNewStaff(Account input, String from, String to, int passSize);

	/**
	 * @param input
	 * @param from
	 * @param to
	 * @param passSize
	 * @return add result
	 */
	public boolean addNewUser(Account input, String from, String to);

	/**
	 * @param input
	 * @return change password result
	 */
	public boolean changePassword(Account input);
	
	/**
	 * @param from
	 * @param to
	 * @param password
	 * @return reset password result
	 */
	public boolean resetPassword(Account input, String from, String to);

	/**
	 * @param input
	 * @param from
	 * @return
	 */
	public boolean resetPasswordByEmail(String input, String from);
	
	/**
	 * @param userName
	 * @return
	 */
	public boolean updateStatusByUserName(String userName);
}
