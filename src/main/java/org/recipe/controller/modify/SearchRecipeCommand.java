package org.recipe.controller.modify;

import org.recipe.controller.search.RecipeSearch;
import org.recipe.model.Recipe;

import java.util.ArrayList;
import java.util.List;
public class SearchRecipeCommand implements Command {
    private final List<Recipe> recipeList;
    private final RecipeSearch searchStrategy;
    private List<Recipe> previousSearchResults;

    public SearchRecipeCommand(List<Recipe> recipeList, RecipeSearch searchStrategy) {
        this.recipeList = recipeList;
        this.searchStrategy = searchStrategy;
    }

    @Override
    public void execute() {
        previousSearchResults = new ArrayList<>(recipeList);

        List<Recipe> searchResults = searchStrategy.search(recipeList);

        if (searchResults.isEmpty()) {
            System.out.println("No recipes found");
        } else {
            System.out.println("Search results:");
            for (Recipe result : searchResults) {
                System.out.println("Id: " + result.getId() + "\n" +
                        "Name: " + result.getName() + "\n" );
            }
            recipeList.clear();
            recipeList.addAll(searchResults);
        }
    }

    @Override
    public void undo() {
        recipeList.clear();
        recipeList.addAll(previousSearchResults);
    }
    public List<Recipe> getSearchResults() {
        return recipeList;
    }
}