package collection;

import java.util.List;
import java.util.Vector;

/*
底層是數組
單綫程的
Arraylist是多綫程的
這是最早期的集合通過addelement（）方法為數組增加長度
 */
public class vectorTest {
    public static void main(String[] args) {
        Vector<String> list = new Vector<>();
        list.add("a");
        System.out.println(list.hashCode());
        list.addElement("b");
        System.out.println(list.hashCode());
        list.addElement("c");
        System.out.println(list.hashCode());
    }
}
