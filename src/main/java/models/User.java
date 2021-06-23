package models;

public class User {
    private int id;
    private String name;
    private int carId;
    private int parkingSlotId;

    public User(int id, String name, int carId, int parkingSlotId){
        this.id = id;
        this.name = name;
        this.carId = carId;
        this.parkingSlotId = parkingSlotId;
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public int getCarId(){
        return carId;
    }
    public void setCarId(int carId){
        this.carId = carId;
    }

    public int getParkingSlotId(){
        return parkingSlotId;
    }
    public void setParkingSlotId(int parkingSlotId){
        this.parkingSlotId = parkingSlotId;
    }
}
