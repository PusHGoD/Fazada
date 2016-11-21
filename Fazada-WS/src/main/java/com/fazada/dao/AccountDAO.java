package com.fazada.dao;

import java.util.List;

import com.fazada.model.Account;

/**
 * @author HuanPM Interface of account DAO (Data Access Object)
 */
public interface AccountDAO {

	/**
	 * @param username
	 * @param password
	 * @return account information
	 */
	public Account findByUserNameAndPassword(String username, String password);

	/**
	 * @param username
	 * @param password
	 * @return account information
	 */
	public Account findByEmailAndPassword(String username, String password);

	/**
	 * @param username
	 * @param password
	 * @return account information
	 */
	public Account findByEmail(String email);

	/**
	 * @param account
	 * @return update result (true/false)
	 */
	public boolean updateInfo(Account account);

	/**
	 * @return list
	 */
	public List<Account> findAll();

	/**
	 * @return list
	 */
	public List<Account> findAllStaff();

	/**
	 * @return list
	 */
	public List<Account> findAllUser();

	/**
	 * @param account
	 */
	public boolean addAccount(Account account);

	/**
	 * @param account
	 * @param password
	 * @return update result
	 */
	public boolean updatePassword(Account account, String password);

	/**
	 * @param account
	 * @param password
	 * @return update result
	 */
	public boolean updatePasswordByEmail(String email, String password);
}
