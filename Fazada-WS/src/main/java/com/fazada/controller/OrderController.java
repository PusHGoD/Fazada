package com.fazada.controller;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.UnmarshalException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;

import org.hibernate.HibernateException;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.dao.DataIntegrityViolationException;
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
import com.fazada.model.Order;
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
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = tl.get();
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}

	/**
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String getOrderList() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(service.getOrderList());
	}

	/**
	 * @param d1
	 * @param d2
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/list/time/{date1},{date2}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
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

	/**
	 * @param userName
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/list/user/{user}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String getOrderListByUser(@PathVariable("user") String userName) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(service.getOrderListByUser(userName));
	}

	/**
	 * @param searchValue
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/list/search/{search}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String getOrderListByUserOrNumber(@PathVariable("search") String searchValue)
			throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(service.getOrderListByUserOrNumber(searchValue));
	}

	/**
	 * @param strJSON
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/list/user/time", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String getOrderListByUserAndTimeRange(@RequestBody String strJSON) throws JsonProcessingException {
		JSONObject json = new JSONObject(strJSON);
		String userName = json.getString("userName");
		Date date1 = null, date2 = null;
		try {
			date1 = tl.get().parse(json.getString("date1"));
			date2 = tl.get().parse(json.getString("date2"));
		} catch (ParseException e) {
			return "";
		}
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(service.getOrderListByUserAndTimeRange(userName, date1, date2));
	}

	/**
	 * @param strJSON
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/update/status", method = RequestMethod.POST)
	public ResponseEntity<String> updateStatus(@RequestBody String strJSON) {
		try {
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
		} catch (JSONException e) {
			return new ResponseEntity<>("Input is not valid!", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Input is not valid!", HttpStatus.BAD_REQUEST);
	}

	/**
	 * @param strJSON
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/import/json", method = RequestMethod.POST)
	public ResponseEntity<String> importOrderByJSON(@RequestBody Order order) {
		try {
			if (order != null) {
				if (!service.getOrderByNumber(order.getOrderId()).isEmpty()) {
					return new ResponseEntity<>("Order id is existed!", HttpStatus.BAD_REQUEST);
				}
				boolean result = service.addOrder(order);
				if (result) {
					return new ResponseEntity<>("Order imported!", HttpStatus.OK);
				} else
					return new ResponseEntity<>("Error in updating status!", HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<>("Input is not valid!", HttpStatus.BAD_REQUEST);
			}
		} catch (HibernateException | DataIntegrityViolationException e) {
			return new ResponseEntity<>("Input is not valid!", HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/import/xml", method = RequestMethod.POST)
	public ResponseEntity<String> importOrderByXML(@RequestBody String message) throws JAXBException {
		File file = new File(message);
		if (file.exists()) {
			try {
				JAXBContext context = JAXBContext.newInstance(Order.class);
				Unmarshaller un = context.createUnmarshaller();
				un.setEventHandler(new ValidationEventHandler() {
		            @Override
		            public boolean handleEvent(ValidationEvent event) {
		                return false;
		            }
		        });
				Order order = (Order) un.unmarshal(file);
				if (order != null) {
					if (!service.getOrderByNumber(order.getOrderId()).isEmpty()) {
						return new ResponseEntity<>("Order id is existed!", HttpStatus.BAD_REQUEST);
					}
					boolean result = service.addOrder(order);
					if (result) {
						return new ResponseEntity<>("Order imported!", HttpStatus.OK);
					} else
						return new ResponseEntity<>("Error in updating status!", HttpStatus.INTERNAL_SERVER_ERROR);
				} else {
					return new ResponseEntity<>("Input is not valid!", HttpStatus.BAD_REQUEST);
				}
			} catch (HibernateException | DataIntegrityViolationException | UnmarshalException e) {
				return new ResponseEntity<>("Input is not valid!", HttpStatus.BAD_REQUEST);
			}
		}
		return new ResponseEntity<>("File does not exist or invalid file type!", HttpStatus.BAD_REQUEST);
	}
}
