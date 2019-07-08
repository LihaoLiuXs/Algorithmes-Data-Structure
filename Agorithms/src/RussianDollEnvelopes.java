import java.util.*;

public class RussianDollEnvelopes {

	public static void main(String[] args) {
		//You have a number of envelopes with widths and heights given as a pair of integers (w, h). 
		//One envelope can fit into another if and only if both the width and height of one envelope is greater than the width and height of the other envelope.
		//What is the maximum number of envelopes can you Russian doll? (put one inside other)
		//Note: Rotation is not allowed.
		int[][] input = {{5,4},{6,4},{6,7},{2,3}};
		System.out.println(maxEnvelopes(input));
	}
    public static int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0 || envelopes[0] == null || envelopes[0].length == 0) {
            return 0; 
        }
        Arrays.sort(envelopes, new Comparator<int[]> () {
        	@Override
        	public int compare(int[] e1, int[] e2) {
        		if (e1[0] == e2[0]) {
        			return e2[1] - e1[1];
        		}
        		return e1[0] - e2[0];
        	}
        });
        List<Integer> refined_memo = new ArrayList<>();
        for (int i = 0; i < envelopes.length; i++) {
            if (refined_memo.size() == 0 || envelopes[i][1] > refined_memo.get(refined_memo.size() - 1)) {
                refined_memo.add(envelopes[i][1]);
            }
            int index = binarySearch(refined_memo, envelopes[i][1]);
            refined_memo.set(index, envelopes[i][1]);
        }
        return refined_memo.size(); 
    }
    private static int binarySearch(List<Integer> list, int target) {
        int left = 0, right = list.size() - 1; 
        while (left + 1 < right) {
            int mid = left + (right - left)/2;
            if (list.get(mid) >= target){
                right = mid;
            }        
            else {
                left = mid + 1;
            }
        }
        return list.get(left) >= target ? left : right;
    }
}
