# nginx学习

## nginx基本概念：

- ### nginx是什么？

  由于单个的服务器的并发量是有限的，而我们可以通过纵向的扩展来增加并发量，而这种扩展总是需要一个对他们的请求进行分发个管理的容器就是nginx

## nginx的安装，命令，配置文件

- ### 在Linux下安装

  首先由于nginx是c语言开发的所有需要安装c语言的环境gcc-c++我是通过yum 在线管理安装的

  其次还要安装一些依赖：prec , openssl ,zlib

  最后解压我们的nginx.tar.gz 

  下面就是nginx的安装了

  首先创建一个/usr/nginx目录，并使用刚刚解压出的shell文件configure 设置前缀到nginx目录

  ./configure --prefix=/usr/nginx/

  编译：make

  安装：make install

  以上完成后会在nginx目录下出现一下目录

  [root@localhost nginx]# ll /usr/nginx/
  total 4
  drwxr-xr-x. 2 root root 4096 Mar 24 22:43 conf
  drwxr-xr-x. 2 root root   40 Mar 24 22:43 html
  drwxr-xr-x. 2 root root    6 Mar 24 22:43 logs
  drwxr-xr-x. 2 root root   19 Mar 24 22:43 sbin

  出现这些文件夹就是安装成功了

  conf:配置文件

  html:静态文件

  logs:日志文件

  sbin:命令

- ### 常用命令

  启动命令： ./nginx 

  用管道命令查看是否启动了：

  [root@localhost sbin]# ps -ef|grep nginx 
  root      66839      1  0 22:50 ?        00:00:00 nginx: master process ./nginx
  nobody    66840  66839  0 22:50 ?        00:00:00 nginx: worker process
  root      66844   9152  0 22:50 pts/1    00:00:00 grep --color=auto nginx

  启动后会出现一些临时文件

  [root@localhost ~]# cd /usr/nginx/
  [root@localhost nginx]# ll
  total 4
  drwx------. 2 nobody root    6 Mar 24 22:50 client_body_temp
  drwxr-xr-x. 2 root   root 4096 Mar 24 22:43 conf
  drwx------. 2 nobody root    6 Mar 24 22:50 fastcgi_temp
  drwxr-xr-x. 2 root   root   40 Mar 24 22:43 html
  drwxr-xr-x. 2 root   root   58 Mar 24 22:50 logs
  drwx------. 2 nobody root    6 Mar 24 22:50 proxy_temp
  drwxr-xr-x. 2 root   root   19 Mar 24 22:43 sbin
  drwx------. 2 nobody root    6 Mar 24 22:50 scgi_temp
  drwx------. 2 nobody root    6 Mar 24 22:50 uwsgi_temp

  中止：./nginx -s stop

- ### 配置文件

  nginx.conf 里可以配置一些基本的配置，

  server{

  #监听的端口

  ​	liisten   80;

  #主机名

  ​	server_name test.com(域名或者ip地址);

  #资源地址配置

  ​	location /test {

  ​			#静态资源路径

  ​				root html/test;

  ​				index test.html;

  ​		}

  }

## nginx配置实例

- ### 反向代理

  #### 代理

  代理可以分为正向代理和反向代理，正向代理就是请求端知道他在请求什么而服务端并不知道具体请求他只负责把中间的代理服务器所需要的交给代理服务器就可以了

  而反向代理则是请求端不知道具体的是哪台服务器为他服务他只是请求代理服务器，而具体的工作有代理服务器交给某个服务器去实现并返回

- ### 负载均衡

  负载均衡这个概念就是代理服务器将请求通过一定的算法交给某个服务器的过程，旨在将请求尽可能的交给适当的服务器去处理，以此来减轻高请求数量的压力。

  轮询，权重，IP哈希这三种算法是最为常见的，

  轮询：就是按顺序去给。比如第一个请求交给服务器1第二个则交给服务器2第三个再交给服务器1这种

  权重就是给服务器设置一个权重，按照权重在权重之和里所占的比例按这个比例的概率去提交请求

  IP哈希：就像哈希表一样某个规则下的某一类IP的请求都会放在某个服务器中，他的特点就是一个IP请求交给了服务器1去处理那么后面这个IP再来请求也一定实在服务器1处理的

- ### 反向代理+负载均衡配置

  这里由于我的虚拟机上jar一个spring boot有点卡，所以我在windows中安装了nginx来实现这个步骤

  Windows中安装很简单把压缩包解压到一个纯英文路径下在cmd中cd到这个路径运行nginx.exe就行了，他的关闭和重启与Linux中差不多，nginx -s stop 和 nginx -s reload

  准备了三个spring boot项目，由于是在一个电脑上所有我写了不同的端口同时启动

  首先在nginx.conf里的server上面加入配置

  #配置负载均衡
      upstream www.caohao.com{
          server 192.168.145.132:8080;
          server 192.168.145.132:8081;
  }

  修改server中的server_name为上面配置的www.caohao.com

  修改location

   location / {

  ​           proxy_pass http://www.caohao.com；
  ​        }

  由于上面写的域名会先到本地的host文件中去查所以我们在host中加入对应的键值对

  C:\Windows\System32\drivers\etc中的hosts

  这样我在浏览器中访问http://www.caohao.com/test就会按照默认的轮询的负载均衡模式依次由两个tomcat去循环调用他们

  #### 修改负载均衡算法

  在刚刚的upstream配置中进行修改

    upstream www.caohao.com{
          server 192.168.145.132:8080 weight=3;
          server 192.168.145.132:8081 weight=1;

  **这就是权重访问**

  在upstream最后一行加入下面的代码就可以改为IP哈希算法了，对我个人来说这个比较好用

   ip_hash;

  **这就是IP哈希**

  这里也可以配置超时时间，最大失败次数之类的

- ### 动静分离

  将传统的动态资源文件与静态资源文件分开处理

  只需要我们将静态资源文件放到nginx中并配置就可以了

## 如何配置高可用的集群

## nginx原理

