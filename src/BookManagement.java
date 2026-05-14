import java.sql.*; //it means import all the jdbc classes from java SQl package
/*jdbc includes-connection,driver manager,preparedstatement,resultset,statement.*/
public class BookManagement {

    // Supabase PostgreSQL Connection Details
    static final String DB_URL =
        "jdbc:postgresql://aws-1-ap-south-1.pooler.supabase.com:5432/postgres?sslmode=require&connectTimeout=10";

    static final String USER = "postgres.yrzvgbmokcawwsvmzibi";
    static final String PASSWORD = "Iamkush@2006";

    public static void main(String[] args) {

        try {

            // Load PostgreSQL Driver
Class.forName("org.postgresql.Driver");/*this line loads JDBC driver into the memory, without them java cant communicate with postgresql */

            // Connect Database
            Connection con = DriverManager.getConnection(
                    DB_URL,
                    USER,
                    PASSWORD
            );

System.out.println("Database Connected Successfully!");

            // ==============================
            // INSERT BOOK
            // ==============================

            String insertQuery =
                    "INSERT INTO books(title, author, price) VALUES (?, ?, ?)";

PreparedStatement insertStmt =
con.prepareStatement(insertQuery);

insertStmt.setString(1, "Java Programming");
insertStmt.setString(2, "James Gosling");
insertStmt.setDouble(3, 599.99);

            int insertResult = insertStmt.executeUpdate();//this lines to use execute the insert query and resturns the rows affected.

System.out.println(insertResult + " Book Inserted");// output = 1 book inserted.


            // ==============================
            // FETCH BOOKS
            // ==============================

            String fetchQuery = "SELECT * FROM books";

            Statement stmt = con.createStatement();//this lines helps you to create the statement object.

ResultSet rs = stmt.executeQuery(fetchQuery);// using the method of executeQuery method to store all the values that we fetched in the rs.

System.out.println("\nBook Records:");

            while (rs.next()) {

System.out.println(
rs.getInt("id") + " | " +
rs.getString("title") + " | " +
rs.getString("author") + " | " +
rs.getDouble("price")
                );
            }// these are the things we are going to use to read the rs row by row and print it.


            // ==============================
            // UPDATE BOOK
            // ==============================

            String updateQuery =
                    "UPDATE books SET price=? WHERE id=?";

PreparedStatement updateStmt =
con.prepareStatement(updateQuery);//we are using the con object to call the prepareStatement function to update query.

updateStmt.setDouble(1, 799.99);//1 place holder of price which value is 799.99
updateStmt.setInt(2, 1);// 2 place holder of id which value is 1

            int updateResult = updateStmt.executeUpdate();// it is used to execute hte update query

System.out.println(
                    "\n" + updateResult + " Book Updated"
            );


            // ==============================
            // DELETE BOOK
            // ==============================

            String deleteQuery =
                    "DELETE FROM books WHERE id=?";

PreparedStatement deleteStmt =
con.prepareStatement(deleteQuery);

deleteStmt.setInt(1, 1);

            int deleteResult = deleteStmt.executeUpdate();

System.out.println(
deleteResult + " Book Deleted"
            );


            // Close Connection
con.close();

System.out.println("\nConnection Closed");

        } catch (Exception e) {

e.printStackTrace();
        }
    }
}
