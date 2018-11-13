package com.newlandframework.test.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.CallbackFilter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InterfaceMaker;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import net.sf.cglib.proxy.NoOp;

public class CGLibTest {

	public static void main(String[] args) {
		// 1
//		methodInterceptor();
		
		// 2
//		callBackFilter();
		
		// 3
//		lazyLoad();
		
		// 4
//		dispatcher();
		
		// 5
		interfaceMaker();
	}
	
	// 拦截器
	private static void methodInterceptor() {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(ConcreteClassNoInterface.class);
		enhancer.setCallback(new ConcreteClassInterceptor());
		ConcreteClassNoInterface ccni = (ConcreteClassNoInterface) enhancer.create();

		ccni.getConcreteMethodA("shensy");
		ccni.getConcreteMethodB(0);
		
		/*
			Before:public java.lang.String com.newlandframework.test.cglib.ConcreteClassNoInterface.getConcreteMethodA(java.lang.String)
			ConcreteMethod A ... shensy
			After:public java.lang.String com.newlandframework.test.cglib.ConcreteClassNoInterface.getConcreteMethodA(java.lang.String)
			Before:public int com.newlandframework.test.cglib.ConcreteClassNoInterface.getConcreteMethodB(int)
			ConcreteMethod B ... 0
			After:public int com.newlandframework.test.cglib.ConcreteClassNoInterface.getConcreteMethodB(int)
		*/
	}

	private static void callBackFilter() {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(ConcreteClassNoInterface.class);
		CallbackFilter filter = new ConcreteClassCallbackFilter();
		enhancer.setCallbackFilter(filter);

		Callback interceptor = new ConcreteClassInterceptor();// (1)
		Callback noOp = NoOp.INSTANCE;// (2)
		Callback fixedValue = new ConcreteClassFixedValue();// (3)
		Callback[] callbacks = new Callback[] { interceptor, noOp, fixedValue };
		enhancer.setCallbacks(callbacks);
		ConcreteClassNoInterface proxyObject = (ConcreteClassNoInterface) enhancer.create();
		// ...见下文

		// 接上文...
		System.out.println("*** NoOp Callback ***");
		proxyObject.getConcreteMethodA("abcde"); // 1

		System.out.println("*** MethodInterceptor Callback ***");
		proxyObject.getConcreteMethodB(1); // 0

		System.out.println("*** FixedValue Callback ***");
		int fixed1 = proxyObject.getConcreteMethodFixedValue(128); // 2
		System.out.println("fixedValue1:" + fixed1);
		int fixed2 = proxyObject.getConcreteMethodFixedValue(256); // 2
		System.out.println("fixedValue2:" + fixed2);
	}

	private static void lazyLoad() {
		LoaderBean loader = new LoaderBean();
		System.out.println(loader.getLoaderName());
		System.out.println(loader.getLoaderValue());
		
		PropertyBean propertyBean = loader.getPropertyBean();// 访问延迟加载对象
		System.out.println(propertyBean.getPropertyName());
		System.out.println(propertyBean.getPropertyValue());
		System.out.println("after...");
		// 当再次访问延迟加载对象时,就不会再执行回调了
		System.out.println(propertyBean.getPropertyName());
		System.out.println(propertyBean.getPropertyValue());
		
		/*
		 	loaderNameA
			123
			LazyLoader loadObject() ...
			lazy-load object propertyName!
			11
			after...
			lazy-load object propertyName!
			11
		 */
	}
	
	private static void dispatcher() {
		DispatcherBean dispatcherBean = new DispatcherBean();
		System.out.println(dispatcherBean.getName());
		System.out.println(dispatcherBean.getValue());

		PropertyBean pb = dispatcherBean.getPropertyBean();
		System.out.println(pb.getPropertyName());
		// 在每次访问时都要进行回调
		System.out.println(pb.getPropertyValue());
		
		/*
			DispatcherBean
			abc
			Dispatcher loadObject ...
			PropertyBeanName!
			Dispatcher loadObject ...
			1
		*/
	}
	
	private static void interfaceMaker() {
		InterfaceMaker im = new InterfaceMaker();
		im.add(ConcreteClassNoInterface.class);
		Class interfaceOjb = im.create();
		System.out.println(interfaceOjb.isInterface());// true
		System.out.println(interfaceOjb.getName());// net.sf.cglib.empty.Object$$InterfaceMakerByCGLIB$$13e205f

		Method[] methods = interfaceOjb.getMethods();
		for (Method method : methods) {
			System.out.println(method.getName());
		}

		Object obj = Enhancer.create(Object.class, new Class[] { interfaceOjb }, new MethodInterceptor() {
			public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
				return "intercept!";
			}
		});

		try {
			Method method = obj.getClass().getMethod("getConcreteMethodA", new Class[] { String.class });
			System.out.println(method.invoke(obj, new Object[] { "12345" }));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
