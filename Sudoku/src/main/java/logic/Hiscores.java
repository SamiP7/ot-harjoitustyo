package logic;

import java.sql.*;
import java.util.*;

public class Hiscores {
    
    /**
     * creates a table for hiscores in the database if it doesn't exist
     * @throws SQLException 
     */
    
    public void createDatabase() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:db");
        PreparedStatement makeDatabase = connection.prepareStatement(
            "CREATE TABLE IF NOT EXISTS Hiscores(id INTEGER AUTO_INCREMENT PRIMARY KEY, time INTEGER);");
        makeDatabase.execute();
        makeDatabase.close();
        connection.close();
    }
    
    /**
     * Adds a value to the table Hiscores
     * @param time
     * @throws SQLException 
     */
    
    public void createTime(Integer time) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:db");
        PreparedStatement newTime = connection.prepareStatement(
            "INSERT INTO Hiscores(time)"
            + " VALUES (?);");
        newTime.setInt(1, time);
        
        newTime.executeUpdate();
        newTime.close();
        connection.close();
    }
    
    /**
     * Gets a list of all the times in the table Hiscores, sorts them and returns the 10 best ones
     * 
     * @return top 10 times
     * @throws SQLException 
     */
    
    public ArrayList<Integer> returnTimes() throws SQLException {
        ArrayList<Integer> allTimes = new ArrayList<>();
        Connection connection = DriverManager.getConnection("jdbc:sqlite:db");
        PreparedStatement getTimes = connection.prepareStatement("SELECT * FROM Hiscores;");
        ResultSet rS = getTimes.executeQuery();
        while (rS.next()) {
            allTimes.add(rS.getInt("time"));
        }
        getTimes.close();
        rS.close();
        connection.close();
        Collections.sort(allTimes);
        ArrayList<Integer> top10 = new ArrayList<>();
        for (int i = 0; i < allTimes.size(); i++) {
            if (i > 9) {
                break;
            }
            top10.add(allTimes.get(i));
        }
        return top10;
    }
}
