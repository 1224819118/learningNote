package CalendarTest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * java.util.Calendar
 * 它是一個抽象類，無法直接創建生成具體的對象，可以通過它的一個靜態方法getInstance()來獲取子類對象
 * 常用方法：
 * get  ：int 獲取給定字段的值
 * set  :void 為給定日曆設定字段值
 * add  :void 爲日裏添加或者減去時間量
 * getTime  :Date  返回日期對象
 */
public class ClendarTest {
    public static void main(String[] args) {
        ClendarTest cc = new ClendarTest();
        Calendar c = Calendar.getInstance();
        cc.setTest(c);
        cc.getTest(c);
        cc.addTest(c);
        cc.getTest(c);
        cc.getTimeTest(c);
    }
    /*

     */
    public void getTest(Calendar calendar){
        int y = calendar.get(Calendar.YEAR);
        int m = calendar.get(Calendar.MONTH)+1;
        int d = calendar.get(Calendar.DATE);
        System.out.println(y+"-"+m+"-"+d);
    }
    /*
    西方的月份是0-11月所以要少些一個月
     */
    public void setTest(Calendar calendar){
        calendar.set(Calendar.YEAR,1999);
        calendar.set(1999,3,18);
    }
    /*
    傳進來的是正數就是增加
    負數就是減少
     */
    public void addTest(Calendar calendar){
        calendar.add(Calendar.YEAR,20);
        calendar.add(Calendar.MONTH,-2);
    }

    public void getTimeTest(Calendar calendar){
        Date date = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        String format = sdf.format(date);
        System.out.println(format);
    }
}
