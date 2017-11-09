/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soelab2;

import java.sql.*;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.*;

/**
 *
 * @author raghav
 */
public class SOElab2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
           long SECOND_MILLIS = 1000;
   long MINUTE_MILLIS = SECOND_MILLIS*60;
  long HOUR_MILLIS = MINUTE_MILLIS*60;
    long DAY_MILLIS = HOUR_MILLIS*24;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Library", "root", "mysqlserver123");
            Statement stmt = con.createStatement();
            String choice, bid, bn, an;
            long millis;
            java.sql.Date date, cd;
            Calendar cal;
            Scanner sc = new Scanner(System.in);
            String uid, pasw;
            int g, c;
            System.out.println("**** Welcome to Library of IIITA ***");
            PreparedStatement ps;
            ResultSet rs;
            while (true) {
                System.out.println("Enter user id and password");
                uid = sc.next();
                pasw = sc.next();
                ps = con.prepareStatement("select * from Users where userid=? and password=?");
                ps.setString(1, uid);
                ps.setString(2, pasw);
                rs = ps.executeQuery();
                if (rs.next()) {
                    break;
                } else {
                    System.out.println("Invalid userid or password");
                }
            }
            if (rs.getString(1).startsWith("admin")) {
                while (true) {
                    System.out.println("Enter 1 for Issuing book");
                    System.out.println("Enter 2 for Adding book");
                    System.out.println("Enter 3 for Deleting book");
                    System.out.println("Enter 4 for Returning book");
                    System.out.println("Enter 5 for exit");
                    c = sc.nextInt();
                    if (c == 5) {
                        break;
                    }
                    switch (c) {
                        case 1:
                            System.out.println("Enter book id and student id");
                            bid = sc.next();
                            uid = sc.next();
                            ps = con.prepareStatement("select count(*) from Bookissued where Studentid=?");
                            ps.setString(1, uid);
                            rs = ps.executeQuery();
                            g=0;
                            while (rs.next()) {
                                g=rs.getInt(1);
                            }
                            if (g == 4) {
                                System.out.println("Cannot issue more than 4 books");
                                break;
                            }
                            ps = con.prepareStatement("select * from Book where Bookid=?");
                            ps.setString(1, bid);
                            rs = ps.executeQuery();
                            if (rs.next() == false) {
                                System.out.println("Not found book with id = " + bid);
                                break;
                            }
                            ps = con.prepareStatement("select * from Book where Bookid=?");
                            ps.setString(1, bid);
                            rs = ps.executeQuery();
                            rs.next();
                           // System.out.println("***");
                            if (rs.getString(4).compareTo("no") != 0) {
                                System.out.println("Book issued");
                                break;
                            }
                            bn = rs.getString(2);
                           // System.out.println("***");
                            millis = System.currentTimeMillis();
                            date = new java.sql.Date(millis);
                            GregorianCalendar gc = new GregorianCalendar();
                            gc.setTime(date);
                            gc.add(Calendar.DATE,15);
                            date=new java.sql.Date(gc.getTime().getTime());
                            ps = con.prepareStatement("insert into Bookissued values(?,?,?)");
                            ps.setString(1, bid);
                            ps.setString(2, uid);
                            ps.setDate(3, date);
                            g = ps.executeUpdate();
                            ps = con.prepareStatement("update Book set issuedornot=? where Bookid=?");
                            ps.setString(1, "yes");
                            ps.setString(2, bid);
                            g = ps.executeUpdate();
                            System.out.println("Book " + bn + " " + "issued due date is " + date.toString());
                            break;
                        case 2:
                            System.out.println("Enter book id book name author's name");
                            bid = sc.next();
                            bn = sc.next();
                            an = sc.next();
                            ps = con.prepareStatement("insert into Book values(?,?,?,?)");
                            ps.setString(1, bid);
                            ps.setString(2, bn);
                            ps.setString(3, an);
                            ps.setString(4, "no");
                            g = ps.executeUpdate();
                            System.out.println("Book " + bn + " added ");
                            break;
                        case 3:
                            System.out.println("Enter book id");
                            bid = sc.next();
                            ps = con.prepareStatement("delete from Book where Bookid=?");
                            ps.setString(1, bid);
                            g = ps.executeUpdate();
                            break;
                        case 4:
                            System.out.println("Enter book id");
                            bid = sc.next();
                            ps = con.prepareStatement("select * from Bookissued where Bookid=?");
                            ps.setString(1, bid);
                            rs = ps.executeQuery();
                            if(rs.next()==false)
                            {
                                System.out.println("Book with id = "+bid+" not issued");
                                break;
                            }
                            date = java.sql.Date.valueOf(rs.getString(3));
                            int fine = 0;
                            int nd = 0;
                            millis = System.currentTimeMillis();
                            
                            cd = new java.sql.Date(millis);
                            nd = (int)((cd.getTime()/DAY_MILLIS) - (date.getTime()/DAY_MILLIS));
                            if (nd <= 15) {
                                nd = 0;
                            }
                            fine = nd * 20;
                            ps = con.prepareStatement("delete from Bookissued where Bookid=?");
                            ps.setString(1, bid);
                            g = ps.executeUpdate();
                            ps = con.prepareStatement("update Book set issuedornot=? where Bookid=?");
                            ps.setString(1, "no");
                            ps.setString(2, bid);
                            g=ps.executeUpdate();
                            System.out.println("Fine = " + fine);
                            break;
                    }
                }
            } else {
                while (true) {
                    System.out.println("Enter 1 for searching book");
                    System.out.println("Enter 2 for searching books that you have issued");
                    System.out.println("Enter 3 for exit");
                    c = sc.nextInt();
                    if (c == 3) {
                        break;
                    }
                    switch (c) {
                        case 1:
                            System.out.println("Enter book id that you want to search");
                            bid = sc.next();
                            ps = con.prepareStatement("select * from Book where Bookid=?");
                            ps.setString(1, bid);
                            rs = ps.executeQuery();
                            g = 0;
                            while (rs.next()) {
                                g++;
                                System.out.println("Book id = " + rs.getString(1) + " Book name = " + rs.getString(2) + " Author's name = " + rs.getString(3) + " Issued status = " + rs.getString(4));
                            }
                            if (g == 0) {
                                System.out.println("Enter correct book id");
                            }
                            break;
                        case 2:
                            System.out.println("Enter your id");
                            uid = sc.next();
                            ps = con.prepareStatement("select * from Bookissued where Studentid=?");
                            ps.setString(1, uid);
                            rs = ps.executeQuery();
                            g = 0;
                            while (rs.next()) {
                                g++;
                                System.out.println("Book id = " + rs.getString(1) + " Student id = " + rs.getString(2) + " Due date = " + rs.getString(3));
                            }
                            if (g == 0) {
                                System.out.println("No books issued");
                            }
                            break;
                    }
                }
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
