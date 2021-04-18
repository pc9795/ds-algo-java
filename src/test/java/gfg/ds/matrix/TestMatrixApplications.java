package gfg.ds.matrix;

import org.junit.jupiter.api.Test;
import utils.Pair;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class TestMatrixApplications {

  @Test
  void testSearchInColumnAndRowWiseSortedMatrix() {
    int[][] mat =
        new int[][] {{10, 20, 30, 40}, {15, 25, 35, 45}, {27, 29, 37, 48}, {32, 33, 39, 50}};

    Optional<Pair<Integer, Integer>> actual =
        MatrixApplications.searchInColumnAndRowWiseSortedMatrix(mat, 29);

    assert actual.isPresent();
    assert actual.get().equals(new Pair<>(2, 1));

    mat = new int[][] {{10, 20, 30, 40}, {15, 25, 35, 45}, {27, 29, 37, 48}, {32, 33, 39, 50}};

    actual = MatrixApplications.searchInColumnAndRowWiseSortedMatrix(mat, 100);

    assert actual.isEmpty();
  }

  @Test
  void testPrintInSpiralForm() {
    int[][] mat =
        new int[][] {
          {1, 2, 3, 4},
          {5, 6, 7, 8},
          {9, 10, 11, 12},
          {13, 14, 15, 16}
        };

    List<Integer> traversal = MatrixApplications.getSpiralList(mat);

    assert traversal.equals(Arrays.asList(1, 2, 3, 4, 8, 12, 16, 15, 14, 13, 9, 5, 6, 7, 11, 10));

    mat =
        new int[][] {
          {1, 2, 3, 4, 5, 6},
          {7, 8, 9, 10, 11, 12},
          {13, 14, 15, 16, 17, 18}
        };

    traversal = MatrixApplications.getSpiralList(mat);

    assert traversal.equals(
        Arrays.asList(1, 2, 3, 4, 5, 6, 12, 18, 17, 16, 15, 14, 13, 7, 8, 9, 10, 11));
  }

  @Test
  void testMakeColumnAndRowOneHavingAtLeastOneOne() {
    int[][] mat =
        new int[][] {
          {1, 0, 0, 1},
          {0, 0, 1, 0},
          {0, 0, 0, 0}
        };

    int[][] expected =
        new int[][] {
          {1, 1, 1, 1},
          {1, 1, 1, 1},
          {1, 0, 1, 1}
        };

    assert Arrays.deepEquals(
        MatrixApplications.makeColumnAndRowOneHavingAtLeastOneOne(mat), expected);
  }

  @Test
  void testGetUniqueRowIndices() {
    boolean[][] mat =
        new boolean[][] {
          {false, true, false, false, true},
          {true, false, true, true, false},
          {false, true, false, false, true},
          {true, true, true, false, false}
        };

    assert MatrixApplications.getUniqueRowIndices(mat).equals(Arrays.asList(0, 1, 3));
  }

  @Test
  void testGetMaxSizeSquareSubMatrixPositionWitAllOnes() {
    int[][] mat = {
      {0, 1, 1, 0, 1},
      {1, 1, 0, 1, 0},
      {0, 1, 1, 1, 0},
      {1, 1, 1, 1, 0},
      {1, 1, 1, 1, 1},
      {0, 0, 0, 0, 0}
    };

    Pair<Integer, Pair<Integer, Integer>> result =
        MatrixApplications.getMaxSizeSquareSubMatrixPositionWitAllOnes(mat);

    assert result.key == 3;
    assert result.value.equals(new Pair<>(4, 3));
  }

  @Test
  void testTransposeInplace() {
    int[] mat = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
    int[] expected = {1, 4, 7, 10, 2, 5, 8, 11, 3, 6, 9, 12};

    MatrixApplications.transposeInplace(mat, 4, 3);

    assert Arrays.equals(mat, expected);
  }

  @Test
  void testCommonElementInRowWiseSortedMatrix() {
    int[][] mat = {
      {10, 10, 10},
      {1, 1, 1},
      {1, 1, 1},
    };

    assert MatrixApplications.commonElementInRowWiseSortedMatrix(mat).isEmpty();
  }

  @Test
  void testCommonElementInRowWiseSortedMatrix2() {
    int[][] mat = {
      {1, 2, 3, 4, 5},
      {2, 4, 5, 8, 10},
      {3, 5, 7, 9, 11},
      {1, 3, 5, 7, 9}
    };

    Optional<Integer> result = MatrixApplications.commonElementInRowWiseSortedMatrix(mat);
    assert result.isPresent();
    assert result.get() == 5;
  }

  @Test
  void testCommonElementInRowWiseSortedMatrixWithHashing() {
    int[][] mat = {
      {10, 10, 10},
      {1, 1, 1},
      {1, 1, 1},
    };

    assert MatrixApplications.commonElementInRowWiseSortedMatrixWithHashing(mat).isEmpty();
  }

  @Test
  void testCommonElementInRowWiseSortedMatrixWithHashing2() {
    int[][] mat = {
      {1, 2, 3, 4, 5},
      {2, 4, 5, 8, 10},
      {3, 5, 7, 9, 11},
      {1, 3, 5, 7, 9}
    };

    Optional<Integer> result =
        MatrixApplications.commonElementInRowWiseSortedMatrixWithHashing(mat);
    assert result.isPresent();
    assert result.get() == 5;
  }

  @Test
  void testGetIslandCount() {
    char[][] mat = {
      {'O', 'O', 'O'},
      {'X', 'X', 'O'},
      {'X', 'X', 'O'},
      {'O', 'O', 'X'},
      {'O', 'O', 'X'},
      {'X', 'X', 'O'}
    };

    assert MatrixApplications.getIslandsCount(mat) == 3;
  }

  @Test
  void testGetSumOfAllSubSquares() {
    int[][] mat = {
      {1, 1, 1, 1, 1}, {2, 2, 2, 2, 2}, {3, 3, 3, 3, 3}, {4, 4, 4, 4, 4}, {5, 5, 5, 5, 5}
    };

    int[][] expected = {
      {18, 18, 18},
      {27, 27, 27},
      {36, 36, 36}
    };

    int[][] result = MatrixApplications.getSumOfAllSubSquares(mat, 3);

    assert Arrays.deepEquals(result, expected);
  }

  @Test
  void testMatrixMultiplication1X1() {
    int[][] mat1 = new int[][] {{1}};
    int[][] mat2 = new int[][] {{2}};
    int[][] expected = new int[][] {{2}};

    int[][] result = MatrixApplications.matrixMultiplication(mat1, mat2);
    assert Arrays.deepEquals(result, expected);
  }

  @Test
  void testMatrixMultiplication2X2() {
    int[][] mat1 = new int[][] {{1, 2}, {3, 4}};
    int[][] mat2 = new int[][] {{5, 6}, {7, 8}};
    int[][] expected = new int[][] {{19, 22}, {43, 50}};

    int[][] result = MatrixApplications.matrixMultiplication(mat1, mat2);
    assert Arrays.deepEquals(result, expected);
  }

  @Test
  void testMatrixMultiplication3X3() {
    int[][] mat1 = new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
    int[][] mat2 = new int[][] {{10, 11, 12}, {13, 14, 15}, {16, 17, 18}};
    int[][] expected = new int[][] {{84, 90, 96}, {201, 216, 231}, {318, 342, 366}};

    int[][] result = MatrixApplications.matrixMultiplication(mat1, mat2);
    assert Arrays.deepEquals(result, expected);
  }

  @Test
  void testGetMaxSumRectangle() {
    int[][] mat =
        new int[][] {{1, 2, -1, -4, -20}, {-8, -3, 4, 2, 1}, {3, 8, 10, 1, 3}, {-4, -1, 1, 7, -6}};

    MatrixApplications.MaxSumRectangle result = MatrixApplications.getMaxSumRectangle(mat);

    assert result.getTopLeftCorner().equals(Pair.of(1, 1));
    assert result.getBottomRightCorner().equals(Pair.of(3, 3));
    assert result.getSum() == 29;
  }
}
