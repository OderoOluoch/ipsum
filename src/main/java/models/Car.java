package models;

import java.util.Objects;

public class Car {
    private String car_name;
    private int car_id;
    private  int parking_slot_id;
    private int id;


    public  Car(String car_name, int car_id, int parking_slot_id){
    this.car_name = car_name;
    this.car_id = car_id;
    this.parking_slot_id = parking_slot_id;
}

//Getters
    public String getCar_name() {
        return car_name;
    }

    public int getCar_id() {
        return car_id;
    }

    public int getParking_slot_id() {
        return parking_slot_id;
    }


    //setters

    public void setCar_name(String car_name) {
        this.car_name = car_name;
    }

    public void setCar_id(int car_id) {
        this.car_id = car_id;
    }

    public void setParking_slot_id(int parking_slot_id) {
        this.parking_slot_id = parking_slot_id;
    }

    //equals & hashCodes


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;
        Car car = (Car) o;
        return car_id == car.car_id &&
                parking_slot_id == car.parking_slot_id &&
                Objects.equals(car_name, car.car_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(car_name, car_id, parking_slot_id);
    }

    public void setId(int id) {
    this.id = id;
    }


}
