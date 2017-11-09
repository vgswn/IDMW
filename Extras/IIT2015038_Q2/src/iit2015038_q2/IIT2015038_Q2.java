/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iit2015038_q2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author placements2018
 */
public class IIT2015038_Q2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here

        int f = 0;
        int l = 0;
        int ifelse = 0;
        int i = 6,d=1,fl=-1,c=0;
        String ls;
        String s = "hello.c";
        
        FileReader fr = new FileReader(s);
        BufferedReader br = new BufferedReader(fr);
        while ((ls = br.readLine()) != null) {
            if (ls.matches("(int|float|char|void|double|long|short)[ ]*[\\w]*[(]{1}[(int|float|char|void|double|long|short)[ ].*[\\w][,]?]*[)]{1}")) {
                f++;
            } else if (ls.matches("(for|while|do){1}.*[(]{1}.*[)]{1}")) {
                l++;
            } else if (ls.matches("(if).*(.*)")) {
                ifelse++;
            }
            else if(ls.matches("(int|char|double|float)[ ]")){
                if(ls.matches("(int)[ ]"))
                    i++;
                if(ls.matches("(char)[ ]"))
                    c++;
                if(ls.matches("(float)[ ]"))
                    fl++;
                if(ls.matches("(double)"))
                    d++;
                
                
                    }
            {
                
            }

        }
        System.out.println("Functions :"+f);
        System.out.println("Loops : "+l);

        System.out.println("If-Else Condition :"+ifelse);
                System.out.println("int : "+i);
        System.out.println("float : "+f);
        System.out.println("double : "+d);
                System.out.println("ch : "+c);



    }

}
