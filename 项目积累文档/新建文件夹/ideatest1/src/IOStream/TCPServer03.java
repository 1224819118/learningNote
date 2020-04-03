package IOStream;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class TCPServer03 {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        while (true){
            Socket socket = serverSocket.accept();
            new Thread(new Runnable() {
                @Override
                public synchronized void run() {
                    try {

                        File file = new File("C:\\Users\\ASUS\\Desktop\\新建文件夹2");
                        if(!file.exists()){
                            file.mkdirs();
                        }

                        String filename = "caohao"+System.currentTimeMillis()+new Random().nextInt(99999)+".jpg";
                        InputStream inputStream = socket.getInputStream();
                        FileOutputStream fos = new FileOutputStream(file+"\\"+filename);
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
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }).start();
        }

    }

}
