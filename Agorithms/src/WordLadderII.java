import java.util.*;

public class WordLadderII {

	public static void main(String[] args) {
		//iven two words (beginWord and endWord), and a dictionary's word list, 
		//find all shortest transformation sequence(s) from beginWord to endWord, such that:
		//1.Only one letter can be changed at a time
		//2.Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
		//note: 
		//Return an empty list if there is no such transformation sequence.
		//All words have the same length.
		//All words contain only lowercase alphabetic characters.
		//You may assume no duplicates in the word list.
		//You may assume beginWord and endWord are non-empty and are not the same.
		List<String> wordList = Arrays.asList("hot","dot","dog","lot","log","cog"); 
		System.out.println(findLadders("hit", "cog", wordList));
	}
    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<List<String>>();
        if (wordList == null || wordList.size() == 0) return res;
        Queue<String> queue = new LinkedList<>();
        HashMap<String, Set<String>> map = new HashMap<>();
        HashSet<String> set = new HashSet<>();
        for (String word : wordList) set.add(word);
        queue.offer(beginWord);
        boolean stop = false; 
        while (!queue.isEmpty() && !stop) {
            int size = queue.size();
            List<String> visited = new ArrayList<>(); 
            for (int i = 0; i < size; i++) {
                String cur = queue.poll(); 
                List<String> nexts = getNexts(cur, set);
                for (String next : nexts) {
                    queue.offer(next);
                    visited.add(next);
                    if (!map.containsKey(next)) {
                        map.put(next, new HashSet<String>());
                    }
                    map.get(next).add(cur);    
                    if (next.equals(endWord)) stop = true;               
                }
            }
            for (String visit : visited) {
                set.remove(visit);
            }
        }
        backTracking(map, endWord, beginWord, res, new LinkedList<String>());
        return res; 
    }
    private static List<String> getNexts(String cur, HashSet<String> set) {
        List<String> list = new ArrayList<>();
        if (set == null || set.size() == 0) return list;
        char[] cc = cur.toCharArray();
        for (int i = 0; i < cc.length; i++) {
            char tmp = cc[i];
            for (char c = 'a'; c <= 'z'; c++) {
                cc[i] = c;
                String str = String.valueOf(cc); 
                if (set.contains(str)) list.add(str);
            }
            cc[i] = tmp; 
        }
        return list;
    }
    private static void backTracking(HashMap<String, Set<String>> map, String cur, String target, List<List<String>> res, List<String> subRes) {
        if (cur.equals(target)) {
            subRes.add(0, target);
            res.add(new ArrayList<String>(subRes));
            subRes.remove(0); 
            return; 
        }
        if (!map.containsKey(cur)) return; 
        subRes.add(0, cur);
        for (String s : map.get(cur)) {
            backTracking(map, s, target, res, subRes);
        }
        subRes.remove(0);
    }
}
