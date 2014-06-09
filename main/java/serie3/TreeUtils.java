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

}
