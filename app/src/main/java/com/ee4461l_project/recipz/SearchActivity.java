package com.ee4461l_project.recipz;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;


public class SearchActivity extends AppCompatActivity {
    EditText editTextSearchTerms;
    Button searchButton;
    static String sURL = "http://food2fork.com/api/search?key=02a03461cd295f9dcf90a669c961e2fd&q=shredded%20chicken";
    GetRecipeFeed recipeGetter = new GetRecipeFeed();
    String raw;


    class GetRecipeFeed extends AsyncTask<URL, Void, String> {
        @Override
        protected String doInBackground(URL... urls) {
            String out = "test";
            try {
                URL s = urls[0];
                URLConnection urlCon = s.openConnection();
                urlCon.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)");
                Scanner in = new Scanner(urlCon.getInputStream(), "UTF-8");
                out = in.useDelimiter("\\A").next();
                in.close();
            } catch(Exception e) {
                Log.e("search","Error in Url processing");
                Log.e("exception", e.toString());
            }

            return out;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        editTextSearchTerms = findViewById(R.id.inputEditText);
        searchButton = findViewById(R.id.searchButton);

        searchButton.setOnClickListener(new View.OnClickListener() {    //creates search button
            public void onClick(View view) {
                String out = "test";
                String url = parseUserInput(editTextSearchTerms.getText().toString());
                try{
                    out = getRecipesString(url);
                } catch(Exception e) {
                    Log.e("bad url", e.toString());
                }
                Gson gson = new Gson();
                SearchResponse res = gson.fromJson(out, SearchResponse.class);
                Recipes[] rec = res.getRecipes();
                send(rec);
            }
        });

        editTextSearchTerms.setOnEditorActionListener(new TextView.OnEditorActionListener() {   //changes so search starts when user press enter
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                String out = "test";
                String url = parseUserInput(editTextSearchTerms.getText().toString());
                try{
                    out = getRecipesString(url);
                } catch(Exception e) {
                    Log.e("bad url", e.toString());
                }
                Gson gson = new Gson();
                SearchResponse res = gson.fromJson(out, SearchResponse.class);
                Recipes[] rec = res.getRecipes();
                send(rec);
                return true;
            }
        });

    }

    public String parseUserInput(String in) {       //parses user input into appropriate url and returns url as a string\
        Log.e("raw in", in);
        String base = "http://food2fork.com/api/search?key=02a03461cd295f9dcf90a669c961e2fd&q=";
        String end = "&count=10";
        CharSequence space = " ";
        CharSequence spaceReplace = "%20";
        in = in.trim();
        in = in.replace(space, spaceReplace);
        Log.e("end url", base+in+end);
        return base + in + end;
    }

    //gets json string from url
    public String getRecipesString(String url) throws ExecutionException, InterruptedException, MalformedURLException {
        URL s = new URL(url);
        GetRecipeFeed feed = new GetRecipeFeed();
        return feed.execute(new URL[] {s}).get();
    }
    //sends recipes[] to BrowseActivity
    public void send(Recipes[] results) {
        Intent intent = new Intent(this, BrowseActivity.class);
        Bundle arg = new Bundle();
        arg.putSerializable("LIST", results);
        intent.putExtra("RECIPES", arg);

        startActivity(intent);
    }
}
