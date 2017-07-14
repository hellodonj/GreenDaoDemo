package com.winning.health.rims.greendaodemo;

import com.winning.health.rims.greendaodemo.greendao.DaoManager;
import com.winning.health.rims.greendaodemo.greendao.UserDao;

/**
 * desc: 实体类管理类
 * author：djj on 2017/7/13 16:07
 * 简书：http://www.jianshu.com/u/dfbde65a03fc
 */
public class EntityManager {

    private static EntityManager mEntityManager;
    public UserDao mUserDao;

    /**
     * 创建UserDao实例
     *
     * @return
     */
    public UserDao getUserDao() {
        mUserDao = DaoManager.getInstant().getSession().getUserDao();
        return mUserDao;
    }

    /**
     * 创建一个实例
     *
     * @return
     */
    public static EntityManager getInstance() {
        if (mEntityManager == null) {
            mEntityManager = new EntityManager();
        }
        return mEntityManager;
    }
}
