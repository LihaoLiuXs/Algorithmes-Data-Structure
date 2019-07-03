
public class REM_pruning_v1 {//RegularExpressionMatching

	public static void main(String[] args) {
		//Given an input string (s) and a pattern (p), 
		//implement regular expression matching with support for '.' and '*'.
		//'.' Matches any single character.
		//'*' Matches zero or more of the preceding element.
		//The matching should cover the entire input string (not partial).
		//s could be empty and contains only lowercase letters a-z.
		//p could be empty and contains only lowercase letters a-z, and characters like . or *.
		//DFS pruning solution
        System.out.println(isMatch("aa","a")); //false 
        System.out.println(isMatch("aa","a*")); //true
        System.out.println(isMatch("ab",".*")); //true
        System.out.println(isMatch("aab","c*a*b")); //true
        System.out.println(isMatch("mississippi","mis*is*p*.")); //false
	}
	
    public static boolean isMatch(String s, String p) {
        if (s == null && p == null) return true;
        return isMatch(s, 0, p, 0, new Boolean[s.length() + 1][p.length() + 1]); 
    }
    
    private static boolean isMatch(String s, int sIndex, String p, int pIndex, Boolean[][] memo) {
        int sLen = s.length(), pLen = p.length();
        if (memo[sIndex][pIndex] != null) return memo[sIndex][pIndex];
        if (pIndex == pLen) return sIndex == sLen; 
        else if (pIndex == pLen -1 || p.charAt(pIndex + 1) != '*') {
            if (sIndex < sLen && (s.charAt(sIndex) == p.charAt(pIndex) || p.charAt(pIndex) == '.')) {
                return isMatch(s, sIndex + 1, p, pIndex + 1, memo);
            }
            else {
                memo[sIndex][pIndex] = false;
                return false;
            }
        }
        else {
            int index = sIndex - 1; 
            while (index < sLen && (index == sIndex - 1 || s.charAt(index) == p.charAt(pIndex) || p.charAt(pIndex) == '.')) {
                if (isMatch(s, index + 1, p, pIndex + 2, memo)) {
                    memo[sIndex][pIndex] = true;
                    return true;
                }
                index++;
            }
            memo[sIndex][pIndex] = false; 
            return false;
        }
    }
}
