package com.tw.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RedisService {
	
	private static final Logger logger = LoggerFactory.getLogger(RedisService.class);

	public void get() {
		logger.info("get ... ");
	}

	public void add() {
		logger.info("add ... ");
	}

	public void del() {
		logger.info("del ... ");
	}

}
