package com.fancl.tinker;


import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.fancl.tinker.library.FixDexUtils;

public class BaseApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        FixDexUtils.loadFixDex(this);
    }
}
