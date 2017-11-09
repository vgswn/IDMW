

import java.sql.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Placements 2018
 */
public class ConnMgr {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/soe_2";

    static final String USER = "root";
    static final String PASS = "root";
    static Connection conn = null;
    
    public void getConn(){
        Statement stmt = null;
        try {    
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
                    
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
