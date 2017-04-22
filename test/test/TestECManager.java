/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entity.Statistic;
import java.util.ArrayList;
import java.util.List;
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
public class TestECManager {

    ConnectDB connectDB = new ConnectDB();

    public TestECManager() {
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
    @Test
    public void getAllClaim() {
        int totalClaim = 11;
        assertEquals("Total all claim", totalClaim, connectDB.getAllClaim(2017).size());
    }

    @Test
    public void getAllClaimbyTwoWeek() {
        int totalClaim = 1;
        assertEquals("Total all claim 2 after week", 1, connectDB.getAllClaimUnresolvedAfterTwoWeek(2017).size());
    }

    @Test
    public void getAllClaimbyTwoWeekById() {
        int idMajor = 1;
        int totalClaim = 0;
        assertEquals("Total all claim 2 after week by Major", totalClaim, connectDB.getAllClaimUnresolvedAfterTwoWeekInMajor(idMajor).size());
    }

    @Test
    public void getAllMajor() {
        assertEquals("All major: ", 2, connectDB.getListMajor().size());
    }

    @Test
    public void getAllStudentUpClaiminFebruary() {
        String year = "2017";
        int idMajor = 1;
        String month = "02";

        List<Statistic> allStdUpClm = connectDB.getAllClaim(year, idMajor);

        List<String> listNameUser = new ArrayList<>();
        Statistic s = new Statistic();
        for (Statistic st : allStdUpClm) {
            if (st.getYear().substring(5, 7).equals(month)) {
                if (listNameUser.size() > 0) {
                    for (int j = 0; j < listNameUser.size(); j++) {
                        if (!listNameUser.get(j).equals(st.getUser())) {
                            listNameUser.add(st.getUser());

                        }
                    }
                } else {
                    listNameUser.add(st.getUser());
                }
                s.setData(listNameUser.size());
            }
        }
        
        assertEquals("Total students up claim", 2, s.getData());
    }
}
