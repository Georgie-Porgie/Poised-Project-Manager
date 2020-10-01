import java.time.*;                        // import the time class     
import java.time.format.DateTimeFormatter; // import DateTimeFormatter
import java.io.File;                       // Import the File class
import java.io.FileWriter;                 // Import the FileWriter class
import java.io.IOException;                // Import the IOException class to handle errors

/**
 * Description of class Project
 * 
 * Project class has all the information pertaining to the respective projects such as amount paid, is the project complete and who are the 
 * parties involved.
 *
 * @author George
 * @version 1.02
 */
public class Project
{
   public Project()
   {
       project_number = 0;
       project_name = "";
       bulding_type = "";
       address = "";
       erf_number = "";
       project_fee = 0.0;
       amount_paid = 0.0;
       project_date_assigned = LocalDate.now();
       project_deadline = null;
       isFinalized = false;
       customer = null;
       architect = null;
       contractor = null;
       System.out.println();
   }
   
   /** Populate constructor Project class with the elements needed
    * 
    * @param pNum project number
    * @param pName project name
    * @param bType building type
    * @param add add new project
    * @param erf erf number
    * @param pFee fee owed
    * @param aPaid fee paid
    * @param pDealine is project complete
    */
   public Project(int pNum, String pName, String bType, String add, String erf, double pFee, double aPaid ,LocalDate pDealine)
   {
       setNumber(pNum);
       setName(pName);
       setType(bType);
       setAddress(add);
       setErfNumber(erf);
       setFee(pFee);
       setAmountPaid(aPaid);
       setDeadline(pDealine);
       isFinalized = false; // project when created is not finalized      
       project_date_assigned = LocalDate.now(); // current date is the date the project was created format(yyy-mm-dd)
   }
   
   /**
    *
    * @param projObj creates project object
    * @return finalise project
    */
   public void finaliseProject(Project projObj)
   {
       String invoice = "";
       if(projObj.getAmountPaid() < projObj.getFee())
       {
           invoice = "Name: " + projObj.getCustomer().getName() + "\n";
           invoice += "Email: " + projObj.getCustomer().getEmail() + "\n";
           invoice += "Telephone: " + projObj.getCustomer().getPhone() + "\n";
           invoice += "address: " + projObj.getCustomer().getAddress() + "\n";
           invoice += "Due amount: " + (projObj.getFee() - projObj.getAmountPaid()) + "\n";
           System.out.println("_________________Customer\'s invoice_____________________\n");
           System.out.println(invoice);
           System.out.println("_________________________________________________________");
       }
       projObj.setIsFinalized(true); // mark project as finalized
       LocalDateTime finalDate = LocalDateTime.now();
       DateTimeFormatter myDateFormat = DateTimeFormatter.ofPattern("E, MMM dd yyyy HH:mm:ss");
       String formattedDate = finalDate.format(myDateFormat); // store date finalized in a string variable
       String content = " "; // this variable will store all information pertaining to the project

       content = "Project number: " + projObj.getNumber() + "\n";
       content += "Is project finalized ?: " + projObj.isFinalized()+ "\n";
       content += "Project name: " + projObj.getName() + "\n";
       content += "Type of building: " + projObj.getType() + "\n";
       content += "The physical address for the project: " + projObj.getAddress() + "\n";
       content += "ERF number: " + projObj.getErfNumber() + "\n";
       content += "The total fee charged for the project: " + projObj.getFee() + "\n";
       content += "The total amount paid to date: " + projObj.getAmountPaid() + "\n";
       content += "Project date assigned: " + projObj.getDate() + "\n";
       content += "Project deadline: " + projObj.getDeadline() + "\n";
       content += "Project date finalized: " + formattedDate + "\n";
       content += "\n\n";
       content += "Customer\'s details:\n";
       content += "Name: " + projObj.getCustomer().getName() + "\n";
       content += "Email: " + projObj.getCustomer().getEmail() + "\n";
       content += "Telephone: " + projObj.getCustomer().getPhone() + "\n";
       content += "address: " + projObj.getCustomer().getAddress() + "\n";
       content += "\n\n";
       content += "Architect\'s details:\n";
       content += "Name: " + projObj.getArchitect().getName() + "\n";
       content += "Email: " + projObj.getArchitect().getEmail() + "\n";
       content += "Telephone: " + projObj.getArchitect().getPhone() + "\n";
       content += "address: " + projObj.getArchitect().getAddress() + "\n";
       content += "\n\n";
       content += "Contractor\'s details:\n";
       content += "Name: " + projObj.getContractor().getName() + "\n";
       content += "Email: " + projObj.getContractor().getEmail() + "\n";
       content += "Telephone: " + projObj.getContractor().getPhone() + "\n";
       content += "address: " + projObj.getContractor().getAddress();

       try
       {
           File oFile = new File("Completed project" + projObj.getNumber() + ".txt"); // specify file
           if (oFile.createNewFile())
           { // if file was successfully created
                 FileWriter myWriter = new FileWriter(oFile); // fileWriter object
                 myWriter.write(content); // write information about the project to file
                 myWriter.close(); // close file after finish writing  
                 System.out.println("***Project information saved to: " + oFile.getName() + "***");      
              }
              else

              {
                  String newFile = "Completed project" + projObj.getNumber() + ".txt";
                  File oFile2 = new File(newFile); // specify file
                  FileWriter myWriter = new FileWriter(oFile2);
                  myWriter.write(content); // write information about the project to file
                  myWriter.close(); // close file after finish writing
                  // let user know of output
                  System.out.println("***Project information saved to: " + oFile2.getName() + "***");
              }
            }
           catch (IOException e)

           {
               System.out.println("**An error occurred**");
                e.printStackTrace();
            }  
   }
   public void setCustomer(String name, String phone, String email, String add, String r)
   {
       customer = new Person(name, phone, email, add, r); // create customer object and assign data
   }
   public void setArchitect(String name, String phone, String email, String add, String r)
   {
       architect = new Person(name, phone, email, add, r); // create architect object and assign data
   }
   
   /**
    * Block compiles all information of the contractor
    *
    * @param name Contractor name
    * @param phone Contractor phone number
    * @param email Contractor email address
    * @param add Contractor details to string
    * @param r Contractor variable
    * @return setContractor
    */
   public void setContractor(String name, String phone, String email, String add, String r)
   {  
       contractor = new Person(name, phone, email, add, r); // create contractor object and assign data
   }
   
   /**
    * Block stores project_name variable
    *
    * @param n project_name
    * @return 
    */
   public void setName(String n)
   {
       project_name = n;
   }
   
   /**
    * Block stores project_number variable
    *
    * @param n project_number
    * @return setNumber
    */
   public void setNumber(int n)
   {
       project_number = n;
   }
   
   /**
    * Block stores building_type variable
    *
    * @param t building_type
    * @return setType
    */
   public void setType(String t)
   {
       bulding_type = t;
   }
   
   /**
    * Block stores address variable
    *
    * @param a address
    * @return setAddress
    */
   public void setAddress(String a)
   {
       address = a;
   }
   
   /**
    * Block stores erf_number variable
    *
    * @param n erf_number
    * @return setErfNumber
    */
   public void setErfNumber(String n)
   {
       erf_number = n;
   }
   
   /**
    * Block stores project_fee
    *
    * @param f project_fee
    * @return setFee
    */
   public void setFee(double f)
   {
       project_fee = f;
   }
   
   /**
    * Block stores amount_paid variable
    *
    * @param a amount_paid
    * @return setAmountPaid
    */
   public void setAmountPaid(double a)
   {
       amount_paid = a;
   }
   
   /**
    * Block stores project_deadline variable
    *
    * @param d project_deadline
    * @return setDeadline
    */
   public void setDeadline(LocalDate d)
   {
       project_deadline = d;
   }
   
   /**
    * Block stores project_date_assigned variable
    *
    * @param d project_date_assigned
    * @return setDate
    */
   public void setDate(LocalDate d)
   {
       project_date_assigned = d;
   }
   
   /**
    * Block stores information in isFinalized 
    *
    * @param s isFinalized
    * @return setIsFinalized
    */
   public void setIsFinalized(boolean s)
   {
       isFinalized = s;
   }
   public String getName()
   {
       return project_name;
   }
   public int getNumber()
   {
       return project_number;
   }
   public String getType()
   {
       return bulding_type;
   }
   public String getAddress()
   {
       return address;
   }
   public String getErfNumber()
   {
       return erf_number;
   }
   public double getFee()
   {
       return project_fee;
   }
   public double getAmountPaid()
   {
       return amount_paid;
   }
   public LocalDate getDeadline()
   {
       return project_deadline;
   }
   public LocalDate getDate()
   {
       return project_date_assigned;
   }
   public boolean isFinalized()
   {
       return isFinalized;
   }
   public Person getCustomer()
   {
       return customer;
   }
   public Person getArchitect()
   {
       return architect;
   }
   public Person getContractor()
   {
       return contractor;
   }
   private int project_number;
   private String project_name;
   private String bulding_type;
   private String address;
   private String erf_number;
   private double project_fee;
   private double amount_paid;
   private LocalDate project_date_assigned;
   private LocalDate project_deadline;
   private boolean isFinalized;
   private Person customer;
   private Person architect;
   private Person contractor;
  
}