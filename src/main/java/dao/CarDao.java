package dao;
import models.Car;
import java.util.List;

public interface CarDao {
    void add (Car car);

    List<Car> getAll();
//    List<Car>getAllCarsByP

    void  deleteById(int id);
    void  clearAll();

}
