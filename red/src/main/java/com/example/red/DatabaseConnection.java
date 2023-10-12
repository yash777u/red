package com.example.red;
import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    public  Connection databaseLink;

    public Connection getConnection()
    {
       String databaseName="bank";
       String databaseUser="root";
       String databasePassword="root";
       String url = "jdbc:mysql://localhost:3306/"+databaseName;

       try{
           Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url,databaseUser,databasePassword);
            return databaseLink;
       }
       catch (Exception e) {
           throw new RuntimeException(e);
       }

    }


}
