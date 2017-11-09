/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soe1_1;

import java.io.*;
import java.util.regex.*;

/**
 *
 * @author Placements 2018
 */
public class Soe1_1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new Soe1_1().go();
    }

    public void go() {
        String fileName = "input.c";
        String line = "null";
        int count = 0;
        try {
            Pattern pattern = Pattern.compile("[a-z]+\\s[a-zA-z0-9]+\\(.*\\)\\{");
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {

                Matcher matcher = pattern.matcher(line);

                if (matcher.find()) {
                    System.out.println("Found one  " + matcher.group() + "\n");
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
