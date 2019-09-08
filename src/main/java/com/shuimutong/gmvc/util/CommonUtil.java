package com.shuimutong.gmvc.util;

import org.apache.commons.lang3.StringUtils;

/**
 * 普通util
 * @ClassName:  CommonUtil   
 * @Description:(这里用一句话描述这个类的作用)   
 * @author: 水木桶
 * @date:   2019年9月8日 上午9:57:41     
 * @Copyright: 2019 [水木桶]  All rights reserved.
 */
public class CommonUtil {
	/**
	 * 格式化uri
	 * @param uri
	 * @return
	 */
	public static String formatUri(String uri) {
		if(StringUtils.isBlank(uri)) {
			return "";
		}
		uri = "/" + uri;
		String res = uri.replaceAll("/+", "/");
		if(res.endsWith("/")) {
			res = res.substring(0, res.length()-1);
		}
		return res;
	}
}
