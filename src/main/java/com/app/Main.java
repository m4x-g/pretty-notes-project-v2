package com.app;

import com.app.Enums.Categories;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        User firstUserEver = new User("JC Denton");
        User secondUserEver = new User("Gunther Hermann");

        Note noteOne = firstUserEver.newUserNote("title 01", "some text", Categories.C1, Categories.C4);
        System.out.println("====== Printing specific single note ========================-");
        noteOne.printNote();
        System.out.println("============================================================--");
        System.out.println();

        Note noteTwo = firstUserEver.newUserNote("title 02", "some text here also");
        Note noteThree = firstUserEver.newUserNote("title 03", "wawa - jukebox", Categories.C3);

        Note noteFour = secondUserEver.newUserNote("note to self", "remember to be awesome");
        Note noteFive = secondUserEver.newUserNote("no title", "it's green >:(", Categories.C2);

        System.out.println("====== Printing ALL notes ====================================");
        PseudoDB.printAllNotes();
        System.out.println("==============================================================");
        System.out.println();
        System.out.println("====== Printing John Doe's notes =============================");
        PseudoDB.printAllUserNotes("John Doe");
        System.out.println("==============================================================");
        System.out.println();
        System.out.println("====== Printing JC Denton's notes ============================");
        PseudoDB.printAllUserNotes("JC Denton");
        System.out.println("==============================================================");
        System.out.println();
        System.out.println("====== Printing Gunther Hermann's notes ======================");
        PseudoDB.printAllUserNotes(secondUserEver.getUserName());
        System.out.println("==============================================================");
        System.out.println();

        Note noteSix = new Note();
        noteSix.setNoteBody("654");
        noteSix.setNoteCategories(Categories.C1, Categories.C3);
        System.out.println("--> " + noteSix.getNoteBody());
        System.out.println("---> " + noteSix.getNoteCategories());
        for (Categories cat : noteSix.getNoteCategories().getCategories()){
            System.out.println(" -c--> " + cat);
        }
    }
}
