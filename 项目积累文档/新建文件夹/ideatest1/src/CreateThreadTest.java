import java.util.concurrent.*;

public class CreateThreadTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
     //   runnableDemo runnableDemo1 = new runnableDemo();
     //   new Thread(runnableDemo1).start();
      //  ThreadDemo threadDemo1 = new ThreadDemo();
     //   new Thread(threadDemo1).start();
     //   CallableDemo callableDemo1 = new CallableDemo();
     //   FutureTask<Integer> futureTask = new FutureTask<Integer>(callableDemo1);
    //    new Thread(futureTask).start();
      //  int i = futureTask.get();
      //  System.out.println(i);
        ExecutorService pool = Executors.newFixedThreadPool(5);
        for (int j = 0; j < 5; j++) {
            pool.submit(new Thread(new RunnableTest()));
        }
        pool.shutdown();
    }

}
//方式一
class runnableDemo implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }
    }

}
//方式二
class ThreadDemo extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }
    }
}
//實現callable接口方式
class CallableDemo implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        int j = 0;
        for (int i = 0; i < 10; i++) {
            j+=i;
        }
        return j;
    }
}
class RunnableTest implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}