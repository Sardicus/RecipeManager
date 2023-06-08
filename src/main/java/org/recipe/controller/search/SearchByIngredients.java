package org.recipe.controller.search;

import org.recipe.model.Recipe;

import java.util.ArrayList;
import java.util.List;

public class SearchByIngredients<T extends Comparable<T>> implements RecipeSearch<String> {
    private String ingredient;

    public SearchByIngredients(String ingredient) {
        this.ingredient = ingredient;
    }


    public List<Recipe> search(List<Recipe> recipes) {
        List<Recipe> results = new ArrayList<>();
        for (Recipe recipe : recipes) {
            if (recipe.getIngredients().contains(ingredient)) {
                results.add(recipe);
            }
        }
        return results;
    }
}