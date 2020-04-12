package com.shuimutong.gmvc.handler;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shuimutong.gmvc.bean.EntityBean;
import com.shuimutong.gmvc.bean.GmvcSystemConst;
import com.shuimutong.gmvc.mapper.InstanceManager;
import com.shuimutong.gmvc.mapper.ServletMapper;

/**
 * 调度servlet
 * @ClassName:  XDispatcherServlet   
 * @Description:(这里用一句话描述这个类的作用)   
 * @author: 水木桶
 * @date:   2019年9月8日 上午11:58:37     
 * @Copyright: 2019 [水木桶]  All rights reserved.
 */
public class XDispatcherServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(XDispatcherServlet.class);
	
	@Override
    public void init() throws ServletException {
        super.init();
        //获取ServletConfig对象
        ServletConfig config = this.getServletConfig();
        //根据参数名获取参数值
//        String basePackage = config.getInitParameter(SystemConst.BASE_PACKAGE);
        Map<String, String> confMap = new HashMap();
        confMap.put(GmvcSystemConst.BASE_PACKAGE, config.getInitParameter(GmvcSystemConst.BASE_PACKAGE));
        try {
			InstanceManager.initAnnotationedResourcesAndDoInit(confMap);
		} catch (Exception e) {
			throw new ServletException(e);
		}
        ServletMapper.init();
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	    System.out.println("Hello");
	    String requestUri = request.getRequestURI().replace(request.getContextPath(), "");
//	    System.out.println("requestUri:" + requestUri);
	    Method resolveMethod = ServletMapper.getMethodByUri(requestUri);
	    if(resolveMethod == null) {
	    	log.warn("UriNotFound:" + requestUri);
	    } else {
	    	EntityBean entityBean = InstanceManager.getEntityBeanByClazz(resolveMethod.getDeclaringClass());
	    	if(entityBean == null) {
	    		throw new ServletException("uriNotFoundException");
	    	}
	    	try {
	    		resolveMethod.invoke(entityBean.getO(), request, response);
	    	} catch (Exception e) {
	    		log.error("execute" + resolveMethod.getName() + "Exception", e);
	    		throw new ServletException(e);
	    	}
	    }
	}
}
