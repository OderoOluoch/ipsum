package dao;

import models.ParkingSlot;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.assertEquals;

public class Sql2oParkingSlotDaoTest {
    Sql2oParkingSlotDao parkingSlotDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        parkingSlotDao = new Sql2oParkingSlotDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingNewsSetsId() throws Exception {
        ParkingSlot slotOne = setupParkingSlot();
        assertEquals(1, slotOne.getId());
    }

    @Test
    public void getAll() throws Exception {
        ParkingSlot slotOne = setupParkingSlot();
        ParkingSlot slotTwo = setupParkingSlot();
        assertEquals(2, parkingSlotDao.getAll().size());
    }

    @Test
    public void deleteById() throws Exception {
        ParkingSlot slotOne = setupParkingSlot();
        ParkingSlot slotTwo = setupParkingSlot();
        assertEquals(2, parkingSlotDao.getAll().size());
        parkingSlotDao.deleteById(slotOne.getId());
        assertEquals(1, parkingSlotDao.getAll().size());
    }

    @Test
    public void clearAll() throws Exception {

        ParkingSlot slotOne = setupParkingSlot();
        ParkingSlot slotTwo = setupParkingSlot();
        parkingSlotDao.clearAll();
        assertEquals(0, parkingSlotDao.getAll().size());
    }

    //helpers
    public ParkingSlot setupParkingSlot() {
        ParkingSlot slot = new ParkingSlot("Ground Floor",5);
        parkingSlotDao.add(slot);
        return slot;
    }
}