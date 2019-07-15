
public class CountTracking {
	public static void main(String[] args) {
		//tracking how many turn needed for go back to the origin
		int[] input1 = {5,1,0,4,2,3}; 
		System.out.println(Hop(input1, 3));
	}
	private static int Hop(int[] array, int target) {
		if (array[target] == -1) return -1; 
		int nextTarget = array[target];
		array[target] = -1; 
		int count = Hop(array, nextTarget) + 1;
		array[target] = nextTarget;
		return count; 
	}
}
