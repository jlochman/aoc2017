package cz.jlochman.aoc2017.day09;

import java.util.Scanner;

import cz.jlochman.aoc2017.utils.FileUtils;

public class Day09 {

   public static void main(String[] args) {
      try (Scanner in = FileUtils.getScanner("Day09/input.txt")) {
         String line = in.next();

         int score = 0;
         int depth = 0;
         boolean garbageOpen = false;
         int gcCount = 0;

         for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            if (c == '!') {
               i++;
            } else if (garbageOpen) {
               if (c == '>') {
                  garbageOpen = false;
               } else {
                  gcCount++;
               }
            } else if (c == '<') {
               garbageOpen = true;
            } else if (c == '{') {
               depth++;
               score += depth;
            } else if (c == '}') {
               depth--;
            }
         }

         System.out.println("partA: " + score);
         System.out.println("partB: " + gcCount);
      }
   }

}
