package Logic;

public class Book {
	private int catalogueID;
	private String title;
	private String author;

	//const
	public Book(String title, String author) {
		this.title = title;
		this.author = author;
	}

	public Book(int catalogueID, String title, String author) {
		this.catalogueID = catalogueID;
		this.title = title;
		this.author = author;
	}


	//get-set
	public int getcatalogueID() {
		return catalogueID;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}


	public void setcatalogueID(int catalogueID) {
		this.catalogueID = catalogueID;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAuthor(String author) {
		this.author = author;
	}


	//methods
	@Override
	public String toString() {
		return String.format("%d, %s, %s", catalogueID, title, author);
	}

}
