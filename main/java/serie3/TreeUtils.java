package serie3;

import serie3.structures.Tree.Node;

public class TreeUtils {
	
	public static Integer lower(Node<Integer> root, int k){
		if(root == null || root.value >= k)
			return null;
		while(root.right.value < k){
			root=root.right;
		}
		return root.value;
	}
	
	public static int countLeavesAtLevel(Node<Integer> root, int k){
		int count = 0;
		if(root==null) return count;
		if(k==0 && root.left==null && root.right==null)
			return count+1;
		k--;
		count+=countLeavesAtLevel(root.left, k);
		count+=countLeavesAtLevel(root.right,k);
		return count;
	}
	
	public static <E> boolean isBalanced(Node<E> root){
		if(root==null)
	        return true;  //tree is empty
		
		int lh = height(root.left);
	    int rh =height( root.right);
	    
	    return abs(rh-lh)<2;
	}
	
	private static <E> int height(Node<E> root) {
		if (root == null)
			return -1;
		int l = height(root.left);
		int r = height(root.right);
		return (l > r) ? l + 1 : r + 1;
	}
	
	private static int abs(int a) {
	    return (a <= 0) ? 0 - a : a;
	}

}
