package com.teslyuk.android.androidtutorial_filespreferences.ui.settings.fragment;

/**
 * Created by taras.teslyuk on 11/17/15.
 */

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.teslyuk.android.androidtutorial_filespreferences.R;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class Fragment1 extends PreferenceFragment {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref1);
    }
}