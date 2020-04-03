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



# 二.缓存机制



# 三.



# 四.多数据源处理



# 五.mybatis-plus