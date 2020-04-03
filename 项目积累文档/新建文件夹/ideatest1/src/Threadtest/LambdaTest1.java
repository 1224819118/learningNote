package Threadtest;

/**
 *lambda表達式由三部分構成：
 * 1.一些參數
 * 2.一個箭頭
 * 3.一段代碼
 * （參數列表）->{代碼}
 * （）：接口中抽象方法的參數列表
 *
 *
 *
 */
public class LambdaTest1 {
    public static void main(String[] args) {
        //使用lambda表達式實現新的綫程
        new Thread(()->{
            System.out.println("新建了一個綫程");
        }).start();
    }
}
