package com.example.admin.storingdata;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Admin on 10/3/2017.
 */

public class MyThread extends Thread {

    private static final String TAG = "MyThread";
    String name;
    Handler handler;

    public MyThread( String name, Handler handler ) {
        this.name = name;
        this.handler = handler;
    }

    @Override
    public void run() {
        //write to file
        //Checks if external storage is available for read and write
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            try {
                String path = Environment.getExternalStorageDirectory() + File.separator + "yourFolder";
                // Create the folder.
                File folder = new File(path);
                folder.mkdirs();

                File f = new File(path, "app data");
                f.createNewFile();
                FileOutputStream fos = new FileOutputStream( f );
                fos.write( name.getBytes() );
                fos.close();
            }  catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //send the results back to the UI thread using a handler message.
            Bundle bundle = new Bundle();
            bundle.putInt( "written", 1 );

            //add bundle to message object
            Message message = new Message();
            message.setData( bundle );

            handler.sendMessage( message );
        }
    }

}
