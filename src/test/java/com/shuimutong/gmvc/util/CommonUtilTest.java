package com.shuimutong.gmvc.util;

import org.junit.Assert;
import org.junit.Test;


public class CommonUtilTest {
	@Test
	public void fmtTest() {
		String url = "//a/b///c/d";
		String res = url.replaceAll("/+", "/");
		System.out.println(res);
	}
	
	@Test
	public void formatUri() {
		String url = "//a/b///c/d";
		String res = CommonUtil.formatUri(url);
		System.out.println(String.format("url:%s,res:%s", url, res));
		Assert.assertTrue(res.equals("/a/b/c/d"));
		
		url = "a/b////c/d///";
		res = CommonUtil.formatUri(url);
		System.out.println(String.format("url:%s,res:%s", url, res));
		Assert.assertTrue(res.equals("/a/b/c/d"));
	}
}
