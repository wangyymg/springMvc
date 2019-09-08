package com.shuimutong.gmvc.mapper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.shuimutong.gmvc.annotation.XRequestMapping;
import com.shuimutong.gmvc.bean.EntityBean;
import com.shuimutong.gmvc.util.CommonUtil;
import com.shuimutong.gutil.common.GUtilCommonUtil;

/**
 * servlet映射
 * @ClassName:  ServletMapper   
 * @Description:(这里用一句话描述这个类的作用)   
 * @author: 水木桶
 * @date:   2019年9月7日 下午6:22:19     
 * @Copyright: 2019 [水木桶]  All rights reserved.
 */
public class ServletMapper {
	/**uri-method映射**/
	private static Map<String, Method> URI_MAP = new HashMap();
	
	public static void init() {
		generateUriMap(InstanceManager.getControllerClazzes());
		StringBuilder logSb = new StringBuilder("ServletMapper,scanUriPath:\n");
		for(String uri : URI_MAP.keySet()) {
			logSb.append(uri).append("\n");
		}
		logSb.append("\n").append("---scanUriPath-----end----");
		System.out.println(logSb.toString());
	}
	
	/**
	 * 生成uri-方法映射
	 * @param controllerClazz
	 */
	private static void generateUriMap(Set<EntityBean> controllerClazzBeans) {
		if(GUtilCommonUtil.checkListEmpty(controllerClazzBeans)) {
			return;
		}
		Class<? extends Annotation> requestMappingClazz = XRequestMapping.class;
		for(EntityBean eb : controllerClazzBeans) {
			Class<?> controllerClazz = eb.getClazz();
			String rootUri = "";
			if(controllerClazz.isAnnotationPresent(requestMappingClazz)) {
				XRequestMapping xrm = (XRequestMapping) controllerClazz.getAnnotation(XRequestMapping.class);
				rootUri = xrm.value();
			}
			Method[] methods = controllerClazz.getDeclaredMethods();
			for(Method method : methods) {
				if(method.isAnnotationPresent(requestMappingClazz)) {
					XRequestMapping xrm = (XRequestMapping) method.getAnnotation(XRequestMapping.class);
					String methodUri = xrm.value();
					String fullUri = rootUri + "/" + methodUri;
					URI_MAP.put(CommonUtil.formatUri(fullUri), method);
				}
			}
		}
	}
	
	/**
	 * 获取uri对应的方法
	 * @param uri
	 * @return
	 */
	public static Method getMethodByUri(String uri) {
		return URI_MAP.get(uri);
	}
	
}
