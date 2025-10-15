package api;


import Model.*;
import Service.*;
import java.util.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminResource {
    private static final AdminResource instance=new AdminResource();
    private final CustomerService customerService=CustomerService.getInstance();
    private final ReservationService reservationService=ReservationService.getInstance();
    private AdminResource(){}
    private final HotelResource hotelService = HotelResource.getInstance();


    // Public method to retrieve the single instance
    public static AdminResource getInstance() {

        return instance;
    }

    public Customer getCustomer(String email){
        return customerService.getCustomer(email);

    }
    public void addRoom(List<IRoom> rooms){
        for(IRoom room:rooms){
            reservationService.addRoom(room);
        }

    }
    public Collection <IRoom> getAllRooms(){
        return reservationService.findRooms(null,null);

    }
    public Collection <Customer> getAllCustomers(){
        return customerService.getAllCustomers();

    }
    public void displayAllReservations(){
        reservationService.printAllReservations();

    }

}
