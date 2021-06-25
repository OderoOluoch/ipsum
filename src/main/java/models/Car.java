package models;

import java.util.ArrayList;
import java.util.Objects;

public class Car {
    private static ArrayList<Car> AllCars;
    private String car_name;
    private int owner_id;
    private  int parking_slot_id;
    private int id;


    public  Car(String car_name, int owner_id, int parking_slot_id){
    this.car_name = car_name;
    this.owner_id = owner_id;
    this.parking_slot_id = parking_slot_id;
}

    public static ArrayList<Car> getAll() {
        return AllCars;
    }

    //Getters
    public String getCar_name() {
        return car_name;
    }

    public int getCar_id() {
        return owner_id;
    }

    public int getParking_slot_id() {
        return parking_slot_id;
    }


    //setters

    public void setCar_name(String car_name) {
        this.car_name = car_name;
    }

    public void setCar_id(int car_id) {
        this.owner_id = owner_id;
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
        return owner_id== car.owner_id &&
                parking_slot_id == car.parking_slot_id &&
                Objects.equals(car_name, car.car_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(car_name, owner_id, parking_slot_id);
    }

    public void setId(int id) {
    this.id = id;
    }


}
