package cz.jlochman.aoc2017.day04;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import cz.jlochman.aoc2017.utils.FileUtils;

public class Day04a {

   public static void main(String[] args) {
      try (Scanner in = FileUtils.getScanner("Day04/input.txt")) {
         int valid = 0;
         while (in.hasNextLine()) {
            String[] line = in.nextLine()
                              .split(" ");
            Set<String> strings = new HashSet<>(Arrays.asList(line));
            if (line.length == strings.size()) {
               valid++;
            }
         }
         System.out.println(valid);
      }
   }

}
