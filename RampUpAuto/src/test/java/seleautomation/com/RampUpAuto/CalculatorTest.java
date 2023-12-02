package seleautomation.com.RampUpAuto;


import org.junit.*;
import org.junit.jupiter.api.Order;

import static org.junit.Assert.*;


public class CalculatorTest {



    CalculatorApp objectCal = new CalculatorApp();
    public int a = 6;
    public int b = 3;
    @BeforeClass
    public static void beforeClass(){
        System.out.println("Started testing the great calculator app");
    }

    @Before
    public void beforeTest(){
        System.out.println("Start a boring test");
    }

    @Test
    // @TestMethodOrder()
    public void TestCaseAdd(){
        System.out.println("Add Calculation");
        assertEquals(9, objectCal.add(a,b));
    }
    @Test
    @Order(2)
    public void TestCaseSubtract(){
        System.out.println("Subtract Calculation");
        assertEquals(3, objectCal.subtract(a,b));
    }
    @Test
    @Order(3)
    public void TestCaseMultibly(){
        System.out.println("Multiply Calculation");
        assertEquals(18, objectCal.multiply(a,b));
    }
    @Test
    @Order(4)
    public void TestCaseDivide(){
        System.out.println("Divide Calculation");
        assertEquals(2, objectCal.divide(a,b));
    }
    @After
    public void afterTest(){
        System.out.println("Finished a boring test");
    }
    @AfterClass
    public static void afterClass(){
        System.out.println("Finished testing the great calculator app");
    }
}