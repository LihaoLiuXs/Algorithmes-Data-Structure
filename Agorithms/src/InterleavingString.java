
public class InterleavingString {

	public static void main(String[] args) {//Lihao 
		//Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
		System.out.println(isInterleave("aabcc","dbbca","aadbbcbcac")); //true
		System.out.println(isInterleave("aabcc","dbbca","aadbbbaccc")); //false
	}
    public static boolean isInterleave(String s1, String s2, String s3) {
        if (s1 == null && s2 == null && s3 == null) return true; 
        if ((s1 == null || s2 == null) && s3 != null) return false; 
        int len1 = s1.length(), len2 = s2.length(), len3 = s3.length();
        if (len1 + len2 != len3) return false; 
        boolean[][] dp = new boolean[len1 + 1][len2 + 1];
        dp[0][0] = true; 
        for (int i = 1; i <= len1; i++) dp[i][0] = s1.substring(0, i).equals(s3.substring(0, i));
        for (int j = 1; j <= len2; j++) dp[0][j] = s2.substring(0, j).equals(s3.substring(0, j));
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                char c1 = s1.charAt(i - 1), c2 = s2.charAt(j - 1), c3 = s3.charAt(j + i - 1);
                if (c1 == c3) dp[i][j] = dp[i - 1][j];
                if (c2 == c3) dp[i][j] = dp[i][j] || dp[i][j - 1];
            }
        }
        return dp[len1][len2];
    }
}
