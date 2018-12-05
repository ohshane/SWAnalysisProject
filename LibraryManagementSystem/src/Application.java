import Logic.DB;
import Logic.Library;

public class Application {
    public static void main(String[] args) {
        Library library = new Library("Sunmoon University Library");
        System.out.println(library.getName());

        DB.createNewDatabase("library.db");
        DB.createNewTable("library.db");


        library.registerOneBorrwer("Alice");
        library.registerOneBorrwer("Bob");
        library.registerOneBook("test title 1", "Mune");

        library.displayBooksForLoan();
        library.displayBooksOnLoan();

        library.displayBooksForLoan();
        library.displayBooksOnLoan();

        library.returnOneBook(3);

        library.displayBooksForLoan();
        library.displayBooksOnLoan();

    }
}
