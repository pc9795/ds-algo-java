package gfg.algo.backtracking;

import utils.Utils;

import java.util.ArrayDeque;

public class RatInMaze {
  public static void ratInMaze(int mat[][]) {
    ArrayDeque<String> stack = new ArrayDeque<>();
    stack.push("0,0");
    if (ratInMazeUtil(mat, stack, 0, 0)) {
      System.out.println(stack);
    } else {
      System.out.println("No path found!");
    }
  }

  private static boolean ratInMazeUtil(int mat[][], ArrayDeque<String> path, int x, int y) {
    if (x == mat.length - 1 && y == mat[0].length - 1) {
      return true;
    }
    if (Utils.isSafe(x + 1, y, mat) && mat[x + 1][y] != 0) {
      path.push((x + 1) + "," + y);
      if (ratInMazeUtil(mat, path, x + 1, y)) {
        return true;
      } else {
        path.pop();
      }
    }
    if (Utils.isSafe(x, y + 1, mat) && mat[x][y + 1] != 0) {
      path.push((x) + "," + (y + 1));
      if (ratInMazeUtil(mat, path, x, y + 1)) {
        return true;
      } else {
        path.pop();
      }
    }
    return false;
  }
}
