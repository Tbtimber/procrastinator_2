package com.procrastinator.isen.procrastinator_2;

import android.app.Application;
import android.content.Context;

/**
 * Created by Matthieu on 25/01/2016.
 */
public class ProcrastinatorApplication extends Application {
    private static Context sContext;

    public void onCreate(){
        super.onCreate();

        // Keep a reference to the application context
        sContext = getApplicationContext();
    }

    // Used to access Context anywhere within the app
    public static Context getContext() {
        return sContext;
    }
}