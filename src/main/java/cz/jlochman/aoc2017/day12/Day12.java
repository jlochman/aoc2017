package cz.jlochman.aoc2017.day12;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.IntStream;

import cz.jlochman.aoc2017.utils.FileUtils;

public class Day12 {

   private static Map<Integer, Integer> map = new HashMap<>();

   public static void main(String[] args) {
      IntStream.range(0, 2000)
               .forEach(i -> map.put(i, i));
      try (Scanner in = FileUtils.getScanner("Day12/input.txt")) {
         while (in.hasNextLine()) {
            Scanner line = new Scanner(in.nextLine());
            int p1 = line.nextInt();
            while (line.hasNextInt()) {
               int p2 = line.nextInt();
               int minGroup = Math.min(map.get(p1), map.get(p2));
               int maxGroup = Math.max(map.get(p1), map.get(p2));
               map.entrySet()
                  .stream()
                  .filter(entry -> entry.getValue() == maxGroup)
                  .forEach(entry -> entry.setValue(minGroup));
            }
            line.close();
         }
      }
      System.out.println("A: " + map.entrySet()
                                    .stream()
                                    .filter(entry -> entry.getValue() == 0)
                                    .count());
      System.out.println("B: " + new HashSet<>(map.values()).size());
   }

}
