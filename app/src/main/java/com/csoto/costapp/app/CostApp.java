package com.csoto.costapp.app;

import android.app.Application;

import com.csoto.costapp.core.database.DaoMaster;
import com.csoto.costapp.core.database.DaoSession;
import com.csoto.costapp.core.database.User;
import com.facebook.stetho.Stetho;

/**
 * Created by csoto on 26/06/18.
 */

public class CostApp extends Application {

    private DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        mDaoSession = new DaoMaster(
                new DaoMaster.DevOpenHelper(this, "greendao_cost_app.db").getWritableDb()).newSession();

        // USER CREATION FOR DEMO PURPOSE
        if(mDaoSession.getUserDao().loadAll().size() == 0){
            mDaoSession.getUserDao().insert(new User(1L, "Janishar Ali","", ""));
            mDaoSession.getUserDao().insert(new User(null, "Camilo Soto","", ""));
        }
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }
}
