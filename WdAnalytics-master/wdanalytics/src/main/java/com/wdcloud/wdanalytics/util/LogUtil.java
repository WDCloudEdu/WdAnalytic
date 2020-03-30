package com.wdcloud.wdanalytics.util;

import android.util.Log;

import static com.wdcloud.wdanalytics.WdAnalytics.isDebug;

/**
 * Info:全局日志控制
 * Created by Yanxin.
 * CreateTime: 2019/11/29 14:55
 */
public class LogUtil {
    public static void e(String tag, String message) {
        if (isDebug) {
            Log.e(tag, message);
        }
    }

    public static void w(String tag, String message) {
        if (isDebug) {
            Log.w(tag, message);
        }
    }

    public static void w(String message) {
        w("warn", message);
    }

    public static void i(String tag, String message) {
        if (isDebug) {
            Log.i(tag, message);
        }
    }

    public static void i(String message) {
        i("info", message);
    }

    public static void d(String tag, String message) {
        if (isDebug) {
            Log.d(tag, message);
        }
    }

    public static void d(String message) {
        d("isDebug", message);
    }

    public static void v(String tag, String message) {
        if (isDebug) {
            Log.v(tag, message);
        }
    }

    public static void v(String message) {
        v("verbose", message);
    }

}
