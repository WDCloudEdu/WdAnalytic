package com.wdcloud.wdanalytics.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Info:上传奔溃服务Bean
 * Created by Yanxin.
 * CreateTime: 2019/12/10 16:38
 */
public class WdAnalyticsCrashBean {
    /**
     * 基本参数说明
     * $originalId":  ”设备ID”,
     * "distinctId":  ”登录用户ID”,
     * "$project": “app名称”,
     * "$packageName": “包名”
     * "locationCode": “武侯/青岛”
     * "channel":   “渠道号  AppStore”
     * "$manufacturer":  ”设备厂商”,
     * "$model": “设备型号”,
     * "$os”:"  “操作系统”,
     * "$osVersion":  ”操作系统版本”,
     * "screenWidth":  ”屏幕宽度”,
     * "screenHeight": ”屏幕高度”,
     * "$appVersion":  ”app版本”,
     * "sdkVersion":  “统计sdk版本”
     * "updateTime":  “上传时间”
     */
    private String $originalId;
    private String distinctId;
    private String $project;
    private String $packageName;
    private String locationCode;
    private String channel;
    private String $manufacturer;
    private String $model;
    private String $os;
    private String $osVersion;
    private int screenWidth;
    private int screenHeight;
    private String $appVersion;
    private String sdkVersion;
    private String updateTime;
    private List<CrashAnalyticsBeans> records;

    public WdAnalyticsCrashBean(String originalId, String project, String packageName, String manufacturer, String model, String os, String osVersion, String appVersion) {
        this.$originalId = originalId;
        this.$project = project;
        this.$packageName = packageName;
        this.$manufacturer = manufacturer;
        this.$model = model;
        this.$os = os;
        this.$osVersion = osVersion;
        this.$appVersion = appVersion;
    }

    public String get$originalId() {
        return $originalId == null ? "" : $originalId;
    }

    public void set$originalId(String $originalId) {
        this.$originalId = $originalId == null ? "" : $originalId;
    }

    public String get$project() {
        return $project == null ? "" : $project;
    }

    public void set$project(String $project) {
        this.$project = $project == null ? "" : $project;
    }

    public String get$packageName() {
        return $packageName == null ? "" : $packageName;
    }

    public void set$packageName(String $packageName) {
        this.$packageName = $packageName == null ? "" : $packageName;
    }

    public String get$manufacturer() {
        return $manufacturer == null ? "" : $manufacturer;
    }

    public void set$manufacturer(String $manufacturer) {
        this.$manufacturer = $manufacturer == null ? "" : $manufacturer;
    }

    public String get$model() {
        return $model == null ? "" : $model;
    }

    public void set$model(String $model) {
        this.$model = $model == null ? "" : $model;
    }

    public String get$os() {
        return $os == null ? "" : $os;
    }

    public void set$os(String $os) {
        this.$os = $os == null ? "" : $os;
    }

    public String get$osVersion() {
        return $osVersion == null ? "" : $osVersion;
    }

    public void set$osVersion(String $osVersion) {
        this.$osVersion = $osVersion == null ? "" : $osVersion;
    }

    public String get$appVersion() {
        return $appVersion == null ? "" : $appVersion;
    }

    public void set$appVersion(String $appVersion) {
        this.$appVersion = $appVersion == null ? "" : $appVersion;
    }

    public String getDistinctId() {
        return distinctId == null ? "" : distinctId;
    }

    public void setDistinctId(String distinctId) {
        this.distinctId = distinctId == null ? "" : distinctId;
    }


    public String getLocationCode() {
        return locationCode == null ? "" : locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode == null ? "" : locationCode;
    }

    public String getChannel() {
        return channel == null ? "" : channel;
    }

    public void setChannel(String channel) {
        this.channel = channel == null ? "" : channel;
    }



    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    public String getSdkVersion() {
        return sdkVersion == null ? "" : sdkVersion;
    }

    public void setSdkVersion(String sdkVersion) {
        this.sdkVersion = sdkVersion == null ? "" : sdkVersion;
    }

    public String getUpdateTime() {
        return updateTime == null ? "" : updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? "" : updateTime;
    }

    public List<CrashAnalyticsBeans> getRecords() {
        if (records == null) {
            return new ArrayList<>();
        }
        return records;
    }

    public void setRecords(List<CrashAnalyticsBeans> records) {
        this.records = records;
    }


    public static class CrashAnalyticsBeans {
        private String crashTime;
        private String pageId;
        private String pageName;
        private String callStack;
        private String $netState;
        private String ip;
        private String userAgent;
        public CrashAnalyticsBeans(){

        }
        public String getCrashTime() {
            return crashTime == null ? "" : crashTime;
        }

        public void setCrashTime(String crashTime) {
            this.crashTime = crashTime == null ? "" : crashTime;
        }

        public String getPageId() {
            return pageId == null ? "" : pageId;
        }

        public void setPageId(String pageId) {
            this.pageId = pageId == null ? "" : pageId;
        }

        public String getPageName() {
            return pageName == null ? "" : pageName;
        }

        public void setPageName(String pageName) {
            this.pageName = pageName == null ? "" : pageName;
        }

        public String getCallStack() {
            return callStack == null ? "" : callStack;
        }

        public void setCallStack(String callStack) {
            this.callStack = callStack == null ? "" : callStack;
        }

        public String get$netState() {
            return $netState == null ? "" : $netState;
        }

        public void set$netState(String $netState) {
            this.$netState = $netState == null ? "" : $netState;
        }

        public String getIp() {
            return ip == null ? "" : ip;
        }

        public void setIp(String ip) {
            this.ip = ip == null ? "" : ip;
        }

        public String getUserAgent() {
            return userAgent == null ? "" : userAgent;
        }

        public void setUserAgent(String userAgent) {
            this.userAgent = userAgent == null ? "" : userAgent;
        }
    }

    @Override
    public String toString() {
        return "WdAnalyticsCrashBean{" +
                "$originalId='" + $originalId + '\'' +
                ", distinctId='" + distinctId + '\'' +
                ", $project='" + $project + '\'' +
                ", $packageName='" + $packageName + '\'' +
                ", locationCode='" + locationCode + '\'' +
                ", channel='" + channel + '\'' +
                ", $manufacturer='" + $manufacturer + '\'' +
                ", $model='" + $model + '\'' +
                ", $os='" + $os + '\'' +
                ", $osVersion='" + $osVersion + '\'' +
                ", screenWidth=" + screenWidth +
                ", screenHeight=" + screenHeight +
                ", $appVersion='" + $appVersion + '\'' +
                ", sdkVersion='" + sdkVersion + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", records=" + records +
                '}';
    }
}
