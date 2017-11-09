
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rakalicious
 */
public class ques6 {
    public static void main(String[] args) {
        new ques6().go();
    }

    public void go() {
        String fileName = "input.c";
        String line = "null";
        //int count = 0;
        int start =0;
        int nestcount = 0;
        boolean nestingstart = false;
        try {
            Pattern pattern1 = Pattern.compile("if|switch\\(.*\\)\\{");
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
