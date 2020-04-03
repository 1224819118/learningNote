package Threadtest;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 有三種解決這個問題的方法
 * 1.同步代碼塊解決
 * 2.同步方法解決
 * 3.靜態同步方法解決
 * 4.lock鎖解決
 */
public class ThreadTest3 {
    public static void main(String[] args) {
        ticket4 t = new ticket4();
        new Thread(t).start();
        new Thread(t).start();
        new Thread(t).start();
    }

}
//第一種方法
class ticket implements Runnable{

    private int ticketNumber = 100;
    Object o = new Object();
    @Override
    public void run() {
        synchronized (o){
            while (ticketNumber>0){
                System.out.println("賣出第"+ticketNumber+"張票");
                ticketNumber--;
            }
        }
    }
}
//第二種方法
//鎖對象是this
class ticket2 implements Runnable{

    private int ticketNumber = 100;
    @Override
    public  void run() {
            payTicket();
    }
    public synchronized void payTicket(){
        while (ticketNumber>0){
            System.out.println("賣出第"+ticketNumber+"張票");
            ticketNumber--;
        }
    }
}
//第三種解決方法
//鎖對象是本類的class屬性
class ticket3 implements Runnable{

    private static int ticketNumber = 100;
    @Override
    public  void run() {
        payTicket();
    }
    public static synchronized void payTicket(){
        while (ticketNumber>0){
            System.out.println("賣出第"+ticketNumber+"張票");
            ticketNumber--;
        }
    }
}
//第四種
//lock 中有lock 和unlock兩個方法
//lock是一個接口
//我們要在成員位置中使用的是lock的具體的實現類
class ticket4 implements Runnable{

    private int ticketNumber = 100;
    Lock lock = new ReentrantLock();
    @Override
    public void run() {
            lock.lock();
            try {
                while (ticketNumber>0){
                    System.out.println("賣出第"+ticketNumber+"張票");
                    ticketNumber--;
                }
            }catch (Exception e ){
                System.out.println(e.getMessage());
            }finally {
                //將釋放鎖放在finally中可以保證無論程序是否與到異常都會釋放鎖
                lock.unlock();
            }


    }
}
