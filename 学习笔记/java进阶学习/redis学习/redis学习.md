# NOSQL概述





菜鸟教程学习笔记：https://www.runoob.com/redis/redis-data-types.html

# 简介

 Redis是一个开源的使用ANSI C语言编写、遵守BSD协议、支持网络、可基于内存亦可持久化的日志型、Key-Value数据库，并提供多种语言的API。 

 它通常被称为数据结构服务器，因为值（value）可以是 字符串(String), 哈希(Hash), 列表(list), 集合(sets) 和 有序集合(sorted sets)等类型。

Redis 是完全开源免费的，遵守BSD协议，是一个高性能的key-value数据库。

Redis 与其他 key - value 缓存产品有以下三个特点：

-  Redis支持数据的持久化，可以将内存中的数据保存在磁盘中，重启的时候可以再次加载进行使用。 
-  Redis不仅仅支持简单的key-value类型的数据，同时还提供list，set，zset，hash等数据结构的存储。
-  Redis支持数据的备份，即master-slave模式的数据备份。 

# **Redis 安装**

**下载地址：**https://github.com/MSOpenTech/redis/releases。

# 配置

Redis 的配置文件位于 Redis 安装目录下，文件名为 **redis.conf**(Windows 名为 redis.windows.conf)。redis的配置可以通过在cmd上修改

你可以通过 **CONFIG** 命令查看或设置配置项。

```
config get *  可以获取全部的配置信息
config set key  value  可以设置配置信息的值
```

# **Redis 数据类型**

Redis支持五种数据类型：string（字符串），hash（哈希），list（列表），set（集合）及zset(sorted set：有序集合)。

**String（字符串）**

redis 127.0.0.1:6379> SET key "学习"

**Hash（哈希）**

Redis hash 是一个键值(key=>value)对集合。

Redis hash 是一个 string 类型的 field 和 value 的映射表，hash 特别适合用于存储对象。

redis 127.0.0.1:6379> HMSET hashName field1 "Hello" field2 "World"

redis 127.0.0.1:6379> HGET hashName field1

**List（列表）**

Redis 列表是简单的字符串列表，按照插入顺序排序。你可以添加一个元素到列表的头部（左边）或者尾部（右边）。

redis 127.0.0.1:6379> lpush listName redis

redis 127.0.0.1:6379> lrange listName 0 1

**Set（集合）**

Redis 的 Set 是 string 类型的无序集合。

集合是通过哈希表实现的，所以添加，删除，查找的复杂度都是 O(1)。

redis 127.0.0.1:6379> sadd setName ch

redis 127.0.0.1:6379> smembers setName 

**zset(sorted set：有序集合)**

 Redis  zset 和 set 一样也是string类型元素的集合,且不允许重复的成员。

不同的是每个元素都会关联一个double类型的分数。redis正是通过分数来为集合中的成员进行从小到大的排序。

zset的成员是唯一的,但分数(score)却可以重复。

redis 127.0.0.1:6379> zadd runoob 0 redis

redis 127.0.0.1:6379> > ZRANGEBYSCORE runoob 0 1000

# 常用命令

redis-cli启动服务器

# Redis 数据备份与恢复

 Redis **SAVE** 命令用于创建当前数据库的备份。

redis 127.0.0.1:6379> SAVE 

## 恢复数据

 如果需要恢复数据，只需将备份文件 (dump.rdb) 移动到 redis 安装目录并启动服务即可。获取 redis 目录可以使用 **CONFIG**

# Redis 管道技术

 

Redis是一种基于客户端-服务端模型以及请求/响应协议的TCP服务。这意味着通常情况下一个请求会遵循以下步骤：

- 客户端向服务端发送一个查询请求，并监听Socket返回，通常是以阻塞模式，等待服务端响应。
- 服务端处理命令，并将结果返回给客户端。

Redis 管道技术可以在服务端未响应时，客户端可以继续向服务端发送请求，并最终一次性读取所有服务端的响应。

# **Java 使用 Redis**

**连接到 redis 服务**

Jedis就是在Java中使用Redis的意思

jedis菜鸟教程：https://www.runoob.com/redis/redis-java.html

jedisAPI:  https://tool.oschina.net/uploads/apidocs/redis/clients/jedis/Jedis.html

jedis

**java中利用jedis连接redis**

```xml
        <!-- 引入redis客户端依赖 -->
 		<dependency>
		    <groupId>redis.clients</groupId>
		    <artifactId>jedis</artifactId>
		    <version>2.4.2</version>
		</dependency>

```



```java
package com.zzipsun.test;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;

/**
 * @author zs
 */
public class RedisTest {

	    public static void main(String[] args) {
	        //连接本地的 Redis 服务
	        Jedis jedis = new Jedis("localhost");
	        System.out.println("连接成功");
	        //查看服务是否运行
	        System.out.println("服务正在运行: "+jedis.ping());
	        //存储数据到列表中
	        jedis.lpush("site-list", "Runoob");
	        jedis.lpush("site-list", "Google");
	        jedis.lpush("site-list", "Taobao");
	        // 获取存储的数据并输出
	        List<String> list = jedis.lrange("site-list", 0 ,0);
	        for(int i=0; i<list.size(); i++) {
	            System.out.println("列表项为: "+list.get(i));
	        }
	        // 获取数据并输出
	        Set<String> keys = jedis.keys("*"); 
	        Iterator<String> it=keys.iterator() ;   
	        while(it.hasNext()){   
	            String key = it.next();   
	            System.out.println(key);   
	        }
	    }
}


```

**spring集成redis**

```xml
	<!-- spring-redis实现 -->
    	<dependency>
            <groupId>org.springframework.data</groupId>
            <artifactId>spring-data-redis</artifactId>
            <version>1.3.4.RELEASE</version>
        </dependency>     

```

```java
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context" 
	xmlns:jdbc="http://www.springframework.org/schema/jdbc"  
	xmlns:jee="http://www.springframework.org/schema/jee" 
	xmlns:tx="http://www.springframework.org/schema/tx"
	xmlns:aop="http://www.springframework.org/schema/aop" 
	xmlns:mvc="http://www.springframework.org/schema/mvc"
	xmlns:util="http://www.springframework.org/schema/util"
	xmlns:jpa="http://www.springframework.org/schema/data/jpa"
	xsi:schemaLocation="
		http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-4.1.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.1.xsd
		http://www.springframework.org/schema/jdbc http://www.springframework.org/schema/jdbc/spring-jdbc-4.1.xsd
		http://www.springframework.org/schema/jee http://www.springframework.org/schema/jee/spring-jee-4.1.xsd
		http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-4.1.xsd
		http://www.springframework.org/schema/data/jpa http://www.springframework.org/schema/data/jpa/spring-jpa-1.3.xsd
		http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-4.1.xsd
		http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc-4.1.xsd
		http://www.springframework.org/schema/util http://www.springframework.org/schema/util/spring-util-4.1.xsd">
	
	<!-- 配置 spring-mybatis.xml -->
	<!-- 读取配置文件 -->
 	<util:properties id="redis"
		location="classpath:conf/redis.properties"/> 	 
	<util:properties id="jdbc"
		location="classpath:conf/jdbc.properties"/> 

	<!-- 配置数据库连接池 -->
	<bean id="dataSource"
		class="org.apache.commons.dbcp.BasicDataSource"
		destroy-method="close"> 
		<property name="driverClassName"
			value="#{jdbc.driver}"/>
		<property name="url"
			value="#{jdbc.url}"/>
		<property name="username"
			value="#{jdbc.user}"/>
		<property name="password"
			value="#{jdbc.password}"/>
		<property name="maxIdle"
			value="#{jdbc.maxIdle}"/>
		<property name="maxWait"
			value="#{jdbc.maxWait}"/>						
		<property name="maxActive"
			value="#{jdbc.maxActive}"/>
		<property name="defaultAutoCommit"
			value="#{jdbc.defaultAutoCommit}"/>
		<property name="defaultReadOnly"
			value="#{jdbc.defaultReadOnly}"/>
		<property name="testOnBorrow"
			value="#{jdbc.testOnBorrow}"/>			
		<property name="validationQuery"
			value="#{jdbc.validationQuery}"/>	
	</bean>
	
	<!-- 配置MyBatis的 SessionFactory -->
	<bean id="sqlSessionFactory"
		class="org.mybatis.spring.SqlSessionFactoryBean">
		<property name="dataSource"
			 ref="dataSource"/>
		
		<property name="mapperLocations"
			value="classpath:mapper/*.xml"/>

	</bean>
	<!-- Mapper接口组件扫描 -->
	<bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
		<property name="basePackage" 
			value="com.zzipsun.dao"/>
	</bean>
	
	<!--  transaction config related... -->	
	<bean id="txManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
		<property name="dataSource"
			ref="dataSource"/>
	</bean>
	<!-- 设置 注解驱动的事务管理  -->
	<tx:annotation-driven 
		transaction-manager="txManager"/>
		
		
    <!-- redis config start -->
    <!-- 配置JedisPoolConfig实例 -->
     <bean id="poolConfig" class="redis.clients.jedis.JedisPoolConfig">
        <property name="maxIdle" value="#{redis.maxIdle}" />
        <property name="maxTotal" value="#{redis.maxActive}" />
        <property name="maxWaitMillis" value="#{redis.maxWait}" />
        <property name="testOnBorrow" value="#{redis.testOnBorrow}" />
    </bean>

   <!--  配置JedisConnectionFactory -->
    <bean id="jedisConnectionFactory"
        class="org.springframework.data.redis.connection.jedis.JedisConnectionFactory">
        <property name="hostName" value="#{redis.host}" />
        <property name="port" value="#{redis.port}" />
       <!--  <property name="password" value="#{redis.password}" /> -->
        <property name="database" value="#{redis.dbIndex}" />
        <property name="poolConfig" ref="poolConfig" />
    </bean>
    <!-- 配置RedisTemplate -->
     <bean id="redisTemplate" class="org.springframework.data.redis.core.RedisTemplate">
        <property name="connectionFactory" ref="jedisConnectionFactory" />
                <!--     如果不配置Serializer，那么存储的时候只能使用String，如果用对象类型存储，那么会提示错误 can't cast to String！！！-->
        <property name="keySerializer">
            <bean class="org.springframework.data.redis.serializer.StringRedisSerializer"/>
        </property>
        <property name="valueSerializer">
            <bean class="org.springframework.data.redis.serializer.JdkSerializationRedisSerializer"/>
        </property>
    </bean> 

    <!--自定义redis工具类,在需要缓存的地方注入此类  -->
    <bean id="redisUtil" class="com.zzipsun.util.RedisUtil">
    	<property name="redisTemplate" ref="redisTemplate" />
    </bean>
	
</beans>

```



# SpringBoot框架中使用Redis缓存**

缓存就是在内存中存储的数据备份，当数据没有发生本质改变的时候，我们就不让数据的查询去数据库进行操作，而去内存中取数据，这样就大大降低了数据库的读写次数，而且从内存中读数据的速度比去数据库查询要快一些，这样同时又提高了效率

```xml
		<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
        </dependency>
```

**创建名为RedisCacheConfig的类，做为Redis的配置类。**

![](F:\opensource\項目積纍文檔\java进阶学习\缓存工具redis学习\img\redis配置信息.png)

```java
/**
 * redis配置类
 * @program: springbootdemo
 * @Date: 2019/1/25 15:20
 * @Author: Mr.Zheng
 * @Description:
 */
@Configuration
@EnableCaching //开启注解
public class RedisConfig extends CachingConfigurerSupport {

    /**
     * retemplate相关配置
     * @param factory
     * @return
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {

        RedisTemplate<String, Object> template = new RedisTemplate<>();
        // 配置连接工厂
        template.setConnectionFactory(factory);

        //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值（默认使用JDK的序列化方式）
        Jackson2JsonRedisSerializer jacksonSeial = new Jackson2JsonRedisSerializer(Object.class);

        ObjectMapper om = new ObjectMapper();
        // 指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // 指定序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String,Integer等会跑出异常
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jacksonSeial.setObjectMapper(om);

        // 值采用json序列化
        template.setValueSerializer(jacksonSeial);
        //使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());

        // 设置hash key 和value序列化模式
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(jacksonSeial);
        template.afterPropertiesSet();

        return template;
    }

    /**
     * 对hash类型的数据操作
     *
     * @param redisTemplate
     * @return
     */
    @Bean
    public HashOperations<String, String, Object> hashOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForHash();
    }

    /**
     * 对redis字符串类型数据操作
     *
     * @param redisTemplate
     * @return
     */
    @Bean
    public ValueOperations<String, Object> valueOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForValue();
    }

    /**
     * 对链表类型的数据操作
     *
     * @param redisTemplate
     * @return
     */
    @Bean
    public ListOperations<String, Object> listOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForList();
    }

    /**
     * 对无序集合类型的数据操作
     *
     * @param redisTemplate
     * @return
     */
    @Bean
    public SetOperations<String, Object> setOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForSet();
    }

    /**
     * 对有序集合类型的数据操作
     *
     * @param redisTemplate
     * @return
     */
    @Bean
    public ZSetOperations<String, Object> zSetOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForZSet();
    }

}
```

```java

```

# docker配置redis

首先拉取镜像，运行，进入redis容器，之后就和普通的redis一样使用就可以了

redis-cli链接redis

select *可以到*号数据库进入之后就可以通过set等命令操作数据了

dbsize 查看当前数据库数据的数量

flushdb清空当前数据库

flushall 清空全部数据库

root@5ef068731883:/data# redis-cli
127.0.0.1:6379> ping
PONG
127.0.0.1:6379> select 0
OK
127.0.0.1:6379> select 12
OK
127.0.0.1:6379[12]> select 0
OK
127.0.0.1:6379> select 1
OK
127.0.0.1:6379[1]> set test test
OK
127.0.0.1:6379[1]> get test
"test"

127.0.0.1:6379[1]> dbsize
(integer) 1

# 常用命令

## 基本命令

keys * 获取所有 的key

move 键名 库号 移动某个到指定的库

type key 看这个key是什么类型

del  key 删除key

exists key 是否存在这个key

expire key 10 10秒过期

pexpire key 10 10毫秒后过期

persist key 删除设定的过期时间

ttl key 看还有多少秒过期

http://redisdoc.com/**这个网站包含了基本的所有命令**

## String命令

set  kry value 插入一个

get  key 取出一个

getrange key 0 -1 取出指定key的范围内的字符

getset key newvalue 为某个key设置新的value

mset key1 v1 k2 v2 同时设置多个

mget key1 k2 k3取多个

setnx k v 不存在就插入

incr age 递增

decr age 递减

append 追加

strlen 长度

## list命令

lpush name v1 v2 v3 ... 插入一个list

lpop key 弹出最后一个元素

rpop key 弹出第一个

rpush key v1 v2 ... 插入

llen key 长度

lrem key 删除

lrem key count value 从count开始往后找值为value的都删除

lindex key 2 指定索引的值

lset key v 设定指定索引的值

linsert key after/before index v 插入

## hash命令

hset 

hget

hmset

hmget

hgetall

hexists

hsetnx 不存在则设置

hincrby 递增

hdel 删除

hkeys 只取出key

hvals 只取出值

hlen 返回长度

## set命令

sadd

smembers key 显示指定的

srem 删除

sismember key value 判断是否在set中

scard key 个数

sdiff | sinter | sunion 差集|交集|并集

srandmember 随机获取一个

spop 弹出一个

## zset命令



# 配置文件redis.conf



# 持久化



# java链接redis

```xml
导入依赖
         <dependency>
            <groupId>redis.clients</groupId>
            <artifactId>jedis</artifactId>
            <version>2.7.1</version><!--版本号可根据实际情况填写-->
         </dependency>

         <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-lang3</artifactId>
            <version>3.3.2</version>!--版本号可根据实际情况填写-->
         </dependency>


//具体操作
import redis.clients.jedis.Jedis;

public class jedisLearn {
    private static final String HOST = "**";
    private static final int PORT = **;
    public static void main(String[] args) {
        Jedis jedis = new Jedis(HOST,PORT,1500);
        System.out.println(jedis);
        String ping = jedis.ping();
        System.out.println(ping);
        /**
         * jedis里面的数据操作和我们在redis的控制台中基本是一样的，此处不做演示了
         */
    }
}

```



# spring集合redis

这里用的依赖和上面是一样的

使用上面也基本一样，我们也可以把一个jedis注入使用也可以正常使用都一样

下面我们说一下秒杀这个需求怎么实现

```
秒杀需求描述：在倒计时结束时人们抢着购买指定数量的商品，这个需求要求我们能够在高并发的情况下实现，不会这边买了在那边还显示有商品，也不能一个商品买两次
我的想法就是学习了缓存之后就会发现很明显，这个需求不可能是直接链接数据库去操作的那样所需要的时间会很多。我们可以通过redis的缓存来实现其中的一部分，但是肯定不会是最好的解决办法这只是学习了新知识后的应用而已。

将要被秒杀的商品存放在redis的链表中可以通过pop方法减少应为redis是单线程的所以不会出现线程问题，我们只需要加大数据池的线程量并且引入消息队列这样应该会对高并发进行一些缓解

redis的数据池创建方式

		 GenericObjectPoolConfig genericObjectPoolConfig = 
 		new GenericObjectPoolConfig();
        genericObjectPoolConfig.setMaxIdle(20);
        genericObjectPoolConfig.setMinIdle(10);
        JedisPool jedisPool = new JedisPool(genericObjectPoolConfig,HOST,PORT);
        Jedis resource = jedisPool.getResource();
        String ping1 = resource.ping();
        System.out.println(ping1);
```



# springboot集合redis

```
启动器
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-redis</artifactId>
    <version>1.3.8.RELEASE</version>
</dependency>

配置
spring:
  redis:
    timeout: 1500
    jedis:
      pool:
        max-idle: 50
        min-idle: 20
        max-wait: 200
        max-active: 30
    port: 6379
    host: ****

使用
 @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Test
    public void RedisTest(){
        ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
        ListOperations<String, String> stringStringListOperations = stringRedisTemplate.opsForList();
        stringStringValueOperations.set("test","v1",Duration.ofSeconds(30));
        String s = stringStringValueOperations.get("test", 0, -1);
        System.out.println(s);
    }

```

StringRedisTemplate使用的是很多的但是当我们要存入一个对象类型就不用这个了而是使用下面我们在做缓存时用的这个类RedisTemplate<String,Object> 

# SpringBoot使用redis做缓存

