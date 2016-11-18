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
	 * @param account
	 * @return update result (true/false)
	 */
	public boolean updateInfo(Account account);

	/**
	 * @return list
	 */
	public List<Account> findAll();

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
	
}
