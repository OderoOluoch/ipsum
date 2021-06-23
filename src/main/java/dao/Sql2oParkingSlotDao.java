package dao;

import models.ParkingSlot;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oParkingSlotDao implements ParkingSlotDao{
    private final Sql2o sql2o;
    public Sql2oParkingSlotDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }


    @Override
    public void add(ParkingSlot slot) {
        String sql = "INSERT INTO users (name, deptId) VALUES (:name , :deptId)"; //if you change your model, be sure to update here as well!
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(slot)
                    .executeUpdate()
                    .getKey();
            slot.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public List<ParkingSlot> getAll() {
        return null;
    }

    @Override
    public List<ParkingSlot> getAllDepartmentsForNews(int id) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void clearAll() {

    }
}
