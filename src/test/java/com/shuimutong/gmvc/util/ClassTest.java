package com.shuimutong.gmvc.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.junit.Assert;
import org.junit.Test;

import com.shuimutong.gmvc.annotation.XController;
import com.shuimutong.gmvc.annotation.XRequestMapping;


public class ClassTest {
	@Test
	public void className() throws Exception {
		Class clazz = ClassTest.class;
		System.out.println("getCanonicalName:" + clazz.getCanonicalName());
		System.out.println("getName:" + clazz.getName());
		System.out.println("getSimpleName:" + clazz.getSimpleName());
		System.out.println("getTypeName:" + clazz.getTypeName());
		
		System.out.println("---------Method--------------");
		Method m = clazz.getDeclaredMethod("testMethod");
		System.out.println("getClass:" + m.getClass());
		System.out.println("getDeclaringClass:" + m.getDeclaringClass());
		
		
//		getCanonicalName:com.shuimutong.gmvc.util.ClassTest
//		getName:com.shuimutong.gmvc.util.ClassTest
//		getSimpleName:ClassTest
//		getTypeName:com.shuimutong.gmvc.util.ClassTest
//		---------Method--------------
//		getClass:class java.lang.reflect.Method
//		getDeclaringClass:class com.shuimutong.gmvc.util.ClassTest
	}
	
	public void testMethod() {
		System.out.println("hello-method--");
	}
	
	@Test
	public void annotationTest() {
		Class clazz = Controller1Test.class;
		boolean res = clazz.isAnnotationPresent((Class<? extends Annotation>) XController.class);
		System.out.println(res);
		Assert.assertTrue(res);
	}
	
	@Test
	public void extendsTest() {
		Class parentClazz = TestParent.class;
		Class childClazz = TestChild.class;
		Class personClazz = TestPerson.class;
		System.out.println(parentClazz.isAssignableFrom(childClazz));
		
		System.out.println(personClazz.isAssignableFrom(childClazz));
		
	}
}
