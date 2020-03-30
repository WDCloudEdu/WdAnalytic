package com.wdcloud.wdanalytics.util;

import android.content.Context;

import com.wdcloud.wdanalytics.bean.WdAnalyticsBean;
import com.wdcloud.wdanalytics.bean.WdAnalyticsCrashBean;

/**
 * Info:基本参数配置工厂
 * Created by Yanxin.
 * CreateTime: 2019/12/2 11:34
 */
public class AnalyticsBeanFactory {
    public static WdAnalyticsCrashBean getCrashWdAanlyticsFactory(){
        AnalyticsSharedPreference instance = AnalyticsSharedPreference.getInstance();
        String PackgeName= (String) instance.getData("PackgeName","");
        String DeviceBrand= (String) instance.getData("DeviceBrand","");
        String OsVersion= (String) instance.getData("OsVersion","");
        String AppVersion= (String) instance.getData("AppVersion","");
        String Project= (String) instance.getData("Project","");
        String PhoneId= (String) instance.getData("PhoneId","");
        String Manufacturer= (String) instance.getData("Manufacturer","");
        String ScreenWidth= (String) instance.getData("ScreenWidth","");
        String ScreenHeight= (String) instance.getData("ScreenHeight","");
        String SdkVersion= (String) instance.getData("SdkVersion","");
        String VersionName= (String) instance.getData("VersionName","");
        String updateTime = TimeUtil.getTimeStame();
        WdAnalyticsCrashBean crashwdAnalyticsBean = new WdAnalyticsCrashBean(PhoneId,Project,PackgeName,Manufacturer,DeviceBrand,"Android",OsVersion,VersionName);
        crashwdAnalyticsBean.setUpdateTime(updateTime);
        crashwdAnalyticsBean.setScreenWidth(Integer.parseInt(ScreenWidth));
        crashwdAnalyticsBean.setScreenHeight(Integer.parseInt(ScreenHeight));
        crashwdAnalyticsBean.setSdkVersion(SdkVersion);
        return crashwdAnalyticsBean;
    }
    public static WdAnalyticsBean getWdAanlyticsFactory(){
        AnalyticsSharedPreference instance = AnalyticsSharedPreference.getInstance();
        String PackgeName= (String) instance.getData("PackgeName","");
        String DeviceBrand= (String) instance.getData("DeviceBrand","");
        String OsVersion= (String) instance.getData("OsVersion","");
        String AppVersion= (String) instance.getData("AppVersion","");
        String Project= (String) instance.getData("Project","");
        String PhoneId= (String) instance.getData("PhoneId","");
        String Manufacturer= (String) instance.getData("Manufacturer","");
        String ScreenWidth= (String) instance.getData("ScreenWidth","");
        String ScreenHeight= (String) instance.getData("ScreenHeight","");
        String SdkVersion= (String) instance.getData("SdkVersion","");
        String VersionName= (String) instance.getData("VersionName","");
        String distinctId= (String) instance.getData("distinctId","");
        String locationCode= (String) instance.getData("locationCode","");
        String channel= (String) instance.getData("channel","");
        String updateTime = TimeUtil.getTimeStame();
        WdAnalyticsBean wdAnalyticsBean = new WdAnalyticsBean(PhoneId,Project,PackgeName,Manufacturer,DeviceBrand,"Android",OsVersion,VersionName);
        wdAnalyticsBean.setUpdateTime(updateTime);
        wdAnalyticsBean.setScreenWidth(Integer.parseInt(ScreenWidth));
        wdAnalyticsBean.setScreenHeight(Integer.parseInt(ScreenHeight));
        wdAnalyticsBean.setSdkVersion(SdkVersion);
        wdAnalyticsBean.setChannel(channel);
        wdAnalyticsBean.setDistinctId(distinctId);
        wdAnalyticsBean.setLocationCode(locationCode);
        return wdAnalyticsBean;
    }
    public static void saveWdAanlyticsFactory(Context context,String project,int millis){
        DeviceInfoUtils deviceInfoUtils = new DeviceInfoUtils(context);
        AnalyticsSharedPreference instance = AnalyticsSharedPreference.getInstance();
        if(millis==0)
        {
            instance.saveData("Millis",5);
        }
        else
        {
            instance.saveData("Millis",millis);
        }
        instance.saveData("PackgeName",deviceInfoUtils.getPackgeName());
        instance.saveData("DeviceBrand",deviceInfoUtils.getDeviceBrand());
        instance.saveData("OsVersion",deviceInfoUtils.getOsVersion());
        instance.saveData("AppVersion",deviceInfoUtils.getVersionCode());
        instance.saveData("Manufacturer",deviceInfoUtils.getDeviceManufacturer());
        instance.saveData("Project",project);
        instance.saveData("PhoneId",deviceInfoUtils.getPhoneId());
        instance.saveData("ScreenWidth",deviceInfoUtils.getDeviceWidth());
        instance.saveData("ScreenHeight",deviceInfoUtils.getDeviceHeight());
        instance.saveData("SdkVersion",deviceInfoUtils.getSdkVersion());
        instance.saveData("VersionName",deviceInfoUtils.getVersionName());
    }
}
