package data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/***
 *formate獎指定的日期轉化爲相應格式的字符串
 * parse將字符串解析為日期
 */
public class dataTest {
    public static void main(String[] args) {
        String data = "1999年04月18日";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd");
        Date date = new Date();
        String da = sdf.format(date);
        System.out.println(da);
        Date date2= new Date();
        try {
            date2 = sdf.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(date2);
    }
}
