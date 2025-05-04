package atmsimulatorn;

import java.util.Scanner;

public class ATM {
    private final Account account;
    private final Scanner scanner = new Scanner(System.in);

    public ATM(Account account) {
        this.account = account;
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
            System.out.println("────────────────────────");
            System.out.print("Välj ett alternativ (1–5): ");

            String choice = scanner.nextLine().trim();

            if (!choice.matches("[1-5]")) {
                System.out.println(" Ogiltigt val. Försök igen.");
                pause();
                continue;
            }

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
                    System.out.println("\n Transaktionshistorik:");
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
