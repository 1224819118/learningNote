package IOStream;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 客戶端先調用本地輸入流讀取文件
 * 將數據流通過網絡輸出流傳給服務器
 * 調用網絡輸入流接受成功信息
 */
public class TCPClient02 {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1",8888);
        FileInputStream fis = new FileInputStream("C:\\Users\\ASUS\\Desktop\\新建文件夹\\test.jpg");
        OutputStream outputStream = socket.getOutputStream();
        int i =0;
        byte[] bytes1 = new byte[1024];
        while ((i=fis.read(bytes1))!=-1){
            outputStream.write(bytes1,0,i);
        }
        System.out.println("22222222");
        socket.shutdownOutput();
        InputStream inputStream = socket.getInputStream();
        byte[] bytes = new byte[1024];
        int len = inputStream.read(bytes);
        System.out.println(new String(bytes,0,len));
        fis.close();
        socket.close();

    }

}
