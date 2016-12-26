package com.fazada.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fazada.service.ProductService;

@RestController
@RequestMapping("product")
public class ProductController {

	/**
	 * Autowired account service
	 */
	@Autowired
	private ProductService service;

	/**
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String getProductList() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(service.getProductList());
	}
}
