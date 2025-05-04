package atmsimulatorn;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private final String type;
    private final double amount;
    private final LocalDateTime timestamp;
    private final Currency currency;

    public Transaction(String type, double amount, Currency currency) {
        this.type = type;
        this.amount = amount;
        this.currency = currency;
        this.timestamp = LocalDateTime.now();
    }

    public String getSummary() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format("%s - %s: %.2f %s", formatter.format(timestamp), type, amount, currency.getSymbol());
    }
}
