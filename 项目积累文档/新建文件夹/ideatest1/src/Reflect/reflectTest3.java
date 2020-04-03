package Reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *方法 getMethod(String name, 类<?>... parameterTypes)
 * 返回一个 方法对象，它反映此表示的类或接口的指定公共成员方法 类对象。
 * 方法[] getMethods()
 * 返回包含一个数组 方法对象反射由此表示的类或接口的所有公共方法 类对象，包括那些由类或接口和那些从超类和超接口继承的声明。
 *
 * 方法 getDeclaredMethod(String name, 类<?>... parameterTypes)
 * 返回一个 方法对象，它反映此表示的类或接口的指定声明的方法 类对象。
 * 方法[] getDeclaredMethods()
 * 返回包含一个数组 方法对象反射的类或接口的所有声明的方法，通过此表示 类对象，包括公共，保护，默认（包）访问和私有方法，但不包括继承的方法。
 */
public class reflectTest3 {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class personClass = Person.class;
        Method[] methods = personClass.getMethods();
        for (Method m:methods) {
            System.out.println(m);
        }
        Method method = personClass.getMethod("show");
        Person p = new Person();
        method.invoke(p);
        Method[] methods1 = personClass.getDeclaredMethods();
        for (Method m: methods1) {
            System.out.println(m);
        }
    }



}
