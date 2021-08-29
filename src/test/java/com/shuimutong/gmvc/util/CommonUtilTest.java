package com.shuimutong.gmvc.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.shuimutong.gmvc.bean.EntityBean;
import com.shuimutong.guti.bean.TwoTuple;


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
	
	@Test
	public void compareTest() {
		List<TwoTuple<Integer, String>> list = new ArrayList();
		list.add(new TwoTuple<Integer, String>(5, "a"));
		list.add(new TwoTuple<Integer, String>(7, "a"));
		list.add(new TwoTuple<Integer, String>(2, "a"));
		System.out.println("排序前：" + JSONObject.toJSONString(list));
		list.sort(new Comparator<TwoTuple<Integer, String>>() {
			@Override
			public int compare(TwoTuple<Integer, String> o1, TwoTuple<Integer, String> o2) {
				return o1.getA() - o2.getA();
			}
		});
		System.out.println("排序后：" + JSONObject.toJSONString(list));
	}
}
