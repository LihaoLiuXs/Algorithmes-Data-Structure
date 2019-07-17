
public class PalindromePartitioningII {

	public static void main(String[] args) {//Lihao Liu
		//Given a string s, partition s such that every substring of the partition is a palindrome.
		//Return the minimum cuts needed for a palindrome partitioning of s.
		System.out.println(minCut("aab"));   //1
	}
    public static int minCut(String s) {
        if (s == null || s.length() <= 1) return 0;
        int len = s.length();
        int[] dp = new int[len + 1];
        for (int i = 0; i < len; i++) {
            dp[i] = Integer.MAX_VALUE; 
        }
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i + 1; j <= len; j++) {
                String str = s.substring(i, j);
                if (isPalindrome(str)) {
                    dp[i] = Math.min(dp[j] + 1, dp[i]);
                }
            }
        }
        return dp[0] - 1;
    }
    private static boolean isPalindrome(String s) {
        if (s == null || s.length() <= 1) return true;
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) return false;
            left++;
            right--;
        }
        return true; 
    }
}
