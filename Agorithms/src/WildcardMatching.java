
public class WildcardMatching {

	public static void main(String[] args) {
		//Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.
		//'?' Matches any single character.
		//'*' Matches any sequence of characters (including the empty sequence).
		//The matching should cover the entire input string (not partial).

		//Note:

		//s could be empty and contains only lowercase letters a-z.
		//p could be empty and contains only lowercase letters a-z, and characters like ? or *.
		System.out.println(isMatch("aa","a"));
		System.out.println(isMatch("aa","*"));
		System.out.println(isMatch("cb","?a"));
	}
    public static boolean isMatch(String s, String p) {
        if (s == null && p == null) return true;
        return isMatch(s, 0, p, 0, new Boolean[s.length() + 1][p.length() + 1]);
    }
    private static boolean isMatch(String s, int sIndex, String p, int pIndex, Boolean[][] memo) {
        if (memo[sIndex][pIndex] != null) return memo[sIndex][pIndex];
        int pLen = p.length(), sLen = s.length();
        if (pLen == pIndex) return sLen == sIndex;
        if (p.charAt(pIndex) != '*') {
            memo[sIndex][pIndex] = false; 
            if (sIndex < sLen && (s.charAt(sIndex) == p.charAt(pIndex) || p.charAt(pIndex) == '?')) {
                if (isMatch(s, sIndex + 1, p, pIndex + 1, memo)) {
                    memo[sIndex][pIndex] = true;
                }
            }
        }
        else {
            boolean preMatch = isMatch(s, sIndex, p, pIndex + 1, memo); 
            boolean futherMatch = false;
            if (sIndex < sLen) futherMatch = isMatch(s, sIndex + 1, p, pIndex, memo);
            memo[sIndex][pIndex] = preMatch || futherMatch; 
        }
        return memo[sIndex][pIndex];
    }
}
