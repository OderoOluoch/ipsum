package models;

public class User {
    private int id;
    private String name;
    private int carId;
    private int parkingId;

    public User(int id, String name, int carId, int parkingId){
        this.id = id;
        this.name = name;
        this.carId = carId;
        this.parkingId = parkingId;
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

    public int getParkingId(){
        return parkingId;
    }
    public void setParkingId(int parkingId){
        this.parkingId = parkingId;
    }
}
