package com.youtu.djf.viewclicksoundeffect;

import android.app.Application;

/**
 * Created by djf on 2017/10/13.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(this);
    }
}
