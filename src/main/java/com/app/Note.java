package com.app;

import com.app.Enums.Categories;

import java.util.HashSet;

public class Note {
    private long noteCreatorId;
    private String noteCreator;
    private String noteTitle;
    private String noteBody;
    private NoteCategories noteCategories = new NoteCategories();
    private HashSet<com.app.Enums.Categories> categories = new HashSet<>();
    private String[] categoriesArray;

    public Note() {
        this.noteCreator = "";
        this.noteTitle = "";
        this.noteBody = "";
    }

    public Note(String noteCreator, String noteTitle, String noteBody, Categories... noteCategories) {
        this.noteCreator = noteCreator;
        this.noteTitle = noteTitle;
        this.noteBody = noteBody;
        this.noteCategories.addCategories(noteCategories);
    }

    public long getNoteCreatorId() {
        return noteCreatorId;
    }

    public void setNoteCreatorId(long noteCreatorId) {
        this.noteCreatorId = noteCreatorId;
    }

    public String[] getCategoriesArray() {
        return categoriesArray;
    }

    public void setCategoriesArray(String[] categoriesArray) {
        this.categoriesArray = categoriesArray;
    }

    public HashSet<Categories> getCategories() {
        return categories;
    }

    public void setCategories(HashSet<Categories> categories) {
        this.categories = categories;
    }

    public String getNoteCreator() {
        return noteCreator;
    }

    public void setNoteCreator(String noteCreator) {
        this.noteCreator = noteCreator;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteBody() {
        return noteBody;
    }

    public void setNoteBody(String noteBody) {
        this.noteBody = noteBody;
    }

    public NoteCategories getNoteCategories() {
        return noteCategories;
    }

    public void setNoteCategories(Categories... noteCategories) {
        this.noteCategories.addCategories(noteCategories);
    }

    public void setNoteCategories(HashSet<Categories> categories) {
        this.noteCategories.setCategories(categories);
    }

    protected void printNote(){
        System.out.println("------------------------------------------------------");
        System.out.println("I was created by: " + this.noteCreator);
        System.out.println("------------------------------------------------------");
        System.out.println("My title is: " + this.noteTitle);
        System.out.println("------------------------------------------------------");
        System.out.println("I contain the following text: " + this.noteBody);
        System.out.println("------------------------------------------------------");
        System.out.print("I have following categories: ");
        this.noteCategories.printCategories();
        System.out.println("------------------------------------------------------");
    }
}
