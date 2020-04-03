Linux系统中一切都是文件，并且文件是没有文件类型的后缀的

命令也是一个文件时一系列的二进制的可执行文件放在/bin/目录下，注意命令严格区分大小写

## Linux各个目录的作用

/bin/:所有用户都可以执行的命令

/sbin/只有root可以执行

/usr/bin/：单用户模式下不可以执行

/sur/sbin/

上面四个放着绝大部分的可执行命令

/boot/ 系统启动目录，保存系统启动相关的文件。最好别在这里写东西，写满了就打开的贼慢

/dev/ 设备文件保存地址，硬件设备的文件

/etc/  默认的配置文件保存地址 绝大部分配置都在这里

/home/用户的家目录每个用户都会在这里放着自己的目录

/lib/系统调用的函数库目录

/lost+found/系统的一些损坏的文件和文件碎片啥的都在这里，也是分区的备份恢复目录，每个分区都有一个自己的这个文件夹

/media/

/mnt/

/misc/

上面三个时挂载目录，象是U盘光盘，移动硬盘之类的

/opt/第三方软件安装的软件保存位置，现在更多的是放在usr下的local目录下这个opt基本不用

/proc/

/sys/

上面两个是虚拟文件系统，这个目录下的

/root/

/srv/

/tmp/

/usr/系统软件资源目录，都是一些系统运行的资源目录

/var/动态资源数据保存目录，象是日志之类的数据

## 服务器注意事项

不许关机只能重启

重启时应该关闭服务

## 常用命令

### 文件处理命令

touch 创建文件

cat 查看文件

more 更方便的查看文件

f翻页   enter换行  q退出 

less 也是查看文件

他包括了more的操作 还多了 pageup往上翻页  上箭头是一行一行翻页  还可以搜索并且N可以跳到下一个关键词   

head  -n 查看文件可以看文件的前n行

tail -n  查看文件的后N行

### 目录处理命令

ls

显示的文件的信息格式如下

-rw-r--r--

-表示一个文件

d一个目录

l一个链接

rw- r-- r--这三个部分是三个组成的各自的权限 -代表没有权限

u   g    o

所有者 所属组  其他人

r读 w写  x执行

cd                             ..回到上一级目录

pwd

mkdir -p []

cp -rp [] []

mv [原文件] [目标目录]

剪切和改名

rm -rf  []

-r删除目录

-f强制删除

删除任何文件之前最好都做备份

做的读写越多回复可能越小

### 链接命令

ln -s  [] []

加s则是软连接 没有就是硬链接

软连接类似于快捷方式，就是一个链接指向

硬链接就是一个可以与源文件同步更新的文件。他们内容是一样的

### 权限管理命令

所有者，所属组，其他人三组用户对应三组权限

只有所有者和root可以更改权限

chmod [ugoa] [+-=] [rwx] 空格 [文件或者目录]

数字方式  r==4  w==2 x==1

举例：

rwxrw-r--

764

chmod 777 test.log

-R 会将目录下的所有子目录都被改

chown  [所有者] [文件]

改变文件的所有者

只有root可以改变所有者

chgrp [] [] 修改所属组

### 文件搜索命令

find [搜索范围] -name [匹配条件]

-iname 是不区分大小写

-size  +n/-n按文件大小搜索(1K对应着两个数据块，Linux按照数据块存储也按照数据快大小查找)

-user -grop 按照所有者/所属组查找



locate [] 这个的搜索速度很快，但是只能搜索到收录到文件库里的文件

updatedb 这个操作可以更新文件资料库

which  查询可执行命令

### 帮助命令

查看命令和配置文件的帮助信息

man [命令或者配置文件]

whatis []可以快速的查看一个命令的信息

apropos []可以快速查看配置文件信息

只想直到命令的选项可以使用在命令后面加上  --help

info  与man差不多  只是更新

help  查看shell内置命令例如cd命令 我们在系统中找不到路径 使用where cd  是notfind

### 用户管理命令

useradd [用户名] 添加用户

passwd 用户名   修改密码  root可以改所有人的   用户可以改自己的

who  显示登录的人 和登录时间还有主机的IP地址

w 可以得到比WHO更详细的信息

### 压缩解压命令

gzip  压缩目标文件为.gz（只能压缩文件）

gunzip（gzip -d） 解压目标压缩包

tar -v[zcf] [压缩后的文件名] [目录] 打包目录

-c打包

-v显示详细信息

-f指定文件名

-z压缩为gz

-j 压缩为bzip2

-zxf解压gz

-jxf解压bzip2

举例   tar -vcf test.tar test打包test为test.tar    tar -vzcf test.tar.gz test  打包并压缩test    

tar -zxf test.tar.gz解压



zip 压缩并保留源文件 -r则可以压缩目录

unzip 解压缩



bzip2 压缩为bzip2压缩文件  -k  可以保留源文件

bunzip2解压



### 网络命令

write可以给其他已登录用户发信息ctrl+d结束输入

wall 可以给所有用户发信息

ping [ip] 看看能不能ping通 crtl+c终止否则一直ping

ifconfig查看网络

mail 发送邮件这样就可以给不在线的用户发

last  查看登录用户的信息

lastlog可以看的更全 -u  则可以查看指定用户的登录信息

traceroute 显示数据包到主机间的路径

netstat 查询网络状态 -t -u -l -r -n

setup 

mount 挂载命令

### 关机重启命令

shutdown 关机命令  -h关机 [时间或者now] 指定时间关机 -c 取消前一个命令   -r重启

## 文本编辑器vim

### vim常用操作

vi filename 进入命令模式

输入i a o进入插入模式  **a在光标所在字符后插入 A在光标所在行尾部插入 i在光标坐在字符前插入 I在										光标所在行头部插入  o在光标下一行插入 O在光标上一行插入**

​										**X删除光标所在处的字符，dd删除所在行，yy复制当前行 p黏贴在当前光标一									下，R进入替换模式ESC退出，u恢复上一步操作  /*可以查找指定的字符关											键字**

esc退回到命令模式

输入:进入编辑模式

:set nu 设置行号

:set nonu 取消行号

​	:n 到第N行

​	gg 到第一行 G到最后一行 nG到第N行

​	：w保存修改

：q！不保存就退出

:wq保存并退出命令模式

### vim使用技巧

：r 文件名  可以导入一个文件到当前的光标后边

：！可以在不退出文件的情况下执行命令

## 软件包管理

### RMP命令管理

rpm命名规则：

**软件包名-2.2.15(版本)-15(发布次数).el6.centos(适合的Linux平台).i686(适合的硬件平台).rpm(rpm包扩展名)**

没有安装的软件包用包全名

已经安装好的包用包名

#### rpm命令管理

RPM安装：

rpm -ivh 包全名  --nodeps 加上这个可以不检测依赖性，最好不用

 		-i 安装

​		 -v 显示详细信息（和使用tar命令时的-v一个意思）

​		 -h 显示安装进度

RPM升级：

rpm -Uvh 包全名

​			-U升级

RPM卸载：

rpm -e 包名

RPM查询：(yum的查询命令不会查询到很多信息所有rpm的查询命令还是很常用的)

rpm -q 包名  

​         -qa 这样可以查询所有的已安装的rpm包

​		-qi(p) 包名  查看这个包的信息 加上p可以查看未安装包的相关信息

​         -ql  包名  查看安装的地址

#### yum在线管理

rpm命令由于rpm包的树形依赖性和环形依赖性导致安装和卸载都极其不方便所有诞生了yum这种通过在线的rpm依赖网站的可以自动管理依赖的命令

**网络yum源** ：我们的源都存在 /etc/yum.rpos.d/目录下  后缀为.erpo的都是合法的yum源

​						其中的CentOS-Base.repo中有一些基本设置可以去更改

**yum list 可以查看到所有的可用的软件包**

**yum  search 关键字  可以查询指定的可用软件包**

**yum**  -**y** **install** **包名**  **这样可以安装指定的软件保**

**yum -y update 包名  升级指定的软件包**

**yum -y remove 包名 卸载指定的包  也会卸载依赖包，慎用 可能会卸载掉其他包所依赖的包**

**服务器安装最小化安装不安装多余软件，尽量不卸载yum卸载最好不用**

**yum  grouplist 查看软件包组**

光盘yum源搭建

### 源码包管理

### 脚本安装包与软件包的选择



## 用户与用户组管理

### 用户配置文件

**etc/passwd文件**

## 权限管理-ACL权限

## 文件系统管理

# 防火墙命令

centos7的防火墙命令有些许改变

## 1、firewalld的基本使用

启动： systemctl start firewalld

查看状态： systemctl status firewalld 

禁用，禁止开机启动： systemctl disable firewalld

停止： systemctl stop firewalld

## 2.配置firewalld-cmd

查看版本： firewall-cmd --version

查看帮助： firewall-cmd --help

显示状态： firewall-cmd --state

查看所有打开的端口： firewall-cmd --zone=public --list-ports

更新防火墙规则： firewall-cmd --reload

更新防火墙规则，重启服务： firewall-cmd --completely-reload

查看已激活的Zone信息:  firewall-cmd --get-active-zones

查看指定接口所属区域： firewall-cmd --get-zone-of-interface=eth0

拒绝所有包：firewall-cmd --panic-on

取消拒绝状态： firewall-cmd --panic-off

查看是否拒绝： firewall-cmd --query-panic

 

 

## 3.信任级别，通过Zone的值指定

 

drop: 丢弃所有进入的包，而不给出任何响应 
block: 拒绝所有外部发起的连接，允许内部发起的连接 
public: 允许指定的进入连接 
external: 同上，对伪装的进入连接，一般用于路由转发 
dmz: 允许受限制的进入连接 
work: 允许受信任的计算机被限制的进入连接，类似 workgroup 
home: 同上，类似 homegroup 
internal: 同上，范围针对所有互联网用户 
trusted: 信任所有连接

 

## 4.firewall开启和关闭端口

 

**以下都是指在public的zone下的操作，不同的Zone只要改变Zone后面的值就可以**

**添加：**

firewall-cmd --zone=public --add-port=80/tcp --permanent    （--permanent永久生效，没有此参数重启后失效）

**重新载入：**

firewall-cmd --reload

**查看：**

firewall-cmd --zone=public --query-port=80/tcp

**删除：**

firewall-cmd --zone=public --remove-port=80/tcp --permanent

 

## 5.**管理服务**

**以smtp服务为例， 添加到work zone**

**添加：**

firewall-cmd --zone=work --add-service=smtp

**查看：**

firewall-cmd --zone=work --query-service=smtp

**删除：**

firewall-cmd --zone=work --remove-service=smtp

 

## 5.配置 IP 地址伪装

**查看：**

firewall-cmd --zone=external --query-masquerade

**打开：**

firewall-cmd --zone=external --add-masquerade

**关闭：**

firewall-cmd --zone=external --remove-masquerade

## 6.端口转发

**打开端口转发，首先需要打开IP地址伪装**

　　**firewall-cmd --zone=external --add-masquerade**

 

**转发 tcp 22 端口至 3753：**

firewall-cmd --zone=external --add-forward-port=22:porto=tcp:toport=3753

**转发端口数据至另一个IP的相同端口：**

firewall-cmd --zone=external --add-forward-port=22:porto=tcp:toaddr=192.168.1.112

**转发端口数据至另一个IP的 3753 端口：**

firewall-cmd --zone=external --add-forward-port=22:porto=tcp:：toport=3753:toaddr=192.168.1.112

 

 

## 6.systemctl是CentOS7的服务管理工具中主要的工具，它融合之前service和chkconfig的功能于一体。

 

启动一个服务：systemctl start firewalld.service
关闭一个服务：systemctl stop firewalld.service
重启一个服务：systemctl restart firewalld.service
显示一个服务的状态：systemctl status firewalld.service
在开机时启用一个服务：systemctl enable firewalld.service
在开机时禁用一个服务：systemctl disable firewalld.service
查看服务是否开机启动：systemctl is-enabled firewalld.service
查看已启动的服务列表：systemctl list-unit-files|grep enabled
查看启动失败的服务列表：systemctl --failed 

```

```


