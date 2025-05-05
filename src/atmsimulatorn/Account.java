package atmsimulatorn;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private double balance;
    private final Currency currency;
    private final List<Transaction> history = new ArrayList<>();
    private final String name;

    public Account(String name, double initialBalance, Currency currency) {
        this.name = name;
        this.balance = initialBalance;
        this.currency = currency;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Beloppet måste vara positivt.");
        balance += amount;

        // Skapa och spara transaktion
        Transaction transaction = new Transaction(name, "Insättning", amount, currency);
        history.add(transaction);
        Database.saveTransaction(transaction);
    }

    public void withdraw(double amount) {
        if (amount > balance) throw new IllegalArgumentException("Otillräckligt saldo.");
        balance -= amount;

        // Skapa och spara transaktion
        Transaction transaction = new Transaction(name, "Uttag", amount, currency);
        history.add(transaction);
        Database.saveTransaction(transaction);
    }

    public double getBalance() {
        return balance;
    }

    public List<Transaction> getHistory() {
        return history;
    }

    public String getName() {
        return name;
    }
}
