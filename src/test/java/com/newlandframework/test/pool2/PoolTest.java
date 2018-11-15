package com.newlandframework.test.pool2;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class PoolTest {

	public static void main(String[] args) throws Exception {
		// 工厂
		LpPoolFactory factory = new LpPoolFactory();
		// 资源池配置
		GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
		poolConfig.setMinIdle(2); // 连接池中最少空闲的连接数,默认为0
		poolConfig.setMaxTotal(3); // 链接池中最大连接数,默认为8
		// 链接池中最大连接数,默认为8
		
		// 创建资源池
		GenericObjectPool<User> objectPool = new GenericObjectPool<User>(factory, poolConfig);

		for(int j=0; j<10; j++) {
			new Thread(() -> {
				for (int i = 0; i < 10; i++) {
					try {
						Thread.sleep(1000);
						// 获取资源对象
						User user = objectPool.borrowObject();
						// 将获取的资源对象，返还给资源池
						objectPool.returnObject(user);
						// 输出资源对象
						System.out.println(user);
						System.out.println();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
	}

}
