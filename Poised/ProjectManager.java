import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *  Poised project manager is a simple program which could be used in any project management scenario that
 *  deals in building/construction. 
 *  Various options are available to the user from adding new projects to searching for any pending projects. 
 *  The menu system makes it easy and clear to navigate and saves all of the information input to a separate
 *  text file and you are also able to track if payments are due and what is still owed.
 *  Once the user exits the program, the data is saved to text file upon exit.
 *  

 * @author George
 * @version 1.01
 * @since 2020/09/24
 */
public class ProjectManager {
    static int projNumber = 0;
    // 
    static Scanner userInput = new Scanner(System.in);

    /**
     * Supplies command-line arguments and creates list array of projects for the
     * user to interact with.
     * 
     * @param args returns String arguments
        return */
        
    public static void main(String[] args) {

        ArrayList<Project> projectList = new ArrayList<Project>();
        System.out.print("=====================================================================================\n");
        System.out.print("=========================== Poised Project Management ===============================\n");
        System.out.print("=====================================================================================\n");

        /**
         * Menu system requests user input to navigate main menu and sub-menus
         * 
         * @param true statement is true and opens switch case menu
         * 
         */
        while (true) {
            System.out.print("==============================Main menu======================================\n");
            System.out.print("\nPlease choose an option from the menu below\n");
            System.out.print("\n1 - Create new Project\n" + "2 - Update existing projects\n"
                + "3 - Get projects from the database\n" + "4 - Finalize existing projects\n"
                + "5 - View pending projects\n" + "6 - View overdue projects \n"
                + "7 - Find project by number or name\n" + "8 - Exit program\n"
                + "=====================================================================================\n\n");

            String option = userInput.nextLine(); // Read user input
            /**
             * 
             *
             * @param option select number 1-8 and go to menu option 
             * @exception number or character selection is out of range or invalid  
             */
            switch (option) {
                case "1":
                    projectList.add(new Project()); // Add project to list of projects and send it to createNew()function
                    createNew(projectList);
                    break;
                case "2":
                    updateProject(projectList);
                    break;
                case "3":
                    System.out.print("======================= Existing Projects ==============================\n");
                    getExistingProjects(projectList);
                    break;
                case "4":
                    finalizeMenu(projectList);
                    break;
                case "5":
                    pendingProject(projectList);
                    break;
                case "6":
                    overDueProject(projectList);
                    break;
                case "7":

                    findProject(projectList);// Find project by number or name
                    break;
                case "8":
                    if (projectList.isEmpty()) {
                        /* There's nothing to do */
                    } else {
                        callSaveToFile(projectList);
                    }
                    System.out.print("Saving and exiting... Have a nice day! \n" + "=====================================================================================");
                    System.exit(0);
                    System.out.print("Invalid selection. Try again\n");
                }
                break;
            }
        }
    

    /**
     * Displays date format pattern when requesting user to enter date.
     * 
     * @param strDate calls string containing date format 
     * @return returns dateObj from calling function
     */
    static LocalDate getDateFormat(String strDate) {
        LocalDate dateObj = null;
        DateTimeFormatter myFormatObj = null;
        myFormatObj = DateTimeFormatter.ofPattern("dd MMMM yyyy"); // Format of date object 
        dateObj = LocalDate.parse(strDate, myFormatObj);// Convert string entered by user to LocalDate object

        return dateObj;
    }
    /**
     * This block requests user to enter details of parties involved in projects
     *
     * @param projObj creats object
     * @param whoIs stores entered details as string
     */
    static void insertPersonInfo(Project projObj, String whoIs) {
        String name = "";
        String email = "";
        String phone = "";
        String add = "";
        System.out.print("Enter name: ");
        name = userInput.nextLine();

        /**
         * Block requests email address
         * 
         * @param true sets string format conditions
         * @exception when email is invalid
         */
        while (true) {
            try {
                System.out.print("Enter email: ");
                email = userInput.nextLine();
                String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";

                if (email.matches(emailRegex)) { // If email is valid
                    break;

                } else { // Email is not valid
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("*Invalid email*");
            }
        }
        /**
         * Block requests phone number
         * 
         * @param true sets number string format conditions
         * @exception when phone number is invalid
         */
        while (true) {
            try {
                System.out.print("Enter Telephone: ");
                phone = userInput.nextLine();
                if (phone.matches("[0/+27]?[0-9]+") && (phone.length() >= 9 && phone.length() <= 12)) {
                    break;

                } else { // Invalid number
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("*Invalid phone number*");
            }
        }
        System.out.print("Enter address:\n");
        add = userInput.nextLine();

        /**
         * customer, architect and contractor objects are populated
         *
         * @param "customer" contains name, phone, email, add, whoIs
         */

        if (whoIs == "customer") {
            projObj.setCustomer(name, phone, email, add, whoIs);

        /**
         *
         * @param "architect" contains name, phone, email, add, whoIs
         */

        } else if (whoIs == "architect") {
            projObj.setArchitect(name, phone, email, add, whoIs);

        /**
         *
         * @param "contractor" contains name, phone, email, add, whoIs
         */

        } else if (whoIs == "contractor") {
            projObj.setContractor(name, phone, email, add, whoIs);
        }
    }

    /**
     * Block creates a new project and lets the user enter and stores those as objects in project.
     * Then saves this as customer 
     *
     * @param projectList stores all information in an array 
     * @exception handles incorrect input
     */

    static void createNew(ArrayList<Project> projectList) {
        int currIndex;
        /* CurrIndex is the index
         ProjectList.get(currIndex) is the project we want */
        for (currIndex = 0; currIndex < projectList.size(); currIndex++)             
        
            currIndex = currIndex - 1;
            System.out.print("\n======= Create New Project ======= \n\n");
            System.out.print("Enter project name: \n"); // Ask for project information
            String projName = userInput.nextLine();// Read user input
            projectList.get(currIndex).setName(projName); // Save input to project
            System.out.println("The number of this project is " + (++projNumber)); // Save project number to project and post increment
            projectList.get(currIndex).setNumber(projNumber); // Read user input
            System.out.print("Enter project address:\n");
            String projAdd = userInput.nextLine(); // Save input to project
            projectList.get(currIndex).setAddress(projAdd);
            System.out.print("Enter project erf number:\n");
            String erf = userInput.nextLine(); // Read user input
            projectList.get(currIndex).setErfNumber(erf); // Save input to project
            System.out.print("Enter project type(eg:house, apartment etc...):\n");
            String buildType = userInput.nextLine();
            projectList.get(currIndex).setType(buildType);


        while (true) {
            try {
                System.out.print("What is the amount being charged for this project ?:\n");
                String projPrice = userInput.nextLine();
                projectList.get(currIndex).setFee(Double.parseDouble(projPrice));
                break;
            } catch (Exception e) {
                System.out.println("*make sure you enter a monetary value*");
                continue;
            }
        }
        enterProjectDueDate(projectList, currIndex);
        System.out.print("________________________________________________\n");
        System.out.print("Enter Customer details\n\n");
        insertPersonInfo(projectList.get(currIndex), "customer"); // Save customer information to project
        projName = projName.replace(" ", ""); // Remove any white space from the if statement bellow to ignore white spaces
        if (projName.isEmpty()) {

            String str = projectList.get(currIndex).getCustomer().getName(); // Get name of customer
            String[] arrOfStr = str.split(" ", 0); 
            String newName = projectList.get(currIndex).getType() + " " + arrOfStr[arrOfStr.length - 1];
            projectList.get(currIndex).setName(newName);
        }
        System.out.print("________________________________________________\n");
        System.out.print("Enter Architect details\n\n");
        insertPersonInfo(projectList.get(currIndex), "architect"); // Save architect information to project
        System.out.print("________________________________________________\n");
        System.out.print("Enter Contractor details\n\n");
        insertPersonInfo(projectList.get(currIndex), "contractor"); // Save contractor information to project
        System.out.print("****Project details have been successufully captured!****\n");
    }

    /**
     * Block requests user to input project due date according to example
     *
     * @param projectList stores information in array
     * @param currIndex stores user input in project list
     */
    public static void enterProjectDueDate(ArrayList<Project> projectList, int currIndex) {

        while (true) {
            try {
                System.out.print("Enter the project due date(format: day month year; eg: 09 November 2020):\n");
                String dateStr = userInput.nextLine(); // Read user input
                projectList.get(currIndex).setDeadline(getDateFormat(dateStr));

                break; // Come out of the loop
            } catch (Exception e) {
                System.out.print("*Make sure date format is correct \nFormat: day month year \nMake use of two digits for the day eg: 01\n" + "and first letter of month must be captalized eg: January*\n\n");
            }
        }
    }

    /**
     * Block updates an existing project and accessess a subscript by checking to see if the 
     * project exists. It then switches between options each with there own subtasks.
     *
     * @param projectList contains updated project information
     * @param opt switch containing menu items 1-4
     * @return goes to main menu
     */
    static void updateProject(ArrayList<Project> projectList) {
        System.out.print("========================= Update existing Project =================================\n\n");
        while (true) {
            System.out.print("Note that there are currently " + projNumber + " project(s) registered.\n" + "Project numbers start from 1\n");

            if (projNumber == 0) { // There are 0 projects do not allow user to continue
                return;
            }
            System.out.print("\nEnter the project number you want to update: ");
            int index = 0;
            try {
                String i = userInput.nextLine();
                index = Integer.parseInt(i); // Convert user input to integer
            } catch (Exception e) {
                System.out.print("**Wrong input format!**");
                return;
            }

            index = index - 1; // Subscript start at 0
            if (index >= 0 && index < projNumber && projectList.isEmpty() == false && projectList.get(index) != null) {
                System.out.print("\nChoose an option from the menu below\n");
                System.out.print("\n1 - Change project due date\n" + "2 - Change total amount of the fee paid to date \n" + "3 - Update Contractor\'s contact details\n" + "4 - Go back to main menu\n");
                String opt = userInput.nextLine();

                switch (opt) {
                    case "1":
                        System.out.print("\n=========== Change project due date ===========\n\n");
                        enterProjectDueDate(projectList, index);
                        System.out.print("***Due date updated successfully!***\n\n");

                        return; // Go back to main menu
                    case "2":
                        System.out.print("\n========== Change total amount of the fee paid to date ===========\n\n");
                        while (true) {
                            try {
                                System.out.print("Enter total amount of the fee paid to date for project " + (index + 1) + " :");
                                String amountStr = userInput.nextLine();
                                double amountPaid = Double.parseDouble(amountStr);
                                projectList.get(index).setAmountPaid(amountPaid); // Save amount paid to date to project
                                System.out.print("***Total amount of the fee paid to date updated successfully!***\n\n");
                                break;
                            } catch (Exception e) {
                                System.out.println("*Make sure you enter a monetary value*");
                                continue;
                            }
                        }
                        return;
                    case "3":
                        System.out.print("\n============= Update Contractor contact details ======================\n\n");
                        System.out.print("Contractor details for project " + (index + 1) + ":\n");
                        insertPersonInfo(projectList.get(index), "contractor");
                        System.out.print("***Contractor details updated successfully!***\n\n");
                        return;
                    case "4":
                        return; // Go back to main menu
                    default:
                        System.out.print("Invalid menu Selection\n\n");
                        break;
                }

            } else {
                System.out.print("You have entered and an invalid project number!\n");
                return;
            }
        }
    }

    /**
     * Displays projects that are still pending.
     *
     * @param projectList accesses
     */
    static void pendingProject(ArrayList<Project> projectList) {
        System.out.print("==================== Pending Projects ===========================\n\n");
        if (projectList.isEmpty()) {
            System.out.print("No projects available\n");
            System.out.print("OR make sure you have retrieved projects from the database (Option 3)");
        } else {
            for (Project obj : projectList) {
                if (!obj.isFinalized()) {
                    displayExistingProjects(obj);
                }
            }
        }
        goBacktoMenu();
    }

    /**
     * Displays overdue projects
     *
     * @param projectList access projectlist from array
     */
    static void overDueProject(ArrayList<Project> projectList) {
        System.out.print("==================== Overdue projects =========================\n\n");
        if (projectList.isEmpty()) {
            System.out.print("No projects available\n");
            System.out.print("Or make sure you get projects from the database (Option 3)");
        } else {
            LocalDate dateNow = LocalDate.now(); // Current date is the date the project was created format(yyy-mm-dd)
            for (Project obj : projectList) {
                if (!obj.isFinalized() && dateNow.isAfter(obj.getDeadline())) {
                    displayExistingProjects(obj);
                }
            }
        }
    }

    /**
     * Block lets user find projects by entering it's name or number. Boolean case will
     * notify the user whether the project exists or not.
     *
     * @param projectList access projectlist from array
     */
    static void findProject(ArrayList<Project> projectList) {
        System.out.print("==================== Find projects =========================\n\n");
        System.out.print("Enter project number or name to find it:\n");
        String input = userInput.nextLine();
        boolean isNumber = false;
        for (int i = 0; i < input.length(); i++) {
            if (!Character.isDigit(input.charAt(i))) {
                isNumber = false;
                break;
            } else {
                isNumber = true;
            }
        }
        boolean foundSomething = false; // This flag will notify user when something went wrong

        try {
            for (Project obj : projectList) {
                if ((isNumber && (obj.getNumber() == Integer.parseInt(input)))|| (!isNumber && obj.getName().equals(input))) {
                    displayExistingProjects(obj);
                    foundSomething = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (foundSomething) { // Pass
        } else { 
            System.out.print("Search result is empty!\n");
        }
        goBacktoMenu();
    }

    /**
     * Block displays how many projects are registered and allows user to finalise the project.
     *
     * @param projectList access projectlist from array
     */
    static void finalizeMenu(ArrayList<Project> projectList) {
        System.out.print("======================== Finalise project ==========================\n\n");
        System.out.print("Note that there are currently " + projNumber + " project(s) registered.\n" + "Project numbers start from 1\n");
        if (projNumber == 0) { // There are 0 projects do not allow user to continue

            return;
        }
        System.out.print("\nEnter the project number you want to finalize: ");
        int index = 0;
        try {
            String i = userInput.nextLine();
            index = Integer.parseInt(i); // convert user input to integer
        } catch (Exception e) {
            System.out.print("**Wrong input format!**");
            return;
        }
        index = index - 1; // Subscript start at 0

        if (index >= 0 && index < projNumber && projectList.isEmpty() == false && projectList.get(index) != null) {
            System.out.print("\n\n");
            projectList.get(index).finaliseProject(projectList.get(index));
        } else {
            System.out.print("Make sure you enter a valid project number!\n");
        }
    }

    /**
     * This block writes the updated content of the project to a text file.
     *
     * @param projectList access projectlist from array
     */
    static void getExistingProjects(ArrayList<Project> projectList) {
        ArrayList<String> list = new ArrayList<String>(); // List will store projects from txt file

        File inputFile = null;
        try {
            inputFile = new File("ExistingProjects.txt"); // Specify file                                                                                                                     

            Scanner fileReader = new Scanner(inputFile);
            // Scanner object
            int count = 1;
     
            while (fileReader.hasNext()) {
                String line = fileReader.nextLine();
                String[] str = line.split(", ", 0);// Line read from file split at comma(", ") and stored to str array
                for (String i : str)
                    list.add(i);
                if (count % 4 == 0) {
                    Project proj = new Project(); // Create project object that will be stored in projectList
                    proj.setName(list.get(0));
                    boolean finalized = Boolean.parseBoolean(list.get(1));
                    proj.setIsFinalized(finalized);
                    proj.setType(list.get(2));
                    proj.setAddress(list.get(3));
                    proj.setErfNumber(list.get(4));
                    proj.setFee(Double.parseDouble(list.get(5)));
                    proj.setAmountPaid(Double.parseDouble(list.get(6)));
                    LocalDate dateObj = null;
                    DateTimeFormatter myFormatObj = null;
                    myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Format how date object will be
                    dateObj = LocalDate.parse(list.get(7), myFormatObj);
                    proj.setDate(dateObj); // Project date assigned
                    dateObj = LocalDate.parse(list.get(8), myFormatObj);
                    proj.setDeadline(dateObj); // Project deadline
                    proj.setCustomer(list.get(9), list.get(10), list.get(11), list.get(12), "customer");
                    proj.setArchitect(list.get(13), list.get(14), list.get(15), list.get(16), "architect");
                    proj.setContractor(list.get(17), list.get(18), list.get(19), list.get(20), "contractor");
                    projectList.add(proj); // Add project to the list of projects
                    proj.setNumber(++projNumber); // Increment number of projects
                    list.clear(); // List is cleared for next new project
                }
                count++;
            }
            for (Project obj : projectList) {

                displayExistingProjects(obj); // Display projects to the user
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.print("File not found");
        } catch (Exception e) {
            System.out.println("Error in " + inputFile.getName());
            System.out.println("Make sure the values in the file are comma separated plus a white space such as -> (\", \")");
        }
        goBacktoMenu();
    }

    /**
     * Returns user to main menu. 
     */
    static void goBacktoMenu() {
        System.out.print("\n*** Press enter to go back to main menu ***\n");
        try {
            System.in.read();
        } catch (Exception e) {
        }
    }

    /**
     * This block saves all the entered information pertaining to the project of all 
     * Relevant parties.
     *
     * @param proj contains all the user prompts for project
     */
    public static void displayExistingProjects(Project proj) {

        String content = " "; // This variable stores all information pertaining to the project
        content = "************** Project number: " + proj.getNumber() + " ************************\n\n";
        content += "Is project finalized ?: " + proj.isFinalized() + "\n";
        content += "Project name: " + proj.getName() + "\n";
        content += "Type of building: " + proj.getType() + "\n";
        content += "The physical address for the project: " + proj.getAddress() + "\n";
        content += "ERF number: " + proj.getErfNumber() + "\n";
        content += "The total fee charged for the project: " + proj.getFee() + "\n";
        content += "The total amount paid to date: " + proj.getAmountPaid() + "\n";
        DateTimeFormatter myFormatObj;
        myFormatObj = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        String formattedDate = proj.getDate().format(myFormatObj);
        content += "Project date assigned: " + formattedDate + "\n";
        formattedDate = proj.getDeadline().format(myFormatObj);
        content += "Project deadline: " + formattedDate + "\n";
        content += "\n";
        content += "Customer details:\n";
        content += "Name: " + proj.getCustomer().getName() + "\n";
        content += "Email: " + proj.getCustomer().getEmail() + "\n";
        content += "Telephone: " + proj.getCustomer().getPhone() + "\n";
        content += "address: " + proj.getCustomer().getAddress() + "\n";
        content += "\n";
        content += "Architect details:\n";
        content += "Name: " + proj.getArchitect().getName() + "\n";
        content += "Email: " + proj.getArchitect().getEmail() + "\n";
        content += "Telephone: " + proj.getArchitect().getPhone() + "\n";
        content += "address: " + proj.getArchitect().getAddress() + "\n";
        content += "\n";
        content += "Contractor details:\n";
        content += "Name: " + proj.getContractor().getName() + "\n";
        content += "Email: " + proj.getContractor().getEmail() + "\n";
        content += "Telephone: " + proj.getContractor().getPhone() + "\n";
        content += "address: " + proj.getContractor().getAddress() + "\n\n";

        System.out.print(content); // Display projects
    }

    /**
     * This block takes all project information and saves it to the text file specified.
     *
     * @param projectList access projectlist from array
     */
    public static void callSaveToFile(ArrayList<Project> projectList) {
        File inputFile = null;
        try {
            inputFile = new File("ExistingProjects.txt"); // specify file                                                                                                                              
            if (inputFile.createNewFile()) {
                saveToFile(projectList);
            } else {
                saveToFile(projectList);
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.out.print("Changes to projects saved to " + inputFile.getName());

        System.exit(0); // Terminate program
    }

    /**
     * This block writes the string to the text file
     *
     * @param projectList access projectlist from array
     * @throws IOException
     */
    static void saveToFile(ArrayList<Project> projectList) throws IOException {
        FileWriter fileWriter = new FileWriter("ExistingProjects.txt");
        String content = "";
        for (Project obj : projectList) {
            content = obj.getName() + ", ";
            content += obj.isFinalized() + ", ";
            content += obj.getType() + ", ";
            content += obj.getAddress() + ", ";
            content += obj.getErfNumber() + ", ";
            content += obj.getFee() + ", ";
            content += obj.getAmountPaid() + ", ";
            content += obj.getDate() + ", ";
            content += obj.getDeadline() + "\n";
            content += obj.getCustomer().getName() + ", ";
            content += obj.getCustomer().getEmail() + ", ";
            content += obj.getCustomer().getPhone() + ", ";
            content += obj.getCustomer().getAddress() + "\n";
            content += obj.getArchitect().getName() + ", ";
            content += obj.getArchitect().getEmail() + ", ";
            content += obj.getArchitect().getPhone() + ", ";
            content += obj.getArchitect().getAddress() + "\n";
            content += obj.getContractor().getName() + ", ";
            content += obj.getContractor().getEmail() + ", ";
            content += obj.getContractor().getPhone() + ", ";
            content += obj.getContractor().getAddress() + "\n";
            fileWriter.write(content);
        }
        fileWriter.close();
    }
}