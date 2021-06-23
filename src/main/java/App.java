import com.google.gson.Gson;
<<<<<<< HEAD
import dao.CarDao;
import dao.Sql2oCarDao;
import exception.ApiException;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import models.Car;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import static spark.Spark.*;


=======
import dao.Sql2oParkingSlotDao;
import models.ParkingSlot;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import static spark.Spark.*;

>>>>>>> 24461736554a375f46d22231012b2f107203ef25
public class App {


    public static void main(String[] args) {
<<<<<<< HEAD
        Sql2oCarDao sql20CarDao;
        Connection conn;
        Gson gson = new Gson();

        String connectionString = "jdbc:postgresql://localhost:5432/car_park";
        Sql2o sql2o = new Sql2o(connectionString, "moringa", "Access");

        sql20CarDao = new Sql2oCarDao (sql2o) {

        };
        conn = sql2o.open();

        post("/cars/new", "application/json", (req, res)->{
            Car car = gson.fromJson(req.body(), Car.class);
            if(car == null || car.getCar_name() == null){
                throw new ApiException(404, String.format("cannotBeEmptyMsg","Car"));
            }
            sql20CarDao.add(car);
            res.type("application/json");
            res.status(201);
            return gson.toJson(car);
        });

        get("/users", "application/json",(req, res) -> {
            if(sql20CarDao.getAll().size() < 1){
                throw new ApiException(404, String.format("notAvailableMsg","Cars"));
            }
            res.type("application/json");
            return gson.toJson(sql20CarDao.getAll());
        });

=======
        Sql2oParkingSlotDao parkingSlotDao;

        Connection conn;
        Gson gson = new Gson();

        String connectionString = "jdbc:h2:~/jadle.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");

        parkingSlotDao = new Sql2oParkingSlotDao(sql2o);
        conn = sql2o.open();

        post("/slots/new", "application/json", (req, res) -> {
            ParkingSlot slot = gson.fromJson(req.body(), ParkingSlot.class);
            parkingSlotDao.add(slot);
            res.status(201);
            res.type("application/json");
            return gson.toJson(slot);
        });

        get("/restaurants", "application/json", (req, res) -> { //accept a request in format JSON from an app
            res.type("application/json");
            return gson.toJson(parkingSlotDao.getAll());//send it back to be displayed
        });
>>>>>>> 24461736554a375f46d22231012b2f107203ef25

//        get("/restaurants/:id", "application/json", (req, res) -> { //accept a request in format JSON from an app
//            res.type("application/json");
//            int slotId = Integer.parseInt(req.params("id"));
//            res.type("application/json");
//            return gson.toJson( parkingSlotDao.findById(slotId) );
//        });
    }
}
