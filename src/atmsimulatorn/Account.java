package atmsimulatorn;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private double balance;
    private final Currency currency;
    private final List<Transaction> history = new ArrayList<>();

    public Account(double initialBalance, Currency currency) {
        this.balance = initialBalance;
        this.currency = currency;
    }

    public double getBalance() {
        return balance;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Beloppet måste vara positivt.");
        balance += amount;
        history.add(new Transaction("Insättning", amount, currency));
    }

    public void withdraw(double amount) {
        if (amount > balance) throw new IllegalArgumentException("Otillräckligt saldo.");
        balance -= amount;
        history.add(new Transaction("Uttag", amount, currency));
    }

    public List<Transaction> getHistory() {
        return history;
    }
}
