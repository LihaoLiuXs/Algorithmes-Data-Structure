import java.util.*;

public class TheSkylineProblem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
    public static List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (buildings == null || buildings.length == 0) return res;
        List<EndPoint> points = new ArrayList<>();
        for (int[] building : buildings) {
            points.add(new EndPoint(building[0], building[2], true));
            points.add(new EndPoint(building[1], building[2], false));
        } 
        Collections.sort(points);
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(points.size(), new Comparator<Integer> () {
            @Override
            public int compare(Integer a, Integer b) {
                return b - a;
            }
        });
        
        for (EndPoint ep : points) {
            if (ep.startPoint) {
               if (maxHeap.isEmpty() || maxHeap.peek() < ep.height) {
                    res.add(Arrays.asList(ep.x, ep.height));
               }
               maxHeap.offer(ep.height);
            }
            else {
                maxHeap.remove(ep.height);
                if (maxHeap.isEmpty() || maxHeap.peek() < ep.height) {
                    res.add(Arrays.asList(ep.x, maxHeap.isEmpty() ? 0 : maxHeap.peek()));
                }
            }
        }
        return res; 
    }
    
    private static class EndPoint implements Comparable<EndPoint>{
        int x; 
        int height; 
        boolean startPoint;
        
        EndPoint(int x, int height, boolean startPoint) {
            this.x = x;
            this.height = height;
            this.startPoint = startPoint;
        }
        
        @Override
        public int compareTo(EndPoint that) {
            if (this.x != that.x) {
                return this.x - that.x;
            }
            else {
                if (this.startPoint && that.startPoint) {
                    return that.height - this.height;
                }
                if (!this.startPoint && !that.startPoint) {
                    return this.height - that.height;
                }
                else {
                    return this.startPoint ? -1 : 1; 
                }
            }
        }
    }
}
