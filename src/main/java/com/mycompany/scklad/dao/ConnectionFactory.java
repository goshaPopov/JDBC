package com.mycompany.scklad.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Georgiy Popov
 */
public class ConnectionFactory {
        
    private static Connection con;
    private static ConnectionFactory instance;
 
    private ConnectionFactory() throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/warehouse";
            con =  DriverManager.getConnection(url, "root", "root");
            con.setAutoCommit(false);
        } catch (ClassNotFoundException e) {
            throw new Exception(e);
        } catch (SQLException e) {
            throw new Exception(e);
        }
    }
    
    public Connection getConnection(){
        return this.con;
    }
    
    public static synchronized ConnectionFactory getInstance() throws Exception {
        if (instance == null) {
            instance = new ConnectionFactory();
        }
        return instance;
    }
}
