package com.fazada.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fazada.service.OrderService;

@RestController
@RequestMapping("order")
public class OrderController {

	/**
	 * Autowired account service
	 */
	@Autowired
	private OrderService service;
	
	/**
	 * A thread-safe method to store SimpleDateFormat
	 */
	private static final ThreadLocal<SimpleDateFormat> tl = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			return sdf;
		}
	};

	/**
	 * register property editors
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = tl.get();
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}
	
	@RequestMapping(value="/list", method = RequestMethod.GET, produces = "application/json")
	public String getOrderList() throws JsonProcessingException{
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(service.getOrderList());
	}
	
	@RequestMapping(value="/list/time/{date1},{date2}", method = RequestMethod.GET, produces = "application/json")
	public String getOrderListByTimeRange(@PathVariable("date1") Date d1, @PathVariable("date2") Date d2) throws JsonProcessingException{
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(service.getOrderListByTimeRange(d1, d2));
	}
	
	@RequestMapping(value="/list/user/{user}", method = RequestMethod.GET, produces = "application/json")
	public String getOrderListByUser(@PathVariable("user") String userName) throws JsonProcessingException{
		ObjectMapper mapper = new ObjectMapper();
		return  mapper.writeValueAsString(service.getOrderListByUser(userName));
	}
	
	@RequestMapping(value="/list/order/{order}", method = RequestMethod.GET, produces = "application/json")
	public String getOrderByNumber(@PathVariable("order") String orderId) throws JsonProcessingException{
		ObjectMapper mapper = new ObjectMapper();
		return  mapper.writeValueAsString(service.getOrderByNumber(orderId));
	}
}
