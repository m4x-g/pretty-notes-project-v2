import Enums.Categories;

public class User {
    private String userName;

    public User(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    protected Note newUserNote(String noteTitle, String noteBody, Categories... noteCategories){
        Note note = new Note(this.userName, noteTitle, noteBody, noteCategories);
        PseudoDB.storeNote(note);
        return note;
    }
}
