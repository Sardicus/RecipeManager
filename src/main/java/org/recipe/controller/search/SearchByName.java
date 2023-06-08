package org.recipe.controller.search;

import org.recipe.model.Recipe;

import java.util.ArrayList;
import java.util.List;

public class SearchByName<T extends Comparable<T>> implements RecipeSearch<String>{
    private String name;

    public SearchByName(String name) {
        this.name = name;
    }
    public List<Recipe> search(List<Recipe> recipes) {
        List<Recipe> results = new ArrayList<>();
        for (Recipe recipe : recipes) {
            if (recipe.getName().startsWith(name)) {
                results.add(recipe);
            }
        }
        return results;
    }
}
