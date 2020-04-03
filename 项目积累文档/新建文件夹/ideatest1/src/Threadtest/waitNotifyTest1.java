package Threadtest;

/**
 * void wait()
 * 导致当前线程等待，直到另一个线程调用该对象的 notify()方法或 notifyAll()方法。
 * void wait(long timeout)
 * 导致当前线程等待，直到另一个线程调用 notify()方法或该对象的 notifyAll()方法，或者指定的时间已过。
 * void wait(long timeout, int nanos)
 * 导致当前线程等待，直到另一个线程调用该对象的 notify()方法或 notifyAll()方法，或者某些其他线程中断当前线程，或一定量的实时时间。
 *
 * 喚醒操作會使當前綫程進入等待
 * void notify()
 * 唤醒正在等待对象监视器的单个线程。
 * void notifyAll()
 * 唤醒正在等待对象监视器的所有线程。
 */
public class waitNotifyTest1 {
    public static void main(String[] args) {
        Object object = new Object();
        new Thread(new Runnable() {
            @Override
            public void run() {
               synchronized (object){
                   System.out.println("顧客點餐");
                   try {
                       object.wait();
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
                System.out.println("顧客可以吃東西了");
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (object){
                    System.out.println("正在做菜");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    object.notify();
                    System.out.println("上菜");
                }
            }
        }).start();

    }
}
