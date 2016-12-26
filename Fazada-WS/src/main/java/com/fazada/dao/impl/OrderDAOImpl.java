package com.fazada.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fazada.dao.OrderDAO;
import com.fazada.model.Order;

public class OrderDAOImpl extends GenericDAOImpl<Integer, Order> implements OrderDAO {

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Order> getOrderList() {
		return this.find();
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Order> getOrderListByTimeRange(Date d1, Date d2) {
		// Get current session
		Session session = sessionFactory.getCurrentSession();
		// Query with criteria object
		Criteria criteria = session.createCriteria(Order.class);
		criteria.add(Restrictions.ge("dateTime", d1));
		criteria.add(Restrictions.le("dateTime", d2));
		// Get result
		List<Order> orders = criteria.list();
		// Return null if none of above conditions are met
		return orders;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Order> getOrderListByUser(String userName) {
		// Get current session
		Session session = sessionFactory.getCurrentSession();
		// Query with criteria object
		Criteria criteria = session.createCriteria(Order.class, "ORDER");
		criteria.createAlias("ORDER.account", "a");
		criteria.add(Restrictions.eq("a.userName", userName));
		// Get result
		List<Order> orders = criteria.list();
		// Return null if none of above conditions are met
		return orders;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Order> getOrderByNumber(Integer orderId) {
		Session session = sessionFactory.getCurrentSession();
		Query<Order> query = session.createQuery("from Order o where o.orderId = :orderid");
		query.setParameter("orderid", orderId);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Order> getOrderListByUserOrNumber(String searchValue) {
		Session session = sessionFactory.getCurrentSession();
		Query<Order> query = session.createQuery(
				"select o from Order o INNER JOIN o.account as a where str(o.orderId) like :orderid OR a.userName like :userName");
		query.setParameter("orderid", "%" + searchValue + "%");
		query.setParameter("userName", "%" + searchValue + "%");
		return query.getResultList();
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Order> getOrderListByUserAndTimeRange(String userName, Date d1, Date d2) {
		// Get current session
		Session session = sessionFactory.getCurrentSession();
		// Query with criteria object
		Criteria criteria = session.createCriteria(Order.class, "ORDER");
		criteria.createAlias("ORDER.account", "a");
		criteria.add(Restrictions.eq("a.userName", userName));
		criteria.add(Restrictions.ge("dateTime", d1));
		criteria.add(Restrictions.le("dateTime", d2));
		// Get result
		List<Order> orders = criteria.list();
		// Return null if none of above conditions are met
		return orders;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public boolean updateStatusById(Integer orderId, Integer status) {
		Session session = sessionFactory.getCurrentSession();
		Query<Order> query = session.createQuery("Update Order o SET orderstatus = :status WHERE orderId = :orderid");
		query.setParameter("orderid", orderId);
		query.setParameter("status", status);
		if (query.executeUpdate() == 1) {
			return true;
		} else
			return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fazada.dao.OrderDAO#addOrder(com.fazada.model.Order)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public boolean addOrder(Order order) {
		if (order != null) {
			return this.add(order);
		}
		return false;
	}
}
