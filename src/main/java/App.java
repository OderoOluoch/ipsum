import com.google.gson.Gson;
import dao.Sql2oCarDao;
import dao.Sql2oParkingSlotDao;
import exception.ApiException;
import models.Car;
import models.ParkingSlot;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class App {
    Sql2oParkingSlotDao parkingSlotDao;


    Gson gson = new Gson();


    public static void main(String[] args) {

//        String connectionString = "jdbc:h2:~/jadle.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
//        Sql2o sql2o = new Sql2o(connectionString, "", "");
//
        String connectionString = "jdbc:postgresql://localhost:5432/car_park";
        Sql2o sql2o = new Sql2o(connectionString, "moringa", "Access");

        Sql2oCarDao sql20CarDao;
        Gson gson = new Gson();
        Connection conn;
        Sql2oParkingSlotDao parkingSlotDao;
        parkingSlotDao = new Sql2oParkingSlotDao(sql2o);
        conn = sql2o.open();




        sql20CarDao = new Sql2oCarDao (sql2o) {

        };
        conn = sql2o.open();



        get("/cars/new",(request, response) ->  {
            Map<String,Object> model = new HashMap<>();
            return new ModelAndView(model,"car-form.hbs");

        },new HandlebarsTemplateEngine());


        post ("/cars/new",(request, response) -> {
            Map<String,Object>model = new HashMap<String, Object>();
            String car_name =request.queryParams("car_name");
            int owner_id= Integer.parseInt(request.queryParams("owner_id"));
//            String power= request.queryParams("power");
            int parking_slot_id = Integer.parseInt(request.queryParams("parking_slot_id"));
            Car newIdentity =new Car("Bugati",78906,768);
            model.put("car",newIdentity);
            return new ModelAndView(model,"index.hbs");
        }, new HandlebarsTemplateEngine());


        get("/",(request, response) -> {
            Map<String,Object> model = new HashMap<String, Object>();
            ArrayList<Car> cars = Car.getAll();
//            ArrayList<Squad>squads =Squad.getAll();
            model.put("cars",cars);
//            model.put("squads",squads);
            return new ModelAndView(model,"success.hbs");

        }, new HandlebarsTemplateEngine());










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

        get("/cars", "application/json",(req, res) -> {
            if(sql20CarDao.getAll().size() < 1){
                throw new ApiException(404, String.format("notAvailableMsg","Cars"));
            }
            res.type("application/json");
            return gson.toJson(sql20CarDao.getAll());
        });



        post("/slots/new", "application/json", (req, res) -> {
            ParkingSlot slot = gson.fromJson(req.body(), ParkingSlot.class);
            parkingSlotDao.add(slot);
            res.status(201);
            res.type("application/json");
            return gson.toJson(slot);
        });

        get("/slots", "application/json", (req, res) -> { //accept a request in format JSON from an app
            res.type("application/json");
            return gson.toJson(parkingSlotDao.getAll());//send it back to be displayed
        });


//        get("/slots/:id", "application/json", (req, res) -> { //accept a request in format JSON from an app
//            res.type("application/json");
//            int slotId = Integer.parseInt(req.params("id"));
//            res.type("application/json");
//            return gson.toJson( parkingSlotDao.findById(slotId) );
//        });
    }
}
