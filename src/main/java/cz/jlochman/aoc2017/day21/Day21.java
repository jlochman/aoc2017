package cz.jlochman.aoc2017.day21;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import cz.jlochman.aoc2017.utils.FileUtils;

public class Day21 {

   private static boolean[][] image =
         new boolean[][] { { false, true, false }, { false, false, true },
                           { true, true, true } };

   public static void main(String[] args) {
      try (Scanner in = FileUtils.getScanner("Day21/input.txt")) {
         Map<String, boolean[][]> map = new HashMap<>();
         while (in.hasNextLine()) {
            Scanner line = new Scanner(in.nextLine());
            Matrix mIn = new Matrix(line.next());
            line.next();
            Matrix mOut = new Matrix(line.next());
            for (int i = 0; i < 4; i++) {
               map.put(mIn.getMatrixString(), mOut.getMatrix());
               mIn.flip();
               map.put(mIn.getMatrixString(), mOut.getMatrix());
               mIn.flip();
               mIn.rotate90();
            }
            line.close();
         }

         for (int iteration = 0; iteration < 18; iteration++) {
            boolean[][] newImage;
            if (image.length % 2 == 0) {
               newImage =
                     new boolean[image.length / 2 * 3][image.length / 2 * 3];
               for (int row = 0; row < image.length; row += 2) {
                  for (int col = 0; col < image.length; col += 2) {
                     Matrix m = new Matrix(image, row, col, 2);
                     putIntoImage(newImage, map.get(m.getMatrixString()),
                                  row / 2 * 3, col / 2 * 3);
                  }
               }
            } else {
               newImage =
                     new boolean[image.length / 3 * 4][image.length / 3 * 4];
               for (int row = 0; row < image.length; row += 3) {
                  for (int col = 0; col < image.length; col += 3) {
                     Matrix m = new Matrix(image, row, col, 3);
                     putIntoImage(newImage, map.get(m.getMatrixString()),
                                  row / 3 * 4, col / 3 * 4);
                  }
               }
            }
            image = newImage;

            if (iteration == 4) {
               System.out.println("A: " + getResult());
            }
         }
         System.out.println("B: " + getResult());
      }
   }

   private static int getResult() {
      int result = 0;
      for (int row = 0; row < image.length; row++) {
         for (int col = 0; col < image.length; col++) {
            if (image[row][col])
               result++;
         }
      }
      return result;
   }

   private static void putIntoImage(boolean[][] img, boolean[][] subImg,
                                    int fromRow, int fromCol) {
      for (int row = fromRow; row < fromRow + subImg.length; row++) {
         for (int col = fromCol; col < fromCol + subImg.length; col++) {
            img[row][col] = subImg[row - fromRow][col - fromCol];
         }
      }
   }

}
