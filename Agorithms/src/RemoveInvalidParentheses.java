import java.util.*;

public class RemoveInvalidParentheses {

	public static void main(String[] args) {
		//Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.
		//Note: The input string may contain letters other than the parentheses ( and ).
		List<String> res = removeInvalidParentheses("()())()");
		for (String s : res) System.out.println(s);
	}
    public static List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>(); 
        if (s == null) return res;
        HashSet<String> resSet = new HashSet<>();
        int rmLeft = 0;
        int rmRight = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                rmLeft++;
            }
            else if (s.charAt(i) == ')'){
                if (rmLeft == 0) {
                    rmRight++;
                }
                else rmLeft--; 
            }
        }
        helper(s, 0, rmLeft, rmRight, 0, resSet, new StringBuilder());
        for (String str : resSet) res.add(str);
        return res;
    }
    private static void helper(String s, int index, int rmLeft, int rmRight, int delta, HashSet<String> set, StringBuilder sb) {
        if (index == s.length() && rmLeft == 0 && rmRight == 0 && delta == 0) {
            set.add(new String(sb));
            return;
        }
        int size = sb.length();
        if (delta < 0 || rmLeft < 0 || rmRight < 0 || index >= s.length()) return;
        if (s.charAt(index) == '(') {
            helper(s, index + 1, rmLeft - 1, rmRight, delta, set, sb);
            sb.append('(');
            helper(s, index + 1, rmLeft, rmRight, delta + 1, set, sb);
            sb.setLength(size);
        }
        else if (s.charAt(index) == ')') {
            helper(s, index + 1, rmLeft, rmRight - 1, delta, set, sb);
            sb.append(')');
            helper(s, index + 1, rmLeft, rmRight, delta - 1, set, sb);
            sb.setLength(size);
        }
        else {
            sb.append(s.charAt(index));
            helper(s, index + 1, rmLeft, rmRight, delta, set, sb);
            sb.setLength(size);
        }
    }
}
