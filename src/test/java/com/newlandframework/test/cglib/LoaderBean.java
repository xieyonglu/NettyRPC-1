package com.newlandframework.test.cglib;

import net.sf.cglib.proxy.Enhancer;

public class LoaderBean {
	private String loaderName;
	private int loaderValue;
	private PropertyBean propertyBean;

	public LoaderBean() {
		this.loaderName = "loaderNameA";
		this.loaderValue = 123;
		this.propertyBean = createPropertyBean();
	}

	@SuppressWarnings("static-access")
	protected PropertyBean createPropertyBean() {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(PropertyBean.class);
		return (PropertyBean) enhancer.create(PropertyBean.class, new ConcreteClassLazyLoader());
	}

	public String getLoaderName() {
		return loaderName;
	}

	public void setLoaderName(String loaderName) {
		this.loaderName = loaderName;
	}

	public int getLoaderValue() {
		return loaderValue;
	}

	public void setLoaderValue(int loaderValue) {
		this.loaderValue = loaderValue;
	}

	public PropertyBean getPropertyBean() {
		return propertyBean;
	}

	public void setPropertyBean(PropertyBean propertyBean) {
		this.propertyBean = propertyBean;
	}
}
