package collection;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * set繼承了collection接口
 * 沒有索引也沒有重複元素并且不能使用普通的for元素遍歷可以使用增强for遍歷
 * hashset實現了set接口
 * 是個無序的集合
 * 底層是一個hash表結構：查詢非常快
 */
public class HashSetTest {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        set.add("a");
        set.add("b");
        set.add("b");
        set.add("c");
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }
}
