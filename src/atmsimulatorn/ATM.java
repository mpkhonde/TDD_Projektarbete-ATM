package atmsimulatorn;

import java.util.Scanner;

public class ATM {
    private Account account;
    private final Scanner scanner = new Scanner(System.in);

    public ATM(Account account) {
        this.account = account; // FIX: korrekt tilldelning
    }

    public void start() {
        boolean running = true;

        while (running) {
            System.out.println("\n\n────────────────────────");
            System.out.println(" Välkommen till ATM");
            System.out.println("────────────────────────");
            System.out.println(" Huvudmeny:");
            System.out.println("1  Visa saldo");
            System.out.println("2  Sätt in");
            System.out.println("3  Ta ut");
            System.out.println("4  Visa kvitto");
            System.out.println("5  Avsluta");
            System.out.println("6  Visa historik från databas");
            System.out.println("7  Rensa databasen (ta bort alla transaktioner)");
            System.out.println("────────────────────────");
            System.out.print("Välj ett alternativ (1–7): ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> {
                    System.out.printf("\n Ditt saldo är: %.2f %s%n",
                            account.getBalance(),
                            account.getCurrency().getSymbol());
                    pause();
                }
                case "2" -> {
                    System.out.print("\n Belopp att sätta in: ");
                    double amount = Double.parseDouble(scanner.nextLine());
                    account.deposit(amount);
                    System.out.println(" Insättning genomförd!");
                    showLastTransaction();
                    pause();
                }
                case "3" -> {
                    System.out.print("\n Belopp att ta ut: ");
                    double amount = Double.parseDouble(scanner.nextLine());
                    try {
                        account.withdraw(amount);
                        System.out.println(" Uttag genomfört!");
                        showLastTransaction();
                    } catch (IllegalArgumentException e) {
                        System.out.println(" Fel: " + e.getMessage());
                    }
                    pause();
                }
                case "4" -> {
                    System.out.println("\n Transaktionshistorik (session):");
                    System.out.println("────────────────────────");
                    if (account.getHistory().isEmpty()) {
                        System.out.println("Inga transaktioner hittades.");
                    } else {
                        for (Transaction t : account.getHistory()) {
                            System.out.println(t.getSummary());
                        }
                    }
                    System.out.println("────────────────────────");
                    pause();
                }
                case "5" -> {
                    System.out.println("\n Tack för att du använde ATM-simulatorn!");
                    running = false;
                }
                case "6" -> {
                    System.out.println("\n Transaktioner från databas:");
                    Database.connect(); // Anslut till databas
                    Database.printAllTransactions();
                    Database.disconnect();
                    pause();
                }
                case "7" -> {
                    System.out.print(" Är du säker? (j/n): ");
                    String confirm = scanner.nextLine().trim().toLowerCase();
                    if (confirm.equals("j")) {
                        Database.connect();
                        Database.clearTransactions();
                        Database.disconnect();
                    } else {
                        System.out.println(" Avbrutet.");
                    }
                    pause();
                }
                default -> {
                    System.out.println(" Ogiltigt val. Försök igen.");
                    pause();
                }
            }
        }
    }

    private void showLastTransaction() {
        if (!account.getHistory().isEmpty()) {
            Transaction last = account.getHistory().get(account.getHistory().size() - 1);
            System.out.println("\n Kvitto:");
            System.out.println("────────────────────────");
            System.out.println(last.getSummary());
            System.out.println("────────────────────────");
        }
    }

    private void pause() {
        System.out.print("\n⏎ Tryck [Enter] för att gå tillbaka...");
        scanner.nextLine();
    }
}
