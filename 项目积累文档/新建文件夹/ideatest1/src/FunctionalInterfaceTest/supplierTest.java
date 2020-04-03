package FunctionalInterfaceTest;

import java.util.List;
import java.util.function.Supplier;

/*
    被称为生产性接口
    指定接口反省是什么类型，那么接口中的get方法就会生产什么类型数据
 */
public class supplierTest {
//    public static String getString(Supplier<String> stringSupplier){
//        return stringSupplier.get();
//    }

    public static void main(String[] args) {
        //String s = getString(()-> "string");
        int[] ints = {100,20,25,30,36};
        int ii = getInt(()->{
            int max = ints[0];
            for (int i: ints) {
                if(i>max){
                    max=i;
                }
            }
            return max;
        });
        System.out.println(ii);

    }
    public static int getInt(Supplier<Integer> integerSupplier){
        return integerSupplier.get();
    }
}
