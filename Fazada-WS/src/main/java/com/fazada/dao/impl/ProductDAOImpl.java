package com.fazada.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fazada.dao.ProductDAO;
import com.fazada.model.Product;

public class ProductDAOImpl extends GenericDAOImpl<Integer, Product> implements ProductDAO {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fazada.dao.ProductDAO#getProductList()
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Product> getProductList() {
		Session session = sessionFactory.getCurrentSession();
		Query<Product> query = session.createQuery("from Product p");
		return query.getResultList();
	}

}
