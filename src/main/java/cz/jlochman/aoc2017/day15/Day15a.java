package cz.jlochman.aoc2017.day15;

public class Day15a {

   private final static long A_START  = 722;
   private final static long A_FACTOR = 16807;

   private final static long B_START  = 354;
   private final static long B_FACTOR = 48271;

   private final static int COUNT = 40000000;

   public static void main(String[] args) {
      long aNext = A_START;
      long bNext = B_START;
      int result = 0;
      for (int i = 0; i < COUNT; i++) {
         aNext = (aNext * A_FACTOR) % 2147483647;
         bNext = (bNext * B_FACTOR) % 2147483647;
         if (aNext % 65536 == bNext % 65536) {
            result++;
         }
      }
      System.out.println(result);
   }

}
