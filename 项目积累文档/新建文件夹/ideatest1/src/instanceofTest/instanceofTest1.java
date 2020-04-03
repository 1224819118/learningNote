package instanceofTest;

public class instanceofTest1 {
    interface zimu{
        public void show();
    }
    static class A implements zimu{
        A(){
            System.out.println("create A");
        }

        @Override
        public void show() {
            System.out.println("show A");
        }
    }
    class B implements zimu{
        B(){
            System.out.println("create B");
        }

        @Override
        public void show() {
            System.out.println("show B");
        }
    }
    public static void main(String[] args) {
        zimu zimu = new A();
        if (zimu instanceof A){
            zimu.show();
        }
    }
}
