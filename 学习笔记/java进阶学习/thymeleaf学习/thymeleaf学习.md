# thymeleaf初学

## 概念

首先thyme leaf再有网络和无网络下都可以使用。

thyme leaf开箱即用

thyme leaf提供spring与springmvc的一个完美的集成的可选模块，可以快速实现表单的绑定，属性编辑器，国际化等。

```
<h2 th:text="${message}" >message</h2>
```

象是这样的简单的使用可以在不修改静态页面的情况下，替换掉内部的值，也可以通过象是注释一样的方式将一部分静态页面屏蔽掉。

```html
<!DOCTYPE html><html lang="en" xmlns:th="http://www.w3.org/1999/xhtml"><head th:fragment="replace1">    <meta charset="UTF-8">    <title>fragment</title></head><body> <p th:fragment="include1">sssssssssssssssssssssssssss</p></body></html>


<!DOCTYPE html><html xmlns:th="http://www.thymeleaf.org"><head th:replace="/fragments :: replace1">    <meta charset="UTF-8">    <title>Title</title></head><body><h2 th:text="${message}" >message</h2>  <div th:include="/fragments :: include1"></div></body></html>
```



## 快速上手

1.赋值,字符串拼接

```html
${message}
赋值就是简单的用$和大括号来赋值
```

```html
字符串拼接有如下两种方式
<h2 th:text="|收到的参数是,${message}|" >message</h2>
<h2 th:text="'收到的参数是,'+${message}" >message</h2>
```



2.条件判断

条件判断有if和unless两种，if是如果条件成立则显示，unless是如果条件不成立则显示

```html
<P th:if="${flag==1}"> 显示了么？</P>
<P th:unless="${flag==1}"> 显示了么？</P>
```

3.循环

iterStat被称为状态变量

- index   当前迭代对象的下标（从0开始）
- count  当前迭代对象的下标（从1开始）
- size     被迭代对象的大小
- current  当前迭代变量
- even/odd   布尔值，当前循环是否是偶数/奇数
- first    当前迭代对象是否是第一个
- last    是否是最后一个

4.内联

[[]]

```html
    <p>hello[[${message}]]</p>
	这种格式在js中也可以使用
	var test = "test"+[[${message}]];
```

5.内嵌变量

常用的内嵌变量如

dates,这个变量可以格式化时间，很多情况下都会用到，

#dates.format(${time},'yyyy-MM-dd');

#dates.crateNow();

string等

#string.empty(${name});

## th标签整理

　　1）简单表达式

　　　--变量表达式  ${……}

```
<input type="text" name="userName" value="James Carrot" th:value="${user.name}" />
```



　　　　上述代码为引用user对象的name属性值。

　　   --选择/星号表达式 *{……}

```
<div th:object="${session.user}">                                                                       
     <p>Nationality: <span th:text="*{nationality}">Saturn</span>.</p>    
</div>
```



　　选择表达式一般跟在th:object后，直接取object中的属性。

　　   --文字国际化表达式  #{……}

```
<p th:utext="#{home.welcome}">Welcome to our grocery store!</p>
```



 　　调用国际化的welcome语句,国际化资源文件如下

```
resource_en_US.properties：
home.welcome=Welcome to here！

resource_zh_CN.properties：home.welcome=欢迎您的到来！
```



　　　-- URL表达式  @{……}              

```
<a href="details.html" th:href="@{/order/details(orderId=${o.id})}">view</a>
```



​          @{……}支持决定路径和相对路径。其中相对路径又支持跨上下文调用url和协议的引用（//code.jquery.com/jquery-2.0.3.min.js）。

　　　当URL为后台传出的参数时，代码如下

```
<img src="../../static/assets/images/qr-code.jpg" th:src="@{${path}}" alt="二维码" />
```



　　2）常用的th标签

　　　--简单数据转换（数字，日期）

```
 　　<dt>价格</dt>
  　 <dd th:text="${#numbers.formatDecimal(product.price, 1, 2)}">180</dd>
　　 <dt>进货日期</dt>
　　 <dd th:text="${#dates.format(product.availableFrom, 'yyyy-MM-dd')}">2014-12-01</dd>
```



　　　--字符串拼接

```
<dd th:text="${'$'+product.price}">235</dd>
```



　　  --转义和非转义文本

　　　　当后台传出的数据为“This is an &lt;em&gt;HTML&lt;/em&gt; text. &lt;b&gt;Enjoy yourself!&lt;/b&gt;”时，若页面代码如下则出现两种不同的结果

```
<div th:text="${html}">
　　This is an &lt;em&gt;HTML&lt;/em&gt; text. &lt;b&gt;Enjoy yourself!&lt;/b&gt;
</div> <div th:utext="${html}">
　　This is an <em>HTML</em> text. <b>Enjoy yourself!</b>
</div>
```



　　　--表单中

```
<form th:action="@{/bb}" th:object="${user}" method="post" th:method="post">

    <input type="text" th:field="*{name}"/>
    <input type="text" th:field="*{msg}"/>

    <input type="submit"/>
</form>
```



　　 --显示页面的数据迭代

```
//用 th:remove 移除除了第一个外的静态数据，用第一个tr标签进行循环迭代显示
　　　　<tbody th:remove="all-but-first">
　　　　　　　　　　//将后台传出的 productList 的集合进行迭代，用product参数接收，通过product访问属性值
                <tr th:each="product:${productList}">　　　　　　　　　　　　//用count进行统计，有顺序的显示
　　　　　　　　　　　　<td th:text="${productStat.count}">1</td>
                    <td th:text="${product.description}">Red Chair</td>
                    <td th:text="${'$' + #numbers.formatDecimal(product.price, 1, 2)}">$123</td>
                    <td th:text="${#dates.format(product.availableFrom, 'yyyy-MM-dd')}">2014-12-01</td>
                </tr>
                <tr>
                    <td>White table</td>
                    <td>$200</td>
                    <td>15-Jul-2013</td>
                </tr>
                <tr>
                    <td>Reb table</td>
                    <td>$200</td>
                    <td>15-Jul-2013</td>
                </tr>
                <tr>
                    <td>Blue table</td>
                    <td>$200</td>
                    <td>15-Jul-2013</td>
                </tr>
      </tbody>
```



　　--条件判断

```
<span th:if="${product.price lt 100}" class="offer">Special offer!</span>
```



　　不能用"<”，">"等符号，要用"lt"等替代

```
<!-- 当gender存在时，选择对应的选项；若gender不存在或为null时，取得customer对象的name-->
<td th:switch="${customer.gender?.name()}">
    <img th:case="'MALE'" src="../../../images/male.png" th:src="@{/images/male.png}" alt="Male" /> <!-- Use "/images/male.png" image -->
    <img th:case="'FEMALE'" src="../../../images/female.png" th:src="@{/images/female.png}" alt="Female" /> <!-- Use "/images/female.png" image -->
    <span th:case="*">Unknown</span>
</td>
```



```
<!--在页面先显示，然后再在显示的数据基础上进行修改--><div class="form-group col-lg-6">    <label>姓名<span>&nbsp;</span></label> 　　<！--除非resume对象的name属性值为null，否则就用name的值作为placeholder值-->    <input type="text" class="form-control" th:unless="${resumes.name} eq '' or ${resumes.name} eq null"             data-required="true" th:placeholder="${resumes.name}" />　　 <!--除非resume对象的name属性不为空，否则就定义一个field方便封装对象，并用placeholder提示-->    <input type="text" th:field="${resume.name}" class="form-control" th:unless="${resumes.name} ne null"            data-required="true" th:placeholder="请填写您的真实姓名"  /></div>
```



```
<!-- 增加class="enhanced"当balance大雨10000 -->
<td th:class="${customer.balance gt 10000} ? 'enhanced'" th:text="${customer.balance}">350</td>
```



　　--根据后台数据选中select的选项

 

```
 <div class="form-group col-lg-6">
      <label >性别<span>&nbsp;Sex:</span></label>
      <select th:field="${resume.gender}" class="form-control" th:switch="${resumes.gender.toString()}"
            data-required="true">
              <option value="男" th:case="'男'" th:selected="selected" >男</option>
              <option value="女" th:case="'女'" th:selected="selected" >女</option>
              <option value="">请选择</option>
      </select>
 </div>
```



 

　　因为gender是定义的Enum（枚举）类型，所以要用toString方法。用th:switch指定传出的变量,用th:case对变量的值进行匹配。！"请选择"放在第一项会出现永远选择的是这个选项。或者用th:if

```
<div class='form-group col-lg-4'>
          <select class='form-control' name="skill[4].proficiency">
                <option >掌握程度</option>
                <option th:if="${skill.level eq '一般'}" th:selected="selected">一般</option>
                 <option th:if="${skill.level eq '熟练'}" th:selected="selected">熟练</option>
                 <option th:if="${skill.level eq '精通'}" th:selected="selected">精通</option>
           </select>
</div>
```



　　--spring表达式语言

```
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
    <head>
        <title>Thymeleaf tutorial: exercise 10</title>
        <link rel="stylesheet" href="../../../css/main-static.css" th:href="@{/css/main.css}" />
        <meta charset="utf-8" />
    </head>
    <body>
        <h1>Thymeleaf tutorial - Solution for exercise 10: Spring Expression language</h1>
  
        <h2>Arithmetic expressions</h2>
        <p class="label">Four multiplied by minus six multiplied by minus two module seven:</p>
        <p class="answer" th:text="${4 * -6 * -2 % 7}">123</p>
 
        <h2>Object navigation</h2>
        <p class="label">Description field of paymentMethod field of the third element of customerList bean:</p>
        <p class="answer" th:text="${customerList[2].paymentMethod.description}">Credit card</p>
 
        <h2>Object instantiation</h2>
        <p class="label">Current time milliseconds:</p>
        <p class="answer" th:text="${new java.util.Date().getTime()}">22-Jun-2013</p>
        
        <h2>T operator</h2>
        <p class="label">Random number:</p>
        <p class="answer" th:text="${T(java.lang.Math).random()}">123456</p>
    </body>
</html>
```



　 --内联

```
<label for="body">Message body:</label>
<textarea id="body" name="body" th:inline="text">
Dear [[${customerName}]],

it is our sincere pleasure to congratulate your in your birthday:
    Happy birthday [[${customerName}]]!!!

See you soon, [[${customerName}]].

Regards,
    The Thymeleaf team
</textarea>
```



　--内联JS <js起止加入如下代码，否则引号嵌套或者"<"">"等不能用>

```
/*<![CDATA[*/
……
/*]]>*/
```



　　--js附加代码：

```
/*[+
var msg = 'This is a working application';
+]*/
```



　　--js移除代码：

```
/*[- */
var msg = 'This is a non-working template';
/* -]*/
```

