/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soe_lab_8;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author vips
 */
public class SOE_LAB_8 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        FileReader f=new FileReader("input.c");
        BufferedReader b=new BufferedReader(f);
        String ln=null;
        String s="",qq="";
        int a=0,aa=0;
        int c=0,cc=0;
        while((ln=b.readLine())!=null)
        {
           // System.out.println(ln);
           

          
            s=s+ln;
            s=s+" ";
            
        }
        Pattern x=Pattern.compile("(/\\*.*?\\*/)");
        Matcher pp=x.matcher(s);
        while(pp.find())
        {
            
        }
        System.out.println(s);

        s=pp.replaceAll(" ");
        System.out.println(s);
        
//        x=Pattern.compile(".*(\\){2}\\n");
        HashMap<String,Integer> h=new HashMap<String,Integer>();
         String regex[]={"<", ">", "==","=","(", ")", "}", "{", "[","++", "--", "+", "-", "/", "*", "%","for", "while", "if", "switch", "case", "else", "default",
            "void", "char", "int", "float", "double","return", "break", "continue",",", ";", "=(?!=)"};
        // System.out.println(regex.length);
         int i;
         String ne="";
         for (i=0;i<regex.length;i++)
         {
             Pattern p=Pattern.compile(Pattern.quote(regex[i]));
             Matcher m=p.matcher(s);
             while(m.find())
             {
                 a++;
                 int k=0;
                 if(h.containsKey(regex[i]))
                     
                         k=h.get(regex[i]);
                 
                 
                 h.put(regex[i], k+1);
                 
             }
                          s=m.replaceAll(" ");

             
         }
         aa=h.size();
         //System.out.println(s);
         
        Pattern pattern = Pattern.compile("\\b\\w+\\b");
        Matcher mm = pattern.matcher(s);        
        while(mm.find())
        {
            c++;
            int k=0;
            String q=mm.group(0);
          //  System.out.println(q);
                 if(h.containsKey(q)==true){
                     
                     k=h.get(q);
                     //System.out.println(k);
                     //System.out.println("egh");
                     
                 }
                 
                 h.put(q, k+1);
        }
        cc=h.size()-aa;
        for(String key:h.keySet())
         {
             System.out.println(key+" : "+h.get(key));
         }
        System.out.println("Unique Operators : "+aa);
        System.out.println("Unique Operands : "+cc);
        System.out.println("Total Operators : "+a);
        System.out.println("Total Operands : "+c);
       
    
    
}
}