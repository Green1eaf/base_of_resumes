package com.urise.webapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Lesson12HW2 {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10 ,12 ,15, 16, 1));
        System.out.println("sum = " + list.stream().reduce(Integer::sum).get());
        System.out.println(oddOrEven(list));
    }

    public static List<Integer> oddOrEven(List<Integer> integers) {
        if (integers.stream().reduce(Integer::sum).get() % 2 == 0) {
            return integers.stream().filter(x -> x % 2 != 0).collect(Collectors.toList());
        } else return integers.stream().filter(x -> x % 2 == 0).collect(Collectors.toList());
    }
}
