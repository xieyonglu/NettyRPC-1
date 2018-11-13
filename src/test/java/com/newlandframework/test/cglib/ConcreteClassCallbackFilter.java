package com.newlandframework.test.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.CallbackFilter;

public class ConcreteClassCallbackFilter implements CallbackFilter {
	
	@Override
	public int accept(Method method) {
		if ("getConcreteMethodB".equals(method.getName())) {
			return 0;// Callback callbacks[0]
		} else if ("getConcreteMethodA".equals(method.getName())) {
			return 1;// Callback callbacks[1]
		} else if ("getConcreteMethodFixedValue".equals(method.getName())) {
			return 2;// Callback callbacks[2]
		}
		return 1;
	}
}
