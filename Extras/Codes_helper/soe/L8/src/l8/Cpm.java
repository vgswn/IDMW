/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpm;

/**
 *
 * @author apoorv
 * whysoseriousss ??
 */
import java.io.*;
import java.util.*;

public class Cpm {

    /**
     * @param args the command line arguments
     */
    static int gr[][]=new int[100][100];
    static int par[][]=new int[100][100];
    static boolean visited[]=new boolean[100];
    static Stack st=new Stack();
    static int cp[]=new int[100];
    static int c=1;
    static int time[]=new int[100];
    static int stime[]=new int[100];
    static int ctime[]=new int[100];
    static Map <String,Integer> m=new HashMap <String,Integer> ();
    static Map <Integer,String> im=new HashMap <Integer,String> ();
    public static void main(String[] args)throws Exception {
        String a;
        String b;
        
        time[0]=0;
        stime[0]=0;
        ctime[0]=0;
        FileReader r=new FileReader("input.txt");
        BufferedReader k=new BufferedReader(r);
        String br;
        
        br=k.readLine();
        
        m.put("-",0);
        while((br=k.readLine())!=null){
        String[] data = br.split("(,|\\s)\\s+");
        int i;
        if(!m.containsKey(data[0]))
        {
            m.put(data[0],c);
            im.put(c,data[0]);
            c++;
        }
        for(i=1;i<data.length-1;i++)
        {
            if(!m.containsKey(data[0]))
            {
            m.put(data[i],c);
            im.put(c,data[i]);
            c++;
            }
            gr[m.get(data[i])][m.get(data[0])]=1;
            par[m.get(data[0])][m.get(data[i])]=1;
        }
        int t=Integer.parseInt(data[data.length-1]);
        time[m.get(data[0])]=t;
        }
        
        int i,j;
        /*for(i=0;i<c;i++){
            for(j=0;j<c;j++)
                System.out.print(gr[i][j]+" ");
        System.out.println();
        
        }*/
    for(i=1;i<c;i++)
            if(!visited[i])
        dfs(i);
    int cmax=0;
    while(!st.empty())
    {
     i=(int)st.pop();
     int max=0;
     for(j=0;j<c;j++)
     {
         if(par[i][j]==1)
         max=max<ctime[j]?ctime[j]:max;   
     }
     
    stime[i]=max;
    ctime[i]=max+time[i]; 
    cmax=cmax<ctime[i]?ctime[i]:cmax;   
    }
    Stack s1=new Stack();
    for(i=0;i<c;i++)
    {
        if(ctime[i]==cmax){
            s1.push(i);
            func(s1,i);
            if(!s1.empty())
            s1.pop();
        }
    }
    System.out.println("Activity\t"+"Start Time\t"+"Completion Time\t"+"Critical Path\t");
    
    for(i=1;i<c;i++)
    {
        System.out.print(im.get(i)+"\t\t"+stime[i]+"\t\t"+ctime[i]+"\t\t");
        if(cp[i]==1)
            System.out.print("*");
    System.out.println();
    }
        
    }
    
    
    
    static void dfs(int s){
    visited[s]=true;
    int i;
    for(i=1;i<c;i++)
    {
        if(gr[s][i]==1 && !visited[i])
            dfs(i);
    }
    st.push(s);
    }
    
    static void func(Stack s,int i)
    {
        cp[i]=1;
        int j;
        int cmax=0;
        int flag=0;
        for(j=1;j<c;j++){
            if(par[i][j]==1){
                flag=1;
                cmax=cmax<ctime[j]?ctime[j]:cmax;
            }
        }
        if(flag==0)
        {
            
            int a[]=new int[100];
            int d=0;
            while(!s.empty())
            {   a[d++]=(int)s.peek();
                System.out.print(im.get((int)s.pop())+"-->");
            
             }
            for(i=d-1;i>=0;i--)
                s.push(a[i]);
            System.out.println();
            return;
        }
        for(j=1;j<c;j++)
        {
            if(ctime[j]==cmax && par[i][j]==1)
            { 
                s.push(j);
                func(s,j);
                if(!s.empty())
                s.pop();
            }
        }
    }
}
