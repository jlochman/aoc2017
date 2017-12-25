package cz.jlochman.aoc2017.day23;

public class Day23b {

   public static void main(String[] args) {
      long b = 0, c = 0, d = 0, h = 0;
      b = 99 * 100 + 100000;
      c = b + 17000;
      while (true) {
         d = 2;
         do {
            if (b % d == 0) {
               h++;
               break;
            }
            d++;
         } while (d != b);
         if (b == c) {
            System.out.println("B: " + h);
            System.exit(0);
         }
         b += 17;
      }
   }

}
