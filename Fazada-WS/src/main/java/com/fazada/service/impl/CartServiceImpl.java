package com.fazada.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fazada.dao.CartDAO;
import com.fazada.model.Cart;
import com.fazada.service.CartService;

public class CartServiceImpl implements CartService {

	@Autowired
	public CartDAO dao;

	@Override
	public boolean deleteInactiveCart() {
		return dao.deleteInactiveCart();
	}

	@Override
	public boolean addToCart() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Cart> getCartList() {
		return dao.getCartList();
	}

}
