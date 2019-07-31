import java.util.*;

public class WordPatternII {

	public static void main(String[] args) {//Lihao
		//Given a pattern and a string str, find if str follows the same pattern.
		//Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.
		System.out.println(wordPatternMatch("abab", "redblueredblue"));
		System.out.println(wordPatternMatch("aaaa", "asdasdasdasd"));
		System.out.println(wordPatternMatch("aabb", "xyzabcxzyabc"));
	}
    public static boolean wordPatternMatch(String pattern, String str) {
        if (pattern == null && str == null) return true;
        if (pattern == null || str == null) return false;
        return dfs(pattern, 0, str, 0, new HashMap<Character, String>(), new HashSet<String>()); 
    }
    private static boolean dfs(String p, int pIndex, String s, int sIndex, HashMap<Character,String> map, HashSet<String> set) {
        int pLen = p.length(), sLen = s.length(); 
        if (pLen == pIndex && sLen == sIndex) return true;
        if (pLen == pIndex || sLen == sIndex) return false;
        char cur = p.charAt(pIndex);
        String match = map.get(cur);
        if (match != null) {
            int mLen = match.length(); 
            if (sIndex + mLen > sLen) return false;
            else {
                String str = s.substring(sIndex, sIndex + mLen); 
                if (str.equals(match)) {
                    if (dfs(p, pIndex + 1, s, sIndex + mLen, map, set)) return true; 
                }
                else return false; 
            }
        }
        else {
            for(int i = sIndex + 1; i <= sLen; i++) {
                String str = s.substring(sIndex, i); 
                if (set.contains(str)) continue; 
                map.put(cur, str);
                set.add(str);
                boolean ret = dfs(p, pIndex + 1, s, i, map, set);
                map.remove(cur);
                set.remove(str); 
                if (ret) return true; 
            }
        }
        return false; 
    }
}
