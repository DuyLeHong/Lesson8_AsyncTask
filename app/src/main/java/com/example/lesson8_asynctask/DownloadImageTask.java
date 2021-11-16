package com.example.lesson8_asynctask;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by hungnm24 on 4/27/20
 * Copyright (c) {2020} VinID. All rights reserved.
 */

public class DownloadImageTask extends AsyncTask<String, Integer, Bitmap> {

    private CallBack callBack;
    private Context context;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    public DownloadImageTask(Context context, CallBack callBack) {
        super();
        this.callBack = callBack;
        this.context = context;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        callBack.onDownloadComplete(bitmap);
        super.onPostExecute(bitmap);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        Bitmap bitmap = downloadImage(strings[0]);


        return bitmap;
    }

    private Bitmap downloadImage(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            return BitmapFactory.decodeStream(input);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    interface CallBack {
        void onDownloadComplete(Bitmap bitmap);
    }
}
