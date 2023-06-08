package org.recipe.controller.rating;

import org.recipe.model.Recipe;

public interface Observer {
    void update(Recipe recipe);
}