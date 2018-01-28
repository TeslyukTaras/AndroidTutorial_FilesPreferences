package com.teslyuk.android.androidtutorial_filespreferences;

import android.app.Application;

import com.teslyuk.android.androidtutorial_filespreferences.utils.InternalFileManager;
import com.teslyuk.android.androidtutorial_filespreferences.utils.PreferenceManager;


/**
 * Created by taras.teslyuk on 10/13/15.
 */
public class MainApplication extends Application {

    private static MainApplication sInstance;
    private static boolean sIsProduction;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        initPreference();
    }

    private void initPreference() {
        PreferenceManager.init(this);
        InternalFileManager.init(this);
    }

    public static synchronized MainApplication getInstance() {
        return sInstance;
    }

    public static boolean isProd() {
        return sIsProduction;
    }

}
