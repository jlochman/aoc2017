package cz.jlochman.aoc2017.day21;

class Matrix {

   private boolean[][] matrix;

   public Matrix(String content) {
      String[] split = content.split("/");
      matrix = new boolean[split.length][split.length];
      for (int row = 0; row < split.length; row++) {
         for (int col = 0; col < split[row].length(); col++) {
            matrix[row][col] = split[row].charAt(col) == '#';
         }
      }
   }

   public Matrix(boolean[][] image, int fromRow, int fromCol, int size) {
      matrix = new boolean[size][size];
      for (int row = fromRow; row < fromRow + size; row++) {
         for (int col = fromCol; col < fromCol + size; col++) {
            matrix[row - fromRow][col - fromCol] = image[row][col];
         }
      }
   }

   public boolean[][] getMatrix() {
      return matrix;
   }

   public String getMatrixString() {
      StringBuilder builder = new StringBuilder();
      for (int row = 0; row < matrix.length; row++) {
         for (int col = 0; col < matrix.length; col++) {
            builder.append(matrix[row][col] ? '1' : '0');
         }
      }
      return builder.toString();
   }

   public void rotate90() {
      boolean[][] rotated = new boolean[matrix.length][matrix.length];
      for (int row = 0; row < matrix.length; row++) {
         for (int col = 0; col < matrix.length; col++) {
            rotated[row][col] = matrix[matrix.length - col - 1][row];
         }
      }
      matrix = rotated;
   }

   public void flip() {
      boolean[][] flipped = new boolean[matrix.length][matrix.length];
      for (int row = 0; row < matrix.length; row++) {
         for (int col = 0; col < matrix.length; col++) {
            flipped[row][matrix.length - col - 1] = matrix[row][col];
         }
      }
      matrix = flipped;
   }

}
