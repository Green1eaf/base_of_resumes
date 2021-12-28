package com.urise.webapp;

import java.util.Arrays;

public class Lesson12HW1 {

    public static void main(String[] args) {
        int[] arr = {3, 2, 3, 2, 3, 3, 1, 2};
        System.out.println(minValue(arr));
    }

    public static int minValue(int[] values) {
        return Arrays.stream(values)
                .distinct()
                .sorted()
                .reduce((a, b) -> 10 * a + b).getAsInt();
    }
}
