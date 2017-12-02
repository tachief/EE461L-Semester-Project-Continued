package com.ee4461l_project.recipz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SearchActivity extends AppCompatActivity {
    EditText editTextSearchTerms;
    Button searchButton;
    TextView testView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchButton = (Button)findViewById(R.id.button2);

        searchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                editTextSearchTerms   = (EditText)findViewById(R.id.editText);
                testView = (TextView)findViewById(R.id.textView1);
                testView.setText("Welcome "+testView.getText().toString()+"!");
            }
        });
    }
}
