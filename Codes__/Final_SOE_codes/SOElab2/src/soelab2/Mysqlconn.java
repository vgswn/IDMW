/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soelab2;

/**
 *
 * @author RAGHAV SABOO
 */
import java.sql.*;
public class Mysqlconn {
    static Connection con;
    public Mysqlconn() throws ClassNotFoundException, SQLException
    {
        Class.forName("com.mysql.jdbc.Driver");  
con=DriverManager.getConnection(  
"jdbc:mysql://localhost:3306/library","root","2015042");
    }
}
