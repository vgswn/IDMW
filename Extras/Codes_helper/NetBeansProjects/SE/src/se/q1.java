/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Placements 2018
 */
public class q1 {
    private static final String FILENAME="C:\\a.cpp";
    public static void main(String [] args) throws FileNotFoundException, IOException{
        System.out.println("output\n");
        BufferedReader br=null;
        FileReader fr=null;
        try{
            fr=new FileReader(FILENAME);
            br=new BufferedReader(fr);
            String curr;
            System.out.println("output\n");
            while((curr=br.readLine())!=null){
                System.out.println(curr);
            }
        }catch(IOException e){
                e.printStackTrace();
        }finally{
            try{
                if(br!=null)
                    br.close();
                if(fr!=null)
                    fr.close();
            }catch(IOException ex){
                ex.printStackTrace();
            }
        }
            
    }
}
