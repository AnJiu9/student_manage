package com.j.sm.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName FormatUtil
 * @Description
 * @Author orange
 * @Date 2020-11-26 14:34
 **/

public class FormatUtil {
    public static String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    public static Date parseDate(String dateString) throws ParseException,IllegalArgumentException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(dateString);
        java.sql.Date sqlDate=new java.sql.Date(date.getTime());
//        } catch (ParseException e) {
//        Date date = null;
//        try {
//            date = sdf.parse(dateString);
//            java.sql.Date sqlDate=new java.sql.Date(date.getTime());
//        } catch (ParseException e) {
//            System.out.println(e.getMessage());
//        }
        return sqlDate;
    }

    public static String formatGender(int gender) {
        if (gender == 0) {
            return "保密";
        } else if (gender == 1) {
            return "男";
        } else {
            return "女";
        }
    }
}
