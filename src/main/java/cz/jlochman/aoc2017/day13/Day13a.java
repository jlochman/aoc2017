package cz.jlochman.aoc2017.day13;

import java.util.HashSet;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

import cz.jlochman.aoc2017.utils.FileUtils;

public class Day13a {

   public static void main(String[] args) {
      try (Scanner in = FileUtils.getScanner("Day13/input.txt")) {
         Set<Layer> layers = new HashSet<>();
         while (in.hasNext()) {
            Scanner line = new Scanner(in.nextLine());
            layers.add(new Layer(line.nextInt(), line.nextInt()));
            line.close();
         }
         int result = 0;
         for (int depth = 0; depth <= layers.stream()
                                            .mapToInt(layer -> layer.getDepth())
                                            .max()
                                            .getAsInt();
              depth++) {
            final int finalDepth = depth;
            Optional<Layer> optLayer = layers.stream()
                                             .filter(layer -> layer.getDepth() == finalDepth)
                                             .findAny();
            if (optLayer.isPresent()) {
               Layer layer = optLayer.get();
               if (layer.getIndex() == 0) {
                  result += layer.getDepth() * layer.getRange();
               }
            }
            layers.stream()
                  .forEach(layer -> layer.tick());
         }
         System.out.println(result);
      }
   }

}
