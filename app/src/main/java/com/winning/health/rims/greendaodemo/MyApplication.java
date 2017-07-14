package com.winning.health.rims.greendaodemo;

import android.app.Application;
import android.content.Context;

import com.winning.health.rims.greendaodemo.greendao.DaoManager;

/**
 * desc: 返回Context对象
 * author：djj on 2017/7/13 15:41
 * 简书：http://www.jianshu.com/u/dfbde65a03fc
 */
public class MyApplication extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        DaoManager.getInstant();
    }

    public static Context getContext() {
        return mContext;
    }
}
