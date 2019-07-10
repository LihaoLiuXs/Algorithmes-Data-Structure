
public class LongestIncreasingPathinaMatrix {

	public static void main(String[] args) {
		//Given an integer matrix, find the length of the longest increasing path.
		//From each cell, you can either move to four directions: left, right, up or down. 
		//You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).
		int[][] input = {{9,9,4},{6,6,8},{2,1,1}}; 
		System.out.println(longestIncreasingPath(input));
	}
    public static int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0; 
        }
        int max = 1;
        int row = matrix.length, col = matrix[0].length;
        int[][] memo = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                max = Math.max(dfs(matrix, i, j, Integer.MIN_VALUE, memo), max);
            }
        }
        return max;
    }
    private static int dfs(int[][] matrix, int i, int j, int preVal, int[][] memo) {
        int row = matrix.length, col = matrix[0].length;
        if (i < 0 || i >= row || j < 0 || j >= col || matrix[i][j] <= preVal) return 0;
        if (memo[i][j] != 0) return memo[i][j];
        int cur = matrix[i][j];
        int retVal = 0; 
        retVal = Math.max(dfs(matrix, i + 1, j, cur, memo), retVal);
        retVal = Math.max(dfs(matrix, i - 1, j, cur, memo), retVal);
        retVal = Math.max(dfs(matrix, i, j + 1, cur, memo), retVal);
        retVal = Math.max(dfs(matrix, i, j - 1, cur, memo), retVal);
        memo[i][j] = ++retVal;
        return retVal;
    }
}
