
public class RegularExpressionMatching {

	public static void main(String[] args) { //Lihao Liu
		//Given an input string (s) and a pattern (p), 
		//implement regular expression matching with support for '.' and '*'.
		//'.' Matches any single character.
		//'*' Matches zero or more of the preceding element.
		//The matching should cover the entire input string (not partial).
		//s could be empty and contains only lowercase letters a-z.
		//p could be empty and contains only lowercase letters a-z, and characters like . or *.
		//DFS no pruning solution
        System.out.println(isMatch("aa","a")); //false 
        System.out.println(isMatch("aa","a*")); //true
        System.out.println(isMatch("ab",".*")); //true
        System.out.println(isMatch("aab","c*a*b")); //true
        System.out.println(isMatch("mississippi","mis*is*p*.")); //false
	}

    public static boolean isMatch(String s, String p) {
        if (s == null && p == null) return true; 
        return helper(s, 0, p, 0);
    }
    private static boolean helper(String s, int indexS, String p, int indexP) {
        if (indexP == p.length()) {
            return indexS == s.length();
        }
        //if (indexS > s.length() || indexP > p.length()) return false;
        if (indexP + 1 == p.length() || p.charAt(indexP + 1) != '*') {
            if (indexS < s.length() && s.charAt(indexS) == p.charAt(indexP) || p.charAt(indexP) == '.') {
                return helper(s, indexS + 1, p, indexP + 1);
            }
            else return false; 
        }
        else {
            int index = indexS - 1; 
            while (index < s.length() && (index < indexS || s.charAt(index) == p.charAt(indexP) || p.charAt(indexP) == '.')) {
                if (helper(s, index + 1, p, indexP + 2)) {
                    return true; 
                }
                index++; 
            }
            return false; 
        }
    }
}
