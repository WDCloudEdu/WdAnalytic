package com.wdcloud.wdanalytics.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.wdcloud.analytics.greendao.gen.DaoSession;
import com.wdcloud.wdanalytics.bean.CrashAnalyticsBean;
import com.wdcloud.wdanalytics.bean.EventBean;
import com.wdcloud.wdanalytics.bean.WdAnalyticsBean;
import com.wdcloud.wdanalytics.bean.WdAnalyticsCrashBean;
import com.wdcloud.wdanalytics.util.AnalyticsBeanFactory;
import com.wdcloud.wdanalytics.util.AnalyticsNetUtil;
import com.wdcloud.wdanalytics.util.AnalyticsSharedPreference;
import com.wdcloud.wdanalytics.util.GreenDaoManager;
import com.wdcloud.wdanalytics.util.GsonUtil;
import com.wdcloud.wdanalytics.util.LogUtil;
import com.wdcloud.wdanalytics.util.TimeUtil;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Response;


/**
 * Info:统计服务
 * Created by Yanxin.
 * CreateTime: 2019/11/28 14:29
 */
public class AnalyticsService extends Service {
    private WdAnalyticsBean wdAanlyticsFactory;
    private AnalyticsThread analyticsThread;
    private WdAnalyticsCrashBean crashWdAanlyticsFactory;
    public final int SELECT_MSG = 1;
    private static String BSSE_ANALYTIC="http://client-capture.wdcloud.cc/";
    private List<WdAnalyticsBean.EventsBean> eventBeanList = new ArrayList<>();
    private List<WdAnalyticsCrashBean.CrashAnalyticsBeans> crashBeanList = new ArrayList<>();
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg.what == SELECT_MSG) {
                event_session = GreenDaoManager.getInstance().getEventSession();
                crash_session = GreenDaoManager.getInstance().getCrashSession();
                List<EventBean> eventBeans = event_session.loadAll(EventBean.class);
                List<CrashAnalyticsBean> crashAnalyticsBeans = crash_session.loadAll(CrashAnalyticsBean.class);
                String select_Time = TimeUtil.longToDate();
                LogUtil.e("查询伟东统计事件总条数======执行时间: "+select_Time, eventBeans.size() + "条");
                LogUtil.e("查询伟东统计奔溃总条数======执行时间: "+select_Time, crashAnalyticsBeans.size() + "条");
                LogUtil.e("查看伟东统计数据库","wdAnalytic.db");
                if (eventBeans.size() > 0) {
                    LogUtil.e("伟东统计===开始组装事件统计","============");
                    for (int i = 0; i < eventBeans.size(); i++) {
                        WdAnalyticsBean.EventsBean eventBean = new WdAnalyticsBean.EventsBean();
                        String eventId = eventBeans.get(i).getEventId();
                        String pageId = eventBeans.get(i).getPageId();
                        String customInfo = eventBeans.get(i).getCustomInfo();
                        eventBean.setRealTime(eventBeans.get(i).getIsRealTime());
                        eventBean.set$type(setNoEmpty(eventBeans.get(i).getType()));
                        eventBean.set$time(setNoEmpty(eventBeans.get(i).getTime()));
                        eventBean.set$duration(setNoEmpty(eventBeans.get(i).getDuration()));
                        if(!eventId.equals(""))
                        {
                            eventBean.setEventId(setNoEmpty(eventBeans.get(i).getEventId()));
                            eventBean.setEventName(setNoEmpty(eventBeans.get(i).getEventName()));
                        }
                        if(!pageId.equals(""))
                        {
                            eventBean.setPageId(setNoEmpty(eventBeans.get(i).getPageId()));
                            eventBean.setPageName(setNoEmpty(eventBeans.get(i).getPageName()));
                            eventBean.setPrefixPageId(setNoEmpty(eventBeans.get(i).getPrefixPageId()));
                            eventBean.setPrefixPageNameId(setNoEmpty(eventBeans.get(i).getPrefixPageNameId()));
                        }
                        String properties = eventBeans.get(i).getCustomInfo();
                        Gson gsonInstance = GsonUtil.getGsonInstance();
                        WdAnalyticsBean.ProPertiesBean proPertiesBean = gsonInstance.fromJson(properties, WdAnalyticsBean.ProPertiesBean.class);
                        eventBean.setProperties(proPertiesBean);
                        eventBeanList.add(eventBean);
                    }
                    wdAanlyticsFactory.setRecords(eventBeanList);
                    LogUtil.e("伟东统计===结束组装事件统计","========");
                    eventAnalytics(wdAanlyticsFactory, event_session);
                }

                if (crashAnalyticsBeans.size() > 0) {
                    LogUtil.e("伟东统计===开始组装奔溃事件统计","========");
                    crashBeanList.clear();
                    for (int i = 0; i < crashAnalyticsBeans.size(); i++) {
                        WdAnalyticsCrashBean.CrashAnalyticsBeans crashAnalyticsBean = new WdAnalyticsCrashBean.CrashAnalyticsBeans();
                        crashAnalyticsBean.setCrashTime(crashAnalyticsBeans.get(i).getCrashTime());
                        crashAnalyticsBean.setPageId(crashAnalyticsBeans.get(i).getPageId());
                        crashAnalyticsBean.setPageName(crashAnalyticsBeans.get(i).getPageName());
                        crashAnalyticsBean.setCallStack(crashAnalyticsBeans.get(i).getCallStack());
                        crashAnalyticsBean.set$netState(crashAnalyticsBeans.get(i).getNetState());
                        crashAnalyticsBean.setIp(crashAnalyticsBeans.get(i).getIp());
                        crashAnalyticsBean.setUserAgent(crashAnalyticsBeans.get(i).getUserAgent());
                        crashBeanList.add(crashAnalyticsBean);
                    }
                    crashWdAanlyticsFactory.setRecords(crashBeanList);
                    LogUtil.e("伟东统计===结束组装事件统计","============");
                    crashAnalytics(crashWdAanlyticsFactory, crash_session);
                }
            }
        }
    };
    private DaoSession event_session;
    private DaoSession crash_session;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        this.analyticsThread = new AnalyticsThread();
        this.analyticsThread.start();
        LogUtil.e("伟东统计上传服务启动===》", "OK ！");
        AnalyticsSharedPreference.init(this);
        super.onCreate();
    }

    private class AnalyticsThread extends Thread {
        @Override
        public void run() {
            super.run();
            wdAanlyticsFactory = AnalyticsBeanFactory.getWdAanlyticsFactory();
            crashWdAanlyticsFactory = AnalyticsBeanFactory.getCrashWdAanlyticsFactory();
            AnalyticsSharedPreference instance = AnalyticsSharedPreference.getInstance();
            int Millis = (int) instance.getData("Millis", 5);
            while (true) {
                try {
                    handler.sendEmptyMessage(SELECT_MSG);
                    Thread.sleep(Millis * 60000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String setNoEmpty(String Msg) {
        String info = Msg == null ? "" : Msg;
        return info;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void eventAnalytics(WdAnalyticsBean wdAnalyticsBean, final DaoSession event_session) {
        Gson gsonInstance = GsonUtil.getGsonInstance();
        String s = gsonInstance.toJson(wdAnalyticsBean);
        Log.e("组装后的数据",s);
        AnalyticsNetUtil.postJson(BSSE_ANALYTIC + "capture/client/userBehavior", wdAnalyticsBean, new AnalyticsNetUtil.AnalyticNetCallBack() {
            @Override
            public void failed(Response response, String errorMsg) {
                LogUtil.e("伟东统计==（非实时）=上传事件统计失败！", "失败信息" + errorMsg);
            }

            @Override
            public void successed(Response response, String data) {
                LogUtil.e("伟东统计==（非实时）=上传事件统计成功！", "成功信息" + data);
                event_session.deleteAll(EventBean.class);
            }
        });
    }

    private void crashAnalytics(WdAnalyticsCrashBean wdAnalyticsCrashBean, final DaoSession crash_session) {
        AnalyticsNetUtil.postJson(BSSE_ANALYTIC + "capture/client/appCrash", wdAnalyticsCrashBean, new AnalyticsNetUtil.AnalyticNetCallBack() {
            @Override
            public void failed(Response response, String errorMsg) {
                LogUtil.e("伟东统计=（非实时）==上传奔溃统计失败！", "错误" + errorMsg);
            }

            @Override
            public void successed(Response response, String data) {
                LogUtil.e("伟东统计=（非实时）==上传奔溃统计成功！", "成功信息" + data);
                crash_session.deleteAll(CrashAnalyticsBean.class);
            }
        });
    }
}
