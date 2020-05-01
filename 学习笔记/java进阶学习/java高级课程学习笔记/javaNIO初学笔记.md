| bio          | nio            |
| :----------- | -------------- |
| 单向的（流） | 双向的（通道） |
| 面向流的     | 面向缓冲区     |
| 阻塞         | 非阻塞         |
| 无           | 选择器         |

对于我们之前所学的bio就是通过流的io操作他总是在每次要进行数据流动时建立一个输入流一个输出流而且这个流在没有数据的时候就会阻塞程序，也就是说会使得程序代码无法进行下去我们可以通过前端页面中的异步来理解。在前端异步通信发生时前端的代码并不会应为这个异步通讯还没有结束就一直等在那里不向下进行。而是跳过这个异步执行下面的代码当异步执行完毕有了返回时就跳回到异步那边再去执行。而流就恰恰相反如果这个流传输一天都不能停，那么这一天以内流下面哪一行代码就没得执行。这一点我们可以通过虚拟机栈执行方法来理解一下，虚拟机栈执行一个方法会在栈中开辟一个栈帧存储本地变量和操作数，而方法具体的执行步骤是要到方法区中的类信息中去找并严格按照类信息中的执行步骤向下走，当执行到了流那就等着这个执行结束才会继续向下走。

nio则是为了解决这个问题而产的新io操作api它不仅不在是单纯的依靠流并且将这个nio放入了新的java.nio类中不是之前的io着可以看出他们之间一定是有所区别的新io不同于之前的io他是通过建立一个channel并搭配着buffer区来进行数据通信的，只需要一个管道channel并将数据放于buffer中再通过buffer来传输数据

#  缓冲区的数据存区

```java
import java.nio.ByteBuffer;

/**
 * buffer缓冲区负责在Javaio中进行数据的存取，缓冲区就是各种数据类型数组
 * 根据数据的类型不同提供了不同类型的缓冲区（Boolean除外其他的基本类型）
 *
 *存取数据的核心方法
 * put get
 *
 * 缓冲区的核心属性
 *     // 这四个属性是继承自buffer类的是所有的buffer都具备的属性他设定了buffer的基本属性
 *     private int mark = -1; 书签位置，记录的一个位置可以直接将position回到这里
 *     private int position = 0;    当前所在位置，正在操做的位置
 *
 *     private int limit;   限制表示缓冲区中可以操作数据的大小
 *                          也就是limit后面的数据是无法操作的
 *     private int capacity;  容量我们通过allocate设置的就是这
 *     个缓冲区的capacity大小这个属性是无法改变的应为底层毕竟是数组
 */
public class testt {
    public static void main(String[] args) {
        //1.分配一个指定大小的缓冲区 position在底部随着加入会不断向上
        //limit在最顶部
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        //2。存数据
        System.out.println(String.format("position: %s limit: %s",buffer.position(),buffer.limit()));
        buffer.put((byte) 11);
        buffer.put("abcde".getBytes());
        System.out.println(String.format("position: %s limit: %s",buffer.position(),buffer.limit()));
        //切换到读数据的状态
        //3.取数据
        buffer.flip();
        //当执行了这个方法时position就会回到数据组的底部，并且limit会回到缓冲区存储的数据量的位置
        //将mark设置为这里
        buffer.mark();
        System.out.println( buffer.get());
        System.out.println(String.format("position: %s limit: %s",buffer.position(),buffer.limit()));
        //4.继续重头读
        //通过方法回到读模式
        buffer.rewind();
        System.out.println(String.format("position: %s limit: %s",buffer.position(),buffer.limit()));
        //假清空，只是将几个属性重置了
        buffer.clear();
        System.out.println(String.format("position: %s limit: %s",buffer.position(),buffer.limit()));
    }
}

```

# 直接缓冲区和非直接缓冲区

也可以说时堆内缓冲区和堆外缓冲区

allocate设置的是将缓冲区建立在堆中也就是jvm的内存中而ByteBuffer.allocateDirect()这个方法是将缓冲区建立在内存中是在堆外可以增加效率但是需要我们自己记得关闭和释放这些内存

# 通道java.nio.channel

![通道内存图](image\通道内存图.PNG)

```Java
import sun.dc.pr.PathStroker;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 通道channel用于源节点与目标节点的一个连接
 * 在nio中负责缓冲区中数据的传输，channel本身是不存储数据的要配合缓冲区完成传输
 * 主要实现类
 * java.nio.channel接口：
 *      1.filechannel  文件传输用于本地文件的传输
 *      2.sockerchannel  网络传输 tcp
 *      3.serversockerchannel  网络传输 tcp
 *      4.datagramchannel   网络传输  udp
 *
 * 获取通道
 * 1. getchannel（）方法获取通道
 * 2.jdk1.7中nio2提供了静态方法针对各个通道提供了open(方法
 * 3.jdk1.7 nio2的一个FILES工具类的newByteChannel方法
 *
 * 通道之间的数据传输
 * transferfrom()
 * transferto()
 *
 */
public class testt {
    public static void main(String[] args) throws IOException {
        //1方法
//        FileInputStream fileInputStream = new FileInputStream("F:\\新建文件夹\\test.PNG");
//        FileOutputStream fileOutputStream = new FileOutputStream("F:\\新建文件夹\\test2.PNG");
//        FileChannel channel = fileInputStream.getChannel();
//        FileChannel channel1 = fileOutputStream.getChannel();
//        ByteBuffer buf = ByteBuffer.allocate(1024);
//
//        while (channel.read(buf)!=-1){
//            buf.flip();
//            channel1.write(buf);
//            buf.clear();
//        }
//        channel.close();
//        channel1.close();
//        fileInputStream.close();
//        fileOutputStream.close();
        //2方法
//        FileChannel open = FileChannel.open(Paths.get("F:\\新建文件夹\\test.PNG"), StandardOpenOption.READ);
//        FileChannel fileChannel = FileChannel.open(Paths.get("F:\\新建文件夹\\test2.PNG"), StandardOpenOption.WRITE,StandardOpenOption.READ);
//        //直接在通道中传输
//        open.transferTo(0,open.size(),fileChannel);
//        open.close();
//        fileChannel.close();


    }
}

```

# 分散与聚集

 * 分散读取：将通道中的数据分散到多个缓冲区
 * 聚集写入：将多个缓冲区中的数据聚集到一个通道中

```java
import sun.dc.pr.PathStroker;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 分散与聚集
 * 分散读取（scattering Readers）：将通道中的数据分散到多个缓冲区
 * 聚集写入（gathering Writes）：将多个缓冲区中的数据聚集到一个通道中
 *
 */
public class testt {
    public static void main(String[] args) throws IOException {
        //分散读取
        FileInputStream inputStream = new FileInputStream("");
        FileChannel channel = inputStream.getChannel();
        ByteBuffer allocate = ByteBuffer.allocate(100);
        ByteBuffer allocate1 = ByteBuffer.allocate(1024);
        ByteBuffer[] buffers = {allocate,allocate1};
        channel.read(buffers);
        channel.close();
        inputStream.close();
        //聚集写入同理


    }
}

```

**字符集**

编码

解码

```java
public class testt {
    public static void main(String[] args) throws IOException {
        SortedMap<String, Charset> stringCharsetSortedMap =
                Charset.availableCharsets();
        Set<Map.Entry<String, Charset>> entries =
                stringCharsetSortedMap.entrySet();
        Iterator<Map.Entry<String, Charset>> iterator = entries.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        Charset charset = Charset.forName("UTF-8");
        CharsetEncoder charsetEncoder = charset.newEncoder();
        CharsetDecoder charsetDecoder = charset.newDecoder();
        CharBuffer buffer = CharBuffer.allocate(1024);
        buffer.put("tests");
        buffer.flip();
        charsetEncoder.encode(buffer);

```

# nio阻塞式与非阻塞网络通信

![nio非阻塞模式](image\nio非阻塞模式.PNG)

nio的阻塞式

```java
import org.junit.Test;
import sun.dc.pr.PathStroker;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

/**
 *使用nio完成网络通信的核心
 * 1。通道channel：负责链接
 *          ----socketChannel
 *          ----serverSocketChannel
 *          ----DatagramChannel
 *
 *          ----Pipe.SinkChannel
 *          ----Pipe.SourceChannel
 * 2.缓冲区buffer：负责数据的存取
 * 3.选择器selector：用于监控被注册的通道的状况
 */
public class testt {
   @Test
   public void client() throws IOException {
      SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1",9001));

      ByteBuffer buffer = ByteBuffer.allocate(1024);

      //读取本地文件并发送
      FileChannel open = FileChannel.open(Paths.get("F:\\新建文件夹\\test.PNG"), StandardOpenOption.READ);
      while (open.read(buffer)!=-1){
         buffer.flip();
         socketChannel.write(buffer);
         buffer.clear();
      }
      //关闭本地文件通道
      open.close();
      socketChannel.close();
   }

   @Test
   public void Server() throws IOException {
      ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
      //绑定端口号
      serverSocketChannel.bind(new InetSocketAddress(9001));
      //获取客户端的通道
      SocketChannel socketChannel = serverSocketChannel.accept();
      //读取数据并保存
      ByteBuffer buffer = ByteBuffer.allocate(1024);
      FileChannel open = FileChannel.open(Paths.get("F:\\新建文件夹\\test2.PNG"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
      while (socketChannel.read(buffer)!=-1){
         buffer.flip();
         open.write(buffer);
         buffer.clear();
      }
      open.close();
      socketChannel.close();
      serverSocketChannel.close();
   }
}

```

非阻塞式

```java
import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;
import java.util.Iterator;

/**
 *使用nio完成网络通信的核心
 * 1。通道channel：负责链接
 *          ----socketChannel
 *          ----serverSocketChannel
 *          ----DatagramChannel
 *
 *          ----Pipe.SinkChannel
 *          ----Pipe.SourceChannel
 * 2.缓冲区buffer：负责数据的存取
 * 3.选择器selector：用于监控被注册的通道的状况
 */
public class testt2 {
   @Test
   public void client() throws IOException {
      SocketChannel socketChannel = SocketChannel.open();
      socketChannel.connect(new InetSocketAddress("127.0.0.1",9001));
      //切换为非阻塞模式
      socketChannel.configureBlocking(false);

      //缓冲区
      ByteBuffer buffer = ByteBuffer.allocate(1024);
      //发送数据给服务端
      buffer.put(new Date().toString().getBytes());
      buffer.flip();
      socketChannel.write(buffer);
      buffer.clear();

      //关闭
      socketChannel.close();



   }

   @Test
   public void Server() throws IOException {
      //获取客户端通道
      ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
      //设置非阻塞
      serverSocketChannel.configureBlocking(false);
      //绑定端口号
      serverSocketChannel.bind(new InetSocketAddress(9001));
      //获取选择器
      Selector selector = Selector.open();
      //将通道注册到选择器,并指定监听事件
      serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
      //通过选择器轮询获取选择器上以及准备就绪的事件
      while (selector.select()>0){
         //获取所有以及满足上面设置的状态的注册事件
         Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
         while (iterator.hasNext()){
            SelectionKey selectionKey = iterator.next();
            //获取准备就绪的事件
            if (selectionKey.isAcceptable()){
               SocketChannel socketChannel = serverSocketChannel.accept();
               //切换非阻塞模式
               socketChannel.configureBlocking(false);
               //将该通道注册到选择器
               socketChannel.register(selector,SelectionKey.OP_READ);
            }else if (selectionKey.isReadable()){
               //获取都就绪状态的通道
               SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
               //读取数据
               ByteBuffer buffer = ByteBuffer.allocate(1024);
               int len = 0;
               while ((len = socketChannel.read(buffer))>0){
                  buffer.flip();
                  System.out.println(new String(buffer.array(),0,len));
                  buffer.clear();
               }
            }
//            //取消选择键
//            iterator.remove();
         }
      }



      serverSocketChannel.close();
   }
}

```

