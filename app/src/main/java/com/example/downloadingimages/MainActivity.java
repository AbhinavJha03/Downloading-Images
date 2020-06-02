package com.example.downloadingimages;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;

    public  void downloadImage(View view)
    {
        ImageDownloader task = new ImageDownloader();
        Bitmap myimage;
        try {
            myimage=task.execute("https://vignette.wikia.nocookie.net/great-characters/images/6/65/Bart_Simpson.png/revision/latest/top-crop/width/360/height/450?cb=20180623202228").get();
            imageView.setImageBitmap(myimage);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView=findViewById(R.id.imageView);
    }
    public class ImageDownloader extends AsyncTask<String, Void, Bitmap>
    {

        @Override
        protected Bitmap doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection()       ;
                connection.connect();
                InputStream in= connection.getInputStream();
                Bitmap myBitmap= BitmapFactory.decodeStream(in);
                return myBitmap;
            }

            catch(Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
