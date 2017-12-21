package cz.jlochman.aoc2017.day20;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import cz.jlochman.aoc2017.utils.FileUtils;

public class Day20a {

   public static void main(String[] args) {
      try (Scanner in = FileUtils.getScanner("Day20/input.txt")) {
         Set<Particle> particles = new HashSet<>();
         while (in.hasNextLine()) {
            particles.add(new Particle(in.nextLine()
                                         .split(" "),
                                       particles.size()));
         }

         int prevClosestParticle = 0;
         while (true) {
            for (int i = 0; i < 500; i++) {
               particles.parallelStream()
                        .forEach(p -> p.tick());
            }
            Particle particle = particles.stream()
                                         .sorted((p1,
                                                  p2) -> Long.compare(p1.getDist(),
                                                                      p2.getDist()))
                                         .findFirst()
                                         .get();

            if (particle.getOrder() == prevClosestParticle) {
               break;
            } else {
               prevClosestParticle = particle.getOrder();
            }
         }

         System.out.println("A: " + prevClosestParticle);
      }
   }

}
