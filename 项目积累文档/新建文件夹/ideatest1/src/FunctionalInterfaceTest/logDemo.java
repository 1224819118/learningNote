package FunctionalInterfaceTest;

public class logDemo {

    public static   void show(int i,log log){
        if (i==1){
            System.out.println(log.pinjie());
        }

    }
    public static void main(String[] args) {

        String s1 = "ss";
        String s2 = "ss";
        String s3 = "ss";
        show(2,()->{
            return s1+s2+s3;
        });


    }

}


@FunctionalInterface
interface log{
    //拼接消息的方法
    public abstract String pinjie();
}
