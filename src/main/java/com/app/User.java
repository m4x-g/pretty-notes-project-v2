package com.app;

import com.app.Enums.Categories;

import java.util.ArrayList;

public class User {
    private long userId;
    private String userName;
    private String userPassword;
    private boolean validated;
    private ArrayList<String> errors = new ArrayList();

    public User() {
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public User(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public boolean isValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    public ArrayList getErrors() {
        return errors;
    }

    public void setErrors(String error) {
        this.errors.add(error);
    }

    protected Note newUserNote(String noteTitle, String noteBody, Categories... noteCategories){
        Note note = new Note(this.userName, noteTitle, noteBody, noteCategories);
        PseudoDB.storeNote(note);
        return note;
    }
}
