package com.ee4461l_project.recipz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;


public class RecipeViewActivity extends AppCompatActivity {
    private WebView webview;
    private ImageButton fav;
    private String URL;

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_view);
        final Intent intent = getIntent();
        fav = findViewById(R.id.imageButton3);
        //prompts user to add current recipe to a list
        //if no lists exist, prompts user to create one and then adds recipe to it
        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = intent.getStringExtra("title");
                String src = intent.getStringExtra("sourceURL");
                String image = intent.getStringExtra("imageURL");
                String publisher = intent.getStringExtra("publisher");
                String publisherUrl = intent.getStringExtra("publisherURL");
                Double socialRank = intent.getDoubleExtra("socialRank", 0);
                String recipeId = intent.getStringExtra("recipeId");
                String f2fUrl = intent.getStringExtra("f2fURL");

                //String title = "Jalapeno Popper Grilled Cheese Sandwich";
                //String src = "http://www.closetcooking.com/2011/04/jalapeno-popper-grilled-cheese-sandwich.html";
                //String image = "http://static.food2fork.com/Jalapeno2BPopper2BGrilled2BCheese2BSandwich2B12B500fd186186.jpg";

                try{
                    FileOutputStream fOut = openFileOutput("Favorites", MODE_PRIVATE);

//                    OutputStreamWriter osw = new OutputStreamWriter(fOut);
//                    FileInputStream fIn = openFileInput("samplefile.txt");
//                    InputStreamReader isr = new InputStreamReader(fIn);

                    addToFavorites(publisher, f2fUrl, title, src, recipeId, image, socialRank, publisherUrl);
                    //addToFavorites(src, title, image);
                } catch (Exception e) {
                    Log.e("addToFavorites Error", e.toString());
                }


                //send();

            }
        });


        fav.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {

            }
        });

        URL = intent.getStringExtra("sourceURL");
        //URL = "http://www.closetcooking.com/2011/04/jalapeno-popper-grilled-cheese-sandwich.html";
        webview = findViewById(R.id.webView);
        webview.setWebViewClient(new WebViewClient());
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setDomStorageEnabled(true);
        webview.setOverScrollMode(WebView.OVER_SCROLL_NEVER);
        webview.loadUrl(URL);
    }

    //assumes file "Favorites" exists
    public void addToFavorites(String publisher,
            String f2f_url,
            String title,
            String source_url,
            String recipe_id,
            String image_url,
            Double social_rank,
            String publisher_url) throws IOException {



        //File file = new File(getApplicationContext().getDataDir().getPath(), "Favorites");
        Gson gson = new Gson();
        ArrayList<Recipes> favList = new ArrayList<>(100);
        FavoritesResponse favResponse = new FavoritesResponse();
        favList.add(new Recipes(publisher, f2f_url, title, source_url, recipe_id, image_url, social_rank, publisher_url));
        favResponse.setRecipes(favList);
        String jsonString = gson.toJson(favResponse);
        FileOutputStream fOut = openFileOutput("Favorites", MODE_PRIVATE);
        OutputStreamWriter osw = new OutputStreamWriter(fOut);
        osw.write(jsonString);
        osw.flush();
        osw.close();
        Log.e("jsonString", title);
        Log.e("jsonString", source_url);
        Log.e("jsonString", image_url);
        Log.e("jsonString", jsonString);

    }

    public void send() {
        Intent intent = new Intent(this, FavoritesActivity.class);
        startActivity(intent);
    }


}
