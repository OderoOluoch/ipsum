package dao;

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
        User testUser = setupUser();
        assertEquals(1, testUser.getId());
    }
    @Test
    public void getAll() throws Exception{
        User testUser1 = setupUser();
        User testUser2 = setupUser();
        assertEquals(2, userDao.getAllUsers().size());
    }
    private User setupUser() {
        User testUser = new User (1,"Queen",1,1);
        userDao.add(testUser);
        return testUser;
    }
}
