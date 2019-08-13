
public class AddAndSearchWordDataStructureDesign {

	public static void main(String[] args) {
		//Design a data structure that supports the following two operations:
		//void addWord(word)
		//bool search(word)
		//search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.

	}


    /** Initialize your data structure here. */
    TrieNode root;
    public AddAndSearchWordDataStructureDesign() {
        root = new TrieNode('/');
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        if (word == null) return;
        TrieNode cur = root; 
        for (char ch : word.toCharArray()) {
            if (cur.nexts[ch] == null) {
                cur.nexts[ch] = new TrieNode(ch);
            }
            cur = cur.nexts[ch];
        }
        cur.isWord = true; 
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return dfsHelper(word, 0, root);
    }
    private boolean dfsHelper(String word, int index, TrieNode cur) {
        if (index == word.length()) return cur.isWord; 
        char ch = word.charAt(index);
        if (ch != '.') {
            if (cur.nexts[ch] != null) {
                if (dfsHelper(word, index + 1, cur.nexts[ch])) return true; 
            }
        }
        else {
            for (TrieNode next : cur.nexts) {
                if (next != null) {
                    if (dfsHelper(word, index + 1, next)) return true;
                }
            }
        }
        return false; 
    }
    
    private class TrieNode {
        char val;
        boolean isWord; 
        TrieNode[] nexts;
        TrieNode(char val) {
            this.val = val;
            isWord = false;;
            nexts = new TrieNode[256]; 
        }
    }
}
