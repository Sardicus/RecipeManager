package org.recipe.model;

import org.recipe.controller.rating.Observer;

import java.util.ArrayList;
import java.util.List;

public class Recipe {
    private static int nextId = 1;
    private List<Observer> observers;
    private int id;
    private String name;
    private List<String> ingredients;
    private String instructions;
    private int servingSize;
    private List<Category> categories;
    private List<Tag> tags;
    private double impact = 0;
    private List<Integer> ratings;
    private boolean hasObserver;

    public Recipe(String name, List<String> ingredients, String instructions, int servingSize, List<Category> categories, List<Tag> tags ) {
        this.id = nextId++;
        this.name = name;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.servingSize = servingSize;
        this.categories = categories;
        this.tags = tags;
        this.ratings = new ArrayList<>();
        this.observers = new ArrayList<>();
        this.hasObserver = false;
    }
    public void addObserver(Observer observer) {
        if (!hasObserver) {
            observers.add(observer);
            hasObserver = true;
        }
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }
    public double getImpact() {
        if (ratings.isEmpty()) {
            return 0.0;
        }
        int sum = 0;
        for (int rating : ratings) {
            sum += rating;
        }
        return (double) sum / ratings.size();
    }
    public void addRating(int rating) {
        ratings.add(rating);
        notifyObservers();
    }
    public void removeRating(int rating) {
        ratings.remove(rating);
        notifyObservers();
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public int getServingSize() {
        return servingSize;
    }

    public void setServingSize(int servingSize) {
        this.servingSize = servingSize;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Integer> getRatings() {
        return ratings;
    }

    public void setImpact(double impact) {
        this.impact = impact;
    }

    public void setRatings(List<Integer> ratings) {
        this.ratings = ratings;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
