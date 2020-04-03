package Threadtest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 常用方法：
 * String getname()獲取此綫程的名稱
 * static Thread currentThread()獲取當前正在執行的綫程
 *setName()設置綫程名稱
 * sleep()是當前的綫程暫停并且在指定的毫秒數結束之後繼續進行
 */
public class ThreadTest2 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            executorService.submit(new t());
            System.out.println(Thread.currentThread().getName());
            Thread t = Thread.currentThread();
        }
        executorService.shutdown();
    }
}
class t extends Thread{
    @Override
    public void run() {
        try {
            sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("threadNmae"+getName());
    }
}