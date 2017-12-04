package com.ee4461l_project.recipz;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class FavoritesActivity extends AppCompatActivity {
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    Intent intent;

    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        verifyStoragePermissions(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        String readString ;
        FileInputStream fIn = null;
        try {
            fIn = openFileInput("Favorites");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        readString = new Scanner(fIn,"UTF-8").useDelimiter("\\A").next();
        Log.e("favList[6]", readString);
        Gson gson = new Gson();

        CardView cardView0 = findViewById(R.id.cardView0);
        ImageView imageView0 = findViewById(R.id.recipeImage0);
        TextView recipeTitle0 = findViewById(R.id.recipeTitle0);
        TextView recipePublisher0 = findViewById(R.id.recipePublisher0);
        FavoritesResponse favResponse = gson.fromJson(readString, FavoritesResponse.class);
        ArrayList<Recipes> favList = favResponse.getRecipes();
        recipeTitle0.setText(favList.get(0).getTitle());
        recipePublisher0.setText(favList.get(0).getPublisher());
        new BrowseActivity.DownloadImageTask((ImageView) findViewById(R.id.recipeImage0))
                .execute(favList.get(0).getImage_url());



    }


}
