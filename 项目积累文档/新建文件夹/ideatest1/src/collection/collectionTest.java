package collection;

import java.util.ArrayList;
import java.util.Collection;

/*
     boolean isEmpty();判斷是否爲空
    boolean contains(Object var1);判斷集合中是否包含某個元素
    Iterator<E> iterator();
    Object[] toArray();將集合轉換爲一個數組
    <T> T[] toArray(T[] var1);
    boolean add(E var1);加入
    boolean remove(Object var1);移除
    boolean containsAll(Collection<?> var1);
    boolean addAll(Collection<? extends E> var1);
    boolean removeAll(Collection<?> var1);
    boolean retainAll(Collection<?> var1);
    void clear();清空集合
    boolean equals(Object var1);
    int size();獲取集合長度
 */
public class collectionTest {
    public static void main(String[] args) {
        Collection<String> collection = new ArrayList<>();
        collection.add("a");
        collection.add("b");
        Collection<String> collection1 = new ArrayList<>();
        collection1.addAll(collection);
        collection1.remove("a");
        System.out.println(collection1.size());
    }
}
