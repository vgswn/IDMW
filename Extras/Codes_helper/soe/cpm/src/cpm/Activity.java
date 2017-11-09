package cpm;

import java.util.*;

public class Activity {
    String name;
    int startTime;
    ArrayList<Activity> predecessor;
    ArrayList<String> successor;
    int duration;
    int completionTime;
    boolean isCritical;
    int ls,lf;
    public Activity(String activityName, ArrayList<Activity> pre, int dur) {
        name = activityName;
        predecessor = pre;
        duration = dur;
        this.successor = new ArrayList<String>();
    }

    /*public int compareTo(Activity b) {
        return this.startTime - (b.startTime);
    }*/
}

