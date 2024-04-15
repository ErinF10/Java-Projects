import java.util.*;
import java.io.*;

public class PhoneBookApp {
    public static void main(String[] args) throws IOException {
        final int MAX_ENTRIES = 10;
        String[] firstNames = new String[MAX_ENTRIES];
        String[] lastNames = new String[MAX_ENTRIES];
        String[] phoneNumbers = new String[MAX_ENTRIES];
        int entriesCount = 0;
        Scanner sc = new Scanner(System.in);
        System.out.print("Name of input file: ");
        File phoneBook = new File(sc.nextLine());
        System.out.println("");
        Scanner fs = new Scanner(phoneBook);
       
        //Read in the entries from file.
        while (fs.hasNext()) {
                firstNames[entriesCount] = fs.next();
                lastNames[entriesCount] = fs.next();
                phoneNumbers[entriesCount] = fs.next();
                entriesCount++;
            }

        String firstName;
        String lastName;
        String phoneNumber;
        String fullName;
        System.out.println("Choices: ");
        System.out.println("l: lookup, r: reverse lookup, c: change number, a: add entry, q: quit");
        char choice = sc.next().charAt(0);
        while (choice != 'q') {
            
            switch (choice) {
                case 'l':
                    lookup(firstNames, lastNames, phoneNumbers, entriesCount, sc);
                    //System.out.println(phoneNumber + "\n");
                    break;
                case 'r':
                    System.out.print("Phone number: ");
                    phoneNumber = sc.next();
                    fullName = reverseLookup(phoneNumber, firstNames, lastNames, phoneNumbers, entriesCount, sc);
                    if (fullName.equals("Phone number not found")) {
                        System.out.println(fullName + "\n");
                    }
                    break;
                case 'c':
                    changeNumber(firstNames, lastNames, phoneNumbers, entriesCount, sc, MAX_ENTRIES);
                    //System.out.println(phoneNumbers[1]);
                    break;
                case 'a':
                    boolean entryAlreadyExists = addNumber(firstNames, lastNames, phoneNumbers, entriesCount, sc, MAX_ENTRIES);
                    if (!entryAlreadyExists) {
                        entriesCount++;
                    }
                    break;
                case 'q':
                    break;
                default:
                    System.out.println("Invalid choice\n");
                
            }
            System.out.println("Choices: ");
            System.out.println("l: lookup, r: reverse lookup, c: change number, a: add entry, q: quit");
            choice = sc.next().charAt(0);
        }
        System.out.print("Name of outputfile: ");
        PrintWriter pw = new PrintWriter(sc.next());
        for (int i = 0; i < entriesCount; i++) {
            pw.println(firstNames[i] + " " + lastNames[i] + " " + phoneNumbers[i]);
        }
        fs.close();
        sc.close();
        pw.close();
    }

    public static void lookup(String[] firstNames, String[] lastNames, String[] phoneNumbers, int entriesCount, Scanner sc) 
    {
        String phoneNumber = "Name not found";
        System.out.print("First Name: ");
        String firstName = sc.next();
        System.out.print("Last Name: ");
        String lastName = sc.next();
        for (int i = 0; i < entriesCount; i++) {
            if (firstName.equals(firstNames[i]) && lastName.equals(lastNames[i])) {
                phoneNumber = phoneNumbers[i];
                System.out.println(firstName + " " + lastName + "'s phone number is " + phoneNumber + "\n");
            } 
        }
        if (phoneNumber.equals("Name not found")) {
            System.out.println(phoneNumber + "\n");
        }

    }

    public static String reverseLookup(String phoneNumber, String[] firstNames, String[] lastNames, String[] phoneNumbers, int entriesCount, Scanner sc) 
    {
        String firstName;
        String lastName;
        String fullName = "Phone number not found";
        for (int i = 0; i < entriesCount; i++) {
            if (phoneNumber.equals(phoneNumbers[i])) {
                firstName = firstNames[i];
                lastName = lastNames[i];
                fullName = firstName + " " + lastName;
                System.out.println(phoneNumber + " is " + fullName + "'s phone number\n");
            } 
        }
        return fullName;
    }

    public static void changeNumber(String[] firstNames, String[] lastNames, String[] phoneNumbers, int entriesCount, Scanner sc, int MAX_ENTRIES) 
    {
        boolean entryExists = false;
        int entryIndex = -1;
        System.out.print("First name: ");
        String firstName = sc.next();
        System.out.print("Last name: ");
        String lastName = sc.next();
        for (int i = 0; i < entriesCount; i++) {
            if (firstName.equals(firstNames[i]) && lastName.equals(lastNames[i])) {
                entryExists = true;
                entryIndex = i;
            }
        }
        if (entryExists == false) {
            System.out.println("Name not found\n");
        }
        if (entryExists) {
            System.out.print("Phone number: ");
            String phoneNumber = sc.next();
            phoneNumbers[entryIndex] = phoneNumber;
            System.out.println("Phone number updated\n");
            }
        }

    public static boolean addNumber(String[] firstNames, String[] lastNames, String[] phoneNumbers, int entriesCount, Scanner sc, int MAX_ENTRIES) 
    {
        boolean entryAlreadyExists = false;
        if (entriesCount == 10) {
            System.out.println("Database is full\n");
            entryAlreadyExists = true;
        } else {
            System.out.print("First name: ");
            String firstName = sc.next();
            System.out.print("Last name: ");
            String lastName = sc.next();
            for (int i = 0; i < entriesCount; i++) {
                if (firstName.equals(firstNames[i]) && lastName.equals(lastNames[i])) {
                    System.out.println("That name already exists\n");
                    entryAlreadyExists = true;
                }
            }
            if (entryAlreadyExists == false) {
                System.out.print("Phone number: ");
                String phoneNumber = sc.next();
                firstNames[entriesCount] = firstName;
                lastNames[entriesCount] = lastName;
                phoneNumbers[entriesCount] = phoneNumber;
                System.out.println("Entry added\n");
            }
        }
        return entryAlreadyExists;
    }
}
