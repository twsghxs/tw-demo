package com.tw.demo.utils.redis;

import java.lang.reflect.Method;

public interface ReadPolicy {
	Object invokeRead(Method method, Object[] args);
}
