package com.example.admin.storingdata;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText nameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameText = (EditText) findViewById( R.id.etName );
    }

    public void writeData(View view) {

        Handler h = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                dataWritten();
                return false;
            }
        });

        MyThread thread = new MyThread( nameText.getText().toString(), h );
        thread.start();
    }

    public void dataWritten() {
        Intent intent = new Intent( this, Main2Activity.class);
        startActivity( intent );
    }
}

/*
Create an application to read and write to external storage.
1. Use a thread to write the contents of the UI from the first activity to a file.
2. Read the contents of the file in the second activity (should be asynchronous as well) and display it.
 */