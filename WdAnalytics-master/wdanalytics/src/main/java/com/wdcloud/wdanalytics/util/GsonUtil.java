package com.wdcloud.wdanalytics.util;

import com.google.gson.Gson;

/**
 * Info:
 * Created by Umbrella.
 * CreateTime: 2020/3/28 15:12
 */
public class GsonUtil {
    private static Gson gsonInstance = new Gson();
    public static Gson getGsonInstance(){
        return gsonInstance;
    }
}
