package com.wdcloud.wdanalytics.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;

import java.util.UUID;

/**
 * Info:获取手机基本信息工具
 * Created by Yanxin.
 * CreateTime: 2019/11/26 14:30
 */
public class DeviceInfoUtils {
    private static String HANNEL_ID = "";
    private Context context;

    public DeviceInfoUtils(Context context) {
        this.context = context;
    }

    /**
     * 获取设备宽度（px）
     */
    public String getDeviceWidth() {
        return context.getResources().getDisplayMetrics().widthPixels+"";
    }

    /**
     * 获取设备高度（px）
     */
    public String getDeviceHeight() {
        return context.getResources().getDisplayMetrics().heightPixels+"";
    }

    /**
     * 获取厂商名
     **/
    public String getDeviceManufacturer() {
        return Build.MANUFACTURER;
    }

    /**
     * 获取手机品牌
     */
    public String getDeviceBrand() {
        return Build.BRAND;
    }

    /**
     * 获取手机型号
     */
    public String getDeviceModel() {
        return Build.MODEL;
    }

    /**
     * 获取包名
     */
    public String getPackgeName() {
        return context.getPackageName();
    }

    /**
     * 获取渠道号
     */
    public String getChannelData() {
        if (TextUtils.isEmpty(HANNEL_ID)) {
            if (context == null) {
                return null;
            }
            try {
                PackageManager packageManager = context.getPackageManager();
                if (packageManager != null) {
                    ApplicationInfo applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
                    if (applicationInfo != null) {
                        if (applicationInfo.metaData != null) {
                            HANNEL_ID = applicationInfo.metaData.getString("Umeng_CHANNEL");
                        }
                    }
                }
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        return HANNEL_ID;
    }

    public String getOsVersion() {
        return android.os.Build.VERSION.SDK_INT + "";
    }

    /**
     * 获取当前本地apk的版本
     *
     * @param
     * @return
     */
    public String getVersionCode() {
        int versionCode = 0;
        try {
            //获取软件版本号，对应AndroidManifest.xml下android:versionCode
            versionCode = context.getPackageManager().
                    getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode + "";
    }

    //获取手机唯一标识
    public String getPhoneId() {
        String serial = null;

        String m_szDevIDShort = "35" +
                Build.BOARD.length()%10+ Build.BRAND.length()%10 +

                Build.CPU_ABI.length()%10 + Build.DEVICE.length()%10 +

                Build.DISPLAY.length()%10 + Build.HOST.length()%10 +

                Build.ID.length()%10 + Build.MANUFACTURER.length()%10 +

                Build.MODEL.length()%10 + Build.PRODUCT.length()%10 +

                Build.TAGS.length()%10 + Build.TYPE.length()%10 +

                Build.USER.length()%10 ; //13 位

        try {
            serial = android.os.Build.class.getField("SERIAL").get(null).toString();
            //API>=9 使用serial号
            return new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
        } catch (Exception exception) {
            //serial需要一个初始化
            serial = "serial"; // 随便一个初始化
        }
        //使用硬件信息拼凑出来的15位号码
        return new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
    }

    /**
     * 得到全局唯一UUID
     */
    private String uuid;

    public String getUUID() {
        AnalyticsSharedPreference.init(context);
        AnalyticsSharedPreference instance = AnalyticsSharedPreference.getInstance();
        if (TextUtils.isEmpty(uuid)) {
            uuid = UUID.randomUUID().toString();
            instance.saveData("uuid", uuid);
        } else {
            uuid = (String) instance.getData("uuid", "");
        }
        return uuid;
    }

/*    public void screenSize() {
        AnalyticsSharedPreference instance = AnalyticsSharedPreference.getInstance();
        WindowManager systemService = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = systemService.getDefaultDisplay();
        Point outPoint = new Point();
        if (Build.VERSION.SDK_INT >= 19) {
            // 可能有虚拟按键的情况
            display.getRealSize(outPoint);
        } else {
//             不可能有虚拟按键
            display.getSize(outPoint);
        }
        int mRealSizeWidth;// 手机屏幕真实宽度
        int mRealSizeHeight;// 手机屏幕真实高度
        mRealSizeHeight = outPoint.y;
        mRealSizeWidth = outPoint.x;
        instance.saveData("screenWidth",mRealSizeWidth+"");
        instance.saveData("screenHeight",mRealSizeHeight+"");
    }*/
    public String getSdkVersion()
    {
        int version = android.os.Build.VERSION.SDK_INT;
        return version+"";
    }
    public String getVersionName(){
        String versionName="";
        try {
            versionName = context.getPackageManager().getPackageInfo(
                    getPackgeName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }
}
