package Model;

public class Room implements IRoom{
    public String getRoomNumber(){
        return roomNumber;

    }
    public Double getRoomPrice(){
        return price;

    }

    @Override
    public RoomType getRoomType() {
        return roomtype;

    }
    public boolean isFree(){
        return price==0.0;

    }
    private String roomNumber;
    private Double price;
    private RoomType roomtype;
    public Room(String roomNumber, Double price, RoomType roomtype){
        this.roomNumber = roomNumber;
        this.price = price;
        this.roomtype = roomtype;
    }
    public String toString() {
        return "Room number is: "+roomNumber+"Room Price is: "+price+"Room Type is: "+roomtype;
    }
}
