package FileTest;

import java.io.File;
import java.io.IOException;


/**
 * 構造方法：
 * File(File parent, String child)
 * 从父抽象路径名和子路径名字符串创建新的 File实例。
 * File(String pathname)
 * 通过将给定的路径名字符串转换为抽象路径名来创建新的 File实例。
 * File(String parent, String child)
 * 从父路径名字符串和子路径名字符串创建新的 File实例。
 * File(URI uri)
 * 通过将给定的 file: URI转换为抽象路径名来创建新的 File实例。
 *
 * 常用方法：
 *Path toPath()
 * 返回从此抽象路径构造的java.nio.file.Path对象。
 * String toString()
 * 返回此抽象路径名的路径名字符串。
 * URI toURI()
 * 构造一个表示此抽象路径名的 file: URI。
 * boolean isAbsolute()
 * 测试这个抽象路径名是否是绝对的。
 * boolean isDirectory()
 * 测试此抽象路径名表示的文件是否为目录。
 * boolean isFile()
 * 测试此抽象路径名表示的文件是否为普通文件。
 * boolean exists()
 * 测试此抽象路径名表示的文件或目录是否存在。
 * File getAbsoluteFile()
 * 返回此抽象路径名的绝对形式。
 * String getAbsolutePath()
 * 返回此抽象路径名的绝对路径名字符串。
 * boolean createNewFile()
 * 当且仅当具有该名称的文件尚不存在时，原子地创建一个由该抽象路径名命名的新的空文件。
 * boolean mkdir()
 * 创建由此抽象路径名命名的目录。
 * boolean mkdirs()
 * 创建由此抽象路径名命名的目录，包括任何必需但不存在的父目录。
 * long length()
 * 返回由此抽象路径名表示的文件的长度。
 * String[] list()
 * 返回一个字符串数组，命名由此抽象路径名表示的目录中的文件和目录。
 * String[] list(FilenameFilter filter)
 * 返回一个字符串数组，命名由此抽象路径名表示的目录中满足指定过滤器的文件和目录。
 *
 *
 */
public class FileTest01 {
    public static void main(String[] args) {
        File file1 = new File("C:\\Users\\ASUS\\Desktop\\新建文件夹\\file");
        File file2 = new File("C:\\Users\\ASUS\\Desktop\\新建文件夹\\file\\1.txt");
        File file3 = new File(file1,"2.txt");
        boolean mkdir = file1.mkdirs();
        System.out.println(mkdir);
        if(file1.exists()){
            try {
                if(!file2.exists()){
                    file2.createNewFile();
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("創建新文件失敗");
            }
        }else {
            file1.mkdirs();
            try {
                file2.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("創建新文件失敗");
            }
        }
        try {
            file3.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(file1.isDirectory());
        System.out.println(file2.isFile());
        System.out.println(file3.list());

    }
}
