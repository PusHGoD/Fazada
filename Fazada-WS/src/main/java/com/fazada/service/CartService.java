package com.fazada.service;

import java.util.List;

import com.fazada.model.Cart;

public interface CartService {

	public boolean deleteInactiveCart();
	
	public boolean addToCart();
	
	public List<Cart> getCartList();
	
}
