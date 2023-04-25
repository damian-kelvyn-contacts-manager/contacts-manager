package ContactsManagerCLI;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Contacts {
    private final ArrayList<Contact> contacts;
    private static final String FILEPATH = "src/main/java/ContactList/contacts.txt";

    public Contacts() {
        this.contacts = new ArrayList<>();
    }


    public static void showContacts() {
        ArrayList<Contact> contacts = readContacts();
        if (contacts.isEmpty()) {
            System.out.println("No contacts found");
        } else {
            for (Contact contact : contacts) {
                System.out.println(contact.getName() + " " + contact.getNumber());
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

    public static void addContact(Scanner scanner) {
        System.out.print("Enter name: ");
        scanner.nextLine();
        String name = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine();
        Contact contact = new Contact(name, phone);
        writeContact(contact);
        System.out.println("Contact added");
    }


    public static void writeContact(Contact contact) {
        try (FileWriter fw = new FileWriter(FILEPATH, true)) {
            fw.write(contact.getName() + "," + contact.getNumber() + "\n");
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    public static void deleteContact(Scanner scanner) {
        System.out.print("Enter name to delete: ");
        scanner.nextLine();
        String name = scanner.nextLine();
        ArrayList<Contact> contacts = readContacts();
        boolean found = false;
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(name)) {
                contacts.remove(contact);
                writeContacts(contacts);
                System.out.println("Contact deleted");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Contact not found");
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

    public static void searchContact(Scanner scanner) {
        System.out.print("Enter name to search for: ");
        String name = scanner.nextLine();
        ArrayList<Contact> contacts = readContacts();
        boolean found = false;
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(name)) {
                System.out.println(contact.getName() + " " + contact.getNumber());
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Contact not found");
        }
    }

}
