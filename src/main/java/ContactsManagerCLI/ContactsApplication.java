package ContactsManagerCLI;

import java.util.Scanner;

public class ContactsApplication extends Contacts {

    public final static void printMenu(){
        System.out.println("1. View contacts.");
        System.out.println("2. Add a new contact.");
        System.out.println("3. Search a contact by name.");
        System.out.println("4. Delete an existing contact.");
        System.out.println("5. Exit.");
        System.out.println("Enter an option (1, 2, 3, 4 or 5):");
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Contacts contacts = new Contacts();

        System.out.println(contacts);
        boolean running = true;
        while(running){
            printMenu();
            int userInput = scan.nextInt();
            switch(userInput){
                case 1:
                    contacts.showContacts();
                    break;
                case 2:
                    System.out.println("Enter contact name: ");
                    scan.nextLine();
                    String name = scan.nextLine();
                    System.out.println("Enter contact number: ");
                    String number = scan.nextLine();

                    Contact nContact = new Contact(name, number);
                    contacts.addNew(nContact);
                    break;
                case 3:
                    System.out.println("Enter contact name: ");
                    String sn = scan.nextLine();
                    String searchName = scan.nextLine();;
                    System.out.println(contacts.searchByName(searchName));
                    break;
                case 4:
                    deleteContact(scan);
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid input. Please try again.");
            }
        }


//        FileWriter fw = null;
//        try {
//            fw = new FileWriter("src/main/java/ContactList/contacts.txt");
//            BufferedWriter bw = new BufferedWriter(fw);
//            bw.write(contacts.showAll().toString());
//            contacts.addNew(newContact);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }



//        String directory = "src/main/java/ContactList";
//        String FILEPATH = "contacts.txt";
//
//        Path dataDirectory = Paths.get(directory);
//        Path dataFile = Paths.get(directory, FILEPATH);
//
//        if(Files.notExists(dataDirectory)) {
//            try {
//                Files.createDirectories(dataDirectory);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        if (!Files.exists(dataFile)) {
//            try {
//                Files.createFile(dataFile);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }




//        List<String> contactList = Arrays.asList(contacts.showAll());
//        for (int i = 0; i < contactList.size(); i++) {
//            System.out.println(contactList.get(i).toString());
//        }
//    }
}
}

