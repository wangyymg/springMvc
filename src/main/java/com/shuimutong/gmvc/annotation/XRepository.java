package com.shuimutong.gmvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Repository注解
 * @ClassName:  XRepository   
 * @Description: DAO层注解
 * @author: 水木桶
 * @date:   2019年9月7日 下午2:49:04     
 * @Copyright: 2019 [水木桶]  All rights reserved.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface XRepository {

}
