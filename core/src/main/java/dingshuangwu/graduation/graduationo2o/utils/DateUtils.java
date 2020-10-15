package dingshuangwu.graduation.graduationo2o.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    //get date for: yyyyMMddHHmmss
    public static String getFormatDate() {
        //设置日期格式  yyyy-MM-dd HH:mm:ss
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = df.format(new Date());
        String year = now.substring(0, 4);
        String month = now.substring(5, 7);
        String day = now.substring(8, 10);
        String hour = now.substring(11, 13);
        String minute = now.substring(14, 16);
        String second = now.substring(17, 19);
        return year + month + day + hour + minute + second;
    }

    //tansform yyyyMMddHHmmss to yyyy-MM-dd HH:mm:ss
    //tansform yyyyMMdd to yyyy-MM-dd
    public static String tansformDate(String date) {
        if (date.length() == 14) {
            String year = date.substring(0, 4);
            String month = date.substring(4, 6);
            String day = date.substring(6, 8);
            String hour = date.substring(8, 10);
            String minute = date.substring(10, 12);
            String second = date.substring(12, 14);
            return year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
        } else if (date.length() == 8) {
            String year = date.substring(0, 4);
            String month = date.substring(4, 6);
            String day = date.substring(6, 8);
            return year + "-" + month + "-" + day;
        } else {
            return "";
        }
    }
}
