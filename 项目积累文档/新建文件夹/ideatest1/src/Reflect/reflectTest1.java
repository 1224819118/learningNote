package Reflect;

public class reflectTest1 {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        //1.forname()
        Class aClass = Class.forName("Reflect.Student");
        System.out.println(aClass);
        //2.类名.class
        Class studentClass = Student.class;
        System.out.println(studentClass);
        //3.对象.getclass
        Student student = new Student();
        Class aClass1 = student.getClass();
        System.out.println(aClass1);
        //同一个字节码文件再一次程序运行过程中加载出来的类对象是同一个
        System.out.println(aClass==studentClass);
        System.out.println(aClass==aClass1);
    }

}
