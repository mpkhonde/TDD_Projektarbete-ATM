package atmsimulatorn;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private final String name;
    private final String type;
    private final double amount;
    private final LocalDateTime timestamp;
    private final Currency currency;

    public Transaction(String name, String type, double amount, Currency currency) {
        this.name = name;
        this.type = type; // ✅ rätt tilldelning
        this.amount = amount;
        this.currency = currency;
        this.timestamp = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getSummary() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format("%s - %s %s: %.2f %s",
                formatter.format(timestamp),
                name,
                type,
                amount,
                currency.getSymbol());
    }
}
