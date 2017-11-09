
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
public class ques5 {
    public static void main(String[] args) {
        new ques5().go();
    }

    public void go() {
        String fileName = "input.c";
        String line = "null";
        int count = 0;
        try {
            Pattern pattern = Pattern.compile("case\\s.*\\:$");
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            while((line = bufferedReader.readLine()) != null) {
                
                Matcher matcher = pattern.matcher(line);
                
                if(matcher.find()){
                    System.out.println("Found one  " + matcher.group() +"\n");
                    count++;
                }
                
            }
            System.out.println(count);
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
