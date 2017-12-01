package com.ee4461l_project.recipz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class SearchActivity extends AppCompatActivity {
    EditText editTextSearchTerms;
    Button searchButton;
    TextView testView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchButton = (Button)findViewById(R.id.button2);
    }
}
