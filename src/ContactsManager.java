import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ContactsManager {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        String contacts = "contacts.txt";
        Path dataFile = Paths.get("./", contacts);
        List<String> contactsList = new ArrayList<>();
        try {
            contactsList = Files.readAllLines(dataFile);
        } catch (IOException ex) {
            System.out.println("error");
        }

        System.out.println("1. View contacts.\n" +
                "2. Add a new contact.\n" +
                "3. Search a contact by name.\n" +
                "4. Delete an existing contact.\n" +
                "5. Exit.\n" +
                "Enter an option (1, 2, 3, 4 or 5):");
        int userInput = scanner.nextInt();

        scanner.nextLine();
        do {
            System.out.println(
                    "Name | Phone number"+
                    "---------------");
            switch (userInput) {
                case 1:
                    for (String contact : contactsList) {
                        System.out.println(contact);
                    }
                    break;
                case 2:
                    System.out.println("Enter contacts name and number.\n");
                    String newContact = scanner.nextLine();
                    try {
                        Files.write(dataFile, Arrays.asList(newContact), StandardOpenOption.APPEND);
                    } catch (IOException ex) {
                        System.out.println("error");
                    }
                    break;
//            case 3:
//                break;
//            case 4:
//                break;
                case 5:
                    System.out.println("Bye.");
                    break;
            }
        } while (true);
    }
}
