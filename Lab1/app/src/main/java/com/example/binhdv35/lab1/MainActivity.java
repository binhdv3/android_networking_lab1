package com.example.binhdv35.lab1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private TextView tvMessage;
    private ImageView img1;
    private Button btnLoad;
    private Bitmap bitmap = null;
    private ProgressDialog progressDialog;
    private Bundle bundle = null;

    private static String LINK_IMAGE = "https://i.pinimg.com/originals/a5/7d/06/a57d062692a78a880fe61acdea5d6281.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //anh xa---
        progressDialog = new ProgressDialog(MainActivity.this);
        initID();
        event();
    }

    public void initID(){
        tvMessage = findViewById(R.id.tv_message);
        img1 = findViewById(R.id.img_1);
        btnLoad = findViewById(R.id.btn_load);
    }

    private void event(){
        btnLoad.setOnClickListener(v -> {

            progressDialog.setMessage("Dowloading...");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setProgress(0);
            progressDialog.setMax(100);
            progressDialog.show();

            if (progressDialog. getProgress() == progressDialog.getMax())
            {
                progressDialog.dismiss();
            }

            //bai 3

            AsyncTask<Void, Void, Void> asyncTask = new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {

                    while (progressDialog. getProgress() < progressDialog.getMax()){
                        handler1.sendMessage(handler1.obtainMessage());//---

                        if (progressDialog. getProgress() == progressDialog.getMax())
                        {
                            bitmap = loadImage(LINK_IMAGE);
                            Message msg = messageHandler.obtainMessage();
                            bundle = new Bundle();
                            String threadMassage = "IMAGE DOWNLOADED";
                            bundle.putString("message", threadMassage);
                            msg.setData(bundle);
                            messageHandler.sendMessage(msg);
                            //---
                            progressDialog.dismiss();

                        }
                    }
                    return null;

                }

                @Override
                protected void onPostExecute(Void unused) {
                    super.onPostExecute(unused);
                }

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();

                    progressDialog.setMessage("Dowloading...");
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressDialog.setProgress(0);
                    progressDialog.setMax(100);
                    progressDialog.show();
                }
            };

            asyncTask.execute();

          //bai 2 dung cai nay
//            Thread thread = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    while (progressDialog. getProgress() < progressDialog.getMax()){
//                        handler1.sendMessage(handler1.obtainMessage());//---
//
//                        if (progressDialog. getProgress() == progressDialog.getMax())
//                        {
//                            bitmap = loadImage(LINK_IMAGE);
//                            Message msg = messageHandler.obtainMessage();
//                            bundle = new Bundle();
//                            String threadMassage = "IMAGE DOWNLOADED";
//                            bundle.putString("message", threadMassage);
//                            msg.setData(bundle);
//                            messageHandler.sendMessage(msg);
//                            //---
//                            progressDialog.dismiss();
//
//                        }
//                    }
//                }
//            });
//            thread.start();
        });
    }

    private Handler handler1 = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            progressDialog.setProgress(progressDialog.getProgress() + 1);
            return true;
        }
    });

    private Handler messageHandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            String message = bundle.getString("message");
            tvMessage.setText(message);
            img1.setImageBitmap(bitmap);
//
        }
    };

    private Bitmap loadImage(String link){
        URL url;
        Bitmap bmp = null;

        try {
            url = new URL(link);
            bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return bmp;
    }


}