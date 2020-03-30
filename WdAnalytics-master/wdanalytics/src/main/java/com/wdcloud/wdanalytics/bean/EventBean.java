package com.wdcloud.wdanalytics.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Info:事件统计Bean
 * Created by Yanxin.
 * CreateTime: 2019/11/27 17:26
 */
@Entity
public class EventBean {
    @Id(autoincrement = true)
    private Long id;
    private Boolean isRealTime;
    private String type;
    private String event;
    private String time;
    private String duration;
    private String eventId;
    private String eventName;
    private String pageId;
    private String pageName;
    private String prefixPageId;
    private String prefixPageNameId;
    private String customInfo;
    @Generated(hash = 1426145772)
    public EventBean(Long id, Boolean isRealTime, String type, String event,
            String time, String duration, String eventId, String eventName,
            String pageId, String pageName, String prefixPageId,
            String prefixPageNameId, String customInfo) {
        this.id = id;
        this.isRealTime = isRealTime;
        this.type = type;
        this.event = event;
        this.time = time;
        this.duration = duration;
        this.eventId = eventId;
        this.eventName = eventName;
        this.pageId = pageId;
        this.pageName = pageName;
        this.prefixPageId = prefixPageId;
        this.prefixPageNameId = prefixPageNameId;
        this.customInfo = customInfo;
    }
    @Generated(hash = 1783294599)
    public EventBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Boolean getIsRealTime() {
        return this.isRealTime;
    }
    public void setIsRealTime(Boolean isRealTime) {
        this.isRealTime = isRealTime;
    }
    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getEvent() {
        return this.event;
    }
    public void setEvent(String event) {
        this.event = event;
    }
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getDuration() {
        return this.duration;
    }
    public void setDuration(String duration) {
        this.duration = duration;
    }
    public String getEventId() {
        return this.eventId;
    }
    public void setEventId(String eventId) {
        this.eventId = eventId;
    }
    public String getEventName() {
        return this.eventName;
    }
    public void setEventName(String eventName) {
        this.eventName = eventName;
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
    public String getPrefixPageId() {
        return this.prefixPageId;
    }
    public void setPrefixPageId(String prefixPageId) {
        this.prefixPageId = prefixPageId;
    }
    public String getPrefixPageNameId() {
        return this.prefixPageNameId;
    }
    public void setPrefixPageNameId(String prefixPageNameId) {
        this.prefixPageNameId = prefixPageNameId;
    }
    public String getCustomInfo() {
        return this.customInfo;
    }
    public void setCustomInfo(String customInfo) {
        this.customInfo = customInfo;
    }
}
