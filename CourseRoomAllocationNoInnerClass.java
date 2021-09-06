package com.gap.gid.jesie.bean;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Problem Statement:
 *
 * Given: 2 integer arrays, one integer 'n'
 *      Here 1st Array having course start time (like if value is 1 it is 1:00AM and if value is 13 it is 1:00PM)
 *      And in 2nd array we are having duration of course in hours with respective index to 1st array
 *      Ex: 1st array [2,13], 2nd array [14,1] here array size 2 so count of courses we have 2
 *          course 1 started at 2(2:00AM) and duration is 14 hours so this course can complete by 2 + 14 = 16(4:00PM)
 *          course 2 started at 13(1:00PM) and duration is 1 hour so this course can complete by 13 + 1 = 14(2:00PM)
 *
 *      And integer 'n' rooms available to conduct course. Here in 1 room we can conduct 1 course at a time
 *
 * Then:
 *      Need to find which room has chance to run maximum numbers of courses
 *
 *
 * Example:
 *  int roomCount = 2;
 *  int[] courseStartTimeArray = {1, 2, 5};
 *  int[] courseDurationArray = {6, 1, 2};
 *
 *  Output: 2
 *
 *  If in 1st room course 1 started at 1 and duration is 6, so room can free by 1 + 6 = 7
 *  And in 2nd room course 2 started at 2 and duration is 1, so room can free by 2 + 1 = 3
 *  Here 3rd course need to start by 5 and room 1 not in free till 7, so we can not take room 1 for course 3
 *  and by this time room is free, so course can conduct in room 2 only.
 *
 *  Here room 2 has chance to conduct maximum number of courses
 *
 *
 *
 *
 *  Note: Here I am checking for free room always in the order 1 to n
 */
public class CourseRoomAllocationNoInnerClass {

    public static void main(String[] args) {
        int roomCount = 3;
        int[] courseStartTimeArray = {1, 2, 5, 6, 14, 15};
        int[] courseDurationArray = {15, 14, 1, 1, 1, 12};

        int keyCount = 0;
        Map<Integer, List<Integer[]>> map = new HashMap<>();

        for (int i = 0; i < courseDurationArray.length; i++) {
            if (keyCount < roomCount) {
                List<Integer[]> list = new ArrayList<>();
                list.add(new Integer[]{courseStartTimeArray[i], courseDurationArray[i]});
                map.put(++keyCount, list);
            } else {
                for (Map.Entry<Integer, List<Integer[]>> entry : map.entrySet()) {
                    Integer[] lastCourse = entry.getValue().get(entry.getValue().size() - 1);

                    if (lastCourse[0] + lastCourse[1] <= courseStartTimeArray[i]) {
                        entry.getValue().add(new Integer[]{courseStartTimeArray[i], courseDurationArray[i]});
                        break;
                    }
                }
            }
        }

        map.forEach((key, val) -> {
            List<List<Integer>> mappedVal = val.stream().map(Arrays::asList).collect(Collectors.toList());
            System.out.println("Room Number: " + key + " --- Courses: " + mappedVal);
        });

        System.out.println(
                map.entrySet().stream()
                        .max(Comparator.comparing(entry -> entry.getValue().size()))
                        .map(entry -> "Maximum courses taken in Room Number: " + entry.getKey())
                        .orElse(null)
        );
    }
}