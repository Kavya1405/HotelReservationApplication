package Model;

import java.util.regex.Pattern;

public class Customer {
    private String firstName;
    private String lastName;
    private String email;
    public Customer(String firstName, String lastName, String email) {
        String emailregex="^(.+)@(.+)\\.(.+)$";

        if(!Pattern.matches(emailregex,email)){
            throw new IllegalArgumentException("Invalid email format"+email);
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getEmail() {
        return email;
    }
    public String toString(){
        return "first name is"+firstName+",last name is"+lastName+",email is"+email;
    }
}
