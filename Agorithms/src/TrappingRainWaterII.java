import java.util.*;

public class TrappingRainWaterII {

	public static void main(String[] args) {//Lihao 
		//Given an m x n matrix of positive integers representing the height of each unit cell in a 2D elevation map, 
		//compute the volume of water it is able to trap after raining.
		//Note:
		//Both m and n are less than 110. The height of each unit cell is greater than 0 and is less than 20,000.
		int[][] input = {
		                 {1,4,3,1,3,2},
		                 {3,2,1,3,2,4},
		                 {2,3,3,2,3,1}
		}; 
		System.out.println(trapRainWater(input));
	}
    public static int trapRainWater(int[][] heightMap) {
        if (heightMap == null || heightMap.length == 0 || heightMap[0] == null || heightMap[0].length == 0) {
            return 0; 
        }
        int row = heightMap.length;
        int col = heightMap[0].length;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(new Comparator<Integer> () {
            @Override 
            public int compare(Integer e1, Integer e2) {
                int value1 = heightMap[e1/col][e1%col];
                int value2 = heightMap[e2/col][e2%col];
                return value1 - value2;
            }
        });
        HashSet<Integer> visited = new HashSet<>();
        //put all edge into the minHeap
        for (int j = 0; j < col; j++) {
            minHeap.offer(j);
            minHeap.offer((row - 1) * col + j);
            visited.add(j);
            visited.add((row - 1) * col + j);
        }
        for (int i = 1; i < row - 1; i++) {
            minHeap.offer(i * col);
            minHeap.offer(i * col + col - 1); 
            visited.add(i * col);
            visited.add(i * col + col - 1);
        }
        //BFS
        int water = 0; 
        int outterEdge = Integer.MIN_VALUE;
        int[][] direction = {{1,0},{-1,0},{0,1},{0,-1}};
        while (!minHeap.isEmpty()) {
            int cur = minHeap.poll();
            int curRow = cur/col;
            int curCol = cur%col;
            outterEdge = Math.max(outterEdge, heightMap[curRow][curCol]);
            water += outterEdge - heightMap[curRow][curCol];
            for (int[] dirc : direction) {
                int nextRow = curRow + dirc[0];
                int nextCol = curCol + dirc[1];
                if (nextRow >= 0 && nextRow < row && nextCol >= 0 && nextCol < col
                   && visited.add(nextRow* col + nextCol)) {
                    minHeap.offer(nextRow* col + nextCol);
                }
            }
        }
        return water; 
    }
}
