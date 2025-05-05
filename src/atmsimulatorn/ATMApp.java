package atmsimulatorn;

import java.util.Scanner;

public class ATMApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Be om användarnamn
        System.out.print("Skriv ditt namn: ");
        String userName = scanner.nextLine().trim();

        // Skapa konto med 0 kr start och SEK som valuta
        Currency currency = Currency.SEK;
        Account account = new Account(userName, 0.0, currency);

        // Starta databasanslutning
        Database.connect();

        ATM atm = new ATM(account);
        atm.start();

        // Stäng anslutning när användaren avslutar
        Database.disconnect();
    }
}
