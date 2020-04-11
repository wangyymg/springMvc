# gmvc

#### 介绍
mvc框架


#### 主要功能
基于注解的支持IOC的MVC框架。
##### 注解
- XComponent
- XController
- XService
- XRespository
- XAutowired
- XRequestMapping
- XServerInit
##### web.xml配置示例

```
<!DOCTYPE web-app PUBLIC
 "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"
 "http://java.sun.com/dtd/web-app_2_3.dtd" >

<web-app>
  <!--配置启动servlet-->
  <display-name>Archetype Created Web Application</display-name>
  <servlet>
  	<servlet-name>gmvc</servlet-name>
  	<servlet-class>com.shuimutong.gmvc.handler.XDispatcherServlet</servlet-class>
  	<init-param>
            <!--扫描路径-->
            <param-name>basePackage</param-name>
            <param-value>com.shuimutong.gmq</param-value>
        </init-param>
        <!--启动优先级-->
  	<load-on-startup>2</load-on-startup>
  </servlet>

  <servlet-mapping>
  	<servlet-name>gmvc</servlet-name>
  	<url-pattern>/</url-pattern>
  </servlet-mapping>
</web-app>
```
##### 仅使用IOC功能

```
map.put(SystemConst.BASE_PACKAGE, "com.package.**");
InstanceManager.initAnnotationedResourcesAndDoInit(Map<String, String> map);
```



#### 参与贡献

1. Fork 本仓库
2. 新建 Feat_xxx 分支
3. 提交代码
4. 新建 Pull Request


#### 码云特技

1. 使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2. 码云官方博客 [blog.gitee.com](https://blog.gitee.com)
3. 你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解码云上的优秀开源项目
4. [GVP](https://gitee.com/gvp) 全称是码云最有价值开源项目，是码云综合评定出的优秀开源项目
5. 码云官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6. 码云封面人物是一档用来展示码云会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)