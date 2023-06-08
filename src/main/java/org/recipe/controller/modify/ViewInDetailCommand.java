package org.recipe.controller.modify;

import org.recipe.model.Recipe;

public class ViewInDetailCommand implements Command {
    private final Recipe selectedRecipe;

    public ViewInDetailCommand(Recipe selectedRecipe) {
        this.selectedRecipe = selectedRecipe;
    }

    @Override
    public void execute() {
        if (selectedRecipe == null) {
            System.out.println("No recipe selected.");
            return;
        }

        System.out.println("Recipe Details:");
        System.out.println("ID: " + selectedRecipe.getId());
        System.out.println("Name: " + selectedRecipe.getName());
        System.out.println("Ingredients: " + selectedRecipe.getIngredients());
        System.out.println("Serving Size: " + selectedRecipe.getServingSize());
        System.out.println("Categories: " + selectedRecipe.getCategories());
        System.out.println("Tags: " + selectedRecipe.getTags());
        System.out.println("Impact: " + selectedRecipe.getImpact());
        System.out.println("Number of ratings: " + selectedRecipe.getRatings().size());

    }

    @Override
    public void undo() {

    }
}