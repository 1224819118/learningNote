package annotiation;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class checkTest {
    public static void main(String[] args) throws IOException {
        int number =0;
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("日志.txt"));
        //创建计算器对象
        jisuanqi jisuanqi = new jisuanqi();
        //获取计算器字节码文件，用于获取方法
        Class jisuanqiClass = jisuanqi.getClass();
        //获取方法
        Method[] methods = jisuanqiClass.getMethods();
        //循环遍历判断方法是否有check注解
        for (Method m: methods) {
            //如果有，则执行方法
            if(m.isAnnotationPresent(check.class)){
                //对出现的异常进行捕获，并打印信息
                try {
                    m.invoke(jisuanqi);
                } catch (Exception e) {
                    bufferedWriter.write("出现了"+e.getClass().getSimpleName()+"异常");
                    bufferedWriter.newLine();
                    bufferedWriter.write("异常的信息是："+e.getCause().getMessage());
                    bufferedWriter.newLine();
                    number++;
                    bufferedWriter.write("----------------------------------");
                    bufferedWriter.newLine();
                }
            }

        }

        //打印异常总次数
        bufferedWriter.write("总共出现了"+number+"次异常");
        bufferedWriter.flush();
        bufferedWriter.close();
    }
}
