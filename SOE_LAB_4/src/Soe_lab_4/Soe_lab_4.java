/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Soe_lab_4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author vips
 */
public class Soe_lab_4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        String s="input.c";
        FileReader f=new FileReader(s);
        BufferedReader b=new BufferedReader(f);
        String ln=null;
        int cnt=0;
        int C=0;
        int t=0;
        int n=0;
        int a[]=new int[10];
        while((ln=b.readLine())!=null)
        {
            
            if(ln.matches(".*(void|int|double ).*"))
            {
                    String q=ln;
                    int w=0;
                    ln=b.readLine();
                   if(ln.matches(".*\\{{1}.*"))
                        cnt++;
                   else
                       continue;
                    
                    C++; 
                    int e=q.indexOf("(");
                   q=q.substring(0, e);
                    
                   System.out.print( q+ " C"+C+" : ");
                   
                    while(cnt!=1)
                    {
                        ln=b.readLine();
                        
                        if(ln==null)
                            break;
                        if(ln.matches(".*\\{{1}.*"))
                            cnt++;
                       if(ln.matches(".*\\}{1}.*"))
                            cnt--;
                        if (ln.matches(".*(if).*"))
                            w++;
                        
                    }
                    w=w+1;
                    a[n++]=w;
                    t=t+w;
                    System.out.println(" "+w);
                
                
            }
        }
        System.out.print("Total = ");
        int i;
        for (i=0;i<n;i++){
            String ss=" + ";
            if (i==n-1)
                ss=" = ";
            System.out.print(a[i]+ss);
        }
        System.out.println(t);
        
        
        
    }
    
}
