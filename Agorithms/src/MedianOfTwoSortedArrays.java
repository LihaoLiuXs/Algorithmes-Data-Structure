
public class MedianOfTwoSortedArrays {

	public static void main(String[] args) {//Lihao Liu
		//There are two sorted arrays nums1 and nums2 of size m and n respectively.
		//Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
		//You may assume nums1 and nums2 cannot be both empty.
		int[] array1 = {1,2};
		int[] array2 = {3};
		System.out.println(findMedianSortedArrays(array1,array2));
	}
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length = nums1.length + nums2.length;
        if (length%2 == 0) {
           return (Kth(nums1, 0, nums2, 0, length/2) + Kth(nums1, 0, nums2, 0, length/2 + 1))/2; 
        }
	    return Kth(nums1, 0, nums2, 0, length - length/2); 
    }
    private static double Kth(int[] a, int aleft, int[] b, int bleft, int k) {
	    if (aleft >= a.length) return b[bleft + k - 1];
	    if (bleft >= b.length) return a[aleft + k - 1];
	    if (k == 1) return a[aleft] < b[bleft] ? a[aleft] : b[bleft]; 
	    int bmid = bleft + k/2 - 1;
	    int amid = aleft + k/2 - 1; 
	    int bval = bmid < b.length ? b[bmid] : Integer.MAX_VALUE; 
	    int aval = amid < a.length ? a[amid] : Integer.MAX_VALUE;
	    if (aval <= bval) return Kth(a, amid + 1, b, bleft, k - k/2);
	    else return Kth(a, aleft, b, bmid + 1, k - k/2); 
    }
}
