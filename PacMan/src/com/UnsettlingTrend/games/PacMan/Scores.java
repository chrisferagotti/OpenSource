package com.UnsettlingTrend.games.PacMan;

import java.util.Vector;
import java.sql.*;

public class Scores {
    private Connection connectionToScoresDatabase = null;
    
     
    public void ConnectToScoresDatabase() throws SQLException
    {
        String driver = "org.apache.derby.jdbc.EmbeddedDriver";
        String databaseName="\\PacmanScores";
        String connectionURL = "jdbc:derby:" + databaseName; 
        
       try
        {
            Class.forName(driver); 
        } 
        catch(java.lang.ClassNotFoundException e) 
        {
            e.printStackTrace();
        }
        try 
        {
            connectionToScoresDatabase = DriverManager.getConnection(connectionURL); 

        }  catch (Throwable e)  
        {  
            e.printStackTrace();
        }
    }
    public void createDBAndTables() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        
    }
    public void SubmitScore(String name, int score) throws SQLException
    {
        String driver = "org.apache.derby.jdbc.EmbeddedDriver";
        String databaseName="\\PacmanScores";
        String connectionURL = "jdbc:derby:" + databaseName; 
        
       try
        {
            Class.forName(driver); 
        } 
        catch(java.lang.ClassNotFoundException e) 
        {
            e.printStackTrace();
        }
        try 
        {
            connectionToScoresDatabase = DriverManager.getConnection(connectionURL);
                   
            Statement statement=connectionToScoresDatabase.createStatement();
            int m = statement.executeUpdate("INSERT INTO scores VALUES('" + name + "'," + score + ")");
            System.out.println("Updated " + m + " rows");

        }  catch (Throwable e)  
        {  
            e.printStackTrace();
        }
        finally
        {
            connectionToScoresDatabase.close();
        }
        
    }
    public Vector GetTopScores(int numberOfScores) throws SQLException
    {
        
     
        Vector topScores = new Vector();
        Score temp = new Score();
         String driver = "org.apache.derby.jdbc.EmbeddedDriver";
        String databaseName="\\PacmanScores";
        String connectionURL = "jdbc:derby:" + databaseName; 
        
       try
        {
            Class.forName(driver); 
        } 
        catch(java.lang.ClassNotFoundException e) 
        {
            e.printStackTrace();
        }
        try 
        {
            connectionToScoresDatabase = DriverManager.getConnection(connectionURL);
            
            Statement statement=connectionToScoresDatabase.createStatement();
            statement.setMaxRows(numberOfScores);
            ResultSet resultSet= statement.executeQuery("SELECT * FROM scores ORDER BY score DESC");
            while(resultSet.next())
            {
                temp = new Score(resultSet.getString(1), resultSet.getInt(2));
                topScores.add(temp);
                temp = null;
            }
            
            resultSet.close();

        }  catch (Throwable e)  
        {  
            e.printStackTrace();
        }
        finally
        {
            connectionToScoresDatabase.close();
             return topScores;
        }          
    }
    public boolean IsTopTenScore(Integer score) throws SQLException
    {
        Score leastTopScore = this.GetLeastTopScore();
        
        if (leastTopScore.Score() <= score)
            return true;
        else
            return false;
    }
    public Score GetLeastTopScore() throws SQLException
    {   Score tempscore = new Score("A", 0);
        
        Vector topTenScores = this.GetTopScores(10);
        if (topTenScores.size() < 10)
            return tempscore;
        else
        return (Score) topTenScores.elementAt(topTenScores.size() -1);
    }
    public void CloseConnectionToScoresDatabase() throws SQLException
    {
        connectionToScoresDatabase.close();
    }
}
