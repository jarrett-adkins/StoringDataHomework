package com.example.admin.storingdata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {

    private EditText nameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        nameText = (EditText) findViewById( R.id.etName );
    }

    public void loadData(View view) {
        MyAsyncTask myAsyncTask = new MyAsyncTask( nameText );
        myAsyncTask.execute();
    }
}
