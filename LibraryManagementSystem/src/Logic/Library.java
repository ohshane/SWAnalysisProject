package Logic;

public class Library {
    private String name;
    
    public Library() {
    	this.name = "";
    }

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
    
    public void lendOneBook(int catalogueID, String name) {
    	if (DB.isBookExist(catalogueID) && DB.isBorrowerExist(name)){
            Loan loan = new Loan(catalogueID, name);
        	DB.lendOneBook(loan);
    	}
    }
    
    public void returnOneBook(int catalogueID) {
    	DB.returnOneBook(catalogueID);
    }
    
    public String displayBooksForLoan() {
    	return DB.displayBooksForLoan();
    }
    
    public String displayBooksOnLoan() {
        return DB.displayBooksOnLoan();
    }

    public String getName() {
        return name;
    }
}
