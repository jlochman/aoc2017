package cz.jlochman.aoc2017.day05;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cz.jlochman.aoc2017.utils.FileUtils;

public class Day05b {

   public static void main(String[] args) {
      try (Scanner in = FileUtils.getScanner("Day05/input.txt")) {
         List<Integer> ints = new ArrayList<>();
         while (in.hasNextInt()) {
            ints.add(in.nextInt());
         }

         int prevIndex = 0;
         int index = 0;
         int steps = 0;
         while (index < ints.size()) {
            steps++;
            prevIndex = index;
            int jump = ints.get(prevIndex);
            if (jump >= 3) {
               jump = -1;
            } else {
               jump = 1;
            }
            index += ints.get(prevIndex);
            ints.set(prevIndex, ints.get(prevIndex) + jump);
         }
         System.out.println(steps);
      }
   }

}
