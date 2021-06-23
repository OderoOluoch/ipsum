package models;//package models;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import static org.junit.Assert.*;
//
//public class CarTest {
//
////    @Before
////    public void setUp() throws Exception {
////    }
////
////    @After
////    public void tearDown() throws Exception {
////    }
//
//
//    @Test
//    public void shouldShowSimpleAssert(){
////        Car car = setupCar();
//        assertEquals(1,1);
//    }

//    @Test
//    public void CarInstanceCorrectlyWith_values() throws Exception {
//        Car car = setupCar();
//        assertEquals("You are in cars_potal",car.getCar_name());
//        assertEquals("You are in cars_potal",car.getCar_id());
//    }
//
//
//    @Test
//    public void setId() {
//        Car testCar = setupCar();
//        testCar.setId(5);
//        assertEquals(5, testCar.getCar_id());
//    }


//    public Car setupCar(){
//        return new Car("Subaru",874,67);
//    }
//
//
//}


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
