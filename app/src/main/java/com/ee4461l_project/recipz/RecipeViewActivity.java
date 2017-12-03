package com.ee4461l_project.recipz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;


public class RecipeViewActivity extends AppCompatActivity {
    private WebView webview;
    private Button Back;
    private String URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_view);
        Intent intent = new Intent();
        URL = intent.getStringExtra("sourceURL");
        //URL = "https://www.allrecipes.com/recipe/7934/blueberry-cheesecake/";
        webview = (WebView)findViewById(R.id.webView);
        Back = (Button)findViewById(R.id.Back);

        webview.setWebViewClient(new WebViewClient());
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setDomStorageEnabled(true);
        webview.setOverScrollMode(WebView.OVER_SCROLL_NEVER);
        webview.loadUrl(URL);
    }

    public void sendMessage(View v){
        if(v == Back) {
            startActivity(new Intent(RecipeViewActivity.this, SearchActivity.class));
        }
    }
}
