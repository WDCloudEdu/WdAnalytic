package com.wdcloud.wdanalytics.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import static com.wdcloud.wdanalytics.util.NetWorkUtils.getAPNType;

/**
 * Info:
 * Created by Umbrella.
 * CreateTime: 2020/4/8 10:53
 */
public class NetWorkStateReciver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        AnalyticsSharedPreference instance = AnalyticsSharedPreference.getInstance();
        String ip = NetWorkUtils.getipAdress(context);
        String apnType = getAPNType(context);
        instance.saveData("ApnType",apnType);
        instance.saveData("IP",ip);
        LogUtil.e("网络状态",apnType+"----ip:"+ip);
    }
}
