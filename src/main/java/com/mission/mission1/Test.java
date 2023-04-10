package com.mission.mission1;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
    public static void main(String[] args) {
        String tempDate = "1681005498000";
        long timestamp = Long.parseLong(tempDate);
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        Date date = new Date();
        date.setTime(timestamp);
        String dateTime = sdf.format(date);
        System.out.println(dateTime);

        // 1681005498000 -> 2023-04-09 10:58:18.0
        // 1681005500000 -> 2023-04-09 10:58:20.0
    }
}
