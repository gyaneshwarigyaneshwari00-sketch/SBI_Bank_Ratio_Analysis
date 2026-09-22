import java.sql.*;
import java.util.Scanner;

public class Login {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("==================================");
        System.out.println(" SBI BANK RATIO ANALYSIS SYSTEM ");
        System.out.println("==================================");

        System.out.print("Enter Username : ");
        String username = sc.nextLine();

        System.out.print("Enter Password : ");
        String password = sc.nextLine();
        Connection con = DBConnection.connect();

if (con != null) {
    try {
        String sql = "SELECT * FROM users WHERE username=? AND password=?";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, username);
        ps.setString(2, password);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            System.out.println("\nLogin Successful!");
            Dashboard.showMenu();
        } else {
            System.out.println("\nInvalid Username or Password!");
        }

    } catch (SQLException e) {
        System.out.println("Database Error: " + e.getMessage());
    }
} else {
    System.out.println("\nDatabase Connection Failed!");
}
        sc.close();
    }
}