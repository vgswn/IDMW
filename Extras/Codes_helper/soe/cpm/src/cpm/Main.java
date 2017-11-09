package cpm;

import java.util.*;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Main {
    
    ArrayList<Activity> table;
    ArrayList<ArrayList<String> > allCriticalPath = new ArrayList<ArrayList<String> >();
    ArrayList<String> criticalPath = new ArrayList<String>();

    void run() {
        String inputData = Helper.getInputFromFile("input.txt");
        createTable(inputData);
        calculateTime();
        findCritical(table);
        printTable();
    }

    void calculateTime() {
        for (Activity activity : table) {
            if (activity.predecessor.size() == 0) {
                activity.startTime = 0;
                activity.completionTime = activity.duration;
            } else {
                activity.startTime = findMax(activity.predecessor);
                activity.completionTime = activity.startTime + activity.duration;
            }
        }
        
        for(int i = table.size() - 1;  i>=0; i--){
            
           if(table.get(i).successor.size()==0){
                table.get(i).lf = findMaxtime();
                table.get(i).ls = table.get(i).lf - table.get(i).duration;
            }
            else if(table.get(i).successor.size()!=0){
                int min = 99999;
                for(int j=0; j<table.get(i).successor.size(); j++){
                    Activity ssd  = search(table.get(i).successor.get(j));
                    if(ssd.ls < min){
                        min = ssd.ls;
                    }
                 
                }
                table.get(i).lf = min;
                table.get(i).ls = table.get(i).lf - table.get(i).duration;
            }
             //System.out.println("sucucc of " + table.get(i).name + " hahaha " + table.get(i).ls);
        }
    }
    int findMaxtime(){
        int max = 0;
        for(Activity act : table){
            int time = act.completionTime;
            if(time > max)
                max = time;
        }
        return max;
    }
    void findCritical(ArrayList<Activity> activities) {
        ArrayList<Activity> crit = new ArrayList<Activity>();
        int maxTime = findMax(activities);
        for (Activity activity : activities) {
            if (activity.completionTime == maxTime) {
                markCritical(activity);
            }
        }
    }

    void markCritical(Activity activity) {
        activity.isCritical = true;
        criticalPath.add(activity.name);
//        findCritical(activity.predecessor);
        if(activity.predecessor.size() == 0) {
            for(int i = criticalPath.size()-1; i >= 0; i--) {
                System.out.print(criticalPath.get(i));
            }
            //allCriticalPath.add(criticalPath);
            System.out.println();
        }
        findCritical(activity.predecessor);
        criticalPath.remove(criticalPath.size()-1);
 }

    void createTable(String data) {
        table = new ArrayList<Activity>();
        String[] rows = data.split("\n");
        for (int i = 1; i < rows.length; i++) {
            table.add(createActivity(rows[i]));
        }
    }

    Activity createActivity(String line) {
        String[] details = line.split("\\s\\s+");
        String activityName = details[0];
        ArrayList<Activity> predecessor = findPredecessor(details[1],details[0]);
        int duration = Integer.parseInt(details[2]);
        return new Activity(activityName, predecessor, duration);
    }

    ArrayList<Activity> findPredecessor(String data, String base) {
        ArrayList<Activity> predecessor = new ArrayList<Activity>();
        if (!data.equals("-")) {
            String[] names = data.split(",");
            for (String name : names) {
                predecessor.add(search(name));
                search(name).successor.add(base);   
            }
        }
        return predecessor;
    }

    Activity search(String name) {
        name = name.trim();
        for (int i = 0; i < table.size(); i++) {
            if (table.get(i).name.equals(name)) {
                return table.get(i);
            }
        }
        return null;
    }

    int findMax(ArrayList<Activity> activities) {
        int max = 0;
        for (Activity activity : activities) {
            if (activity.completionTime > max) {
                max = activity.completionTime;
            }
        }
        return max;
    }

    void printTable() {
        System.out.println("Critical Path");
        System.out.println("");
        System.out.println("");
        for (Activity activity : table) {
            System.out.println(activity.name + " " + activity.ls + " " + activity.lf + " " + activity.isCritical);
        }
    }
            
    public static void main(String[] args) {
        new Main().run();
    }
}

