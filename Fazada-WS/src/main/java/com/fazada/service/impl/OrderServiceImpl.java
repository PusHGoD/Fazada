package com.fazada.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.fazada.dao.OrderDAO;
import com.fazada.model.Order;
import com.fazada.service.OrderService;

@Service("/orderservice")
@Component
public class OrderServiceImpl implements OrderService {

	/**
	 * Autowired account DAO
	 */
	@Autowired
	private OrderDAO dao;

	@Override
	public List<Order> getOrderList() {
		return dao.getOrderList();
	}

	@Override
	public List<Order> getOrderListByTimeRange(Date d1, Date d2) {
		return dao.getOrderListByTimeRange(d1, d2);
	}

	@Override
	public List<Order> getOrderListByUser(String userName) {
		return dao.getOrderListByUser(userName);
	}

	@Override
	public List<Order> getOrderByNumber(String orderId) {
		return dao.getOrderByNumber(orderId);
	}

	@Override
	public List<Order> getOrderListByUserOrNumber(String searchValue) {
		return dao.getOrderListByUserOrNumber(searchValue);
	}
}
