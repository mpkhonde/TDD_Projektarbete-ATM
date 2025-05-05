package atmsimulatorn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseTest {
    public static void main(String[] args) {
        // Ladda JDBC-drivrutinen manuellt
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.out.println(" SQLite JDBC-drivrutin kunde inte laddas: " + e.getMessage());
            return;
        }

        String url = "jdbc:sqlite:identifier.sqlite"; // Anpassa om filen ligger annorlunda

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                System.out.println("✅ Anslutning till SQLite lyckades!");
            }
        } catch (SQLException e) {
            System.out.println(" Fel vid anslutning: " + e.getMessage());
        }
    }
}
