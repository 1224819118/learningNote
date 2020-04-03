## 简介

 

Apache Shiro 是 Java 的一个安全框架。目前，使用 Apache Shiro 的人越来越多，因为它相当简单，对比 Spring Security，可能没有 Spring Security 做的功能强大，但是在实际工作时可能并不需要那么复杂的东西，所以使用小而简单的 Shiro 就足够了。对于它俩到底哪个好，这个不必纠结，能更简单的解决项目问题就好了。

Shiro 可以非常容易的开发出足够好的应用，其不仅可以用在 JavaSE 环境，也可以用在 JavaEE 环境。Shiro 可以帮助我们完成：认证、授权、加密、会话管理、与 Web 集成、缓存等。这不就是我们想要的嘛，而且 Shiro 的 API 也是非常简单；其基本功能点如下图所示：

![](F:\opensource\項目積纍文檔\java进阶学习\shiro学习\img\1[1].png)

**Authentication**：身份认证 / 登录，验证用户是不是拥有相应的身份；

**Authorization**：授权，即权限验证，验证某个已认证的用户是否拥有某个权限；即判断用户是否能做事情，常见的如：验证某个用户是否拥有某个角色。或者细粒度的验证某个用户对某个资源是否具有某个权限；  

**Session Manager**：会话管理，即用户登录后就是一次会话，在没有退出之前，它的所有信息都在会话中；会话可以是普通 JavaSE 环境的，也可以是如 Web 环境的； 

**Cryptography**：加密，保护数据的安全性，如密码加密存储到数据库，而不是明文存储； 

**Web Support**：Web 支持，可以非常容易的集成到 Web 环境；  

**Caching**：缓存，比如用户登录后，其用户信息、拥有的角色 / 权限不必每次去查，这样可以提高效率；

**Concurrency**：shiro 支持多线程应用的并发验证，即如在一个线程中开启另一个线程，能把权限自动传播过去； 

**Testing**：提供测试支持； 

**Run As**：允许一个用户假装为另一个用户（如果他们允许）的身份进行访问；  

**Remember Me**：记住我，这个是非常常见的功能，即一次登录后，下次再来的话不用登录了。

**记住一点，Shiro 不会去维护用户、维护权限；这些需要我们自己去设计 / 提供；然后通过相应的接口注入给 Shiro 即可。**

## 1.shiro.ini文件

### 1.shiro.ini文件的说明

1.ini初始化文件Windows系统的文件扩展名

2.shiro使用时可以连接数据库，也可以不廉洁数据库，不连接数据库的话可以在shiro.ini中配置静态数据

### 2.shiro.ini文件的组成部分

#### 1.main定义全局变量

​	内置security manager对象

操作内置对象时，在main里写东西

#### 2.user定义用户名和密码

[user]

#定义用户名为caohao密码为ch

caohao=ch

#定义用户名test密码为test1同时具有role1和role2两个角色

test=test1,role1,role2

#### 3.roles定义角色

[roles]

role1=权限名1，权限名2

role2=权限名3，权限名4

#举例

[roles]

role1=user:query,user:add,user:delete

#### 4.[urls]定义哪些内置urls生效在web应用时使用

[urls]

#url地址=内置filter 或者自定义filter

#访问时出现/login必须去认证支持authc对应的Filter

/login=authc

#任意的url都不需要进行认证

/**=anon

#所有的内容都必须保证用户已经登陆

/**=user

#url abc 访问时必须保证用户具有role1和role2角色

/abc=roles["role1,role2"]



Authc   代表必须认证之后才能访问

anon    任意的url都不需要认证

user     所有的内容都必须保证用户已经登陆

logout  注销

以上四个都是已经内部定制的过滤器

### 3.shiro.ini实现认证

#### 1.身份验证

在应用中什么能证明他就是他本人，一般提供身份id，一些标识信息来表明是他本人，如提供身份证，用户名/密码来证明。

在shiro中，用户需要提供principals（身份）和credentials（证明）给shiro，从而应用可以验证身份

##### principals

身份即主体的标识属性，可以是任何东西，如用户名，邮箱等。唯一即可

一个主体可以有多个principals但是只能有一个primary principals 一般是用户名/密码/手机号。

##### credentials

证明/凭证，即只有主体才知道的安全值，如密码/数字证书等

最常见的principals和credentials组合就是用户名/密码了

##### 使用shiro完成认证管理

##  2.spring boot整合shiro

<dependency>
            <groupId>org.apache.shiro</groupId>
            <artifactId>shiro-spring</artifactId>
            <version>1.4.0</version>
        </dependency>

### 分析shiro核心api

subject 用户主体（把操作交给securitymanager）

securityManager  安全管理器（关联realm）

Realm  shiro连接数据库的桥梁

### 自定义realm类



### 编写配置类

