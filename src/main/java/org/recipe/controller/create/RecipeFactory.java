package org.recipe.controller.create;

import org.recipe.model.Category;
import org.recipe.model.Recipe;
import org.recipe.model.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RecipeFactory {
    private List<Recipe> recipeList;

    public RecipeFactory() {
        recipeList = new ArrayList<Recipe>();
    }

    public Recipe createRecipe() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter recipe name: ");
        String name = scanner.nextLine();

        System.out.print("Enter ingredients (comma-separated): ");
        String ingredientsString = scanner.nextLine();
        List<String> ingredients = new ArrayList<String>();
        for (String ingredient : ingredientsString.split(",")) {
            ingredients.add(ingredient.trim());
        }

        System.out.print("Enter cooking instructions: ");
        String cookingInstructions = scanner.nextLine();

        System.out.print("Enter serving size: ");
        int servingSize = 0;
        try {
             servingSize = scanner.nextInt();
        }catch (Exception ex){
            System.out.println("Enter a valid number");
            servingSize = scanner.nextInt();
        }

        scanner.nextLine();

        System.out.print("Enter up to three categories (comma-separated): ");
        String categoriesString = scanner.nextLine();
        List<Category> categories = new ArrayList<Category>();
        for (String category : categoriesString.split(",")) {
            if (categories.size() < 3 && !category.trim().isEmpty()) {
                Category newCategory = new Category(category.trim());
                categories.add(newCategory);
            }
        }

        System.out.print("Enter up to three tags (comma-separated): ");
        String tagsString = scanner.nextLine();
        List<Tag> tags = new ArrayList<Tag>();
        for (String tag : tagsString.split(",")) {
            if (tags.size() < 3 && !tag.trim().isEmpty()) {
                Tag newTag = new Tag(tag.trim());
                tags.add(newTag);
            }
        }

        Recipe recipe = new Recipe(name, ingredients, cookingInstructions, servingSize, categories, tags);

        recipeList.add(recipe);

        System.out.println("Recipe saved successfully.");
        return recipe;
    }

    public List<Recipe> getRecipeList() {
        return recipeList;
    }
    public Recipe getRecipe(int id){
        for (Recipe recipe : recipeList) {
        if (recipe.getId() == id){
            return recipe;
        }
    }
        return null; }
    public void deleteRecipe(Recipe recipeToDelete) {
        recipeList.remove(recipeToDelete);
    }
}