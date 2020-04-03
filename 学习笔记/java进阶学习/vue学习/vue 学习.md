# vue 学习

## 1.vue简介

​	vue是一种javascript框架。

<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
国内的比较快速的cdnhttps://cdn.staticfile.org/vue/2.2.2/vue.min.js

## 2.第一个vue程序

```
   <div id="app">
        {{message}}
    </div>

    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
    <script>
        var app = new Vue({
            el:"#app",
            data:{
                message:"第一个vue测试"
            }
        })
    </script>
```



## 3.el:挂载点

el后面命中的标签中的被两个大括号包起来的字符串会被替换为el挂载点中设置的值，只能支持双标签（除了body）

## 4.data数据对象

![1581503820543](C:\Users\ASUS\AppData\Roaming\Typora\typora-user-images\1581503820543.png)

data数据对象是可以实现数组和jason格式的数据的

## 5. vue 指令学习（以v-开头的语法）

### 5.1 :v-text和{{}}（插值表达式）

设置标签的文本值

![1581507353174](C:\Users\ASUS\AppData\Roaming\Typora\typora-user-images\1581507353174.png)

### 5.2 :v-on

为标签添加一个事件

![1581507688864](C:\Users\ASUS\AppData\Roaming\Typora\typora-user-images\1581507688864.png)

### 5.3 :v-html

设置标签中的文本值如果符合html格式则自动解析为html

![1581507514746](C:\Users\ASUS\AppData\Roaming\Typora\typora-user-images\1581507514746.png)

## 单元测试-写一个计数器

![1581508385345](C:\Users\ASUS\AppData\Roaming\Typora\typora-user-images\1581508385345.png)

### 5.4 :v-show

设置是否显示

![1581508884915](C:\Users\ASUS\AppData\Roaming\Typora\typora-user-images\1581508884915.png)

### 5.5 :v-if

根据返回的Boolean值来设置是否可见

![1581508941277](C:\Users\ASUS\AppData\Roaming\Typora\typora-user-images\1581508941277.png)



### 5.6 :v-bind

设置元素的属性（比如src,title,class）

![1581509498847](C:\Users\ASUS\AppData\Roaming\Typora\typora-user-images\1581509498847.png)

## **单元测试**

![1581510129266](C:\Users\ASUS\AppData\Roaming\Typora\typora-user-images\1581510129266.png)

### 5.7 :v-for

根据数据生成列表结构

![1581510378422](C:\Users\ASUS\AppData\Roaming\Typora\typora-user-images\1581510378422.png)

### 5.8 :v-model

获取和设置表单元素的值（双向数据绑定）

![1581514257801](C:\Users\ASUS\AppData\Roaming\Typora\typora-user-images\1581514257801.png)

### 5.9 :v-on补充

触发事件时可以传递自定义参数，事件修饰符

![1581514378537](C:\Users\ASUS\AppData\Roaming\Typora\typora-user-images\1581514378537.png)

## 本地应用-记事本

这个记事本的功能是能够将新写的文本加入到用于储存文本的数据结构中，并且可以自动显示数据结构中的所有的数据。在最下方提供了用于清空数据的按钮和能够显示一共有多少条数据的可变文本。

```
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>test</title>
</head>
<body>
    <div id="main">
        <h1>记事本案例</h1><br>
        <div id="contioner">
            <input type="text" v-model="message" @keyup.enter="subnew">
            <div  >
               <h2 v-for="(item,index) in date"> {{index+1}}:{{item}}</h2>
            </div>
            <div v-show="date.length!=0">
                <span>{{date.length}}</span>items<br>
            </div>
            <a href="#" @click="clear" v-if="date.length!=0">clear</a>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
    <script>
     var contioner = new Vue({
         el:"#contioner",
         data:{
                date:["测试数据","测试数据"],
                message:""
                
         },
         methods:{
                clear:function(){
                    this.date=[];   
                },
                subnew:function(){
                   this.date.push(this.message);
                }
         }
     })
    </script>
</body>
</html>
```

![1581565413442](C:\Users\ASUS\AppData\Roaming\Typora\typora-user-images\1581565413442.png)

当输入数据并按下回车时

![1581565426251](C:\Users\ASUS\AppData\Roaming\Typora\typora-user-images\1581565426251.png)

如果数据为零则

![1581565436705](C:\Users\ASUS\AppData\Roaming\Typora\typora-user-images\1581565436705.png)

## 6 异步访问

vue有两种异步访问的js一个是vue-resource 和 axios目前提倡的是axios

vue结合网络数据开发应用

axios是一个网络请求库 

axios是通过promise实现对ajax技术的一种封装，就像jQuery实现ajax封装一样。
简单来说： ajax技术实现了网页的局部数据刷新，axios实现了对ajax的封装。
axios是ajax ajax不止axios。

ajax：
本身是针对MVC的编程,不符合现在前端MVVM的浪潮
基于原生的XHR开发，XHR本身的架构不清晰，已经有了fetch的替代方案
JQuery整个项目太大，单纯使用ajax却要引入整个JQuery非常的不合理（采取个性化打包的方案又不能享受CDN服务
axios：
从 node.js 创建 http 请求
支持 Promise API
客户端支持防止CSRF
提供了一些并发请求的接口（重要，方便了很多的操作）

[官方中文文档]: http://www.axios-js.com/docs/vue-axios.html



### 6.1 axios基本使用

https://www.kancloud.cn/yunye/axios/234845官方文档

安装axios

npm：
 `$ npm install axios -S`
 cdn：
 `<script src="https://unpkg.com/axios/dist/axios.min.js"></script`

基本格式：

axios.get(地址?key1=velue&key2=velue).then(function(response){},function(err){})

axios.post(地址,{key1:velue&key2:velue}).then(function(response){},function(err){})

测试代码：

后端已经写好了

前端的vue+axios代码如下

```
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>test</title>
</head>
<body>
    <div id="axiostest">
        <input type="button" value="gettest"  @click="gett">
    <input type="button" value="posttest"  @click="postt">
    
    </div>
    
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
     <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
    <script>
        var test = new Vue({
            el:"#axiostest",
            data:{
                postmessage:{
                    pojo:{
                            "id": 4,
                            "name": "inserttest",
                            "sex": "男",
                            "tel": "1515151515",
                            "addr": "无家可归"
                        },

                    get:"",
                    post:""
                }
            },
            methods:{
                
                gett:function(){
                    var that=this;
                    axios.get("http://127.0.0.1:8088/customerTable/1").then(function(response){
                        that.get=response.data;
                        alert(response.data);
                    },function(err){

                    })

                },
                postt:function(){
                    var that=this;
                    axios.post("http://127.0.0.1:8088/customerTable,"+that.pojo).then(
                        function(response){
                           that.post=response.data;
                        },function(err){

                        }
                    )

                }
            }
        })
        
    </script>
</body>
</html>
```

![1581569735525](C:\Users\ASUS\AppData\Roaming\Typora\typora-user-images\1581569735525.png)

测试时候出现了访问资源被拦截的问题这个是由于我们前后端分离而产生的跨域请求问题可以参照下面的网页解决

[解决网页]: https://blog.csdn.net/qq_43147121/article/details/104294154

## 6.2 vue-resouce

这个仅仅支持到vue2.0在后面的vue3.0就不支持这个了，需要我们使用axios了

https://www.cnblogs.com/zshno1/p/9777566.html

## 7.组件化应用构建

组件系统是 Vue 的另一个重要概念，因为它是一种抽象，允许我们使用小型、独立和通常可复用的组件构建大型应用。仔细想想，几乎任意类型的应用界面都可以抽象为一个组件树：

在 Vue 里，一个组件本质上是一个拥有预定义选项的一个 Vue 实例。在 Vue 中注册组件很简单：

```html
Vue.component('todo-item', {
// todo-item 组件现在接受一个
// "prop"，类似于一个自定义 attribute。
// 这个 prop 名为 todo。
props: ['todo'],
template: '<li>{{ todo.text }}</li>'
})

现在，我们可以使用 v-bind 指令将待办项传到循环输出的每个组件中：
<div id="app-7">
  <ol>
    <!--
      现在我们为每个 todo-item 提供 todo 对象
      todo 对象是变量，即其内容可以是动态的。
      我们也需要为每个组件提供一个“key”，稍后再
      作详细解释。
    -->
    <todo-item
      v-for="item in groceryList"
      v-bind:todo="item"
      v-bind:key="item.id"
    ></todo-item>
  </ol>
</div>
Vue.component('todo-item', {
  props: ['todo'],
  template: '<li>{{ todo.text }}</li>'
})

var app7 = new Vue({
  el: '#app-7',
  data: {
    groceryList: [
      { id: 0, text: '蔬菜' },
      { id: 1, text: '奶酪' },
      { id: 2, text: '随便其它什么人吃的东西' }
    ]
  }
})
```

## 8 Vue 实例

## 9 node服务器安装

类似一=与后端的服务器，他是一个前端的服务器

### 9.1 npm命令

npm是js包管理工具

由于npm命令下载会到一些国外网站去下载所以会很慢我们要改为国内的镜像，

```
npm install -g cnpm --registry=https://registry.npm.taobao.org
```

### 9.2 安装vuecli

这个是一个脚手架工具，

cnmp install -g @vue/cli

### 9.3使用vue-cli创建前端项目

1.使用命令创建    vue create my-project

2.使用图形化界面创建  vue ui