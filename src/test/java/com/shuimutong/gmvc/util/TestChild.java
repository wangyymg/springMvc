package com.shuimutong.gmvc.util;

public class TestChild extends TestParent implements TestPerson {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void speak() {
		System.out.println("hello----");
	}

}
