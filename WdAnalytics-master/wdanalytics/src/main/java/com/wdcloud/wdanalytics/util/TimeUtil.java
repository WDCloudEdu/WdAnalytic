package com.wdcloud.wdanalytics.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Info:时间工具
 * Created by Yanxin.
 * CreateTime: 2019/12/2 14:28
 */
public class TimeUtil {
    public static String longToDate(){
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sd.format(date);
    }
//    public static String getTimeDifference(String strTime1, String strTime2) {
//        try{
//            Date now = df.parse(strTime1);
//            Date date=df.parse(strTime2);
//            long l= now.getTime()-date.getTime();       //获取时间差
//            long day=l/(24*60*60*1000);
//            long hour=(l/(60*60*1000)-day*24);
//            long min=((l/(60*1000))-day*24*60-hour*60);
//            long s=(l/1000-day*24*60*60-hour*60*60-min*60);
//            differentTime=day+"天"+hour+"小时"+min+"分"+s+"秒";
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        return differentTime;
//    }
    public static String getTimeDifference(String strTime1, String strTime2){
        String differentTime="";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date now = df.parse(strTime1);
            Date date=df.parse(strTime2);
            long l= date.getTime()-now.getTime();       //获取时间差
            int time=(int)(l/1000);
            differentTime=time+"";
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return differentTime;
    }
    public static String getTimeStame() {
        //获取当前的毫秒值
        long time = System.currentTimeMillis();
        //将毫秒值转换为String类型数据
        String time_stamp = String.valueOf(time);
        //返回出去
        return time_stamp;
    }
}
