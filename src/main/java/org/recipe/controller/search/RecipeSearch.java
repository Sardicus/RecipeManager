package org.recipe.controller.search;

import org.recipe.model.Recipe;

import java.util.List;

public interface RecipeSearch<T extends Comparable<T>> {
    List<Recipe> search(List<Recipe> recipes);
}
