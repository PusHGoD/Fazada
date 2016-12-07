package com.fazada.dao.impl;

import java.util.List;

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
		return this.find();
	}

}
