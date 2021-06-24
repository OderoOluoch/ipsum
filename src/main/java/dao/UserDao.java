package dao;

import models.User;

import java.util.List;

public interface UserDao {

    List<User> getAllUsers();
    void addUser(User user);
    int findUserById(int id);

    void updateUser(User user, int id, String name, int carId, int parkingSlotId);

    void deleteById(int id);
    void clearAll();

}
