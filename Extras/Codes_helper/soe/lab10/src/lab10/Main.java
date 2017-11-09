
package lab10;

/**
 *
 * @author Swarnima
 */



import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Main {

    int opCount, uniOp;
    int operCount, uniOper;
    
    void run()  {
             
        PrintStream out;
             
        try {
            out = new PrintStream(new FileOutputStream("output.txt"));
            System.setOut(out);
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
             
        String inputData = Helper.getInputFromFile("input.c");
        inputData = Helper.removeQuotes(inputData);
        inputData = Helper.removeSinglelineComments(inputData);
        inputData = Helper.removeMultilineComments(inputData);
        String pattern = "(void|int|float|char|double)\\s+(.*)\\(.*?\\)\\s*\\{";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(inputData);
         while (m.find()) {
            int openingIndex = m.end();
            int closingIndex = findMatching(inputData, openingIndex);
            System.out.println("operators");
            findOperator(inputData.substring(openingIndex,closingIndex));
            System.out.println("operands");
            findOperand(inputData.substring(openingIndex,closingIndex));
            System.out.println("total operator "+opCount + " "+ "unique operator" + uniOp);
            System.out.println("total operands "+operCount + " "+"unique operands" + uniOper);
        }
        
    }

    void findOperator(String data)
    {
        String oper[] = {"<","=", ">","-",",",";","\\(","\\)","[]","\\{","\\}","[^\\+]\\+[^\\+]","\\+\\+","for","if","int","return"};
        for(String x : oper)
        {
            int c = 0;
            if(x == "[]")
            {
             Pattern pat = Pattern.compile("\\[\\s*\\w+\\s*\\]");
                Matcher m = pat.matcher(data);
                
                while(m.find())
                {
                    c++;
                }
            }
            else{
                Pattern pat = Pattern.compile(x);
                Matcher m = pat.matcher(data);
                
                while(m.find())
                {
                    c++;
                }
            }
            if(x == "[^\\+]\\+[^\\+]"){
                x = "+";
            }
            else{
                x = x.replace("\\", "");
            }
            
            System.out.println(x + " " + c);
            
            if(c != 0 ){
                uniOp++;
                opCount+= c;
            }
        }
    
    }
    
    
    void findOperand(String data)
    {
        //StringBuffer buf = new StringBuffer(data);
        HashSet<String> hs = new HashSet<String>();
        String oper[] = {"<","=", ">","-",",",";","\\(","\\)","[]","\\{","\\}","[^\\+]\\+[^\\+]","\\+\\+","for","if","int","return"};
        for(String x : oper)
        {
            
            
            if(x == "[]")
            {
             data = data.replace("[","$");
             data = data.replace("]","$");
             
                
            }
            else{
                if(x == "[^\\+]\\+[^\\+]"){
                x = "+";
            }
            else{
            x = x.replace("\\", "");
            }
                data = data.replace(x,"$");
            }
          
            
        }   
        
        data = data.replace(" ","$");
        data = data.replace("\t","$");
        data = data.replace("\n","$");
       // System.out.println(data);
        StringTokenizer st = new StringTokenizer(data,"$");
        
        while(st.hasMoreTokens())
        {
            hs.add(st.nextToken());
        }
        
        for(String str : hs)
        {
            int c = 0;
             Pattern pat = Pattern.compile(str);
             Matcher m = pat.matcher(data);
              while(m.find())
              {
                  c++;
              }
              System.out.println(str + " " + c);
              if(c != 0 ){
                uniOper++;
                operCount+= c;
            }
        }
    }
    
    int findMatching(String inputData, int openingIndex) {
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

    boolean filter(String name) {
        Pattern keyword = Pattern.compile("(int|float|void|double|public|private|protected|boolean)");
        Matcher m = keyword.matcher(name);
        if (m.find()) {
            return false;
        } else {
            return true;
        }
    }
    public static void main(String[] args) {
        new Main().run();
    }
}
