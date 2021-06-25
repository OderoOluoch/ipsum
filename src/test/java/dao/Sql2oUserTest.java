package dao;

import models.ParkingSlot;
import models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.assertEquals;

public class Sql2oUserTest {
    Sql2oUserDao userDao;
    private Connection conn;
    Sql2oParkingSlotDao parkingSlotDao;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        userDao = new Sql2oUserDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }
    @Test
    public void addingUserSetsId() throws Exception{
        User user = setupUser();
        assertEquals(1, user.getId());
    }
    @Test
    public void getAll() throws Exception{
        User user1 = setupUser();
        User user2 = setupUser();
        assertEquals(2, userDao.getAllUsers().size());
    }
    @Test
    public void getAllUsersByParkingSlot(){
        ParkingSlot parking = setupParking();
        ParkingSlot parking2 = setupParking();

        User user1 = setupUserForParkingSlot(parking);
        User user2 = setupUserForParkingSlot(parking);
        User userForParking2 = setupUserForParkingSlot(parking2);

//        assertEquals(2, userDao.getAllUsersByParkingSlots(parking.getId()).size());
    }
    @Test
    public void deleteById() throws Exception{
        User user = setupUser();
        User user2 = setupUser();
        assertEquals(2,userDao.getAllUsers().size());
        userDao.deleteById(user.getId());
        assertEquals(1,userDao.getAllUsers().size());
    }

    @Test
    public void clearAll() throws Exception{
        User user = setupUser ();
        User user2 = setupUser ();
        userDao.clearAll ();
        assertEquals (0, userDao.getAllUsers ().size ());
    }
    private User setupUserForParkingSlot(ParkingSlot parking2) {
        User user = new User (1, "Odero", 3, parking2.getId ());
        userDao.add (user);
        return user;
    }

    private ParkingSlot setupParking() {
       ParkingSlot parkingSlot = new ParkingSlot ("Queen", 1);
       parkingSlotDao.add(parkingSlot);
       return parkingSlot;
    }

    private User setupUser() {
        User user = new User (1,"Queen",1,1);
        userDao.add(user);
        return user;
    }
}
