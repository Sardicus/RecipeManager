package org.recipe.controller.modify;

import org.recipe.model.Recipe;

public class RatingCommand implements Command {
    private Recipe recipe;
    private int rating;

    public RatingCommand(Recipe recipe, int rating) {
        this.recipe = recipe;
        this.rating = rating;
    }

    @Override
    public void execute() {
        recipe.addRating(rating);
    }

    @Override
    public void undo() {
        recipe.removeRating(rating);
    }
}