package cz.jlochman.aoc2017.day25;

import java.util.HashSet;
import java.util.Set;

public class Day25 {

   public static void main(String[] args) {
      char state = 'A';
      int position = 0;
      Set<Integer> ones = new HashSet<>();
      for (int i = 0; i < 12208951; i++) {
         switch (state) {
         case 'A':
            if (!ones.contains(position)) {
               ones.add(position++);
               state = 'B';
            } else {
               ones.remove(position--);
               state = 'E';
            }
            break;
         case 'B':
            if (!ones.contains(position)) {
               ones.add(position--);
               state = 'C';
            } else {
               ones.remove(position++);
               state = 'A';
            }
            break;
         case 'C':
            if (!ones.contains(position)) {
               ones.add(position--);
               state = 'D';
            } else {
               ones.remove(position++);
            }
            break;
         case 'D':
            if (!ones.contains(position)) {
               ones.add(position--);
               state = 'E';
            } else {
               ones.remove(position--);
               state = 'F';
            }
            break;
         case 'E':
            if (!ones.contains(position)) {
               ones.add(position--);
               state = 'A';
            } else {
               ones.add(position--);
               state = 'C';
            }
            break;
         case 'F':
            if (!ones.contains(position)) {
               ones.add(position--);
               state = 'E';
            } else {
               ones.add(position++);
               state = 'A';
            }
            break;
         }
      }
      System.out.println(ones.size());
   }

}
