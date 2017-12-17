package cz.jlochman.aoc2017.day17;

import java.util.ArrayList;
import java.util.List;

public class Day17 {

   private final static int INPUT = 328;

   public static void main(String[] args) {
      List<Integer> buffer = new ArrayList<>();
      buffer.add(0);
      int currentPosition = 0;
      int resultB = 0;
      for (int i = 1; i <= 50000000; i++) {
         currentPosition = ((currentPosition + INPUT) % i) + 1;
         if (currentPosition == 1) {
            resultB = i;
         }
         if (i <= 2017) {
            buffer.add(currentPosition, i);
            if (i == 2017) {
               System.out.println("A: " + buffer.get(currentPosition + 1));
            }
         }
      }
      System.out.println("B: " + resultB);
   }

}
