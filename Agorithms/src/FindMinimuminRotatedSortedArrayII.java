
public class FindMinimuminRotatedSortedArrayII {

	public static void main(String[] args) {//Lihao Liu
		//Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
		//Find the minimum element.
		//The array may contain duplicates.
		//(i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
		int[] input1 = {1,3,5}; 
		System.out.println(findMin(input1));
		int[] input2 = {2,2,2,0,1}; 
		System.out.println(findMin(input2));
		int[] input3 = new int[1];
		System.out.println(findMin(input3));
	}
    public static int findMin(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0, right = nums.length - 1;
        if (nums[left] < nums[right]) return nums[left];
        while (left + 1 < right) {
            while (left < right && nums[left] == nums[right]) left++;
            int mid = left + (right - left)/2;
            if (nums[right] < nums[mid]) left = mid;
            else right = mid; 
        }
        return nums[left] < nums[right] ? nums[left] : nums[right];
    }
}
