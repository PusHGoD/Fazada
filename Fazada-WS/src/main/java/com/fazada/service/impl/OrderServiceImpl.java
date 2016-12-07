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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fazada.service.OrderService#getOrderList()
	 */
	@Override
	public List<Order> getOrderList() {
		return dao.getOrderList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.fazada.service.OrderService#getOrderListByTimeRange(java.util.Date,
	 * java.util.Date)
	 */
	@Override
	public List<Order> getOrderListByTimeRange(Date d1, Date d2) {
		return dao.getOrderListByTimeRange(d1, d2);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fazada.service.OrderService#getOrderListByUser(java.lang.String)
	 */
	@Override
	public List<Order> getOrderListByUser(String userName) {
		return dao.getOrderListByUser(userName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fazada.service.OrderService#getOrderByNumber(java.lang.String)
	 */
	@Override
	public List<Order> getOrderByNumber(String orderId) {
		return dao.getOrderByNumber(orderId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.fazada.service.OrderService#getOrderListByUserOrNumber(java.lang.
	 * String)
	 */
	@Override
	public List<Order> getOrderListByUserOrNumber(String searchValue) {
		return dao.getOrderListByUserOrNumber(searchValue);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.fazada.service.OrderService#getOrderListByUserAndTimeRange(java.lang.
	 * String, java.util.Date, java.util.Date)
	 */
	@Override
	public List<Order> getOrderListByUserAndTimeRange(String userName, Date date1, Date date2) {
		return dao.getOrderListByUserAndTimeRange(userName, date1, date2);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fazada.service.OrderService#updateStatusById(java.lang.Integer,
	 * java.lang.Integer)
	 */
	@Override
	public boolean updateStatusById(Integer orderId, Integer status) {
		return dao.updateStatusById(orderId, status);
	}

	/* (non-Javadoc)
	 * @see com.fazada.service.OrderService#addOrder(com.fazada.model.Order)
	 */
	@Override
	public boolean addOrder(Order order) {
		return dao.addOrder(order);
	}
}
