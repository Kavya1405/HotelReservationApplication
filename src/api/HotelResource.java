package api;
import Model.Customer;
import Model.Reservation;

import Model.*;
import Service.*;

import java.util.*;
public class HotelResource {
    //private Map<String,Customer> customers=new HashMap<>();
    //private Set<Reservation> reservations=new HashSet<>();
    private static final HotelResource instance=new HotelResource();
    private final CustomerService customerService=CustomerService.getInstance();
    private final ReservationService reservationService=ReservationService.getInstance();
    private HotelResource() {}
    public static HotelResource getInstance() {
        return instance;
    }
    public void createACustomer(String email,String firstName,String lastName){
        customerService.addCustomer(email,firstName,lastName);
    }
    public Customer getCustomer(String email){
        return customerService.getCustomer(email);

    }
    public Reservation bookARoom(String customerEmail,IRoom room,Date checkInDate,Date checkOutDate){
        Customer customer=getCustomer(customerEmail);
        return reservationService.reserveAtRoom(customer,room,checkInDate,checkOutDate);
    }
    public IRoom getRoom(String roomNumber){
        return reservationService.getARoom(roomNumber);

    }
    public Collection<Reservation> getCustomersReservations(String  customerEmail){
        Customer customer=getCustomer(customerEmail);
        return reservationService.getCustomersReservations(customer);

    }
    public Collection<IRoom> findARoom(Date checkIn,Date checkOut){
        return reservationService.findRooms(checkIn,checkOut);

    }

}
