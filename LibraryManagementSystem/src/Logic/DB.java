package Logic;

import java.sql.*;

public class DB {

    public static String fileName = "library.db";
    public static String url = "jdbc:sqlite:./db/" + fileName;

//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Select your *.db file");
//        String s = scanner.next();
//        fileName = s;
//    }

//	public static void connect(String fileName) {
//		Connection conn = null;
//		try {
//			String url = "jdbc:sqlite:./db/" + fileName;
//			conn = DriverManager.getConnection(url);
//
//			System.out.println("Connected");
//
//		} catch (SQLException e) {
//			System.out.println(e.getMessage());
//		} finally {
//			try {
//                if (conn != null) {
//                    conn.close();
//                }
//            } catch (SQLException e) {
//                System.out.println(e.getMessage());
//            }
//		}
//	}
	
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
                "name VARCHAR(45) NOT NULL, UNIQUE(name))";

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
        String sql = String.format("INSERT OR IGNORE INTO borrower (name) VALUES ('%s')", borrower.getName());

        try (Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement()) {

            if (stmt.execute(sql)) {
                System.out.printf("registered one borrower: %s\n", borrower);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void registerOneBook(Book book) {
        String sql = String.format("INSERT OR IGNORE INTO book (title, author) VALUES ('%s', '%s')", book.getTitle(), book.getAuthor());
        try (Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement()) {

            if (stmt.execute(sql)) {
                System.out.printf("added one book: %s\n", book);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        sql = "SELECT catalogueID FROM book ORDER BY catalogueID DESC LIMIT 1";
        try (Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);

            sql = "INSERT OR IGNORE INTO loan (catalogueID) VALUES (" + rs.getInt(1) + ")";
            stmt.execute(sql);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
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
	    String sql = "SELECT l.catalogueID, b.title, b.author, l.loanStartDate, l.loanEndDate FROM loan AS l " +
                "LEFT JOIN book AS b ON l.catalogueID == b.catalogueID " +
                "WHERE l.borrowerID IS NOT NULL";

        try (Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);

            StringBuffer sb = new StringBuffer();

            System.out.println("books on loan: ");
            while (rs.next()) {
                sb.append(String.format("%d, %s, %s, %s, %s\n", rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }

            System.out.println(sb.toString());
            return sb.toString();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void lendOneBook(Loan loan) {
        String sql = String.format("UPDATE OR IGNORE loan SET borrowerID = %d, loanStartDate = '%s', loanEndDate = '%s' WHERE catalogueID = %d",
                loan.getBorrowerID(), loan.getLoanStartDate(), loan.getLoanEndDate(), loan.getCatalogueID());

        try (Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement()) {

            if (stmt.execute(sql)) {
                System.out.printf("lended one book: %s\n", loan);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void returnOneBook(int catalogueID) {
        String sql = String.format("UPDATE OR IGNORE loan SET borrowerID = null, loanStartDate = null, loanEndDate = null WHERE catalogueID = %d", catalogueID);

        try (Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement()) {

            if (stmt.execute(sql)) {
                System.out.printf("returned one book: %s\n", catalogueID);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

//    private static boolean execute(String s) {
//	    try (Connection conn = DriverManager.getConnection(url);
//            Statement stmt = conn.createStatement()) {
//            stmt.execute(s);
//            return true;
//
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//            return false;
//        }
//    }

}
