package cz.jlochman.aoc2017.day02;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cz.jlochman.aoc2017.utils.FileUtils;

public class Day02b {

   public static void main(String[] args) {
      try (Scanner in = FileUtils.getScanner("Day02/input.txt")) {
         int result = 0;
         while (in.hasNextLine()) {
            Scanner line = new Scanner(in.nextLine());
            List<Integer> ints = new ArrayList<>();
            while (line.hasNextInt()) {
               ints.add(line.nextInt());
            }
            line.close();
            result += getMarklar(ints);
         }
         System.out.println(result);
      }
   }

   private static int getMarklar(List<Integer> ints) {
      for (int i = 0; i < ints.size(); i++) {
         for (int k = i + 1; k < ints.size(); k++) {
            int bigger = Math.max(ints.get(i), ints.get(k));
            int smaller = Math.min(ints.get(i), ints.get(k));
            if (bigger % smaller == 0) {
               return bigger / smaller;
            }
         }
      }
      return 0;
   }

}
