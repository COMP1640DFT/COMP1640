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
public class TestStudent {
    
    ConnectDB connectDB = new ConnectDB();
    
    public TestStudent() {
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
    public void getAllClaimManageByMajor(){
        assertEquals("Total all claim of IT", 2, connectDB.getAllClaimManage(1).size());
    }
    
    @Test 
    public void getAllClaimOfClaimManage(){
        int idClaimManage =1;
        String idUser = "taicngc";
        assertEquals("Total all claim of user id taincgc ", 3, connectDB.getAllClaimOfStudent("taincgc", idClaimManage).size());
    }
}
