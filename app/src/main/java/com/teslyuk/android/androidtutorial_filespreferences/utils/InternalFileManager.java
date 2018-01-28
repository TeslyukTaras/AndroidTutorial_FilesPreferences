package com.teslyuk.android.androidtutorial_filespreferences.utils;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by taras.teslyuk on 11/16/15.
 */
public class InternalFileManager {

    private static InternalFileManager instance;
    private static Context applicationContext;

    public static void init(Context context) {
        instance = new InternalFileManager(context);
    }

    public static InternalFileManager getInternalFileManager() {
        return instance;
    }

    public InternalFileManager(Context context) {
        applicationContext = context;
    }

    public static File createFile(String fileName) {
        File file = new File(applicationContext.getFilesDir(), fileName);
        return file;
    }

    public static File createCacheFile(String fileName) {
        File file = new File(applicationContext.getCacheDir(), fileName);
        return file;
    }

    public static String[] getFilesList() {
        String[] files = applicationContext.fileList();
        return files;
    }

    public static void removeFile(String fileName) {
        applicationContext.deleteFile(fileName);
    }


    public static void writeToFile(String fileName, String data) throws IOException {
        FileOutputStream fOut = applicationContext.openFileOutput(fileName, Context.MODE_PRIVATE);
        fOut.write(data.getBytes());
        fOut.close();
    }

    public static String readFromFile(String fileName) throws IOException {
        FileInputStream fin = applicationContext.openFileInput(fileName);
        int c;
        StringBuilder temp = new StringBuilder();
        while ((c = fin.read()) != -1) {
            temp.append(Character.toString((char) c));
        }
        fin.close();

        return temp.toString();
    }
}
