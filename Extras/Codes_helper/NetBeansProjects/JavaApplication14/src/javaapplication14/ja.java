/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication14;

/**
 *
 * @author placements2018
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
public class ja {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
 String s="input.c";
 int n=0;
        FileReader fr=new FileReader(s);
        BufferedReader br=new BufferedReader(fr);
        ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>(105);
        for(int i=0;i<105;i++)
        {
            adj.add(new ArrayList<Integer>());
        }
        HashMap <Integer,Integer> mp=new HashMap<Integer,Integer>();
        int node=0;
        String ln;
        int flag=0;
        int fbr=-1,poslop=0,posif=0,isif=0,islop=0;
        while((ln=br.readLine())!=null)
        {
            if(ln.matches("(void|char|float|double|int){1}.*[(]{1}.*[)]{1}"))
            {
                flag=1;
            }
            if(flag==1)
            {
               // System.out.println(ln);
                if(ln.compareTo("{")==0)
                {
                    if(fbr==-1)
                    {
                        fbr=0;
                    }
                    fbr++;
                }
                else if(ln.compareTo("}")==0)
                {
                    fbr--;
                }
                if(fbr==0)
                {
                    flag=0;
                }
                else
                {
                   if(ln.compareTo("}")==0&&islop==1)
                   {
                     //  System.out.println("Hurrah");
                       adj.get(node).add(poslop);
                       islop=0;
                   }
                   else if(ln.matches("(if).*")||isif==1)
                   {
                       if(isif!=1)
                       {
                        node++;
                        posif=node;
                        mp.put(node,node);
                        if(node-1>=1)
                        {
                            adj.get(node-1).add(node);
                        }
                       }
                       isif=1;
                       ln=br.readLine();
                       ln=br.readLine();
                       node++;
                       adj.get(node-1).add(node);
                       mp.put(node,node);
                       while((ln=br.readLine()).compareTo("}")!=0)
                       {
                           
                       }
                              
      if(islop==1)
                       {
                           adj.get(node).add(poslop);
                       }
                       
                       isif=0;
                       br.mark(100);
                       ln=br.readLine();
                       
                       if(ln.startsWith("else if"))
                       {
                           node++;
                            adj.get(posif).add(node);
                            mp.put(node,node);
                            isif=1;
                            posif=node;
                                         if(islop==1)
                       {
                           adj.get(node).add(poslop);
                       }
                           
                           
                           ln=br.readLine();
                            ln=br.readLine();
                            node++;
                            adj.get(posif).add(node);
                            mp.put(node,node);
                             //System.out.println(ln);
                            while((ln=br.readLine()).compareTo("}")!=0)
                            {

                            }
                            
                            //System.out.println("new");
                           
                       }
 
                       else if(ln.matches("(else).*"))
                        {
                            
                            ln=br.readLine();
                            ln=br.readLine();
                            node++;
                            adj.get(posif).add(node);
                            mp.put(node,node);
                            while((ln=br.readLine()).compareTo("}")!=0)
                            {

                            }
                                         if(islop==1)
                       {
                           adj.get(node).add(poslop);

                       }
                            br.mark(100);
                            ln=br.readLine();
                            //System.out.println("*  "+ln);
                            if(ln.compareTo("}")!=0)
                            {
                                node++;
                                mp.put(node,node);
                                adj.get(node-1).add(node);
                                adj.get(posif+1).add(node);
                                if(ln.matches("(while).*"))
                                {
                                    poslop=node;
                                    islop=1;
                                }
                                else if(ln.matches("(if).*"))
                                {
                                    isif=1;
                                }
                            }
                            else
                            {
                                br.reset();
                            }
                            //isif=0;
                        }
                        else
                        {
                            ln=br.readLine();
                            node++;
                            mp.put(node,node);
                            adj.get(posif).add(node);
                            adj.get(posif+1).add(node);
                            if(ln.matches(".*(if).*"))
                            {
                                isif=1;
                            }
                            else
                            {
                                isif=0;
                                if(ln.matches("(while).*"))
                                {
                                    poslop=node;
                                    islop=1;
                                }
                            }
                            br.reset();
                        }
                   }
                   else if(ln.matches(".*(while).*")||islop==1)
                   {
                        //System.out.println("****** "+ln);
                       if(islop!=1)
                       {
                           node++;
                           adj.get(node-1).add(node);
                           mp.put(node,node);
                           poslop=node;
                       }
                       islop=1;
                   }
                }
            }
        }
       // System.out.println(poslop);
        int i,j;
        for(i=1;i<=node;i++)
        {
            System.out.print(mp.get(i)+"  :  ");
            int x[]=new int[10];
            for(j=0;j<10;j++)
            {
                x[j]=0;
            }
            for(j=0;j<adj.get(i).size();j++)
            {
                x[mp.get(adj.get(i).get(j))]=1;
                //System.out.print(mp.get(adj.get(i).get(j)));
                //System.out.print("   ");
            }
            for(j=0;j<10;j++)
            {
                System.out.print(x[j]+" ");
            }
            System.out.println();
        }
       br.close();
       fr.close();
    }
}