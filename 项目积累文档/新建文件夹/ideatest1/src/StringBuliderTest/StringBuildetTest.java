package StringBuliderTest;

import java.lang.StringBuilder;
/**
 *
 @HotSpotIntrinsicCandidate
 public StringBuilder() {
 @HotSpotIntrinsicCandidate
 public StringBuilder(String str)
 *  常用方法：
 *   append 返回值是當前對象
 *  toString
 */

public class StringBuildetTest {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("test");
        StringBuilder sb2 = new StringBuilder();
        sb2.append(sb);
        System.out.println(sb2.hashCode());
        sb2.append("1");
        //鏈式編程
        sb2.append(2).append(3).append(4);
        String s = sb2.toString();
        System.out.println(sb2);
        System.out.println(s);
        System.out.println(sb2.hashCode());
        System.out.println(s.hashCode());

    }

}
