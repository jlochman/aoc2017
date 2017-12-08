package cz.jlochman.aoc2017.day07;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import cz.jlochman.aoc2017.utils.FileUtils;

public class Day07a {

   public static void main(String[] args) {
      try (Scanner in = FileUtils.getScanner("Day07/input.txt")) {
         Map<String, Node> nodeMap = new HashMap<>();
         while (in.hasNextLine()) {
            Scanner line = new Scanner(in.nextLine());

            Node parentNode = getNodeByName(nodeMap, line.next());

            line.next();
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

         nodeMap.values()
                .stream()
                .filter(n -> n.getParent() == null)
                .forEach(n -> System.out.println(n.getName()));

      }
   }

   private static Node getNodeByName(Map<String, Node> nodeMap, String name) {
      if (!nodeMap.containsKey(name)) {
         nodeMap.put(name, new Node(name));
      }
      return nodeMap.get(name);
   }

}
