package atmsimulatorn;

import java.util.Scanner;

public class ATMApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(" Välkommen till ATM-simulatorn");
        System.out.println("Välj valuta:");
        System.out.println("1. SEK (kr)");
        System.out.println("2. USD ($)");
        System.out.println("3. EUR (€)");
        System.out.print("Val: ");
        String input = scanner.nextLine();

        Currency currency = switch (input) {
            case "2" -> Currency.USD;
            case "3" -> Currency.EUR;
            default -> Currency.SEK;
        };

        // Startsaldo och konto med vald valuta
        Account account = new Account(1000, currency);
        ATM atm = new ATM(account);
        atm.start();
    }
}
