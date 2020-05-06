# tcpdump常用命令

## 简单使用例子‘

查看网卡

sudo tcpdump -D


1.en0 [Up, Running]
2.p2p0 [Up, Running]
3.awdl0 [Up, Running]
4.llw0 [Up, Running]
5.utun0 [Up, Running]
6.en1 [Up, Running]
7.utun1 [Up, Running]
8.en2 [Up, Running]
9.utun2 [Up, Running]
10.en3 [Up, Running]

11.en4 [Up, Running]
12.en8 [Up, Running]
13.lo0 [Up, Running, Loopback]
14.gif0
15.stf0
16.bridge0
17.ap1

 

监听 报文

 

tcpdump -i 2 host 1.1.11.3 and udp port 5066

 

-i 指的是监听第2块网卡

host 指的是要监听的ip

udp 指的是监听udp报文

port 指的是监听指定的端口

 





## TCPdump抓包命令 

tcpdump是一个用于截取网络分组，并输出分组内容的工具。tcpdump凭借强大的功能和灵活的截取策略，使其成为类UNIX系统下用于网络分析和问题排查的首选工具。 
tcpdump提供了源代码，公开了接口，因此具备很强的可扩展性，对于网络维护和***者都是非常有用的工具。tcpdump存在于基本的Linux系统中，由于它需要将网络界面设置为混杂模式，普通用户不能正常执行，但具备root权限的用户可以直接执行它来获取网络上的信息。因此系统中存在网络分析工具主要不是对本机安全的威胁，而是对网络上的其他计算机的安全存在威胁。



### **一、概述**

\****顾名思义，tcpdump可以将网络中传送的数据包的“头”完全截获下来提供分析。它支持针对网络层、协议、主机、网络或端口的过滤，并提供and、or、not等逻辑语句来帮助你去掉无用的信息。
引用
\# tcpdump -vv
tcpdump: listening on eth0, link-type EN10MB (Ethernet), capture size 96 bytes
11:53:21.444591 IP (tos 0x10, ttl 64, id 19324, offset 0, flags [DF], proto 6,length: 92) asptest.localdomain.ssh > 192.168.228.244.1858: P3962132600:3962132652(52) ack 2726525936 win 1266
asptest.localdomain.1077 > 192.168.228.153.domain: [bad udp cksum 166e!]325+ PTR? 244.228.168.192.in-addr.arpa. (46)
11:53:21.446929 IP (tos 0x0, ttl 64, id 42911, offset 0, flags [DF], proto 17,length: 151) 192.168.228.153.domain > asptest.localdomain.1077: 325 NXDomainq: PTR? 244.228.168.192.in-addr.arpa. 0/1/0 ns: 168.192.in-addr.arpa. (123)
11:53:21.447408 IP (tos 0x10, ttl 64, id 19328, offset 0, flags [DF], proto 6,length: 172) asptest.localdomain.ssh > 192.168.228.244.1858: P 168:300(132)ack 1 win 1266
347 packets captured
1474 packets received by filter
745 packets dropped by kernel
不带参数的tcpdump会收集网络中所有的信息包头，数据量巨大，必须过滤。



### **二、选项介绍**

引用
-A 以ASCII格式打印出所有分组，并将链路层的头最小化。 
-c 在收到指定的数量的分组后，tcpdump就会停止。 
-C 在将一个原始分组写入文件之前，检查文件当前的大小是否超过了参数file_size 中指定的大小。如果超过了指定大小，则关闭当前文件，然后在打开一个新的文件。参数 file_size 的单位是兆字节（是1,000,000字节，而不是1,048,576字节）。 
-d 将匹配信息包的代码以人们能够理解的汇编格式给出。 
-dd 将匹配信息包的代码以c语言程序段的格式给出。 
-ddd 将匹配信息包的代码以十进制的形式给出。 
-D 打印出系统中所有可以用tcpdump截包的网络接口。 
-e 在输出行打印出数据链路层的头部信息。 
-E 用spi@ipaddr algo:secret解密那些以addr作为地址，并且包含了安全参数索引值spi的IPsec ESP分组。 
-f 将外部的Internet地址以数字的形式打印出来。 
-F 从指定的文件中读取表达式，忽略命令行中给出的表达式。 
-i 指定监听的网络接口。 
-l 使标准输出变为缓冲行形式，可以把数据导出到文件。 
-L 列出网络接口的已知数据链路。 
-m 从文件module中导入SMI MIB模块定义。该参数可以被使用多次，以导入多个MIB模块。 
-M 如果tcp报文中存在TCP-MD5选项，则需要用secret作为共享的验证码用于验证TCP-MD5选选项摘要（详情可参考RFC 2385）。 
-b 在数据-链路层上选择协议，包括ip、arp、rarp、ipx都是这一层的。
-n 不把网络地址转换成名字。
-nn 不进行端口名称的转换。
-N 不输出主机名中的域名部分。例如，‘nic.ddn.mil‘只输出’nic‘。 
-t 在输出的每一行不打印时间戳。 
-O 不运行分组分组匹配（packet-matching）代码优化程序。 
-P 不将网络接口设置成混杂模式。 
-q 快速输出。只输出较少的协议信息。 
-r 从指定的文件中读取包(这些包一般通过-w选项产生)。 
-S 将tcp的序列号以绝对值形式输出，而不是相对值。 
-s 从每个分组中读取最开始的snaplen个字节，而不是默认的68个字节。 
-T 将监听到的包直接解释为指定的类型的报文，常见的类型有rpc远程过程调用）和snmp（简单网络管理协议；）。 
-t 不在每一行中输出时间戳。 
-tt 在每一行中输出非格式化的时间戳。 
-ttt 输出本行和前面一行之间的时间差。 
-tttt 在每一行中输出由date处理的默认格式的时间戳。 
-u 输出未解码的NFS句柄。 
-v 输出一个稍微详细的信息，例如在ip包中可以包括ttl和服务类型的信息。 
-vv 输出详细的报文信息。 
-w 直接将分组写入文件中，而不是不分析并打印出来。



### 三，tcpdump的表达式介绍

表达式是一个正则表达式，tcpdump利用它作为过滤报文的条件，如果一个报文满足表达式的条件，则这个报文将会被捕获。如果没有给出任何条件，则网络上所有的信息包将会被截获。 
在表达式中一般如下几种类型的关键字： 
引用
***\*第一种是关于类型的关键字，\****主要包括host，net，port，例如 host 210.27.48.2，指明 210.27.48.2是一台主机，net 202.0.0.0指明202.0.0.0是一个网络地址，port 23 指明端口号是23。如果没有指定类型，缺省的类型是host。 
***\*第二种是确定传输方向的关键字，\****主要包括src，dst，dst or src，dst and src，这些关键字指明了传输的方向。举例说明，src 210.27.48.2 ，指明ip包中源地址是 210.27.48.2 ， dst net 202.0.0.0 指明目的网络地址是202.0.0.0。如果没有指明方向关键字，则缺省是src or dst关键字。 
***\*第三种是协议的关键字，\****主要包括fddi，ip，arp，rarp，tcp，udp等类型。Fddi指明是在FDDI (分布式光纤数据接口网络)上的特定的网络协议，实际上它是”ether”的别名，fddi和ether 具有类似的源地址和目的地址，所以可以将fddi协议包当作ether的包进行处理和分析。其他的几个关键字就是指明了监听的包的协议内容。如果没有指定任何协议，则tcpdump 将会监听所有协议的信息包。

除了这三种类型的关键字之外，其他重要的关键字如下：gateway， broadcast，less， greater，还有三种逻辑运算，取非运算是 ‘not ' '! ‘，与运算是’and’，’&&';或运算是’or’ ，’&#124;&#124;’；这些关键字可以组合起来构成强大的组合条件来满足人们的需要。

### ***\*四、输出结果介绍\****

下面我们介绍几种典型的tcpdump命令的输出信息 
**(1)** ***\*数据链路层头信息\**** ***\*
\****使用命令： 
\#tcpdump --e host ICE
ICE 是一台装有linux的主机。它的MAC地址是0：90：27：58：AF：1A H219是一台装有Solaris的SUN工作站。它的MAC地址是8：0：20：79：5B：46；上一条命令的输出结果如下所示：
引用
21:50:12.847509 eth0 < 8:0:20:79:5b:46 0:90:27:58:af:1a ip 60: h219.33357> ICE. telne t 0:0(0) ack 22535 win 8760 (DF)
21：50：12是显示的时间， 847509是ID号，eth0 <表示从网络接口eth0接收该分组， eth0 >表示从网络接口设备发送分组， 8:0:20:79:5b:46是主机H219的MAC地址，它表明是从源地址H219发来的分组. 0:90:27:58:af:1a是主机ICE的MAC地址，表示该分组的目的地址是ICE。 ip 是表明该分组是IP分组，60 是分组的长度， h219.33357 > ICE. telnet 表明该分组是从主机H219的33357端口发往主机ICE的 TELNET(23)端口。 ack 22535 表明对序列号是222535的包进行响应。 win 8760表明发送窗口的大小是8760。 
**(2) ARP*****\*包的\*******\*tcpdump\*******\*输出信息\**** 
使用命令： 
\#tcpdump arp
得到的输出结果是：
引用
22:32:42.802509 eth0 > arp who-has route tell ICE (0:90:27:58:af:1a)
22:32:42.802902 eth0 < arp reply route is-at 0:90:27:12:10:66(0:90:27:58:af:1a)
22:32:42是时间戳， 802509是ID号， eth0 >表明从主机发出该分组，arp表明是ARP请求包， who-has route tell ICE表明是主机ICE请求主机route的MAC地址。 0:90:27:58:af:1a是主机 ICE的MAC地址。
**(3) TCP*****\*包的输出信息\**** 
用tcpdump捕获的TCP包的一般输出信息是： 
引用
src > dst: flags data-seqno ack window urgent options
src > dst:表明从源地址到目的地址， flags是TCP报文中的标志信息，S 是SYN标志， F (FIN)， P (PUSH) ， R (RST) "." (没有标记); data-seqno是报文中的数据的顺序号， ack是下次期望的顺序号，window是接收缓存的窗口大小， urgent表明报文中是否有紧急指针。 Options是选项。 
**(4) UDP*****\*包的输出信息\****
用tcpdump捕获的UDP包的一般输出信息是： 
引用
route.port1 > ICE.port2: udp lenth
UDP十分简单，上面的输出行表明从主机route的port1端口发出的一个UDP报文到主机ICE的port2端口，类型是UDP，包的长度是lenth。 

### ***\*五、举例\****

(1) 想要截获所有210.27.48.1 的主机收到的和发出的所有的分组： 
\#tcpdump host 210.27.48.1 
(2) 想要截获主机210.27.48.1 和主机210.27.48.2或210.27.48.3的通信，使用命令（注意：括号前的反斜杠是必须的）： 
\#tcpdump host 210.27.48.1 and 210.27.48.2or210.27.48.3
(3) 如果想要获取主机210.27.48.1除了和主机210.27.48.2之外所有主机通信的ip包，使用命令： 
\#tcpdump ip host 210.27.48.1 and ! 210.27.48.2
(4) 如果想要获取主机192.168.228.246接收或发出的ssh包，并且不转换主机名使用如下命令： 
\#tcpdump -nn -n src host 192.168.228.246 and port 22 and tcp
(5) 获取主机192.168.228.246接收或发出的ssh包，并把mac地址也一同显示：
\# tcpdump -e src host 192.168.228.246 and port 22 and tcp -n -nn
(6) 过滤的是源主机为192.168.0.1与目的网络为192.168.0.0的报头：
tcpdump src host 192.168.0.1 and dst net 192.168.0.0/24 
(7) 过滤源主机物理地址为XXX的报头：
tcpdump ether src 00:50:04:BA:9B and dst……
（为什么ether src后面没有host或者net？物理地址当然不可能有网络喽）。 
(8) 过滤源主机192.168.0.1和目的端口不是telnet的报头，并导入到tes.t.txt文件中：
Tcpdump src host 192.168.0.1 and dst port not telnet -l > test.txt
ip icmp arp rarp 和 tcp、udp、icmp这些选项等都要放到第一个参数的位置，用来过滤数据报的类型。

tcpdump采用命令行方式，它的命令格式为：
tcpdump [-nn] [-i 接口] [-w 储存档名] [-c 次数] [-Ae]
            [-qX] [-r 文件] [所欲捕获的数据内容]
参数：
-nn，直接以 IP 及 Port Number 显示，而非主机名与服务名称。
-i，后面接要「监听」的网络接口，例如 eth0, lo, ppp0 等等的接口。
-w，如果你要将监听所得的数据包数据储存下来，用这个参数就对了。后面接文件名。
-c，监听的数据包数，如果没有这个参数， tcpdump 会持续不断的监听，
   直到用户输入 [ctrl]-c 为止。
-A，数据包的内容以 ASCII 显示，通常用来捉取 WWW 的网页数据包资料。
-e，使用资料连接层 (OSI 第二层) 的 MAC 数据包数据来显示。
-q，仅列出较为简短的数据包信息，每一行的内容比较精简。
-X，可以列出十六进制 (hex) 以及 ASCII 的数据包内容，对于监听数据包内容很有用。
-r，从后面接的文件将数据包数据读出来。那个「文件」是已经存在的文件，
   并且这个「文件」是由 -w 所制作出来的。
所欲捕获的数据内容：我们可以专门针对某些通信协议或者是 IP 来源进行数据包捕获。
   那就可以简化输出的结果，并取得最有用的信息。常见的表示方法有。
  'host foo', 'host 127.0.0.1' ：针对单台主机来进行数据包捕获。
   'net 192.168' ：针对某个网段来进行数据包的捕获。
   'src host 127.0.0.1' 'dst net 192.168'：同时加上来源(src)或目标(dst)限制。
   'tcp port 21'：还可以针对通信协议检测，如tcp、udp、arp、ether 等。
   除了这三种类型的关键字之外，其他重要的关键字如下：gateway, broadcast,less,
greater,还有三种逻辑运算，取非运算是 'not ' '! ', 与运算是'and','&&';或运算是'o
r' ,'||'；


范例一：以 IP 与 Port Number 捉下 eth0 这个网卡上的数据包，持续 3 秒
[root@linux ~]# tcpdump -i eth0 -nn
tcpdump: verbose output suppressed, use -v or -vv for full protocol decode
listening on eth0, link-type EN10MB (Ethernet), capture size 96 bytes
01:33:40.41 IP 192.168.1.100.22 > 192.168.1.11.1190: P 116:232(116) ack 1win 
9648
01:33:40.41 IP 192.168.1.100.22 > 192.168.1.11.1190: P 232:364(132) ack 1win 
9648
<==按下 [ctrl]-c 之后结束
6680 packetscaptured       <==捉取下来的数据包数量
14250 packets received by filter  <==由过滤所得的总数据包数量
7512 packets dropped by kernel   <==被核心所丢弃的数据包
至于那个在范例一所产生的输出中，我们可以大概区分为几个字段，现以范例一当中那行特殊字体行来说明一下：

· 01:33:40.41：这个是此数据包被捕获的时间，“时:分:秒”的单位。

· IP：通过的通信协议是IP。

·192.168.1.100.22>：传送端是192.168.1.100这个IP，而传送的Port Number为22，那个大于（>）的符号指的是数据包的传输方向。

·192.168.1.11.1190：接收端的IP是192.168.1.11，且该主机开启port 1190来接收。

· P 116:232(116)：这个数据包带有PUSH的数据传输标志，且传输的数据为整体数据的116~232 Byte，所以这个数据包带有116 Bytes的数据量。

· ack 1 win 9648：ACK与Window size的相关资料。

最简单的说法，就是该数据包是由192.168.1.100传到192.168.1.11，通过的port是由22到1190，且带有116 Bytes的数据量，使用的是PUSH的标记，而不是SYN之类的主动联机标志。

接下来，在一个网络状态很忙的主机上面，你想要取得某台主机对你联机的数据包数据时，使用tcpdump配合管线命令与正则表达式也可以，不过，毕竟不好捕获。我们可以通过tcpdump的表达式功能，就能够轻易地将所需要的数据独立的取出来。在上面的范例一当中，我们仅针对eth0做监听，所以整个eth0接口上面的数据都会被显示到屏幕上，但这样不好分析，可以简化吗？例如，只取出port 21的联机数据包，可以这样做：

[root@linux ~]# tcpdump -i eth0 -nn port  21 tcpdump: verbose output suppressed, use -v or -vv for full protocol decode  listening on eth0, link-type EN10MB (Ethernet), capture size 96 bytes  01:54:37.96 IP 192.168.1.11.1240 > 192.168.1.100.21:. ack 1 win 65535  01:54:37.96 IP 192.168.1.100.21 > 192.168.1.11.1240:P 1:21(20) ack 1 win  5840 01:54:38.12 IP 192.168.1.11.1240 > 192.168.1.100.21:. ack 21 win  65515 01:54:42.79 IP 192.168.1.11.1240 > 192.168.1.100.21:P 1:17(16) ack  21 win 65515 01:54:42.79 IP 192.168.1.100.21 > 192.168.1.11.1240: . ack 17  win 5840 01:54:42.79 IP 192.168.1.100.21 > 192.168.1.11.1240: P 21:55(34)  ack 17 win 5840

看！这样就仅取出port 21的信息，如果仔细看的话，你会发现数据包的传递都是双向的，Client端发出请求而Server端则予以响应，所以，当然是有去有回了。而我们也就可以经过这个数据包的流向来了解到数据包运动的过程了。例如：

· 我们先在一个终端机窗口输入“tcpdump-i lo-nn”的监听。

· 再另开一个终端机窗口来对本机（127.0.0.1）登录“ssh localhost”，那么输出的结果会是如何？

[root@linux ~]# tcpdump -i lo -nn 1  tcpdump: verbose output suppressed, use -v or -vv for full protocol decode 2  listening on lo, link-type EN10MB (Ethernet), capture size 96 bytes 3  11:02:54.253777 IP 127.0.0.1.32936 > 127.0.0.1.22: S  933696132:933696132(0) win 32767 4 11:02:54.253831 IP 127.0.0.1.22 >  127.0.0.1.32936: S 920046702:920046702(0) ack 933696133 win 32767 5  11:02:54.253871 IP 127.0.0.1.32936 > 127.0.0.1.22: . ack 1 win 8192 6  11:02:54.272124 IP 127.0.0.1.22 > 127.0.0.1.32936: P 1:23(22) ack 1 win  8192 7 11:02:54.272375 IP 127.0.0.1.32936 > 127.0.0.1.22: . ack 23 win  8192

代码显示的头两行是tcpdump的基本说明，然后：

· 第3行显示的是来自Client端带有SYN主动联机的数据包。 
· 第4行显示的是来自Server端，除了响应Client端之外（ACK），还带有SYN主动联机的标志。 
· 第5行则显示Client端响应Server确定联机建立（ACK）。
· 第6行以后则开始进入数据传输的步骤。

从第3~5行的流程来看，熟不熟悉啊？没错。那就是3次握手的基础流程，有趣吧。不过tcpdump之所以被称为***软件之一远不止上面介绍的功能。上面介绍的功能可以用来作为我们主机的数据包联机与传输的流程分析，这将有助于我们了解到数据包的运作，同时了解到主机的防火墙设置规则是否有需要修订的地方。

还有更神奇的用法。当我们使用tcpdump在Router上面监听明文的传输数据时，例如FTP传输协议，你觉得会发生什么问题呢？我们先在主机端执行“tcpdump -i lo port 21 -nn –X”，然后再以FTP登录本机，并输入账号与密码，结果你就可以发现如下的状况：

[root@linux ~]# tcpdump -i lo -nn -X  'port 21' 0x0000: 4500 0048 2a28 4000 4006 1286 7f00 0001 E..H*(@.@.......  0x0010: 7f00 0001 0015 80ab 8355 2149 835c d825 .........U!I.\.% 0x0020: 8018  2000 fe3c 0000 0101 080a 0e2e 0b67 .....<.........g 0x0030: 0e2e 0b61 3232  3020 2876 7346 5450 6420 ...a220.(vsFTPd. 0x0040: 322e 302e 3129 0d0a 2.0.1).  0x0000: 4510 0041 d34b 4000 4006 6959 7f00 0001 E..A.K@.@.iY.... 0x0010: 7f00  0001 80ab 0015 835c d825 8355 215d .........\.%.U!] 0x0020: 8018 2000 fe35  0000 0101 080a 0e2e 1b37 .....5.........7 0x0030: 0e2e 0b67 5553 4552 2064  6d74 7361 690d ...gUSER.dmtsai. 0x0040: 0a . 0x0000: 4510 004a d34f 4000 4006  694c 7f00 0001 E..J.O@.@.iL.... 0x0010 7f00 0001 80ab 0015 835c d832 8355  217f .........\.2.U!. 0x0020: 8018 2000 fe3e 0000 0101 080a 0e2e 3227  .....>........2' 0x0030: 0e2e 1b38 5041 5353 206d 7970 6173 7377  ...8PASS.mypassw 0x0040: 6f72 6469 7379 6f75 0d0a ordisyou..

上面的输出结果已经被简化过了，你需要自行在你的输出结果中搜索相关的字符串才行。从上面输出结果的特殊字体中，我们可以发现该FTP软件使用的是 vsFTPd，并且用户输入dmtsai这个账号名称，且密码是mypasswordisyou。如果使用的是明文方式来传输你的网络数据呢？

另外你得了解，为了让网络接口可以让tcpdump监听，所以执行tcpdump时网络接口会启动在“混杂模式（promiscuous）”，所以你会在 /var/log/messages里面看到很多的警告信息，通知你说你的网卡被设置成为混杂模式。别担心，那是正常的。至于更多的应用，请参考man tcpdump了。

 

例题：如何使用tcpdump监听来自eth0适配卡且通信协议为port 22，目标来源为192.168.1.100的数据包资料？答：tcpdump -i eth0 -nn 'port 22 and src host  192.168.1.100'。

\##############例子2#######################################

 

普通情况下，直接启动tcpdump将监视第一个网络界面上所有流过的数据包。
\# tcpdump
tcpdump: listening on fxp0
11:58:47.873028 202.102.245.40.netbios-ns > 202.102.245.127.netbios-ns: udp50
11:58:47.974331 0:10:7b:8:3a:56 > 1:80:c2:0:0:0 802.1d ui/C len=43
0000 0000 0080 0000 1007 cf08 0900 0000
0e80 0000 902b 4695 0980 8701 0014 0002
000f 0000 902b 4695 0008 00
11:58:48.373134 0:0:e8:5b:6d:85 > Broadcast sap e0 ui/C len=97
ffff 0060 0004 ffff ffff ffff ffff ffff
0452 ffff ffff 0000 e85b 6d85 4008 0002
0640 4d41 5354 4552 5f57 4542 0000 0000
0000 00
使用-i参数指定tcpdump监听的网络界面，这在计算机具有多个网络界面时非常有用，
使用-c参数指定要监听的数据包数量，
使用-w参数指定将监听到的数据包写入文件中保存

A想要截获所有210.27.48.1 的主机收到的和发出的所有的数据包：
\#tcpdump host 210.27.48.1

B想要截获主机210.27.48.1 和主机210.27.48.2 或210.27.48.3的通信，使用命令：（在命令行中适用　　　括号时，一定要
\#tcpdump host 210.27.48.1 and \ (210.27.48.2 or 210.27.48.3 \)

C如果想要获取主机210.27.48.1除了和主机210.27.48.2之外所有主机通信的ip包，使用命令：
\#tcpdump ip host 210.27.48.1 and ! 210.27.48.2

D如果想要获取主机210.27.48.1接收或发出的telnet包，使用如下命令：
\#tcpdump tcp port 23 host 210.27.48.1

E 对本机的udp 123 端口进行监视 123 为ntp的服务端口
\# tcpdump udp port 123

F 系统将只对名为hostname的主机的通信数据包进行监视。主机名可以是本地主机，也可以是网络上的任何一台计算机。下面的命令可以读取主机hostname发送的所有数据：
\#tcpdump -i eth0 src host hostname

G 下面的命令可以监视所有送到主机hostname的数据包：
\#tcpdump -i eth0 dst host hostname

H 我们还可以监视通过指定网关的数据包：
\#tcpdump -i eth0 gateway Gatewayname

I 如果你还想监视编址到指定端口的TCP或UDP数据包，那么执行以下命令：
\#tcpdump -i eth0 host hostname and port 80

J 如果想要获取主机210.27.48.1除了和主机210.27.48.2之外所有主机通信的ip包
，使用命令：
\#tcpdump ip host 210.27.48.1 and ! 210.27.48.2

K 想要截获主机210.27.48.1 和主机210.27.48.2 或210.27.48.3的通信，使用命令
：（在命令行中适用　　　括号时，一定要
\#tcpdump host 210.27.48.1 and \ (210.27.48.2 or 210.27.48.3 \)

L 如果想要获取主机210.27.48.1除了和主机210.27.48.2之外所有主机通信的ip包，使用命令：
\#tcpdump ip host 210.27.48.1 and ! 210.27.48.2

M 如果想要获取主机210.27.48.1接收或发出的telnet包，使用如下命令：
\#tcpdump tcp port 23 host 210.27.48.1

第三种是协议的关键字，主要包括fddi,ip ,arp,rarp,tcp,udp等类型
除了这三种类型的关键字之外，其他重要的关键字如下：gateway, broadcast,less,
greater,还有三种逻辑运算，取非运算是 ‘not ‘ ‘! ‘, 与运算是‘and‘,‘&&‘;或运算是‘o
r‘ ,‘||‘；
第二种是确定传输方向的关键字，主要包括src , dst ,dst or src, dst and src ,
如果我们只需要列出送到80端口的数据包，用dst port；如果我们只希望看到返回80端口的数据包，用src port。
\#tcpdump –i eth0 host hostname and dst port 80 目的端口是80
或者
\#tcpdump –i eth0 host hostname and src port 80 源端口是80 一般是提供http的服务的主机
如果条件很多的话要在条件之前加and 或 or 或 not
\#tcpdump -i eth0 host ! 211.161.223.70 and ! 211.161.223.71 and dst port 80

如果在ethernet 使用混杂模式系统的日志将会记录

May 7 20:03:46localhost kernel: eth0: Promiscuous mode enabled.
May 7 20:03:46 localhost kernel: device eth0 entered promiscuous mode
May 7 20:03:57 localhost kernel: device eth0 left promiscuous mode

tcpdump对截获的数据并没有进行彻底解码，数据包内的大部分内容是使用十六进制的形式直接打印输出的。显然这不利于分析网络故障，通常的解决办法是先使用带-w参数的tcpdump 截获数据并保存到文件中，然后再使用其他程序进行解码分析。当然也应该定义过滤规则，以避免捕获的数据包填满整个硬盘。

除了过滤语句，还有一个很重要的参数，也就是说，如果这个参数不设置正确，会导致包数据的丢失！

它就是-s 参数，snaplen, 也就是数据包的截取长度，仔细看man就会明白的！默认截取长度为60个字节，但一般ethernet MTU都是1500字节。所以，要抓取大于60字节的包时，使用默认参数就会导致包数据丢失！

只要使用-s 0就可以按包长，截取数据！

 

 

###  常用命令

下面的例子全是以抓取eth0接口为例，如果不加”-i eth0”是表示抓取所有的接口包括lo。

1、抓取包含10.10.10.122的数据包 
\# tcpdump -i eth0 -vnn host 10.10.10.122

2、抓取包含10.10.10.0/24网段的数据包
\# tcpdump -i eth0 -vnn net 10.10.10.0/24

3、抓取包含端口22的数据包
\# tcpdump -i eth0 -vnn port 22 

4、抓取udp协议的数据包
\# tcpdump -i eth0 -vnn udp

5、抓取icmp协议的数据包
\# tcpdump -i eth0 -vnn icmp

6、抓取arp协议的数据包
\# tcpdump -i eth0 -vnn arp
 
7、抓取ip协议的数据包
\# tcpdump -i eth0 -vnn ip
 
8、抓取源ip是10.10.10.122数据包。
\# tcpdump -i eth0 -vnn src host 10.10.10.122
 
9、抓取目的ip是10.10.10.122数据包
\# tcpdump -i eth0 -vnn dst host 10.10.10.122
 
10、抓取源端口是22的数据包
\# tcpdump -i eth0 -vnn src port 22
 
11、抓取源ip是10.10.10.253且目的ip是22的数据包
\# tcpdump -i eth0 -vnn src host 10.10.10.253 and dst port 22
         
12、抓取源ip是10.10.10.122或者包含端口是22的数据包
\# tcpdump -i eth0 -vnn src host 10.10.10.122 or port 22
 
13、抓取源ip是10.10.10.122且端口不是22的数据包
[root@ ftp]# tcpdump -i eth0 -vnn src host 10.10.10.122 and not port 22


14、抓取源ip是10.10.10.2且目的端口是22，或源ip是10.10.10.65且目的端口是80的数据包。
\# tcpdump -i eth0 -vnn srchost10.10.10.2anddstport22 or srchost10.10.10.65anddstport80

 

15、抓取源ip是10.10.10.59且目的端口是22，或源ip是10.10.10.68且目的端口是80的数据包。
[root@localhost ~]# tcpdump -i eth0 -vnn 'src host 10.10.10.59 and dstport 22' or ' src host 10.10.10.68 and dst port 80 '

 

16、把抓取的数据包记录存到/tmp/fill文件中，当抓取100个数据包后就退出程序。
\# tcpdump –i eth0 -vnn -w /tmp/fil1 -c 100

 

17、从/tmp/fill记录中读取tcp协议的数据包
\# tcpdump –i eth0 -vnn -r /tmp/fil1 tcp

 

18、从/tmp/fill记录中读取包含10.10.10.58的数据包
\# tcpdump –i eth0 -vnn -r /tmp/fil1 host 10.10.10.58