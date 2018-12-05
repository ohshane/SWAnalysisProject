package Logic;

import java.sql.*;

public class DB {

    public static String fileName = "library.db";
    public static String url = "jdbc:sqlite:./db/" + fileName;

    public static void main(String[] args) {
    	createNewDatabase(url);
    	connect(url);
    }

	public static void connect(String url) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url);

			System.out.println("Connected");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
		}
	}
	
	public static void createNewDatabase(String fileName) {

        DB.fileName = fileName;
 
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createNewTable(String fileName) {

        String sqlBookCreate = "CREATE TABLE IF NOT EXISTS book(" +
                "catalogueID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title VARCHAR(255) NOT NULL, " +
                "author VARCHAR(255) NOT NULL)";

        String sqlBorrowerCreate = "CREATE TABLE IF NOT EXISTS borrower(" +
                "borrowerID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name VARCHAR(45) NOT NULL, UNIQUE(borrowerID, name))";

        String sqlLoanCreate = "CREATE TABLE IF NOT EXISTS loan(" +
                "catalogueID INTEGER PRIMARY KEY, " +
                "borrowerID INTEGER, " +
                "loanStartDate VARCHAR(10), " +
                "loanEndDate VARCHAR(10), UNIQUE(catalogueID))";

        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sqlBookCreate);
            stmt.execute(sqlBorrowerCreate);
            stmt.execute(sqlLoanCreate);
            System.out.println("Created tables");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }


    public static void registerOneBorrower(Borrower borrower) {

        if (!DB.isBorrowerExist(borrower.getName())) {

            String sql = String.format("INSERT OR IGNORE INTO borrower (name) VALUES ('%s')", borrower.getName());

            try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {

                stmt.execute(sql);
                sql = "SELECT borrowerID FROM borrower ORDER BY borrowerID DESC LIMIT 1";

                ResultSet rs = stmt.executeQuery(sql);

                System.out.printf("이용자를 등록했습니다. %d, %s\n", rs.getInt(1), borrower.getName());

            } catch (SQLException e) {
                System.out.println("이용자등록을 등록실패했습니다." + e.getMessage());
            }
        } else {
            System.out.println("이용자등록을 등록실패했습니다.");
        }
    }

    public static void registerOneBook(Book book) {
        String sql = String.format("INSERT OR IGNORE INTO book (title, author) VALUES ('%s', '%s')", book.getTitle(), book.getAuthor());
        try (Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement()) {

            stmt.execute(sql);
            
            sql = "SELECT catalogueID FROM book ORDER BY catalogueID DESC LIMIT 1";
            ResultSet rs = stmt.executeQuery(sql);
            int i = rs.getInt(1);
            
            sql = "INSERT OR IGNORE INTO loan (catalogueID) VALUES (" + i + ")";
            stmt.execute(sql);
            System.out.printf("책을 등록했습니다. %d, %s, %s\n", i, book.getTitle(), book.getAuthor());
            
        } catch (SQLException e) {
        	System.out.println("책을 등록실패했습니다." + e.getMessage());
        }

    }

    public static String displayBooksForLoan() {
        String sql = "SELECT l.catalogueID, b.title, b.author FROM loan AS l " +
                "LEFT JOIN book AS b ON l.catalogueID == b.catalogueID " +
                "WHERE l.borrowerID ISNULL";

        try (Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("books for loan: ");

            StringBuffer sb = new StringBuffer();
            while (rs.next()) {
                sb.append(String.format("%d, %s, %s\n", rs.getInt(1), rs.getString(2), rs.getString(3)));
            }

            System.out.println(sb.toString());
            return sb.toString();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    public static String displayBooksOnLoan() {
	    String sql = "SELECT l.catalogueID, b.title, b.author, bo.borrowerID, bo.name, l.loanStartDate, l.loanEndDate FROM loan AS l " +
                "LEFT JOIN book AS b ON l.catalogueID == b.catalogueID " +
	    		"LEFT JOIN borrower as bo ON l.borrowerID == bo.borrowerID " +
                "WHERE l.borrowerID IS NOT NULL";

        try (Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);

            StringBuffer sb = new StringBuffer();

            System.out.println("books on loan: ");
            while (rs.next()) {
                sb.append(String.format("%d, %s, %s, %d, %s, %s, %s\n", rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7)));
            }

            System.out.println(sb.toString());
            return sb.toString();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void lendOneBook(Loan loan) {

        String sql = String.format("SELECT borrowerID FROM borrower WHERE name = '%s'", loan.getName());
        int borrowerID = 0;
        try (Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement()) {

        	ResultSet rs = stmt.executeQuery(sql);
            borrowerID = rs.getInt(1);

        } catch (SQLException e) {
            System.out.println("대출실패되었습니다. " + e.getMessage());
        }

        sql = String.format("UPDATE OR IGNORE loan SET borrowerID = %d, loanStartDate = '%s', loanEndDate = '%s' WHERE catalogueID = %d AND borrowerID IS NULL",
                borrowerID, loan.getLoanStartDate(), loan.getLoanEndDate(), loan.getCatalogueID());

        try (Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement()) {
        	
        	stmt.execute(sql);
        	System.out.println("대출되었습니다.");
            

        } catch (SQLException e) {
            System.out.println("대출실패되었습니다. " + e.getMessage());
        }
    }

    public static void returnOneBook(int catalogueID) {
        String sql = String.format("UPDATE OR IGNORE loan SET borrowerID = null, loanStartDate = null, loanEndDate = null WHERE catalogueID = %d", catalogueID);

        try (Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement()) {

            stmt.execute(sql);
            
            System.out.println("반납되었습니다.");

        } catch (SQLException e) {
            System.out.println("반납실패되었습니다." + e.getMessage());
        }
    }
    
    public static boolean isBookExist(int catalogueID) {
    	String sql = "SELECT count(catalogueID) FROM book WHERE catalogueID = " + catalogueID + ";";
    	
    	int i = 0;
        try (Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            
            if (rs.next()) {
                i = rs.getInt(1);
            }
            
            System.out.println(i);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        
        return i > 0 ? true : false;
    }
    
    public static boolean isBorrowerExist(int borrowerID) {
    	String sql = "SELECT count(borrowerID) FROM borrower WHERE borrowerID = " + borrowerID + ";";
    	
    	int i = 0;
        try (Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            
            if (rs.next()) {
                i = rs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        
        return i > 0 ? true : false;
    }

    public static boolean isBorrowerExist(String name) {
    	String sql = String.format("SELECT count(borrowerID) FROM borrower WHERE name = '%s'", name);

    	int i = 0;
        try (Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                i = rs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return i > 0 ? true : false;
    }


}
