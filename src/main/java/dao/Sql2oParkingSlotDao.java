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
        String sql = "INSERT INTO slots (name, carId) VALUES (:name , :carId)"; //if you change your model, be sure to update here as well!
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
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM slots")
                    .executeAndFetch(ParkingSlot.class);
        }
    }

    @Override
    public List<ParkingSlot> getAllParkingSlotsForCars(int id) {

        return null;
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from slots WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void clearAll() {
        String sql = "DELETE from slots";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql).executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    public int findById(int slotId) {
        String sql = "SELECT * FROM slots WHERE id = :id";
        try (Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("id", slotId)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(ParkingSlot.class);

        }
        return slotId;
    }

}
