package com.teslyuk.android.androidtutorial_filespreferences.ui.internal;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.teslyuk.android.androidtutorial_filespreferences.R;
import com.teslyuk.android.androidtutorial_filespreferences.ui.adapter.FileListAdapter;
import com.teslyuk.android.androidtutorial_filespreferences.utils.InternalFileManager;

import java.io.IOException;
import java.util.Calendar;

/**
 * Created by taras.teslyuk on 11/16/15.
 */
public class InternalStorageActivity extends Activity
        implements View.OnClickListener,
        FileListAdapter.FileListAdapterEventListener {

    private static final String TAG = "InternalStorage";

    private ListView fileListView;
    private EditText textView;
    private Button addBtn;

    private String[] fileNames;
    private FileListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_storage);

        initView();
        initListener();
    }

    private void initView() {
        fileListView = findViewById(R.id.internal_file_list_lv);
        textView = findViewById(R.id.internal_file_text_et);
        addBtn = findViewById(R.id.internal_add_file_btn);
    }

    private void initListener() {
        addBtn.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateFileList();
    }

    private void createFile() {
        Log.d(TAG, "createFile");
        String text = textView.getText().toString();

        if (!TextUtils.isEmpty(text)) {
            String fileName = Calendar.getInstance().getTimeInMillis() + ".txt";
            try {
                InternalFileManager.writeToFile(fileName, text);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "Please, enter text.", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateFileList() {
        Log.d(TAG, "updateFileList");
        fileNames = InternalFileManager.getFilesList();
        for (int i = 0; i < fileNames.length; i++) {
            Log.d(TAG, "i:" + i + " name: " + fileNames[i]);
        }
        adapter = new FileListAdapter(this, fileNames);
        adapter.addListener(this);
        fileListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.internal_add_file_btn:
                createFile();
                updateFileList();
                break;
        }
    }

    @Override
    public void previewFile(int position) {
        String fileName = fileNames[position];
        String fileBody = null;
        try {
            fileBody = InternalFileManager.readFromFile(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!TextUtils.isEmpty(fileBody)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("File preview")
                    .setMessage(fileBody)
                    .setIcon(R.mipmap.ic_launcher)
                    .setCancelable(false)
                    .setNegativeButton("ОК",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
            AlertDialog alert = builder.create();
            alert.show();
        }
    }

    @Override
    public void removeFile(int position) {
        String fileName = fileNames[position];
        InternalFileManager.removeFile(fileName);
        updateFileList();
    }
}