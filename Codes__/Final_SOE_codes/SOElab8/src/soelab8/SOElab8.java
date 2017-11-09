/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author RAGHAV SABOO
 */
package soelab8;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author placements2018
 */
public class SOElab8 {

    /**
     * @param args the command line arguments
     */
    static String on[] = {"<", "=", ">", "-", ";", ",", "(", ")", "[", "{", "}", "+", "++", "for", "if", "int", "return", "while", "float", "double", "long", "short", "do", "break", "continue", "switch", "case"};
    static String operand[] = {"<", ">", "<=", ">=", "==", "=", "!=", "+", "-", "/", "*", "%"};

    static boolean check_operand(char ch) {
        int i;
        for (i = 0; i < 12; i++) {
            if (operand[i].length() == 1 && operand[i].charAt(0) == ch) {
                return true;
            }
        }
        return false;
    }

    static boolean check(char ch) {
        int i;
        for (i = 0; i < 12; i++) {
            if (on[i].length() == 1 && on[i].charAt(0) == ch) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        String s = "C:\\Users\\RAGHAV SABOO\\Documents\\NetBeansProjects\\SOElab8\\src\\soelab8\\input.c";
        FileReader fr = new FileReader(s);
        BufferedReader br = new BufferedReader(fr);
        String on[] = {"<", "=", ">", "-", ";", ",", "(", ")", "[", "{", "}", "+", "++", "for", "if", "int", "return", "while", "float", "double", "long", "short", "do", "break", "continue", "switch", "case"};
        int op_cnt[] = new int[105];
        int operand_cnt[]=new int[105];
        int var_cnt[]=new int[105];
        int top=0,dop=0;
        String ln;
        int c = 0, i, j, k;
        for (i = 0; i < 105; i++) {
            op_cnt[i] = 0;
            operand_cnt[i]=0;
            var_cnt[i]=0;
        }
        br.mark(1000);
        while ((ln = br.readLine()) != null) {
            //System.out.println(ln);
            ln = ln.trim();

            for (i = 0; i < on.length; i++) {
                c = 0;
                //System.out.println(on[i]);
                for (j = 0; j <= ln.length() - on[i].length(); j++) {
                    String ts = ln.substring(j, j + on[i].length());
                    if (ts.compareTo(on[i]) == 0) {
                        c = c + 1;
                    }
                }
                op_cnt[i] += c;
            }
        }
        op_cnt[11] -= op_cnt[12] * 2;
        System.out.println("***** "+"Operators and their counts ******");
        for (i = 0; i < on.length; i++) {
            if (on[i].compareTo("[") == 0) {
                on[i] = "[]";
            }
            if(op_cnt[i]>0)
            {
                System.out.println(on[i] + "  " + op_cnt[i]);
                top+=op_cnt[i];
                dop++;
            }
        }
        br.reset();
        ArrayList<String> operand = new ArrayList<>();
        ArrayList<String> var = new ArrayList<>();
        br.mark(1000);
        while ((ln = br.readLine()) != null) {
            ln = ln.trim();
            if (ln.matches("String.*") || ln.matches("long.*") || ln.matches("int.*") || ln.matches("float.*") || ln.matches("short.*")) {
                for (i = 0; i < ln.length(); i++) {
                    if (ln.charAt(i) == ' ') {
                        break;
                    }
                }
                String ts = "";
                for (; i < ln.length(); i++) {
                    if (ln.charAt(i) == '=') {
                        while (i < ln.length() && ln.charAt(i) != ',') {
                            i++;
                        }
                    }
                    if (ln.charAt(i) == '[') {
                        while (ln.charAt(i) != ']' && i < ln.length()) {
                            i++;
                        }
                        i++;
                    }
                    if (i == ln.length()) {
                        continue;
                    }
                    if (ln.charAt(i) == ',') {
                        if (ts.length() > 0) {
                            var.add(ts);
                            var_cnt[var.indexOf(ts)]++;
                        }
                        ts = "";
                    } else if (Character.isLetter(ln.charAt(i))) {
                        ts = ts + ln.charAt(i);
                    }
                }
                if (var.contains(ts) == false) {
                    var.add(ts);
                    var_cnt[var.indexOf(ts)]++;
                }
            }
            for (i = 0; i < ln.length(); i++) {
                String ts = "";
                j = i;
                while (i < ln.length() && Character.isDigit(ln.charAt(i)) == true) {
                    ts = ts + ln.charAt(i);
                    i++;
                }
                if (ts.length() > 0 && (j - 1) >= 0 && (check_operand(ln.charAt(j - 1))||ln.charAt(j-1)==' ')) {
                    //System.out.println("****** "+ts);
                    if (operand.contains(ts) == false) {
                        operand.add(ts);
                        operand_cnt[operand.indexOf(ts)]++;
                        
                    }
                    else
                    {
                        operand_cnt[operand.indexOf(ts)]++;
                    }
                    
                }
            }
        }
        br.reset();
        while((ln=br.readLine())!=null)
        {
            ln=ln.trim();
            if(ln.matches("String.*") || ln.matches("long.*") || ln.matches("int.*") || ln.matches("float.*") || ln.matches("short.*"))
            {
                continue;
            }
            for(i=0;i<ln.length();i++)
            {
                String ts="";
                while(i<ln.length()&&Character.isLetter(ln.charAt(i)))
                {
                    ts=ts+ln.charAt(i);
                    i++;
                }
                if(var.contains(ts)&&(i)<ln.length()&&(check_operand(ln.charAt(i))||ln.charAt(i)=='['||ln.charAt(i)==']'||ln.charAt(i)==' '||ln.charAt(i)==';'))
                {
                    //System.out.println(ln+"  "+ts);
                    var_cnt[var.indexOf(ts)]++;
                }
            }
        }
        int total=0;
        System.out.println("***** "+"Variable and their counts ******");
        for (i = 0; i < var.size(); i++) {
            total+=var_cnt[i];
            System.out.println(var.get(i)+"  "+var_cnt[i]);
        }
        System.out.println("***** "+"Operands and their counts ******");
        for (i = 0; i < operand.size(); i++) {
            total+=operand_cnt[i];
            System.out.println(operand.get(i)+"  "+operand_cnt[i]);
        }
        System.out.println("Total no of operators = "+top+" Distinct no of operators = "+dop);
        System.out.println("Total no of operands = "+total+" Distinct no of operands = "+(operand.size()+var.size()));
        br.close();
        fr.close();
    }

}
