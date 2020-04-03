

# 1需求分析

**角色**：普通访客，管理员（我）

- 访客：分页查看博客
- 访客：快速查询访问最多博客的六个分类
- 访客：查看所有分类
- 访客：查看某个分类下的所有博客
- 访客：查看标记最多的十个标签
- 访客：查看所有标签
- 访客：查看标签下的博客里列表
- 访客：根据年度时间查看列表
- 访客：推荐最新的博客
- 访客：关键字的全局搜索
- 访客：查看单个博客内容
- 访客：点赞
- 访客：评论
- 访客：微信扫码查看博客内容
- 访客：扫码关注公众号

我：

- 我：登录后台管理页面
- 我：发布新博客
- 我：分类博客
- 我：打标签
- 我：删除，修改博客
- 我：查询博客
- 我：增删改分类
- 我：根据分类名查询分类
- 我：增删改标签
- 我：根据标签名查询标签

![](F:\opensource\項目積纍文檔\个人博客实战\img\个人博客.png)

# 2.页面设计

## 2.1设计原型

**前端页面：**首页，博客详情页，分类页，标签页，归档页，自我介绍页面。

**后台管理页面：**新增页面，管理页面，登录页面。

前后端的页面设计是通过semantic ui来设计的这个前端框架对于设计页面还是很好用的。

**我们还要集成一些框架用于功能增加和友好展示：**

编辑器：markdown https://pandao.github.io/editor.md/

内容排版：typo.css https://github.com/sofish/typo.css

动画：animate.css  https://daneden.github.io/animate.css/

代码高亮：prism https://github.com/PrismJS/prism

波动侦测：waypoints 

平滑波动：jquery.scrollTo https://github.com/flesler/jquery.scrollTo

目录生成：Tocbot  https://tscanlin.github.io/tocbot/

<script src="https://cdnjs.cloudflare.com/ajax/libs/tocbot/4.4.2/tocbot.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tocbot/4.4.2/tocbot.css">

二维码生成：grcode.js http://davidshimjs.github.io/qrcodejs/

# 框架搭建（spring boot）

**引入的模块：**

- web
- Thymeleaf
- jpa
- mysql
- aspects
- devTools

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>

	<groupId>com.lrm</groupId>
	<artifactId>blog</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<packaging>jar</packaging>

	<name>blog</name>
	<description>Demo project for Spring Boot</description>

	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>1.5.7.RELEASE</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>

	<properties>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		<project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
		<java.version>1.8</java.version>
		<thymeleaf.version>3.0.2.RELEASE</thymeleaf.version>
		<thymeleaf-layout-dialect.version>2.1.1</thymeleaf-layout-dialect.version>
	</properties>

	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-aop</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-thymeleaf</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>


</project>

```

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<configuration>
    <!--包含Spring boot对logback日志的默认配置-->
    <include resource="org/springframework/boot/logging/logback/defaults.xml" />
    <property name="LOG_FILE" value="${LOG_FILE:-${LOG_PATH:-${LOG_TEMP:-${java.io.tmpdir:-/tmp}}}/spring.log}"/>
    <include resource="org/springframework/boot/logging/logback/console-appender.xml" />

    <!--重写了Spring Boot框架 org/springframework/boot/logging/logback/file-appender.xml 配置-->
    <appender name="TIME_FILE"
              class="ch.qos.logback.core.rolling.RollingFileAppender">
        <encoder>
            <pattern>${FILE_LOG_PATTERN}</pattern>
        </encoder>
        <file>${LOG_FILE}</file>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern>${LOG_FILE}.%d{yyyy-MM-dd}.%i</fileNamePattern>
            <!--保留历史日志一个月的时间-->
            <maxHistory>30</maxHistory>
            <!--
            Spring Boot默认情况下，日志文件10M时，会切分日志文件,这样设置日志文件会在100M时切分日志
            -->
            <timeBasedFileNamingAndTriggeringPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
                <maxFileSize>10MB</maxFileSize>
            </timeBasedFileNamingAndTriggeringPolicy>

        </rollingPolicy>
    </appender>

    <root level="INFO">
        <appender-ref ref="CONSOLE" />
        <appender-ref ref="TIME_FILE" />
    </root>

</configuration>
        <!--
            1、继承Spring boot logback设置（可以在appliaction.yml或者application.properties设置logging.*属性）
            2、重写了默认配置，设置日志文件大小在100MB时，按日期切分日志，切分后目录：

                blog.2017-08-01.0   80MB
                blog.2017-08-01.1   10MB
                blog.2017-08-02.0   56MB
                blog.2017-08-03.0   53MB
                ......
        -->
```



## 异常处理

404

500

error

自己编写三个状态所对应的页面

并对代码进行必要的修改与添加

自定义异常

```java
package com.caohao.caohaoblog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFindException extends RuntimeException {
    public NotFindException() {
        super();
    }

    public NotFindException(String message) {
        super(message);
    }

    public NotFindException(String message, Throwable cause) {
        super(message, cause);
    }
}

```

```java
//拦截全部的请求进行自定义的处理
package com.caohao.caohaoblog.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(HttpServletRequest httpServletRequest,Exception e) throws Exception {
        logger.error("Request URL : {}, Exception : {}",httpServletRequest.getRequestURI(),e);
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class)!=null){
            throw e;
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("url",httpServletRequest.getRequestURI());
        modelAndView.addObject("exception",e);
        modelAndView.setViewName("error/error");
        return modelAndView;
    }
}

```

## 日志处理

**记录的内容**

- 访问的url
- 访问者的ip
- 调用方法classMethod
- 参数 args
- 返回的内容

**记录日志类**

采用aop进行处理

```java
package com.caohao.caohaoblog.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class LogAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Pointcut("execution(* com.caohao.caohaoblog.web.*.*(..))")
    public void log(){}


    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURI();
        String ip = request.getRemoteAddr();
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "."+joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        RequestLog requestLog = new RequestLog(url,ip,classMethod,args);
        logger.info("Request : {}",requestLog);
    }

    @After("log()")
    public void doAfter(){

    }

    @AfterReturning(returning = "result",pointcut = "log()")
    public void doAfterReturn(Object result){
        logger.info("Result : {}",result);
    }

    private class RequestLog{
        private String url;
        private String ip;
        private String ClassMethod;
        private Object[] args;

        public RequestLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            ClassMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "RequestLog{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", ClassMethod='" + ClassMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }
}

```

## 页面处理

**静态页面导入project**

**thymeleaf布局**

- 定义fragment
- 使用fragment布局

**错误页面美化**

## 设计与规范

实体类 :

- 博客 blog
- 博客分类  type
- 博客标签  tag
- 博客评论  comment
- 用户  user

