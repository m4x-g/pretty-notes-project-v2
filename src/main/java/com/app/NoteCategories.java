package com.app;

import java.util.HashSet;

public class NoteCategories {
    private HashSet<com.app.Enums.Categories> categories = new HashSet<>();

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
}
