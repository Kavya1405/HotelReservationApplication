import Model.Customer;

public class Driver {
    public static void main(String[] args){
        Customer cust=new Customer("Kavya",
                "Bejawada",
                "kavya@gmail.com");
        System.out.println(cust);
        Customer cust1_invalid=new Customer("Kavya","Bejawada","Kavya");

        System.out.println(cust1_invalid);


    }
}
