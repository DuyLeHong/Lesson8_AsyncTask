package com.example.lesson8_asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private static final String IMAGE_URL = "https://codefresher.vn/wp-content/uploads/2021/06/Banner-01-QC-Android-1024x1024.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startDownloadImage();
    }

    private void startDownloadImage() {
        DownloadImageTask task = new DownloadImageTask(this.getApplicationContext());
        task.execute(IMAGE_URL);
    }

    private void onDownloadComplete(Bitmap bm) {
        ImageView imageView = findViewById(R.id.ivImage);
        imageView.setImageBitmap(bm);
    }

    class DownloadImageTask extends AsyncTask<String, Integer, Bitmap> {

        private Context context;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        public DownloadImageTask(Context context) {
            super();
            this.context = context;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            onDownloadComplete(bitmap);
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


    }
}