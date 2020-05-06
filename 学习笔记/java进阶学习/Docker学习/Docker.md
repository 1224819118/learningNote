# Docker学习

## docker的基本理解

只代表我自己的理解，对于自己使用的技术有自己的理解的话是可以更好的深入学习这门技术，并且在遇到问题时也能更好产生解决思路。

docker是一种容器，它可以对一系列的环境进行管理，例如我们在本机上搭好了一个项目但是这都是基于我们自己本机的环境和版本的，而服务器上的环境可能与我们的版本并不一致，而使用了docker技术就可以将我们本机上的环境与项目构建为一个箱子放到服务器上其他的都由docker来管理，这样可以为我们带来很多的方便。比如像我现在还在上学买了个服务器，想要尝试一些新的技术比如nginx，fastdfs等但是象是fastdfs没办法在Windows上安装而虚拟机有很卡我们完全可以直接在服务器的docker上去尝试这个新技术，删除时直接删掉这个容器就可以了，不会象是在Linux上搭一个去删除就很麻烦。而且我本人现在没学一个新的技术都会写一个小项目用上这个技术他们的环境都不会太一至，而使用 了docker就是每个项目和他们的环境都是一个容器直接把这个容器镜像放到服务器上就可以发布了，删除也是直接删镜像就很方便，不会是所有的东西全都下载在我的服务器上一至于我的项目拿到新买的服务器上又要下载一堆的东西才能跑起来。

## docker的安装

最好在centos7上并且是64位的系统，可以用uname -r看看自己的版本合不合适，像我之前用的虚拟机就是6，不合适的话就在下个新的，我下的7

3.10.0-957.el7.x86_64     内核要在3.10以及以上的内核才行啊

注意7和6的防火墙操作是不一样的，6直接在iptables配置里改就行了，7要用firewall命令去网上搜一下很多讲解的

https://docs.docker.com/install/linux/docker-ce/centos/这是官方的安装文档

我们要有c环境才行安装gcc.c++

安装dockers   yum -y install docker

启动docker  systemctl start docker

查看版本  docker version 

```
[root@localhost user1]# docker version
Client:
 Version:         1.13.1   #版本
 API version:     1.26
 Package version: docker-1.13.1-109.gitcccb291.el7.centos.x86_64
 Go version:      go1.10.3
 Git commit:      cccb291/1.13.1
 Built:           Tue Mar  3 17:21:24 2020
 OS/Arch:         linux/amd64

Server:
 Version:         1.13.1
 API version:     1.26 (minimum version 1.12)
 Package version: docker-1.13.1-109.gitcccb291.el7.centos.x86_64
 Go version:      go1.10.3
 Git commit:      cccb291/1.13.1
 Built:           Tue Mar  3 17:21:24 2020
 OS/Arch:         linux/amd64
 Experimental:    false
```

docker images 可以看当前我们都有什么镜像

测试运行helloword如下这个hello world是在docker官方的一个测试镜像可以在hubdocker上查找我们所需要的镜像都可以这么拉取

```
[root@localhost user1]# docker run hello-world

Unable to find image 'hello-world:latest' locally
Trying to pull repository docker.io/library/hello-world ... 
latest: Pulling from docker.io/library/hello-world
1b930d010525: Pull complete 
Digest: sha256:f9dfddf63636d84ef479d645ab5885156ae030f611a56f3a7ac7f2fdd86d7e4e
Status: Downloaded newer image for docker.io/hello-world:latest

Hello from Docker!
This message shows that your installation appears to be working correctly.

To generate this message, Docker took the following steps:
 1. The Docker client contacted the Docker daemon.
 2. The Docker daemon pulled the "hello-world" image from the Docker Hub.
    (amd64)
 3. The Docker daemon created a new container from that image which runs the
    executable that produces the output you are currently reading.
 4. The Docker daemon streamed that output to the Docker client, which sent it
    to your terminal.

To try something more ambitious, you can run an Ubuntu container with:
 $ docker run -it ubuntu bash

Share images, automate workflows, and more with a free Docker ID:
 https://hub.docker.com/

For more examples and ideas, visit:
 https://docs.docker.com/get-started/
再次看看我们的镜像都有哪些
[root@localhost user1]# docker images
REPOSITORY              TAG                 IMAGE ID            CREATED             SIZE
docker.io/hello-world   latest              fce289e99eb9        15 months ago       1.84 kB
[root@localhost user1]# 
多出了一个新的镜像就是刚刚的helloworld
```

在拉一个前面学的nginx

[root@localhost user1]# docker pull nginx  #在hubdocker上搜索这个在侧面有一个复制按钮就会复制到这个命令直接用就ok

pull  命令就是去拉镜像

docker run -p 8080:80 -d docker.io/nginx也可以通过这种命令,这个时运行的命令，但是如果我们没有要运行的这个镜像就会自动去下载

run 运行  变成容器

-P 端口映射

8080：80  8080代表宿主机对外暴漏的端口  80代表容器里面的nginx服务器使用的端口

-d 代表后台运行  不加-d命令窗口就不能用了

最后面那串代表要运行的镜像

[root@localhost user1]# docker images
REPOSITORY              TAG                 IMAGE ID            CREATED             SIZE
docker.io/nginx         latest              6678c7c2e56c        3 weeks ago         127 MB
docker.io/hello-world   latest              fce289e99eb9        15 months ago       1.84 kB

 上面那串命令会默认下载最新的镜像我们也可以通过镜像id来下载指定版本的镜像

如果报了 (iptables failed: iptables --wait -t nat -A DOCKER -p tcp -d 0/0 --dport 8080 -j DNAT --to-destination 172.17.0.2:80 ! -i docker0: iptables: No chain/target/match by that name.错误应该是你在运行docker时才关闭的防火墙，重启docker就行了 systemctl restare docker

我们再拉一个tomcat 会发现下载的镜像并不是仅仅一个tomcat应为很明显下的这些比tomcat大，应为这是docker会自动下载运行tomcat所依赖的东西，比如jdk等等就像yum命令一样树形的依赖会自动下载上

## 配置阿里镜像仓库进行加速

搜索阿里云的容器镜像服务，注意这个要有阿里云的账号才可以。找到镜像加速器

进入/etc/docker/daemon.json 在里面加上你自己的镜像

重新加载daemon重新启动docker就可以了

## docker的其他命令

docker rmi 镜像id  这个可以移除镜像

```
[root@localhost user1]# docker rmi fce289e99eb9
Error response from daemon: conflict: unable to delete fce289e99eb9 (must be forced) - image is being used by stopped container c6ef148e3d94

```

移除之前的hello world时报了这个错是因为我们这个镜像正在被启动变为了容器

我们要先关掉这个容器 docker --help 可以看帮助文档和Linux中的命令一样

```
Commands:
  attach      Attach to a running container
  build       Build an image from a Dockerfile
  commit      Create a new image from a container's changes
  cp          Copy files/folders between a container and the local filesystem
  create      Create a new container
  diff        Inspect changes on a container's filesystem
  events      Get real time events from the server
  exec        Run a command in a running container
  export      Export a container's filesystem as a tar archive
  history     Show the history of an image
  images      List images
  import      Import the contents from a tarball to create a filesystem image
  info        Display system-wide information
  inspect     Return low-level information on Docker objects
  kill        Kill one or more running containers
  load        Load an image from a tar archive or STDIN
  login       Log in to a Docker registry
  logout      Log out from a Docker registry
  logs        Fetch the logs of a container
  pause       Pause all processes within one or more containers
  port        List port mappings or a specific mapping for the container
  ps          List containers
  pull        Pull an image or a repository from a registry
  push        Push an image or a repository to a registry
  rename      Rename a container
  restart     Restart one or more containers
  rm          Remove one or more containers
  rmi         Remove one or more images
  run         Run a command in a new container
  save        Save one or more images to a tar archive (streamed to STDOUT by default)
  search      Search the Docker Hub for images
  start       Start one or more stopped containers
  stats       Display a live stream of container(s) resource usage statistics
  stop        Stop one or more running containers
  tag         Create a tag TARGET_IMAGE that refers to SOURCE_IMAGE
  top         Display the running processes of a container
  unpause     Unpause all processes within one or more containers
  update      Update configuration of one or more containers
  version     Show the Docker version information
  wait        Block until one or more containers stop, then print their exit codes

```

以上是所有的命令可以看到有个rm可以移除容器，镜像启动后就会变为容器

注意exec进入容器是会新建一个终端而attach是进入当前的终端，所以如果要进入centos这种用attach，进入nginx，tomcat这种用exec



## docker底层原理



## docker镜像

docker的镜像是一种层级式的联合文件，像是洋葱一样一层一层的包裹着不同的镜像，这样的好处就是可以资源共享

commit命令

当镜像运行成为容器后可以commit为一个新的镜像

docker commit -m ="描述" -a="作者" imageid caohaotomcat:1.0

## docker容器数据卷

现在我们打开了一个容器但是一旦这个容器被移除了那他里面的数据就没了，而容器数据卷这个东西就是为了解决这个问题，它可以创建一个由宿主机和容器与容器之间共享的文件夹

1.命令添加

docker run -it -v /宿主机目录:/容器内目录  -p 9000:80 -d  nginx /bin/bash

如果出现文件夹没有权限的问题就在创建容器时加上--privileged=true这个

2.使用dockerfile添加

## dockerfile详解

1.dockerfile的构建过程

编写Dockerfile文件

docker build 生成新的镜像

docker run 运行镜像

### **关键字**

FROM
 指定基础镜像，当前新镜像是基于哪个镜像的。其中，`scratch`是个空镜像，这个镜像是虚拟的概念,并不实际存在,它表示一个空白的镜像，当前镜像没有依赖于其他镜像
 `FROM scratch` 

MAINTAINER
 镜像维护者的姓名和邮箱地址
 `MAINTAINER Sixah <sixah@163.com>` 

RUN（尽量少run）
 容器构建时需要运行的Linux命令
 `RUN echo 'Hello, Docker!'` 

EXPOSE
 当前容器对外暴露出的端口，这个没有实际作用只是一个提示作用
 `EXPOSE 8080` 

WORKDIR
 指定在创建容器后，终端默认登陆进来的工作目录，一个落脚点
 `WORKDIR /go/src/app` 

ENV
 用来在构建镜像过程中设置环境变量
 例如，ENV MY_PATH /usr/mytest
 这个环境变量可以在后续的任何RUN指令中使用，这就如同在命令前面指定了环境变量前缀一样;当然，也可以在其他指令中直接使用这些环境变量，比如：WORKDIR $MY_PATH

ADD
 将宿主机目录下的文件拷贝进镜像且ADD命令会自动处理URL和解压tar压缩包
 `ADD Linux_amd64.tar.gz` /

COPY
 类似于ADD，拷贝文件和目录到镜像中，将从构建上下文目录中<源路径>的文件/目录复制到新的一层镜像内的<目标路径>位置
 `COPY . /go/src/app` 

VOLUME
 容器数据卷，用于数据保存和持久化工作
 `VOLUME /data` 

CMD
 指定一个容器启动时要运行的命令。Dockerfile中可以有多个CMD指令，但只有最后一个生效，CMD会被docker run之后的参数替换
 `CMD ["/bin/bash"]` 

ENTRYPOINT
 指定一个容器启动是要运行的命令。ENTRYPOINT的目的和CMD一样，都是在指定容器启动程序及参数

ONBUILD
 当构建一个被继承的Dockerfile时运行的命令，父镜像在被子镜像继承后，父镜像的ONBUILD指令被触发

我们写一个我的centos来测试这些命令

```

#基于镜像
FROM centos
#作者
MAINTAINER 曹昊<1224819118@qq.com>
#声明变量
ENV ROOT_PATH   /usr/local/
#设置工作目录，用户进入容器新建终端的目录
WORKDIR $ROOT_PATH
#安装一个vim
RUN yum -y install vim
#安装网络工具
RUN yum -y install net-tools
#对外暴漏端口
EXPOSE 80
#输出一个构建成功的字符串
CMD echo "suucess"

```

[root@localhost mycentos]# docker build -t mycentos:1.0 .

注意后面那个点，不能落下

运行就完事了

[root@localhost mycentos]# docker run -it mycentos:1.0 /bin/bash
[root@b5cd9b442039 local]# pwd
/usr/local
[root@b5cd9b442039 local]# 

下面来看下制作jar包的镜像和war包的镜像

jar:

基于jdk

我这边只写基于jar包的了，war包跟这个一个原理

- 上传jar
- 编写Dockerfile
- build
- 运行测试

分为以上四步

我用的xshell rz上去就行了

```
FROM openjdk:8u181-jdk-alpine
MAINTAINER 曹昊<1224819118@qq.com>
ENV ROOT_PATH  /root/
WORKDIR $ROOT_PATH
COPY blog.jar ./
EXPOSE 8080
CMD ["java","-jar","blog.jar"]


root@localhost myjar]# docker build -t myblog:1.0 .
。。。。。
[root@localhost myjar]# docker images
REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
myblog              1.0                 5c7da35a99ca        8 seconds ago       140 MB
mycentos            1.0                 dd24d81e5849        33 minutes ago      327 MB
docker.io/tomcat    latest              a7fa4ac97be4        8 days ago          528 MB
docker.io/nginx     latest              6678c7c2e56c        3 weeks ago         127 MB
docker.io/centos    8                   470671670cac        2 months ago        237 MB
docker.io/centos    latest              470671670cac        2 months ago        237 MB
docker.io/openjdk   8u181-jdk-alpine    04060a9dfc39        15 months ago       103 MB
[root@localhost myjar]# 

```

war:

基于tomcat

## 本地镜像发布到阿里云

首先在阿里云镜像仓库创建一个仓库，然后点击管理按照里面的说明走就行

## docker四种网络模式

bridge模式：使用--net =bridge指定这个模式，这个也是默认模式，就像虚拟机一样会虚拟出两张网卡为我们服务在容器中创建eth0,吗，每启动一个都会虚拟出一个veth....网卡与容器进行通信，主用

host模式：使用--net =host指定这个模式，这个模式容器会与主机共用一个网络ip，不会模拟出一个网卡，有时会用

none模式：使用--net =none指定这个模式，

container模式：使用--net =container指定这个模式，

## docker常用安装

安装MySQL时直接运行回报一下错误

```
[root@localhost docker]# docker run -p 3306:3306 mysql:5.7
2020-03-26 11:08:46+00:00 [Note] [Entrypoint]: Entrypoint script for MySQL Server 5.7.29-1debian10 started.
2020-03-26 11:08:46+00:00 [Note] [Entrypoint]: Switching to dedicated user 'mysql'
2020-03-26 11:08:46+00:00 [Note] [Entrypoint]: Entrypoint script for MySQL Server 5.7.29-1debian10 started.
2020-03-26 11:08:46+00:00 [ERROR] [Entrypoint]: Database is uninitialized and password option is not specified
	You need to specify one of MYSQL_ROOT_PASSWORD, MYSQL_ALLOW_EMPTY_PASSWORD and MYSQL_RANDOM_ROOT_PASSWORD

```

这是由于我们没有设置用户名和密码所致

[root@localhost docker]# docker run --name mysql-57-9010 -p 9010:3306 -e MYSQL_ROOT_PASSWORD=6222688-d mysql:5.7

解决办法如上 -e就是ENTRYPOINT的意思就是在在镜像的Dockerfile中加入

ENTRYPOINT MYSQL_ROOT_PASSWORD=123456这句话

还有就是最好用之前学的数据卷来完成数据的共享否则这个容器停止了那数据就没了

**docker 安装Nginx** 

首先拉取一个nginx镜像

docker pull nginx:1.18.0

之后我们运行这个镜像让他变成一个容器

这边我们可以选择直接运行也可以进行配置文件的挂载

我这里由于没有事先准备nginx配置文件所以我打算到容器内去配置这些文件

docker exec -it nginx bash

nginx.conf配置文件在 /etc/nginx/ 下面，但是你使用vim nginx.conf 或者vi nginx.conf

会发现vi或者vim命令没有用，解决办法：apt-get update 完成之后 apt-get install vim

安装好了vim之后就可以进去配置具体的信息了

这一部分在nginx的笔记中写了