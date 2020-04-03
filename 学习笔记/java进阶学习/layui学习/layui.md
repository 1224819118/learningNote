[TOC]

https://www.layui.com/doc/官方文档

# 概述

下载完我们会看到文件目录结构如下

css  样式

font  字体

images  图片

lay  具体的model

layui.all.js  包含了所有模块的js 这个比较大的200多kb

layui.js  这个是我们要用什么就layui.use{["table",form]}这样去引用  这个就8kb

# 学习内容

使用方法

引入layui.js和layui.css

注意我在引入的时候发现一定要按照官网的结构目录去引入否则是引入不了的而且要在第一个斜杠前面加.我一开始写的绝对路径能跳转过去但是引入失败

## 图标

方法一：字符实体

```
 <div class="layui-icon">
        &#xe60c;
    </div>
```

这种方法就像是我们在使用&nbsp一样layui把图片包装为字符实体但是注意的是我们想要用这些字符实体就一定要把他们放在一个容器组件中并且这个容器的class一定要包含layui-icon

方法二：样式

```
<div class="layui-icon layui-icon-face-smile"></div> 
```

这种方法就好用多了，直接在容器组件的class中写对应的类名就可以了

## 按钮

1. 必要样式 layui-btns

2. 主题样式 

   - layui-btn-primary   原始按钮
   - layui-btn layui-btn-normal  百搭按钮
   - layui-btn layui-btn-warm  暖色
   - "layui-btn layui-btn-danger  警告
   - layui-btn layui-btn-disabled  禁用

3. 尺寸

   - "layui-btn layui-btn-lg"  大型
   - layui-btn layui-btn-sm"  小型
   - layui-btn layui-btn-xs"   微型
   - layui-btn-fluid"   填充满的按钮

4. 圆角

   layui-btn layui-btn-radius

5. 图标

   在button中夹着icon就可以了也可以继续链接着文字等等

   1. <button type="button" class="layui-btn">
   2.   <i class="layui-icon">*&*#xe608;</i> 添加
   3. </button>

6. 按钮组

   可以将一堆按钮最为一组，默认的他们是无缝相连的

   layui-btn-group

7. 按钮容器

   按钮容器的按钮之间是有空隙的

   layui-btn-container

## 导航菜单

水平导航

layui-nav这个代表导航栏

垂直导航               

layui-nav layui-nav-tree 这是垂直导航栏 

我们也可以加背景等等其他的元素

而且可以通过element元素js来设置下拉菜单                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 

```html
<ul class="layui-nav layui-nav-tree layui-bg-blue" lay-filter="">
        <li class="layui-nav-item"><a href="">最新活动</a></li>
        <li class="layui-nav-item layui-this"><a href="">产品</a></li>
        <li class="layui-nav-item"><a href="" class="layui-icon layui-icon-align-center layui-icon-camera">大数据</a></li>
        <li class="layui-nav-item">
          <a href="javascript:;">解决方案</a>
          <dl class="layui-nav-child"> <!-- 二级菜单 -->
            <dd><a href="">移动模块</a></dd>
            <dd><a href="">后台模版</a></dd>
            <dd><a href="">电商平台</a></dd>
          </dl>
        </li>
        <li class="layui-nav-item"><a href="">社区</a></li>
      </ul>
    <script src="./layui/layui.js"></script>
    
<script>
    //注意：导航 依赖 element 模块，否则无法进行功能性操作
    layui.use('element', function(){
      var element = layui.element;
      
      //…
    });
    </script>
```

相关样式

​	方向 水平 默认的

​			垂直  layui-nav-tree

​	其他样式

​			 layui-nav

​						layui-nav-item  代表这是一个导航菜单一个子项

​										layui-nav-child  子项的子项

​						layui-this 代表被选中的菜单项



## 选项卡

```html
  <div class="layui-tab layui-tab-card " lay-allowClose="true">
    <ul class="layui-tab-title">
      <li class="layui-this">网站设置</li>
      <li>用户管理</li>
      <li>权限分配</li>
      <li>商品管理</li>
      <li>订单管理</li>
    </ul>
    <div class=" layui-tab-content">
      <div class=" layui-tab-item layui-show">1</div>
      <div class=" layui-tab-item">2</div>
      <div class=" layui-tab-item">3</div>
      <div class=" layui-tab-item">4</div>
      <div class=" layui-tab-item">5</div>
    </div>
   </div>
    
   <script src="./layui/layui.js"></script>
<script type="text/javascript">
    //注意：导航 依赖 element 模块，否则无法进行功能性操作
    layui.use(['element'], function(){
      var element = layui.element;
      
      //…
    });
    </script>
```

风格说明：默认风格 只用加layui-tab

​					简洁风格 layui-tab layui-tab-brief

​					卡片风格  "layui-tab layui-tab-card"

默认所有的选项卡风格都支持响应式 而且如果你想要使得选项卡可删除只要加上 **lay-allowClose="true"** 就可以了						

相关样式

layui-tab  带表一个选项卡

​				layui-tab-title 代表卡片的标题

​								layui-this 代表是否被选中、

​				layui-tab-content  代表具体的卡片内容

​								layui-item 代表卡片内容的对应

​								layui-show  这个卡片内容默认显示

## 进度条

相关样式

layui-progress这代表一个进度条

lay-showPercent="true" 显示比例文本

​						layui-progress-bar 代表进度条内部的那个进度

​						lay-percent  代表进度的比例

动态操作进度条     

你肯定不仅仅是满足于进度条的初始化显示，通常情况下你需要动态改变它的进度值，element模块提供了这样的基础方法：*element.progress(filter, percent);*。

你只需要在某个事件或者语句中执行方法：element.progress('demo', '50%');



```html
  <div class="layui-progress layui-progress-big" lay-filter="demo" lay-showPercent="true">
    <div class="layui-progress-bar layui-bg-cyan" lay-percent="20%"></div>
  </div>
  <button class="layui-btn layui-bg-gray change">修改</button>

    <script src="./layui/layui.js"></script>
   <script type="text/javascript">
      //注意：导航 依赖 element 模块，否则无法进行功能性操作
      layui.use(['element','jquery'], function(){
        var element = layui.element;
        var $=layui.jquery;
        var percent=0;
       
        $(".change").click(function () {
         percent+=100;
          element.progress('demo',percent);
          })

      })
   </script>
```



## layui面板

样式风格 卡片面板

​				折叠面板

​						默认折叠

​						手风琴折叠

卡片面板相关样式：

layui-row 代表一行， 一行被分为12块 和之前用的semanticui是一样的

layui-col-space2 表示间隔大小

​		 layui-col-md5 表示占12块中的5快， 代表一列

​		          layui-card  表示一个卡片

​							layui-card-header 表示卡片的头信息

​							layui-card-body 表示卡片的内容

折叠面板相关样式：

layui-collapse 表示这是一个折叠卡片的面板*lay-accordion* 这个可以开启手风琴模式

​		layui-colla-item这是一个折叠卡片

​				layui-colla-title 卡片标题

​				layui-colla-content 卡片内容  layui-show

相关事件：

当折叠面板点击展开或收缩时触发，回调函数返回一个object参数，携带三个成员：

```
element.on('collapse(filter)', function(data){
  console.log(data.show); //得到当前面板的展开状态，true或者false
  console.log(data.title); //得到当前点击面板的标题区域DOM对象
  console.log(data.content); //得到当前点击面板的内容区域DOM对象
});
```

```html
  <!--默认卡片-->
  <div class="layui-progress layui-progress-big" lay-filter="demo" lay-showPercent="true">
    <div class="layui-progress-bar layui-bg-cyan" lay-percent="20%"></div>
  </div>
  <button class="layui-btn layui-bg-gray change">修改</button>
  <div class="layui-row layui-col-space2">
    <div class="layui-col-md6">
      <div class="layui-card">
        <div class="layui-card-header layui-bg-orange">sadasd</div>
        <div class="layui-card-body">
            safsadasdasd
        </div>
      </div>
    </div>
    <div class="layui-col-md3">
      <div class="layui-card">
        <div class="layui-card-header layui-bg-orange">sadsad</div>
        <div class="layui-card-body layui-bg-green">
            sadasdasdasdasdd
        </div>
      </div>
    </div>
    <div class="layui-col-md3">
      <div class="layui-card">
        <div class="layui-card-header layui-bg-orange">ss</div>
        <div class="layui-card-body layui-bg-cyan">
          sadasdasdasdasdd
        </div>
      </div>
    </div>
  </div>
   
 <!--折叠卡片-->
  <div class="layui-collapse" lay-filter="collapse" lay-accordion="true">
    <div class="layui-colla-item">
      <h2 class="layui-colla-title">杜甫</h2>
      <div class="layui-colla-content layui-show">内容区域</div>
    </div>
    <div class="layui-colla-item">
      <h2 class="layui-colla-title">李清照</h2>
      <div class="layui-colla-content layui-show">内容区域</div>
    </div>
    <div class="layui-colla-item">
      <h2 class="layui-colla-title">鲁迅</h2>
      <div class="layui-colla-content layui-show">内容区域</div>
    </div>
  </div>
```



## 布局

相关样式：

layui-row

​		layui-col-xs   移动设备

​		layui-col-sm   平板

​		layui-col-md  座面设备

## 徽章

相关样式：

layui-badge-dot 小圆点

layui-badge   椭圆体

layui-badge-rim   边框体

## 时间线

相关样式

​	layui-timeline 这是一个时间线容器

​			layui-timeline-item 代表一个时间节点

​					layui-timeline-axis  左边的竖线

​					layui-timeline-content 时间线的内容

​					layui-timeline-text 代表文本

​								layui-timeline-title 标题

​								内容直接卸载标题下就行了								

## 动画

layui-anim 这代表引入动画

## 颜色选择器

使用这个我们要加载colorpicker

再通过colorpicker的render方法去设置元素属性

## 滑块

加载'slider'

再通过slider的render方法去设置元素属性

## 评分

加载'rate'

再通过rate的render方法去设置元素属性

也可以自定义文本的回调

## 轮播

## 代码修饰器

## 时间日期选择器

## 表单控件

## 表单对象及相关事件

## 弹出层

## 数据表格

## 文件上传

## layuiDtree

## layuicms