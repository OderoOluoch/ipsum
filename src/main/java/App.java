import com.google.gson.Gson;
import dao.Sql2oParkingSlotDao;
import models.ParkingSlot;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import static spark.Spark.*;

public class App {

    public static void main(String[] args) {
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

//        get("/restaurants/:id", "application/json", (req, res) -> { //accept a request in format JSON from an app
//            res.type("application/json");
//            int slotId = Integer.parseInt(req.params("id"));
//            res.type("application/json");
//            return gson.toJson( parkingSlotDao.findById(slotId) );
//        });
    }
}
