package org.recipe.controller.search;

import org.recipe.model.Category;
import org.recipe.model.Recipe;

import java.util.ArrayList;
import java.util.List;

public class SearchByCategory<T extends Comparable<T>> implements RecipeSearch<Category> {
    private Category category;

    public SearchByCategory(Category category) {
        this.category = category;
    }

    public List<Recipe> search(List<Recipe> recipes) {
        List<Recipe> results = new ArrayList<>();
        for (Recipe recipe : recipes) {
            if (recipe.getCategories().stream().anyMatch(c -> c.getName().startsWith(category.getName()))) {
                results.add(recipe);
            }
        }
        return results;
    }
}
