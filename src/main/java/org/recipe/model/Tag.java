package org.recipe.model;

public class Tag implements Comparable<Tag>{
    String name;

    public Tag(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Tag o) {
        return name.compareTo(o.getName());
    }

    @Override
    public String toString() {
        return name ;
    }
}
