/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab9;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author placements2018
 */
public class Lab9 {

    /**
     * @param args the command line arguments
     */
    
    int gl_var=0;
    int func=0;
    String gl_vars[]= new  String[100];
    HashMap<String, Integer> var_func_count=new HashMap<>();
    void run() {
        String inputData = Helper.getInputFromFile("input.java");
        inputData = Helper.removeQuotes(inputData);
        inputData = Helper.removeSinglelineComments(inputData);
        inputData = Helper.removeMultilineComments(inputData);
        detectClasses(inputData);
    }
    void detectClasses(String inputData){
        Pattern detectclass = Pattern.compile("(class)\\s+(\\w+)\\s*\\{");
        
        Matcher matcher = detectclass.matcher(inputData);
        while (matcher.find()) {
            int openingIndex = matcher.end();
            int closingIndex = Helper.findMatching(inputData, openingIndex);
            System.out.println(matcher.group(2));
            detectFunctions(inputData.substring(openingIndex, closingIndex-1));
            int x=0,y;
            for (int g=0;g<gl_var;g++){
                //System.out.println(gl_vars[g]+" "+var_func_count.get(gl_vars[g]));
                y=var_func_count.get(gl_vars[g]);
                x+=(y*(y-1))/2;
            }
            y=(func*(func-1))/2;
            x=y-2*x;
            System.out.println("LCOM : "+x);
            func=0;
            gl_var=0;
            gl_vars= new  String[100];
            var_func_count=new HashMap<>();
        }
    }
     void detectFunctions(String inputData) {
        Pattern detectFunction = Pattern.compile("(float|int|void|double|char)\\s+(\\w+)\\s*\\((.*?)\\)\\s*\\{");
        String[] datatypes = {"void", "char", "int", "float", "double","public","private","protected","static"};
        String[] misc = {",", ";", "=(?!=)"};
        String data;
        Matcher matcher = detectFunction.matcher(inputData);
        while (matcher.find()) {
            func++;
            int openingIndex = matcher.end();
            int closingIndex = Helper.findMatching(inputData, openingIndex);
            if(func==1){
                //System.out.println(inputData);
                data = findKeywords(inputData.substring(0, matcher.start()), datatypes);
                //data = findMisc(data, misc);
                //System.out.println(data);
                if(matcher.start()>=0)
                    findglvars(data);
                
                for(int g=0;g<gl_var;g++){
                    //System.out.println(gl_vars[g]);
                    var_func_count.put(gl_vars[g], 0);
                }
                
            }
            System.out.println(matcher.group(2)+":- "+matcher.group(3));
            findfuncvars(inputData.substring(openingIndex, closingIndex-1));
        }
        
        
     }
     
     void findglvars(String data) {

        Pattern pattern = Pattern.compile("\\b\\w+\\b");
        Matcher m = pattern.matcher(data);
        while (m.find()) {
            String operand = m.group(0);
            gl_vars[gl_var++]=operand;
        }
    }
     
     void findfuncvars(String data) {

        Pattern pattern = Pattern.compile("\\b\\w+\\b");
        Matcher m = pattern.matcher(data);
        Set <String> s = new HashSet<String>();
        while (m.find()) {
            String operand = m.group(0);
            if(var_func_count.containsKey(operand)){
                s.add(operand);
            }
            
        }
        for (String st:s){
            var_func_count.put(st, var_func_count.get(st)+1);
        }
    }
     
     String findKeywords(String data, String[] keywords) {
        for (String op : keywords) {
            Pattern pattern = Pattern.compile("\\b"+Pattern.quote(op)+"\\b");
            Matcher m = pattern.matcher(data);
            int count = 0;
            while (m.find()) {
                count++;
            }
            data = m.replaceAll(" ");
        }
        return data;
    }

    String findMisc(String data, String[] misc) {
        for (String op : misc) {
            Pattern pattern = Pattern.compile(op);
            Matcher m = pattern.matcher(data);
            int count = 0;
            while (m.find()) {
                count++;
            }
            data = m.replaceAll(" ");
        }
        return data;
    }
    public static void main(String[] args) {
        // TODO code application logic here
        new Lab9().run();
    }
    
}
