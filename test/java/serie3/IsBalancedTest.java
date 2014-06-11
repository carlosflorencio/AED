package serie3;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import serie3.structures.Tree;
import serie3.structures.Tree.Node;

public class IsBalancedTest {
	private Tree<Integer> increasingSequence(int end){
		Tree<Integer> root = new Tree<Integer>(new Node<Integer>(0));
		
		for(int i = 0; i<=end; i++){
			root.insert(i);
		}
		
		return root;
	}
	
	@Test
	public void isBalanced_withIncreasingValuesTree(){
		Tree<Integer> t =  increasingSequence(9);
		boolean res = TreeUtils.isBalanced(t.getRoot());
		
		assertFalse(res);
	}
	
	@Test
	public void isBalanced_withSpecificTree(){
		Tree<Integer> t = new Tree<Integer>(new Node<Integer>(8));
		t.insert(3);
		boolean res1 = TreeUtils.isBalanced(t.getRoot());
		t.insert(1);
		boolean res2 = TreeUtils.isBalanced(t.getRoot());
		t.insert(10);
		boolean res3 = TreeUtils.isBalanced(t.getRoot());
		t.insert(6);
		boolean res4 = TreeUtils.isBalanced(t.getRoot());
		t.insert(4);
		t.insert(7);
		boolean res5 = TreeUtils.isBalanced(t.getRoot());
		t.insert(14);
		t.insert(13);
		boolean res = TreeUtils.isBalanced(t.getRoot());
		
		assertTrue(res1);
		assertFalse(res2);
		assertTrue(res3);
		assertTrue(res4);
		assertFalse(res5);
		assertTrue(res);
	}
	
	@Test
	public void isBalanced_withEmptyTree(){
		Tree<Integer> t = new Tree<Integer>();
		boolean res = TreeUtils.isBalanced(t.getRoot());
		
		assertTrue(res);
	}
	
	@Test
	public void isBalanced_withRootOnly(){
		Tree<Integer> t = new Tree<Integer>(new Node<Integer>(5));
		boolean res = TreeUtils.isBalanced(t.getRoot());
		
		assertTrue(res);
	}

}
