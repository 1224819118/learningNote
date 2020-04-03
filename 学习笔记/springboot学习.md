[TOC]

------

# springboot学习

## 1.RESTful接口实现

### 1.1.RESTful接口与HTTP协议状态表述

**rest是面向资源的（名词）**

rest通过uri暴露资源时不要再uri中出现动词

象是这样 GET /api/dogs/{id}

**用HTTP方法体现对资源的操作**

​	·get方法获取资源
​	.post方法添加资源
​	.put方法修改资源
​	.delete方法删除资源

**HTTP状态码**

​	通过HTTP状态码体现动作的结果，不要自定义
​	200 OK
​	400 错误请求（像是用户输入错误）
​	500 系统内部发生错误（像是系统自己代码的错误）

**get方法和查询参数不应该改变数据**

​	改变数据交给post put delete

**使用复数名词**

​	/dogs而不是/dog

**复杂资源关系表达式**

​	/cars/711/drivers 返回使用car711号的所有司机
​	/cars/711/drivers/4 返回使用car711的所有司机中的四号司机

**高级用法：：HATEOAS**

​	超媒体所谓应用状态的引擎，restful api最好做到返回结果中提供链接，连向其他API方法，使得用户不查文档也知道下一步做什么

**为集合提供过滤 排序 选择 和分页等功能**

​	

**版本化你的API**

在请求前面加上API版本

**符合标准的代码**
	

```
package com.caohao.bootlearn.model;

public class Article {

    /**
     * id : 1
     * name : caohao
     */

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

​```

​```
package com.caohao.bootlearn.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class AjaxResponse {
    private boolean isok;//是否成功
    private int code;//状态码
    private String message;//信息
    private Object data;//数据


    public static AjaxResponse SUCCESS(Object data){
        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.setIsok(true);
        ajaxResponse.setCode(200);
        ajaxResponse.setMessage("Success");
        ajaxResponse.setData(data);
        return ajaxResponse;
    }
    public static AjaxResponse SUCCESS(){
        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.setIsok(true);
        ajaxResponse.setCode(200);
        ajaxResponse.setMessage("Success");
        return ajaxResponse;
    }

    public static AjaxResponse requestFalse(Object data){
        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.setIsok(false);
        ajaxResponse.setCode(400);
        ajaxResponse.setMessage("requestFalse");
        ajaxResponse.setData(data);
        return ajaxResponse;
    }
    public static AjaxResponse requestFalse(){
        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.setIsok(false);
        ajaxResponse.setCode(400);
        ajaxResponse.setMessage("requestFalse");
        return ajaxResponse;
    }
}

​```

​```
package com.caohao.bootlearn.controller;


import com.caohao.bootlearn.model.AjaxResponse;
import com.caohao.bootlearn.model.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/rest")
public class RestFulTestController {
    @RequestMapping(value = "/Articles",method = RequestMethod.POST,produces = "application/json")
    //@PostMapping("/Articles")
    public AjaxResponse saveObject(@RequestBody Article article){

        log.info("saveArticles"+article);
        //.....
        return AjaxResponse.SUCCESS(article);
    }
    @DeleteMapping("/Articles/{id}")
    public AjaxResponse deleteArticle(@PathVariable int id){
        log.info("delete Articles"+id);
        //.....
        return AjaxResponse.SUCCESS(id);
    }
    @PutMapping("/Articles/{id}")
    public AjaxResponse updateArticle(@RequestBody Article article,@PathVariable int id){
        log.info("changeArtices"+article);
        //......
        return AjaxResponse.SUCCESS(article);
    }
    @GetMapping("/Articles/{id}")
    public AjaxResponse getArticle(int id){
        log.info("getArticles"+id);
        //.....
        return AjaxResponse.SUCCESS(id);
    }

}

​```

```

### 1.2.spring常用注解

```
使用注解的优势：

     1.采用纯java代码，不在需要配置繁杂的xml文件

     2.在配置中也可享受面向对象带来的好处

     3.类型安全对重构可以提供良好的支持

     4.减少复杂配置文件的同时亦能享受到springIoC容器提供的功能

 

一、注解详解（配备了完善的释义）------(可采用ctrl+F 来进行搜索哦~~~~)

@SpringBootApplication：申明让spring boot自动给程序进行必要的配置，这个配置等同于：

@Configuration ，@EnableAutoConfiguration 和 @ComponentScan 三个配置。

@ResponseBody：表示该方法的返回结果直接写入HTTP response body中，一般在异步获取数据时使用，用于构建RESTful的api。在使用@RequestMapping后，返回值通常解析为跳转路径，加上@esponsebody后返回结果不会被解析为跳转路径，而是直接写入HTTP response body中。比如异步获取json数据，加上@Responsebody后，会直接返回json数据。该注解一般会配合@RequestMapping一起使用。

@Controller：用于定义控制器类，在spring项目中由控制器负责将用户发来的URL请求转发到对应的服务接口（service层），一般这个注解在类中，通常方法需要配合注解@RequestMapping。

@RestController：用于标注控制层组件(如struts中的action)，@ResponseBody和@Controller的合集。

@RequestMapping：提供路由信息，负责URL到Controller中的具体函数的映射。

@EnableAutoConfiguration：SpringBoot自动配置（auto-configuration）：尝试根据你添加的jar依赖自动配置你的Spring应用。例如，如果你的classpath下存在HSQLDB，并且你没有手动配置任何数据库连接beans，那么我们将自动配置一个内存型（in-memory）数据库”。你可以将@EnableAutoConfiguration或者@SpringBootApplication注解添加到一个@Configuration类上来选择自动配置。如果发现应用了你不想要的特定自动配置类，你可以使用@EnableAutoConfiguration注解的排除属性来禁用它们。

@ComponentScan：表示将该类自动发现扫描组件。个人理解相当于，如果扫描到有@Component、@Controller、@Service等这些注解的类，并注册为Bean，可以自动收集所有的Spring组件，包括@Configuration类。我们经常使用@ComponentScan注解搜索beans，并结合@Autowired注解导入。可以自动收集所有的Spring组件，包括@Configuration类。我们经常使用@ComponentScan注解搜索beans，并结合@Autowired注解导入。如果没有配置的话，Spring Boot会扫描启动类所在包下以及子包下的使用了@Service,@Repository等注解的类。

@Configuration：相当于传统的xml配置文件，如果有些第三方库需要用到xml文件，建议仍然通过@Configuration类作为项目的配置主类——可以使用@ImportResource注解加载xml配置文件。

@Import：用来导入其他配置类。

@ImportResource：用来加载xml配置文件。

@Autowired：自动导入依赖的bean

@Service：一般用于修饰service层的组件

@Repository：使用@Repository注解可以确保DAO或者repositories提供异常转译，这个注解修饰的DAO或者repositories类会被ComponetScan发现并配置，同时也不需要为它们提供XML配置项。

@Bean：用@Bean标注方法等价于XML中配置的bean。

@Value：注入Spring boot application.properties配置的属性的值。示例代码：

@Inject：等价于默认的@Autowired，只是没有required属性；

@Component：泛指组件，当组件不好归类的时候，我们可以使用这个注解进行标注。

@Bean:相当于XML中的,放在方法的上面，而不是类，意思是产生一个bean,并交给spring管理。

@AutoWired：自动导入依赖的bean。byType方式。把配置好的Bean拿来用，完成属性、方法的组装，它可以对类成员变量、方法及构造函数进行标注，完成自动装配的工作。当加上（required=false）时，就算找不到bean也不报错。

@Qualifier：当有多个同一类型的Bean时，可以用@Qualifier(“name”)来指定。与@Autowired配合使用。@Qualifier限定描述符除了能根据名字进行注入，但能进行更细粒度的控制如何选择候选者，具体使用方式如下：

@Resource(name=”name”,type=”type”)：没有括号内内容的话，默认byName。与@Autowired干类似的事。

二、注解列表如下

@SpringBootApplication：包含了@ComponentScan、@Configuration和@EnableAutoConfiguration注解。其中

@ComponentScan：让spring Boot扫描到Configuration类并把它加入到程序上下文。

@Configuration ：等同于spring的XML配置文件；使用Java代码可以检查类型安全。

@EnableAutoConfiguration ：自动配置。

@ComponentScan ：组件扫描，可自动发现和装配一些Bean。

@Component可配合CommandLineRunner使用，在程序启动后执行一些基础任务。

@RestController：注解是@Controller和@ResponseBody的合集,表示这是个控制器bean,并且是将函数的返回值直 接填入HTTP响应体中,是REST风格的控制器。

@Autowired：自动导入。

@PathVariable：获取参数。

@JsonBackReference：解决嵌套外链问题。

@RepositoryRestResourcepublic：配合spring-boot-starter-data-rest使用。

三、JPA注解

@Entity：@Table(name=”“)：表明这是一个实体类。一般用于jpa这两个注解一般一块使用，但是如果表名和实体类名相同的话，@Table可以省略

@MappedSuperClass:用在确定是父类的entity上。父类的属性子类可以继承。

@NoRepositoryBean:一般用作父类的repository，有这个注解，spring不会去实例化该repository。

@Column：如果字段名与列名相同，则可以省略。

@Id：表示该属性为主键。

@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = “repair_seq”)：表示主键生成策略是sequence（可以为Auto、IDENTITY、native等，Auto表示可在多个数据库间切换），指定sequence的名字是repair_seq。

@SequenceGeneretor(name = “repair_seq”, sequenceName = “seq_repair”, allocationSize = 1)：name为sequence的名称，以便使用，sequenceName为数据库的sequence名称，两个名称可以一致。

@Transient：表示该属性并非一个到数据库表的字段的映射,ORM框架将忽略该属性。如果一个属性并非数据库表的字段映射,就务必将其标示为@Transient,否则,ORM框架默认其注解为@Basic。@Basic(fetch=FetchType.LAZY)：标记可以指定实体属性的加载方式

@JsonIgnore：作用是json序列化时将Java bean中的一些属性忽略掉,序列化和反序列化都受影响。

@JoinColumn（name=”loginId”）:一对一：本表中指向另一个表的外键。一对多：另一个表指向本表的外键。

@OneToOne、@OneToMany、@ManyToOne：对应hibernate配置文件中的一对一，一对多，多对一。

四、springMVC相关注解

@RequestMapping：@RequestMapping(“/path”)表示该控制器处理所有“/path”的UR L请求。RequestMapping是一个用来处理请求地址映射的注解，可用于类或方法上。
用于类上，表示类中的所有响应请求的方法都是以该地址作为父路径。该注解有六个属性：
params:指定request中必须包含某些参数值是，才让该方法处理。
headers:指定request中必须包含某些指定的header值，才能让该方法处理请求。
value:指定请求的实际地址，指定的地址可以是URI Template 模式
method:指定请求的method类型， GET、POST、PUT、DELETE等
consumes:指定处理请求的提交内容类型（Content-Type），如application/json,text/html;
produces:指定返回的内容类型，仅当request请求头中的(Accept)类型中包含该指定类型才返回

@RequestParam：用在方法的参数前面。
@RequestParam
String a =request.getParameter(“a”)。

@PathVariable:路径变量。如

参数与大括号里的名字一样要相同。

五、全局异常处理

@ControllerAdvice：包含@Component。可以被扫描到。统一处理异常。

@ExceptionHandler（Exception.class）：用在方法上面表示遇到这个异常就执行以下方法。

六、项目中具体配置解析和使用环境

@MappedSuperclass：

1.@MappedSuperclass 注解使用在父类上面，是用来标识父类的

2.@MappedSuperclass 标识的类表示其不能映射到数据库表，因为其不是一个完整的实体类，但是它所拥有的属性能够映射在其子类对用的数据库表中

3.@MappedSuperclass 标识的类不能再有@Entity或@Table注解

@Column：

1.当实体的属性与其映射的数据库表的列不同名时需要使用@Column标注说明，该属性通常置于实体的属性声明语句之前，还可与 @Id 标注一起使用。

2.@Column 标注的常用属性是name，用于设置映射数据库表的列名。此外，该标注还包含其它多个属性，如：unique、nullable、length、precision等。具体如下：

  1 name属性：name属性定义了被标注字段在数据库表中所对应字段的名称

  2 unique属性：unique属性表示该字段是否为唯一标识，默认为false，如果表中有一个字段需要唯一标识，则既可以使用该标记，也可以使用@Table注解中的@UniqueConstraint

  3 nullable属性：nullable属性表示该字段是否可以为null值，默认为true

  4 insertable属性：insertable属性表示在使用”INSERT”语句插入数据时，是否需要插入该字段的值

  5 updateable属性：updateable属性表示在使用”UPDATE”语句插入数据时，是否需要更新该字段的值

  6 insertable和updateable属性：一般多用于只读的属性，例如主键和外键等，这些字段通常是自动生成的

  7 columnDefinition属性：columnDefinition属性表示创建表时，该字段创建的SQL语句，一般用于通过Entity生成表定义时使用，如果数据库中表已经建好，该属性没有必要使用

  8 table属性：table属性定义了包含当前字段的表名

  9 length属性：length属性表示字段的长度，当字段的类型为varchar时，该属性才有效，默认为255个字符

 10 precision属性和scale属性：precision属性和scale属性一起表示精度，当字段类型为double时，precision表示数值的总长度，scale表示小数点所占的位数

 

    具体如下：
   1.double类型将在数据库中映射为double类型，precision和scale属性无效
   2.double类型若在columnDefinition属性中指定数字类型为decimal并指定精度，则最终以columnDefinition为准
   3.BigDecimal类型在数据库中映射为decimal类型，precision和scale属性有效
   4.precision和scale属性只在BigDecimal类型中有效

3.@Column 标注的columnDefinition属性: 表示该字段在数据库中的实际类型.通常 ORM 框架可以根据属性类型自动判断数据库中字段的类型,但是对于Date类型仍无法确定数据库中字段类型究竟是DATE,TIME还是TIMESTAMP.此外,String的默认映射类型为VARCHAR,如果要将 String 类型映射到特定数据库的 BLOB 或TEXT字段类型.

4.@Column标注也可置于属性的getter方法之前

@Getter和@Setter（Lombok）

@Setter：注解在属性上；为属性提供 setting 方法 @Getter：注解在属性上；为属性提供 getting 方法

  1 @Data：注解在类上；提供类所有属性的 getting 和 setting 方法，此外还提供了equals、canEqual、hashCode、toString 方法
  2 
  3 @Setter：注解在属性上；为属性提供 setting 方法
  4 
  5 @Getter：注解在属性上；为属性提供 getting 方法
  6 
  7 @Log4j2 ：注解在类上；为类提供一个 属性名为log 的 log4j 日志对象，和@Log4j注解类似
  8 
  9 @NoArgsConstructor：注解在类上；为类提供一个无参的构造方法
 10 
 11 @AllArgsConstructor：注解在类上；为类提供一个全参的构造方法
 12 
 13 @EqualsAndHashCode:默认情况下，会使用所有非瞬态(non-transient)和非静态(non-static)字段来生成equals和hascode方法，也可以指定具体使用哪些属性。
 14 
 15 @toString:生成toString方法，默认情况下，会输出类名、所有属性，属性会按照顺序输出，以逗号分割。
 16 
 17 @NoArgsConstructor, @RequiredArgsConstructor and @AllArgsConstructor
 18 无参构造器、部分参数构造器、全参构造器，当我们需要重载多个构造器的时候，只能自己手写了
 19 
 20 @NonNull：注解在属性上，如果注解了，就必须不能为Null
 21 
 22 @val:注解在属性上，如果注解了，就是设置为final类型，可查看源码的注释知道

 

当你在执行各种持久化方法的时候，实体的状态会随之改变，状态的改变会引发不同的生命周期事件。这些事件可以使用不同的注释符来指示发生时的回调函数。

@javax.persistence.PostLoad：加载后。

@javax.persistence.PrePersist：持久化前。

@javax.persistence.PostPersist：持久化后。

@javax.persistence.PreUpdate：更新前。

@javax.persistence.PostUpdate：更新后。

@javax.persistence.PreRemove：删除前。

@javax.persistence.PostRemove：删除后。

1）数据库查询

@PostLoad事件在下列情况下触发：

执行EntityManager.find()或getreference()方法载入一个实体后。

执行JPQL查询后。

EntityManager.refresh()方法被调用后。

2）数据库插入

@PrePersist和@PostPersist事件在实体对象插入到数据库的过程中发生：

@PrePersist事件在调用persist()方法后立刻发生，此时的数据还没有真正插入进数据库。

@PostPersist事件在数据已经插入进数据库后发生。

3）数据库更新

@PreUpdate和@PostUpdate事件的触发由更新实体引起：

@PreUpdate事件在实体的状态同步到数据库之前触发，此时的数据还没有真正更新到数据库。

@PostUpdate事件在实体的状态同步到数据库之后触发，同步在事务提交时发生。

4）数据库删除

@PreRemove和@PostRemove事件的触发由删除实体引起：

@PreRemove事件在实体从数据库删除之前触发，即在调用remove()方法删除时发生，此时的数据还没有真正从数据库中删除。

@PostRemove事件在实体从数据库中删除后触发。
```



### 1.3.常用注解开发RESTful接口

```
package com.caohao.bootlearn.controller;


import com.caohao.bootlearn.model.AjaxResponse;
import com.caohao.bootlearn.model.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/rest")
public class RestFulTestController {
    @RequestMapping(value = "/Articles",method = RequestMethod.POST,produces = "application/json")
    //@PostMapping("/Articles")
    public AjaxResponse saveObject(@RequestBody Article article){

        log.info("saveArticles"+article);
        //.....
        return AjaxResponse.SUCCESS(article);
    }
    @DeleteMapping("/Articles/{id}")
    public AjaxResponse deleteArticle(@PathVariable int id){
        log.info("delete Articles"+id);
        //.....
        return AjaxResponse.SUCCESS(id);
    }
    @PutMapping("/Articles/{id}")
    public AjaxResponse updateArticle(@RequestBody Article article,@PathVariable int id){
        log.info("changeArtices"+article);
        //......
        return AjaxResponse.SUCCESS(article);
    }
    //@GetMapping(value = "/Articles/{id}",produces = "application/json")
    @RequestMapping(value = "/Articles/{id}",method = RequestMethod.GET,produces = "application/json")
    public AjaxResponse getArticle(@PathVariable int id){
        log.info("getArticles"+id);
        //.....
        return AjaxResponse.SUCCESS(id);
    }

}

```



### 1.4.JSON数据处理与PostMan测试



### 1.5.使用Mockito编码完成接口测试



### 1.6.使用Swagger2构建API文档

```

swagger2可以方便的帮我们生成API文档并且在我们代码发生改变时自动改变文档信息
​```
<!-- swaggerui相关依赖 -->

<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger2</artifactId>
    <version>2.7.0</version>
</dependency>

<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger-ui</artifactId>
    <version>2.7.0</version>
</dependency>
​```





配置文件类
​```
package com.caohao.bootlearn.config;

import io.swagger.annotations.SwaggerDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2 {
    @Bean
    public Docket cerateRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.caohao.bootlearn"))//指定扫描controller的包
                .paths(PathSelectors.regex("/rest/.*"))//指定controller哪些请求的方法
                .build();
    }
    public ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("第一次使用swagger2创建API文档")
                .description("restful风格")
                .termsOfServiceUrl("http://www.caohao.com")
                .version("1.0")
                .build();
    }
}

​```
http://localhost:8080/swagger-ui.html生成的文档访问地址

```

[常用注解]: https://www.cnblogs.com/chancy/p/9759553.html

![1581481287842](C:\Users\ASUS\AppData\Roaming\Typora\typora-user-images\1581481287842.png)

### 1.7.接口文档多种格式导出离线阅读

## 2.springboot配置和原理学习

### 2.1.springboot学习

#### 2.1.1 自动配置原理





### 2.2.spring配置文件（yml,properties）

主配置中可以配置很多东西

数据源，端口号甚至是视图解析器和静态资源访问

```
server:
  port: 8889
spring:
  datasource:
      url: jdbc:mysql:///springboot
      driver-class-name: com.mysql.jdbc.Driver
      username: root
      password: 6222688
  jackson:
    date-format: "yyyy-MM-dd HH:mm:ss"
    time-zone: GMT+8
  mvc:
    view:
      prefix: WEB-INFO
      suffix: .jsp
      #固定的
    static-path-pattern: /static/**
#mybatis:
#  mapper-locations: com.caohao.bootlearn.Mapper
logging:
  level:
    com.caohao.bootlearn: debug
```



### 2.3.获取配置文件信息的方式

@value放在field属性上也就是放在成员变量上，可以写入自己写的数据也可以通过${name.propertie}的写法导入配置文件里的信息

```
@ConfigurationProperties(prefix = "user")
可以直接将user对象传递给当前被标注类的成员变量属性
这个可以搭配@Validated使用来进行检测
@value不行
```

### 2.4.导入外部配置文件的方法

```
@PropertySource()
@ImportResource()
两种方法第一种导入外部的资源文件
第二中可以导入以前使用的那些配置文件像是一些xml文件
```

### 2.5.切换环境profile

![1581481837862](C:\Users\ASUS\AppData\Roaming\Typora\typora-user-images\1581481837862.png)

像是图中这样不同的配置环境可以通过application-环境简称.yml(properties)来区别

切换配置有三种方式：

​	1.可以在主配置文件中配置环境spring.profiles.active=profileActive

​	2.可以通过改变vm中的属性来改变环境-Dspring.profiles.active=dev

![1581482104858](C:\Users\ASUS\AppData\Roaming\Typora\typora-user-images\1581482104858.png)

​	3.可以通过控制台命令改变配置环境

输入命令：java -jar  jar名  --spring.profiles.active=test ,回车即可启动项目

### 2.6.简单加密

**jasypt实现简单加密**

		<dependency>
			<groupId>com.github.ulisesbocchio</groupId>
			<artifactId>jasypt-spring-boot-starter</artifactId>
			<version>1.16</version>
		</dependency>
配置文件项加解密密码，此处作为测试完，实际情况应该注释，而放在代码中（放在代码中使加密密钥和密文分开）

jasypt.encryptor.password: demo

```
测试代码

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = JasyptApplication.class)
public class JasyptApplicationTests {

    @Autowired
    private StringEncryptor stringEncryptor;

	@Test
	public void contextLoads() {
        //加密方法
        System.out.println(stringEncryptor.encrypt("123456"));
        System.out.println(stringEncryptor.encrypt("123456"));
        //解密方法
        System.out.println(stringEncryptor.decrypt("uaNBj4ZmzCD83uedRYUXqQ=="));
        System.out.println(stringEncryptor.decrypt("oKBQENfbbQiMyPvECAgPGA=="));
	}

}


```

**MD5加密**

```
public class MD5 {
//    static final String[] digla= {"1","2","3","4","5","6","7","8","9"
//            ,"a","b","c","d","e","f"};
    public static void main(String[] args) throws NoSuchAlgorithmException,
            UnsupportedEncodingException {
        String password="123456";//---->32为16进制字符串
        //封装了md5算法的核心类
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        //经过MD5会变成长度16的字节数组---->转换为32位的字符串(16进制)
        byte[] bytes = md5.digest(password.getBytes("UTF-8"));
        for (byte aByte : bytes) {
            System.out.println(aByte);
        }

    }
}
```

加密成数字

### 2.7 配置webmvc

​	配置mvc我们有两种情况，第一种是保留spring boot的自动配置并在其上加入一些新的配置像是intercepters这种，第二中则是我们想要完全放弃掉spring boot的自动配置信息我们自己配置全部的信息像是使用spring时那样

第一种：

写一个配置类实现WebMvcConfigurer接口

第二钟

写一个配置类继承WebMvcConfigurationSupport类

继承这个类就像是spring boot1.x时候引入@EnableWebMvc注解一样仔细看源码你会发现

```java

@Retention(RetentionPolicy.RUNTIME)

@Target({ElementType.TYPE})

@Documented

@Import({DelegatingWebMvcConfiguration.class})

public @interface EnableWebMvc {

}

```

它实际上是通过@import注解引入了DelegatingWebMvcConfiguration.class这个类打开这个类我们可以看到

```java

@Configuration

public class DelegatingWebMvcConfiguration extends WebMvcConfigurationSupport {

```

他就是一个继承了WebMvcConfigurationSupport的实现类



## 3.常用的web开发数据库框架

### 3.1.1spring JDBC学习



### 3.1.2spring JDBC多数据源实现



### 3.1.3spring JDBC JTA实现分布式事务管理



### 3.2.1orm主流框架

我平常使用的orm框架，基本上就两种一是mybatis二是spring data jpa

### 3.2.2bean转换Dozer学习

dozer是用来两个对象之间属性转换的工具，有了这个工具之后，我们将一个对象的所有属性值转给另一个对象时，就不需要再去写重复的set和get方法了。

大白话:dozer是一个能把实体和实体之间进行转换的工具.只要建立好映射关系.就像是ORM的数据库和实体映射一样.

```
依赖
        <dependency>
            <groupId>net.sf.dozer</groupId>
            <artifactId>dozer</artifactId>
            <version>5.5.1</version>
        </dependency>
```

```

配置类导入要使用的bean

@Configuration

public class DozerBeanMapperConfigure {

    @Bean

    public DozerBeanMapper mapper() {

        DozerBeanMapper mapper = new DozerBeanMapper();

        return mapper;

    }

}

```

如果要映射的两个对象有完全相同的属性名，那么一切都很简单。
只需要直接使用Dozer的API即可：

```
@Autowired
Mapper mapper ；

DestinationObject destObject =  

    mapper.map(sourceObject, DestinationObject.class);

```

但是现实的环境中通常属性名是不一致的那么就要配置我们的dozer映射

这里只讲通过注解的配置@Mapping源码如下

```

@Retention(RetentionPolicy.RUNTIME)

@Target({ElementType.FIELD, ElementType.METHOD})

public @interface Mapping {

  String value() default "";

}

```

```

public class SourceBean {

    private Long id;

    private String name;
    @Mapping("binaryData")
    private String data;
    @Mapping("pk")
    public Long getId() {
        return this.id;
    }
    //其余getter/setter方法略
}

public class TargetBean {
    private String pk;
    private String name;
    private String binaryData;
    //getter/setter方法略

}

@Test

public void testAnnotationMapping() {
    SourceBean src = new SourceBean();
    src.setId(7L);
    src.setName("邦德");
    src.setData("00000111");

    TargetBean desc = mapper.map(src, TargetBean.class);

    Assert.assertNotNull(desc);
}

```



### 3.2.3Spring Data JPA数据框架学习

<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>



### 3.2.3Spring data JPA的多数据源实现

### 3.2.4JPA+atomikos实现分布式事务

### 3.3.1Mybatis深入理解

### 3.3.2Spring mybatis的多数据源实现

在配置文件中写入两个配置源

```
## test1 database
spring.datasource.test1.url=jdbc:mysql://localhost:3307/multipledatasource1?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false
spring.datasource.test1.username=root
spring.datasource.test1.password=root
spring.datasource.test1.driver-class-name=com.mysql.cj.jdbc.Driver
## test2 database
spring.datasource.test2.url=jdbc:mysql://localhost:3307/multipledatasource2?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false
spring.datasource.test2.username=root
spring.datasource.test2.password=root
spring.datasource.test2.driver-class-name=com.mysql.cj.jdbc.Driver

```

同时实现两个数据源对象

其中一个一定要加入@Primary以此来区分默认数据源和次数据源

### 3.3.3mybatis+atomikos实现分布式事务

spring的事务管理在平时一个数据源环境下使用基本上是没有问题的但是当我们同时像两个数据库中加入数据一个成功一个失败时spring的事务就不会成功了

在多个数据源的情况下我们可以实现分布式事务来管理事务

 <!--分布式事务支持-->
      <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-jta-atomikos</artifactId>
      </dependency>

### 3.3.4mybaitsplus插件学习

通过SQL语句进行，就必须写大量的xml文件，很是麻烦。mybatis-plus就很好的解决了这个问题。

Mybatis-Plus（简称MP）是一个 Mybatis 的增强工具，在 Mybatis 的基础上只做增强不做改变，为简化开发、提高效率而生。这是官方给的定义，关于mybatis-plus的更多介绍及特性，可以参考[mybatis-plus官网](http://mp.baomidou.com/#/)。那么它是怎么增强的呢？其实就是它已经封装好了一些crud方法，我们不需要再写xml了，直接调用这些方法就行，就类似于JPA。
mybatis-plus在mybatis的基础上只做增强不做改变，因此其与spring的整合亦非常简单。只需把mybatis的依赖换成mybatis-plus的依赖，再把sqlSessionFactory换成mybatis-plus的即可。接下来看具体操作：

 <!--mybatis-plus自动的维护了mybatis以及mybatis-spring的依赖，
     在springboot中这三者不能同时的出现，避免版本的冲突，表示：跳进过这个坑-->

<dependency>
   <groupId>com.baomidou</groupId>
   <artifactId>mybatis-plus-boot-starter</artifactId>
   <version>2.3</version>
</dependency>

官方已经提供了基于springboot的配置，将其拷贝过来放在application.yml中即可使用，此处只是将官方部分的配置删减过一些。其中column-underline: true特别好用，会自动将下划线格式的表字段，转换为以驼峰格式命名的属性。

```

mybatis-plus:
  global-config:
    db-config:
      id-type: auto
      field-strategy: not_empty
      #驼峰下划线转换
      column-underline: true
      #逻辑删除配置
      logic-delete-value: 0
      logic-not-delete-value: 1
      db-type: mysql
    refresh: false
  configuration:
    map-underscore-to-camel-case: true
    cache-enabled: false
```

1.创建实体类，创建实体类与JPA特别相似，其中@TableName注解是为了指定此实体类对应数据库的哪一张表；@TableId指定的是主键，type属性指定的是该主键自增的方式,AUTO代表自增，UUID代表使用UUID增加主键；还有一个注解@TableFiled,此注解主要用在非主键实体属性上，下面是官方给出的此注解所有属性。



![1581493155977](C:\Users\ASUS\AppData\Roaming\Typora\typora-user-images\1581493155977.png)

2.创建mapper，与JPA有相像之处。使用@Mapper为了把mapper这个DAO交給Spring管理，且可以不再写mapper映射文件；继承BaseMapper可以方便使用mybatis-plus方法，泛型需要写对用的实体类。此处直接使用slelect *  from，mybatis-plus会根据实体类自动驼峰转下划线映射到表的字段中。

**查询**

1.查询单条数据，非主键查询，只要在一个实体内写入属性，然后再用mybatis-plus自带的selectOne()方法即可查询（注意:查询的限制条件必须此条数据是唯一的，不然查询后会报错）。个人觉得此种查询使用不便，不如直接使用nativeSql进行查询来的利索。

2.查询数量，含有拼接方式查询。使用mybatis-plus条件查询需要使用到EntityWrapper构造器，它能添加查询的条件，如:.eq("job_id",13)表示查询job_id=13的记录，isNull("deleted_at")表示deleted_at必须为null(注意：此处查询的条件是使用的是数据库表中的字段名称)。
EntityWrapper<PatrolTaskEntity> wrapper = new EntityWrapper<>().eq("job_id",13).isNull("deleted_at");

List<PatrolTaskEntity> tasks = patrolTaskMapper.selectCount(wrapper);

3.查询列表，与上面基本相同，只不过此处使用的是mybatis-plus的selectList()方法

**分页**

1.添加配置文件，此处配置文件表示开启mybatis-plus分页功能

```
@EnableTransactionManagement
@Configuration
public class MybatisPlusConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}

```

2.写查询语句，此出的Pagination必须给出，不然无法完成分页功能。

```

@Mapper
public interface BannerLogMapper extends BaseMapper<BannerLogEntity> {
    @Select("select * from t_pub_banner_logs")
    List<BannerLogEntity> queryLogByPage(Pagination page);
}
```

3.进行分页查询代码块，只需要将要查询的第几页也页面大小写入到Page对象中。然后进行查询，查询后分页属性(当前页，总页数、总条数)， 是经过插件自动回写到传入page对象中。

```

Page<BannerLogEntity> page = new Page<>(pageNo,pageSize);
List<BannerLogEntity> bannerLogs = bannerLogMapper.queryLogByPage(page);
page.getTotal();
page.getCurrent();
page.getPages();
```



[官方学习文档]: https://mp.baomidou.com/guide/



### 3.4.3spring data rest 学习



## 4.静态资源与模板引擎的学习

### 4.1 webjars与静态资源学习

以jar包的方式引入静态资源，https://www.webjars.org/

<dependency>
    <groupId>org.webjars</groupId>
    <artifactId>jquery</artifactId>
    <version>3.4.1</version>
</dependency>

上面就是jQuery的webjars

所有的/webjars/**,都去classpath:/META-INF/resources/webjars/找资源

那么我们想要使用我们引入的jQuery时我们就访问/webjars/jquery/3.4.1/jquery.js

### 4.2.1模板引擎与前端开发框架

### 4.2.2spring整合jsp

### 4.2.3freemarker学习

```

```

### 4.2.4thymeleaf学习

```
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>
```

使用thyme leaf我们只需要引入依赖并且将静态页面放入类路径下为我们准备好的templates文件夹下即可，应为具体的配置都由spring为我们配置好了。

但是spring自带的时2.*的版本我们想要改成3.*的版本时同时要改变layout的版本为2.*（原本为1.*）

```xml

 <!-- 修改参数 -->

  <properties>

   <!-- 修改JDK的编译版本为1.8 -->

   <java.version>1.8</java.version>

   <!-- 修改thymeleaf版本 -->

   <thymeleaf.version>3.0.2.RELEASE</thymeleaf.version>

   <thymeleaf-layout-dialect.version>2.0.4</thymeleaf-layout-dialect.version>

  </properties>

```

但是spring boot2以上的版本已经整合了thyme leaf3了

就不需要我们再改了

#### thyme leaf语法

1.导入thyme leaf名称空间

```html

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml"

      xmlns:th="http://www.thymeleaf.org">

```

2.使用thymelleaf语法



### 4.2.5vue学习

## 5.生命周期内的拦截过滤与监听



## 6.嵌入式容器的配置与应用

## 7.统一的全局异常管理

## 8.日志框架与全局日志管理

### 8.1日志框架



![1581835315863](C:\Users\ASUS\AppData\Roaming\Typora\typora-user-images\1581835315863.png)

日志接口选择slf4j

日志实现选择log back

### 8.2 slf4j使用

```java
package com.caohao.bootlearn.config;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class llf4j {
    public Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test(){
        //日志级别由下到上依次如下 系统默认打印的日志级别为info以及以上，我们可以在配置文件中定义我们重新定义为root级别
        logger.trace("trace...");
        logger.debug("debug...");
//--------------------------------------
        logger.info("info....");
        logger.warn("warn...");
        logger.error("error....");
    }
    public static void main(String[] args) {

        llf4j llf4j = new llf4j();
        llf4j.test();

    }
}

```

```yaml
logging:
  level:
    com.caohao.bootlearn: debug
  pattern:
    level: root
  file:
    path: C:\Users\ASUS\Desktop\test
    name: log.log
```

具体的在配置文件中修改日志格式可以上网搜索



## 9.异步任务与定时任务

## 10.redis缓存与session共享

## 11.整合分布式文件系统fastdfs

## 12.服务器推送技术

## 13.消息队列的整合与使用

## 14.邮件发送的整合与使用

## 15.响应式框架webflux

## 16.应用程序监控管理

## 

