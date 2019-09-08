package com.shuimutong.gmvc.mapper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.reflections.Reflections;

import com.shuimutong.gmvc.annotation.XAutowired;
import com.shuimutong.gmvc.annotation.XComponent;
import com.shuimutong.gmvc.annotation.XController;
import com.shuimutong.gmvc.annotation.XRepository;
import com.shuimutong.gmvc.annotation.XService;
import com.shuimutong.gmvc.bean.EntityBean;
import com.shuimutong.gmvc.bean.SystemConst;
import com.shuimutong.gmvc.util.ClazzUtil;

/**
 * 实例管理
 * @ClassName:  InstanceManager   
 * @Description:(这里用一句话描述这个类的作用)   
 * @author: 水木桶
 * @date:   2019年9月7日 下午10:08:27     
 * @Copyright: 2019 [水木桶]  All rights reserved.
 */
public class InstanceManager {
	/**被注解的类**/
	private static Map<String, EntityBean> CLASS_ENTITY_MAP = new HashMap();
	/**被XController注解的类**/
	private static Set<EntityBean> CONTROLLER_CLASS_ENTITY_MAP = new HashSet();
	
	/**
	 * 初始化
	 * @param conf
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public static void init(Map<String, String> conf) throws InstantiationException, IllegalAccessException {
		String basePackageStr = conf.get(SystemConst.BASE_PACKAGE);
		scanAnnotationedResources(basePackageStr);
		generateAnnotationedEntity();
	}
	
	/**
	 * 获取controller类
	 * @return
	 */
	public static Set<EntityBean> getControllerClazzes() {
		return CONTROLLER_CLASS_ENTITY_MAP;
	}
	
	/**
	 * 根据类（被框架管理的类）获取对应的实例对象
	 * @param clazz
	 * @return
	 */
	public static EntityBean getEntityByClazz(Class clazz) {
		String className = ClazzUtil.getClazzName(clazz);
		return CLASS_ENTITY_MAP.get(className);
	}
	
	/**
	 * 扫描需要框架管理的类
	 * @param basePackageStr
	 */
	private static void scanAnnotationedResources(String basePackageStr) {
		if(StringUtils.isBlank(basePackageStr)) {
			return;
		}
		String[] basePackages = basePackageStr.split(",");
		Reflections reflections = new Reflections(basePackages);
		Class<?>[] annotations = {XController.class, XService.class, XRepository.class, XComponent.class};
		for(Class<?> annotation : annotations) {
			Set<Class<?>> resourceClazzes = reflections
					.getTypesAnnotatedWith((Class<? extends Annotation>) annotation);
			for(Class<?> resourceClazz : resourceClazzes) {
				String className = ClazzUtil.getClazzName(resourceClazz);
				CLASS_ENTITY_MAP.put(className, new EntityBean(className, resourceClazz));
				if(resourceClazz.isAnnotationPresent(XController.class)) {
					CONTROLLER_CLASS_ENTITY_MAP.add(new EntityBean(className, resourceClazz));
				}
			}
		}
	}
	
	/**
	 * 对通过框架管理的类进行实例化
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	private static void generateAnnotationedEntity() throws InstantiationException, IllegalAccessException {
		//先根据构造方法初始化bean
		initBeanInstance(CLASS_ENTITY_MAP.values());
		Set<String> clazzNames = CLASS_ENTITY_MAP.keySet();
		for(String clazzName : clazzNames) {
			EntityBean entityBean = CLASS_ENTITY_MAP.get(clazzName);
			initBeanAutowired(entityBean);
		}
	}
	
	/**
	 * 初始化实例对象
	 * @param classEntityMap
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	private static void initBeanInstance(Collection<EntityBean> entityBeans) throws InstantiationException, IllegalAccessException {
		for(EntityBean entityBean : entityBeans) {
			if(entityBean.getO() == null) {
				Class<?> destClazz = entityBean.getClazz();
				entityBean.setO(destClazz.newInstance());
			}
		}
	}
	
	/**
	 * 初始化bean中注入的类
	 * @param entityBean
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	private static void initBeanAutowired(EntityBean entityBean) throws IllegalArgumentException, IllegalAccessException, InstantiationException {
		if(entityBean.isFullAutowired()) {
			return;
		}
		Class<?> destClazz = entityBean.getClazz();
		Field[] fields = destClazz.getDeclaredFields();
		Object entityInstance = entityBean.getO();
		Collection<EntityBean> entityBeans = CLASS_ENTITY_MAP.values();
		for(Field field : fields) {
			if(!field.isAnnotationPresent(XAutowired.class)) {
				continue;
			}
			field.setAccessible(true);
			Object fieldVal = field.get(entityInstance);
			if(fieldVal != null) {
				continue;
			}
			Class<?> fieldClazz = field.getType();
			EntityBean relayEntity = getEntityByClazz(fieldClazz);
			//依赖的对象能够直接查到
			if(relayEntity != null) {
				field.set(entityInstance, relayEntity.getO());
			} else {
				boolean find = false;
				for(EntityBean otherEntityBean : entityBeans) {
					//判断子类
					if(fieldClazz.isAssignableFrom(otherEntityBean.getClazz())) {
						field.set(entityInstance, otherEntityBean.getO());
						find = true;
						break;
					}
				}
				if(!find) {
					throw new IllegalArgumentException("autowiredEntityNotFoundException");
				}
			}
		}
		entityBean.setFullAutowired(true);
	}
	
	
}
