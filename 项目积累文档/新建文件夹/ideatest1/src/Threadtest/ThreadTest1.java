package Threadtest;

import java.util.concurrent.*;

public class ThreadTest1 {
    public static void main(String[] args) {

        //new Thread(new r()).start();
//        new r().run();
//        new r().start();
//        new r2().run();
//        new Thread(new r2()).start();
//        FutureTask<String> f = new FutureTask<String>(new r3());
//        new Thread(f).start();
//        try {
//            System.out.println(f.get());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }

        ExecutorService executorService = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 20; i++) {
            executorService.submit(new r3());
        }
        executorService.shutdown();
        for (int i = 0; i < 20; i++) {
            System.out.println("main"+i);
        }

    }
}
class r extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println("thread"+i);
        }
    }
}
class r2 implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println("r2"+i);
        }
    }
}
class r3 implements Callable<String>{

    @Override
    public String call() throws Exception {
        for (int i = 0; i < 20; i++) {
            System.out.println("r3"+i);
        }
        return "r3";
    }
}
