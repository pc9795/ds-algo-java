package gfg.ds.matrix;

import gfg.ds.array.ArrayApplications;
import utils.Pair;
import utils.Utils;

import java.util.*;

/** @noinspection WeakerAccess */
public class MatrixApplications {

  /** t=O(n) */
  public static Optional<Pair<Integer, Integer>> searchInColumnAndRowWiseSortedMatrix(
      int mat[][], int key) {
    assert Utils.isMatrixNonEmpty(mat);

    int rows = mat.length;
    int columns = mat[0].length;
    int i = 0;
    int j = columns - 1;

    for (; i < rows && j >= 0; ) {
      if (mat[i][j] == key) {
        return Optional.of(new Pair<>(i, j));
      }
      if (mat[i][j] > key) {
        j--;
      } else {
        i++;
      }
    }

    return Optional.empty();
  }

  /** t=O(n) */
  public static List<Integer> getSpiralList(int mat[][]) {
    assert Utils.isMatrixNonEmpty(mat);

    int lastRow = mat.length - 1;
    int lastColumn = mat[0].length - 1;
    List<Integer> traversal = new ArrayList<>();

    for (int startRow = 0, startColumn = 0; startRow <= lastRow && startColumn <= lastColumn; ) {
      for (int i = startColumn; i <= lastColumn; i++) {
        traversal.add(mat[startRow][i]);
      }
      startRow++;

      for (int i = startRow; i <= lastRow; i++) {
        traversal.add(mat[i][lastColumn]);
      }
      lastColumn--;

      if (startRow <= lastRow) {
        for (int i = lastColumn; i >= startColumn; i--) {
          traversal.add(mat[lastRow][i]);
        }
        lastRow--;
      }

      if (startColumn <= lastColumn) {
        for (int i = lastRow; i >= startRow; i--) {
          traversal.add(mat[i][startColumn]);
        }
        startColumn++;
      }
    }

    return traversal;
  }

  public static int[][] makeColumnAndRowOneHavingAtLeastOneOne(int mat[][]) {
    assert Utils.isMatrixNonEmpty(mat);

    boolean firstRowHasOne = false;
    boolean firstColumnHasOne = false;

    for (int i = 0; i < mat[0].length; i++) {
      if (mat[0][i] == 1) {
        firstRowHasOne = true;
        break;
      }
    }

    for (int[] row : mat) {
      if (row[0] == 1) {
        firstColumnHasOne = true;
        break;
      }
    }

    // First row and column are used for storage instead of auxiliary arrays
    for (int i = 1; i < mat.length; i++) {
      for (int j = 1; j < mat[0].length; j++) {
        if (mat[i][j] == 1) {
          mat[0][j] = 1;
          mat[i][0] = 1;
        }
      }
    }

    for (int i = 1; i < mat.length; i++) {
      for (int j = 1; j < mat[0].length; j++) {
        if (mat[0][j] == 1 || mat[i][0] == 1) {
          mat[i][j] = 1;
        }
      }
    }

    if (firstColumnHasOne) {
      for (int i = 0; i < mat.length; i++) {
        mat[i][0] = 1;
      }
    }

    if (firstRowHasOne) {
      for (int i = 0; i < mat[0].length; i++) {
        mat[0][i] = 1;
      }
    }

    return mat;
  }

  public static List<Integer> getUniqueRowIndices(boolean mat[][]) {
    HashMap<Integer, Integer> decimalValueToRowIndexMap = new LinkedHashMap<>();

    for (int i = 0; i < mat.length; i++) {
      int decimal = Utils.getDecimalFromBooleanArray(mat[i]);
      if (!decimalValueToRowIndexMap.containsKey(decimal)) {
        decimalValueToRowIndexMap.put(decimal, i);
      }
    }

    return new ArrayList<>(decimalValueToRowIndexMap.values());
  }

  public static Pair<Integer, Pair<Integer, Integer>> getMaxSizeSquareSubMatrixPositionWitAllOnes(
      int mat[][]) {
    int aux[][] = new int[mat.length][mat[0].length];
    aux[0][0] = mat[0][0];
    // First column
    for (int i = 1; i < mat.length; i++) {
      if (mat[i][0] == 1) {
        aux[i][0] = aux[i - 1][0] + 1;
      }
    }

    // First row
    for (int i = 1; i < mat[0].length; i++) {
      if (mat[0][i] == 1) {
        aux[0][i] = aux[0][i - 1] + 1;
      }
    }

    for (int i = 1; i < mat.length; i++) {
      for (int j = 1; j < mat[0].length; j++) {
        if (mat[i][j] == 1) {
          aux[i][j] = Math.min(Math.min(aux[i - 1][j - 1], aux[i - 1][j]), aux[i][j - 1]) + 1;
        }
      }
    }

    Pair<Integer, Integer> maxPos = new Pair<>(0, 0);
    for (int i = 0; i < mat.length; i++) {
      for (int j = 0; j < mat[0].length; j++) {
        if (aux[i][j] > mat[maxPos.key][maxPos.value]) {
          maxPos = new Pair<>(i, j);
        }
      }
    }

    return new Pair<>(aux[maxPos.key][maxPos.value], maxPos);
  }

  /** t = O(rows*cols) s = O(rows*cols) */
  public static void transposeInplace(int[] mat, int rows, int cols) {
    assert mat.length == rows * cols;
    boolean transposed[] = new boolean[mat.length];
    transposed[0] = transposed[mat.length - 1] = true; // first and last values are self-cycles

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (transposed[i * cols + j]) {
          continue;
        }

        int currPos = i * cols + j;
        int currValue = mat[currPos];
        int nextPos;
        do {
          nextPos = (currPos * rows) % (rows * cols - 1);
          int temp = mat[nextPos];
          mat[nextPos] = currValue;
          currValue = temp;
          transposed[currPos] = true;
          currPos = nextPos;
        } while (!transposed[nextPos]);
      }
    }
  }

  /** t=O(m*n) s=O(n) */
  public static Optional<Integer> commonElementInRowWiseSortedMatrix(int[][] mat) {
    assert Utils.isMatrixNonEmpty(mat);

    int minRow = 0;
    int rows = mat.length;
    int cols = mat[0].length;

    int[] colPointers = new int[rows];
    Arrays.fill(colPointers, cols - 1); // Initially pointing to the last column of a row

    boolean pointersChanged;
    do {
      pointersChanged = false;
      for (int i = 0; i < rows; i++) {
        if (colPointers[i] < 0) {
          break;
        }
        if (mat[i][colPointers[i]] > mat[minRow][colPointers[minRow]]) {
          pointersChanged = true;
          colPointers[i]--;
        } else if (mat[i][colPointers[i]] < mat[minRow][colPointers[minRow]]) {
          pointersChanged = true;
          minRow = i;
        }
      }
    } while (pointersChanged);

    if (colPointers[0] < 0) {
      return Optional.empty();
    }
    int firstRowValue = mat[0][colPointers[0]];

    for (int i = 1; i < rows; i++) {
      if (colPointers[i] < 0 || firstRowValue != mat[i][colPointers[i]]) {
        return Optional.empty();
      }
    }

    return Optional.of(firstRowValue);
  }

  /** t=O(m*n) s=O(n) */
  public static Optional<Integer> commonElementInRowWiseSortedMatrixWithHashing(int[][] mat) {
    assert Utils.isMatrixNonEmpty(mat);

    Map<Integer, Integer> firstRowValuesCountInOtherRows = new HashMap<>();
    for (int i = 0; i < mat[0].length; i++) {
      firstRowValuesCountInOtherRows.put(mat[0][i], 1);
    }

    for (int i = 1; i < mat.length; i++) {
      for (int j = 0; j < mat[0].length; j++) {
        int value = mat[i][j];
        if (firstRowValuesCountInOtherRows.containsKey(value)) {
          firstRowValuesCountInOtherRows.put(value, firstRowValuesCountInOtherRows.get(value) + 1);
        }
      }
    }

    for (Integer key : firstRowValuesCountInOtherRows.keySet()) {
      if (firstRowValuesCountInOtherRows.get(key) == mat.length) {
        return Optional.of(key);
      }
    }

    return Optional.empty();
  }

  /** t=O(m*n) */
  public static int getIslandsCount(char[][] mat) {
    if (mat.length == 0 || mat[0].length == 0) {
      return 0;
    }

    int count = 0;
    for (int i = 0; i < mat.length; i++) {
      for (int j = 0; j < mat[0].length; j++) {
        if (mat[i][j] == 'X') {
          // Find top most corners of islands
          if ((i == 0 || mat[i - 1][j] == 'O') && (j == 0 || mat[i][j - 1] == 'O')) {
            count++;
          }
        } else if (mat[i][j] != 'O') {
          throw new RuntimeException(
              String.format("Invalid matrix found [%s] at (%s, %s)", mat[i][j], i, j));
        }
      }
    }

    return count;
  }

  public static int[][] getSumOfAllSubSquares(int[][] mat, int subSqSize) {
    assert subSqSize > 0;
    assert mat.length != 0 && mat.length >= subSqSize;
    assert mat[0].length > subSqSize;

    int rows = mat.length;
    int cols = mat[0].length;
    int[][] temp = new int[rows][];
    for (int i = 0; i < rows; i++) {
      temp[i] = Arrays.copyOf(mat[i], cols);
    }

    // Compressing by rows
    for (int j = 0; j < cols; j++) {
      for (int i = 0; i < subSqSize - 1; i++) {
        temp[subSqSize - 1][j] += temp[i][j];
      }
      for (int i = subSqSize; i < rows; i++) {
        temp[i][j] += temp[i - 1][j] - temp[i - subSqSize][j];
      }
    }

    // Compressing by cols
    for (int i = subSqSize - 1; i < rows; i++) {
      for (int j = 0; j < subSqSize - 1; j++) {
        temp[i][subSqSize - 1] += temp[i][j];
      }
      for (int j = subSqSize; j < cols; j++) {
        temp[i][j] += temp[i][j - 1] - temp[i][j - subSqSize];
      }
    }

    int[][] subSqSumMat = new int[rows - subSqSize + 1][cols - subSqSize + 1];
    for (int i = subSqSize - 1; i < rows; i++) {
      System.arraycopy(
          temp[i], subSqSize - 1, subSqSumMat[i - subSqSize + 1], 0, cols - subSqSize + 1);
    }

    return subSqSumMat;
  }

  public static int[][] matrixMultiplication(int mat1[][], int mat2[][]) {
    assert Utils.isMatrixNonEmpty(mat1) && Utils.isMatrixNonEmpty(mat2);
    assert mat1.length == mat1[0].length;
    assert mat2.length == mat2[0].length;
    assert mat1.length == mat2.length;

    return matrixMultiplicationUtil(mat1, mat2);
  }

  private static int[][] matrixMultiplicationUtil(int[][] mat1, int[][] mat2) {
    int n = mat1.length;
    if (n == 1) {
      return new int[][] {{mat1[0][0] * mat2[0][0]}};
    }

    boolean zeroPadded = false;
    if (n % 2 != 0) {
      zeroPadded = true;
      mat1 = addZeroPaddedRowAndColumn(mat1);
      mat2 = addZeroPaddedRowAndColumn(mat2);
    }

    MatrixQuadrants matrixQuadrants1 = new MatrixQuadrants(mat1);
    MatrixQuadrants matrixQuadrants2 = new MatrixQuadrants(mat2);

    int[][] a = matrixQuadrants1.q1;
    int[][] b = matrixQuadrants1.q2;
    int[][] c = matrixQuadrants1.q3;
    int[][] d = matrixQuadrants1.q4;
    int[][] e = matrixQuadrants2.q1;
    int[][] f = matrixQuadrants2.q2;
    int[][] g = matrixQuadrants2.q3;
    int[][] h = matrixQuadrants2.q4;

    int[][] p1 = matrixMultiplicationUtil(a, matrixSubtraction(f, h));
    int[][] p2 = matrixMultiplicationUtil(matrixAddition(a, b), h);
    int[][] p3 = matrixMultiplicationUtil(matrixAddition(c, d), e);
    int[][] p4 = matrixMultiplicationUtil(d, matrixSubtraction(g, e));
    int[][] p5 = matrixMultiplicationUtil(matrixAddition(a, d), matrixAddition(e, h));
    int[][] p6 = matrixMultiplicationUtil(matrixSubtraction(b, d), matrixAddition(g, h));
    int[][] p7 = matrixMultiplicationUtil(matrixSubtraction(a, c), matrixAddition(e, f));

    int q1[][] = matrixAddition(p5, matrixAddition(matrixSubtraction(p4, p2), p6));
    int q2[][] = matrixAddition(p1, p2);
    int q3[][] = matrixAddition(p3, p4);
    int q4[][] = matrixAddition(p1, matrixSubtraction(matrixSubtraction(p5, p3), p7));

    int[][] result = MatrixQuadrants.fromQuadrants(q1, q2, q3, q4);
    if (zeroPadded) {
      result = removeZeroPaddedRowAndColumn(result);
    }
    return result;
  }

  private static int[][] addZeroPaddedRowAndColumn(int mat[][]) {
    int n = mat.length;
    int temp[][] = new int[n + 1][n + 1];
    for (int i = 0; i < n; i++) {
      System.arraycopy(mat[i], 0, temp[i], 0, n);
    }

    return temp;
  }

  private static int[][] removeZeroPaddedRowAndColumn(int mat[][]) {
    int n = mat.length;
    int temp[][] = new int[n - 1][n - 1];
    for (int i = 0; i < n - 1; i++) {
      System.arraycopy(mat[i], 0, temp[i], 0, n - 1);
    }

    return temp;
  }

  private static class MatrixQuadrants {
    int[][] mat;
    int[][] q1;
    int[][] q2;
    int[][] q3;
    int[][] q4;

    MatrixQuadrants(int[][] mat) {
      this.mat = mat;
      this.createQuadrants();
    }

    private void createQuadrants() {
      int n = this.mat.length;
      this.q1 = new int[n / 2][n / 2];
      this.q2 = new int[n / 2][n / 2];
      this.q3 = new int[n / 2][n / 2];
      this.q4 = new int[n / 2][n / 2];

      for (int i = 0; i < n / 2; i++) {
        System.arraycopy(mat[i], 0, q1[i], 0, n / 2);
        System.arraycopy(mat[i], n / 2, q2[i], 0, n / 2);
        System.arraycopy(mat[i + n / 2], 0, q3[i], 0, n / 2);
        System.arraycopy(mat[i + n / 2], n / 2, q4[i], 0, n / 2);
      }
    }

    public static int[][] fromQuadrants(int[][] q1, int[][] q2, int[][] q3, int[][] q4) {
      int n = q1.length * 2;
      int[][] mat = new int[n][n];

      for (int i = 0; i < n / 2; i++) {
        System.arraycopy(q1[i], 0, mat[i], 0, n / 2);
        System.arraycopy(q2[i], 0, mat[i], n / 2, n / 2);
        System.arraycopy(q3[i], 0, mat[i + n / 2], 0, n / 2);
        System.arraycopy(q4[i], 0, mat[i + n / 2], n / 2, n / 2);
      }

      return mat;
    }
  }

  private static int[][] matrixAddition(int mat1[][], int mat2[][]) {
    int[][] result = new int[mat1.length][mat1[0].length];
    for (int i = 0; i < mat1.length; i++) {
      for (int j = 0; j < mat1[0].length; j++) {
        result[i][j] = mat1[i][j] + mat2[i][j];
      }
    }
    return result;
  }

  private static int[][] matrixSubtraction(int[][] mat1, int[][] mat2) {
    int[][] result = new int[mat1.length][mat1[0].length];
    for (int i = 0; i < mat1.length; i++) {
      for (int j = 0; j < mat1[0].length; j++) {
        result[i][j] = mat1[i][j] - mat2[i][j];
      }
    }
    return result;
  }

  /** t=O(n^3) */
  public static MaxSumRectangle getMaxSumRectangle(int[][] mat) {
    assert Utils.isMatrixNonEmpty(mat);

    int rows = mat.length;
    int cols = mat[0].length;
    int sumBetweenCols[];
    int max = Integer.MIN_VALUE;
    Pair<Integer, Integer> topLeftCorner = Pair.of(-1, -1);
    Pair<Integer, Integer> bottonRightCorner = Pair.of(-1, -1);

    for (int col1 = 0; col1 < cols; col1++) {
      sumBetweenCols = new int[rows];
      for (int col2 = col1; col2 < cols; col2++) {
        for (int row = 0; row < rows; row++) {
          sumBetweenCols[row] += mat[row][col2];
        }
        ArrayApplications.MaxSumSubarray result =
            ArrayApplications.getMaxSumSubarray(sumBetweenCols);
        if (result.getSum() > max) {
          topLeftCorner = Pair.of(result.getStartIndex(), col1);
          bottonRightCorner = Pair.of(result.getEndIndex(), col2);
          max = result.getSum();
        }
      }
    }

    return new MaxSumRectangle(topLeftCorner, bottonRightCorner, max);
  }

  public static class MaxSumRectangle {
    private Pair<Integer, Integer> topLeftCorner;
    private Pair<Integer, Integer> bottomRightCorner;
    private int sum;

    public MaxSumRectangle(
        Pair<Integer, Integer> topLeftCorner, Pair<Integer, Integer> bottomRightCorner, int sum) {
      this.topLeftCorner = topLeftCorner;
      this.bottomRightCorner = bottomRightCorner;
      this.sum = sum;
    }

    public Pair<Integer, Integer> getTopLeftCorner() {
      return topLeftCorner;
    }

    public Pair<Integer, Integer> getBottomRightCorner() {
      return bottomRightCorner;
    }

    public int getSum() {
      return sum;
    }
  }
}
