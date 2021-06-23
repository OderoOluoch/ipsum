package dao;

import models.ParkingSlot;

import java.util.List;

public interface ParkingSlotDao {
    //create
    void add(ParkingSlot slot);

    //read
    List<ParkingSlot> getAll();
    List<ParkingSlot> getAllDepartmentsForNews(int id);

    //update
    //omit for now

    //delete
    void deleteById(int id);

    void clearAll();
}
