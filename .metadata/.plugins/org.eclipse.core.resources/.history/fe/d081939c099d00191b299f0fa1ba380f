
public class JumpGameII {

	public static void main(String[] args) {
		//Given an array of non-negative integers, you are initially positioned at the first index of the array.
		//Each element in the array represents your maximum jump length at that position.
		//Your goal is to reach the last index in the minimum number of jumps.
		int[] input1 = {1,3,5}; 
		System.out.println(jump(input1));
		int[] input2 = {2,3,1,1,4}; 
		System.out.println(jump(input2));
		int[] input3 = {}; 
		System.out.println(jump(input3));
	}
	
    public static int jump(int[] nums) {
        if (nums == null || nums.length <= 1) return 0;
        int curEnd = 0;
        int curFarest = 0;
        int jump = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            curFarest = Math.max(curFarest, nums[i] + i);
            if (i == curEnd) {
                jump++; 
                curEnd = curFarest; 
            }         
        }
        return jump; 
    }

}
