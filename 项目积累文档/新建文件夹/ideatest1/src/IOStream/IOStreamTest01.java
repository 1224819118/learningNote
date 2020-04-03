package IOStream;

import java.io.*;

/**
 * 流代表數據，而輸入輸出流就是將磁盤中的數據讀取到内存和將數據從内存中寫入到磁盤的意思
 * 字節流讀取中文時會有亂碼問題應爲GBK一個字符是2個字節而UTF-8一個字符是三個字節
 * 所以我們應該使用字符流讀取中文
 * Java程序-》jvm虛擬機-》os（操作系統）按照這麽一個順序進行流的輸入以及輸出
 * \r\n可以用於換行
 *字節輸出流常用的有子类：
 * ByteArrayOutputStream ， FileOutputStream ，  ObjectOutputStream ， PipedOutputStream
 *字符輸出流：
 *
 * 字節輸入流：
 *已知直接子类：
 * AudioInputStream ， ByteArrayInputStream ， FileInputStream ， FilterInputStream ， InputStream ， ObjectInputStream ， PipedInputStream ， SequenceInputStream ， StringBufferInputStream
 *
 *
 * 字符輸入流：
 * F
 */
public class IOStreamTest01 {
    public static void main(String[] args) throws IOException {
        //FileOutputStresmTest();
        //FileInputStreamTest();
        //fuzhiTest();
        //FilewriterTest();
        FileReaderTest();
    }

    /**
     * FileOutPutStream:
     *  * 構造方法：
     *  *  FileOutputStream(File file)
     *  * 创建文件输出流以写入由指定的 File对象表示的文件。
     *  * FileOutputStream(File file, boolean append)
     *  * 创建文件输出流以写入由指定的 File对象表示的文件，Boolean表示是否可以對文件進行追加寫。
     *  * FileOutputStream(String name)
     *  * 创建文件输出流以指定的名称写入文件。
     *  * FileOutputStream(String name, boolean append)
     *  * 创建文件输出流以指定的名称写入文件，Boolean表示是否可以對文件進行追加寫。
     *  * 使用步驟：
     *  * 1.創建對象
     *  * 2.寫入
     *  * 3.釋放流
     *  * 常用方法：
     *  *寫入方法：
     *  * public void write(int b)
     *            throws IOException将指定的字节写入此文件输出流。 实现write方法OutputStream 。
     *  * public void write(byte[] b)
     *            throws IOException将 b.length字节从指定的字节数组写入此文件输出流。
     *  * public void write(byte[] b,
     *                   int off,
     *                   int len)
     *            throws IOException从位于偏移量 off的指定字节数组写入 len字节到该文件输出流。
     *  *
     */
    public static void FileOutputStresmTest() throws IOException {
        FileOutputStream fos = new FileOutputStream("C:\\Users\\ASUS\\Desktop\\新建文件夹\\fos.txt",true);
        fos.write("你好文件字節輸出流".getBytes());//字節流只能輸出字節所以要獲取輸入字符的字節才可
        fos.write("\r\n".getBytes());
        byte[] bytes = {65,64,63};//Byte[] 是不可以的 一定要是byte[]對象
        fos.write(bytes);
        fos.close();
    }

    /**
     * FileInputStream
     * 構造器：
     *FileInputStream(File file)
     * 通过打开与实际文件的连接创建一个 FileInputStream ，该文件由文件系统中的 File对象 file命名。
     * FileInputStream(String name)
     * 通过打开与实际文件的连接来创建一个 FileInputStream ，该文件由文件系统中的路径名 name命名。
     *
     * 常用方法:
     * int read()
     * 从该输入流读取一个字节的数据。
     * int read(byte[] b)
     * 从该输入流读取最多 b.length个字节的数据为字节数组。
     * int read(byte[] b, int off, int len)
     * 从该输入流读取最多 len字节的数据为字节数组。
     */
    public  static  void FileInputStreamTest() throws IOException {
        FileInputStream fis = new FileInputStream("C:\\Users\\ASUS\\Desktop\\新建文件夹\\fos.txt");
        //對之前的fos文件進行讀取測試
//        int i = fis.read();//每次讀取一個字節，并且指針向後移動一位儅讀取到最後一位還繼續讀取的話會返回-1
//        System.out.println((char) i);
//        int i=0;
//        while ((i=fis.read())!=-1){//循環讀取
//            System.out.print((char) i);
//        }
        byte[] bytes = new byte[2];//一次讀取兩個
        //利用String的String(byte[] bytes)
        //通过使用平台的默认字符集解码指定的字节数组来构造新的 String 。
        int i = fis.read(bytes);
        System.out.println(new String(bytes));
        fis.close();


    }

    /**
     * 通過上面的學習寫一個文件複製的小練習
     */
    public static void fuzhiTest() throws IOException{
        FileInputStream fis = new FileInputStream("C:\\Users\\ASUS\\Desktop\\新建文件夹\\test.txt");
        FileOutputStream fos = new FileOutputStream("C:\\Users\\ASUS\\Desktop\\新建文件夹\\test(1).txt");
        int i = 0;
        while ((i=fis.read())!=-1){
            fos.write(i);
        }
        fis.close();
        fos.close();
    }

    /**
     * FileReader
     * 構造器：
     * FileReader(File file)
     * 创建一个新的 FileReader ，给出 File读取。
     * FileReader(String fileName)
     * 创建一个新的 FileReader ，给定要读取的文件的名称。
     *
     * 使用步驟
     * 1.創建對象
     * 2.調用writer方法
     * 3.釋放流
     *
     * 常用方法：
     *與字節流大致相同
     */

    public static void FileReaderTest() throws IOException{
        FileReader fr = new FileReader("C:\\Users\\ASUS\\Desktop\\新建文件夹\\fr.txt");
//        int i = 0;
//
//        while ((i=fr.read())!=-1){
//            System.out.println((char) i);
//        }
        char[] bytes = new char[2];
        while ((fr.read(bytes))!=-1){
            String s = new String(bytes);
            System.out.println(s);
        }
        fr.close();

    }

    /**
     * Filewriter
     * 構造器：
     *FileWriter(File file)
     * 给一个File对象构造一个FileWriter对象。
     * FileWriter(File file, boolean append)
     * 给一个File对象构造一个FileWriter对象。
     * FileWriter(String fileName)
     * 构造一个给定文件名的FileWriter对象。
     * FileWriter(String fileName, boolean append)
     * 构造一个FileWriter对象，给出一个带有布尔值的文件名，表示是否附加写入的数据。
     *
     * 使用步驟
     * 與字節流的最大區別就是flash方法，字符流的writer方法不會直接寫入到磁盤中而是放在緩衝區裏，
     * 只有刷新了才會存入磁盤
     * 1.創建對象
     * 2.調用writer方法
     * 3.執行flash方法，將緩衝區的存入磁盤
     * 4.釋放流，close方法會先自動調用flash方法在關閉流
     *
     * 常用方法：
     * 與字節流大致相同
     */
    public static void FilewriterTest() throws IOException{

        FileWriter fw = new FileWriter("C:\\Users\\ASUS\\Desktop\\新建文件夹\\fr.txt",true);
        for (int i = 0; i < 10; i++) {
            fw.write("你好");
            fw.write("\r\n");
            fw.flush();
        }
        fw.close();
    }

}
