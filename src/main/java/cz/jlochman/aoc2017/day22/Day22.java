package cz.jlochman.aoc2017.day22;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import cz.jlochman.aoc2017.utils.FileUtils;

public class Day22 {

   public static void main(String[] args) {
      try (Scanner in = FileUtils.getScanner("Day22/input.txt")) {
         Set<Node> nodes = new HashSet<>();
         int size = 25;
         int row = -size / 2;
         while (in.hasNextLine()) {
            String line = in.nextLine();
            for (int i = 0; i < size; i++) {
               if (line.charAt(i) == '#')
                  nodes.add(new Node(row, i - size / 2));
            }
            row++;
         }

         Carrier carrier = new Carrier();
         int infections = 0;
         for (int i = 0; i < 10000; i++) {
            if (nodes.contains(carrier.getNode())) { // on infected
               carrier.turnRight();
               nodes.remove(carrier.getNode());
            } else { // on clean
               carrier.turnLeft();
               nodes.add(carrier.getNode());
               infections++;
            }
            carrier.move();
         }
         System.out.println(infections);
      }
   }

}
