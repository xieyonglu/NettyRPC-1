package com.newlandframework.test.cglib;

import net.sf.cglib.proxy.Dispatcher;

public class ConcreteClassDispatcher implements Dispatcher {

	@Override
	public Object loadObject() throws Exception {
		System.out.println("Dispatcher loadObject ...");
		PropertyBean object = new PropertyBean();
		object.setPropertyName("PropertyBeanName!");
		object.setPropertyValue(1);
		return object;
	}
}
