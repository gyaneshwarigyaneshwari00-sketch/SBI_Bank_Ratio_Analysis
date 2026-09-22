import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/users_login";
    private static final String USER = "root";
    private static final String PASSWORD = "newsystem123";

    public static Connection connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            return con;

        } catch (Exception e) {
            System.out.println("Database Connection Failed!");
            e.printStackTrace();
            return null;
        }
    }
}