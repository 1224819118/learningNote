package Reflect;

import java.lang.reflect.Field;

/**
 * Field getField(String name)
 * 返回一个 Field对象，它反映此表示的类或接口的指定公共成员字段 类对象。
 * Field[] getFields()
 * 返回包含一个数组 Field对象反射由此表示的类或接口的所有可访问的公共字段 类对象。
 *Field getDeclaredField(String name)
 * 返回一个 Field对象，它反映此表示的类或接口的指定已声明字段 类对象。
 * Field[] getDeclaredFields()
 * 返回的数组 Field对象反映此表示的类或接口声明的所有字段 类对象。
 *
 *
 *
 */
public class reflectTest {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        //1.获取person类对象
        Class personClass = Person.class;
        //2.获取filed对象（public级别的成员对象）
        Field[] fields = personClass.getFields();
        for (Field field:fields) {
            System.out.println(field);
        }
        Field field = personClass.getField("sex");
        Person person = new Person();
        field.set(person,"nan");
        System.out.println(field.get(person));
        //3.不考虑修饰符情况下获取成员变量
        Field[] fields1 = personClass.getDeclaredFields();
        for (Field field1:fields1) {
            System.out.println(field);
        }
        //  暴力反射也就是忽略修饰权限修改成员变量
        Field field1 = personClass.getDeclaredField("name");
        field1.setAccessible(true);
        field1.set(person,"sss");
        System.out.println(person.getName());

    }

}
