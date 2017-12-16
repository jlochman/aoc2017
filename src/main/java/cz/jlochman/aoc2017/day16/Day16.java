package cz.jlochman.aoc2017.day16;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cz.jlochman.aoc2017.utils.FileUtils;

public class Day16 {

   public static char[] programs =
         new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
                      'l', 'm', 'n', 'o', 'p' };

   public static void main(String[] args) {
      List<Worker> workers = new ArrayList<>();
      try (Scanner in = FileUtils.getScanner("Day16/input.txt")) {
         while (in.hasNextLine()) {
            Scanner line = new Scanner(in.nextLine());
            switch (line.next()) {
            case "s":
               workers.add(new Worker.Spinner(line.nextInt()));
               break;
            case "x":
               workers.add(new Worker.Exchanger(line.nextInt(),
                                                line.nextInt()));
               break;
            case "p":
               workers.add(new Worker.Partner(line.next()
                                                  .charAt(0),
                                              line.next()
                                                  .charAt(0)));
               break;
            }
            line.close();
         }
      }
      workers.stream()
             .forEach(w -> w.work());
      System.out.println("A: " + getResult());

      // po 48 cyklech dostavam poradi, ktere uz bylo
      for (int i = 1; i < 1e9 % 48; i++) {
         workers.stream()
                .forEach(w -> w.work());
      }
      System.out.println("B: " + getResult());
   }

   private static String getResult() {
      String result = "";
      for (int i = 0; i < programs.length; i++) {
         result += programs[i];
      }
      return result;
   }

}
