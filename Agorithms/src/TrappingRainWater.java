
public class TrappingRainWater {

	public static void main(String[] args) {
		//Given n non-negative integers representing an elevation map where the width of each bar is 1, 
		//compute how much water it is able to trap after raining.
		//The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. 
		//Thanks Marcos for contributing this image!
		int[] input = {0,1,0,2,1,0,1,3,2,1,2,1};
		System.out.println(trap(input));
	}
    public static int trap(int[] height) {
        if (height == null || height.length <= 2) return 0;
        int areaHeight = 0;
        int left = 0, right = height.length - 1, sum = 0; 
        while (left < right) {
            int curHeight = Math.min(height[left], height[right]);
            if (curHeight >= areaHeight) areaHeight = curHeight;
            else sum += areaHeight - curHeight;
            if (height[left] < height[right]) left++; 
            else right--; 
        }
        return sum; 
    }
}
