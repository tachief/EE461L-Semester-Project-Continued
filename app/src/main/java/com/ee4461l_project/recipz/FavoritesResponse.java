package com.ee4461l_project.recipz;

import java.util.ArrayList;

/**
 * Created by tachief on 12/3/17.
 */

public class FavoritesResponse {
    ArrayList<Recipes> recipes;

    public FavoritesResponse () {
        recipes = new ArrayList<Recipes>();
    }

    public ArrayList<Recipes> getRecipes() {
        return recipes;
    }

    public void setRecipes(ArrayList<Recipes> recipes) {
        this.recipes = recipes;
    }

}

class Favorite {
    String title;
    String source_url;
    String image_url;

    public Favorite() {
        this.title = "";
        this.source_url = "";
        this.image_url = "";
    }

    public Favorite (String title, String source_url, String image_url){
        this.title = title;
        this.source_url = source_url;
        this.image_url = image_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource_url() {
        return source_url;
    }

    public void setSource_url(String source_url) {
        this.source_url = source_url;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
