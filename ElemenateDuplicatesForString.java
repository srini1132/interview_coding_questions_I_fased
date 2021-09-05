package com.gap.gid.jesie.bean;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ElemenateDuplicatesForString {

    public static void main(String[] args) {

        String s = "TATA DOCOMO";

        char[] aArr = s.toCharArray();

        Map<Character, Integer> map = new LinkedHashMap<>();

        for(char c: aArr) {
            if(map.keySet().contains(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }

        for (Map.Entry<Character, Integer> entry: map.entrySet()) {
            if (entry.getValue() == 1) {
                System.out.println(entry.getKey());
            }
        }


        //Stream solution
        s.chars().boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream().filter(entry -> entry.getValue() == 1)
                .map(entry -> (char) entry.getKey().intValue())
                .forEach(System.out::println);
    }
}
