package com.rcnewproject;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import org.acra.ACRA;
import org.acra.ReportingInteractionMode;
import org.acra.annotation.ReportsCrashes;

@ReportsCrashes(
        mailTo = "name.surname@appinventiv.com",
        mode = ReportingInteractionMode.TOAST,
        resToastText = R.string.crash_toast_text)

    public class MyApplication extends Application {
    private static MyApplication mApplicationInsatnce;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        ACRA.init(this);
        mApplicationInsatnce = this;
    }

    public static MyApplication getInstance(){
        return mApplicationInsatnce;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        System.gc();
    }
}
