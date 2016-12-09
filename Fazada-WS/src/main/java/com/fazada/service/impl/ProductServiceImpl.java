package com.fazada.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fazada.dao.ProductDAO;
import com.fazada.model.Product;
import com.fazada.service.ProductService;

public class ProductServiceImpl implements ProductService {

	/**
	 * Autowired product DAO
	 */
	@Autowired
	private ProductDAO dao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fazada.service.ProductService#getProductList()
	 */
	@Override
	public List<Product> getProductList() {
		return dao.getProductList();
	}

}
