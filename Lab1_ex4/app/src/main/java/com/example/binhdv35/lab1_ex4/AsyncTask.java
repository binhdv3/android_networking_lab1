package com.example.binhdv35.lab1_ex4;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;

public class AsyncTask extends android.os.AsyncTask<String, String, String> {

    ProgressDialog progressDialog;
    TextView textView;
    EditText editText;
    Context context;
    String resp;

    public AsyncTask(TextView textView, EditText editText, Context context) {
        this.textView = textView;
        this.editText = editText;
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {
        publishProgress("Sleeping");
        try {
            int time = Integer.parseInt(strings[0] ) * 1000;
            Thread.sleep(time);
            resp = "Wait for "+ strings[0] + " second";
        }catch (Exception e){
            e.printStackTrace();
            resp = e.getMessage();
        }
        return resp;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(context,
                "dialog",
                "Wait for time "+ editText.getText().toString() +" second");

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (progressDialog.isShowing()){
            progressDialog.dismiss();
        }

        textView.setText(s);
    }
}
