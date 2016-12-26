package com.fazada.dao;

import java.util.List;

import com.fazada.model.Cart;

public interface CartDAO {

	public List<Cart> getCartList();
	
	public Cart getCartByUserId(Integer id);
	
	public boolean addToCart(Cart cart);
	
	public boolean deleteInactiveCart();
}
