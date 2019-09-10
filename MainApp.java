package hardwarestore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.Border;
import java.io.IOException;
import java.util.Scanner;

/**
 * This is the main class of the Hardware Store database manager. It provides a
 * console for a user to use the 5 main commands.
 *
 * @author Junye Wen
 */
public class MainApp {


    // This object will allow us to interact with the methods of the class HardwareStore
    private final HardwareStore hardwareStore;
    private static final Scanner CONSOLE_INPUT = new Scanner(System.in); // Used to read from System's standard input


    /**
     * Default constructor. Initializes a new object of type HardwareStore
     *
     * @throws IOException
     */
    public MainApp() throws IOException {
        hardwareStore = new HardwareStore();
    }

    //Function 1

    /**
     * This method shows all items in the inventory.
     */
    public void showAllItems() {
        // We need to sort the item list first
        HardwareStore.sortItemList();
        System.out.print(hardwareStore.getAllItemsFormatted());
    }

    //Function 2

    /**
     * This method will add items quantity with given number. If the item does
     * not exist, it will call another method to add it.
     */
    public void addItemQuantity() {
        String idNumber = "";
        while (true) {
            System.out.println("Please input the ID of item (String, 5 alphanumeric characters).");
            System.out.println("If the item does not exist, it will be added as a new entry.");
            idNumber = CONSOLE_INPUT.nextLine();
            if (!idNumber.matches("[A-Za-z0-9]{5}")) {
                System.out.println("Invalid ID Number: not proper format. "
                        + "ID Number must be 5 alphanumeric characters.");
                continue;
            }
            break;
        }


        int itemIndex = hardwareStore.findItemIndex(idNumber);
        if (itemIndex != -1) { // If item exists in the database
            System.out.println("Item found in database.");
            int quantity = 0;
            while (true) {
                System.out.println("Current quantity: " + hardwareStore.findItem(idNumber).getQuantity());
                System.out.println("Please enter quantity you want to add.");
                quantity = CONSOLE_INPUT.nextInt();
                CONSOLE_INPUT.nextLine();
                if (quantity <= 0) {
                    System.out.println("Invalid quantity. "
                            + "The addition amount must be larger than 0.");
                    continue;
                }
                break;
            }


            hardwareStore.addQuantity(itemIndex, quantity);
        } else {
            // If it reaches here, the item does not exist. We need to add new one.
            System.out.println("Item with given number does not exist.");

            // Enter name
            System.out.println("Please type the name of item.");
            String name = CONSOLE_INPUT.nextLine();

            // Enter quantity
            int quantity = 0;
            while (true) {
                System.out.println("Please type the quantity of the item (integer).");
                try {
                    quantity = CONSOLE_INPUT.nextInt();
                    CONSOLE_INPUT.nextLine();
                    if (quantity < 0) {
                        System.out.println("Invalid price. "
                                + "The quantity cannot be smaller than 0.");
                        continue;
                    }
                } catch (Exception e) {
                    System.out.println("Illegal input: Must input an integer.");
                    CONSOLE_INPUT.nextLine();
                    continue;
                }
                break;
            }


            // Enter price
            float price = 0;
            while (true) {
                System.out.println("Please type the price of the item (float).");
                try {
                    price = CONSOLE_INPUT.nextFloat();
                    CONSOLE_INPUT.nextLine();
                    if (price < 0) {
                        System.out.println("Invalid price. "
                                + "The price cannot be smaller than 0.");
                        continue;
                    }
                } catch (Exception e) {
                    System.out.println("Illegal input: Must input a float.");
                    CONSOLE_INPUT.nextLine();
                    continue;
                }
                break;

            }

            // Select item type
            while (true) {
                System.out.println("Please select the type of item.");
                System.out.println("1: Small Hardware Items\n2: Appliances");
                int selection = 0;
                try {
                    selection = CONSOLE_INPUT.nextInt();
                    CONSOLE_INPUT.nextLine();
                    switch (selection) {
                        case 1:
                            // Adding small hardware items
                            // Select category
                            String category = null;
                            System.out.println("Please select the category of item.");
                            System.out.println("1: Door&Window\n2: Cabinet&Furniture\n3: Fasteners\n4: Structural\n5: Other");
                            try {
                                selection = CONSOLE_INPUT.nextInt();
                                CONSOLE_INPUT.nextLine();
                                switch (selection) {
                                    case 1:
                                        category = "Door&Window";
                                        break;
                                    case 2:
                                        category = "Cabinet&Furniture";
                                        break;
                                    case 3:
                                        category = "Fasteners";
                                        break;
                                    case 4:
                                        category = "Structural";
                                        break;
                                    case 5:
                                        category = "Other";
                                        break;
                                    default:
                                        System.out.println("Invalid input.");
                                        continue;
                                }
                            } catch (Exception e) {
                                System.out.println("Illegal input: Must input an integer.");
                                CONSOLE_INPUT.nextLine();
                                continue;
                            }
                            hardwareStore.addNewSmallHardwareItem(idNumber, name, quantity, price, category);
                            return;

                        case 2:
                            // Adding appliances
                            // Input brand
                            System.out.println("Please input the brand of appliance. (String)");
                            String brand = CONSOLE_INPUT.nextLine();
                            // Select type
                            String type = null;
                            System.out.println("Please select the type of appliance.");
                            System.out.println("1: Refrigerators\n2: Washers&Dryers\n3: Ranges&Ovens\n4: Small Appliance");
                            try {
                                selection = CONSOLE_INPUT.nextInt();
                                CONSOLE_INPUT.nextLine();
                                switch (selection) {
                                    case 1:
                                        type = "Door&Window";
                                        break;
                                    case 2:
                                        type = "Washers&Dryers";
                                        break;
                                    case 3:
                                        type = "Ranges&Ovens";
                                        break;
                                    case 4:
                                        type = "Small Appliance";
                                        break;
                                    default:
                                        System.out.println("Invalid input.");
                                        continue;
                                }
                            } catch (Exception e) {
                                System.out.println("Illegal input: Must input an integer.");
                                CONSOLE_INPUT.nextLine();
                                continue;
                            }

                            hardwareStore.addNewAppliance(idNumber, name, quantity, price, brand, type);
                            return;
                        default:
                            System.out.println("Invalid input.");
                            continue;
                    }

                } catch (Exception e) {
                    System.out.println("Illegal input: Must input an integer.");
                    CONSOLE_INPUT.nextLine();
                    continue;
                }
            }

        }

    }

    //Function 3

    /**
     * This method will remove the item with given ID.
     * If the item does not exist, it will show an appropriate message.
     */
    public void removeItem() {
        System.out.println("\033[31m" + "WARNING: This function will remove the whole item entry. Please use with caution!");
        System.out.println("\033[31m" + "Will return to main menu if you make any mistake inputting information!");
        System.out.println("\033[0m" + "Please input the ID of item.");
        String idNumber = CONSOLE_INPUT.nextLine();
        if (!idNumber.matches("[A-Za-z0-9]{5}")) {
            System.out.println("Invalid ID Number: not proper format. "
                    + "ID Number must be at least 5 alphanumeric characters.");
            System.out.println("Will return to main menu.");
            return;
        }

        int itemIndex = hardwareStore.findItemIndex(idNumber);
        if (itemIndex == -1) {
            System.out.println("Item does not exist.");
            System.out.println("Will return to main menu.");
            return;
        } else {
            System.out.println("\033[31m" + "Item found. Are you sure you want to remove the whole entry?");
            System.out.println("\033[31m" + "(Data cannot be recovered!)");
            System.out.println("\033[31m" + "Please type YES (all capitalized) to confirm deletion.");
            String input = CONSOLE_INPUT.nextLine();
            if (input.equals("YES")) {
                System.out.println("\033[0m" + "User typed " + input + ". Confirm: Item will be removed.");
                hardwareStore.removeItem(itemIndex);
                System.out.println("\033[0m" + "Item removed from inventory.");
            } else {
                System.out.println("\033[0m" + "User typed " + input + ". Abort: Item will not be removed.");
            }

        }
    }

    //Function 4

    /**
     * This method can search item by a given name (part of name.
     * Case-insensitive.) Will display all items with the given name.
     */
    public void searchItemByName() {

        System.out.println("Please input the name of item.");
        String name = CONSOLE_INPUT.nextLine();

        String output = hardwareStore.getMatchingItemsByName(name);
        if (output == null) {
            System.out.println("Item not found with: " + name + ".");
        } else {
            System.out.println(output);
        }
    }

    //Function 5

    /**
     * This method shows all users in the system.
     */
    public void showAllUsers() {
        System.out.print(hardwareStore.getAllUsersFormatted());
    }
    //Function 6

    /**
     * This method will add a user (customer or employee) to the system.
     */
    public void addUser() {
        int selection = 0;

        String firstName = "";
        String lastName = "";
        // First select if you want to add customer or employee
        while (true) {
            System.out.println("Please select the type of user.");
            System.out.println("1: Employee\n2: Customer");
            try {
                selection = CONSOLE_INPUT.nextInt();
                CONSOLE_INPUT.nextLine();

                switch (selection) {
                    case 1:
                        // Add Employee
                        System.out.println("Please input the first name (String):");
                        firstName = CONSOLE_INPUT.nextLine();
                        System.out.println("Please input the last name (String):");
                        lastName = CONSOLE_INPUT.nextLine();
                        System.out.println("Please input the SSN (9-digit integer, no other characters):");
                        int socialSecurityNumber = 0;
                        try {
                            socialSecurityNumber = CONSOLE_INPUT.nextInt();
                            CONSOLE_INPUT.nextLine();
                            if (socialSecurityNumber <= 100000000 || socialSecurityNumber > 999999999) {
                                System.out.println("Invalid social security number. "
                                        + "SSN is a 9-digit integer.");
                                continue;
                            }
                        } catch (Exception e) {
                            System.out.println("Illegal input: Must input an integer.");
                            CONSOLE_INPUT.nextLine();
                            continue;
                        }

                        System.out.println("Please input the monthly salary (float):");
                        float monthlySalary = 0;
                        try {
                            monthlySalary = CONSOLE_INPUT.nextFloat();
                            CONSOLE_INPUT.nextLine();
                            if (monthlySalary < 0) {
                                System.out.println("Invalid salary."
                                        + "It must be (at least) 0.");
                                continue;
                            }
                        } catch (Exception e) {
                            System.out.println("Illegal input: Must input a float.");
                            CONSOLE_INPUT.nextLine();
                            continue;
                        }

                        hardwareStore.addEmployee(firstName, lastName, socialSecurityNumber, monthlySalary);
                        return;

                    case 2:
                        // Add Customer
                        System.out.println("Please input the first name (String):");
                        firstName = CONSOLE_INPUT.nextLine();
                        System.out.println("Please input the last name (String):");
                        lastName = CONSOLE_INPUT.nextLine();
                        System.out.println("Please input the phone number (String):");
                        String phoneNumber = CONSOLE_INPUT.nextLine();
                        System.out.println("Please input the address (String):");
                        String address = CONSOLE_INPUT.nextLine();
                        hardwareStore.addCustomer(firstName, lastName, phoneNumber, address);
                        return;
                    default:
                        System.out.println("Invalid input.");
                        continue;
                }
            } catch (Exception e) {
                System.out.println("Illegal input: Must input an integer.");
                CONSOLE_INPUT.nextLine();
                continue;
            }
        }


    }

    //Function 7

    /**
     * This method will edit a user (customer or employee).
     */
    public void editUser() {
        int idInput = 0;
        while (true) {
            System.out.println("Please input the ID of user.");
            try {
                idInput = CONSOLE_INPUT.nextInt();
                CONSOLE_INPUT.nextLine();
            } catch (Exception e) {
                System.out.println("Illegal input: Must input an integer.");
                CONSOLE_INPUT.nextLine();
                continue;
            }
            break;
        }


        User editUser = hardwareStore.findUser(idInput);
        if (editUser == null) {
            System.out.println("User not found.");
            System.out.println("Will return to main menu.");
            return;
        }
        String text = " -------------------------------------------------------------------------------------------------\n" +
                String.format("| %-10s| %-9s| %-12s| %-12s| %-45s|%n", "User Type", "User ID", "First Name", "Last Name", "Special") +
                " -------------------------------------------------------------------------------------------------\n";
        text += editUser.getFormattedText();
        text += " -------------------------------------------------------------------------------------------------\n";

        System.out.println("Current user information:");
        System.out.println(text);
        String firstName = "";
        String lastName = "";
        if (editUser.isEmployee) {
            //User is employee
            int socialSecurityNumber = 0;
            float monthlySalary = 0;
            while (true) {
                System.out.println("Please input the first name (String):");
                firstName = CONSOLE_INPUT.nextLine();
                System.out.println("Please input the last name (String):");
                lastName = CONSOLE_INPUT.nextLine();
                System.out.println("Please input the SSN (9-digit integer, no other characters):");

                try {
                    socialSecurityNumber = CONSOLE_INPUT.nextInt();
                    CONSOLE_INPUT.nextLine();
                    if (socialSecurityNumber <= 100000000 || socialSecurityNumber > 999999999) {
                        System.out.println("Invalid social security number. "
                                + "SSN is a 9-digit integer.");
                        continue;
                    }
                } catch (Exception e) {
                    System.out.println("Illegal input: Must input an integer.");
                    CONSOLE_INPUT.nextLine();
                    continue;
                }

                System.out.println("Please input the monthly salary (float):");
                try {
                    monthlySalary = CONSOLE_INPUT.nextFloat();
                    CONSOLE_INPUT.nextLine();
                    if (monthlySalary < 0) {
                        System.out.println("Invalid salary."
                                + "It must be (at least) 0.");
                        continue;
                    }
                } catch (Exception e) {
                    System.out.println("Illegal input: Must input a float.");
                    CONSOLE_INPUT.nextLine();
                    continue;
                }
                break;
            }

            hardwareStore.editEmployeeInformation(idInput, firstName, lastName, socialSecurityNumber, monthlySalary);
            return;

        } else {
            //User is customer
            System.out.println("Please input the first name (String):");
            firstName = CONSOLE_INPUT.nextLine();
            System.out.println("Please input the last name (String):");
            lastName = CONSOLE_INPUT.nextLine();
            System.out.println("Please input the phone number (String):");
            String phoneNumber = CONSOLE_INPUT.nextLine();
            System.out.println("Please input the address (String):");
            String address = CONSOLE_INPUT.nextLine();
            hardwareStore.editCustomerInformation(idInput, firstName, lastName, phoneNumber, address);
            return;
        }
    }

    //Function 8

    /**
     * This method will lead user to complete a transaction.
     */
    public void finishTransaction() {
        String itemID = "";
        int itemIndex = 0;
        int saleQuantity = 0;
        //Get the item ID. Will not break unless got a valid input.
        while (true) {
            System.out.println("Please input the item ID:");
            itemID = CONSOLE_INPUT.nextLine();
            itemIndex = hardwareStore.findItemIndex(itemID);
            if (itemIndex == -1) {
                System.out.println("Item not found. Will return to main menu.");
                return;
            } else {
                Item tempItem = hardwareStore.findItem(itemID);
                System.out.println("Please input the amount of items sold in this transaction (int)");
                System.out.println("Maximum number: " + tempItem.getQuantity());
                try {
                    saleQuantity = CONSOLE_INPUT.nextInt();
                    CONSOLE_INPUT.nextLine();
                    if (saleQuantity <= 0) {
                        System.out.println("Invalid input: must be greater than 0.");
                        continue;
                    } else if (saleQuantity > tempItem.getQuantity()) {
                        System.out.println("Invalid input: Number too big. Transaction cannot progress.");
                        continue;
                    }
                    break;

                } catch (Exception e) {
                    System.out.println("Amount of items sold input invalid: not an integer");
                    continue;
                }
            }

        }

        //Get employee ID. Will check the input: must be a user, and employee.
        int employeeID = 0;
        while (true) {
            System.out.println("Please input the id of the employee.");
            try {
                employeeID = CONSOLE_INPUT.nextInt();
                CONSOLE_INPUT.nextLine();
                if (hardwareStore.findUserIndex(employeeID) == -1) {
                    System.out.println("User not found.");
                    continue;
                } else if (!hardwareStore.findUser(employeeID).isEmployee) {
                    System.out.println("This user is not an employee.");
                }
                break;

            } catch (Exception e) {
                System.out.println("Illegal input: Must input an integer.");
                CONSOLE_INPUT.nextLine();
                continue;
            }
        }

        //Get customer ID. Will check the input: must be a user, and customer.
        int customerID = 0;
        while (true) {
            System.out.println("Please input the id of the customer.");
            try {
                customerID = CONSOLE_INPUT.nextInt();
                CONSOLE_INPUT.nextLine();
                if (hardwareStore.findUserIndex(customerID) == -1) {
                    System.out.println("User not found.");
                    continue;
                } else if (hardwareStore.findUser(customerID).isEmployee) {
                    System.out.println("This user is not a customer.");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Illegal input: Must input an integer.");
                CONSOLE_INPUT.nextLine();
                continue;
            }
        }

        hardwareStore.progressTransaction(itemID, saleQuantity, customerID, employeeID, itemIndex);
        System.out.println("Transaction complete.");

    }

    //Function 9
    public void showAllTransactions() {
        System.out.print(hardwareStore.getAllTransactionsFormatted());
    }

    //Function 10

    /**
     * These method is called to save the database before exit the system.
     *
     * @throws IOException
     */
    public void saveDatabase() throws IOException {
        hardwareStore.writeDatabase();
    }

    /**
     * This method will begin the user interface console. Main uses a loop to
     * continue executing commands until the user types '6'.
     *
     * @param args this program expects no command line arguments
     * @throws Exception
     */

    private JFrame frame;
    JButton confirmButtom = new JButton("Confirm");
    JButton confirmButtom2 = new JButton("Confirm");
    JButton confirmButtom25 = new JButton("Confirm");
    JButton confirmButtom3 = new JButton("Confirm");
    JButton confirmButtom4 = new JButton("Confirm");
    JButton confirmButtom5 = new JButton("Confirm");
    JButton confirmButtom6 = new JButton("Confirm");
    JButton confirmButtom65 = new JButton("Confirm");
    JButton confirmButtom7 = new JButton("Confirm");
    JButton confirmButtom75 = new JButton("Confirm");
    JButton confirmButtom8 = new JButton("Confirm");
    JButton confirmButtom9 = new JButton("Confirm");
    int counter = 0;                            // counter for user ID



    public static void main(String[] args) throws Exception {

        MainApp app = new MainApp();
        app.run();

        String welcomeMessage = "\nWelcome to the Hardware Store database. Choose one of the following functions:\n\n"
                + "\t 1. Show all existing items records in the database (sorted by ID number).\n"
                + "\t 2. Add new item (or quantity) to the database.\n"
                + "\t 3. Delete an item from a database.\n"
                + "\t 4. Search for an item given its name.\n"
                + "\t 5. Show a list of users in the database.\n"
                + "\t 6. Add new user to the database.\n"
                + "\t 7. Update user info (given their id).\n"
                + "\t 8. Complete a sale transaction.\n"
                + "\t 9. Show completed sale transactions.\n"
                + "\t10. Exit program.\n";


        System.out.println(welcomeMessage);
        String selection = CONSOLE_INPUT.nextLine();

        while (!selection.equals("10")) {

            switch (selection) {
                case "1":
                    // 1. Show all existing items records in the database (sorted by ID number).
                    app.showAllItems();
                    break;
                case "2":
                    // 2. Add new item (or quantity) to the database.
                    app.addItemQuantity();
                    break;
                case "3":
                    // 3. Delete an item from a database.
                    app.removeItem();
                    break;
                case "4":
                    // 4. Search for an item given its name.
                    app.searchItemByName();
                    break;
                case "5":
                    // 5. Show a list of users in the database.
                    app.showAllUsers();
                    break;
                case "6":
                    // 6. Add new user to the database.
                    app.addUser();
                    break;
                case "7":
                    // 7. Update user info (given their id).
                    app.editUser();
                    break;
                case "8":
                    // 8. Complete a sale transaction.
                    app.finishTransaction();
                    break;
                case "9":
                    // 9. Show completed sale transactions.
                    app.showAllTransactions();
                    break;
                case "h":
                    System.out.println(welcomeMessage);
                    break;
                default:
                    System.out.println("That is not a recognized command. Please enter another command or 'h' to list the commands.");
                    break;

            }

            System.out.println("Please enter another command or 'h' to list the commands.\n");
            selection = CONSOLE_INPUT.nextLine();
        }

        CONSOLE_INPUT.close();


        System.out.print("Saving database...");
        app.saveDatabase();

        System.out.println("Have a nice day!");

    }

    public void run() {


        frame = new JFrame("Hardware Store");
        JLabel guideText = new JLabel("Please select the command");
        JPanel panel = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel newPanelforSelection = new JPanel();
        JTextArea workWindow = new JTextArea();

        workWindow.setFont(new Font("Monospaced", Font.BOLD, 12));
        workWindow.setText("Welcome to the Hardware Store Management System.");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String[] menuOptions = {"Please select....",
                "1. Show all existing items records in the database (sorted by ID number).",
                "2. Add new item (or quantity) to the database.",
                "3. Delete an item from a database.",
                "4. Search for an item given its name.",
                "5. Show a list of users in the database.",
                "6. Add new user to the database.",
                "7. Update user info (given their id).",
                "8. Complete a sale transaction.",
                "9. Show completed sale transactions.",
                "10. Exit program."};



        JComboBox menuList = new JComboBox(menuOptions);
        menuList.setSelectedIndex(0);
        newPanelforSelection.add(guideText);
        newPanelforSelection.add(menuList);
        frame.add(newPanelforSelection);
        frame.setVisible(true);

        menuList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == menuList) {
                    JComboBox cb = (JComboBox) e.getSource();
                    String msg = (String) cb.getSelectedItem();
                    switch (msg) {
                        case "Please select....":
                            panel.removeAll();
                            newPanelforSelection.add(guideText);
                            newPanelforSelection.add(menuList);
                            workWindow.setText("Welcome to the Hardware Store Management System. \n");
                            frame.setVisible(true);
                            break;

                        case "1. Show all existing items records in the database (sorted by ID number).":
                            panel.removeAll();
                            newPanelforSelection.add(guideText);
                            newPanelforSelection.add(menuList);
                            HardwareStore.sortItemList();
                            workWindow.setText("\n1. Show all existing items records in the database" +
                                    " (sorted by ID number)." + "\n" + hardwareStore.getAllItemsFormatted());

                            // Hidden Labels + Forms for formatting
                            JLabel hidden1 = new JLabel("Name");
                            JTextArea hidden2 = new JTextArea();
                            panel.add(hidden1);
                            panel.add(hidden2);
                            hidden1.setVisible(false);
                            hidden2.setVisible(false);
                            hidden2.setColumns(20);
                            hidden2.setAlignmentX(Component.CENTER_ALIGNMENT);
                            panel.updateUI();
                            frame.setVisible(true);
                            break;

                        case "2. Add new item (or quantity) to the database.":
                            panel.removeAll();
                            newPanelforSelection.add(guideText);
                            newPanelforSelection.add(menuList);
                            workWindow.setText("2. Add new item (or quantity) to the database.");

                            //Labels
                            JLabel pkgLabel = new JLabel("Package Type");

                            JLabel idLabel = new JLabel("Item ID");
                            idLabel.setBounds(65,68,46,14);

                            JLabel nameLabel = new JLabel("Item Name");
                            nameLabel.setBounds(70, 73, 46, 14);

                            JLabel quanLabel = new JLabel("Item Quantity");
                            quanLabel.setBounds(75, 78, 46, 14);

                            JLabel priceLabel = new JLabel("Item Price");
                            priceLabel.setBounds(65, 68, 46, 14);

                            JLabel typeLabel = new JLabel("Appliance Type");
                            typeLabel.setBounds(65, 68, 46, 14);

                            JLabel brandLabel = new JLabel("Appliance Brand");
                            brandLabel.setBounds(65, 68, 46, 14);

                            JLabel categoryLabel = new JLabel("Category");
                            categoryLabel.setBounds(70, 38, 46, 14);


                            //Forms
                            JTextArea idForm = new JTextArea();
                            idForm.setBounds(128,65,86,20);

                            JTextArea nameForm = new JTextArea();
                            nameForm.setBounds(128,65,86,20);

                            JTextArea quanForm = new JTextArea();
                            quanForm.setBounds(128,65,86,20);

                            JTextArea priceForm = new JTextArea();
                            priceForm.setBounds(128,65,86,20);

                            JTextArea brandForm = new JTextArea();
                            brandForm.setBounds(128,65,86,20);


                            String[] packageOptions = {"Small Hardware Item",
                                    "Appliance"};

                            String[] applianceOptions = {"Refrigerators",
                                    "Washers & Dryers",
                                    "Ranges & Ovens",
                                    "Small Appliance"};

                            String[] categoryOptions = {"Door & Window",
                                    "Cabinet & Furniture",
                                    "Fasteners",
                                    "Structural",
                                    "Other"};

                            JComboBox packageType = new JComboBox(packageOptions);
                            JComboBox applianceType = new JComboBox(applianceOptions);
                            JComboBox categoryType = new JComboBox(categoryOptions);

                            packageType.getSelectedItem();
                            Object selectedItem = packageType.getSelectedItem();
                            packageType.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if (e.getSource() == packageType) {
                                        JComboBox cb2 = (JComboBox) e.getSource();
                                        String msg2 = (String) cb2.getSelectedItem();
                                        switch (msg2) {
                                            case "Small Hardware Item":
                                                panel.removeAll();
                                                newPanelforSelection.add(guideText);
                                                newPanelforSelection.add(menuList);

                                                panel.add(pkgLabel);
                                                panel.add(packageType);

                                                panel.add(idLabel, BorderLayout.CENTER);
                                                panel.add(idForm, BorderLayout.CENTER);
                                                idForm.setColumns(10);

                                                panel.add(nameLabel, BorderLayout.CENTER);
                                                panel.add(nameForm, BorderLayout.CENTER);
                                                nameForm.setColumns(10);

                                                panel.add(quanLabel, BorderLayout.CENTER);
                                                panel.add(quanForm, BorderLayout.CENTER);
                                                quanForm.setColumns(10);

                                                panel.add(priceLabel);
                                                panel.add(priceForm);
                                                priceForm.setColumns(10);

                                                panel.add(categoryLabel);
                                                panel.add(categoryType);

                                                frame.setVisible(true);
                                                panel.add(confirmButtom2);
                                                panel.updateUI();
                                                confirmButtom2.addActionListener(new ActionListener() {
                                                    @Override
                                                    public void actionPerformed(ActionEvent e) {

                                                        categoryType.getSelectedItem();
                                                        String selectedItem3 =
                                                                categoryType.getSelectedItem().toString();

                                                        String id = idForm.getText();
                                                        String name = nameForm.getText();
                                                        int quan = Integer.parseInt(quanForm.getText());
                                                        int price = Integer.parseInt(priceForm.getText());
                                                        hardwareStore.addNewSmallHardwareItem(id, name, quan,
                                                                price, selectedItem3);
                                                        workWindow.setText("\nNew small hardware " +
                                                                "item has been added.");

                                                    }
                                                });
                                                frame.setVisible(true);
                                                break;

                                            case "Appliance":
                                                panel.removeAll();
                                                newPanelforSelection.add(guideText);
                                                newPanelforSelection.add(menuList);
                                                panel.add(pkgLabel);
                                                panel.add(packageType);

                                                panel.add(idLabel);
                                                panel.add(idForm);
                                                idForm.setColumns(10);

                                                panel.add(nameLabel);
                                                panel.add(nameForm);
                                                nameForm.setColumns(10);

                                                panel.add(quanLabel);
                                                panel.add(quanForm);
                                                quanForm.setColumns(10);

                                                panel.add(priceLabel);
                                                panel.add(priceForm);
                                                priceForm.setColumns(10);

                                                panel.add(typeLabel);
                                                panel.add(applianceType);

                                                panel.add(brandLabel);
                                                panel.add(brandForm);
                                                brandForm.setColumns(10);

                                                frame.setVisible(true);

                                                panel.add(confirmButtom25);
                                                panel.updateUI();
                                                confirmButtom25.addActionListener(new ActionListener() {
                                                    @Override
                                                    public void actionPerformed(ActionEvent e) {

                                                        applianceType.getSelectedItem();
                                                        String selectedItem2 =
                                                                applianceType.getSelectedItem().toString();

                                                        String id = idForm.getText();
                                                        String name = nameForm.getText();
                                                        String brand = brandForm.getText();
                                                        int quan = Integer.parseInt(quanForm.getText());
                                                        int price = Integer.parseInt(priceForm.getText());
                                                        hardwareStore.addNewAppliance(id, name, quan, price,
                                                                brand, selectedItem2);
                                                        workWindow.setText("New appliance item has been added.");

                                                    }
                                                });
                                                frame.setVisible(true);
                                                break;

                                            default:
                                                workWindow.setText("How");
                                                break;
                                        }


                                    }


                                }
                            });

                            // Upon initial selection of case 2
                            if(selectedItem == "Small Hardware Item") {
                                panel.removeAll();
                                panel.add(pkgLabel);
                                panel.add(packageType);

                                panel.add(idLabel, BorderLayout.CENTER);
                                panel.add(idForm, BorderLayout.CENTER);
                                idForm.setColumns(10);

                                panel.add(nameLabel, BorderLayout.CENTER);
                                panel.add(nameForm, BorderLayout.CENTER);
                                nameForm.setColumns(10);

                                panel.add(quanLabel, BorderLayout.CENTER);
                                panel.add(quanForm, BorderLayout.CENTER);
                                quanForm.setColumns(10);

                                panel.add(priceLabel, BorderLayout.CENTER);
                                panel.add(priceForm, BorderLayout.CENTER);
                                priceForm.setColumns(10);

                                panel.add(categoryLabel);
                                panel.add(categoryType);
                                frame.setVisible(true);

                                panel.add(confirmButtom);
                                panel.updateUI();
                                confirmButtom.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {

                                        applianceType.getSelectedItem();
                                        String selectedItem2 = applianceType.getSelectedItem().toString();

                                        if (selectedItem == "Small Hardware Item") {
                                            String id = idForm.getText();
                                            String name = nameForm.getText();
                                            int quan = Integer.parseInt(quanForm.getText());
                                            int price = Integer.parseInt(priceForm.getText());
                                            hardwareStore.addNewSmallHardwareItem(id, name, quan, price, selectedItem2);
                                            workWindow.setText("New small hardware item has been added.");
                                        }
                                    }
                                });
                            }


                            frame.add(panel3, BorderLayout.LINE_END);
                            frame.setVisible(true);


                            break;

                        case "3. Delete an item from a database.":

                            workWindow.setText("3. Delete an item from a database");

                            JLabel idLabel2 = new JLabel("Item ID");

                            JTextArea idForm2 = new JTextArea();

                            Container pane = new Container();
                            pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

                            panel.removeAll();
                            newPanelforSelection.add(guideText);
                            newPanelforSelection.add(menuList);

                            panel.add(idLabel2);
                            panel.add(idForm2);
                            idForm2.setColumns(20);
                            idForm2.setAlignmentX(Component.CENTER_ALIGNMENT);

                            panel.add(confirmButtom3);
                            confirmButtom3.setAlignmentX(Component.CENTER_ALIGNMENT);
                            confirmButtom3.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {

                                    int itemIdToSearch = Integer.parseInt(idForm2.getText());
                                    int tempValueForIndex = hardwareStore.findItemIndex(idForm2.getText());
                                    hardwareStore.removeItem(tempValueForIndex);
                                    workWindow.setText("\nPackage Deleted\n");
                                }
                            });

                            frame.add(pane, BorderLayout.EAST);
                            panel.updateUI();
                            frame.setVisible(true);
                            break;

                        case "4. Search for an item given its name.":
                            panel.removeAll();
                            newPanelforSelection.add(guideText);
                            newPanelforSelection.add(menuList);

                            JLabel nameSearch = new JLabel("Name");

                            JTextArea nameForm2 = new JTextArea();
                            nameForm2.setBounds(450,350,20,20);

                            panel.add(nameSearch);
                            nameSearch.setAlignmentY(Component.CENTER_ALIGNMENT);
                            panel.add(nameForm2);
                            nameForm2.setColumns(10);

                            panel.add(confirmButtom4);
                            panel.updateUI();
                            confirmButtom4.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    String nameToSearch = nameForm2.getText();
                                    workWindow.setText(hardwareStore.getMatchingItemsByName(nameToSearch));

                                }
                            });
                            workWindow.setText("4. Search for an item given its name.");
                            frame.setVisible(true);
                            break;

                        case "5. Show a list of users in the database.":
                            panel.removeAll();
                            newPanelforSelection.add(guideText);
                            newPanelforSelection.add(menuList);
                            panel.updateUI();
                            workWindow.setText("5. Show a list of users in the database.\n"
                                    + hardwareStore.getAllUsersFormatted());
                            break;

                        case "6. Add new user to the database.":
                            panel.removeAll();
                            newPanelforSelection.add(guideText);
                            newPanelforSelection.add(menuList);
                            workWindow.setText("6. Add new user to the database.");
                            //Labels
                            JLabel usrType = new JLabel("User Type");

                            JLabel nameLabel1 = new JLabel("First Name");
                            nameLabel1.setBounds(65,100,46,14);

                            JLabel lastName1 = new JLabel("Last Name");
                            lastName1.setBounds(65,68,46,14);

                            JLabel phnnumba = new JLabel("Phone Number(string)");
                            phnnumba.setBounds(65,68,46,14);

                            JLabel address1 = new JLabel("Address(string)");
                            address1.setBounds(65,68,46,14);

                            JLabel salary1 = new JLabel("Salary (float)");
                            salary1.setBounds(65,68,46,14);

                            JLabel SSN1 = new JLabel("SSN (9 - digital int)");
                            SSN1.setBounds(65,68,46,14);
                            //Forms
                            JTextArea nameForm3 = new JTextArea();
                            nameForm3.setBounds(128,40,200,20);

                            JTextArea lastNameForm = new JTextArea();
                            lastNameForm.setBounds(128,65,86,20);

                            JTextArea phoneNumberForm1 = new JTextArea();
                            phoneNumberForm1.setBounds(128,65,86,20);

                            JTextArea addressForm = new JTextArea();
                            addressForm.setBounds(128,65,86,20);

                            JTextArea salaryForm = new JTextArea();
                            salaryForm.setBounds(128,65,86,20);

                            JTextArea SSNForm = new JTextArea();
                            SSNForm.setBounds(128,65,86,20);

                            String[] userOptions = {"Customer",
                                    "Employee"};

                            JComboBox userType = new JComboBox(userOptions);
                            panel.add(userType);
                            panel.updateUI();

                            userType.getSelectedItem();
                            Object chosenOption = userType.getSelectedItem();
                            userType.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if (e.getSource() == userType){
                                        JComboBox cb3 = (JComboBox) e.getSource();
                                        String msg3 = (String) cb3.getSelectedItem();

                                        switch (msg3) {
                                            case "Customer":
                                                panel.removeAll();
                                                newPanelforSelection.add(guideText);
                                                newPanelforSelection.add(menuList);

                                                panel.add(usrType);
                                                panel.add(userType);

                                                panel.add(nameLabel1);
                                                panel.add(nameForm3);
                                                nameForm3.setColumns(10);

                                                panel.add(lastName1);
                                                panel.add(lastNameForm);
                                                lastNameForm.setColumns(10);

                                                panel.add(phnnumba);
                                                panel.add(phoneNumberForm1);
                                                phoneNumberForm1.setColumns(10);

                                                panel.add(address1);
                                                panel.add(addressForm);
                                                addressForm.setColumns(10);

                                                panel.add(confirmButtom6);
                                                confirmButtom6.addActionListener(new ActionListener() {
                                                    @Override
                                                    public void actionPerformed(ActionEvent e) {

                                                        String name = nameForm3.getText();
                                                        String lastName = lastNameForm.getText();
                                                        String phoneNumber = phoneNumberForm1.getText();
                                                        String address = addressForm.getText();
                                                        hardwareStore.addCustomer(name, lastName,
                                                                phoneNumber, address);
                                                        workWindow.setText("Customer has been  added");
                                                        counter++;

                                                    }

                                                });
                                                frame.setVisible(true);
                                                panel.updateUI();
                                                break;
                                            case "Employee":
                                                panel.removeAll();
                                                newPanelforSelection.add(guideText);
                                                newPanelforSelection.add(menuList);

                                                panel.add(usrType);
                                                panel.add(userType);

                                                panel.add(nameLabel1);
                                                panel.add(nameForm3);
                                                nameForm3.setColumns(10);

                                                panel.add(lastName1);
                                                panel.add(lastNameForm);
                                                lastNameForm.setColumns(10);

                                                panel.add(salary1);
                                                panel.add(salaryForm);
                                                salaryForm.setColumns(10);

                                                panel.add(SSN1);
                                                panel.add(SSNForm);
                                                SSNForm.setColumns(10);


                                                frame.setVisible(true);

                                                panel.add(confirmButtom65);
                                                panel.updateUI();
                                                confirmButtom65.addActionListener(new ActionListener() {
                                                    @Override
                                                    public void actionPerformed(ActionEvent e) {

                                                        String name = nameForm3.getText();
                                                        String lastname = lastNameForm.getText();
                                                        float slry = Float.parseFloat(salaryForm.getText());
                                                        int ssn = Integer.parseInt(SSNForm.getText());
                                                        hardwareStore.addEmployee(name,lastname,ssn,slry);
                                                        workWindow.setText("New Employee has been added.");
                                                        counter++;

                                                    }
                                                });

                                                frame.setVisible(true);
                                                break;

                                            default:
                                                workWindow.setText("How");
                                                break;
                                        }

                                    }

                                    frame.add(panel3, BorderLayout.LINE_END);
                                    frame.setVisible(true);

                                }
                            });
                            break;

                        case "7. Update user info (given their id).":
                            panel.removeAll();
                            newPanelforSelection.add(guideText);
                            newPanelforSelection.add(menuList);

                            JLabel userID = new JLabel("User ID");
                            JTextArea userIDform = new JTextArea();
                            JButton searchUser = new JButton("Search User");

                            panel.add(userID);
                            panel.add(userIDform);
                            panel.add(searchUser);
                            panel.updateUI();
                            workWindow.setText("7. Update user info (given their id).");
                            frame.setVisible(true);

                            searchUser.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {

                                    int userIDnumber = Integer.parseInt(userIDform.getText());
                                    User editUser = hardwareStore.findUser(userIDnumber);
                                    if (editUser == null) {
                                        workWindow.setText("\nUser not found.\n\n");
                                    }
                                    if (editUser.isEmployee) {
                                        workWindow.append("\nEmployee record found. \n\n" +
                                                "Updating Employee information. \n \n");
                                        panel.removeAll();
                                        panel.add(userID);
                                        panel.add(userIDform);

                                        panel.add(searchUser);

                                        // Labels
                                        JLabel newFirstName = new JLabel("First Name");
                                        JLabel newLastName = new JLabel("Last Name");
                                        JLabel newSalary = new JLabel("Salary (float)");
                                        JLabel newSSN = new JLabel("SSN (9-digit-int");
                                        JLabel dummyLabel = new JLabel();

                                        // Forms
                                        JTextArea newFirstNameForm = new JTextArea();
                                        JTextArea newLastNameForm = new JTextArea();
                                        JTextArea newSalaryForm = new JTextArea();
                                        JTextArea newSSNForm = new JTextArea();

                                        panel.add(dummyLabel);

                                        panel.add(newFirstName);
                                        panel.add(newFirstNameForm);

                                        panel.add(newLastName);
                                        panel.add(newLastNameForm);


                                        panel.add(newSalary);
                                        panel.add(newSalaryForm);

                                        panel.add(newSSN);
                                        panel.add(newSSNForm);


                                        panel.add(confirmButtom7);
                                        panel.updateUI();
                                        confirmButtom7.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                String firstNameFielder = newFirstNameForm.getText();
                                                String lastNameFielder = newFirstNameForm.getText();
                                                int salSal = Integer.parseInt(newSalaryForm.getText());
                                                int ssnForFunc = Integer.parseInt(newSSNForm.getText());

                                                hardwareStore.editEmployeeInformation(userIDnumber, firstNameFielder,
                                                        lastNameFielder, ssnForFunc, salSal);

                                                workWindow.setText("Employee updated.");
                                            }
                                        });


                                    }

                                    else {
                                        // Labels
                                        JLabel custNewFName = new JLabel("First Name");
                                        JLabel custNewLName = new JLabel("Last Name");
                                        JLabel custNewPhone = new JLabel("Phone Number (string)");
                                        JLabel custNewAddy = new JLabel("Address (string)");
                                        JLabel dummyLabel= new JLabel();

                                        // Forms
                                        JTextArea custNewFNameForm = new JTextArea();
                                        JTextArea custNewLNameForm = new JTextArea();
                                        JTextArea custNewPhoneForm = new JTextArea();
                                        JTextArea custNewAddyForm = new JTextArea();

                                        panel.removeAll();

                                        workWindow.append("\n Customer record found. \n\n" +
                                                "Updating Customer information. \n \n");
                                        panel.add(userID);
                                        panel.add(userIDform);

                                        panel.add(searchUser);
                                        panel.add(dummyLabel);

                                        panel.add(custNewFName);
                                        panel.add(custNewFNameForm);

                                        panel.add(custNewLName);
                                        panel.add(custNewLNameForm);

                                        panel.add(custNewPhone);
                                        panel.add(custNewPhoneForm);

                                        panel.add(custNewAddy);
                                        panel.add(custNewAddyForm);

                                        panel.add(confirmButtom75);
                                        panel.updateUI();
                                        confirmButtom75.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {

                                                String firstNameToInput = custNewFNameForm.getText();
                                                String lastNameToInput = custNewLNameForm.getText();
                                                String phoneToInput = custNewPhoneForm.getText();
                                                String addyToInput = custNewAddyForm.getText();

                                                hardwareStore.editCustomerInformation(userIDnumber, firstNameToInput,
                                                        lastNameToInput, phoneToInput, addyToInput);
                                                workWindow.setText("Customer updated.");
                                            }
                                        });
                                    }






                                }
                            });
                            break;

                        case "8. Complete a sale transaction.":
                            panel.removeAll();
                            newPanelforSelection.add(guideText);
                            newPanelforSelection.add(menuList);

                            workWindow.setText("8. Complete a sale transaction.");

                            // Forms
                            JLabel customerID = new JLabel("Customer ID (int)");
                            JLabel empID = new JLabel("Employee ID (int)");
                            JLabel itemID2 = new JLabel("Item ID (string)");
                            JLabel quanLabel2 = new JLabel("Quantity (int)");

                            // Fields
                            JTextArea customerIDForm = new JTextArea();
                            JTextArea empIDForm = new JTextArea();
                            JTextArea itemID2Form = new JTextArea();
                            JTextArea quanLabel2Form = new JTextArea();

                            panel.add(customerID);
                            panel.add(customerIDForm);

                            panel.add(empID);
                            panel.add(empIDForm);

                            panel.add(itemID2);
                            panel.add(itemID2Form);

                            panel.add(quanLabel2);
                            panel.add(quanLabel2Form);

                            panel.add(confirmButtom8);
                            panel.updateUI();
                            confirmButtom8.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    int customerIdGet = Integer.parseInt(customerIDForm.getText());
                                    int empIdGet = Integer.parseInt(empIDForm.getText());
                                    String itemIdGet = itemID2Form.getText();
                                    int quanGet = Integer.parseInt(quanLabel2Form.getText());
                                    int tempValueForIndex2 = hardwareStore.findItemIndex(itemID2Form.getText());
                                    hardwareStore.progressTransaction(itemIdGet, quanGet, customerIdGet, empIdGet,
                                            tempValueForIndex2);
                                    workWindow.setText("Transaction Completed!");
                                }
                            });

                            frame.setVisible(true);
                            break;

                        case "9. Show completed sale transactions.":
                            panel.removeAll();
                            newPanelforSelection.add(guideText);
                            newPanelforSelection.add(menuList);
                            panel.updateUI();
                            workWindow.setText("9. Show completed sale transactions.\n" +
                                    hardwareStore.getAllTransactionsFormatted());
                            break;

                        case "10. Exit program.":
                            System.exit(0);
                            break;

                        default:
                            workWindow.setText("Welcome to the Hardware Store Management System.");
                    }
                }
            }
        });
        panel.setLayout(new GridLayout(10, 2));
        panel.setPreferredSize(new Dimension(550, 250));
        frame.add(newPanelforSelection, BorderLayout.NORTH);
        frame.setPreferredSize(new Dimension(900, 700));
        workWindow.setPreferredSize(new Dimension(875, 350));
        Border outsideBorder = BorderFactory.createEtchedBorder();
        workWindow.setBorder(BorderFactory.createCompoundBorder(outsideBorder, BorderFactory.createLineBorder(Color.black)));
        JScrollPane scroller = new JScrollPane(workWindow);
        scroller.setBounds(10, 11, 455, 249);
        panel2.add(scroller);
        frame.add(panel, BorderLayout.EAST);
        frame.add(panel2, BorderLayout.SOUTH);
        workWindow.setLineWrap(true);
        workWindow.setWrapStyleWord(true);
        newPanelforSelection.add(guideText);
        newPanelforSelection.add(menuList);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        workWindow.setEditable(false);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


    }

}


