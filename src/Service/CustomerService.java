package Service;

import Model.Customer;

import java.util.Collection;
import java.util.*;
public class CustomerService {
    private String email;
    private String firstName;
    private String lastName;
    private static final CustomerService instance=new CustomerService();
    private final Map<String, Customer> customers = new HashMap<>();
    private CustomerService(){}
    public static CustomerService getInstance(){
        return instance;
    }

    public void addCustomer(String email,String firstname,String lastname) {
        Customer newcustomer=new Customer(firstname,lastname,email);
        customers.put(email,newcustomer);

    }
    public Customer getCustomer(String customerEmail){
        return customers.get(customerEmail);

    }
    public Collection<Customer> getAllCustomers() {
        return customers.values();

    }

}
