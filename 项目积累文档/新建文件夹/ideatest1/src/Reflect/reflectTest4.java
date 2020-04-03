package Reflect;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 *
 */
public class reflectTest4 {
    public static void main(String[] args) throws Exception {
        //创建properties对象
        Properties properties = new Properties();
        //创建类加载器
        ClassLoader classLoader = reflectTest4.class.getClassLoader();
        //加载配置文件
        InputStream resourceAsStream = classLoader.getResourceAsStream("pro.properties");
        properties.load(resourceAsStream);
        //获取配置信息
        String className = properties.getProperty("ClassName");
        String methodName = properties.getProperty("MethodName");
        //创建类对象
        Class aClass = Class.forName(className);
        //创建目标对象
        Object o = aClass.newInstance();
        //执行目标方法
        Method method = aClass.getMethod(methodName);
        method.invoke(o);
    }
}
