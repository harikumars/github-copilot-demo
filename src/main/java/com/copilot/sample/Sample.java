package com.copilot.sample;

import java.util.ArrayList;
import java.util.List;

public class Sample {
    //method to compare two list and return the common elements
    public static List<String> compareList(List<String> list1, List<String> list2) {
        List<String> list = new ArrayList<>();
        for (String t : list1) {
            if (list2.contains(t)) {
                list.add(t);
            }
        }
        return list;
    }

    //method to compare two list and return the common elements without for loop
    public static List<String> compareListWithoutForLoop(List<String> list1, List<String> list2) {
        List<String> list = new ArrayList<>();
        list1.stream().filter(list2::contains).forEach(list::add);
        return list;
    }
    //method to compare two large data list and return the common elements



}
