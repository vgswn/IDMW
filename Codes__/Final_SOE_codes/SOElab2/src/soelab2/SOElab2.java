/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soelab2;

import java.sql.SQLException;
import java.util.regex.Pattern;

/**
 *
 * @author RAGHAV SABOO
 */
public class SOElab2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // TODO code application logic here
        Mysqlconn ob=new Mysqlconn();
        Initial ib=new Initial();
        ib.setVisible(true);
    }
    
}
