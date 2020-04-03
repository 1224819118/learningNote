package IOStream;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

/**
 *properties是唯一一個與流相關的集合對象
 * 通過properties寫的文件中鍵值對由=或者空格分隔#表示注釋不會被load出來
 *
 * void load(InputStream inStream)
 * 从输入字节流读取属性列表（键和元素对）。
 * void load(Reader reader)
 * 以简单的线性格式从输入字符流读取属性列表（关键字和元素对）。
 *
 * Enumeration<?> propertyNames()
 * 返回此属性列表中所有键的枚举，包括默认属性列表中的不同键，如果尚未从主属性列表中找到相同名称的键。
 *Set<String> stringPropertyNames()
 * 返回此属性列表中的一组键，其中键及其对应的值为字符串，包括默认属性列表中的不同键，如果尚未从主属性列表中找到相同名称的键。
 *
 * void store(OutputStream out, String comments)
 * 将此属性列表（键和元素对）写入此 Properties表中，以适合于使用 load(InputStream)方法加载到 Properties表中的格式输出流。
 * void store(Writer writer, String comments)
 * 将此属性列表（键和元素对）写入此 Properties表中，以适合使用 load(Reader)方法的格式输出到输出字符流。
 *
 * String getProperty(String key)
 * 使用此属性列表中指定的键搜索属性。
 * String getProperty(String key, String defaultValue)
 * Object setProperty(String key, String value)
 * 致电 Hashtable方法 put 。
 */
public class PropertiesTest {
    public static void main(String[] args) {
        show1();
        show2();
    }

    private static void show2() {
        Properties p = new Properties();
        try(FileReader fr = new FileReader("C:\\Users\\ASUS\\Desktop\\新建文件夹\\propertiestest.txt");) {
            p.load(fr);
            Set<String> set = p.stringPropertyNames();
            for (String s:set) {
                System.out.println(s+p.getProperty(s));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void show1() {
        Properties p = new Properties();
        p.setProperty("A","111");
        p.setProperty("B","222");
        try(FileWriter fw = new FileWriter("C:\\Users\\ASUS\\Desktop\\新建文件夹\\propertiestest.txt");) {
            p.store(fw,"store test date");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
