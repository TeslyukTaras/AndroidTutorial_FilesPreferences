package com.teslyuk.android.androidtutorial_filespreferences.ui.preference;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.teslyuk.android.androidtutorial_filespreferences.R;
import com.teslyuk.android.androidtutorial_filespreferences.utils.PreferenceManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by taras.teslyuk on 11/16/15.
 */
public class PreferenceActivity extends Activity implements View.OnClickListener {

    private static final String TAG = PreferenceActivity.class.getSimpleName();

    private ListView listView;
    private EditText keyET, valueET;
    private Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);

        initView();
        initListener();
    }

    private void initView() {
        listView = findViewById(R.id.preference_list_lv);
        keyET = findViewById(R.id.preference_key_et);
        valueET = findViewById(R.id.preference_value_et);
        addBtn = findViewById(R.id.preference_add_btn);
    }

    private void initListener() {
        addBtn.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        initList();
    }

    private void initList() {
        SharedPreferences sharedPreferences = PreferenceManager.getPrefs(this);
        Map<String, ?> keys = sharedPreferences.getAll();
        List<String> values = new ArrayList<String>();
        for (Map.Entry<String, ?> entry : keys.entrySet()) {
            values.add(entry.getKey() + ": " + entry.getValue().toString());
            Log.d("map values", entry.getKey() + ": " + entry.getValue().toString());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, values);
        listView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.preference_add_btn:
                addPreference();
                initList();
                break;
        }
    }

    private void addPreference() {
        String key = keyET.getText().toString();
        String value = valueET.getText().toString();
        if (!TextUtils.isEmpty(key))
            if (!TextUtils.isEmpty(value)) {
                Log.d(TAG, "add preference: key: " + key + " value: " + value);
                PreferenceManager.setString(key, value);
            }
    }
}