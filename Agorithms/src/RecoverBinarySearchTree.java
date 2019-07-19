

public class RecoverBinarySearchTree {
	class TreeNode {
		int val; 
		TreeNode left;
		TreeNode right;
		public TreeNode(int val) {
			this.val = val;
			left = null;
			right = null;
		}
	}
	
	public static void main(String[] args) {
		//Two elements of a binary search tree (BST) are swapped by mistake.
		//Recover the tree without changing its structure.
	}
    public static void recoverTree(TreeNode root) {
        if (root == null) return;
        TreeNode[] pair = new TreeNode[2];
        helper(root, new TreeNode[1], pair);
        int temp = pair[0].val; 
        if (pair[1] != null) {
            pair[0].val = pair[1].val;
            pair[1].val = temp;
        }
    }
    private static void helper(TreeNode root, TreeNode[] pre, TreeNode[] pair) {
        if (root == null) return;
        helper(root.left, pre, pair);
        if (pre[0] != null && pre[0].val >= root.val) {
            if (pair[0] == null) pair[0] = pre[0];
            pair[1] = root; 
        }
        pre[0] = root;
        helper(root.right, pre, pair);
    }
}
