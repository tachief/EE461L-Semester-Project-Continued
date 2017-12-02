package com.ee4461l_project.recipz;

import java.io.Serializable;

/**
 * Created by tachief on 12/1/17.
 */

public class SearchResponse implements Serializable{
    int count;
    Recipes[] recipes;

    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public Recipes[] getRecipes() {
        return recipes;
    }
    public void setRecipes(Recipes[] recipes) {
        this.recipes = recipes;
    }

}

class Recipes implements Serializable{
    String publisher;
    String f2f_url;
    String title;
    String source_url;
    String recipe_id;
    String image_url;
    Double social_rank;
    String publsiher_url;

    public String getPublisher() {
        return publisher;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    public String getF2f_url() {
        return f2f_url;
    }
    public void setF2f_url(String f2f_url) {
        this.f2f_url = f2f_url;
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
    public String getRecipe_id() {
        return recipe_id;
    }
    public void setRecipe_id(String recipe_id) {
        this.recipe_id = recipe_id;
    }
    public String getImage_url() { return image_url; }
    public void setImage_url(String image_url) { this.image_url = image_url; }
    public Double getSocial_rank() {
        return social_rank;
    }
    public void setSocial_rank(Double social_rank) {
        this.social_rank = social_rank;
    }
    public String getPublsiher_url() {
        return publsiher_url;
    }
    public void setPublsiher_url(String publsiher_url) {
        this.publsiher_url = publsiher_url;
    }
}



