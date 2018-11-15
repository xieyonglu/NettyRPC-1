package com.newlandframework.test.tostring;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ToStringTest {

	public static void main(String[] args) {
		User user = new User("zhangsan", "123", 8, "male");
		
//		ReflectionToStringBuilder.setDefaultStyle(ToStringStyle.SIMPLE_STYLE);
//		ReflectionToStringBuilder.setDefaultStyle(ToStringStyle.MULTI_LINE_STYLE);
		ReflectionToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
		
		String toString = ReflectionToStringBuilder.toStringExclude(user, "password");// 将password属性排出在toString方法之外
		System.out.println(toString);
	}

}
