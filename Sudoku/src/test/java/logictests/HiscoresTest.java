package logictests;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import sudokuapp.logic.Hiscores;

public class HiscoresTest {
    
    Hiscores hiscoresTest = new Hiscores();
    
    @Before
    public void setUp() throws SQLException {
        hiscoresTest.createDatabase("test");
    }
    
    @After
    public void tearDown() throws SQLException {
        File deleteDataTest = new File("test");
        deleteDataTest.delete();
    }
    
    @Test
    public void addTime() throws SQLException {
        Integer time = 500;
        hiscoresTest.createTime(time);
        
        assertEquals(time, hiscoresTest.returnTimes().get(0));
    }
    
    @Test
    public void bestTime() throws SQLException {
        hiscoresTest.createTime(400);
        hiscoresTest.createTime(300);
        hiscoresTest.createTime(230);
        hiscoresTest.createTime(610);
        hiscoresTest.createTime(202);
        Integer bestTime = 202;
        assertEquals(bestTime, hiscoresTest.returnTimes().get(0));
    }
    
    @Test
    public void bestTimes() throws SQLException {
        hiscoresTest.createTime(400);
        hiscoresTest.createTime(300);
        hiscoresTest.createTime(230);
        hiscoresTest.createTime(611);
        hiscoresTest.createTime(202);
        hiscoresTest.createTime(400);
        hiscoresTest.createTime(300);
        hiscoresTest.createTime(230);
        hiscoresTest.createTime(610);
        hiscoresTest.createTime(202);
        hiscoresTest.createTime(201);
        
        ArrayList<Integer> top10 = new ArrayList<>();
        top10.add(400);
        top10.add(300);
        top10.add(230);
        top10.add(202);
        top10.add(400);
        top10.add(300);
        top10.add(230);
        top10.add(610);
        top10.add(202);
        top10.add(201);
        Collections.sort(top10);
        
        assertEquals(top10, hiscoresTest.returnTimes());
    }
}
