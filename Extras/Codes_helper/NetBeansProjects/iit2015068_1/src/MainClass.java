
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Placements 2018
 */
public class MainClass extends JFrame{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new ConnMgr().getConn();
        new MainClass().go();
        
    }
    public void go(){
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        JButton b1 = new JButton("Click here for student");
        JButton b2 = new JButton("Click here for invigilator");
        b1.addActionListener(new B1Listener());
        b2.addActionListener(new B2Listener());
        panel.add(b1);
        panel.add(b2);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    public class B1Listener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            new StudLogin().setVisible(true);            
        }
        
    }
    public class B2Listener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            new InvLogin().setVisible(true);            
        }
        
    }
}
