package com.fazada.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fazada.dao.AccountDAO;
import com.fazada.model.Account;

/**
 * @author HuanPM Implementation of account DAO
 */
public class AccountDAOImpl extends GenericDAOImpl<Integer, Account> implements AccountDAO {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spring.dao.AccountDAO#checkLogin(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Account findByUserNameAndPassword(String username, String password) {
		// Get current session
		Session session = sessionFactory.getCurrentSession();
		// Query with criteria object
		Criteria criteria = session.createCriteria(Account.class);
		criteria.add(Restrictions.eq("userName", username));
		criteria.add(Restrictions.eq("password", password));
		// Get result
		Account account = (Account) criteria.uniqueResult();
		return account;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fazada.dao.AccountDAO#findByEmailAndPassword(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Account findByEmailAndPassword(String email, String password) {
		// Get current session
		Session session = sessionFactory.getCurrentSession();
		// Query with criteria object
		Criteria criteria = session.createCriteria(Account.class);
		criteria.add(Restrictions.eq("email", email));
		criteria.add(Restrictions.eq("password", password));
		// Get result
		Account account = (Account) criteria.uniqueResult();
		return account;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fazada.dao.AccountDAO#findByEmail(java.lang.String)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Account findByEmail(String email) {
		// Get current session
		Session session = sessionFactory.getCurrentSession();
		// Query with criteria object
		Criteria criteria = session.createCriteria(Account.class);
		criteria.add(Restrictions.eq("email", email));
		// Get result
		Account account = (Account) criteria.uniqueResult();
		return account;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fazada.dao.impl.GenericDAOImpl#findById(java.io.Serializable)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Account findByUserId(Integer id) {
		if (id != null) {
			return this.findById(id);
		} else {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fazada.dao.AccountDAO#findByUsername(java.lang.String)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Account findByUsername(String userName) {
		// Get current session
		Session session = sessionFactory.getCurrentSession();
		// Query with criteria object
		Criteria criteria = session.createCriteria(Account.class);
		criteria.add(Restrictions.eq("userName", userName));
		// Get result
		Account account = (Account) criteria.uniqueResult();
		return account;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spring.dao.AccountDAO#updateInfo(com.spring.model.Account)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public boolean updateInfo(Account account) {
		if (account != null) {
			// Inject hidden info (password, active) to arg account
			Account hiddenInfo = this.findById(account.getId());
			if (hiddenInfo != null) {
				if (account.getDateOfBirth() == null) {
					account.setDateOfBirth(hiddenInfo.getDateOfBirth());
				}
				if (account.getEmail() == null) {
					account.setEmail(hiddenInfo.getEmail());
				}
				if (account.getFirstName() == null) {
					account.setFirstName(hiddenInfo.getFirstName());
				}
				if (account.getLastName() == null) {
					account.setLastName(hiddenInfo.getLastName());
				}
				account.setId(hiddenInfo.getId());
				account.setPassword(hiddenInfo.getPassword());
				account.setRole(hiddenInfo.getRole());
				account.setActive(hiddenInfo.isActive());
				// Update account and return result
				return this.update(account);
			} else {
				return false;
			}
		} else
			return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spring.dao.AccountDAO#findAll()
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Account> findAll() {
		return this.find();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fazada.dao.AccountDAO#findAllStaff()
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Account> findAllStaff() {
		// Get current session
		Session session = sessionFactory.getCurrentSession();
		// Query with criteria object
		Criteria criteria = session.createCriteria(Account.class);
		criteria.add(Restrictions.eq("role", "staff"));
		// Get result
		List<Account> list = criteria.list();
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fazada.dao.AccountDAO#findAllUser()
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Account> findAllUser() {
		// Get current session
		Session session = sessionFactory.getCurrentSession();
		// Query with criteria object
		Criteria criteria = session.createCriteria(Account.class);
		criteria.add(Restrictions.eq("role", "user"));
		// Get result
		List<Account> list = criteria.list();
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spring.dao.impl.GenericDAOImpl#add(java.lang.Object)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public boolean addAccount(Account account) {
		if (account != null) {
			return this.add(account);
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fazada.dao.AccountDAO#updatePassword(com.fazada.model.Account,
	 * java.lang.String)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public boolean updatePassword(String userName, String password) {
		if (userName != null && password != null) {
			Account hiddenInfo = this.findByUsername(userName);
			if (hiddenInfo != null) {
				hiddenInfo.setPassword(password);
				return this.update(hiddenInfo);
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fazada.dao.AccountDAO#updatePasswordByEmail(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public boolean updatePasswordByEmail(String email, String password) {
		if (email != null && password != null) {
			Account account = this.findByEmail(email);
			if (account != null) {
				account.setPassword(password);
				return this.update(account);
			}
		}
		return false;
	}
}
