package Service;
import Model.*;
import Model.IRoom;
import Model.Reservation;

import java.util.Collection;
import java.util.Date;
import java.util.*;
import java.util.stream.*;

public class ReservationService {
    private static final ReservationService instance=new ReservationService();


    private final Map<String, IRoom> rooms = new HashMap<>();
    private final Set<Reservation> reservations = new HashSet<>();
    private ReservationService() {}
    public static ReservationService getInstance() {
        return instance;
    }
    public void addRoom(IRoom room) {
        rooms.put(room.getRoomNumber(), room);

    }
    public IRoom getARoom(String roomId) {
        return rooms.get(roomId);

    }

    public Reservation reserveAtRoom(Customer customer,IRoom room,Date checkindate,Date checkoutDate){
        Reservation newreservation=new Reservation(customer,room,checkindate,checkoutDate);
        reservations.add(newreservation);
        return newreservation;

    }
    public Collection<IRoom> findRooms(Date checkindate,Date checkoutDate) {
        return rooms.values();

    }
    public Collection<Reservation> getCustomersReservations(Customer customer) {
        List<Reservation> result=new ArrayList<>();
        for(Reservation reservation : reservations) {
            if(reservation.toString().contains(customer.getEmail())){
                result.add(reservation);
            }
        }
        return result;

    }
    public void printAllReservations() {
        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
            return;
        }
        for (Reservation reservation : reservations) {
            System.out.println(reservation);

        }

    }
}
