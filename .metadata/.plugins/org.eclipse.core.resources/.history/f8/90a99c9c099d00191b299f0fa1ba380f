import java.util.*;

public class ExpressionAddOperators {

	public static void main(String[] args) {
		//Given a string that contains only digits 0-9 and a target value, 
		//return all possibilities to add binary operators (not unary) +, -, or * 
		//between the digits so they evaluate to the target value.
		System.out.println(addOperators("123",6));
		System.out.println(addOperators("232",8));
		System.out.println(addOperators("105",5));
		System.out.println(addOperators("00",0));
		System.out.println(addOperators("3456237490",9191));
	}

    public static List<String> addOperators(String num, int target) {
        List<String> res = new LinkedList<>();
        if (num == null) return res;
        helper(num, target, 0, res, 0, 0, new StringBuilder());
        return res;
    }
    private static void helper(String num, int target, int index, List<String> res, long sum, long pre, StringBuilder sb) {
        if (index == num.length()) {
            if (sum == target) res.add(0, new String(sb));
            return;
        }
        long value = 0; 
        for (int i = index; i < num.length(); i++) {
            value = value * 10 + (num.charAt(i) - '0');
            int size = sb.length();
            if (size == 0) {
                sb.append(value);
                helper(num, target, i + 1, res, sum + value, value, sb);
                sb.setLength(size);
            }
            else {
                sb.append("+" + value);
                helper(num, target, i + 1, res, sum + value, value, sb);
                sb.setLength(size);
                sb.append("-" + value);
                helper(num, target, i + 1, res, sum - value, -value, sb);
                sb.setLength(size);
                sb.append("*" + value);
                helper(num, target, i + 1, res, sum + pre * value - pre, pre * value, sb);
                sb.setLength(size);
            }
            if (value == 0) break; 
        }
    }
}
