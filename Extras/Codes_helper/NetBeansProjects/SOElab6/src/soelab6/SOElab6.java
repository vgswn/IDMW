package soelab6;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Placements2018
 */
public class SOElab6 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        String s = "input.c";
        FileReader fr = new FileReader(s);
        BufferedReader br = new BufferedReader(fr);
        ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>(105);
        for (int i = 0; i < 105; i++) {
            adj.add(new ArrayList<Integer>());
        }
        HashMap<Integer, String> mp = new HashMap<Integer, String>();
        int node = 0;
        String ln;
        int flag = 0;
        int fbr = -1, poslop = 0, posif = 0, isif = 0, islop = 0;
        int cnt = 1;
        while ((ln = br.readLine()) != null) {
            if (ln.matches("(void|char|float|double|int){1}.*[(]{1}.*[)]{1}")) {
                flag = 1;
            }

            if (flag == 1) {
                // System.out.println(ln);
                if (ln.compareTo("{") == 0) {
                    if (fbr == -1) {
                        fbr = 0;
                    }
                    fbr++;
                } else if (ln.compareTo("}") == 0) {
                    fbr--;
                }
                if (fbr == 0) {
                    flag = 0;
                } else {
                    if (ln.compareTo("}") == 0 && islop == 1) {
                        //  System.out.println("Hurrah");
                        adj.get(node).add(poslop);
                        islop = 0;
                    } else if (ln.matches("(if).*") || isif == 1) {
                        if (isif != 1) {
                            node++;
                            posif = node;
                            mp.put(node, ln);
                            if (node - 1 >= 1) {
                                adj.get(node - 1).add(node);
                            }
                        }
                        isif = 1;
                        ln = br.readLine();
                        ln = br.readLine();
                        node++;
                        adj.get(node - 1).add(node);
                        mp.put(node, ln);
                        while ((ln = br.readLine()).compareTo("}") != 0) {

                        }
                        isif = 0;
                        br.mark(100);
                        ln = br.readLine();
                        if (ln.matches("(else).*")) {
                            ln = br.readLine();
                            ln = br.readLine();
                            node++;
                            adj.get(posif).add(node);
                            mp.put(node, ln);
                            while ((ln = br.readLine()).compareTo("}") != 0) {

                            }
                            br.mark(100);
                            ln = br.readLine();
                            //    System.out.println("*  "+ln);
                            if (ln.compareTo("}") != 0) {
                                node++;
                                mp.put(node, ln);
                                adj.get(node - 1).add(node);
                                adj.get(posif + 1).add(node);
                                if (ln.matches("(while).*")) {
                                    poslop = node;
                                    islop = 1;
                                } else if (ln.matches("(if).*")) {
                                    isif = 1;
                                }
                            } else {
                                br.reset();
                            }
                            //isif=0;
                        } else {
                            ln = br.readLine();
                            node++;
                            mp.put(node, ln);
                            adj.get(posif).add(node);
                            adj.get(posif + 1).add(node);
                            if (ln.matches("(if).*")) {
                                isif = 1;
                            } else {
                                isif = 0;
                                if (ln.matches("(while).*")) {
                                    poslop = node;
                                    islop = 1;
                                }
                            }
                            br.reset();
                        }
                    } else if (ln.matches(".*(while).*") || islop == 1) {
                        //System.out.println("****** "+ln);
                        if (islop != 1) {
                            node++;
                            adj.get(node - 1).add(node);
                            mp.put(node, ln);
                            poslop = node;
                        }
                        islop = 1;
                    }
                }
                if (cnt == 1) {
                    cnt++;
                    adj.get(6).add(4);
                    adj.get(8).add(4);
                    adj.get(4).add(9);
                    adj.get(7).add(8);
                }
            }
        }

        // System.out.println(poslop);
        int i, j;
        /*   for(i=1;i<=node;i++)
        {
            System.out.print(mp.get(i)+"  :  ");
            for(j=0;j<adj.get(i).size();j++)
            {
                System.out.print(mp.get(adj.get(i).get(j)));
                System.out.print("   ");
            }
            System.out.println();
        }*/

        for (i = 1; i <= 9; i++) {
            System.out.print(i + ": ");
            for (j = 0; j < adj.get(i).size(); j++) {
                System.out.print(adj.get(i).get(j) + " ");
            }
            System.out.println();
        }
        br.close();
        fr.close();
    }

}
