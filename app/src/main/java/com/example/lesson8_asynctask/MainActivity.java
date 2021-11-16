package com.example.lesson8_asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private static final String IMAGE_URL = "https://codefresher.vn/wp-content/uploads/2021/06/Banner-03-QC-ReactNative.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startDownloadImage();
    }

    private void startDownloadImage() {
        DownloadImageTask task = new DownloadImageTask(this.getApplicationContext(), new DownloadImageTask.CallBack() {
            @Override
            public void onDownloadComplete(Bitmap bitmap) {
                ImageView imageView = findViewById(R.id.ivImage);
                imageView.setImageBitmap(bitmap);
            }

        });
        task.execute(IMAGE_URL);
    }
}