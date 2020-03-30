package com.wdcloud.wdanalytics.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Info:统计使用Sp
 * Created by Yanxin.
 * CreateTime: 2019/11/28 15:50
 */
public class AnalyticsSharedPreference {

    public static final String FILE_NAME = "wdanalytics_data";
    public static SharedPreferences mSharedPreferences;// 单例
    public static AnalyticsSharedPreference instance;// 单例
    public AnalyticsSharedPreference(Context context) {
        mSharedPreferences = context.getSharedPreferences(FILE_NAME,
                Context.MODE_MULTI_PROCESS);
    }
    /**
     * 初始化单例
     * @param context
     */
    public static synchronized void init(Context context) {
        if (instance == null) {
            instance = new AnalyticsSharedPreference(context);
        }
    }

    /**
     * 获取单例
     *
     * @return
     */
    public static AnalyticsSharedPreference getInstance() {
        if (instance == null) {
            throw new RuntimeException("class should init!");
        }
        return instance;
    }

    /**
     * 保存数据
     *
     * @param key
     * @param data
     */
    public void saveData(String key, Object data) {
        String type = data.getClass().getSimpleName();

        SharedPreferences.Editor editor = mSharedPreferences.edit();

        if ("Integer".equals(type)) {
            editor.putInt(key, (Integer) data);
        } else if ("Boolean".equals(type)) {
            editor.putBoolean(key, (Boolean) data);
        } else if ("String".equals(type)) {
            editor.putString(key, (String) data);
        } else if ("Float".equals(type)) {
            editor.putFloat(key, (Float) data);
        } else if ("Long".equals(type)) {
            editor.putLong(key, (Long) data);
        }

        editor.commit();
    }

    /**
     * 得到数据
     *
     * @param key
     * @param defValue
     * @return
     */
    public Object getData(String key, Object defValue) {

        String type = defValue.getClass().getSimpleName();
        if ("Integer".equals(type)) {
            return mSharedPreferences.getInt(key, (Integer) defValue);
        } else if ("Boolean".equals(type)) {
            return mSharedPreferences.getBoolean(key, (Boolean) defValue);
        } else if ("String".equals(type)) {
            return mSharedPreferences.getString(key, (String) defValue);
        } else if ("Float".equals(type)) {
            return mSharedPreferences.getFloat(key, (Float) defValue);
        } else if ("Long".equals(type)) {
            return mSharedPreferences.getLong(key, (Long) defValue);
        }
        return null;
    }
    public void clearData(Context context)
    {
        SharedPreferences preferences = context.getSharedPreferences("wdanalytics_data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
    }
}
