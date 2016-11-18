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

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Order> getOrderListByTimeRange(Date d1, Date d2) {
		// Get current session
		Session session = sessionFactory.getCurrentSession();
		// Query with criteria object
		Criteria criteria = session.createCriteria(Order.class);
		criteria.add(Restrictions.le("dateTime", d1));
		criteria.add(Restrictions.ge("dateTime", d2));
		// Get result
		List<Order> orders = criteria.list();
		// Return null if none of above conditions are met
		return orders;
	}

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

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Order> getOrderByNumber(String orderId) {
		Session session = sessionFactory.getCurrentSession();
		Query<Order> query = session.createQuery("from Order o where str(o.id) like :orderid");
		query.setParameter("orderid", "%" + orderId + "%");
		return query.getResultList();
	}

}
