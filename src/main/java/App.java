import com.google.gson.Gson;
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


public class App {


    public static void main(String[] args) {
        Sql2oCarDao sql20CarDao;
        Connection conn;
        Gson gson = new Gson();

        String connectionString = "jdbc:postgresql://localhost:5432/news_potal";
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


    }
}
