package com.newlandframework.test.cglib;

public class ConcreteClassNoInterface {
	public String getConcreteMethodA(String str) {
		System.out.println("ConcreteMethod A ... " + str);
		return str;
	}

	public int getConcreteMethodB(int n) {
		System.out.println("ConcreteMethod B ... " + n);
		return n + 10;
	}

	public int getConcreteMethodFixedValue(int n) {
		System.out.println("getConcreteMethodFixedValue..." + n);
		return n + 10;
	}
}

