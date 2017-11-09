/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soe_2;

import static java.lang.Math.abs;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rishabh1rishu
 */
/*public class SOE_2 {

    /**
     * @param args the command line arguments
     */
 /*   public static void main(String[] args) throws SQLException, ParseException {
        String url = "jdbc:mysql://localhost/soe_2";
        String username = "root";
        String password = "root";
        Scanner xx = new Scanner(System.in);

        System.out.println("Connecting database...");

        Connection connection;

        connection = DriverManager.getConnection(url, username, password);

        System.out.println("Database connected!");

        while (true) {

            System.out.println("Select your option");
            System.out.println("1:Insert\n2:Delete\n3:Update\n4:Edit\n5:Student Details:\n6:Search Book\n7:Check Status\n8:View Table:\n9:Exit");
            int a = xx.nextInt();
            String aq = xx.nextLine();
            if (a == 9) {
                break;
            } else if (a == 1) {
                //System.out.println("Enter book name,author's name, book id,issue status,student id,due date,fine");
                System.out.println("Enter book name: ");
                String bn = xx.nextLine();;
                System.out.println("Enter Author's Name:");
                String an = xx.nextLine();;
                System.out.println("Enter Book Id:");
                String bi = xx.nextLine();;
                //System.out.println("Enter Issue Status(isssue or available):");
                String is = "available";
                String si = "NULL";

                String dd = null;
                int fine = 0;

                String query = " insert into soe_2.database values (?, ?, ?, ?, ?,?,?)";
                try {
                    PreparedStatement stmt = connection.prepareStatement(query);
                    stmt.setString(1, an);
                    stmt.setString(2, bn);
                    stmt.setString(3, bi);
                    stmt.setString(4, is);
                    stmt.setString(5, si);
                    stmt.setString(6, dd);
                    stmt.setInt(7, fine);
                    stmt.execute();

                } catch (SQLException ex) {
                    Logger.getLogger(SOE_2.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else if (a == 2) {
                System.out.println("Enter book id to delete from database");
                String bi = xx.nextLine();;
                String query = " delete from soe_2.database where book_id = ?";
                PreparedStatement stmt = connection.prepareStatement(query);
                stmt.setString(1, bi);
                stmt.execute();

            } else if (a == 3) {
                System.out.println("Enter book id to be updated:");
                String bi = xx.nextLine();;
                System.out.println("Enter new book id");
                String nbi = xx.nextLine();
                String query = " update soe_2.database set book_id= ?  where book_id = ?";
                PreparedStatement stmt = connection.prepareStatement(query);
                stmt.setString(1, nbi);
                stmt.setString(2, bi);
                stmt.execute();
            } else if (a == 4) {
                System.out.println("Enter book id :");
                String bi = xx.nextLine();;
                System.out.println("Issue or Return :");
                String ss = xx.nextLine();
                String si = "NULL";
                String dd = "NULL";
                int fine = 0;
                if (ss.equalsIgnoreCase("issue")) {

                    PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM soe_2.database where book_id=? ");
                    preparedStatement.setString(1, bi);
                    ResultSet resultSet = preparedStatement.executeQuery();

                    if (resultSet.next()) {
                        if (!resultSet.getString("issue_status").equalsIgnoreCase("available")) {
                            System.out.println("Book is Already Issued to :" + resultSet.getString("student_id") + " Due Dated: " + resultSet.getString("due_date"));
                            continue;
                        }
                    }

                    System.out.println("Enter Student Id:");
                    si = xx.nextLine();
                    PreparedStatement prepared = connection.prepareStatement("SELECT * FROM soe_2.database where student_id=? ");
                    prepared.setString(1, si);
                    ResultSet result = prepared.executeQuery();
                    int x = 0;
                    while (result.next()) {
                        x++;
                    }

                    if (x >= 4) {
                        System.out.println("You Already have 4 Books Issued ");
                        continue;
                    }
                    System.out.println("Enter Due Date(dd/mm/yyyy):");
                    dd = xx.nextLine();;
                    System.out.println("Enter Fine:");
                    fine = xx.nextInt();
                    String query = " update soe_2.database set student_id=?,due_date=?,fine=?,issue_status=?   where book_id = ?";
                    PreparedStatement stmt = connection.prepareStatement(query);
                    stmt.setString(1, si);
                    stmt.setString(2, dd);
                    stmt.setInt(3, fine);
                    stmt.setString(4, "issued");
                    stmt.setString(5, bi);
                    stmt.execute();
                    continue;
                } else {

                    System.out.println("Enter Today's date:");
                    String td = xx.nextLine();;
                    PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM soe_2.database WHERE book_id=? ");
                    preparedStatement.setString(1, bi);

                    ResultSet resultSet = preparedStatement.executeQuery();

                    if (resultSet.next()) {
                        SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
                        String ddd;
                        ddd = resultSet.getString("due_date");
                        try {
                            java.util.Date date1 = myFormat.parse(td);
                            java.util.Date date2 = myFormat.parse(ddd);
                            long diff = date2.getTime() - date1.getTime();
                            if (abs(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)) > 15) {
                                System.out.println("Your Fine Will be : " + abs(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)) * 5);
                            }

                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                    }
                    String query = " update soe_2.database set student_id=?,due_date=?,fine=?,issue_status=?   where book_id = ?";
                    PreparedStatement stmt = connection.prepareStatement(query);
                    stmt.setString(1, "NULL");
                    stmt.setString(2, "NULL");
                    stmt.setInt(3, 0);
                    stmt.setString(4, "available");
                    stmt.setString(5, bi);
                    stmt.execute();

                }

            } else if (a == 5) {

                System.out.println("Enter Student Id:");
                String si = xx.nextLine();;
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM soe_2.database WHERE student_id=? ");
                preparedStatement.setString(1, si);

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    //System.out.println("Author's Name\t BookName\t BookId\t   IssueStatus\t StudentID\t DueDate\t Fine   ");
                    System.out.println("Author's Name;" + resultSet.getString(1) + "\n" + "BookName: " + resultSet.getString(2) + " \n" + "BookId: " + resultSet.getString(3) + " \n" + "IssueStatus: " + resultSet.getString(4) + "\n" + "StudentID: " + resultSet.getString(5) + " \n" + "Due Date: " + resultSet.getString(6) + " \n" + "Fine: " + resultSet.getString(7) + "\n");

                }
            } else if (a == 6) {

                System.out.println("Enter Book Id:");
                String si = xx.nextLine();;
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM soe_2.database WHERE book_id=? ");
                preparedStatement.setString(1, si);

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    //System.out.println("Author's Name\t BookName\t BookId\t   IssueStatus\t StudentID\t DueDate\t Fine   ");
                    System.out.println("Author's Name;" + resultSet.getString(1) + "\n" + "BookName: " + resultSet.getString(2) + " \n" + "BookId: " + resultSet.getString(3) + " \n" + "IssueStatus: " + resultSet.getString(4) + "\n" + "StudentID: " + resultSet.getString(5) + " \n" + "Due Date: " + resultSet.getString(6) + " \n" + "Fine: " + resultSet.getString(7) + "\n");

                }
            } else if (a == 7) {

                System.out.println("Enter Book Id:");
                String si = xx.nextLine();;
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM soe_2.database WHERE book_id=? ");
                preparedStatement.setString(1, si);

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    System.out.println("BOOK STATUS: " + resultSet.getString("issue_status"));

                }
            } else if (a == 8) {

                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM soe_2.database ");

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    System.out.println("Author's Name;" + resultSet.getString(1) + "\n" + "BookName: " + resultSet.getString(2) + " \n" + "BookId: " + resultSet.getString(3) + " \n" + "IssueStatus: " + resultSet.getString(4) + "\n" + "StudentID: " + resultSet.getString(5) + " \n" + "Due Date: " + resultSet.getString(6) + " \n" + "Fine: " + resultSet.getString(7) + "\n");

                }
            }

        }

    }

}*/
