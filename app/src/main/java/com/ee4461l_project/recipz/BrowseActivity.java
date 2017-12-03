package com.ee4461l_project.recipz;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.InputStream;

public class BrowseActivity extends AppCompatActivity {
    TextView recipeTitles[] = new TextView[10];
    CardView recipeCards[] = new CardView[10];
    TextView recipePublishers[] = new TextView[10];
    RatingBar recipeRanks[] = new RatingBar[10];
    Button nextButton;
    SearchResponse res;
    Recipes[] rec;
    static String searchParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);

        recipeTitles[0] = findViewById(R.id.recipeTitle0);
        recipeTitles[1] = findViewById(R.id.recipeTitle1);
        recipeTitles[2] = findViewById(R.id.recipeTitle2);
        recipeTitles[3] = findViewById(R.id.recipeTitle3);
        recipeTitles[4] = findViewById(R.id.recipeTitle4);
        recipeTitles[5] = findViewById(R.id.recipeTitle5);
        recipeTitles[6] = findViewById(R.id.recipeTitle6);
        recipeTitles[7] = findViewById(R.id.recipeTitle7);
        recipeTitles[8] = findViewById(R.id.recipeTitle8);
        recipeTitles[9] = findViewById(R.id.recipeTitle9);

        recipeCards[0] = findViewById(R.id.cardView0);
        recipeCards[1] = findViewById(R.id.cardView1);
        recipeCards[2] = findViewById(R.id.cardView2);
        recipeCards[3] = findViewById(R.id.cardView3);
        recipeCards[4] = findViewById(R.id.cardView4);
        recipeCards[5] = findViewById(R.id.cardView5);
        recipeCards[6] = findViewById(R.id.cardView6);
        recipeCards[7] = findViewById(R.id.cardView7);
        recipeCards[8] = findViewById(R.id.cardView8);
        recipeCards[9] = findViewById(R.id.cardView9);

        recipePublishers[0] = findViewById(R.id.recipePublisher0);
        recipePublishers[1] = findViewById(R.id.recipePublisher1);
        recipePublishers[2] = findViewById(R.id.recipePublisher2);
        recipePublishers[3] = findViewById(R.id.recipePublisher3);
        recipePublishers[4] = findViewById(R.id.recipePublisher4);
        recipePublishers[5] = findViewById(R.id.recipePublisher5);
        recipePublishers[6] = findViewById(R.id.recipePublisher6);
        recipePublishers[7] = findViewById(R.id.recipePublisher7);
        recipePublishers[8] = findViewById(R.id.recipePublisher8);
        recipePublishers[9] = findViewById(R.id.recipePublisher9);

        recipeRanks[0] = findViewById(R.id.recipeRank0);
        recipeRanks[1] = findViewById(R.id.recipeRank1);
        recipeRanks[2] = findViewById(R.id.recipeRank2);
        recipeRanks[3] = findViewById(R.id.recipeRank3);
        recipeRanks[4] = findViewById(R.id.recipeRank4);
        recipeRanks[5] = findViewById(R.id.recipeRank5);
        recipeRanks[6] = findViewById(R.id.recipeRank6);
        recipeRanks[7] = findViewById(R.id.recipeRank7);
        recipeRanks[8] = findViewById(R.id.recipeRank8);
        recipeRanks[9] = findViewById(R.id.recipeRank9);

        Intent intent = getIntent();
        Bundle arg = intent.getBundleExtra("RECIPES");
        searchParams = intent.getStringExtra("SEARCH_PARAMS");
        res = (SearchResponse)arg.getSerializable("LIST");

        rec = res.getRecipes();

        updateUI();

        nextButton = findViewById(R.id.nextPageBtn);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    private void updateUI() {
        new DownloadImageTask((ImageView) findViewById(R.id.recipeImage0))
                .execute(rec[0].getImage_url());
        new DownloadImageTask((ImageView) findViewById(R.id.recipeImage1))
                .execute(rec[1].getImage_url());
        new DownloadImageTask((ImageView) findViewById(R.id.recipeImage2))
                .execute(rec[2].getImage_url());
        new DownloadImageTask((ImageView) findViewById(R.id.recipeImage3))
                .execute(rec[3].getImage_url());
        new DownloadImageTask((ImageView) findViewById(R.id.recipeImage4))
                .execute(rec[4].getImage_url());
        new DownloadImageTask((ImageView) findViewById(R.id.recipeImage5))
                .execute(rec[5].getImage_url());
        new DownloadImageTask((ImageView) findViewById(R.id.recipeImage6))
                .execute(rec[6].getImage_url());
        new DownloadImageTask((ImageView) findViewById(R.id.recipeImage7))
                .execute(rec[7].getImage_url());
        new DownloadImageTask((ImageView) findViewById(R.id.recipeImage8))
                .execute(rec[8].getImage_url());
        new DownloadImageTask((ImageView) findViewById(R.id.recipeImage9))
                .execute(rec[9].getImage_url());

        recipeCards[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view == recipeCards[0]) {
                    Intent changeToRecipeViewActivity = new Intent(getApplicationContext(), RecipeViewActivity.class);
                    changeToRecipeViewActivity.putExtra("sourceURL", rec[0].getSource_url());
                    startActivity(changeToRecipeViewActivity);
                }
            }
        });
        recipeCards[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view == recipeCards[1]) {
                    Intent changeToRecipeViewActivity = new Intent(getApplicationContext(), RecipeViewActivity.class);
                    changeToRecipeViewActivity.putExtra("sourceURL", rec[1].getSource_url());
                    startActivity(changeToRecipeViewActivity);
                }
            }
        });
        recipeCards[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view == recipeCards[2]) {
                    Intent changeToRecipeViewActivity = new Intent(getApplicationContext(), RecipeViewActivity.class);
                    changeToRecipeViewActivity.putExtra("sourceURL", rec[2].getSource_url());
                    startActivity(changeToRecipeViewActivity);
                }
            }
        });

        recipeCards[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view == recipeCards[3]) {
                    Intent changeToRecipeViewActivity = new Intent(getApplicationContext(), RecipeViewActivity.class);
                    changeToRecipeViewActivity.putExtra("sourceURL", rec[3].getSource_url());
                    startActivity(changeToRecipeViewActivity);
                }
            }
        });

        recipeCards[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view == recipeCards[4]) {
                    Intent changeToRecipeViewActivity = new Intent(getApplicationContext(), RecipeViewActivity.class);
                    changeToRecipeViewActivity.putExtra("sourceURL", rec[4].getSource_url());
                    startActivity(changeToRecipeViewActivity);
                }
            }
        });

        recipeCards[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view == recipeCards[5]) {
                    Intent changeToRecipeViewActivity = new Intent(getApplicationContext(), RecipeViewActivity.class);
                    changeToRecipeViewActivity.putExtra("sourceURL", rec[5].getSource_url());
                    startActivity(changeToRecipeViewActivity);
                }
            }
        });

        recipeCards[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view == recipeCards[6]) {
                    Intent changeToRecipeViewActivity = new Intent(getApplicationContext(), RecipeViewActivity.class);
                    changeToRecipeViewActivity.putExtra("sourceURL", rec[6].getSource_url());
                    startActivity(changeToRecipeViewActivity);
                }
            }
        });

        recipeCards[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view == recipeCards[7]) {
                    Intent changeToRecipeViewActivity = new Intent(getApplicationContext(), RecipeViewActivity.class);
                    changeToRecipeViewActivity.putExtra("sourceURL", rec[7].getSource_url());
                    startActivity(changeToRecipeViewActivity);
                }
            }
        });

        recipeCards[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view == recipeCards[8]) {
                    Intent changeToRecipeViewActivity = new Intent(getApplicationContext(), RecipeViewActivity.class);
                    changeToRecipeViewActivity.putExtra("sourceURL", rec[8].getSource_url());
                    startActivity(changeToRecipeViewActivity);
                }
            }
        });
        recipeCards[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view == recipeCards[9]) {
                    Intent changeToRecipeViewActivity = new Intent(getApplicationContext(), RecipeViewActivity.class);
                    changeToRecipeViewActivity.putExtra("sourceURL", rec[9].getSource_url());
                    startActivity(changeToRecipeViewActivity);
                }
            }
        });

        for(int i = 0; i <= 9; i++) {
            recipeTitles[i].setText(rec[i].getTitle());
            recipePublishers[i].setText(rec[i].getPublisher());
            recipeRanks[i].setRating((float) (rec[i].getSocial_rank()/20.0));
        }
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

}
