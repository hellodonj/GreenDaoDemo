package com.winning.health.rims.greendaodemo.greendao;

import android.database.sqlite.SQLiteDatabase;

import com.winning.health.rims.greendaodemo.MyApplication;

/**
 * desc: greenDao管理类
 * author：djj on 2017/7/13 15:40
 * 简书：http://www.jianshu.com/u/dfbde65a03fc
 */
public class DaoManager {

    private static DaoManager mInstant;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    private DaoMaster.DevOpenHelper devOpenHelper;

    public static DaoManager getInstant() {
        if (mInstant == null) {
            mInstant = new DaoManager();
        }
        return mInstant;
    }

    private DaoManager() {
        //创建一个数据库
        devOpenHelper = new DaoMaster.DevOpenHelper(MyApplication.getContext(), "my-db", null);
        SQLiteDatabase db = devOpenHelper.getWritableDatabase();
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }

    public DaoMaster getMaster() {
        return mDaoMaster;
    }

    public DaoSession getSession() {
        return mDaoSession;
    }

    public DaoSession getNewSession() {
        mDaoSession = mDaoMaster.newSession();
        return mDaoSession;
    }

    /**
     * 关闭数据库连接
     */
    public void close() {
        if (devOpenHelper != null) {
            devOpenHelper.close();
        }
    }
}
