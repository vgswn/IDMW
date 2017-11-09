/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soe2_1;

import java.sql.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Placements 2018
 */
public class Soe2_1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ConnMgr connMgr = new ConnMgr();
        connMgr.getConn();

    }

    public void addNewBook() {
        try {
            String id;
            String bookName;
            String author;
            String sql = "insert into bookinfo values(?,?,?)";
            Scanner s = new Scanner(System.in);
            System.out.println("Enter Book ID");
            id = s.nextLine();
            System.out.println("Enter Name of the book");
            bookName = s.nextLine();
            System.out.println("Enter Author");
            author = s.nextLine();

            PreparedStatement stmt = null;
            stmt = ConnMgr.conn.prepareStatement(sql);

            stmt.setString(1, id);
            stmt.setString(2, bookName);
            stmt.setString(3, author);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Soe2_1.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void deleteBook() {
        try {
            System.out.println("Enter ID of book you want to delete");
            Scanner s = new Scanner(System.in);
            String id = s.nextLine();
            String sql = "delete from bookinfo where id = ?";
            PreparedStatement stmt = null;
            stmt = ConnMgr.conn.prepareStatement(sql);
            stmt.setString(1, id);
            stmt.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Soe2_1.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
