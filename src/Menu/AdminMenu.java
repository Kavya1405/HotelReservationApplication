package Menu;



import api.AdminResource;
import Model.*;
import java.util.*;

public class AdminMenu {
    private static final AdminResource adminResource = AdminResource.getInstance();
    private static final Scanner scanner = new Scanner(System.in);

    public static void showAdminMenu() {
        boolean back = false;

        while (!back) {
            System.out.println("\n=== ADMIN MENU ===");
            System.out.println("1. See all Customers");
            System.out.println("2. See all Rooms");
            System.out.println("3. See all Reservations");
            System.out.println("4. Add a Room");
            System.out.println("5. Back to Main Menu");
            System.out.print("Select an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    seeAllCustomers();
                    break;
                case "2":
                    seeAllRooms();
                    break;
                case "3":
                    adminResource.displayAllReservations();
                    break;
                case "4":
                    addRoom();
                    break;
                case "5":
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option. Please choose 1–5.");
            }
        }
    }

    private static void seeAllCustomers() {
        Collection<Customer> customers = adminResource.getAllCustomers();
        if (customers.isEmpty()) {
            System.out.println("No customers found.");
        } else {
            customers.forEach(System.out::println);
        }
    }

    private static void seeAllRooms() {
        Collection<IRoom> rooms = adminResource.getAllRooms();
        if (rooms.isEmpty()) {
            System.out.println("No rooms available.");
        } else {
            rooms.forEach(System.out::println);
        }
    }

    private static void addRoom() {
        List<IRoom> newRooms = new ArrayList<>();

        boolean addMore = true;
        while (addMore) {
            System.out.print("Enter room number: ");
            String roomNumber = scanner.nextLine();

            System.out.print("Enter price per night: ");
            Double price = Double.parseDouble(scanner.nextLine());

            System.out.print("Enter room type (1 for SINGLE, 2 for DOUBLE): ");
            int type = Integer.parseInt(scanner.nextLine());
            RoomType roomType = (type == 1) ? RoomType.SINGLE : RoomType.DOUBLE;

            IRoom room = new Room(roomNumber, price, roomType);
            newRooms.add(room);

            System.out.print("Add another room? (y/n): ");
            addMore = scanner.nextLine().equalsIgnoreCase("y");
        }

        adminResource.addRoom(newRooms);
        System.out.println("Rooms added successfully!");
    }
}
