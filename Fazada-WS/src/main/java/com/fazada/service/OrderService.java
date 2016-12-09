package com.fazada.service;

import java.util.Date;
import java.util.List;

import com.fazada.model.Order;

public interface OrderService {

	/**
	 * @return
	 */
	public List<Order> getOrderList();

	/**
	 * @param d1
	 * @param d2
	 * @return
	 */
	public List<Order> getOrderListByTimeRange(Date d1, Date d2);

	/**
	 * @param userName
	 * @return
	 */
	public List<Order> getOrderListByUser(String userName);

	/**
	 * @param orderId
	 * @return
	 */
	public List<Order> getOrderByNumber(Integer orderId);

	/**
	 * @param searchValue
	 * @return
	 */
	public List<Order> getOrderListByUserOrNumber(String searchValue);

	/**
	 * @param userName
	 * @param date1
	 * @param date2
	 * @return
	 */
	public List<Order> getOrderListByUserAndTimeRange(String userName, Date date1, Date date2);
	
	/**
	 * @param orderId
	 * @param status
	 * @return
	 */
	public boolean updateStatusById(Integer orderId, Integer status) ;
	
	/**
	 * @param order
	 * @return
	 */
	public boolean addOrder(Order order);
}
