import java.util.*;

public class ZumaGame {

	public static void main(String[] args) {
		//Think about Zuma Game. You have a row of balls on the table, colored red(R), yellow(Y), blue(B), green(G), and white(W). 
		//You also have several balls in your hand.
		//Each time, you may choose a ball in your hand, and insert it into the row (including the leftmost place and rightmost place). 
		//Then, if there is a group of 3 or more balls in the same color touching, remove these balls. Keep doing this until no more balls can be removed.
		//Find the minimal balls you have to insert to remove all the balls on the table. 
		//If you cannot remove all the balls, output -1.
		System.out.println(findMinStep("WRRBBW", "RB"));
		System.out.println(findMinStep("WWRRBBWW", "WRBRW"));
		System.out.println(findMinStep("G", "GGGGG"));
		System.out.println(findMinStep("RBYYBBRRB", "YRBGB"));
	}
	
    public static int findMinStep(String board, String hand) {
        if (board == null || board.length() == 0) return 0;
        if (hand == null || hand.length() == 0) return -1;
        int[] min = new int[1];
        min[0] = Integer.MAX_VALUE;
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : hand.toCharArray()) {
            Integer num = map.get(c);
            if (num != null) map.put(c, num + 1);
            else map.put(c, 1);
        }
        helper(board, map, 0, min);
        return min[0] == Integer.MAX_VALUE ? -1 : min[0];
    }
    private static void helper(String board, HashMap<Character, Integer> map, int usedBall, int[] min) {
        if (board.length() == 0) {
            min[0] = Math.min(usedBall, min[0]);
            return; 
        }
        if (map.size() == 0) {
            return;
        }
        for (int i = 0; i < board.length(); i++) {
            char cur = board.charAt(i);
            Integer num = map.get(cur);
            if (num != null) {
                if (i < board.length() - 1 && board.charAt(i + 1) == cur) {
                    if (num - 1 == 0) map.remove(cur);
                    else map.put(cur, num - 1);
                    String newBoard = updateBoard(board, i - 1, i + 2);
                    helper (newBoard, map, usedBall + 1, min);
                    map.put(cur, num);
                }
                if (num >= 2) {
                    if (num - 2 == 0) map.remove(cur);
                    else map.put(cur, num - 2);
                    String newBoard = updateBoard(board, i - 1, i + 1);
                    helper (newBoard, map, usedBall + 2, min);
                    map.put(cur, num);
                }
            }
        }
    }
    private static String updateBoard(String board, int left, int right) {
        if (board == null || board.length() == 0) return board;
        while (left >= 0 && right < board.length()) {
            int removeBall = 0; 
            int i = left, j = right;
            while (i >= 0 && board.charAt(i) == board.charAt(right)) {
                i--;
                removeBall++; 
            }
            while (j < board.length() && board.charAt(j) == board.charAt(left)) {
                j++;
                removeBall++; 
            }
            if (removeBall >= 3) {
                left = i; 
                right = j;
            }
            else break; 
        }
        StringBuilder sb = new StringBuilder(); 
        for (int i = 0; i <= left; i++) {
            sb.append(board.charAt(i));
        }
        for (int j = right; j < board.length(); j++) {
            sb.append(board.charAt(j));
        }
        return sb.toString(); 
    }

}
