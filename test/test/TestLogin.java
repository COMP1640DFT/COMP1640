/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import model.ConnectDB;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author DaoMinhThien
 */
public class TestLogin {
    ConnectDB connectDB = new ConnectDB();
    
    public TestLogin() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void checkLoginWithStudent(){
        assertEquals("User student", 1, connectDB.checkLogin("taincgc", "123123").getLever());
    }
    
    @Test
    public void checkLoginWithEcManager(){
        assertEquals("User ecmanager", 3, connectDB.checkLogin("ecmanager1", "123123").getLever());
    }
    
    @Test
    public void checkLoginWithAdmin(){
        assertEquals("User admin", 2, connectDB.checkLogin("admin", "123123").getLever());
    }
    
    @Test
    public void checkLoginWithEcCon(){
        assertEquals("User condinator", 4, connectDB.checkLogin("eccoorit", "123123").getLever());
    }
    
    @Test
    public void checkLoginWithUserNotCreated(){
        assertEquals("User was not created", null, connectDB.checkLogin("studentid", "123123").getIdUser());
    }
}
