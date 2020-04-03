package Reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 *Constructor<T> getConstructor(类<?>... parameterTypes)
 * 返回一个 Constructor对象，该对象反映 Constructor对象表示的类的指定的公共 类函数。
 * Constructor<?>[] getConstructors()
 * 返回包含一个数组 Constructor对象反射由此表示的类的所有公共构造 类对象。
 *
 *
 *
 *T newInstance(Object... initargs)
 * 使用此 Constructor对象表示的构造函数，使用指定的初始化参数来创建和初始化构造函数的声明类的新实例
 *
 */
public class reflectTest2 {
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Class personClass = Person.class;
        Constructor constructor = personClass.getEnclosingConstructor();
        //Object o = constructor.newInstance("name", "nan");
//        Person p = (Person) o;
//        System.out.println(p.getName()+p.getSex());
        //如果使用空参构造，可以使用class对象的newInstance方法
        Object o = personClass.newInstance();
        Person person = (Person) o;
        System.out.println(person);
        System.out.println(person.getName()+person.getSex());
    }

}
