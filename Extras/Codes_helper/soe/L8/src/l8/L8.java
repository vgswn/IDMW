/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package l8;

/**
 *
 * @author Swarnima
 */

import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.*;

public class L8 {
public static int[] ar = new int[24];
int [][] ar1 = new int[40][40];
public static    int [] es = new int [24];
public static    int [] ls = new int [24];
public static char[] ou = new char[20];
    /**
     * @param args the command line arguments
     */
    void run() {
       PrintStream out;    
        try {
            out = new PrintStream(new FileOutputStream("iwm2014003.txt"));
            System.setOut(out);
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
int cn = 0;
String inputData = "";
try {
FileReader in = new FileReader("input.txt");
BufferedReader br = new BufferedReader(in);
String input;
int i = 0;
while ((input = br.readLine()) != null) {
 if(i == 0)
 {
     i = 1;
     continue;
 }
inputData += input + "\n";
String pattern = "(.)\\s+(.*)\\s+(\\d*)";
Pattern p = Pattern.compile(pattern);
Matcher m = p.matcher(input);

    
while(m.find())
{
    cn++;
    String str = m.group(2);
    String s2 = m.group(1),s3 = m.group(3);
    char ch = s2.charAt(0);
//    char ch1 = s3.chatAt()
    int in3 = Integer.parseInt(s3);
    int in1 = (int)ch,in2=0;
 //   System.out.println(in1-65);
  ar[in1-65] = in3;
 // System.out.println(ar[0]);
 // System.out.println((in1-65) + " " +ar[in1-65]);
    str = str.replaceAll("\\s+","");
    int s1=0;
    if(str.contains(","))
    {
  //      System.out.println(ar[0]);
        StringTokenizer st = new StringTokenizer(str,",");
        int max = 0;
        while(st.hasMoreTokens())
        {
            String var = st.nextToken();
            char ch1 = var.charAt(0);
             in2 = (int)ch1;
 //            ar1[in1][in2] = 1;
 //           System.out.print(in2+" ");
                ar1[in1-64][in2-64] = 1;
                ar1[in2-64][in1-64] = 1;
 //               System.out.println("if      "+(in1-65) +" "+(in2-65)+" "+ ar[in2-65] + " ");
                if(max < (ar[in2-65]+es[in2-65]))
                {
                    max = ar[in2-65]+es[in2-65];
                    s1 = in2-65;
                }
        }
 //       System.out.println(max + " " +s1);
        es[in1-65] = max;
//        System.out.println((in1-65)+" "+es[in1-65]);
 //       System.out.println();
    }
    else if(str.equals("-"))
    {
//        System.out.println(in1-65);
        es[in1-65] = 0;
        ar1[0][in1-64] = 1;
        ar1[in1-64][0] = 1;
 //       System.out.println((in1-65)+" "+es[in1-65]);
    }
    else
    {
        in2 = (int)str.charAt(0);
 //       System.out.println((in2-65) +" " +(in1-65));
        ar1[in1-64][in2-64] = 1;
        ar1[in2-64][in2-64] = 1;
        es[in1-65] = ar[in2-65]+es[in2-65];
  //      System.out.println((in1-65)+" "+es[in1-65]);
    }
}
/*int i=0;
for(i=0;i<cn;i++)
{
    System.out.println(es[i]);
}*/
}
} catch (Exception ex) {
ex.printStackTrace();
}
//System.out.println(cn);
int i=0,j=0,m=0,y=0,tm=0;
int ct =0,k=0;
Stack st = new Stack();
for(i=0;i<cn;i++)
{
 //   System.out.println(es[i]);
    if(m < es[i]+ar[i])
    {
        m = es[i]+ar[i];
        y =i;
    }
}
m = m- ar[y];
//System.out.println(y);
/*for(i=0;i<=cn;i++)
{
    for(j=0;j<=cn;j++)
    {
        System.out.print(ar1[i][j]+" ");
    }
    System.out.println();
}*/
i =y+1;
int max = 0;
ou[ct++] = (char)(y+65);
//System.out.println(ou);
/*while(i>0)
{
//    if(
//    System.out.println(i);
    for(j=i;j>=0;j--)
    {
        if(ar1[i][j] == 1 && j != 0)
        {
//            System.out.println(i+" "+j);
            if(j!=0)
            {
//            System.out.println(es[i-1]+" "+es[j-1]);
     /*       if(es[j-1] == (es[i-1]-ar[j-1]))
                    {
                        tm = j;
                 //       System.out.println(j);
                        ou[ct++] =(char)(j+64);
                    }*/
/*            if(max < es[j-1]+ar[j-1])
            {
                max = es[j-1]+ar[j-1];
                tm = j;
            }
            }
        }
        else if(ar1[i][0] == 1)
        {
            tm = -1;
  //          System.out.println(j);
        }
    }
  //  ou[ct++] = (char)(tm+64);
    i = tm;
}*/
//System.out.println(ou);
//ou[0] = (char)()
//System.out.println(i);
//i = i+1;
while(i>0)
{
    max = 0;
    for(j=i;j>=0;j--)
    {
       // System.out.println(j);
        if(ar1[i][j] == 1 && j!=0)
        {
            if(max < es[j-1]+ar[j-1])
            {
 //               System.out.println(max);
                max = es[j-1]+ar[j-1];
  //              System.out.println(max);
                tm = j;
            }
        }
        else if(ar1[i][0] == 1)
        {
            tm = -1;
        }
    }
    ou[ct++] = (char)(tm+64);
  //  System.out.println(max);
    i = tm;
}
System.out.println("Activity	Start Time	Completion Time 	Critical Path");
for(i=0;i<cn;i++)
{
    j = es[i]+ar[i];
    System.out.print((char)(i+65) +"      "+es[i] +"      "+j+"      " );
    for(k=0;k<ct;k++)
    {
        if((int)ou[k] == (i+65))
        {
            System.out.print("  *  " );
        }
    }
    System.out.println();
}
for(i=ct-2;i>=0;i--)
{
    System.out.print(ou[i]+"---> ");
}
 }
    public static void main(String[] args) {
        // TODO code application logic here
        int i,j,k;
        L8 l = new L8();
        l.run();
    }
    
}
