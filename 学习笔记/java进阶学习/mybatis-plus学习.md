# 一.mybatis与jdbc

mybatis是一个优秀的持久层框架。mybatis与jdbc的区别就是他对jdbc的一些操作做了封装但是又不是一个像hibernate的全自动的持久层框架他是半自动的就是在mybatis中我们需要自己去写一些sql，它具备动态sql是十分好用的。

mybatis底层使用了jdbc的接口完成结果与普通对象的映射。

核心组件：

1. SQL session factory builder  这是一个用来通过装载了配置信息的configuration实例对象去创建一个SQL session factory的接口，它也可以同时配置多个数据源信息
2. SQL session factory 应用实例  可以创建sqlsession。一个数据库就对应一个SQL session factory
3. SQL session 它封装了一系列的操作方法，可以面向数据库执行SQL语句，内部的执行方法是executor，涉及了一级缓存，二级缓存
4. executor   SQL session提供了数据库的操作方法但是真正面向数据库执行sql语句的对象是这个executor的实例对象
5. baseexecutor   这是一个抽象的实例化executor它实现了executor接口并且是一个抽象类，定义了许多抽象方法来让他的子类来实现
6. cache   mybatis中的缓存接口，提供了缓存相关的操作

mybatis的简单的运行原理：

​	首先将我们写的基本的配置信息封装为一个configuration对象，这个信息包括了不同环境的信息和数据源信息等等。

​	那么mybatis是如何将我们的sql语句成功执行的呢？首先会通过动态代理的方式将实现了mapper接口的对象全部动态代理为一个代理对象，在通过代理对象中的方法名去匹配我们所写的sql，并将所有的mapper都像这样代理完毕。然后我们在mapper中的方法会通过对应的select或者是delete这种信息去调用SQL session的方法来执行SQL session会调用执行器executor的方法去调用jdbc的接口来执行sql语句他。

这就是一个简单的过程但是实际中不会这样简单，我们还会面对多数据源等其他的问题。

# 二.缓存机制

mybatis内部值集合了ehcache这个缓存框架

mybatis的一级缓存是默认开启的他的的意思就是想要尽量的减少直接向数据库去查数据的次数，当我们第一次去数据库查询信息时会调用session然后session会调用执行器对象将查出来的数据先通过缓存执行器存入localcache中，在将数据返回给我们，当我们第二次查询时就会先进入缓存中去找这个sql如果已经存在了就直接将缓存中的拿出去，而不是到数据库中找，如果我们更新了数据就会清除掉缓存中的数据这样不会出现脏读。但是这个一级缓存的作用范围是在一个sqlsession中这就意味着我们具有两个数据源的时候一级缓存就不能满足我们了，这时候就可以打开二级缓存，但是二级缓存的作用范围是以命名空间为主的而我们明显不会将所有的sql都写在一个命名空间中而且大多时候一个命名空间中写的大多都是一个表的操作那么所以在多表的操作时会有脏读现象。没那么好用。

# 三.获取数据库中的表和字段

获取数据库的表和字段的方法：

## 1.通过sql语句

获取表select * from information_schema.Tables where TABLES_SCHEMA=(select database())

获取字段select * from information schema.COLUMNS where TABLES_SCHEMA=(select database()) and TABLE_NAME=#{tableName}

## 2.通过show命令

获取表show table status

获取字段show full filelds from 'student'

# 四.多数据源处理

## 1.单独使用mybatis

通过上面的学习我们可以知道一个SQL session对应一个数据源那么我们只要将多个数据源的信息分别写在不同的environment中并且在生成SQL session factory时指定不同的环境来生产多个SQL session就可以了

## 2.集成spring

集成spring时比单独使用mybatis还要简单，我们只需要在配置文件中写入多个数据源的信息，并且在配置类中创建不同的数据源对象bean在为不同的mapper赋予不同的数据源对象就可以了。

也可以通过注解的动态数据源的方式来实现。通过拦截器的方式来实现

## 3.读写分离



# 五.mybatis-plus

可以简化开发

https://mp.baomidou.com/guide/generator.html#使用教程