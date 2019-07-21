import java.util.*;

public class FrogJump {

	public static void main(String[] args) {
		//A frog is crossing a river. 
		//The river is divided into x units and at each unit there may or may not exist a stone. 
		//The frog can jump on a stone, but it must not jump into the water.
		//Given a list of stones' positions (in units) in sorted ascending order, determine if the frog is able to cross the river by landing on the last stone. 
		//Initially, the frog is on the first stone and assume the first jump must be 1 unit.
		//If the frog's last jump was k units, then its next jump must be either k - 1, k, or k + 1 units. Note that the frog can only jump in the forward direction.
		//The number of stones is larger or equal to 2 and is less than 1,100.
		//Each stone's position will be a non-negative integer < 231.
		//The first stone's position is always 0.
		int[] input1 = {0,1,3,5,6,8,12,17};
		System.out.println(canCross(input1)); //true
		int[] input2 = {0,1,2,3,4,8,9,11};
		System.out.println(canCross(input2));  //false
	}
    public static boolean canCross(int[] stones) {
        if (stones == null || stones.length <= 1) return true;
        if (stones[1] - stones[0] != 1) return false;
        HashMap<Integer, Boolean>[] memo = new HashMap[stones.length + 1];
        for (int i = 0; i < memo.length; i++) {
            memo[i] = new HashMap<Integer, Boolean>(); 
        }
        return canCross(stones, 1, 1, memo);
    }
    private static boolean canCross(int[] stones, int index, int diff, HashMap<Integer, Boolean>[] memo) {
        int len = stones.length; 
        if (index == len - 1) return true;
        HashMap<Integer, Boolean> map = memo[index];
        Boolean res = map.get(diff);
        if (res != null) return res;
        for (int i = index + 1; i < len; i++) {
            int newDiff = stones[i] - stones[index]; 
            if (newDiff < diff - 1) continue;
            if (newDiff > diff + 1) break;
            if (canCross(stones, i, newDiff, memo)) {
                map.put(diff, true);
                return true;
            }
        }
        map.put(diff, false);
        return false;
    }
}
