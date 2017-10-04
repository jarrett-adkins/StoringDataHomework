package com.example.admin.storingdata;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Admin on 10/3/2017.
 */

public class MyAsyncTask extends AsyncTask<String, Integer, String> {

    private static final String TAG = "MyAsyncTask";
    TextView textView;

    public MyAsyncTask(TextView textView) {
        this.textView = textView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d(TAG, "onPreExecute: " + Thread.currentThread());

        //textView.setText( "Setting up the Task" );
    }

    @Override
    protected String doInBackground(String... strings) {
        Log.d(TAG, "doInBackground: " + Thread.currentThread());

        String data = "";

        try {
            String path = Environment.getExternalStorageDirectory() + File.separator + "yourFolder";
            File myFile = new File( path,"ClassProject");
            FileInputStream fis = new FileInputStream(myFile);
            byte[] dataArray = new byte[fis.available()];

            while (fis.read(dataArray) != -1) {
                data = new String(dataArray);
            }
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        Log.d(TAG, "onProgressUpdate: " + Thread.currentThread());

        textView.setText( String.valueOf( values[0] ) );
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.d(TAG, "onPostExecute: " + Thread.currentThread());

        textView.setText( s );
    }
}
