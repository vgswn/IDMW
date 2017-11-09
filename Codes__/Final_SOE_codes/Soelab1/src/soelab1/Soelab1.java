/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soelab1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author RAGHAV SABOO
 */
class Pair<String, Integer> {

    private String first;
    private Integer second;

    Pair(String a, Integer b) {
        first = a;
        second = b;
    }

    String getKey() {
        return first;
    }

    Integer getValue() {
        return second;
    }
}

public class Soelab1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        String s = "C:\\Users\\RAGHAV SABOO\\Documents\\NetBeansProjects\\Soelab1\\src\\soelab1\\hello.c";
        FileReader fr = new FileReader(s);
        BufferedReader br = new BufferedReader(fr);
        // ef="";
        String ls;
        br.mark(100);
        ls=br.readLine();
        System.out.println(ls);
        br.mark(100);
        ls=br.readLine();
        br.reset();
        //ls=br.readLine();8
        System.out.println(ls);
        
        /*int ln = 1;
        int i;
        ArrayList<Pair<String, Integer>> hf = new ArrayList<>();
        ArrayList<Pair<String, Integer>> fs = new ArrayList<>();
        ArrayList<Pair<String, Integer>> fc = new ArrayList<>();
        ArrayList<Pair<String, Integer>> lop = new ArrayList<>();
        ArrayList<Pair<String, Integer>> ncs = new ArrayList<>();
        int casswc = 0;
        while ((ls = br.readLine()) != null) {
            ls = ls.trim();
            //System.out.println(ls);
            if (ls.matches("#include.*")) {
                hf.add(new Pair<String, Integer>(ls, ln));
                //System.out.println(ls);
            } else if (ls.matches("(int|float|char|void|double|long|short)[ ]*[\\w]*[(]{1}[(int|float|char|void|double|long|short)[ ].*[,]?]*[)]{1}")) {
                fs.add(new Pair<String, Integer>(ls, ln));
                // System.out.println(ls);
            } else if (ls.matches("(for|while|do){1}[ ]*[(]{1}.*[)]{1}")) {
                lop.add(new Pair<String, Integer>(ls, ln));
                //System.out.println(ls);                
            } else if (ls.matches("case.*:")) {
                casswc++;
            } else if (ls.matches("(if).*(.*)")) {
                ncs.add(new Pair<String, Integer>(ls, ln));
            } else if (ls.matches(".*[(]{1}.*[)]{1}[;]{1}")) {
                //fc.add(new Pair<String, Integer>(ls, ln));
                String word = ls;
                String c = "(";
                for (int index = word.indexOf(c); index >= 0; index= word.indexOf(c)) {
                    if(word.matches(".*[(]{1}.*[)]{1}[;]{1}"))
                    {
                        fc.add(new Pair<String, Integer>(word, ln));
                    }
                    word=word.substring(index+1);
                }
            }
            ln++;
        }
        System.out.println(hf.size() + " Header files :-");
        for (Pair<String, Integer> p : hf) {
            System.out.println(p.getKey() + " at line no " + p.getValue());
        }
        System.out.println();
        System.out.println(fs.size() + " Function Signatures :-");
        for (Pair<String, Integer> p : fs) {
            System.out.println(p.getKey() + " at line no " + p.getValue());
        }
        System.out.println();
        System.out.println(fc.size() + " Function calls :-");
        for (Pair<String, Integer> p : fc) {
            System.out.println(p.getKey() + " at line no " + p.getValue());
        }
        System.out.println();
        System.out.println(lop.size() + " Loop :-");
        for (Pair<String, Integer> p : lop) {
            System.out.println(p.getKey() + " at line no " + p.getValue());
        }
        System.out.println();
        System.out.println(ncs.size() + " Conditional statement :-");
        for (Pair<String, Integer> p : ncs) {
            System.out.println(p.getKey() + " at line no " + p.getValue());
        }
        System.out.println();
        System.out.println("No of cases in switch : " + casswc);
        br.close();
        fr.close();
        /*System.out.println(Pattern.matches("(([0|1]{1}[0-9]?[0-9]?)|([2]{1}[0-5]?[0-5]?)|([0-9]{1}[0-9]?))[.]{1}(([0|1]{1}[0-9]?[0-9]?)|([2]{1}[0-5]?[0-5]?)|([0-9]{1}[0-9]?))[.]{1}(([0|1]{1}[0-9]?[0-9]?)|([2]{1}[0-5]?[0-5]?)|([0-9]{1}[0-9]?))[.]{1}(([0|1]{1}[0-9]?[0-9]?)|([2]{1}[0-5]?[0-5]?)|([0-9]{1}[0-9]?))","000.12.12.12"));
         System.out.println(Pattern.matches("[[0-3]|[5-6]]{2}","35"));
         System.out.println(Pattern.matches("[\\d]{2}","a5"));
         System.out.println(Pattern.matches("[[int]|[char]].*","s int a"));
         System.out.println(Pattern.matches("[[int]|[float]]+[ ].*","int a"));
         System.out.println(Pattern.matches("[[[int]{3}]|[[float]{4}]|[[char]{4}]][ ]*.*","int a"));
         System.out.println(Pattern.matches("(int|char|float)[ ].*","int  a"));
         System.out.println(Pattern.matches("(int)","int"));
         System.out.println(Pattern.matches("[int|float]{1}","int"));
         System.out.println(Pattern.matches("^((?!int)|(?!float)).*","float"));*/
       /* System.out.println(Pattern.matches("(?i).*[ ]\\b(AM)\\b.*","i am a boy"));
        s="hey hey hey hi";
        String d=s.replaceAll("(?i)\\b([a-z]+)\\b(?:\\s+\\1\\b)+","$1");
        System.out.println(d);
        FileWriter fw=new FileWriter("output.c");
        BufferedWriter bw=new BufferedWriter(fw);
        for(i=0;i<fc.size();i++)
        {
            bw.write(fc.get(i).getKey());
            bw.newLine();
        }
                
        bw.close();
        fw.close();*/
    }

}
