package org.recipe.controller.search;

import org.recipe.model.Recipe;
import org.recipe.model.Tag;

import java.util.ArrayList;
import java.util.List;

public class SearchByTag<T extends Comparable<T>> implements RecipeSearch<Tag>{
    private Tag tag;

    public SearchByTag(Tag tag) {
        this.tag = tag;
    }

    public List<Recipe> search(List<Recipe> recipes) {
        List<Recipe> results = new ArrayList<>();
        for (Recipe recipe : recipes) {
            if (recipe.getCategories().stream().anyMatch(c -> c.getName().startsWith(tag.getName()))) {
                results.add(recipe);
            }
        }
        return results;
    }
}
