package com.fazada.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fazada.service.CartService;

@Component
public class ScheduledTask {

	private static final Logger log = Logger.getLogger(ScheduledTask.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	/**
	 * Autowired account DAO
	 */
	@Autowired
	private CartService service;

	@Scheduled(cron = "0 0 0 * * 7")
	public void reportDeleteInactiveCartStatus() {
		service.deleteInactiveCart();
		log.info("Deleted inactive cart at " + dateFormat.format(new Date()));
	}
}