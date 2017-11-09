/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soelab7;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import static soelab7.SOElab7.dfs;

/**
 *
 * @author placements2018
 */
public class SOElab7 {

    static void dfs(int u, ArrayList<Integer> topo, int vis[], ArrayList<ArrayList<Integer>> adj) {
        vis[u] = 1;
        int i;
        for (i = 0; i < adj.get(u).size(); i++) {
            if (vis[adj.get(u).get(i)] == 0) {
                dfs(adj.get(u).get(i), topo, vis, adj);
            }
        }
        topo.add(u);
    }

    public static void main(String args[]) throws FileNotFoundException, IOException {
        String s = "C:\\Users\\RAGHAV SABOO\\Documents\\NetBeansProjects\\SOElab7\\src\\soelab7\\input.txt";
        FileReader fr = new FileReader(s);
        BufferedReader br = new BufferedReader(fr);
        String ln;
        HashMap<Integer, String> hm = new HashMap<Integer, String>();
        HashMap<String, Integer> rhm = new HashMap<String, Integer>();
        int x = 0, i;
        br.mark(1000);
        while ((ln = br.readLine()) != null) {
            if (ln.matches("Activity.*")) {
                continue;
            } else {
                s = "";
                for (i = 0; i < ln.length(); i++) {
                    if (ln.charAt(i) == ' ') {
                        break;
                    }
                    s = s + ln.charAt(i);
                }
                //System.out.println(s+" "+x);
                hm.put(x, s);
                rhm.put(s, x);
                x++;
            }
        }
        //System.out.println(x);
        br.reset();
        int d[] = new int[x];
        int j = 0;
        int edg[][] = new int[1005][2];
        int m = 0;
        while ((ln = br.readLine()) != null) {
            if (ln.matches("Activity.*")) {
                continue;
            } else {
                int c = 0;
                for (i = 0; i < ln.length(); i++) {
                    if (ln.charAt(i) == ' ') {
                        c++;
                    }
                    if (c == 2) {
                        break;
                    }
                }
                int no = 0;
                i++;
                for (; i < ln.length(); i++) {
                    no *= 10;
                    no += (ln.charAt(i) - '0');
                }

                for (i = 0; i < ln.length(); i++) {
                    if (ln.charAt(i) == ' ') {
                        break;
                    }
                }
                i++;
                // System.out.println(i);
                s = "";
                for (; i < ln.length(); i++) {
                    if (ln.charAt(i) == ' ') {
                        break;
                    }
                    if (ln.charAt(i) == ',') {
                        edg[m][0] = rhm.get(s);
                        edg[m][1] = j;
                        m++;
                        s = "";
                    }
                    //else
                    if (ln.charAt(i) != '-' && ln.charAt(i) != ',') {
                        s = s + ln.charAt(i);
                    }
                }
                if (rhm.containsKey(s)) {
                    edg[m][0] = rhm.get(s);
                    edg[m][1] = j;
                    m++;
                }
                d[j++] = no;
            }
        }
        for (Iterator<Map.Entry<String, Integer>> it = rhm.entrySet().iterator(); it.hasNext();) {
            Map.Entry mm = it.next();
            // System.out.println(mm.getKey()+" "+mm.getValue()); 
        }
        for (i = 0; i < m; i++) {
            //System.out.println(d[i]);
            //System.out.println(edg[i][0] + "  " + edg[i][1]);
        }
        Scanner sc = new Scanner(System.in);
        int n, a, b;
        n = x;
        int dur[] = new int[n];
        int fin[] = new int[n];
        int start[] = new int[n];
        ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>(n);
        ArrayList<ArrayList<Integer>> par = new ArrayList<ArrayList<Integer>>(n);
        ArrayList<Integer> topo = new ArrayList<Integer>();
        for (i = 0; i < n; i++) {
            adj.add(new ArrayList<Integer>());
            par.add(new ArrayList<Integer>());
        }
        int vis[] = new int[n];
        for (i = 0; i < n; i++) {
            vis[i] = 0;
        }
        for (i = 1; i <= m; i++) {
            adj.get(edg[i - 1][0]).add(edg[i - 1][1]);
            par.get(edg[i - 1][1]).add(edg[i - 1][0]);
        }
        for (i = 0; i < n; i++) {
            dur[i] = d[i];
        }
        int ss = 0;
        for (i = 0; i < n; i++) {
            if (par.get(i).size() == 0) {
                ss = i;
                break;
            }
        }
        dfs(ss, topo, vis, adj);
        int tim = 0;
        for (i = topo.size() - 1; i >= 0; i--) {
            tim = 0;
            for (j = 0; j < par.get(topo.get(i)).size(); j++) {
                if (tim < fin[par.get(topo.get(i)).get(j)]) {
                    tim = fin[par.get(topo.get(i)).get(j)];
                }
            }
            start[topo.get(i)] = tim;
            fin[topo.get(i)] = tim + dur[topo.get(i)];
        }
        ArrayList<Integer> path = new ArrayList<Integer>();
        path.add(ss);
        a = ss;
        while (true) {
            tim = 0;
            b = 0;
            for (i = 0; i < adj.get(a).size(); i++) {
                if (tim < fin[adj.get(a).get(i)]) {
                    tim = fin[adj.get(a).get(i)];
                    b = adj.get(a).get(i);
                }
            }
            a = b;
            path.add(a);
            if (adj.get(a).size() == 0) {
                break;
            }
        }
        for (i = 0; i < n; i++) {
            System.out.println(hm.get(i) + "  " + start[i] + "   " + fin[i]);
        }
        System.out.print("Critical path : ");
        for (i = 0; i < path.size(); i++) {
            System.out.print(hm.get(path.get(i)));
            if (i < path.size() - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }
}
