package com.newlandframework.test.pool2;

import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;

public class LpPoolFactory implements PooledObjectFactory<User> {

	/**
	 * 功能描述：激活资源对象
	 * 
	 * 什么时候会调用此方法
	 * 1：从资源池中获取资源的时候
	 * 2：资源回收线程，回收资源的时候，根据配置的 testWhileIdle 参数，判断 是否执行 factory.activateObject()方法，true 执行，false 不执行
	 * @param arg0 
	 */
	@Override
	public void activateObject(PooledObject<User> arg0) throws Exception {
	    System.out.println("activate Object");
	}
	
	@Override
	public void destroyObject(PooledObject<User> arg0) throws Exception {
	    System.out.println("destroy Object");
	    User user = arg0.getObject();
	    user = null;
	}
	
	@Override
	public PooledObject<User> makeObject() throws Exception {
	    System.out.println("make Object");
	    User user = new User(Thread.currentThread().getName(), 26, "178cm");
	    return new DefaultPooledObject<User>(user);
	}
	
	/**
	 * 功能描述：钝化资源对象
	 * 
	 * 什么时候会调用此方法
	 * 1：将资源返还给资源池时，调用此方法。
	 */
	@Override
	public void passivateObject(PooledObject<User> arg0) throws Exception {
	    System.out.println("passivate Object");
	}
	
	/**
	 * 功能描述：判断资源对象是否有效，有效返回 true，无效返回 false
	 * 
	 * 什么时候会调用此方法
	 * 1：从资源池中获取资源的时候，参数 testOnBorrow 或者 testOnCreate 中有一个 配置 为 true 时，则调用  factory.validateObject() 方法
	 * 2：将资源返还给资源池的时候，参数 testOnReturn，配置为 true 时，调用此方法
	 * 3：资源回收线程，回收资源的时候，参数 testWhileIdle，配置为 true 时，调用此方法
	 */
	@Override
	public boolean validateObject(PooledObject<User> arg0) {
	    System.out.println("validate Object");
	    return true;
	}
	
}
