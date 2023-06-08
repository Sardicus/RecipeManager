package org.recipe.controller.modify;

import org.recipe.controller.create.RecipeFactory;
import org.recipe.model.Recipe;

public class CreateRecipeCommand implements Command{

    private RecipeFactory recipeFactory;
    private Recipe recipe;

    public CreateRecipeCommand(RecipeFactory recipeFactory) {
        this.recipeFactory = recipeFactory;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    @Override
    public void execute() {
        recipe = recipeFactory.createRecipe();
    }

    @Override
    public void undo() {
        recipeFactory.deleteRecipe(recipe);
    }
}
