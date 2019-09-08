package com.shuimutong.gmvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 资源路径注解
 * @ClassName:  XRequestMapping   
 * @Description: 用于Controller中
 * @author: 水木桶
 * @date:   2019年9月7日 下午2:49:22     
 * @Copyright: 2019 [水木桶]  All rights reserved.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface XRequestMapping {
    /**
     * 关联路径
     * @return
     */
    String value();
    
}
