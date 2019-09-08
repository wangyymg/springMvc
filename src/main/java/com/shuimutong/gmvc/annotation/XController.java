package com.shuimutong.gmvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Controller注解
 * @ClassName:  XController   
 * @Description: Controller层注解
 * @author: 
 * @date:   2019年9月7日 下午2:48:22     
 * @Copyright: 2019 [水木桶]  All rights reserved.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface XController {

}
