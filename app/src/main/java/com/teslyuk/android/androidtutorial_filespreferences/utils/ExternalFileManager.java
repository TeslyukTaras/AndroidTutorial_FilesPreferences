package com.teslyuk.android.androidtutorial_filespreferences.utils;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.FileNameMap;

/**
 * Created by taras.teslyuk on 10/13/15.
 */
public class ExternalFileManager {

    private static final String appDir = "/testApp/";

    private static String getExternalStoragePath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    private static String getAppDirPath() {
        return getExternalStoragePath() + appDir;
    }

    private static boolean hasDirectory() {
        //create folder
        File dir = new File(getAppDirPath());
        //check if folder already exist
        boolean success = dir.exists() && dir.isDirectory();

        if (!dir.exists()) {//create folders
            success = dir.mkdirs();
        }

        return success;
    }

    public static boolean fileExist(String fileName) {
        if (!hasDirectory()) {
            return false;
        } else {
            File file = new File(getAppDirPath(),
                    (fileName));
            if (!file.exists()) {
                return false;
            } else {
                return true;
            }
        }

    }

    public static File createFile(String fileName) {
        if (hasDirectory()) {
            File file = new File(getAppDirPath(), fileName);
            if (!file.exists()) {//create new file
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                file.delete();
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return file;
        }
        return null;
    }

    public static File getFile(String fileName) {
        if (fileExist(fileName)) {
            File file = new File(getAppDirPath(), (fileName));
            if (!file.exists()) {
                return null;
            } else {
                return file;
            }
        } else {
            return null;
        }
    }

    public static boolean removeFile(String fileName) {
        if (fileExist(fileName)) {
            File file = getFile(fileName);
            return file.delete();
        } else {
            return true;
        }
    }

    public static boolean removeFile(File file) {
        if (file.exists()) {
            return file.delete();
        } else {
            return true;
        }
    }

    public static File[] getAllFiles() {
        if (!hasDirectory()) {
            return null;
        } else {
            File dir = new File(getAppDirPath());
            File[] files = dir.listFiles();
            return files;
        }
    }

    public static void copyFile(File src, File dst) throws IOException {
        InputStream in = new FileInputStream(src);
        OutputStream out = new FileOutputStream(dst);

        // Transfer bytes from in to out
        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        in.close();
        out.close();
    }

    public static void writeToFile(File file, String data) throws IOException {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    public static String readFromFile(File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            return sb.toString();
        } finally {
            br.close();
        }
    }
}