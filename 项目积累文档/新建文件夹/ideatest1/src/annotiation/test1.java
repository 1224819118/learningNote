package annotiation;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 通过注解实现之前写的代码
 * 使用反射机制实现在不确定类名和方法名的前提下可以创建类对象并调用类方法
 */
@MyAnnotiation2(className = "Reflect.Person",methodName = "show")
public class test1 {
    public static void main(String[] args) throws Exception {
        //获取注解定义位置的字节码对象
        Class<test1> test1Class = test1.class;
        //通过getannotiation()方法获取注解
        MyAnnotiation2 annotation = test1Class.getAnnotation(MyAnnotiation2.class);
        //获取注解内的属性
        String className = annotation.className();
        String methodName = annotation.methodName();
        //下面就是通过反射构造目标类对象并调用目标方法
        Class aClass = Class.forName(className);
        Constructor constructor = aClass.getConstructor();
        Object o = constructor.newInstance();
        Method method = aClass.getMethod(methodName);
        method.invoke(o);
        System.out.println(o);


    }
}
