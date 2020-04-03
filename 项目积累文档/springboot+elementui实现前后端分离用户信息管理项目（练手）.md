# springboot+elementui实现前后端分离用户信息管理项目（练手）

## 开发环境以及相关工具

**前端：**elementui+vue

**后端：**spring boot+mybatisplus+lombok+eastcode代码生成器

**后端开发工具：**idea

**api测试工具：**postman

**前端开发工具：**vscode

## 建立数据库

MySQL数据库

customer_db

customer_table

![1581494382911](C:\Users\ASUS\AppData\Roaming\Typora\typora-user-images\1581494382911.png)

## 创建一个后端项目

pom.xml

```
  <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.2.4.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    
    
        <dependencies>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
<!--        mysql驱动-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
        </dependency>
<!--        lobok依赖-->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
            <exclusions>
                <exclusion>
                    <groupId>org.junit.vintage</groupId>
                    <artifactId>junit-vintage-engine</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
        <!-- https://mvnrepository.com/artifact/com.baomidou/mybatis-plus-boot-starter -->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>3.2.0</version>
        </dependency>
    </dependencies>
    
    
   配置文件
   
   server:
  port: 8088
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/customer_db
    username: root
    password: 6222688
mybatis-plus:
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl

```

## 编写API接口程序

使用easycode生成Java代码，也就是API接口程序

easycode 使用mybatisplus的模板

![1581496094410](C:\Users\ASUS\AppData\Roaming\Typora\typora-user-images\1581496094410.png)

## 使用postman测试

![1581497354925](C:\Users\ASUS\AppData\Roaming\Typora\typora-user-images\1581497354925.png)

![1581497564165](C:\Users\ASUS\AppData\Roaming\Typora\typora-user-images\1581497564165.png)

## 配置分页插件

在项目加入配置类，通过bean的方式注册分页组件

```
@Configuration
@EnableTransactionManagement
public class config {
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        //设置请求的页面大于最大页面的操作，ture返回到首页，false继续请求 默认false
        //paginationInterceptor.setOverflow(false);
        //设置最大单页限制数量，默认500条 -1不受限制
        //paginationInterceptor.setLimit(500);
        return paginationInterceptor;
    }
}
```

分页查询测试

![1581498271869](C:\Users\ASUS\AppData\Roaming\Typora\typora-user-images\1581498271869.png)

![1581498487740](C:\Users\ASUS\AppData\Roaming\Typora\typora-user-images\1581498487740.png)

## 创建一个前端项目

使用elementui +vue 手工建单创建项目（不使用vue+cli）

使用vscode工具编辑

[elementui快速成型网站官网]: https://element.eleme.cn/#/zh-CN

