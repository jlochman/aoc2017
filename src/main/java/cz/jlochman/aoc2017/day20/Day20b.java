package cz.jlochman.aoc2017.day20;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import cz.jlochman.aoc2017.utils.FileUtils;

public class Day20b {

   public static void main(String[] args) {

      try (Scanner in = FileUtils.getScanner("Day20/input.txt")) {
         List<Particle> particles = new ArrayList<>();
         while (in.hasNextLine()) {
            particles.add(new Particle(in.nextLine()
                                         .split(" "),
                                       particles.size()));
         }

         int prevParticleCount = particles.size();
         while (true) {
            for (int i = 0; i < 50; i++) {
               Set<Particle> particlesToRemove = new HashSet<>();
               for (int m = 0; m < particles.size(); m++) {
                  Particle p1 = particles.get(m);
                  for (int n = m + 1; n < particles.size(); n++) {
                     Particle p2 = particles.get(n);
                     if (p1.equals(p2)) {
                        particlesToRemove.add(p1);
                        particlesToRemove.add(p2);
                     }
                  }
               }
               particlesToRemove.stream()
                                .forEach(particles::remove);
               particles.parallelStream()
                        .forEach(p -> p.tick());
            }

            if (particles.size() == prevParticleCount) {
               break;
            } else {
               prevParticleCount = particles.size();
            }
         }
         
         System.out.println(prevParticleCount);
      }
   }
}
