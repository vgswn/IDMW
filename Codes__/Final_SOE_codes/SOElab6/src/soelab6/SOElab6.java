/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soelab6;

/**
 *
 * @author RAGHAV SABOO
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

class Node {

    String s;
    int ln;
    int ob, cb;
    String type;

    Node(String s, int lno, int ob, int cb, String type) {
        this.s = s;
        this.ln = lno;
        this.ob = ob;
        this.cb = cb;
        this.type = type;
    }
}

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
        HashMap<Integer, Node> mp = new HashMap<Integer, Node>();
        int node = 0;
        String ln, x, y;
        int flag = 0;
        ArrayList<Node> nd = new ArrayList<Node>();
        int lno = 0, lob = 0, lcb = 0, c = 0, tc;
        int lntono[] = new int[1005];
        for (int i = 0; i < 1005; i++) {
            lntono[i] = -1;
        }
        while ((ln = br.readLine()) != null) {
            ln = ln.trim();
            lno++;
            if (lno == 1) {
                //System.out.println(ln);
            }
            if (ln.matches("(void|char|float|double|int){1}.*[(]{1}.*[)]{1}")) {
                br.mark(1000);
                lob = lno + 1;
                tc = lno;
                do {
                    x = br.readLine();
                    x = x.trim();
                    tc++;
                    if (x.compareTo("{") == 0) {
                        c++;
                    } else if (x.compareTo("}") == 0) {
                        c--;
                    }
                } while (c != 0);
                br.reset();
                //System.out.println(tc+" "+br.readLine()+" "+br.readLine());
                Node ob = new Node(ln, lno, lob, tc, "function");
                lntono[lno] = nd.size();
                nd.add(ob);
            } else if (ln.matches("(if).*")) {
                br.mark(1000);
                lob = lno + 1;
                tc = lno;
                c = 0;
                do {
                    x = br.readLine();
                    x = x.trim();
                    tc++;
                    if (x.compareTo("{") == 0) {
                        c++;
                    } else if (x.compareTo("}") == 0) {
                        c--;
                    }
                } while (c != 0);
                br.reset();
                // System.out.println(ln + "  " + lob + "  " + tc);
                Node ob = new Node(ln, lno, lob, tc, "if");
                lntono[lno] = nd.size();
                nd.add(ob);
            } else if (ln.matches("(else).*")) {
                br.mark(1000);
                lob = lno + 1;
                tc = lno;
                do {
                    x = br.readLine();
                    x = x.trim();
                    tc++;
                    if (x.compareTo("{") == 0) {
                        c++;
                    } else if (x.compareTo("}") == 0) {
                        c--;
                    }
                } while (c != 0);
                br.reset();
                Node ob = new Node(ln, lno, lob, tc, "else");
                lntono[lno] = nd.size();
                nd.add(ob);
            } else if (ln.matches("(while).*")) {
                br.mark(1000);
                lob = lno + 1;
                tc = lno;
                do {
                    x = br.readLine();
                    x = x.trim();
                    tc++;
                    if (x.compareTo("{") == 0) {
                        c++;
                    } else if (x.compareTo("}") == 0) {
                        c--;
                    }
                } while (c != 0);
                br.reset();
                Node ob = new Node(ln, lno, lob, tc, "loop");
                lntono[lno] = nd.size();
                nd.add(ob);
            } else if (ln.compareTo("{") != 0 && ln.compareTo("}") != 0) {
                //System.out.println(" **** " + ln);
                lntono[lno] = nd.size();
                Node ob = new Node(ln, lno, -1, -1, "ss");
                nd.add(ob);
            }
        }
        for (int i = 0; i < nd.size(); i++) {
            // System.out.println(nd.get(i).s+nd.get(i).type);
        }
        for (int i = 0; i < nd.size(); i++) {
            s = nd.get(i).s;
            String type = nd.get(i).type;
            if (type.compareTo("function") == 0) {
                lno = nd.get(i).ln;
                lno++;
                while (lno < 1005 && lntono[lno] == -1) {
                    lno++;
                }
                adj.get(i).add(lntono[lno]);
            } else if (type.compareTo("if") == 0) {
                lno = nd.get(i).ln;
                lno++;
                while (lno < 1005 && lntono[lno] == -1) {
                    lno++;
                }
                adj.get(i).add(lntono[lno]);
                lno = nd.get(i).cb;
                while (lno < 1005 && lntono[lno] == -1) {
                    lno++;
                }
                // System.out.println(nd.get(i).cb + "  " + lno + "  " + lntono[lno]);
                if (lno < 1005) {
                    adj.get(i).add(lntono[lno]);
                }
            } else if (type.compareTo("else") == 0) {
                lno = nd.get(i).ln;
                lno++;
                while (lno < 1005 && lntono[lno] == -1) {
                    lno++;
                }
                adj.get(i).add(lntono[lno]);
            } else if (type.compareTo("loop") == 0) {
                lno = nd.get(i).ln;
                lno++;
                while (lno < 1005 && lntono[lno] == -1) {
                    lno++;
                }
                adj.get(i).add(lntono[lno]);
                int cb = nd.get(i).cb;
                while (cb < 1005 && lntono[cb] == -1) {
                    cb++;
                }
                if (cb < 1005 && lntono[cb] != -1) {
                    adj.get(i).add(lntono[cb]);
                }
                //System.out.println("KK  "+cb);
                cb = nd.get(i).cb;
                cb--;
                while (cb >= lno && lntono[cb] == -1) {
                    cb--;
                }
                //System.out.println("KK  "+cb);
                int ccb = lntono[cb];
                if (nd.get(ccb).type.compareTo("ss") == 0) {
                    // System.out.println("******************** "+nd.get(ccb).s);
                    adj.get(ccb).add(i);
                }
                if (nd.get(i).cb - 1 != cb) {
                    lno = nd.get(i).ln;
                    lno++;
                    while (lno < nd.get(i).cb) {
                        //System.out.println("******************** "+lno+"  "+nd.get(i).cb);
                        if (lntono[lno] != -1 && nd.get(lntono[lno]).type.compareTo("if") == 0) {
                            lno++;
                            while (lno < 1005 && lntono[lno] == -1) {
                                lno++;
                            }
                            adj.get(lntono[lno]).add(i);
                            break;
                        }
                        lno++;
                    }
                }
            } else if (type.compareTo("ss") == 0) {
                lno = nd.get(i).ln;
                lno++;
                while (lno < 1005 && lntono[lno] == -1) {
                    lno++;
                }
                if (lno < 1005) {
                    //System.out.println(nd.get(i).s + "  " + nd.get(lntono[lno]).s);
                    {
                        flag = 0;
                        int lim = 0;
                        //System.out.println(adj.get(i).size()+"   "+nd.get(i).s);
                        for (int j = 0; j < adj.get(i).size(); j++) {
                            if (nd.get(adj.get(i).get(j)).type.compareTo("loop") == 0) {
                                flag = 1;
                                lim = nd.get(adj.get(i).get(j)).cb;
                                // System.out.println("........ "+lim+"  "+nd.get(i).s);
                                //break;
                            } else if (nd.get(adj.get(i).get(j)).type.compareTo("else") == 0) {
                                flag = 1;
                            }
                        }
                        if (flag == 1 && lim > lno && nd.get(lntono[lno]).type.compareTo("else") != 0) {
                            // System.out.println("........ "+lno);
                            //System.out.println(nd.get(i).s+"   "+nd.get(lntono[lno]).s);
                            adj.get(i).add(lntono[lno]);
                        } else if (flag == 0 && nd.get(lntono[lno]).type.compareTo("else") != 0) {
                            // System.out.println(nd.get(i).s+"   "+nd.get(lntono[lno]).s);
                            adj.get(i).add(lntono[lno]);
                        } else if (nd.get(lntono[lno]).type.compareTo("else") == 0) {
                            int fx = 0;
                            fx = nd.get(lntono[lno]).cb + 1;
                            while (fx < 1005 && lntono[fx] == -1) {
                                fx++;
                            }
                            if (fx < 1005) {
                                adj.get(i).add(lntono[fx]);
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < 23; i++) {
            if (lntono[i] != -1) {
                // System.out.println(i + "  " + nd.get(lntono[i]).s);
            }
        }
        int i, j, no = adj.size();
        for (i = 0; i < nd.size(); i++) {
            System.out.print(nd.get(i).s + " : ");
            for (j = 0; j < adj.get(i).size(); j++) {
                System.out.print(nd.get(adj.get(i).get(j)).s + "   ");
            }
            System.out.println();
        }

        for (i = 0; i < nd.size(); i++) {
            System.out.print(nd.get(i).s);
            for (j = nd.get(i).s.length(); j < 30; j++) {
                System.out.print(" ");
            }
            for (j = 0; j < nd.size(); j++) {
                if (adj.get(i).contains(j)) {
                    System.out.print("1 ");
                } else {
                    System.out.print("0 ");
                }
            }
            System.out.println();
        }

    }

}
