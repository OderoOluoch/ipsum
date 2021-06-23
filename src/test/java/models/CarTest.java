package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import models.Car;
//import Car;

import static org.junit.Assert.assertEquals;

public class CarTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void NewCarObjectGetsCorrectlyCreated_true() throws Exception {
        Car car = new Car("hi", 45,56);
        assertEquals(true, car instanceof Car);
    }

    @Test
    public void NewCarObjectGetsCorrectlyCreated_false() throws Exception {
        Car car = new Car("subaru", 1,6);
        assertEquals(true, car instanceof Car);
    }
}
