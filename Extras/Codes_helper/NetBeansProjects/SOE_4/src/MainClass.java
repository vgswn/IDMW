/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.*;
import java.util.regex.*;
import java.io.*;

/**
 *
 * @author Placements 2018
 */
public class MainClass {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new MainClass().go();
    }

    public void go() {
        String inputData = this.getInput("input.c");  
        String pattern = "(int|void|float|char)\\s\\*?\\s?([a-zA-Z0-9()*,; ]*)\\n{";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(inputData);
        int i = 1;
        int total = 0;
        while (m.find()) {
            int openingIndex = m.end();
            int closingIndex = findMatching(inputData, openingIndex);
            int decisionPoints = findDecisionPoints(inputData.substring(openingIndex, closingIndex));
            System.out.println("Function " + m.group(2) + "(C" + i + "): " + decisionPoints + " + 1 = " + (decisionPoints + 1));
            total += decisionPoints + 1;
            i++;
        }
        System.out.println("TC = " + total);
    }

    public int findDecisionPoints(String data) {
        int count = 0;

        String pattern = "\\b(if|while|for)\\b\\s*\\(.*?\\)";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(data);
        while (m.find()) {
            count++;
        }

        pattern = "\\bcase\\b";
    
        p = Pattern.compile(pattern);
        Matcher n = p.matcher(data);
        while (n.find()) {
            count++;
        }

        return count;
    }

    public int findMatching(String inputData, int openingIndex) {
        int i = openingIndex;
        int count = 1;
        int matchingIndex = -1;
        while (true) {
            if (inputData.charAt(i) == '{') {
                count++;
            } else if (inputData.charAt(i) == '}') {
                count--;
            }
            if (count == 0) {
                matchingIndex = i;
                break;
            }
            i++;
        }
        return matchingIndex;
    }

    public String getInput(String filename) {
        String inputData = "";
        try {
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String input;
            while ((input = bufferedReader.readLine()) != null) {
                inputData += input + "\n";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return inputData;
    }

}
