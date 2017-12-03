package com.ee4461l_project.recipz;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;


public class RecipeViewActivity extends AppCompatActivity {
    private WebView webview;
    private ImageButton fav;
    private String URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_view);
        final Intent intent = getIntent();
        fav = findViewById(R.id.imageButton3);
        fav.setOnClickListener(new View.OnClickListener() {
            //prompts user to add current recipe to a list
            //if no lists exist, prompts user to create one and then adds recipe to it
            @Override
            public void onClick(View view) {
                String src = intent.getStringExtra("sourceURL");
                String title = intent.getStringExtra("title");

            }
        });

        fav.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {

            }
        });

        URL = intent.getStringExtra("sourceURL");
        webview = findViewById(R.id.webView);
        webview.setWebViewClient(new WebViewClient());
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setDomStorageEnabled(true);
        webview.setOverScrollMode(WebView.OVER_SCROLL_NEVER);
        webview.loadUrl(URL);
    }


}
