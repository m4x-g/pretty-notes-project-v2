import java.util.HashSet;

public class NoteCategories {
    private HashSet<Enums.Categories> categories = new HashSet<>();

    protected void addCategories(Enums.Categories... newCategory){
        for (Enums.Categories category : newCategory){
            this.categories.add(category);
        }
    }

    protected void printCategories(){
        for (Enums.Categories category : this.categories){
            System.out.print(" [" + category + "]");
        }
        System.out.println();
    }
}
