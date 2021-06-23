package dao;

import models.ParkingSlot;

import java.util.List;

public interface ParkingSlotDao {
    //create
    void add(ParkingSlot slot);

    //read
    List<ParkingSlot> getAll();
    //public void findById(int id);
    List<ParkingSlot> getAllParkingSlotsForCars(int id);

    //update
    //omit for now

    //delete
    void deleteById(int id);

    void clearAll();
}
