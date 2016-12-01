package com.fazada.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
			SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd yyyy");
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

	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
	public String getOrderList() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(service.getOrderList());
	}

	@RequestMapping(value = "/list/time/{date1},{date2}", method = RequestMethod.GET, produces = "application/json")
	public String getOrderListByTimeRange(@PathVariable("date1") String d1, @PathVariable("date2") String d2)
			throws JsonProcessingException {
		Date date1 = null, date2 = null;
		try {
			date1 = tl.get().parse(d1);
			date2 = tl.get().parse(d2);
		} catch (ParseException e) {
			return "";
		}
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(service.getOrderListByTimeRange(date1, date2));
	}

	@RequestMapping(value = "/list/user/{user}", method = RequestMethod.GET, produces = "application/json")
	public String getOrderListByUser(@PathVariable("user") String userName) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(service.getOrderListByUser(userName));
	}

	@RequestMapping(value = "/list/order/{order}", method = RequestMethod.GET, produces = "application/json")
	public String getOrderByNumber(@PathVariable("order") String orderId) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(service.getOrderByNumber(orderId));
	}

	@RequestMapping(value = "/list/search/{search}", method = RequestMethod.GET, produces = "application/json")
	public String getOrderListByUserOrNumber(@PathVariable("search") String searchValue)
			throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(service.getOrderListByUserOrNumber(searchValue));
	}

	@RequestMapping(value = "/list/user/time", method = RequestMethod.POST, produces = "application/json")
	public String getOrderListByUserAndTimeRange(@RequestBody String strJSON) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		JSONObject json = new JSONObject(strJSON);
		String userName = json.getString("userName");
		Date date1 = null, date2 = null;
		try {
			date1 = tl.get().parse(json.getString("date1"));
			date2 = tl.get().parse(json.getString("date2"));
		} catch (ParseException e) {
			return "";
		}
		return mapper.writeValueAsString(service.getOrderListByUserAndTimeRange(userName, date1, date2));
	}

	@RequestMapping(value = "/update/status/", method = RequestMethod.POST)
	public ResponseEntity<String> updateStatus(@RequestBody String strJSON) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		JSONObject json = new JSONObject(strJSON);
		Integer id = json.getInt("orderId");
		Integer status = json.getInt("orderStatus");
		if (id != null && status != null) {
			boolean result = service.updateStatusById(id, status);
			if (result) {
				return new ResponseEntity<>("Status updated!", HttpStatus.OK);
			} else
				return new ResponseEntity<>("Error in updating status!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>("Input is not valid!", HttpStatus.BAD_REQUEST);
	}
}
