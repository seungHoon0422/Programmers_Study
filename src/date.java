import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class date {
    public static void main(String[] args) {

        int year = Integer.valueOf((int) (Math.floor(Math.random() * 9000)+1));
        int month = Integer.valueOf((int) (Math.floor(Math.random() * 12) + 1));

        int[] days = {0,31,28,31,30,31,30,31,31,30,31,30,31};
        int day = days[month];
        if(month == 2) {
            if((year%4==0) && ((year%100!=0) || (year%400==0))) day = 29;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(day).append(" days for ").append(year).append("-").append(month);
        System.out.println(sb);


    }
}
