package Model;

public class FreeRoom extends Room {
    public FreeRoom(String roomNumber, Double price, RoomType roomtype) {
        super(roomNumber,0.0, roomtype);



    }
    public String toString(){
        return super.toString() + "(Free Room)";

    }

}
