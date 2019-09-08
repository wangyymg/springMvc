package com.shuimutong.gmvc.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

/**
 * 请求处理类
 * @ClassName:  RequestResolveUtil   
 * @Description:(这里用一句话描述这个类的作用)   
 * @author: 水木桶
 * @date:   2019年9月8日 上午11:39:43     
 * @Copyright: 2019 [水木桶]  All rights reserved.
 */
public class RequestResolveUtil {
	private static final Logger log = LoggerFactory.getLogger(RequestResolveUtil.class);
	public static final String DEFAULT_ENCODE = "UTF-8";
	
	/**
	 * 向请求返回数据
	 * @param request
	 * @param response
	 * @param returnContent
	 */
	public static void returnJson(HttpServletRequest request,
            HttpServletResponse response, String returnContent) {
        try {
        	String encode = request.getParameter("encode");
        	if(StringUtils.isBlank(encode)) {
        		encode = DEFAULT_ENCODE;
        	}
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setCharacterEncoding(encode);
            response.setContentType("application/json;charset=" + encode);

            byte[] data = returnContent.getBytes(encode);
            response.setContentLength(data.length);
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.getOutputStream().write(data, 0, data.length);
        } catch (Exception e) {
            log.error("print result error!", e);
        }
    }
	
	private static String filterHtml(String body) {
        if (body == null) {
            return "";
        }
        body = body.replaceAll("<", "&lt;").replaceAll(">", "&gt;")
                .replaceAll("\"", "&quot;").replaceAll("'", "&quot;");
        return body;
    }
}
