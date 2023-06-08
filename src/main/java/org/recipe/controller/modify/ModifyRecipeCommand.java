package org.recipe.controller.modify;

import org.recipe.model.Category;
import org.recipe.model.Recipe;
import org.recipe.model.Tag;

import java.util.List;

public class ModifyRecipeCommand implements Command {
    private Recipe recipe;
    private Recipe originalRecipe;
    private String newCookingInstructions;
    private List<String> newIngredients;
    private List<Category> newCategories;
    private List<Tag> newTags;

    public ModifyRecipeCommand(Recipe recipe, String newCookingInstructions, List<String> newIngredients, List<Category> newCategories, List<Tag> newTags) {
        this.recipe = recipe;
        this.newCookingInstructions = newCookingInstructions;
        this.newIngredients = newIngredients;
        this.newCategories = newCategories;
        this.newTags = newTags;
    }

    public void execute() {
        originalRecipe = new Recipe(recipe.getName(), recipe.getIngredients(), recipe.getInstructions(), recipe.getServingSize(),  recipe.getCategories() , recipe.getTags());
        recipe.setInstructions(newCookingInstructions);
        recipe.setIngredients(newIngredients);
        recipe.setCategories(newCategories);
        recipe.setTags(newTags);
    }

    public void undo() {
        recipe.setInstructions(originalRecipe.getInstructions());
        recipe.setIngredients(originalRecipe.getIngredients());
        recipe.setCategories(originalRecipe.getCategories());
        recipe.setTags(originalRecipe.getTags());
    }
}
