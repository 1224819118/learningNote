package collection;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 不能使用多態創建List<String> list = new LinkedList<String>();這是不對的
 * 底層是一個鏈表結構
 * 查詢慢增刪快
 * 擁有大量對首位元素操作的方法
 */
public class LinkListTest {
    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<String>();
        list.add("a");
        list.push("b");
        list.addFirst("c");
        Iterator<String> iterator = list.iterator();
        iterator(iterator);
       list.removeFirst();
       list.pop();
       list.clear();

    }
    public static void iterator(Iterator<String> iterator){
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
