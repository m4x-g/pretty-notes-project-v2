package Enums;

public enum Categories {
    C1("people"),
    C2("orcs"),
    C3("music"),
    C4("explosions");

    private String categoryName;

    Categories(String categoryName) {
        this.categoryName = categoryName;
    }
    @Override
    public String toString(){
        return this.categoryName;
    }
}
