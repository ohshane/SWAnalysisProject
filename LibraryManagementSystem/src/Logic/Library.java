package Logic;

import java.util.Iterator;

public class Library {
    private String name;

    public Library(String name) {
    	this.name = name;
    }
    
    public void registerOneBorrwer(String borrowerName) {
    	
    }
    
    public void addOneBook(String title, String author, int uniqueCatalogueNumber) {
    	
    }
    
    public void lendOneBook(String borrwerName, Book book) {
    	
    }
    
    public void returnOneBook(Book book) {
    	
    }
    
    public void displayBooksAvailableForLoan() {
    	
    }
    
    public void displayBooksForLoan() {
    	Iterator iter = registeredBooks.iterator();
    	System.out.println("/nBooks available for loan");
    	
    	while(iter.hasNext() == true)
    	{
    		Book book = (Book)iter.next();
    		if(book.getBorrower() == null)
    		{
    			 book.display();
    		}
    	}
    }

    public String getName() {
        return name;
    }
}
