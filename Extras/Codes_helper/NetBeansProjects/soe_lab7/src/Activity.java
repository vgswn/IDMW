/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Placements 2018
 */
import java.util.*;

public class Activity {
    String name;
    int startTime;
    ArrayList<Activity> predecessor;
    int duration;
    int completionTime;
    boolean isCritical;

    public Activity(String name, ArrayList<Activity> predecessor, int duration) {
        this.name = name;
        this.predecessor = predecessor;
        this.duration = duration;
    }


}


