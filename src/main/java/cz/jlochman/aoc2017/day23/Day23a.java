package cz.jlochman.aoc2017.day23;

public class Day23a {

   public static void main(String[] args) {
      int result = 0;
      long b = 0, c = 0, d = 0, e = 0, g = 0;
      b = 99;
      c = b;
      while (true) {
         d = 2;
         do {
            e = 2;
            do {
               g = d * e - b;
               result++;
               e++;
               g = e - b;
            } while (g != 0);
            d++;
            g = d - b;
         } while (g != 0);
         g = b - c;
         if (g == 0) {
            System.out.println("A: " + result);
            System.exit(0);
         }
         b += 17;
      }
   }

}
