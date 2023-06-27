package com.example.binhdv35.lab1_ex4;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText ed;

    private TextView tv;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed = findViewById(R.id.ed);
        btn = findViewById(R.id.btn);
        tv = findViewById(R.id.tv);

        event();
    }

    private void event() {
        btn.setOnClickListener(v -> {
            AsyncTask asyncTask = new AsyncTask(tv, ed, MainActivity.this);
            String sleepTime = ed.getText().toString();
            asyncTask.execute(sleepTime);
        });
    }
}