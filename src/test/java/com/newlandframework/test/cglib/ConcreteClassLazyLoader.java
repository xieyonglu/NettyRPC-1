package com.newlandframework.test.cglib;

import net.sf.cglib.proxy.LazyLoader;

public class ConcreteClassLazyLoader implements LazyLoader {
	
	@Override
	public Object loadObject() throws Exception {
		System.out.println("LazyLoader loadObject() ...");
		PropertyBean bean = new PropertyBean();
		bean.setPropertyName("lazy-load object propertyName!");
		bean.setPropertyValue(11);
		return bean;
	}
}
