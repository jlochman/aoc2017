package cz.jlochman.aoc2017.utils;

import java.util.Scanner;

public class FileUtils {

   public static Scanner getScanner(String fileName) {
      return new Scanner(FileUtils.class.getClassLoader()
                                        .getResourceAsStream(fileName));
   }

}
