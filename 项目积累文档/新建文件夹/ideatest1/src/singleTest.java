import javax.management.timer.Timer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class singleTest {
    public static void main(String[] args) {
     /*   new Thread(new Runnable() {
            @Override
            public void run() {
                user user = single.getUser();
                System.out.println(user);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                user user = single.getUser();
                System.out.println(user);
            }
        }).start();*/
        ExecutorService pool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            pool.submit(() -> {
                user user = single.getUser();
                System.out.println(user);
            });

        }


    }
}
class user{
    public user(){
        System.out.printf("create new user");
    }
}

/**
 *
 */
class single{
    private static user user;
    public static user getUser(){
       synchronized (user.class){
           if (user==null){
               try {
                   Thread.sleep(200);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               user = new user();
           }
       }
        return user;
    }
}

