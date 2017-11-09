/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soe_3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vips
 */
public class SOE_3 {

    public static Connection connection;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            String url = "jdbc:mysql://localhost/SOE_3";
            String username = "root";
            String password = "iiita123";
            
            
            System.out.println("Connecting database...");
            
           
            
            connection = DriverManager.getConnection(url, username, password);
            
            System.out.println("Database connected!");
            Login_.main(args);
            
            // TODO code application logic here
        } catch (SQLException ex) {
            Logger.getLogger(SOE_3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
