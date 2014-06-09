package serie3;

import static org.junit.Assert.*;

import org.junit.Test;

import serie3.structures.Tree;
import serie3.structures.Tree.Node;

public class LowerTest {
	
	private Tree<Integer> increasingSequence(int end){
		Tree<Integer> root = new Tree<Integer>(new Node<Integer>(0));
		
		for(int i = 0; i<=end; i++){
			root.insert(i);
		}
		
		return root;
	}
	
	@Test
	public void lower_withEmptyTree(){
		Tree<Integer> t = new Tree<Integer>();
		Integer res = TreeUtils.lower(t.getRoot(), 3);
		
		assertEquals(res, null);
	}
	
	@Test
	public void lower_withoutValidValue(){
		Tree<Integer> t = increasingSequence(5);
		
		Integer res = TreeUtils.lower(t.getRoot(), 0);
		assertEquals(res,null);
	}
	
	@Test
	public void lower_withIncreasingValuesTree(){
		
		Tree<Integer> t = increasingSequence(10);
		
		Integer res1 = TreeUtils.lower(t.getRoot(), 3);
		Integer res2 = TreeUtils.lower(t.getRoot(), 10);
		Integer res3 = TreeUtils.lower(t.getRoot(), 7);
		
		assertTrue(res1==2);
		assertTrue(res2==9);
		assertTrue(res3==6);
		
	}
	

}
