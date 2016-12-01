package com.fazada.service;

import java.util.Date;
import java.util.List;

import com.fazada.model.Order;

public interface OrderService {

	public List<Order> getOrderList();

	public List<Order> getOrderListByTimeRange(Date d1, Date d2);

	public List<Order> getOrderListByUser(String userName);

	public List<Order> getOrderByNumber(String orderId);

	public List<Order> getOrderListByUserOrNumber(String searchValue);

	public List<Order> getOrderListByUserAndTimeRange(String userName, Date date1, Date date2);
	
	public boolean updateStatusById(Integer orderId, Integer status) ;
}
