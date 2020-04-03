# 数据结构与算法学习笔记

程序设计=数据结构+算法

传统上，我们把数据结构分为逻辑结构和物理结构

逻辑结构：

四大逻辑结构：

​	①集合结构：

​	②线性结构：

​	③树形结构：

​	④图形结构：

物理结构：

​	顺序存储结构：

​	链式存储结构：

数据结构和算法的关系：

## 1.时间复杂度和空间复杂度

事件复杂度=函数体的时间复杂度*执行次数

![](F:\opensource\項目積纍文檔\数据结构与算法学习笔记\img\LD@NWP$S4R9_867E03K5]UY.png)

```java
int i,j;,
for(i=0;i<n,i++){
    function(i);
}
void function(int count){
    printf("%d",count);
}
```

计算上面代码的时间复杂度

o(n)

假如function改变为如下的代码

```java
void function(int c){
    int j;
    for(j=c;j<n;j++){
        pringf(j);
    }
}
```

那么他的时间复杂度则变为了O(n^2)

![](F:\opensource\項目積纍文檔\数据结构与算法学习笔记\img\PU[`5I3M}A~$[2NHZHC8YLE.png)

## 2.线性表

像是排队一样具有线一样的性质结构

list（线性表）：由零个或多个数据元素组成的**有序**序列，有前驱和后继除了第一个和最后一个，n表示元素的个数当n为零时为空，允许有空表

![](F:\opensource\項目積纍文檔\数据结构与算法学习笔记\img\list1.png)

![](F:\opensource\項目積纍文檔\数据结构与算法学习笔记\img\listAdt.png)

![](F:\opensource\項目積纍文檔\数据结构与算法学习笔记\img\listopetion.png)

![](F:\opensource\項目積纍文檔\数据结构与算法学习笔记\img\llistendadt.png)

List数据结构的接口如下

```java
public interface list {
    //向列表的尾部添加指定的元素
    public abstract int add(Object o);
    //在列表的指定位置插入指定元素
    public abstract void add(int i,Object o);
    // 返回列表中指定位置的元素
    public abstract Object get(int i);
    //返回此列表中第一次出现的指定元素的索引；如果此列表不包含该元素，则返回 -1
    public abstract int indexOf(Object o);
    //从此列表中移除第一次出现的指定元素（如果存在）
    public abstract boolean remove(int i);
    // 用指定元素替换列表中指定位置的元素
    public abstract Object set(int i, Object o);
    // 如果列表不包含元素，则返回 true
    public abstract boolean isEmpty();
    //返回列表中的元素数
    public abstract int size();

}
```

线性表的顺序存储结构：在一段连续的内存内存储相同数据量类型的一系列元素，

![](F:\opensource\項目積纍文檔\数据结构与算法学习笔记\img\arraylist01.png)

代码 如下：

```java
package List;

import java.util.Arrays;

public class arrayList<T> implements list {
    Object[] items;
    int size;
    public arrayList(){
        items = new Object[8];
        size=0;
    }
    //判断长度是否足够
    public void isEnouthLength(){
        if ((size+1)>items.length){//如果长度不够扩张为原来的1.5倍
            items = Arrays.copyOf(items,items.length*3/2);
        }
    }
    
    @Override
    public int add(Object o) {
        isEnouthLength();
        items[size]=o;
        size++;
        return size-1;
    }

    @Override
    public void add(int i, Object o) {
        
        if (i>size||i<0){
            return;
        }
        isEnouthLength();
        for (int j=size-1;j>=i;j--){//将i位置之后的元素依次后移
            items[j+1]=items[j];
        }
        items[i]=o;
        size++;
    }

    @Override
    public Object get(int i) {
        if (i<0||i>size||items[i]==null){
            return null;
        }
        return items[i];
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (o.equals(items[i])){
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean remove(int i) {
        if (i<0||i>=size){
            return false;
        }
        for (int j=i+1;j>size-1;j++){//将第I个以后的元素依次向前
            items[j-1]=items[j];
        }
        return true;
    }

    @Override
    public Object set(int i, Object o) {
        if (i<0||i>=size){
            return null;
        }
        Object _o = items[i];
        items[i]=o;
        return _o;
    }

    @Override
    public boolean isEmpty() {
        
        return size<=0?true:false;
    }

    @Override
    public int size() {
        return size;
    }
}

```



线性表的链式存储结构(单链表)：

```java
package List;

public class linkedList implements list {
    public class Enity{
        Enity next;
        Object item;
        public Enity(){}
        public Enity(Enity next,Object o){
            super();
            this.next=next;
            this.item=o;
        }
    }

    Enity head;//头节点,一般是空节点
    int size;//链表尺寸
    public linkedList(){
        super();
        head = null;
        size=0;
    }
    @Override
    public int add(Object o) {
        Enity enity = new Enity(head,o);
        head=enity;
        size++;
        return 0;
    }

    @Override
    public void add(int i, Object o) {
        if (i<0||i>=size)
            return;
        Enity enity = head;
        int j=0;
        while (j<i-1){
            enity= enity.next;
            j++;
        }
        enity.next=new Enity(enity.next,o);
        size++;
        
    }

    @Override
    public Object get(int i) {
        if (i<0||i>=size)
            return null;
        Enity enity = head;
        int j=0;
        while (j<i-1){
            enity= enity.next;
            j++;
        }
        return enity.item;
    }

    @Override
    public int indexOf(Object o) {
        Enity enity = head;
        int j=0;
        while (j<size&&!o.equals(enity.item)){
            enity= enity.next;
            j++;
        }
        return j;
    }

    @Override
    public boolean remove(int i) {
        return false;
    }

    @Override
    public Object set(int i, Object o) {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }
}

```



## 3.栈和队列

## 4.