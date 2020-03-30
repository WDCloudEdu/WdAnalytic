package com.wdcloud.wdanalytics.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Info:奔溃统计数据库Bean
 * Created by Yanxin.
 * CreateTime: 2019/12/11 14:17
 */
@Entity
public class CrashAnalyticsBean {
    @Id
    private Long id;
    private String crashTime;
    private String pageId;
    private String pageName;
    private String callStack;
    private String netState;
    private String ip;
    private String userAgent;
    @Generated(hash = 1326025117)
    public CrashAnalyticsBean(Long id, String crashTime, String pageId,
            String pageName, String callStack, String netState, String ip,
            String userAgent) {
        this.id = id;
        this.crashTime = crashTime;
        this.pageId = pageId;
        this.pageName = pageName;
        this.callStack = callStack;
        this.netState = netState;
        this.ip = ip;
        this.userAgent = userAgent;
    }
    @Generated(hash = 1678532930)
    public CrashAnalyticsBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCrashTime() {
        return this.crashTime;
    }
    public void setCrashTime(String crashTime) {
        this.crashTime = crashTime;
    }
    public String getPageId() {
        return this.pageId;
    }
    public void setPageId(String pageId) {
        this.pageId = pageId;
    }
    public String getPageName() {
        return this.pageName;
    }
    public void setPageName(String pageName) {
        this.pageName = pageName;
    }
    public String getCallStack() {
        return this.callStack;
    }
    public void setCallStack(String callStack) {
        this.callStack = callStack;
    }
    public String getNetState() {
        return this.netState;
    }
    public void setNetState(String netState) {
        this.netState = netState;
    }
    public String getIp() {
        return this.ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public String getUserAgent() {
        return this.userAgent;
    }
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
}
