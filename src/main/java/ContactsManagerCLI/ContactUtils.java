package ContactsManagerCLI;

import java.io.*;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ContactUtils {
    protected final ArrayList<Contact> contacts;

    public ContactUtils() {
        this.contacts = new ArrayList<>();
    }

    static String FILEPATH = "src/main/java/ContactList/contacts.txt";

    public static void printMenu() {
        System.out.println("1. View contacts.");
        System.out.println("2. Add a new contact.");
        System.out.println("3. Search a contact by name.");
        System.out.println("4. Delete an existing contact.");
        System.out.println("5. Exit.");
        System.out.println("Enter an option (1, 2, 3, 4 or 5):");
    }

    public static void showContacts() {
        ArrayList<Contact> contacts = readContacts();
        if (contacts.isEmpty()) {
            System.out.println("No contacts found");
        } else {
            System.out.println("Name | Phone number\n-------------------");
            for (Contact contact : contacts) {
                System.out.println(contact.getName() + " | " + contact.getNumber() + "\n");
            }
        }
    }

    public static ArrayList<Contact> readContacts() {
        ArrayList<Contact> contacts = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILEPATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0];
                String phone = parts[1];
                Contact contact = new Contact(name, phone);
                contacts.add(contact);
            }
        } catch (IOException e) {
            System.out.println("Error reading contacts file: " + e.getMessage());
        }
        return contacts;
    }

    public static void addNew(Scanner scan) {
        System.out.println("Enter contact name: ");
        scan.nextLine();
        String name = scan.nextLine();
        System.out.println("Enter contact number: ");
        String number = scan.nextLine();
        Contact nContact = new Contact(name, number);
        writeContact(nContact);
        System.out.println("Contact added");
    }

    public static void writeContact(Contact contact) {
        try (FileWriter fw = new FileWriter(FILEPATH, true)) {
            fw.write(contact.getName() + "," + contact.getNumber() + "\n");
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    public static void searchContact(Scanner scanner) {
        System.out.print("Enter name to search for: ");
        scanner.nextLine();
        String name = scanner.nextLine();
        ArrayList<Contact> contacts = readContacts();
        boolean found = false;
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(name)) {
                System.out.println(contact.getName() + " | " + contact.getNumber());
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Contact not found");
        }
    }

    public static void deleteContact(Scanner scan) {
        System.out.print("Enter first name to delete: ");
        scan.nextLine();
        String name = scan.nextLine();
        ArrayList<Contact> contacts = readContacts();
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(name)) {
                contacts.remove(contact);
                writeContacts(contacts);
                System.out.println("Contact deleted");
                break;
            }
        }
    }

    public static void writeContacts(ArrayList<Contact> contacts) {
        try (FileWriter fw = new FileWriter(FILEPATH)) {
            for (Contact contact : contacts) {
                fw.write(contact.getName() + "," + contact.getNumber() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing contacts file: " + e.getMessage());
        }
    }
}

