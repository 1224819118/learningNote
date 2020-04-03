package IOStream;

import java.io.*;
import java.util.Properties;

/**
 * 緩衝流的常用子類有
 * 緩衝字節輸入流:BufferedInputStrea
 * 緩衝字節輸出流:BufferedOutputStream
 * 緩衝字符輸入流:BufferedReader
 * 緩衝字符輸出流:BufferedWriter
 */
public class bufferedTest {
    public static void main(String[] args) throws IOException {
        //test1();
        test2();
    }



    /**
     * 緩衝字節輸入輸出流
     *
     */
    private static void test1() throws FileNotFoundException {
        FileOutputStream fos = new FileOutputStream("C:\\Users\\ASUS\\Desktop\\新建文件夹\\bufferedZijie.txt",true);
        BufferedOutputStream bof = new BufferedOutputStream(fos);//有兩種構造器，分別是（fileoutputStream）和（fileoutputStream，size）
        FileInputStream fis = new FileInputStream("C:\\Users\\ASUS\\Desktop\\新建文件夹\\bufferedZijie.txt");
        BufferedInputStream bis = new BufferedInputStream(fis);
//        Properties properties = new Properties();
//        properties.setProperty("a","111");
//        properties.setProperty("b","222");
//        properties.setProperty("c","333");
        try (bof;bis;fos;fis){
            bof.write("nihao".getBytes());
            for (int i = 0; i < 10; i++) {
                String s = ""+i;
                bof.write(s.getBytes());
                bof.write("\r\n".getBytes());
            }
            bof.flush();
            byte[] bytes = new byte[2];
            int i = 0 ;
            while ((i=bis.read(bytes))!=-1){
                System.out.println((char) i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /**
     * 字符緩衝輸入輸出流
     */
    private static void test2() throws IOException {
        FileWriter fw = new FileWriter("C:\\Users\\ASUS\\Desktop\\新建文件夹\\bufferedZifu.txt",true);
        BufferedWriter bw = new BufferedWriter(fw);
        FileReader fr = new FileReader("C:\\Users\\ASUS\\Desktop\\新建文件夹\\bufferedZifu.txt");
        BufferedReader br = new BufferedReader(fr);

        try (fw;bw;fr;br){
            for (int i = 0; i < 10; i++) {
                bw.write("你好");
                bw.newLine();
            }
            bw.flush();
            String s = "";
           while ( (s=br.readLine())!=null){
               System.out.println(s);
           }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
