package com.interview.satechnologies;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DuplicateIntegers {

    public static void main(String[] args) {
        System.out.println(findDuplicates(List.of(1,2,3,1,2,1))); // 1, 1, 1, 2, 2 // 1, 2
        System.out.println(findDuplicates(List.of(1,2,3)));
        System.out.println(findDuplicates(List.of(1,1,1,1)));
    }

    public static List<Integer> findDuplicates(List<Integer> input) {
        Set<Integer> elements = new HashSet<>();
        Set<Integer> elementDuplicates = new HashSet<>();

        for(Integer num : input) {
            if(elements.contains(num)) {
                elementDuplicates.add(num);
            } else {
                elements.add(num);
            }
        }

        return new ArrayList<>(elementDuplicates);
    }
}
