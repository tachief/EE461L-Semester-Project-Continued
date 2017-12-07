package com.ee4461l_project.recipz;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.InputStream;

public class BrowseActivity extends AppCompatActivity {
    TextView recipeTitles[] = new TextView[10];
    CardView recipeCards[] = new CardView[10];
    TextView recipePublishers[] = new TextView[10];
    RatingBar recipeRanks[] = new RatingBar[10];
    TextView browseErrorMsg;
    Button nextButton;
    SearchResponse res;
    Recipes[] rec;
    int page = 1;
    static String searchParams;

    public static float dpToPx(float dp, DisplayMetrics displayMetrics){
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics);
        return px;
    }

    class myOnClickListener implements View.OnClickListener{
        int index;
        public myOnClickListener(int index){
            this.index = index;
        }

        @Override
        public void onClick(View v){
            Intent changeToRecipeViewActivity = new Intent(getApplicationContext(), RecipeViewActivity.class);
            changeToRecipeViewActivity.putExtra("sourceURL", rec[index].getSource_url());
            changeToRecipeViewActivity.putExtra("title", rec[index].getTitle());
            changeToRecipeViewActivity.putExtra("imageURL", rec[index].getImage_url());
            changeToRecipeViewActivity.putExtra("socialRank", rec[index].getSocial_rank());
            changeToRecipeViewActivity.putExtra("f2fURL", rec[index].getF2f_url());
            changeToRecipeViewActivity.putExtra("publisherURL", rec[index].getPublisher_url());
            changeToRecipeViewActivity.putExtra("publisher", rec[index].getPublisher());
            changeToRecipeViewActivity.putExtra("recipeId", rec[index].getRecipe_id());
            startActivity(changeToRecipeViewActivity);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);

        browseErrorMsg = findViewById(R.id.browseErrorMsg);
        browseErrorMsg.setText("");
        browseErrorMsg.setVisibility(View.GONE);

        Intent intent = getIntent();
        Bundle arg = intent.getBundleExtra("RECIPES");
        searchParams = intent.getStringExtra("SEARCH_PARAMS");

        res = (SearchResponse)arg.getSerializable("LIST");
        rec = res.getRecipes();

        try {
            if (rec[0] == null || rec[1] == null || rec[2] == null || rec[3] == null || rec[4] == null || rec[5] == null || rec[6] == null || rec[7] == null || rec[8] == null || rec[9] == null) {
                return;
            }
        }
        catch (Exception e) {
            for(int i = 0; i <= 9; i++) {
                recipeCards[i].setVisibility(View.GONE);
            }
            browseErrorMsg.setText("No Recipes Found");
            browseErrorMsg.setVisibility(View.VISIBLE);
            findViewById(R.id.nextPageBtn).setVisibility(View.GONE);
            return;
        }

        updateUI();

        nextButton = findViewById(R.id.nextPageBtn);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page++;

                SearchActivity searchActivity = new SearchActivity();
                String URL = searchActivity.parseUserInput(searchParams, page);
                String out = "";
                try{
                    out = searchActivity.getRecipesString(URL);
                } catch(Exception e) {
                    Log.e("bad url", e.toString());
                }
                Gson gson = new Gson();
                res = gson.fromJson(out, SearchResponse.class);
                rec = res.getRecipes();
                ScrollView scrollView = findViewById(R.id.recipeScroll);
                scrollView.fullScroll(ScrollView.FOCUS_UP);
                updateUI();
            }
        });

    }

    private void updateUI() {
        try {
            if (rec[0] == null || rec[1] == null || rec[2] == null || rec[3] == null || rec[4] == null || rec[5] == null || rec[6] == null || rec[7] == null || rec[8] == null || rec[9] == null) {
                return;
            }
        }
        catch (Exception e) {
            Log.e("Exception Thrown", "No more recipes");
            browseErrorMsg.setText("No More Recipes Found");
            browseErrorMsg.setVisibility(View.VISIBLE);
            return;
        }

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        for(int i=0; i<10;i++){
            CardView cardView = new CardView(this);
            LinearLayout.LayoutParams cardViewParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            cardViewParams.setMargins((int)dpToPx(10, displayMetrics),(int)dpToPx(5, displayMetrics), (int)dpToPx(10, displayMetrics), 0);
            cardView.setLayoutParams(cardViewParams);
            cardView.setRadius(dpToPx(8, displayMetrics));
            cardView.setElevation(dpToPx(15, displayMetrics));

            cardView.setOnClickListener(new myOnClickListener(i));

            LinearLayout linearLayoutInCard = new LinearLayout(this);
            linearLayoutInCard.setGravity(Gravity.CENTER);
            linearLayoutInCard.setOrientation(LinearLayout.VERTICAL);
            linearLayoutInCard.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            cardView.addView(linearLayoutInCard);

            TextView title = new TextView(this);
            title.setGravity(Gravity.CENTER);
            title.setText(rec[i].getTitle());
            LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            titleParams.setMargins((int)dpToPx(2.5f, displayMetrics), 0, (int)dpToPx(2.5f, displayMetrics), 0);
            title.setLayoutParams(titleParams);
            title.setTextSize(20);
            linearLayoutInCard.addView(title);

            TextView publisher = new TextView(this);
            publisher.setGravity(Gravity.CENTER);
            publisher.setText(rec[i].getPublisher());
            LinearLayout.LayoutParams publisherParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            publisherParams.setMargins((int)dpToPx(2.5f, displayMetrics), 0, (int)dpToPx(2.5f, displayMetrics), (int)dpToPx(2.5f, displayMetrics));
            publisher.setLayoutParams(titleParams);
            publisher.setTextSize(18);
            linearLayoutInCard.addView(publisher);

            ImageView recipeImage = new ImageView(this);
            new DownloadImageTask(recipeImage)
                    .execute(rec[i].getImage_url());
            recipeImage.setAdjustViewBounds(true);
            LinearLayout.LayoutParams recipeImageParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            recipeImage.setLayoutParams(recipeImageParams);
            recipeImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
            linearLayoutInCard.addView(recipeImage);

            RelativeLayout ratingView = new RelativeLayout(this);
            ratingView.setGravity(Gravity.CENTER);
            LinearLayout.LayoutParams ratingViewParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
            ratingView.setLayoutParams(ratingViewParams);
            linearLayoutInCard.addView(ratingView);

            RatingBar ratingBar = new RatingBar(this);
            ratingBar.setRating((float)(rec[i].getSocial_rank()/20));
            LinearLayout.LayoutParams ratingBarParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            ratingBar.setLayoutParams(ratingBarParams);
            ratingBar.setNumStars(5);
            ratingBar.setIsIndicator(true);
            LayerDrawable stars = (LayerDrawable)ratingBar.getProgressDrawable();
            stars.getDrawable(2).setColorFilter(Color.parseColor("#D4AF37"), PorterDuff.Mode.SRC_ATOP);     //rating
            //stars.getDrawable(0).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);    //background
            stars.getDrawable(1).setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);      //shadow of star i think
            ratingView.addView(ratingBar);

            LinearLayout recipeViewer = findViewById(R.id.recipeViewer);
            recipeViewer.addView(cardView);
        }

        //for loop that capitalize the beginning of each word in the title
//        for(int i = 0; i <= 9; i++) {
//            recipeTitles[i].setText(rec[i].getTitle().substring(0, 1).toUpperCase() + rec[i].getTitle().substring(1));
//            recipePublishers[i].setText(rec[i].getPublisher());
//            recipeRanks[i].setRating((float) (rec[i].getSocial_rank()/20.0));
//        }
    }

    public static class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
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
