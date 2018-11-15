package com.newlandframework.test.tostring;

public class User {
	private String name;
	private String password;
	private int age;
	private String gender;

	public User(String name, String password, int age, String gender) {
		this.name = name;
		this.password = password;
		this.age = age;
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	/*
	 * @Override public String toString() { return
	 * ReflectionToStringBuilder.toString(this, ToStringStyle.SIMPLE_STYLE); }
	 */

}
