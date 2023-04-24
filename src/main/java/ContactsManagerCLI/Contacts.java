package ContactsManagerCLI;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class Contacts {
    private final HashMap<String, String> contacts;
    public Contacts(){
        this.contacts = new HashMap<>();
    }

    public HashMap<String, String> load(String file) {
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line = br.readLine();
            while (line != null) {
                String[] pair = line.split("\t");
                this.contacts.put(pair[0], pair[1]);
            }

            br.close();
            fr.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.contacts;
    }
    public HashMap<String, String> showAll() {
        return this.contacts;
    }
    public Contact addNew(Contact contact){
         this.contacts.put(contact.getName(), contact.getNumber());
        return contact;
    }

    public HashMap<String, String> searchByName(String name) {
        HashMap<String, String> result = new HashMap<>();
        if (this.contacts.containsKey(name)) {
            result.put(name, this.contacts.get(name));
            System.out.printf("%s -> %s", name, this.contacts.get(name));
        }
        return result;
    }

    public void delete(String name){
        this.contacts.remove(name);
    }

}
