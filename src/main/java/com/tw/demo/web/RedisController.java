package com.tw.demo.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tw.demo.service.RedisService;


@Controller
@RequestMapping("feedback/redis")
public class RedisController {
	private static final Logger logger = LoggerFactory.getLogger(RedisController.class);
	@Resource
	private RedisService redisService;

	@RequestMapping(value = "get", method = RequestMethod.GET)
	@ResponseBody
	public Object getRedis(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			redisService.get();
		} catch (Exception e) {
			logger.error("get redis error ...", e);
		}
		return "error";
	}

	@RequestMapping(value = "add", method = RequestMethod.GET)
	@ResponseBody
	public Object addRedis(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			redisService.add();
		} catch (Exception e) {
			logger.error("add redis error ...", e);
		}
		return "error";
	}

	@RequestMapping(value = "del", method = RequestMethod.GET)
	@ResponseBody
	public Object delRedis(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			redisService.del();
		} catch (Exception e) {
			logger.error("del redis error ...", e);
		}
		return "error";
	}

}
