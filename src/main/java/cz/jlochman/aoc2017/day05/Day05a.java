package cz.jlochman.aoc2017.day05;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cz.jlochman.aoc2017.utils.FileUtils;

public class Day05a {

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
            index += ints.get(index);
            ints.set(prevIndex, ints.get(prevIndex) + 1);
         }
         System.out.println(steps);
      }
   }

}
