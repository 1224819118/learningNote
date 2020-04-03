package annotiation;


import java.lang.annotation.*;

@Target({ElementType.TYPE,ElementType.FIELD})
@Retention(RetentionPolicy.CLASS)
@Documented
@Inherited
public @interface Myannotiaction {
    /**
     * public @interface name {
     *     属性列表
     * }
     * 注解中的抽象方法被称为属性
     *  要求：
     *      1.属性的返回值要求
     *          * 基本类型
     *          * string
     *          * 枚举
     *          * 注解
     *          * 以上类型的数组
     *       2.定义了属性的注解，在使用这个注解时要给属性赋值
     *          1. 如果定义属性时使用default关键字给属性默认初始化值咋使用注解是可以不必赋值
     *          2. 如果数组赋值时数组只有一个值那么{}可以省略
     *元注解：描述注解的注解
     *      * @target  描述注解能做用的位置
     *          * elementType:
     *                  TYPE,可以作用在类上
     *                  FIELD,可以作用在成员上
     *                  METHOD,可以作用在方法上
     *      * @retention 描述注解被保留的阶段
     *          * RetentionPolicy :
     *                      SOURCE,
     *                      CLASS,
     *                      RUNTIME;
     *      * @document 描述注解是否被抽取到api文档中
     *      * @inherited 描述注解是否被子类继承
     *
     *
     */
    //int show();//使用时@Myannotiaction(show=5)
    //@Myannotiaction(show2=person.p1,show3={2,2,2})
//    person  show2();
//    int[] show3();
//





}
