package cz.jlochman.aoc2017.day08;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import cz.jlochman.aoc2017.utils.FileUtils;

public class Day08 {

   public static void main(String[] args) {
      try (Scanner in = FileUtils.getScanner("Day08/input.txt")) {
         int highestValue = 0;
         Map<String, Integer> regs = new HashMap<>();
         while (in.hasNextLine()) {
            Scanner line = new Scanner(in.nextLine());

            String targetReg = line.next();
            if (!regs.containsKey(targetReg)) {
               regs.put(targetReg, 0);
            }

            int multiplier = line.next()
                                 .equals("inc") ? 1 : -1;
            int change = line.nextInt() * multiplier;

            line.next();
            String condRegister = line.next();
            if (!regs.containsKey(condRegister)) {
               regs.put(condRegister, 0);
            }
            String condition = line.next();
            int condValue = line.nextInt();

            if (condition.equals(">") && regs.get(condRegister) > condValue) {
               regs.put(targetReg, regs.get(targetReg) + change);
            } else if (condition.equals("<")
                       && regs.get(condRegister) < condValue) {
               regs.put(targetReg, regs.get(targetReg) + change);
            } else if (condition.equals(">=")
                       && regs.get(condRegister) >= condValue) {
               regs.put(targetReg, regs.get(targetReg) + change);
            } else if (condition.equals("<=")
                       && regs.get(condRegister) <= condValue) {
               regs.put(targetReg, regs.get(targetReg) + change);
            } else if (condition.equals("==")
                       && regs.get(condRegister) == condValue) {
               regs.put(targetReg, regs.get(targetReg) + change);
            } else if (condition.equals("!=")
                       && regs.get(condRegister) != condValue) {
               regs.put(targetReg, regs.get(targetReg) + change);
            } else {
               System.err.println("Unhandled: " + condition);
            }
            line.close();

            if (regs.get(targetReg) > highestValue) {
               highestValue = regs.get(targetReg);
            }
         }

         System.out.println("A highest: " + regs.values()
                                                .stream()
                                                .mapToInt(Integer::valueOf)
                                                .max()
                                                .getAsInt());
         System.out.println("B overallHighest: " + highestValue);
      }
   }

}
