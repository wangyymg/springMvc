package com.shuimutong.gmvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自动注入
 * @ClassName:  XAutowired   
 * @Description:类变量加上这个注解以便自动注入   
 * @author: 
 * @date:   2019年9月7日 下午2:45:27   
 *     
 * @Copyright: 2019 水木桶 All rights reserved.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface XAutowired {

}
