package IOStream;

import java.io.*;

/**
 * writer和reader的底層也是對iostream的包裝構造
 * 而轉換流同理
 */
public class ioStreamReaderWriter {
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        isr_utf();
        //osw_utf();
    }


    /**
     *OutputStreamWriter是字符的桥梁流以字节流：向其写入的字符编码成使用指定的字节charset 。
     * OutputStreamWriter(OutputStream out)
     * 创建一个使用默认字符编码的OutputStreamWriter。
     * OutputStreamWriter(OutputStream out, Charset cs)
     * 创建一个使用给定字符集的OutputStreamWriter。
     * OutputStreamWriter(OutputStream out, CharsetEncoder enc)
     * 创建一个使用给定字符集编码器的OutputStreamWriter。
     * OutputStreamWriter(OutputStream out, String charsetName)
     * 创建一个使用命名字符集的OutputStreamWriter。
     *
     * void close()
     * 关闭流，先刷新。
     * void flush()
     * 刷新流。
     * String getEncoding()
     * 返回此流使用的字符编码的名称。
     * void write(char[] cbuf, int off, int len)
     * 写入字符数组的一部分。
     * void write(int c)
     * 写一个字符
     * void write(String str, int off, int len)
     * 写一个字符串的一部分。
     */
    private static void osw_utf() throws FileNotFoundException, UnsupportedEncodingException {
        FileOutputStream ops = new FileOutputStream("C:\\Users\\ASUS\\Desktop\\新建文件夹\\轉換輸出流_gbk.txt",true);
        OutputStreamWriter osw = new OutputStreamWriter(ops,"GBK");
        try(osw;) {
            osw.write("第一次的轉換流輸出測試");
            osw.flush();
            osw.write("A");
            osw.write("b");
            osw.flush();
            System.out.println(osw.getEncoding());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * InputStreamReader是从字节流到字符流的桥：它读取字节，并使用指定的charset将其解码为字符 。
     * InputStreamReader(InputStream in)
     * 创建一个使用默认字符集的InputStreamReader。
     * InputStreamReader(InputStream in, Charset cs)
     * 创建一个使用给定字符集的InputStreamReader。
     * InputStreamReader(InputStream in, CharsetDecoder dec)
     * 创建一个使用给定字符集解码器的InputStreamReader。
     * InputStreamReader(InputStream in, String charsetName)
     * 创建一个使用命名字符集的InputStreamReader。
     *
     * void close()
     * 关闭流并释放与之相关联的任何系统资源。
     * String getEncoding()
     * 返回此流使用的字符编码的名称。
     * int read()
     * 读一个字符
     * int read(char[] cbuf, int offset, int length)
     * 将字符读入数组的一部分。
     * boolean ready()
     * 告诉这个流是否准备好被读取。
     */
    private static void isr_utf() throws FileNotFoundException, UnsupportedEncodingException {
        FileInputStream fis = new FileInputStream("C:\\Users\\ASUS\\Desktop\\新建文件夹\\轉換輸出流_gbk.txt");
        InputStreamReader isr = new InputStreamReader(fis,"GBK");
        try(isr) {
            System.out.println(isr.ready());
            System.out.println(isr.getEncoding());
            char[] chars = new char[4];
            while ((isr.read(chars))!=-1){
                isr.read(chars);
                System.out.println(new String(chars));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
