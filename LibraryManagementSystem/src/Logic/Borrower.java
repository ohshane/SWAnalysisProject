package Logic;

public class Borrower {
    private int borrowerID;
    private String name;

    //const
    public Borrower(String name) {
        this.name = name;
    }

    public Borrower(int borrowerID, String name) {
        this.borrowerID = borrowerID;
        this.name = name;
    }

    //get-set
    public int getBorrowerID() {
        return borrowerID;
    }

    public String getName() {
        return name;
    }


    public void setBorrowerID(int borrowerID) {
        this.borrowerID = borrowerID;
    }

    public void setName(String name) {
        this.name = name;
    }


    //methods
    @Override
    public String toString() {
        return String.format("%d, %s", borrowerID, name);
    }

}
