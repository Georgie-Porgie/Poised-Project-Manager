/**
 * Description of class Person
 * 
 * Person contains all the names, addresses, phone numbers and people involved in the project. 
 *
 * @author George
 * @version 1.02
 */
public class Person
{
   public Person()
   {
       name = "";
       telephone = "";
       email = "";
       address = "";
       role_in_project = "";
   }
   
   /** 
    * This block adds elements to class Person
    * @param name set customer name 
    * @param phone set phone number
    * @param email set email address
    * @param address set physical address
    * @param role set role i.e architect, contractor or customer
    */
   public Person(String name, String phone, String email, String address, String role)
   {
       // call setter functions
       setName(name);
       setPhone(phone);
       setEmail(email);
       setAddress(address);
       setRole(role);  
   }
   
   /** Block sets name
    *
    * @param n is customer name variable
    * @return n
    */
   public void setName(String n)
   {
       name = n;
   }
   
   /** Block sets phone number varaible
    *
    * @param n 
    * @return n
    */
   public void setPhone(String n)
   {
       telephone = n;
   }
   
   /** Block sets email address variable
    *
    * @param e
    * @return e
    */
   public void setEmail(String e)
   {
       email = e;
   }
   
   /** Block sets physical address variable
    *
    * @param a
    * @return a
    */
   public void setAddress(String a)
   {
       address = a;
   }
   
   /** Block defines persons's role in project
    *
    * @param r string is either architect, customer or contractor
    * @return r
    */
   public void setRole(String r)
   {
       role_in_project = r;  
   }
   public String getName()
   {
       return name;
   }
   public String getPhone()
   {
       return telephone;
   }
   public String getEmail()
   {
       return email;
   }
   public String getAddress()
   {
       return address;
   }
   public String getRole()
   {
       return role_in_project;
   }
   private String name;
   private String telephone;
   private String email;
   private String address;
   private String role_in_project; 
}