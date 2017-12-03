package cz.jlochman.aoc2017.day02;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Scanner;

import cz.jlochman.aoc2017.utils.FileUtils;

public class Day02a {

   public static void main(String[] args) {
      try (Scanner in = FileUtils.getScanner("Day02/input.txt")) {
         List<IntSummaryStatistics> stats = new ArrayList<>();
         while (in.hasNextLine()) {
            Scanner line = new Scanner(in.nextLine());
            List<Integer> ints = new ArrayList<>();
            while (line.hasNextInt()) {
               ints.add(line.nextInt());
            }
            line.close();
            stats.add(ints.stream()
                          .mapToInt(Integer::intValue)
                          .summaryStatistics());
         }
         
         int result = 0;
         for (IntSummaryStatistics stat : stats) {
            result += stat.getMax() - stat.getMin();
         }
         System.out.println(result);
      }
   }

}
