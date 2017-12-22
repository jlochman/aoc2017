package cz.jlochman.aoc2017.day22;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import cz.jlochman.aoc2017.utils.FileUtils;

public class Day22b {

   public static void main(String[] args) {
      try (Scanner in = FileUtils.getScanner("Day22/input.txt")) {
         Set<Node> weakenedNodes = new HashSet<>();
         Set<Node> infectedNodes = new HashSet<>();
         Set<Node> flaggedNodes = new HashSet<>();
         int size = 25;
         int row = -size / 2;
         while (in.hasNextLine()) {
            String line = in.nextLine();
            for (int i = 0; i < size; i++) {
               if (line.charAt(i) == '#')
                  infectedNodes.add(new Node(row, i - size / 2));
            }
            row++;
         }

         Carrier carrier = new Carrier();
         int infections = 0;
         for (int i = 0; i < 10000000; i++) {
            if (weakenedNodes.contains(carrier.getNode())) { // on weakened
               weakenedNodes.remove(carrier.getNode());
               infectedNodes.add(carrier.getNode());
               infections++;
            } else if (infectedNodes.contains(carrier.getNode())) { // on infected
               carrier.turnRight();
               infectedNodes.remove(carrier.getNode());
               flaggedNodes.add(carrier.getNode());
            } else if (flaggedNodes.contains(carrier.getNode())) { // on flagged
               carrier.reverseDirection();
               flaggedNodes.remove(carrier.getNode());
            } else { // on clean
               carrier.turnLeft();
               weakenedNodes.add(carrier.getNode());
            }
            carrier.move();
         }
         System.out.println(infections);
      }
   }

}
