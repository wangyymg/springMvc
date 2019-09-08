package com.shuimutong.gmvc.bean;

import com.alibaba.fastjson.JSONObject;

/**
 * 类对象
 * @ClassName:  EntityBean   
 * @Description:(这里用一句话描述这个类的作用)   
 * @author: 水木桶
 * @date:   2019年9月7日 下午4:18:32     
 * @Copyright: 2019 [水木桶]  All rights reserved.
 */
public class EntityBean {
    /**类名**/
    private String name;
    /**class**/
    private Class<?> clazz;
    /**实例对象**/
    private Object o;
    /**依赖对象是否全部注入**/
    private boolean fullAutowired;
    
    public EntityBean(String name, Class<?> clazz) {
        super();
        this.name = name;
        this.clazz = clazz;
    }
    public EntityBean() {
        super();
        // TODO Auto-generated constructor stub
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Class<?> getClazz() {
        return clazz;
    }
    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }
    
    public Object getO() {
        return o;
    }
    public void setO(Object o) {
        this.o = o;
    }
    
    public boolean isFullAutowired() {
		return fullAutowired;
	}
	public void setFullAutowired(boolean fullAutowired) {
		this.fullAutowired = fullAutowired;
	}
	@Override
    public String toString() {
        return "EntityBean:" + JSONObject.toJSONString(this);
    }
    
}
