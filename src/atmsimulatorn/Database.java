package atmsimulatorn;

import java.sql.*;

public class Database {
    private static final String URL = "jdbc:sqlite:identifier.sqlite";
    private static Connection connection;

    // Startar databasanslutningen
    public static void connect() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL);
                System.out.println("✅ Databasen är ansluten.");
            }
        } catch (SQLException e) {
            System.out.println(" Kunde inte ansluta till databasen: " + e.getMessage());
        }
    }

    // Stänger anslutningen
    public static void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println(" Databasen är frånkopplad.");
            }
        } catch (SQLException e) {
            System.out.println(" Fel vid frånkoppling: " + e.getMessage());
        }
    }

    // Spara transaktion
    public static void saveTransaction(Transaction transaction) {
        String sql = "INSERT INTO transactions(name, type, amount, currency, timestamp) VALUES(?,?,?,?,?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, transaction.getName());
            pstmt.setString(2, transaction.getType());
            pstmt.setDouble(3, transaction.getAmount());
            pstmt.setString(4, transaction.getCurrency().toString());
            pstmt.setString(5, transaction.getTimestamp().toString());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(" Fel vid sparande i databasen: " + e.getMessage());
        }
    }

    // Visa alla transaktioner
    public static void printAllTransactions() {
        String sql = "SELECT name, type, amount, currency, timestamp FROM transactions";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (!rs.isBeforeFirst()) {
                System.out.println("🔍 Inga transaktioner hittades.");
                return;
            }

            System.out.println("──────────────────────────────");
            while (rs.next()) {
                String name = rs.getString("name");
                String type = rs.getString("type");
                double amount = rs.getDouble("amount");
                String currency = rs.getString("currency");
                String time = rs.getString("timestamp");

                System.out.printf(" %s - %s %s: %.2f %s%n", time, name, type, amount, currency);
            }
            System.out.println("──────────────────────────────");

        } catch (SQLException e) {
            System.out.println(" Fel vid läsning från databasen: " + e.getMessage());
        }
    }

    // Rensa databasen
    public static void clearTransactions() {
        String sql = "DELETE FROM transactions";

        try (Statement stmt = connection.createStatement()) {
            int rows = stmt.executeUpdate(sql);
            System.out.println(" " + rows + " transaktioner raderades.");
        } catch (SQLException e) {
            System.out.println(" Fel vid rensning: " + e.getMessage());
        }
    }
}
