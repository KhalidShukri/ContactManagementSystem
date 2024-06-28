import java.util.*;

public class ContactManager {
    private Map<String,Contact> contacts;
    private Map<String, Set<String>> groups;
    private Scanner scanner;

    public ContactManager(){
        contacts = new HashMap<>();
        groups = new HashMap<>();
        scanner = new Scanner(System.in);
    }
//    A method to intialize/run our program
    public void run(){
        while (true){
            System.out.println("-----------Contact Management System-----------");
            System.out.println("1.Add a Contact");
            System.out.println("2.View all Contacts");
            System.out.println("3.Edit Contact");
            System.out.println("4.Delete a Contact");
            System.out.println("5.Search contact");
            System.out.println("6.Add Contact to a Group");
            System.out.println("7.View Contacts in a Group");
            System.out.println("8.Exit");
            System.out.print("Enter Your Choice");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice){
                case 1:
                    addContactMenu();
                    break;
                case 2:
                    viewAllContacts();
                    break;
                case 3:
                    editContactMenu();
                    break;
                case 4:
                    deleteContactMenu();
                    break;
                case 5:
                    searchContactMenu();
                    break;
                case 6:
                    addContactToGroupMenu();
                    break;
                case 7:
                    viewContactsInGroupMenu();
                    break;
                case 8:
                    System.out.println("Thank You for using the Contact Management System. GoodBye!!");
                    break;
                default:
                    System.out.println("You have entered an Invalid Choice.Please Try Again");
            }

        }
    }
//    A method to add new contact
    public void addContactMenu(){
        System.out.print("Enter Contact Name");
        String name = scanner.nextLine();
        System.out.print("Enter Phone Number");
        String phoneNumber = scanner.nextLine();
        System.out.print("Enter Email Address");
        String email = scanner.nextLine();

        Contact contact = new Contact(name,phoneNumber,email);
        contacts.put(name,contact);
        System.out.println("Contact Added Successfully");
    }
    public void viewAllContacts(){
        if (contacts.isEmpty()){
            System.out.println("No Contacts Found");
        }
        for (Contact contact : contacts.values()){
            System.out.println(contact);

        }
    }
    public void editContactMenu(){
        System.out.print("Enter The Name of the Contact to edit");
        String name = scanner.nextLine();
        Contact contact = contacts.get(name);
        if (contact == null){
            System.out.println("Contact not Found");
        }
        System.out.println("Current Details" + contact);
        System.out.print("Enter new Phone Number(Press Enter to keep the current)");
        String phoneNumber = scanner.nextLine();
        if(!phoneNumber.isEmpty()){
            contact.setPhoneNumber(phoneNumber);
        }
        System.out.print("Enter New email address(Press Enter to keep the current:)");
        String email = scanner.nextLine();
        if (!email.isEmpty()){
            contact.setEmail(email);
        }
        System.out.println("Contacts updated Successfully");

    }
    public void deleteContactMenu(){
        System.out.print("Enter The name of the Contact to delete");
        String name = scanner.nextLine();

        Contact removedContact = contacts.get(name);
        if (removedContact != null){
            System.out.println("Contact deleted successfully: " + removedContact);
//            remove contacts from all groups
           for (Set <String> groupMembers :  groups.values()){
                groupMembers.remove(name);
            }
        } else {
            System.out.println("Contact not Found");
        }
    }
//    Method to search for a contact
    public void searchContactMenu(){
        System.out.print("Enter search Term: ");
        String searchTerm = scanner.nextLine().toLowerCase();
        boolean found = false;
        for (Contact contact : contacts.values()){
            if (contact.getName().toLowerCase().contains(searchTerm) || contact.getPhoneNumber().contains(searchTerm) || contact.getEmail().contains(searchTerm)){
                System.out.println(contact);
                return;
            }
        }
        if (!found){
            System.out.println("No contacts matching the search Term.");
        }
    }
    public void addContactToGroupMenu(){
        System.out.print("Enter Contact name");
        String contactName = scanner.nextLine();
        if(!contacts.containsKey(contactName)){
            System.out.println("Contact Not found");
            return;
        }
        System.out.print("Enter group name: ");
        String groupName = scanner.nextLine();
        groups.computeIfAbsent(groupName, k-> new HashSet<>()).add(contactName);
        System.out.println("Contact added to group successfully");

    }
//    method to view contact in group
    public void viewContactsInGroupMenu(){
        System.out.print("Enter Group Name");
        String groupName = scanner.nextLine();
        Set<String> groupMembers = groups.get(groupName);
        if (groupMembers == null || groupMembers.isEmpty()){
            System.out.println("No contacts found in this group");
        }
        System.out.println("Contacts in group: " + groupName + ":");
        for (String memberName : groupMembers){
            Contact contact = contacts.get(memberName);
            if(contact != null){
                System.out.println(contact);
            }
        }
    }

}
