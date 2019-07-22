
public class LongestSubstringWithAtMostTwoDistinctCharacters {

	public static void main(String[] args) {
		//Given a string s , find the length of the longest substring t  that contains at most 2 distinct characters.
		System.out.println(lengthOfLongestSubstringTwoDistinct("eceba"));
		System.out.println(lengthOfLongestSubstringTwoDistinct("ccaabbb"));
	}
    public static int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null || s.length() == 0) return 0; 
        int start = 0, max = 0, idx1 = -1, idx2 = -1, end = 0;
        while (end < s.length()) {
            if (idx1 == -1 || s.charAt(end) == s.charAt(idx1)) {
                idx1 = end++;
            }
            else if (idx2 == -1 || s.charAt(end) == s.charAt(idx2)) {
                idx2 = end++; 
            }
            else {
                if (idx1 < idx2) {
                    start = idx1 + 1; 
                    idx1 = end++; 
                }
                else {
                    start = idx2 + 1;
                    idx2 = end++; 
                }
            }
            max = Math.max(max, end - start); 
        }
        return max; 
    }
}
