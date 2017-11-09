/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soelab3;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author RAGHAV SABOO
 */
public class Mysqlconn {
 static Connection con;   
 public Mysqlconn()
 {
     try{  
Class.forName("com.mysql.jdbc.Driver");  
con=DriverManager.getConnection(  
"jdbc:mysql://localhost:3306/cultural_event_manager","root","2015042");
}catch(Exception e){ System.out.println(e);}  
}  
}  