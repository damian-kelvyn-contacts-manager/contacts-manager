package ContactsManagerCLI;

import java.io.*;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


public class Contacts {
    protected final ArrayList<Contact> contacts;
    public Contacts(){
        this.contacts = new ArrayList<>();
    }


    static String directory = "src/main/java/ContactList";
    static String FILEPATH = "src/main/java/ContactList/contacts.txt";

    Path dataDirectory = Paths.get(directory);
    Path dataFile = Paths.get(directory, FILEPATH);


//        if(Files.notExists("contacts.txt")) {
//        try {
//            Files.createDirectories(dataDirectory);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//        if (!Files.exists(Paths.get("src/main/java/ContactList", "contacts.txt"))) {
//        try {
//            Files.createFile(dataFile);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public  void writeContact(Contact contact) {
        try (FileWriter fw = new FileWriter(FILEPATH, true)) {
            fw.write(contact.getName() + "," + contact.getNumber() + "\n");
        } catch (IOException e) {
            System.out.println("Error");
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


//    public ArrayList<Contact> showAll() {
//        int x = 1;
//        System.out.println("Name | Phone number\n" +
//                "---------------");
//        for (Contact contact : this.contacts) {
//            System.out.println(x + ": " + contact.getName() + " | " + contact.getNumber());
//            x++;
//        }
//        System.out.println();
//
//        return this.contacts;
//    }
public static void showContacts() {
    ArrayList<Contact> contacts = readContacts();
    if (contacts.isEmpty()) {
        System.out.println("No contacts found");
    } else {
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

    //adding
    public Contact addNew(Contact contact){
         this.contacts.add(contact);
         writeContact(contact);
        return contact;
    }

    public ArrayList<Contact> searchByName(String name) {
        ArrayList<Contact> result = new ArrayList<>();

        for (Contact contact : this.contacts) {
            if (contact.getName().equalsIgnoreCase(name)) {
                System.out.println(contact.getName() + " | " + contact.getNumber());
                break;}
        }
        return result;
        }

//    public void delete (String name){
//        for (Contact contact : this.contacts) {
//            if (contact.getName().contains(name)) {
//                this.contacts.remove(contact);
//            }
//        }
//    }


public static void deleteContact(Scanner scan){
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

    @Override
    public String toString(){
        String res = "";
        for (Contact contact : this.contacts) {
            res += contact.getName() + " | " + contact.getNumber() + "\n";
        }
        return res;
    }
}
