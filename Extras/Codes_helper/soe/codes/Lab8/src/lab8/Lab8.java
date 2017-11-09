package lab8;
import java.io.*;
import java.util.*;
public class Lab8 {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        FileReader f1 = new FileReader("input.txt");
        BufferedWriter f2 = new BufferedWriter( new FileWriter("output.txt"));
        int temp ;
        String text="";
        boolean flag=false;
        while((temp = f1.read())!=-1){
           char c = (char) temp;
           if(c==')')
           {
               flag=true;
           }
           if(flag && c!=' ' && c!=')'){
               text=text+c;
           }
        }
        text=text.replaceAll("\\s+","");
        //System.out.println(text);
        int i=0,l=text.length();
        int[] map=new int[50];
        int[] start=new int[50];
        int[] cmp=new int[50];
        String st="";
        for(i=0;i<50;i++){
            start[i]=-1;
            cmp[i]=-1;
        }
        int[][] dep=new int[50][50];
        int[] du=new int[50];
        int[] size =new int[50];
        i=0;
        //int l=text.length();
        int count=0;
        while(i<l){
            int ind=i,it;
            if(text.charAt(i+1)=='-'){
                size[(int)text.charAt(ind)-65]=0;
                //System.out.println((int)text.charAt(ind)-65);
                it=i+2;
                count++;
            }else{
                it=i+1;
                int p=0;
                while(!(((int)text.charAt(it))>=48 && ((int)text.charAt(it))<=57)){
                    if(text.charAt(it)!=','){
                        dep[((int)text.charAt(ind))-65][p++]=(int)text.charAt(it)-65;
                    }
                    it++;
                }
                size[(int)text.charAt(ind)-65]=p;
                count++;
            }
            int ii=it;
            while(ii<l && (int)text.charAt(ii)>=48 && (int)text.charAt(ii)<=57){
                st=st+text.charAt(ii);
                ii++;
            }
            du[(int)text.charAt(ind)-65]=Integer.parseInt(st);
            st="";
            i=ii;
        }
        int[] par=new int[50];
        for(i=0;i<count;i++){
            if(size[i]==0){
                start[i]=0;
                cmp[i]=du[i];
                par[i]=-1;
            }
        }
        int j;
        int mind=0,maxx;
        for(i=0;i<count;i++){
            if(size[i]!=0){
                maxx=-9;
                for(j=0;j<size[i];j++){
                    if(cmp[dep[i][j]]>maxx){
                        maxx=cmp[dep[i][j]];
                        mind=j;
                    }
                }
                par[i]=dep[i][mind];
                start[i]=maxx;
                cmp[i]=start[i]+du[i];
            }
        }
        
        maxx=-1;
        for(i=0;i<count;i++){
            if(maxx<cmp[i]){
                maxx=cmp[i];
                mind=i;
            }
        }
        int c=0;
        Stack stt = new Stack();
        int[] cpt=new int[50];
        while(mind!=-1){
            stt.push(mind);
            cpt[mind]=1;
            mind=par[mind];
            c++;
        }
        //stt.push()
        f2.write("Activity            StartTime            EndTime            CriticalPath");
        f2.newLine();
        for(i=0;i<count;i++){
            f2.write((char)(i+65)+"                    ");
            String formatted = String.format("%02d", start[i]);
            f2.write(formatted+"                      ");
            f2.write(cmp[i]+"                      ");
            if(cpt[i]==1){
                f2.write("*");
            }
            f2.newLine();
        }
        f2.write("******************Critical Path***********************\n");
        f2.newLine();
        //System.out.println(count);
        while(!stt.empty()){
            f2.write(((char)((int)stt.peek()+65)));
            //System.out.print(((char)((int)stt.peek()+65)));
            stt.pop();
            if(!stt.empty()){
                f2.write("->");
              //  System.out.print("->");
            }else{
                f2.newLine();
                //System.out.println("\n");
            }
        }
        f2.close();
    }
}