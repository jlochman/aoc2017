package cz.jlochman.aoc2017.day11;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

import cz.jlochman.aoc2017.utils.FileUtils;

public class Day11 {

   public static void main(String[] args) {
      try (Scanner in = FileUtils.getScanner("Day11/input.txt")) {
         String[] split = in.nextLine()
                            .split(",");
         int[] counts = new int[6];
         int maxDist = 0;
         for (String s : split) {
            if (s.equals("n")) {
               counts[0]++;
            } else if (s.equals("ne")) {
               counts[1]++;
            } else if (s.equals("se")) {
               counts[2]++;
            } else if (s.equals("s")) {
               counts[0]--;
            } else if (s.equals("sw")) {
               counts[1]--;
            } else if (s.equals("nw")) {
               counts[2]--;
            } else {
               System.err.println("unhandled input: " + s);
            }
            int actDist = getDist(counts);
            if (actDist > maxDist) {
               maxDist = actDist;
            }
         }
         System.out.println("finDist: " + getDist(counts));
         System.out.println("maxDist: " + maxDist);
      }
   }

   private static int getDist(int[] input) {
      int[] counts = Arrays.copyOf(input, input.length);
      for (int i = 0; i < 2; i++) {
         if (counts[i] < 0) {
            counts[i + 3] = -counts[i];
            counts[i] = 0;
         }
      }
      for (int i = 0; i < counts.length; i++) {
         int j = (i + 1) % counts.length;
         int k = (i + 2) % counts.length;
         int min = Math.min(counts[i], counts[k]);
         counts[i] -= min;
         counts[j] += min;
         counts[k] -= min;
      }
      return IntStream.of(counts)
                      .sum();
   }

}
