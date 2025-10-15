package Menu;

import api.HotelResource;
import Model.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MainMenu {
    private static final HotelResource hotelResource = HotelResource.getInstance();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        showMainMenu();
    }

    public static void showMainMenu() {
        boolean exit = false;

        while (!exit) {
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1. Find and reserve a room");
            System.out.println("2. See my reservations");
            System.out.println("3. Create an account");
            System.out.println("4. Admin");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    findAndReserveRoom();
                    break;
                case "2":
                    seeMyReservations();
                    break;
                case "3":
                    createAccount();
                    break;
                case "4":
                    AdminMenu.showAdminMenu();
                    break;
                case "5":
                    System.out.println("Thank you for using the Hotel Reservation App!");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please choose 1–5.");
            }
        }
    }

    private static void findAndReserveRoom() {
        try {
            System.out.print("Enter Check-In Date (dd/MM/yyyy): ");
            Date checkIn = new SimpleDateFormat("dd/MM/yyyy").parse(scanner.nextLine());

            System.out.print("Enter Check-Out Date (dd/MM/yyyy): ");
            Date checkOut = new SimpleDateFormat("dd/MM/yyyy").parse(scanner.nextLine());

            Collection<IRoom> availableRooms = hotelResource.findARoom(checkIn, checkOut);

            if (availableRooms.isEmpty()) {
                System.out.println("No rooms available for those dates.");
                return;
            }

            System.out.println("Available Rooms:");
            for (IRoom room : availableRooms) {
                System.out.println(room);
            }

            System.out.print("Do you have an account? (y/n): ");
            String hasAccount = scanner.nextLine();

            if (hasAccount.equalsIgnoreCase("y")) {
                System.out.print("Enter your email: ");
                String email = scanner.nextLine();

                Customer customer = hotelResource.getCustomer(email);
                if (customer == null) {
                    System.out.println("No account found. Please create one first.");
                    return;
                }

                System.out.print("Enter room number to reserve: ");
                String roomNumber = scanner.nextLine();
                IRoom room = hotelResource.getRoom(roomNumber);

                if (room == null) {
                    System.out.println("Room not found.");
                    return;
                }

                Reservation reservation = hotelResource.bookARoom(email, room, checkIn, checkOut);
                System.out.println("Reservation successful!");
                System.out.println(reservation);
            } else {
                System.out.println("Please create an account first.");
            }
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use dd/MM/yyyy.");
        }
    }

    private static void seeMyReservations() {
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        Collection<Reservation> reservations = hotelResource.getCustomersReservations(email);

        if (reservations == null || reservations.isEmpty()) {
            System.out.println("No reservations found.");
        } else {
            reservations.forEach(System.out::println);
        }
    }

    private static void createAccount() {
        System.out.print("Enter Email (format: name@domain.com): ");
        String email = scanner.nextLine();
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();

        try {
            hotelResource.createACustomer(email, firstName, lastName);
            System.out.println("Account created successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
