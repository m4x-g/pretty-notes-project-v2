package com.app;

import com.app.Enums.Categories;

import java.util.HashSet;

public class NoteCategories {
    private HashSet<com.app.Enums.Categories> categories = new HashSet<>();
    private String categoriesList;

    public HashSet<Categories> getCategories() {
        return categories;
    }

    protected void addCategories(com.app.Enums.Categories... newCategory){
        for (com.app.Enums.Categories category : newCategory){
            this.categories.add(category);
        }
    }

    protected void printCategories(){
        for (com.app.Enums.Categories category : this.categories){
            System.out.print(" [" + category + "]");
        }
        System.out.println();
    }

    public String getCategoriesList(){
        categoriesList = "";
        for (Categories category : categories){
            categoriesList += "[ " + category + " ]";
        }
        return categoriesList;
    }
}
