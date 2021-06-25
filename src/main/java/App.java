import com.google.gson.Gson;
import dao.Sql2oCarDao;
import dao.Sql2oParkingSlotDao;
import exception.ApiException;
import models.Car;
import dao.Sql2oUserDao;
import models.ParkingSlot;
import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static spark.Spark.get;
import static spark.Spark.post;

public class App {
    Sql2oParkingSlotDao parkingSlotDao;
    static Sql2oUserDao userDao;

        Gson gson = new Gson ( );


        public static void main (String[]args) {

            String connectionString = "jdbc:h2:~/jadle.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
            Sql2o sql2o = new Sql2o (connectionString, "", "");
//
//        String connectionString = "jdbc:postgresql://localhost:5432/car_park";
//        Sql2o sql2o = new Sql2o(connectionString, "moringa", "Access");

            Sql2oCarDao sql20CarDao;
            Gson gson = new Gson ( );
            Connection conn;
            Sql2oParkingSlotDao parkingSlotDao;
            parkingSlotDao = new Sql2oParkingSlotDao (sql2o);
            userDao = new Sql2oUserDao (sql2o);
            conn = sql2o.open ( );


            sql20CarDao = new Sql2oCarDao (sql2o) {

            };
            conn = sql2o.open ( );

            post ("/cars/new", "application/json", (req, res) -> {
                Car car = gson.fromJson (req.body ( ), Car.class);
                if (car == null || car.getCar_name ( ) == null) {
                    throw new ApiException (404, String.format ("cannotBeEmptyMsg", "Car"));
                }
                sql20CarDao.add (car);
                res.type ("application/json");
                res.status (201);
                return gson.toJson (car);
            });

            get ("/cars", "application/json", (req, res) -> {
                if (sql20CarDao.getAll ( ).size ( ) < 1) {
                    throw new ApiException (404, String.format ("notAvailableMsg", "Cars"));
                }
                res.type ("application/json");
                return gson.toJson (sql20CarDao.getAll ( ));
            });


            post ("/slots/new", "application/json", (req, res) -> {
                ParkingSlot slot = gson.fromJson (req.body ( ), ParkingSlot.class);
                parkingSlotDao.add (slot);
                res.status (201);
                res.type ("application/json");
                return gson.toJson (slot);
            });
            post ("/users/new", "application/json", (request, response) -> {
                User user = gson.fromJson (request.body ( ), User.class);
                userDao.add (user);
                response.status (201);
                return gson.toJson (user);
            });

            get ("/users/:id", "application/json", ((request, response) -> {
                int userId = Integer.parseInt (request.params ("id"));
                return gson.toJson (userDao.findUserById (userId));
            }));

            get ("/slots", "application/json", (req, res) -> { //accept a request in format JSON from an app
                res.type ("application/json");
                return gson.toJson (parkingSlotDao.getAll ( ));//send it back to be displayed
            });


            get ("/slots/:id", "application/json", (req, res) -> { //accept a request in format JSON from an app
                res.type ("application/json");
                int slotId = Integer.parseInt (req.params ("id"));
                res.type ("application/json");
                return gson.toJson (parkingSlotDao.findById (slotId));
            });

            get ("/slots/:id/cars", (req, res) -> {
                int slotId = Integer.parseInt (req.params ("id"));
                return gson.toJson (parkingSlotDao.getAllParkingSlotsForCars (slotId));
            });
        }
    }
