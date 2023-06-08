package org.recipe.controller.rating;

import org.recipe.model.Recipe;


public class RecipeRatingObserver implements Observer {
    private Recipe recipe;

    public RecipeRatingObserver(Recipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public void update(Recipe recipe) {
        System.out.println("Recipe rating has been updated for " + recipe.getName());
        System.out.println("New impact: " + recipe.getImpact());
        System.out.println("Number of Ratings: " + recipe.getRatings().size());
    }


}