package models;

public class Car {
    private String car_name;
    private int car_id;
    private  int parking_slot_id;


public  Car(String car_name, int car_id, int parking_slot_id){
    this.car_name = car_name;
    this.car_id = car_id;
    this.parking_slot_id = parking_slot_id;
}

    public String getCar_name() {
        return car_name;
    }

    public int getCar_id() {
        return car_id;
    }

    public int getParking_slot_id() {
        return parking_slot_id;
    }

    public void setCar_name(String car_name) {
        this.car_name = car_name;
    }

    public void setCar_id(int car_id) {
        this.car_id = car_id;
    }

    public void setParking_slot_id(int parking_slot_id) {
        this.parking_slot_id = parking_slot_id;
    }
}
