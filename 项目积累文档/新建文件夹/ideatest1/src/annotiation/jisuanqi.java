package annotiation;

public class jisuanqi {
    @check
    public void add(){
        String s=null;
        s.toString();//有空指针异常
        System.out.println("1+1="+(1+1));
    }
    @check
    public void sub(){
        System.out.println("1-0="+(1-0));
    }
    @check
    public void cheng(){
        System.out.println("1*0="+(1*0));
    }
    @check
    public void chu(){
        System.out.println("1/0="+(1/0));//有算数异常
    }
    public void show(){
        System.out.println("这个方法不会有异常");
    }
}
