import java.util.*;

public class WordBreakII {

	public static void main(String[] args) {
		//Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, 
		//add spaces in s to construct a sentence where each word is a valid dictionary word. 
		//Return all such possible sentences.
		//Note: 
		//The same word in the dictionary may be reused multiple times in the segmentation.
		//You may assume the dictionary does not contain duplicate words.
		List<String> wordList = Arrays.asList("cat", "cats", "and", "sand", "dog"); 
		System.out.println(wordBreak("catsanddog", wordList));
	}
    public static List<String> wordBreak(String s, List<String> wordDict) {
        List<String> res = new ArrayList<>(); 
        if(s == null || wordDict == null) return res;
        HashSet<String> dict = getSet(wordDict);
        dfs(s, 0, dict, res, new StringBuilder(), new Boolean[s.length() + 1]);
        return res;
    }
    private static void dfs(String s, int index, HashSet<String> dict, List<String> res, StringBuilder sb, Boolean[] memo) {
        if (memo[index] != null && memo[index] == false) return; 
        if (index == s.length()) {
            sb.setLength(sb.length() - 1);
            res.add(new String(sb));
            return; 
        }
        int resLength = res.size();
        for (int i = index; i <= s.length(); i++) {
            String cur = s.substring(index, i); 
            if (dict.contains(cur)) {
                int size = sb.length();
                sb.append(cur + " "); 
                dfs(s, i, dict, res, sb, memo);
                sb.setLength(size);
            }
        }
        memo[index] = !(resLength == res.size());
    }
    private static HashSet<String> getSet(List<String> wordDict) {
        HashSet<String> set = new HashSet<>();
        for (String s : wordDict) {
            set.add(s);
        }
        return set;
    }
}
