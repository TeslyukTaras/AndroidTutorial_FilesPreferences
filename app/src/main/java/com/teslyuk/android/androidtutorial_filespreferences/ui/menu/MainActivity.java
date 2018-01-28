package com.teslyuk.android.androidtutorial_filespreferences.ui.menu;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.teslyuk.android.androidtutorial_filespreferences.R;
import com.teslyuk.android.androidtutorial_filespreferences.ui.external.ExternalStorageActivity;
import com.teslyuk.android.androidtutorial_filespreferences.ui.internal.InternalStorageActivity;
import com.teslyuk.android.androidtutorial_filespreferences.ui.preference.PreferenceActivity;
import com.teslyuk.android.androidtutorial_filespreferences.ui.settings.SettingsActivity;

import android.util.Log;


public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int REQUEST_CODE_PERMISSION = 10;

    private Button internalBtn, externalBtn, preferenceBtn, settingBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initListener();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_CODE_PERMISSION) {
            if (grantResults.length >= 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // permission was granted
                Log.d(TAG, "permission was granted");
                externalBtn.setVisibility(View.VISIBLE);
            } else {
                // permission wasn't granted
                Log.d(TAG, "permission wasn't granted");
                externalBtn.setVisibility(View.GONE);
            }
        }
    }

    private void checkWritingPermission() {
        Log.d(TAG, "checkWritingPermission");
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "not granted");
//            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
//                // permission wasn't granted
//
//                externalBtn.setVisibility(View.GONE);
//            } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_PERMISSION);
//            }
        } else {
            Log.d(TAG, "granted");
        }
    }

    private boolean canWriteToExternalStorage() {
        return ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    private void initView() {
        internalBtn = findViewById(R.id.button_internal_storage);
        externalBtn = findViewById(R.id.button_external_storage);
        preferenceBtn = findViewById(R.id.button_preference_storage);
        settingBtn = findViewById(R.id.button_settings);

        if (canWriteToExternalStorage()) {
            externalBtn.setVisibility(View.VISIBLE);
        } else {
            checkWritingPermission();
        }
    }

    private void initListener() {
        internalBtn.setOnClickListener(this);
        externalBtn.setOnClickListener(this);
        preferenceBtn.setOnClickListener(this);
        settingBtn.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            showSettings();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_internal_storage:
                showInternalStorageScreen();
                break;

            case R.id.button_external_storage:
                showExternalStorageScreen();
                break;

            case R.id.button_preference_storage:
                showSharedPreferenceScreen();
                break;

            case R.id.button_settings:
                showSettings();
                break;
        }
    }

    private void showInternalStorageScreen() {
        Intent internalIntent = new Intent(this, InternalStorageActivity.class);
        startActivity(internalIntent);
    }

    private void showExternalStorageScreen() {
        Intent externalIntent = new Intent(this, ExternalStorageActivity.class);
        startActivity(externalIntent);
    }

    private void showSharedPreferenceScreen() {
        Intent prefIntent = new Intent(this, PreferenceActivity.class);
        startActivity(prefIntent);
    }

    private void showSettings() {
        Intent settingIntent = new Intent(this, SettingsActivity.class);
        startActivity(settingIntent);
    }
}