package Logic;

public class Library {
    private String name;

    public Library(String name) {
    	this.name = name;
    }
    
    public void registerOneBorrwer(String borrowerName) {
        Borrower borrower = new Borrower(borrowerName);
        DB.registerOneBorrower(borrower);
    }
    
    public void registerOneBook(String title, String author) {
        Book book = new Book(title, author);
        DB.registerOneBook(book);
    }
    
    public void lendOneBook(int catalogueID, int borrowerID) {
        Loan loan = new Loan(catalogueID, borrowerID);
    	DB.lendOneBook(loan);
    }
    
    public void returnOneBook(int catalogueID) {
    	DB.returnOneBook(catalogueID);
    }
    
    public void displayBooksForLoan() {
    	DB.displayBooksForLoan();
    }
    
    public void displayBooksOnLoan() {
        DB.displayBooksOnLoan();
    }

    public String getName() {
        return name;
    }
}
