package com.wdcloud.wdanalytics.util;

import android.content.Context;

import com.wdcloud.analytics.greendao.gen.DaoMaster;
import com.wdcloud.analytics.greendao.gen.DaoSession;
import com.wdcloud.analytics.greendao.gen.EventBeanDao;

import org.greenrobot.greendao.database.Database;


/**
 * Info:数据库初始化
 * Created by Yanxin.
 * CreateTime: 2019/11/27 17:51
 */
public class GreenDaoManager {
    private static DaoMaster event_mDaoMaster;
    private static DaoSession event_mDaoSession;
    private static DaoMaster crash_mDaoMaster;
    private static DaoSession crash_mDaoSession;
    private static volatile GreenDaoManager mInstance = null;
    private static final String EVENT_DB_NAME = "WdAnalytics.db";
    private static final String CRASH_DB_NAME = "WdAnalyticsCrash.db";
    private GreenDaoManager() {
    }

    public static void init(Context context) {
        DaoMaster.DevOpenHelper event_OpenHelper = new
                DaoMaster.DevOpenHelper(context, EVENT_DB_NAME);
        DaoMaster.DevOpenHelper crash_OpenHelper = new
                DaoMaster.DevOpenHelper(context, CRASH_DB_NAME);
        event_mDaoMaster = new DaoMaster(event_OpenHelper.getWritableDatabase());
        event_mDaoSession = event_mDaoMaster.newSession();
        crash_mDaoMaster = new DaoMaster(crash_OpenHelper.getWritableDatabase());
        crash_mDaoSession = crash_mDaoMaster.newSession();
        //删表和建表
        Database database = GreenDaoManager.getInstance().getEventSession().getDatabase();
        GreenDaoManager.getInstance().getEventSession().getEventBeanDao();
        EventBeanDao.dropTable(database, true);
        EventBeanDao.createTable(GreenDaoManager.getInstance().getEventSession().getDatabase(), true);
    }

    public static GreenDaoManager getInstance() {
        if (mInstance == null) {
            synchronized (GreenDaoManager.class) {
                if (mInstance == null) {
                    mInstance = new GreenDaoManager();
                }
            }
        }
        return mInstance;
    }

    public DaoSession getEventSession() {
        return event_mDaoSession;
    }
    public DaoSession getCrashSession(){
        return crash_mDaoSession;
    }
}
