package com.tw.demo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultBeanNameGenerator;

/**
 * 数据库读写分离
 * 
 * @author Toy
 *
 */
public class CustomBeanNameGenerator extends DefaultBeanNameGenerator{
	private static final Logger logger = LoggerFactory.getLogger(CustomBeanNameGenerator.class);
	private String prefix;
	
	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
		try {
			String className = definition.getBeanClassName();
			className = className.substring(className.lastIndexOf(".") + 1);
			return prefix + className;
		} catch (Throwable ex) {
			logger.error("error", ex);
		}
		return definition.getBeanClassName();
	}
}
