package Logic;

public abstract class Librarian {

	public abstract void registerOneBorrwer(String borrowerName);

	public abstract void addOneBook(String title, String author, int uniqueCatalogueNumber);
	
	public abstract void lendOneBook(String borrowName, Book book);
	
	public abstract void returnOneBook(Book book);
	
	public abstract void displayBooksAvailableForLoan();
	
	public abstract void displayBooksForLoan();

}
