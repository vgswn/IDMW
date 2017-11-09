/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;
import java.util.*;
/**
 *
 * @author Placements 2018
 */
public class node {
    String label;
    int number;
    ArrayList<node> next = new ArrayList<node>();

    public node(String title, int no) {
        label = title;
        number = no;
    }
}
