package com.teslyuk.android.androidtutorial_filespreferences.ui.settings;

import android.annotation.TargetApi;
import android.os.Build;
import android.preference.PreferenceActivity;
import android.util.Log;

import com.teslyuk.android.androidtutorial_filespreferences.R;

import java.util.List;

/**
 * Created by taras.teslyuk on 11/17/15.
 */

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class SettingsActivity extends PreferenceActivity {

    private static final String TAG = SettingsActivity.class.getSimpleName();

    public void onBuildHeaders(List<Header> target) {
        loadHeadersFromResource(R.xml.pref_head, target);
    }

    @Override
    protected boolean isValidFragment(String fragmentName) {
        Log.d(TAG, "isValidFragment: " + fragmentName);
        if (fragmentName.contains("Fragment1"))
            return true;
        if (fragmentName.contains("Fragment2"))
            return true;

        return super.isValidFragment(fragmentName);
    }
}