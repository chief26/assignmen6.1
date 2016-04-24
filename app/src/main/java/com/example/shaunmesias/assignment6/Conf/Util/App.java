package com.example.shaunmesias.assignment6.Conf.conf.Util;

import android.app.Application;
import android.content.Context;

/**
 * Created by Shaun Mesias on 2016/04/19.
 */
public class App extends Application {
    private static App singleton;
    private static Context context;
    public App() {
    }

    public static App getInstance(){
        if(singleton == null) {
            singleton = new App();
        }
        return singleton;
    }

    public void onCreate() {
        super.onCreate();
        App.context = getApplicationContext();
        singleton = this;
    }

    public static Context getAppContext() {
        return App.context;
    }
}
