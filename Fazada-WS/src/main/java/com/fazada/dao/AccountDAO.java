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
	 * @param email
	 * @param password
	 * @return account information
	 */
	public Account findByEmailAndPassword(String email, String password);

	/**
	 * @param email
	 * @return account information
	 */
	public Account findByEmail(String email);

	/**
	 * @param id
	 * @return account information
	 */
	public Account findByUserId(Integer id);

	/**
	 * @param userName
	 * @return account information
	 */
	public Account findByUsername(String userName);

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
	public boolean updatePassword(String userName, String password);

	/**
	 * @param account
	 * @param password
	 * @return update result
	 */
	public boolean updatePasswordByEmail(String email, String password);
}
