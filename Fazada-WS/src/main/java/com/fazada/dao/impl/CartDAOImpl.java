package com.fazada.dao.impl;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fazada.dao.CartDAO;
import com.fazada.model.Cart;

public class CartDAOImpl extends GenericDAOImpl<Integer, Cart> implements CartDAO {

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Cart> getCartList() {
		return this.find();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Cart getCartByUserId(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		Query<Cart> query = session.createQuery("FROM Cart AS c WHERE c.userId = :userId)");
		query.setParameter("userId", id);
		return query.getSingleResult();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public boolean addToCart(Cart cart) {
		if (cart != null) {
			Session session = sessionFactory.getCurrentSession();
			session.saveOrUpdate(cart);
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public boolean deleteInactiveCart() {
		// Get current session
		Session session = sessionFactory.getCurrentSession();
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, -7);
		Query<Cart> query = session.createQuery("DELETE FROM Cartdetail AS cd WHERE cd.cart IN "
				+ "(SELECT c FROM Cart AS c WHERE c.updateDate <= :weekbefore)");
		query.setParameter("weekbefore", calendar.getTime());
		query.executeUpdate();
		query = session.createQuery("DELETE FROM Cart AS c WHERE c.updateDate <= :weekbefore)");
		query.setParameter("weekbefore", calendar.getTime());
		query.executeUpdate();
		return true;
	}
}
