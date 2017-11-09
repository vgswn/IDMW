/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Placements 2018
 */
import java.io.*;
import java.util.*;


public class Main {

    ArrayList<Activity> table;
    ArrayList<String> criticalPath = new ArrayList<String>();

    public static void main(String[] args) {
        new Main().calc();
    }

    public void calc() {
        table = new ArrayList<Activity>();
        try {
            FileReader fr = new FileReader("input.txt");
            BufferedReader br = new BufferedReader(fr);
            String ip;
            ip = br.readLine();
            while ((ip = br.readLine()) != null) {
                String[] tokens = ip.split("\t\t\t");
                ArrayList<Activity> predecessor = this.findPredecessor(tokens[1]);
                table.add(new Activity(tokens[0], predecessor, Integer.parseInt(tokens[2])));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        for (Activity activity : table) {
            if (activity.predecessor.size() == 0) {
                activity.startTime = 0;
                activity.completionTime = activity.duration;
            } else {
                activity.startTime = findMax(activity.predecessor);
                activity.completionTime = activity.startTime + activity.duration;
            }
        }
        this.findCritical(table);

        System.out.println("Activity" + "    " + "Start Time" + "   " + "Completion Time" + "   " + "Critical Path");
        for (Activity activity : table) {
            System.out.println(activity.name + "\t\t" + activity.startTime + "\t \t" + activity.completionTime + "\t\t" + (activity.isCritical ? "*" : " "));
        }
        System.out.println();
        System.out.print("The critical path is ");
        int i;
        for (i = criticalPath.size() - 1; i > 0; i--) {
            System.out.print(criticalPath.get(i) + "->");
        }
        System.out.print(criticalPath.get(i) + ".");
        System.out.println();

    }

    public void findCritical(ArrayList<Activity> activities) {
        int maxTime = findMax(activities);
        for (Activity activity : activities) {
            if (activity.completionTime == maxTime) {
                activity.isCritical = true;
                criticalPath.add(activity.name);
                findCritical(activity.predecessor);
            }
        }
    }

    public ArrayList<Activity> findPredecessor(String data) {
        ArrayList<Activity> predecessor = new ArrayList<Activity>();
        if (!data.equals("-")) {
            String[] names = data.split(",");
            for (String name : names) {
                predecessor.add(search(name));
            }
        }
        return predecessor;
    }

    public Activity search(String name) {
        name = name.trim();
        for (int i = 0; i < table.size(); i++) {
            if (table.get(i).name.equals(name)) {
                return table.get(i);
            }
        }
        return null;
    }

    public int findMax(ArrayList<Activity> activities) {
        int max = 0;
        for (Activity activity : activities) {
            if (activity.completionTime > max) {
                max = activity.completionTime;
            }
        }
        return max;
    }
}
