import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ContactsManager {
    private static Scanner scanner = new Scanner(System.in);
    private static String contacts = "contacts.txt";
    private static Path dataFile = Paths.get("./", contacts);
    private static List<String> contactsList;
    private static ContactsManager search = new ContactsManager();
    private static boolean repeat = true;

//    Loads the contacts.txt into an ArrayList
    public static void loadContacts() throws IOException {
        contactsList = Files.readAllLines(dataFile);
    }

//    Shows the contacts
    public static void showContacts() {
        System.out.println(
                "Name | Phone number\n"+
                        "---------------");
        for(String contact : contactsList) {
            System.out.println(contact);
        }
    }

//    Adds a contact. Won't add if exact name and number already exists
    public static void addContact() throws IOException {
        System.out.println("Enter contacts name and number.\n");
        String newContact = scanner.nextLine();
        boolean isExist = false;
        for (String s : contactsList) {
            if (newContact.equals(s)) {
                isExist = true;
                break;
            }
        }
        if (isExist) {
            System.out.println("This contact already exists.\n");
        } else {
            Files.write(dataFile, Arrays.asList(newContact), StandardOpenOption.APPEND);
        }
    }

//    Enter contact you want to search for
    public static void userSearch() throws FileNotFoundException {
        System.out.println("Enter the contact(s) name you are searching for:");
        String userSearch = scanner.nextLine();
        search.searchFile(dataFile, userSearch);
    }

    //    This is handling the contact search feature
    public static void searchFile(Path fileName, String searchStr) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(String.valueOf(fileName)));
        while(scan.hasNext()){
            String line = scan.nextLine();
            if(line.contains(searchStr)){
                System.out.println(line);
                break;
            }
        }
    }

//    Deletes a contact
    public static void deleteContact() throws IOException {
        System.out.println("Enter contact name to delete:");
        String userDelete = scanner.nextLine();
        for (String contact : contactsList) {
            if (contact.contains(userDelete)) {
                contactsList.remove(contact);
                Files.write(dataFile, contactsList, Charset.defaultCharset());
                System.out.println("Deleted successfully.");
                break;
            }
        }
    }

//    Displays the menu
    public static void userMenu() throws IOException {
        System.out.println("1. View contacts.\n" +
                "2. Add a new contact.\n" +
                "3. Search a contact by name.\n" +
                "4. Delete an existing contact.\n" +
                "5. Exit.\n" +
                "Enter an option (1, 2, 3, 4 or 5):");
        int userInput = scanner.nextInt();
        scanner.nextLine();
        switch (userInput) {
            case 1:
                showContacts();
                System.out.println();
                break;
            case 2:
                addContact();
                loadContacts();
                showContacts();
                System.out.println();
                break;
            case 3:
                userSearch();
                System.out.println();
                break;
            case 4:
                deleteContact();
                System.out.println();
                loadContacts();
                showContacts();
                System.out.println();
                break;
            case 5:
                showContacts();
                System.out.println();
                System.out.println("Bye.");
                repeat=false;
                break;
        }
    }

    public static void main(String[] args) throws IOException{
        loadContacts();
        System.out.println("Welcome to your contacts. Do you want to see the menu? [y/n]");
        String response = scanner.next();
        if (response.equals("y")) {
            do {
                userMenu();
            } while (repeat);
        } else {
            System.out.println("Goodbye.");
        }
    }
}
