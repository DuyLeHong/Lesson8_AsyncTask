package com.example.lesson8_asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private static final String IMAGE_URL = "https://s1.o7planning.com/vi/12751/images/63147290.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startDownloadImage();
    }

    private void startDownloadImage() {
        DownloadImageTask task = new DownloadImageTask(this.getApplicationContext());
        task.execute(IMAGE_URL);

        DownloadTask task2 = new DownloadTask();

        //String [] inputs = new String[]{"link1", "link2", "link3"};
        task2.execute("link truyen vao");

    }

    private void onDownloadComplete(Bitmap bm) {
        ImageView imageView = findViewById(R.id.ivImage);
        imageView.setImageBitmap(bm);
    }

    class DownloadTask extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... strings) {

            String link1 = strings[0];

            Log.d("DownloadTask", link1);



            return link1;
        }

        @Override
        protected void onPostExecute(String _data) {
            super.onPostExecute(_data);

            Toast.makeText(getApplicationContext(), _data, Toast.LENGTH_SHORT).show();
        }
    }

    class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

        private Context context;
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.show();
        }

        public DownloadImageTask(Context context) {
            super();
            this.context = context;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            onDownloadComplete(bitmap);
            progressDialog.dismiss();
            super.onPostExecute(bitmap);
        }

//        @Override
//        protected void onProgressUpdate(Integer... values) {
//            super.onProgressUpdate(values);
//        }

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