package dao;

import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

public class Sql2oUserDao implements UserDao{
    private final Sql2o sql2o;

    public Sql2oUserDao(Sql2o sql2o) {

        this.sql2o = sql2o;
    }

    @Override
    public List<User> getAllUsers() {
        try (Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM users")
                    .executeAndFetch(User.class);
        }
    }

    @Override
    public void addUser(User user) {
        String sql = "INSERT INTO users (name, carId, parkingId) VALUES (:name, :carId, :parkingId);";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql, true)
                    .bind(user)
                    .executeUpdate()
                    .getKey();
            user.setId(id);

        }

    }

    @Override
    public int findUserById(int id) {
        String sql = "SELECT * FROM user WHERE id = :id";
        try (Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(User.class);

        }
        return id;
    }

    @Override
    public void updateUser(User user, int id, String name, int carId, int parkingId) {
        String sql = "UPDATE users SET (name, carId, parkingId) = :name, :carId, :parkingId";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("carId", carId)
                    .addParameter("parkingId", parkingId)
                    .executeUpdate();
            user.setName(name);
            user.setCarId(carId);
            user.setParkingId(parkingId);
        }
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void clearAll() {
        String sql = "DELETE FROM users";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .executeUpdate();
        }

    }
}
