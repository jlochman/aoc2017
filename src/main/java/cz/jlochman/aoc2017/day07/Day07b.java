package cz.jlochman.aoc2017.day07;

import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.Map;
import java.util.Scanner;

import cz.jlochman.aoc2017.utils.FileUtils;

public class Day07b {

   public static void main(String[] args) {
      try (Scanner in = FileUtils.getScanner("Day07/input.txt")) {
         Map<String, Node> nodeMap = new HashMap<>();
         while (in.hasNextLine()) {
            Scanner line = new Scanner(in.nextLine());

            Node parentNode = getNodeByName(nodeMap, line.next());
            parentNode.setSelfWeight(Integer.parseInt(line.next()
                                                          .replace("(", "")
                                                          .replace(")", "")));

            if (line.hasNext()) {
               line.next();
            }
            while (line.hasNext()) {
               Node childNode = getNodeByName(nodeMap, line.next()
                                                           .replace(",", ""));
               parentNode.addChild(childNode);
            }
            line.close();
         }

         Node masterNode = null;
         for (Node node : nodeMap.values()) {
            node.setTotalWeight(getWeight(node));
            if (node.getParent() == null) {
               masterNode = node;
            }
         }

         do {
            masterNode = getUniqueNode(masterNode);
         } while (masterNode != null);

      }

   }

   private static Node getNodeByName(Map<String, Node> nodeMap, String name) {
      if (!nodeMap.containsKey(name)) {
         nodeMap.put(name, new Node(name));
      }
      return nodeMap.get(name);
   }

   private static int getWeight(Node node) {
      int weight = node.getSelfWeight();
      for (Node child : node.getChildren()) {
         weight += getWeight(child);
      }
      return weight;
   }

   private static Node getUniqueNode(Node master) {
      IntSummaryStatistics wStats = master.getChildren()
                                          .stream()
                                          .mapToInt(n -> n.getTotalWeight())
                                          .summaryStatistics();
      int min = wStats.getMin();
      int max = wStats.getMax();
      double avg = wStats.getAverage();

      if (min == max) {
         return null;
      }

      Node uniqueNode = null;
      Node someOtherNode = null;
      if (avg - min < max - avg) {
         uniqueNode = getChildNodeByTotalWeight(master, max);
         someOtherNode = getChildNodeByTotalWeight(master, min);
      } else {
         uniqueNode = getChildNodeByTotalWeight(master, min);
         someOtherNode = getChildNodeByTotalWeight(master, max);
      }

      System.out.println("Answer on this level: " + uniqueNode.getSelfWeight()
                         + (someOtherNode.getTotalWeight()
                            - uniqueNode.getTotalWeight()));

      return uniqueNode;
   }

   private static Node getChildNodeByTotalWeight(Node master, int totalWeight) {
      return master.getChildren()
                   .stream()
                   .filter(n -> n.getTotalWeight() == totalWeight)
                   .findAny()
                   .get();
   }

}
