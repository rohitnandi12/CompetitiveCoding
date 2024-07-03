package com.codesignal;

import java.util.List;

/**
 <a href="https://codesignal.com/blog/interview-prep/example-codesignal-questions/">Blog Link</a>

 Question 1: Array Manipulation
 Given an array a, your task is to output an array b of the same length by applying the following transformation:
 – For each i from 0 to a.length - 1 inclusive, b[i] = a[i - 1] + a[i] + a[i + 1]
 – If an element in the sum a[i - 1] + a[i] + a[i + 1] does not exist, use 0 in its place
 – For instance, b[0] = 0 + a[0] + a[1]

 Example

 For a = [4, 0, 1, -2, 3]:
 – b[0] = 0 + a[0] + a[1] = 0 + 4 + 0 = 4
 – b[1] = a[0] + a[1] + a[2] = 4 + 0 + 1 = 5
 – b[2] = a[1] + a[2] + a[3] = 0 + 1 + (-2) = -1
 – b[3] = a[2] + a[3] + a[4] = 1 + (-2) + 3 = 2
 – b[4] = a[3] + a[4] + 0 = (-2) + 3 + 0 = 1

 So, the output should be solution(a) = [4, 5, -1, 2, 1].
 **/
public class ArrayManipulation {

    public static List<Integer> arrayManipulation(List<Integer> input) {

        List<Integer> output = new java.util.ArrayList<>(List.copyOf(input));

        for(int i=0; i<input.size(); i++) {
            Integer sumOfNeighbours = i > 0 ? input.get(i-1) : 0;
            sumOfNeighbours += i < input.size()-1 ? input.get(i+1) : 0;

            output.set(i, output.get(i) + sumOfNeighbours);
        }

        return output;
    }

    public static void print(List<Integer> list) {
        list.forEach(l -> System.out.print(l + ","));
        System.out.println();
    }
    public static void main(String[] args) {
        print(arrayManipulation(List.of(4, 0, 1, -2, 3)));
    }
}
