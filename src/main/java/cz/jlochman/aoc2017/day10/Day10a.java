package cz.jlochman.aoc2017.day10;

import java.util.stream.IntStream;

public class Day10a {

   private final static int[] input =
         new int[] { 230, 1, 2, 221, 97, 252, 168, 169, 57, 99, 0, 254, 181,
                     255, 235, 167 };

   public static void main(String[] args) {
      int[] list = IntStream.rangeClosed(0, 255)
                            .toArray();
      int currentPosition = 0;
      int skipSize = 0;

      for (int i = 0; i < input.length; i++) {
         int in = input[i];
         for (int k = currentPosition; k < currentPosition + in / 2; k++) {
            int firstPos = k % list.length;
            int mirrorPos = (2 * currentPosition + in - k - 1) % list.length;
            int helper = list[firstPos];
            list[firstPos] = list[mirrorPos];
            list[mirrorPos] = helper;
         }
         currentPosition += in + skipSize;
         skipSize++;
      }

      System.out.println(list[0] * list[1]);

   }

}
