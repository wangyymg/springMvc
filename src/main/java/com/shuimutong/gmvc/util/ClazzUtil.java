package com.shuimutong.gmvc.util;

public class ClazzUtil {
	public static String getClazzName(Class clazz) {
		if(clazz != null) {
			return clazz.getName();
		}
		return null;
	}
}
