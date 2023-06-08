package org.recipe;

import org.recipe.controller.modify.*;
import org.recipe.controller.rating.Observer;
import org.recipe.controller.rating.RecipeRatingObserver;
import org.recipe.controller.search.*;
import org.recipe.model.Category;
import org.recipe.model.Recipe;
import org.recipe.controller.create.RecipeFactory;
import org.recipe.model.Tag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final List<Command> commandHistory = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RecipeFactory recipeFactory = new RecipeFactory();


        List<Recipe> recipeList = recipeFactory.getRecipeList();



        while (true) {
            System.out.println("Please choose an option:\n" +
                    "1. Create Recipe\n" +
                    "2. Search Recipes\n" +
                    "3. Modify Recipe\n" +
                    "4. Rate Recipe\n" +
                    "5. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    CreateRecipeCommand createRecipeCommand = new CreateRecipeCommand(recipeFactory);

                    createRecipeCommand.execute();
                    Recipe recipe = createRecipeCommand.getRecipe();

                    System.out.println("Would you like to keep this recipe? (Yes/No) ");
                    String saveRecipe = scanner.nextLine();
                    if (saveRecipe.equals("Yes"))
                    {
                        System.out.println("Recipe created with ID: " + recipe.getId());
                    } else if (saveRecipe.equals("No")) {
                       createRecipeCommand.undo();
                        System.out.println("Exiting");
                    }
                    break;

                case 2:
                    System.out.println("Choose search strategy:");
                    System.out.println("1. Search by name");
                    System.out.println("2. Search by ingredients");
                    System.out.println("3. Search by categories");
                    System.out.println("4. Search by tags");
                    String searchStrategyChoice = scanner.nextLine();
                    System.out.println("Enter search query:");
                    String searchQuery = scanner.nextLine();


                    RecipeSearch searchStrategy = null;
                    switch (searchStrategyChoice) {
                        case "1":
                            searchStrategy = new SearchByName(searchQuery);
                            break;
                        case "2":
                            searchStrategy = new SearchByIngredients(searchQuery);
                            break;
                        case "3":
                            Category searchCategory = new Category(searchQuery);
                            searchStrategy = new SearchByCategory(searchCategory);
                            break;
                        case "4":
                            Tag searchTag = new Tag(searchQuery);
                            searchStrategy = new SearchByTag(searchTag);
                            break;
                        default:
                            System.out.println("Invalid search strategy choice");
                            return;
                    }

                    SearchRecipeCommand searchRecipeCommand = new SearchRecipeCommand(recipeList, searchStrategy);
                    searchRecipeCommand.execute();
                    List<Recipe> searchResults = searchRecipeCommand.getSearchResults();

                    System.out.print("Enter the ID of the recipe to view in detail: ");
                    int recipeId = scanner.nextInt();
                    scanner.nextLine();
                    Recipe selectedRecipe = null;
                    for (Recipe recipeToView : searchResults) {
                        if (recipeToView.getId() == recipeId) {
                            selectedRecipe = recipeToView;
                            break;
                        }
                    }

                    if (selectedRecipe == null) {
                        System.out.println("Recipe with ID " + recipeId + " not found.");
                        return;
                    }

                    Command viewInDetailCommand = new ViewInDetailCommand(selectedRecipe);
                    viewInDetailCommand.execute();
                    commandHistory.add(viewInDetailCommand);
                    commandHistory.add(searchRecipeCommand);
                    break;
                case 3:
                    System.out.print("Enter recipe ID to modify: ");
                    int idToModify = scanner.nextInt();
                    scanner.nextLine();


                    Recipe recipeToModify = recipeFactory.getRecipe(idToModify);
                    if (recipeToModify == null) {
                        System.out.println("Recipe not found.");
                        break;
                    }


                    System.out.print("Enter new cooking instructions: ");
                    String newInstructions = scanner.nextLine();


                    System.out.print("Enter new ingredients (separated by commas): ");
                    String ingredientInput = scanner.nextLine();
                    List<String> newIngredients = Arrays.asList(ingredientInput.split(","));


                    System.out.print("Enter new categories (separated by commas): ");
                    String categoryInput = scanner.nextLine();
                    List<Category> newCategories = new ArrayList<>();
                    String[] categoryNames = categoryInput.split(",");
                    for (String categoryName : categoryNames) {
                        Category category = new Category(categoryName);
                        newCategories.add(category);
                    }


                    System.out.print("Enter new tags (separated by commas): ");
                    String tagInput = scanner.nextLine();
                    List<Tag> newTags = new ArrayList<>();
                    String[] tagNames = tagInput.split(",");
                    for (String tagName : tagNames) {
                        Tag tag = new Tag(tagName.trim());
                        newTags.add(tag);
                    }


                    Command modifyRecipeCommand = new ModifyRecipeCommand(recipeToModify, newInstructions, newIngredients, newCategories, newTags);
                    System.out.println("Would you like the save the changes?( Yes/No)");
                    String save = scanner.nextLine();
                    modifyRecipeCommand.execute();
                    if (save.equals("Yes"))
                    {
                        System.out.println("Recipe modified successfully.");
                    } else if (save.equals("No")) {
                        modifyRecipeCommand.undo();
                        System.out.println("Original recipe restored");
                    }

                    break;
                case 4:
                    System.out.print("Enter recipe ID to rate: ");
                    int idToRate = scanner.nextInt();
                    scanner.nextLine();

                    Recipe recipeToRate= recipeFactory.getRecipe(idToRate);
                    if (recipeToRate == null) {
                        System.out.println("Recipe not found.");
                        break;
                    }
                    Observer observer = new RecipeRatingObserver(recipeToRate);

                    recipeToRate.addObserver(observer);

                    int rating;

                    while (true) {
                        System.out.print("Enter a rating (1-5): ");
                        if (scanner.hasNextInt()) {
                            rating = scanner.nextInt();
                            if (rating >= 1 && rating <= 5) {
                                break;
                            } else {
                                System.out.println("Invalid rating. Please enter a number from 1 to 5.");
                            }
                        } else {
                            System.out.println("Invalid input. Please enter a number.");
                            scanner.nextLine();
                        }
                    }

                    RatingCommand addRatingCommand = new RatingCommand(recipeToRate, rating);
                    addRatingCommand.execute();

                    break;
                case 5:
                    System.out.println("Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}