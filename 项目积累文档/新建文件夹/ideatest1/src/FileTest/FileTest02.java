package FileTest;

import java.io.File;
import java.io.FileFilter;

/**
 * 遍歷之前寫好的文件夾結構，通過三種方式
 * 1.遍歷實現
 * 2.用fileFilter，實現
 * 3.用lambda表達式實現file filter
 */
public class FileTest02 {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\ASUS\\Desktop\\新建文件夹");
        FileTest02 fileTest02 = new FileTest02();
//        fileTest02.show(file);
        fileTest02.show3(file);
    }
    //方法一實現
    public void show(File file){
        if(file.isDirectory()){
            File[] files = file.listFiles();
            for (File f:files) {
                if(f.isFile()){
                    if(f.getName().endsWith(".java")){
                        System.out.println("找到Java文件"+f.getName());
                    }else
                        System.out.println(f.getName());

                }
                if(f.isDirectory()){
                    System.out.println("進入"+f.getName()+"  文件夾");
                    show(f);
                }

            }
        }
    }
    //方法二實現
    public void show2(File file){
        if(file.isDirectory()){
            File[] files = file.listFiles(new fileFilterTest());
            for (File f:files) {
                if(f.isFile()){
                    System.out.println("文件名為"+f.getName());

                }else
                    show2(f);

            }
        }
    }
    //方法三實現
    public void show3(File file){
        if(file.isDirectory()){
            File[] files = file.listFiles((File file1)->{
                return file1.getName().endsWith(".java")||file1.isDirectory();
            });
            for (File f:files) {
                if(f.isFile()){
                    System.out.println("文件名為"+f.getName());

                }else
                    show3(f);

            }
        }
    }
}
class fileFilterTest implements FileFilter{

    @Override
    public boolean accept(File file) {
        return file.getName().endsWith(".java")||file.isDirectory();
    }
}
