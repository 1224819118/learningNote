package collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
/*
        public boolean hasNext() 判斷有沒有下一個元素
        public String next()  取出下一個元素
        iterator是一個接口，我們要通過Collection中的Iterator方法獲取對應的迭代器對象
 */
public class iteratorTest {
    public static void main(String[] args) {
        Collection<String> collection = new ArrayList<>();
        collection.add("a");
        collection.add("b");
        collection.add("c");
        collection.add("d");
        Iterator<String> iterator = collection.iterator();
        while(iterator.hasNext()){
            String next = iterator.next();
            System.out.println(next);
        }
    }
}
