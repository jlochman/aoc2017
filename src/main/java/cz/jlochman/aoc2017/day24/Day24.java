package cz.jlochman.aoc2017.day24;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import cz.jlochman.aoc2017.utils.FileUtils;

public class Day24 {

   private static Set<Component>  components = new HashSet<>();
   private static List<Component> leafs      = new ArrayList<>();

   public static void main(String[] args) {
      try (Scanner in = FileUtils.getScanner("Day24/input.txt")) {
         while (in.hasNextLine()) {
            Scanner line = new Scanner(in.nextLine());
            components.add(new Component(line.nextInt(), line.nextInt()));
            line.close();
         }

         Set<Component> rootComponents = new HashSet<>();
         for (Component firstComponent : components.stream()
                                                   .filter(c -> c.getPort1() == 0
                                                                || c.getPort2() == 0)
                                                   .collect(Collectors.toSet())) {
            rootComponents.add(new Component(0,
                                             firstComponent.getOtherPort(0)));
         }

         for (Component rootComponent : rootComponents) {
            setChildren(rootComponent);
         }

         int strongest = 0;
         int longest = 0;
         for (Component leaf : leafs) {
            if (getStrength(0, leaf) > strongest) {
               strongest = getStrength(0, leaf);
            }
            if (getLength(0, leaf) > longest) {
               longest = getLength(0, leaf);
            }
         }
         System.out.println("A: " + strongest);

         strongest = 0;
         for (Component leaf : leafs) {
            if (getLength(0, leaf) == longest
                && getStrength(0, leaf) > strongest) {
               strongest = getStrength(0, leaf);
            }
         }
         System.out.println("B: " + strongest);
      }
   }

   private static int getLength(int length, Component component) {
      length++;
      if (component.getParent() != null) {
         return getLength(length, component.getParent());
      } else {
         return length;
      }
   }

   private static int getStrength(int strength, Component component) {
      strength += component.getPort1() + component.getPort2();
      if (component.getParent() != null) {
         return getStrength(strength, component.getParent());
      } else {
         return strength;
      }
   }

   private static void setChildren(Component component) {
      Set<Component> children = getAttachableComponents(component);
      if (children != null && !children.isEmpty()) {
         component.setChildren(children);
         children.stream()
                 .forEach(ch -> setChildren(ch));
      } else {
         leafs.add(component);
      }
   }

   private static Set<Component> getAttachableComponents(Component component) {
      Set<Component> usedComponents =
            getUsedComponents(new HashSet<>(), component);
      return components.stream()
                       .filter(c -> !usedComponents.contains(c)
                                    && (c.getPort1() == component.getPort2()
                                        || c.getPort2() == component.getPort2()))
                       .map(c -> new Component(component.getPort2(),
                                               c.getOtherPort(component.getPort2())))
                       .collect(Collectors.toSet());
   }

   private static Set<Component>
         getUsedComponents(Set<Component> usedComponents, Component component) {
      if (component != null) {
         usedComponents.add(component);
      }
      if (component.getParent() != null) {
         return getUsedComponents(usedComponents, component.getParent());
      } else {
         return usedComponents;
      }
   }

}
