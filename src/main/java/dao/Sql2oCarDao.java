package dao;

import models.Car;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oCarDao implements CarDao {

private  final Sql2o sql2o;

    public Sql2oCarDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }



    @Override
    public void add(Car car) {
        String sql = "INSERT INTO cars ( Car_name, Car_id,parking_slot_id)";
        try (Connection conn = sql2o.open()) {
            int id = (int) conn.createQuery(sql, true)
                    .bind(car)
                    .executeUpdate()
                    .getKey();
            car.setId(id);
        }catch (Sql2oException ex){
            System.out.println(ex);

        }

    }

    @Override
    public List<Car> getAll() {
        try(Connection con =sql2o.open()){
            return  con.createQuery("SELECT * FROM cars")
                 .executeAndFetch(Car.class);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE  from cars WHERE id =:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }catch ( Sql2oException ex) {
            System.out.println(ex);

        }

    }

    @Override
    public void clearAll() {
        String sql = "DELETE from cars";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql).executeUpdate();
        }catch (Sql2oException ex){
            System.out.println();

        }

    }
}
