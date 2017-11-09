
package test;

/**
 *
 * @author Swarnima
 */

import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.*;

public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
             PrintStream out;    
        try {
            out = new PrintStream(new FileOutputStream("iwm2014003.txt"));
            System.setOut(out);
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        String inp;
        int c = 0;
        int x = 0,ri =0;
        String ans[][] = null;
        try {
FileReader in = new FileReader("input.txt");
BufferedReader br = new BufferedReader(in);
while ((inp = br.readLine()) != null) {
 //   System.out.println(input);
    inp = inp.replaceAll("\\s+",",");
//    System.out.println(inp);
 if(c==0)
 {
     c = 1;
     StringTokenizer st = new StringTokenizer(inp,",");
     x = st.countTokens();
 //    System.out.println(x);
     ans = new String[100][x+5];
 }
            else{
                if(!inp.contains("Actions"))
                {
                    String pattern = "(\\(\\d+%\\))";
                    Pattern p = Pattern.compile(pattern);
                    Matcher m = p.matcher(inp);
                    if(m.find())
                    {
                        inp = inp.replaceAll(pattern,"");
                    }
                    StringTokenizer st = new StringTokenizer(inp,",");
            //        inp = inp.replaceAll("","");
                    int i=0;
                    while(st.hasMoreTokens())
                    {
                        ans[ri][i++] = st.nextToken();
                    }
                               ri++;     
                }
            
            }
}
        }
        catch (Exception ex) {
ex.printStackTrace();
}
         for(int j = 1 ; j<x;j++)
        {
            for(int i=0;i<ri-1;i++)
            {
                System.out.print(ans[i][j]+" "+ans[i][0]+"->");
            }
            System.out.println(ans[ri-1][j]);
            
        }
}
    
}
