package serie3;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import serie3.structures.Tree;
import serie3.structures.Tree.Node;

public class CountLeavesAtLevelTest {
	
	private Tree<Integer> increasingSequence(int end){
		Tree<Integer> root = new Tree<Integer>(new Node<Integer>(0));
		
		for(int i = 0; i<=end; i++){
			root.insert(i);
		}
		
		return root;
	}
	
	@Test
	public void testCountLeavesAtLevel_withIncreasingValuesTree(){
		Tree<Integer> t =  increasingSequence(9);
		int res = TreeUtils.countLeavesAtLevel(t.getRoot(), 8);
		int res1 = TreeUtils.countLeavesAtLevel(t.getRoot(), 9);
		
		assertEquals(res, 0);
		assertEquals(res1, 1);
	}
	
	@Test
	public void testCountLeavesAtLevel_withoutValues(){
		Tree<Integer> t = new Tree<Integer>();
		int res = TreeUtils.countLeavesAtLevel(t.getRoot(), 0);
		assertEquals(res, 0);
	}
	
	@Test 
	public void testCountLeavesAtLevel_withOneElement(){
		Tree<Integer> t = new Tree<Integer>(new Node<Integer>(5));
		int res = TreeUtils.countLeavesAtLevel(t.getRoot(), 0);
		assertEquals(res, 1);
	}
	
	@Test
	public void testCountLeavesAtLevel_withSpecificTree(){
		Tree<Integer> t = new Tree<Integer>(new Node<Integer>(8));
		t.insert(3);
		t.insert(1);
		t.insert(10);
		t.insert(6);
		t.insert(4);
		t.insert(7);
		t.insert(14);
		t.insert(13);
		int res1 = TreeUtils.countLeavesAtLevel(t.getRoot(), 3);
		int res2 = TreeUtils.countLeavesAtLevel(t.getRoot(), 2);
		int res3 = TreeUtils.countLeavesAtLevel(t.getRoot(), 1);
		
		assertEquals(res1,3);
		assertEquals(res2,1);
		assertEquals(res3,0);
	}

}
