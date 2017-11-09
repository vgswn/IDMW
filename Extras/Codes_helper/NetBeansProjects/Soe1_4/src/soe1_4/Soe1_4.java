/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soe1_4;

import java.io.*;
import java.util.regex.*;

/**
 *
 * @author Placements 2018
 */
public class Soe1_4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    public void go() {
        String fileName = "input.c";
        String line = "null";
        //int count = 0;
        int start =0;
        int nestcount = 0;
        boolean nestingstart = false;
        try {
            Pattern pattern1 = Pattern.compile("for|while|do\\(.*\\)\\{");
            Pattern pattern2 = Pattern.compile("\\}");
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            while((line = bufferedReader.readLine()) != null) {
                
                Matcher matcher1 = pattern1.matcher(line);
                Matcher matcher2 = pattern2.matcher(line);
                if(matcher1.find()){
                    System.out.println("Found one  " + matcher1.group() +"\n");
                    if(nestingstart == false){
                        nestingstart = true;
                        start = matcher1.start();
                        
                    }
                    else{
                        nestcount++;
                        System.out.println(matcher1.group() + " at " + matcher1.start() + "\n");
                    }
                }
                else if(matcher2.find()){
                    if(matcher2.start() == start){
                        nestingstart = false;
                        continue;
                    }
                    else{
                        continue;
                    }
                }
                
            }
            System.out.println(nestcount);
        } catch (FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '"
                    + fileName + "'");
        } catch (IOException ex) {
            System.out.println(
                    "Error reading file '"
                    + fileName + "'");
        }
    }
}
