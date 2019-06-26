import java.util.*;

public class BinaryTreePostorderTraversal {

	public static void main(String[] args) {
		//Given a binary tree, return the postorder traversal of its nodes' values.
		//Do not use recursion
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.right.right = new TreeNode(4);
		System.out.println(postorderTraversal(root));
	}
	
    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) return res;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            res.add(cur.val);
            if (cur.left != null) stack.push(cur.left);
            if (cur.right != null) stack.push(cur.right);
        }
        Collections.reverse(res);
        return res;
    }
    
    public static class TreeNode{
    	int val; 
    	TreeNode left;
    	TreeNode right;
    	public TreeNode(int value) {
    		this.val = value;
    		left = null;
    		right = null; 
    	}
    }
}
