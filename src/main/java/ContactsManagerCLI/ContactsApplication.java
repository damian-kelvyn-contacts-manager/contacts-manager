package ContactsManagerCLI;

import java.util.Scanner;


public class ContactsApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Show all contacts");
            System.out.println("2. Add a new contact");
            System.out.println("3. Search for a contact by name");
            System.out.println("4. Delete an existing contact");
            System.out.println("5. Exit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    Contacts.showContacts();
                    break;
                case 2:
                    Contacts.addContact(scanner);
                    break;
                case 3:
                    Contacts.searchContact(scanner);
                    break;
                case 4:
                    Contacts.deleteContact(scanner);
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
