package com.company;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: rashedul.hasan
 * Date: 4/13/14
 * Time: 1:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class JUnitTest1 {

    Validator validator;
    private User student;

    @Before
    public void setUp() {
        student = new User("Rijul", "ra.ha@gmail.com", 23);
        validator = new Validator(student);
        System.out.println("@Before - setUp");
    }

    @Test
    public void testUser() {
        assertTrue("Sorry This object can't be validated", validator.validateAll());
        System.out.println("Test Case 1= Validated");
    }

    @Test
    public void testUserEmail() {
        assertTrue("Email Checking Failed", validator.validateAllEmail());
        System.out.println("Email Validated");
    }

    @After
    public void tearDown() {
        System.out.println("@After - tearDown");
    }
}
