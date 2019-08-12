import java.util.*;

public class CutOffTreesforGolfEvent {

	public static void main(String[] args) {
		//You are asked to cut off trees in a forest for a golf event. The forest is represented as a non-negative 2D map, in this map:
		//0 represents the obstacle can't be reached.
		//1 represents the ground can be walked through.
		//The place with number bigger than 1 represents a tree can be walked through, and this positive number represents the tree's height.
		//You are asked to cut off all the trees in this forest in the order of tree's height - always cut off the tree with lowest height first. And after cutting, the original place has the tree will become a grass (value 1).
		//You will start from the point (0, 0) and you should output the minimum steps you need to walk to cut off all the trees. If you can't cut off all the trees, output -1 in that situation.
		//You are guaranteed that no two trees have the same height and there is at least one tree needs to be cut off.

	}
    public static int cutOffTree(List<List<Integer>> forest) {
        if (forest == null || forest.size() == 0) return 0;
        int row = forest.size();
        int col = forest.get(0).size();
        PriorityQueue<Point> minHeap = new PriorityQueue<>(row * col, new Comparator<Point> () {
            @Override
            public int compare(Point a, Point b) {
                return forest.get(a.x).get(a.y) - forest.get(b.x).get(b.y);
            }
        });
        
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (forest.get(i).get(j) > 1) {
                    minHeap.offer(new Point(i, j));
                }
            }
        }
        int sum = twoWayBFS(new Point(0,0), minHeap.peek(), forest);
        if (sum == -1) return -1; 
        while (!minHeap.isEmpty()) {
            Point cur = minHeap.poll();
            if (minHeap.isEmpty()) break;
            Point target = minHeap.peek();
            int curSum = twoWayBFS(cur, target, forest);
            if (curSum == -1) return -1;
            sum += curSum;
        }
        return sum;
    }
    
    private static int twoWayBFS(Point start, Point end, List<List<Integer>> forest) {
        HashSet<Point> startPoints = new HashSet<>();
        HashSet<Point> endPoints = new HashSet<>();
        HashSet<Point> visited = new HashSet<>();
        startPoints.add(start);
        endPoints.add(end);
        int distance = 0; 
        int row = forest.size();
        int col = forest.get(0).size(); 
        int[][] dirctions = {{1,0}, {0,1}, {-1,0}, {0,-1}};
        while (!startPoints.isEmpty() && !endPoints.isEmpty()) {
            HashSet<Point> temp = new HashSet<>();
            for (Point cur : startPoints) {
                if (endPoints.contains(cur)) return distance;
                if (visited.contains(cur)) continue;
                visited.add(cur);
                for (int[] dirct : dirctions) {
                    int nextRow = cur.x + dirct[0];
                    int nextCol = cur.y + dirct[1];
                    if (nextRow >= 0 && nextRow < row && nextCol >= 0 && nextCol < col
                       && forest.get(nextRow).get(nextCol) > 0) {
                        Point next = new Point(nextRow, nextCol);
                        if (!visited.contains(next)) temp.add(next);
                    }
                }
            }
            distance++;
            startPoints = endPoints;
            endPoints = temp; 
        }
        return -1; 
    }
    private static class Point {
        int x;
        int y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        @Override
        public int hashCode() {
            return x * 31 + y;
        }
        
        @Override
        public boolean equals(Object o) {
            if (o instanceof Point) {
                Point that = (Point) o;
                return this.x == that.x && this.y == that.y;
            }
            else return false; 
        } 
    }
}
