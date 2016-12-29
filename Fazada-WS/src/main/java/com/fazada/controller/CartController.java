package com.fazada.controller;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cart")
public class CartController {

	/**
	 * Log4j logger
	 */
	private static Logger logger = Logger.getLogger(CartController.class.getName());

	@RequestMapping(value = "/addToCart")
	public ResponseEntity<String> addToCart(@RequestBody String json) {
		logger.info("Not implemented");
		return new ResponseEntity<String>("1", HttpStatus.OK);
	}
}
