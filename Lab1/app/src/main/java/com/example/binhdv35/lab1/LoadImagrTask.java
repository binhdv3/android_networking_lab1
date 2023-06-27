package com.example.binhdv35.lab1;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class LoadImagrTask extends AsyncTask<String, Void, Bitmap> {

    private Listener mListener;
    private ProgressDialog mProgressDialog;

    public LoadImagrTask(Listener mListener, Context context) {
        this.mListener = mListener;
        this.mProgressDialog = new ProgressDialog(context);
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        try {
            return BitmapFactory.decodeStream((InputStream) new URL(strings[0]).getContent());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.show();
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
    }
}
