import java.util.*;

public class ShortestDistancefromAllBuildings {

	public static void main(String[] args) {
		//You want to build a house on an empty land which reaches all buildings in the shortest amount of distance. 
		//You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:
		//Each 0 marks an empty land which you can pass by freely.
		//Each 1 marks a building which you cannot pass through.
		//Each 2 marks an obstacle which you cannot pass through.
		int[][] grid = {{1,0,2,0,1},{0,0,0,0,0},{0,0,1,0,0}};
		System.out.println(shortestDistance(grid));
	}
    public static int shortestDistance(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) return -1; 
        int row = grid.length;
        int col = grid[0].length;
        int[][] cost = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    bfs(grid, cost, i, j); 
                }
            }
        }
        
        int minCost = Integer.MAX_VALUE; 
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 0) {
                    minCost = Math.min(minCost, cost[i][j]);
                }
            }
        }
        return minCost == Integer.MAX_VALUE ? -1 : minCost; 
    }
    
    private static void bfs(int[][] grid, int[][] cost, int curI, int curJ) {
        int row = grid.length; 
        int col = grid[0].length;
        boolean[][] visited = new boolean[row][col];
        int[][] dirction = {{1,0},{-1,0},{0,1},{0,-1}}; 
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(curI*col + curJ);
        visited[curI][curJ] = true; 
        int distance = 0; 
        while(!queue.isEmpty()) {
            int size = queue.size(); 
            distance++;
            for (int i = 0; i < size; i++) {
                int curPosition = queue.poll(); 
                int curRow = curPosition/col;
                int curCol = curPosition%col; 
                for (int[] dirc : dirction) {
                    int nextRow = curRow + dirc[0];
                    int nextCol = curCol + dirc[1];
                    if (nextRow >= 0 && nextRow < row && nextCol >= 0 && nextCol < col
                       && grid[nextRow][nextCol] == 0 && !visited[nextRow][nextCol]) {
                        queue.offer(nextRow*col + nextCol);
                        visited[nextRow][nextCol] = true;
                        cost[nextRow][nextCol] += distance; 
                    }
                }
            }
        }
        
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 0 && !visited[i][j]) {
                    grid[i][j] = 2; 
                }
            }
        }
    }
}
