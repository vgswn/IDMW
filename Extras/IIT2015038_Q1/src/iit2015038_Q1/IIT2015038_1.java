/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iit2015038_Q1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author placements2018
 */
public class IIT2015038_1 {

    /**
     * @param args the command line arguments
     */
    public static Connection connection;

    public static void main(String[] args) {
        try {
            String url = "jdbc:mysql://localhost/SOElab1_1";
            String username = "root";
            String password = "iiita123";

            connection = DriverManager.getConnection(url, username, password);
            Choose.main(args);

        } catch (SQLException ex) {
            Logger.getLogger(IIT2015038_1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
