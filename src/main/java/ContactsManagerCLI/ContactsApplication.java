package ContactsManagerCLI;

import java.util.Scanner;

public class ContactsApplication extends ContactUtils {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean running = true;
        while (running) {
            printMenu();
            int userInput = scan.nextInt();
            switch (userInput) {
                case 1 -> showContacts();
                case 2 -> addNew(scan);
                case 3 -> searchContact(scan);
                case 4 -> deleteContact(scan);
                case 5 -> {
                    System.out.println("Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid input. Please try again.");
            }
        }
    }
}

