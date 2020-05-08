[TOC]



# kotlin语言初学

## 基础语法



Var 表示一个变量。  val表示一个不可变的变量（不是一个常量）

注意的是空值必须是？形式的对应类型，而且空值类型和普通类型不能相互传值，而加入了!!就可以声明这个参数是一个非空参数跳过空参检查。

ko t lin的函数也和Java有一些区别，使用fun来声明一个方法，变量名也是先写上变量名在声明变量类型的形式如果这个方法有返回值则在方法参数列表后边声明一个返回类型

```kotlin
package com.kotlinlearn.learn1

var one:Int = 18
val two:String ="caohao"
var two2:String? = null
var two3:String = "null"

fun main() {
    two3 = two2!!
    two2 = two3
    printl(two)

}
fun printl(str: String): String{
    println("这个字符串是：$str")

    return str
}
```

上面这串代码看起来是没有问题的但是执行之后会有一个kotlin.KotlinNullPointerException异常

不对啊，不是说kotlin没有空指针异常么？上网查阅资料之后发现

问题出在!!这里，这个只是声明参数为非空跳过检查但是它本质还是一个空参，这个!!的意思就跟Java中忽略异常的注解差不多。就是你认为他是非空的但是真正执行如果是个空的那么该出错还是出错。

#### when

```
val i=10
when(i){
    1 -> println(1)
    10 -> println(10)
}
```

kotlin中没有switch而是用when来使用case语句

下面使用when实现一个将数字转化为大写的汉子的小代码

#### 数组

```
fun main() {
    var sum =0
//    for (num in 0..100){ 1到100闭区间
//        sum += num
//    }
    println(sum)
    for (num in 1 until 100 step 10){  //1到100前闭后开区间
        sum += num
    }
    println(sum)
    println((1 .. 100).count())//求数组一共有多少个元素
    println((1 .. 100).reversed())//将数组反转

}
```

#### 链表

```
fun main() {
    var lists:ArrayList<Int> = arrayListOf()
    for (num in 1 .. 100){
        lists.add(num)
    }
    println(lists.size)
//    for (i in lists){
//        println(i)
//    }
//
    for ((i,e) in lists.withIndex()){
        println("$i,$e")
    }

}
```

#### map

```
fun main() {
    var m:HashMap<Int,String> = java.util.HashMap()

    m.put(1,"你")
    m.put(2,"好")
    m.put(3,"世界")

    if ( m.containsKey(1)){

        println(m.get(1)+m.get(2)+m.get(3))
    }


}
```

#### 简单函数式编程和函数表达式

```
fun main() {
    println(add(1,2))

    var i = {x:Int,y:Int -> x+y}
    println(i(3,5))

    var j:(Int,Int)->Int = {x,y->x+y}
    println(j(7,8))

}

fun add(i:Int,j:Int):Int = i+j
```

#### 默认参数和具名参数

```
fun main() {
    println(获取长乘宽面积(1,2))//输出2
    println(获取长乘宽面积(1))//输出0，如果不输入则使用默认值

}

fun 获取长乘宽面积(i:Int=0,j:Int=0):Int = i*j
```

#### 字符串和数字之间的转换

```
fun main() {
    var a = "13"
    var b = 13
    b.toString()
    a.toInt()
}
```

#### 人机交互键盘录入



```
fun main() {
    println("输入")
   var str = readLine()
    println("您输入的是$str")
    var x = readLine()
    var y = readLine()
    var x1= x!!.toInt()
    var y1= y!!.toInt()
    println("$x1*$y1=${x1 * y1}")


}
```

#### 异常处理

和Java中的异常处理基本一致

```
fun main() {
  while (true){
      println("输入")
      var x = readLine()
      var y = readLine()
      //这里我们输入字符，不输入数字产生字符串转化数字异常

      try {
          var x1= x!!.toInt()
          var y1= y!!.toInt()
          println("$x1*$y1=${x1 * y1}")
          return
      }catch(e:Exception){
          println("输入的不是数字")
      }
  }
```

#### 递归

```
fun main() {
println(jiecheng(4))
}
fun jiecheng(x:Int=10):Int{
   var result = x
    fun act(i:Int=x):Int{
        if (i==1){
            return result
        }
        println("$result * ${i-1}")
        result = result * (i -1)
        println(result)
        return act(i-1)
    }
    return act()
}
```

#### 尾递归优化

#### 面向对象

```
open class  Rect(){

    init {
        println("初始化代码")
    }
    constructor(test2: Int):this(){
        this.test2=test2
    }
    val test:String = "guding"
    get() = field

    var test2:Int = 0
    get() = field
    set

}
```

#### 委托和代理

by关键字

#### 单例模式

object关键字

枚举

enum

#### 印章类

Seals 关键字

#### 函数式编程

```
fun main() {
    var print = fun (str:String) { println(str)}
    var lists = listOf<String>("1","2","3","4")
    lists.forEach(print)
}
```

函数调用函数吧函数当作一个对象。

```
fun main() {

    var lists = listOf<String>("1","2","3","4")
    lists.forEach{ a->print(a)}
}
```

#### 图像处理

```
fun main() {
    var image = BufferedImage(100,100,BufferedImage.TYPE_INT_RGB)
    image.setRGB(0,0,0xffff00)
    var w = 0..99
    var h = 0..99
    image.apply { for (i in w){
        for (j in h){
            setRGB(i,j,0xff0000)
        }
    } }
    ImageIO.write(image,"bmp",File("a.bmp"))
}
```

## kotlin与Java互相调用

我们将上面那个one.kt文件修改后通过Java的一个test.java类中的main函数来调用这个one.kt中的print方法

```java
package com.kotlinlearn.learn1;

public class test1 {
    public static void main(String[] args) {
        OneKt.printl("调用kotlin函数");
    }
}
```

可以看到之前我们在写kotlin方法时并不是在一个类中写的但是我们知道kotlin是在jvm上运行的那么他编译之后一定也是一个类中的方法，而通过上面这个例子我们就知道了他是将这个文件名作为一个类名来调用里面的方法和变量的。

下面我们会看到Java和kotlin相互调用的过程

```kotlin
package com.kotlinlearn.learn1



fun main() {
    printl(test1.`in`)
    javaclasstest(test1::class.java)

}
fun printl(str: String): String{
    println("这个字符串是：$str")

    return str
}
fun javaclasstest(clazz: Class<test1>){
    println(clazz.simpleName)

}

object kclass{
    val kclassval:String = "kclassval"

```

```java
public class test1 {
    public static String in ="in";
    public static void main(String[] args) {
        OneKt.printl("调用kotlin函数");
        String kclassval = kclass.INSTANCE.getKclassval();
        System.out.println(kclassval);


    }
}
```

从上面我们可以看到，在kotlin中实现一个内部类是十分方便的直接用object声明即可，而想要在Java中调用这个内部类我们要在类名后加上一个instance单例的意思而且会自动改为一个方法名来获取内部类的参数

通过上面的代码我们也可以发现在Java和kotlin之间传递class对象时也是有一点小小的区别的如果是kotlin的类的话在kotlin中传递不需要什么变化直接::class就行但如果这个类是一个Java类那么就要声明一下在::class后面加一个Java意思是这是一个Java类

## Java和kotlin一起使用常常遇到的问题

- kotlin是没有封装类的
- kotlin中是有空值敏感的
- kotlin没有静态变量与静态方法

这里我们写一个接口，里面有两个重栽的方法，参数一个是int一个是integer。

我们在kotlin中实现这个借口看一下

```kotlin
class aaa:ainterface {
    override fun test(a: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        println(a)
    }
}
```

我们会发现实现的接口在kotlin中会认为两个重载的方法是一摸一样的所以要求我们只实现一个





我们在Java代码中写这样一个类

```
public class a {
   public static String test(String str){
       return str.equals("") ?null:str;
   }
}
```

之后我们在kotlin中使用他的方法，应为我们知道kotlin是空安全的但是我们在Java和kotlin 互相使用时一定会出现在Java中是一个空值传到kotlin中去使用的情况，那么我们应该怎么做

```kotlin
fun main() {
tt("")


}

fun tt(str:String){
    var t = a.test(str) //我们使用kotlin自己检测返回值类型的方式，会发现它是一个string!这个类型在之前我们实现重在接口时也看到了
//    var t2:String = a.test(str)//这里我们用string类型来接返回值，会发现包一个空异常，这时因为string类型是不可以为空的
    var t3:String? = a.test(str)//这里我们使用空安全的string？类型
//    println(t.length)
    println(t3?.length)
}
```

我们会看到t调用并使用时会和在Java中使用一样包Java.lang.nullpointer空指针异常

而t3就会输出一个长度为null

所以当我们在接受一个Java对象是在不确定是否为空那么我们最好用一个可空类型接受





还有就是在kotlin中静态方法要我们家一个注解才能声明是一个静态方法

```
object test2{
    @JvmStatic
    fun show1(){
        println(1)
    }
}
```

## 函数嵌套和一些特性

kotlin的函数是可以在参数列表设置默认值的，而且他的函数内部可以直接定义一个函数这个是函数的嵌套，一般在递归情况和一些你不想让外部调用的函数时可以使用其他情况一般不建议这么用，应为可读性比较低。

```kotlin
fun main() {
    qiantao()
}

fun qiantao(str:String = "morenzhi"){
    val i :Int = 1

    fun qiantao2(){
        
        print(i.inc())
        println()
    }
    for (x in 1..10){
        qiantao2()
    }
    println(str)
}
```

输出会发现i的值其实根本没变

只不过是多了一个=2的对象而已,想要改变自身必须再吧i.inc的值付给i才可以

```
fun main() {
    qiantao()
}

fun qiantao(str:String = "morenzhi"){
    var i :Int = 1

    fun qiantao2(){
        i = i.inc()
        print(i)
        println()
    }
    for (x in 1..10){

        qiantao2()
    }
    println(str)
}
```

## 扩展函数

kotlin可以为一个已经定义好的类加入一个新的函数

```
fun main() {
    var aa:a = a()
    aa.ttt()

}

fun a.ttt()= println("kuozhanfangfa")
```

java是一门静态语言，无法动态的为类添加方法、属性，除非修改类的源码，并重新编译该类。

 kotlin扩展属性、方法时看起来是为该类动态添加了成员，实际上并没有真正修改这个被扩展的类，kotlin实质是**定义了一个函数**，当被扩展的类的对象调用扩展方法时，kotlin会执行**静态解析**,将调用扩展函数静态解析为函数调用。

 **静态解析**：根据调用对象、方法名找到拓展函数，转换为函数调用

## 闭包



## 高阶函数

一个函数的参数也可以定义为一个函数,或者是将返回值定义为一个函数，这个是高节函数

```
fun main() {
    var aa:a = a()
    aa.ttt("test", ::println)

}

fun a.ttt(str:String,function:(String)->Unit){
    function(str)
}
```

函数名（函数名：（参数）-> 返回值）

### 常用高阶函数

kotlin提供的常用高阶函数 ：

maxby() 根据某一条查找最大

,minby()。查找最小

,filter()。根据boolean函数过滤。 

,map()     将数据快速封装为一个新的list

any()。 判断有没有满足要求的有则返回true否则返回false

count()    统计数量

find()   查找第一个符合条件的数据

groupby（）   安某个条件去分组为一个map集合

### Dsl（领域特定语言）

扩展函数



中缀表达式

