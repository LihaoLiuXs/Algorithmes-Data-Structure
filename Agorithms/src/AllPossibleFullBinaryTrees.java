import java.util.*;

public class AllPossibleFullBinaryTrees {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
    public List<TreeNode> allPossibleFBT(int N) {
        return allPossibleFBT(N, new HashMap<Integer, List<TreeNode>>());
    }
    private List<TreeNode> allPossibleFBT(int N, HashMap<Integer, List<TreeNode>> cache) {
        List<TreeNode> res = new ArrayList<>();
        if (N == 1) {
            res.add(new TreeNode(0));
            return res;
        }
        if (cache.containsKey(N)) return cache.get(N);
        for (int i = 1; i < N - 1; i++) {
            List<TreeNode> leftList = allPossibleFBT(i, cache);
            List<TreeNode> rightList = allPossibleFBT(N - i - 1, cache);
            for (TreeNode l : leftList) {
                for (TreeNode r : rightList) {
                    if ((l == null && r == null) || (l != null && r != null)) {  
                        TreeNode root = new TreeNode(0);
                        root.left = l;
                        root.right = r; 
                        res.add(root);
                    }
                }
            }
        }
        cache.put(N, res);
        return res; 
    }
    public class TreeNode {
    	 int val;
    	 TreeNode left;
    	 TreeNode right;
    	 TreeNode(int x) { val = x; }
    }
}
