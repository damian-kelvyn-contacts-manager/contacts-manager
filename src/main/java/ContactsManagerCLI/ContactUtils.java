package ContactsManagerCLI;

import java.io.*;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ContactUtils {
    // This protected ArrayList will be used by the ContactsApplication class
    protected ArrayList<Contact> contacts;

    // This is the constructor for the ContactUtils class
    public ContactUtils() {
        this.contacts = new ArrayList<>();
    }

    // This method will read the contacts.txt file and return an ArrayList of Contact objects
    static String FILEPATH = "src/main/java/ContactList/contacts.txt";

    public static void printMenu() {
        System.out.println("1. View contacts.");
        System.out.println("2. Add a new contact.");
        System.out.println("3. Search a contact by name.");
        System.out.println("4. Delete an existing contact.");
        System.out.println("5. Exit.");
        System.out.println("Enter an option (1, 2, 3, 4 or 5):");
    }

    // This method will print out the contacts in the contacts.txt file
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

    // This method will read the contacts.txt file and return an ArrayList of Contact objects
    public static ArrayList<Contact> readContacts() {
        ArrayList<Contact> contacts = new ArrayList<>();
        // This try/catch block will read the contacts.txt file and add each line to the ArrayList
        try (BufferedReader br = new BufferedReader(new FileReader(FILEPATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                if(line.equals("\n"))
                    break;
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

    // This method will search the contacts.txt file for a contact by name
    public static void addNew(Scanner scan) {
            System.out.println("Enter contact name: ");
            String name = scan.nextLine();
            ArrayList<Contact> contacts = readContacts();
            for (Contact contact : contacts) {
                if (contact.getName().equalsIgnoreCase(name)) {
                    overwriteContact(contact.getName());
                    return;
                }
            }
            System.out.println("Enter contact number: ");
            String number = scan.nextLine();
            Contact nContact = new Contact(name, number);
            contacts.add(nContact);
            writeContacts(contacts);
            System.out.println("Contact added");
        }

        // This method will overwrite a contact if the user chooses to do so in the addNew method above (if the contact already exists)
    public static void overwriteContact(String name){
        System.out.printf("There's already a contact named %s. Do you want to overwrite it? (Yes/No)\n",name);
        Scanner scan = new Scanner(System.in);
        String response = scan.nextLine();
        ArrayList<Contact> contacts = readContacts();
        if(response.equalsIgnoreCase("Yes")){
            for(Contact contact : contacts){
                if(contact.getName().equalsIgnoreCase(name)){
                    System.out.println("Enter new number: ");
                    String number = scan.nextLine();
                    contact.setNumber(number);
                    writeContacts(contacts);
                    System.out.println("Contact overwritten");
                    return;
                }
            }
        }
        System.out.println("Contact not overwritten");
    }

    // This method will write the contacts ArrayList to the contacts.txt file (overwriting the file) after a contact is added or deleted from the ArrayList
    public static void writeContacts(ArrayList<Contact> contacts) {
        try (FileWriter fw = new FileWriter(FILEPATH)) {
            for (Contact contact : contacts) {
                fw.write(contact.getName() + "," + (contact.getNumber().length() > 7 && contact.getNumber().length() >= 12 ? contact.getNumber() : formattedNumber(contact)) + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing contacts file: " + e.getMessage());
        }
    }

    // This method will format the phone number to the format xxx-xxx-xxxx or xxx-xxxx
    public static String formattedNumber(Contact contact) {
        String formatNumber = "";
        boolean running = true;
        while(running) {
            if (contact.getNumber().length() == 10) {
                formatNumber = contact.getNumber().substring(0, 3) + "-" + contact.getNumber().substring(3, 6) + "-" + contact.getNumber().substring(6);
                running = false;
            } else if (contact.getNumber().length() == 7) {
                formatNumber = contact.getNumber().substring(0, 3) + "-" + contact.getNumber().substring(3);
                running = false;
            } else {
                System.out.println("Invalid number");
                System.out.println("Enter contact number: ");
                Scanner scan = new Scanner(System.in);
                String number = scan.nextLine();
                contact.setNumber(number);
            }
        }
        return formatNumber;
    }

    // This method will search for a contact by name
    public static void searchContact(Scanner scan) {
        System.out.print("Enter name to search for: ");
        String name = scan.nextLine();
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

    // This method will delete a contact by name
    public static void deleteContact(Scanner scan) {
        System.out.print("Enter full name to delete: ");
        String name = scan.nextLine();
        ArrayList<Contact> contacts = readContacts();
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(name)) {
                contacts.remove(contact);
                writeContacts(contacts);
                System.out.println("Contact deleted");
                return;
            }
        }

    }

    // This method will exit the program and save the contacts to the contacts.txt file
        public static void exitProgram() {
            ArrayList<Contact> contacts = readContacts();
            System.out.println("Saving contacts...");
            writeContacts(contacts);
            System.out.println("Contacts saved successfully!");
            System.out.println("Goodbye!");
        }
}

