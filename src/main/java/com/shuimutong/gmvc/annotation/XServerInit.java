package com.shuimutong.gmvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 服务初始注解
 * @ClassName:  XServerInit   
 * @Description:(这里用一句话描述这个类的作用)   
 * @author: 水木桶
 * @date:   2019年10月21日 上午8:19:11     
 * @Copyright: 2019 [水木桶]  All rights reserved.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface XServerInit {
    /**
     * 优先级
     * @return
     */
    int priority();
    
}
