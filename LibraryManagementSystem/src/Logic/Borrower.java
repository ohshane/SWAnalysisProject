package Logic;

public class Borrower {
    private String name;
    
    public Borrower(String name) {
        this.name = name;
    }
    
    public void attachBook() {
    	
    }
    
    public void detachBook() {
    	
    }
    
    public int compareTo(Object obj) {
    	return (int) obj;
    }
    
    public boolean equals(Object obj) {
    	return (boolean) obj;
    }
    
    public int hashCode() {
        return 0;
    }
}
