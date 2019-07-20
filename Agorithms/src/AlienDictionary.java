import java.util.*;

public class AlienDictionary {

	public static void main(String[] args) {
		//There is a new alien language which uses the latin alphabet. However, 
		//the order among letters are unknown to you. 
		//You receive a list of non-empty words from the dictionary, 
		//where words are sorted lexicographically by the rules of this new language. 
		//Derive the order of letters in this language.
		String[] input = {"wrt", "wrf", "er", "ett", "rftt"};
		System.out.println(alienOrder(input)); //"wertf"
		String[] input2 = {"z", "x", "z"}; 
		System.out.println(alienOrder(input2)); //""
		String[] input3 = {"z", "x"};
		System.out.println(alienOrder(input3)); //"zx"
	}

    private enum Status {
        INITIAL, VISITING, VISITED; 
    }
    private static class Vet {
        char val;
        List<Vet> nexts;
        Status status; 
        public Vet(char val) {
            this.val = val;
            nexts = new ArrayList<Vet>();
            status = Status.INITIAL; 
        }
    }
    public static String alienOrder(String[] words) {
        if (words == null || words.length == 0) return "";
        if (words.length == 1) return words[0];
        List<Vet> vs = new ArrayList<>();
        Vet[] graph = new Vet[26];
        if (!buildGraph(words, vs, graph)) return "";
        int[] index = new int[1];
        index[0] = vs.size() - 1;
        char[] res = new char[vs.size()];
        for (Vet v : vs) {
            if(!dfs(v, index, res)) return "";
        }
        return new String(res); 
    }
    private static boolean dfs(Vet v, int[] index, char[] res) {
        if (v.status == Status.VISITED) return true;
        if (v.status == Status.VISITING) return false;
        v.status = Status.VISITING;
        for (Vet next : v.nexts) {
            if (!dfs(next, index, res)) return false;
        }
        v.status = Status.VISITED; 
        res[index[0]--] = v.val; 
        return true; 
    }
        
        
    private static boolean buildGraph(String[] words, List<Vet> vs, Vet[] graph) {
        String pre = words[0];
        for (int i = 1; i < words.length; i++) { 
            boolean valid = false; 
            String cur = words[i];
            int pIndex = 0, cIndex = 0;
            int pLen = pre.length(), cLen = cur.length();
            while (pIndex < pLen && cIndex < cLen) {
                char pChar = pre.charAt(pIndex++), cChar = cur.charAt(cIndex++); 
                if (graph[pChar - 'a'] == null) {
                    graph[pChar - 'a'] = new Vet(pChar);
                    vs.add(graph[pChar - 'a']);
                }
                if (graph[cChar - 'a'] == null) {
                    graph[cChar - 'a'] = new Vet(cChar);
                    vs.add(graph[cChar - 'a']);
                }
                if (cChar != pChar) {
                    graph[pChar - 'a'].nexts.add(graph[cChar - 'a']); 
                    valid = true; 
                    break; 
                }
            }
            while (pIndex < pLen) {
                if (!valid) return false;
                char pChar = pre.charAt(pIndex++);
                if (graph[pChar - 'a'] == null) {
                    graph[pChar - 'a'] = new Vet(pChar);
                    vs.add(graph[pChar - 'a']);
                }
            }
            while (cIndex < cLen) {
                char cChar = cur.charAt(cIndex++);
                if (graph[cChar - 'a'] == null) {
                    graph[cChar - 'a'] = new Vet(cChar);
                    vs.add(graph[cChar - 'a']);
                }
            }
            pre = cur; 
        }
        return true; 
    }
}
