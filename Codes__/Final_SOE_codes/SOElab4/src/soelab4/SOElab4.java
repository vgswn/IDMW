/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soelab4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author RAGHAV SABOO
 */
public class SOElab4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        String s="C:\\Users\\RAGHAV SABOO\\Documents\\NetBeansProjects\\SOElab4\\src\\soelab4\\input.txt";
        FileReader fr=new FileReader(s);
        BufferedReader br=new BufferedReader(fr);
        String ln;
        ArrayList<String> func=new ArrayList<>();
        ArrayList<Integer> cc=new ArrayList<>();
        int i,l;
        br.mark(1000);
        while((ln=br.readLine())!=null)
        {
            ln=ln.trim();
            //System.out.println(ln);
            if(ln.matches("(void|char|float|long|int|double){1}.*[(]{1}.*[)]{1}"))
            {
              //  System.out.println(ln);
                func.add(ln);
            }
        }
        br.reset();
        while((ln=br.readLine())!=null)
        {
            ln=ln.trim();
            if(ln.matches("(void|char|float|long|int|double){1}.*[(]{1}.*[)]{1}"))
            {
                int c=0,ndp=0;
                do
                {
                    ln=br.readLine();
                    ln=ln.trim();
                    if(ln.compareTo("{")==0)
                    {
                        c++;
                    }
                    else if(ln.compareTo("}")==0)
                    {
                        c--;
                    }
                    else if(ln.matches("(for|while|if).*"))
                    {
                        ndp++;
                    }
                }while(c!=0);
                cc.add(ndp+1);
            }
        }
        int tc=0;
        for(i=0;i<func.size();i++)
        {
            tc=tc+cc.get(i);
            System.out.println(func.get(i)+"   "+cc.get(i));
        }
        System.out.println("Total cyclomatic complexity = "+tc);
    }
    
}
