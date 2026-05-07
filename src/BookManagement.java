import java.sql.*;

public class BookManagement {

    // Supabase PostgreSQL Connection Details
    static final String DB_URL =
        "jdbc:postgresql://aws-1-ap-south-1.pooler.supabase.com:5432/postgres?sslmode=require&connectTimeout=10";

    static final String USER = "postgres.yrzvgbmokcawwsvmzibi";
    static final String PASSWORD = "tq50ESC5s3pLh8bs";

    public static void main(String[] args) {

        try {

            // Load PostgreSQL Driver
Class.forName("org.postgresql.Driver");

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

            int insertResult = insertStmt.executeUpdate();

System.out.println(insertResult + " Book Inserted");


            // ==============================
            // FETCH BOOKS
            // ==============================

            String fetchQuery = "SELECT * FROM books";

            Statement stmt = con.createStatement();

ResultSet rs = stmt.executeQuery(fetchQuery);

System.out.println("\nBook Records:");

            while (rs.next()) {

System.out.println(
rs.getInt("id") + " | " +
rs.getString("title") + " | " +
rs.getString("author") + " | " +
rs.getDouble("price")
                );
            }


            // ==============================
            // UPDATE BOOK
            // ==============================

            String updateQuery =
                    "UPDATE books SET price=? WHERE id=?";

PreparedStatement updateStmt =
con.prepareStatement(updateQuery);

updateStmt.setDouble(1, 799.99);
updateStmt.setInt(2, 1);

            int updateResult = updateStmt.executeUpdate();

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
