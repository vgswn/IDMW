
package lab6;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Lab6 {
    
    HashMap node;
    ArrayList<pair> map; 
    int counter=0;
    

    void run() {
        String inputData = Helper.getInputFromFile("input.c");
        inputData = Helper.removeQuotes(inputData);
        inputData = Helper.removeSinglelineComments(inputData);
        inputData = Helper.removeMultilineComments(inputData);
        CFG(inputData);
    }
    
    void CFG(String inputData){
        node=new HashMap();
        map=new ArrayList<pair>();
        
        String pattern = "(void|int|float|char|double)\\s+(.*)\\(.*?\\)\\s*\\{";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(inputData);
        while (m.find()) {
            int openingIndex = m.end();
            int closingIndex = findMatching(inputData, openingIndex);
            node.put(0, "start");
            processing(0, inputData, openingIndex, closingIndex);
        }
        
    }
    
    ArrayList<Integer> processing(int v,String inputData, int openingIndex, int closingIndex){
        int index=openingIndex;
        int flag=0;
        ArrayList<Integer> returning = new ArrayList<Integer>();
        int latest=v;
        int previous=v;
        while(index<closingIndex){
          //  System.out.println(index+" "+closingIndex);
            String pattern = "(if|while|for|else|else if)\\s*(\\(.*?\\))\\s*\\{";
            Pattern p = Pattern.compile(pattern);
            String ss=inputData.substring(index, closingIndex);
            Matcher m = p.matcher(ss);
            
                    
            if(m.find()){
                flag=1;
                int x=m.end();
                int y=findMatching(ss,x);
                index+=y;
                if(m.group(1).equals("if") || m.group(1).equals("else if")){
                   // System.out.println(m.group(0));
                    previous=counter;
                    counter++;
                    node.put(counter, m.group(0));
                    map.add(new pair(latest,counter));
                    latest=counter;
                   /* if(!returning.isEmpty()){
                        for(int i=0;i<returning.size();i++)
                            map.add(new pair(returning.get(i),counter));
                    }*/
                    returning.addAll(processing(counter,ss,x,y));
                    
                    String pattern2="else\\s*\\{";
                    Pattern p2 = Pattern.compile(pattern2);
                    String ss2=inputData.substring(index, closingIndex);
                    Matcher m2 = p2.matcher(ss2);
                    
                    String pattern3="else if";
                    Pattern p3 = Pattern.compile(pattern3);
                    String ss3=inputData.substring(index, closingIndex);
                    Matcher m3 = p3.matcher(ss3);
                    
                    if(m2.find()){
                        flag=1;
                        x=m2.end();
                        y=findMatching(ss2,x);
                        index+=y;
                        returning.addAll(processing(latest,ss2,x,y));
                    }
                    else if(!m3.find()){
                        returning.add(latest);
                    }
                    
                }
                else if(m.group(1).equals("for") || m.group(1).equals("while")){
                  //  System.out.println(m.group(0));
                    previous=latest;
                    counter++;
                    node.put(counter, m.group(0));
                  //  map.add(new pair(latest,counter)); 
                    latest=counter;
                    if(!returning.isEmpty()){
                        for(int i=0;i<returning.size();i++)
                            map.add(new pair(returning.get(i),counter));
                    }
                    returning=processing(counter,ss,x,y);
                    
                    if(!returning.isEmpty()){
                        for(int i=0;i<returning.size();i++)
                            map.add(new pair(returning.get(i),latest));
                    }
                    
                    counter++;
                    node.put(counter, "end");
                    map.add(new pair(latest,counter));
                    returning.add(latest);
                    returning.add(counter);
                }
            }
            else if(flag==0){
                latest=counter;
                counter++;
                node.put(counter,inputData.substring(index, closingIndex));
              //  System.out.println(inputData.substring(index, closingIndex));
                map.add(new pair(v,counter));
                returning.add(counter);
              //  System.out.println("not finding");
                break;
            }
            else{
                break;
            }
        }
        return returning;
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
    
    void print(){
     
        
        System.out.println(counter);
        int[][] arr=new int[counter+1][counter+1];
        System.out.println("Counter = "+counter);
        for (int i=0; i<counter+1; i++)
            for (int j=0; j<counter+1; j++)
                    arr[i][j]=0;
        
        for(int i=0;i<map.size();i++){
         //   System.out.println(map.get(i).first+"   "+map.get(i).second);
            arr[map.get(i).first][map.get(i).second]=1;
        }
        for (int i=1; i<counter+1; i++)
        {
            System.out.println("");
            for (int j=1; j<counter+1; j++)
            {
                System.out.print(arr[i][j]+" ");
            }
        }
    }
    
    public static void main(String[] args) {
        Lab6 mn=new Lab6();
        mn.run();
        mn.print();
        
    }
    
}