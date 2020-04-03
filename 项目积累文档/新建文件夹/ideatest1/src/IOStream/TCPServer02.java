package IOStream;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服務器拿到網絡流傳輸的文件
 * 通過本地流寫入磁盤
 * 調用網絡輸出流返回成功信息
 */
public class TCPServer02 {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        Socket socket = serverSocket.accept();
        File file = new File("C:\\Users\\ASUS\\Desktop\\新建文件夹2");
        if(!file.exists()){
            file.mkdirs();
        }


            InputStream inputStream = socket.getInputStream();
            FileOutputStream fos = new FileOutputStream(file+"\\test2.jpg");
            int i =0;
            byte[] bytes1 = new byte[1024];
            while ((i=inputStream.read(bytes1))!=-1){
                fos.write(bytes1,0,i);
            }
            System.out.println("2222222");
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write("成功接收".getBytes());
            fos.close();
            socket.close();
            serverSocket.close();


    }

}
