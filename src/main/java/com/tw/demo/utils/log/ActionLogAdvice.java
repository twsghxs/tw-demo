package com.tw.demo.utils.log;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.AopUtils;

public class ActionLogAdvice implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		ActionLog data = invocation.getMethod().getAnnotation(ActionLog.class);
		if (data == null) {
			return invocation.proceed();
		}
		String methodName = invocation.getMethod().getName();
		String serviceFullName = AopUtils.getTargetClass(invocation.getThis()).getName();
		long startTime = 0;
		long endTime = 0;
		long costTime = 0;
		Object result = null;
		Double usedSecond = null;
		try {
			startTime = System.currentTimeMillis();
			result = invocation.proceed();
			endTime = System.currentTimeMillis();
			costTime = endTime - startTime;
		} catch (Throwable e) {
			int responseCode = -1;
			endTime = System.currentTimeMillis();
			costTime = endTime - startTime;
			throw e;
		}
		return result;
	}

}
