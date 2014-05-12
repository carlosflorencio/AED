package serie1;

import org.junit.Assert;
import org.junit.Test;

import static serie1.Arrays.getMaximumIncreasingSubsequence;

public class GetMaximumIncreasingSubsequenceTest {
	private static final int[] original={0, 1, 2, 3, 2, 5, 6, 3, 8, 10, 12 };
	
	@Test
	public void test_getMaximumIncreasingSubsequence_with_empty_subsequences(){
		int[] emptySequence={};
		int[] res=getMaximumIncreasingSubsequence(emptySequence, 0, emptySequence.length-1);
		Assert.assertTrue(res[0] > res[1]);	
		res=getMaximumIncreasingSubsequence(original, original.length>>1, (original.length>>1) -1);
		Assert.assertTrue(res[0] > res[1]);	
		res=getMaximumIncreasingSubsequence(original, original.length, original.length-1);
		Assert.assertTrue(res[0] > res[1]);	
	}
	
	@Test
	public void test_getMaximumIncreasingSubsequence_at_the_begin(){
		int[] res=getMaximumIncreasingSubsequence(original, 0, original.length-1);
		Assert.assertArrayEquals(new int[]{0,3}, res);		
		res=getMaximumIncreasingSubsequence(original, 1, original.length-2);
		Assert.assertArrayEquals(new int[]{1,3}, res);		
	}
	
	@Test
	public void test_getMaximumIncreasingSubsequence_at_the_end(){
		int[] res=getMaximumIncreasingSubsequence(original, 1, original.length-1);
		Assert.assertArrayEquals(new int[]{7,10}, res);		
	}
	
	@Test
	public void test_getMaximumIncreasingSubsequence_at_the_middle(){
		int[] res=getMaximumIncreasingSubsequence(original, 2, original.length-3);
		Assert.assertArrayEquals(new int[]{4,6}, res);		
	}

	@Test
	public void test_getMaximumIncreasingSubsequence_increasing_sequence(){
		int[][] arrayTest = { {1}, {1,2}, {1,2,3}, {1,2,3,4,5,6} };
		int[] res;
		for (int i = 0; i < arrayTest.length; ++i) {
			res=getMaximumIncreasingSubsequence(arrayTest[i], 0, arrayTest[i].length-1);
			Assert.assertEquals(0, res[0]);		
			Assert.assertEquals(arrayTest[i].length-1, res[1]);
		}
	}
	
	@Test
	public void test_getMaximumIncreasingSubsequence_decreasing_sequence(){
		int[][] arrayTest = { {6,5,4,3,2,1}, {1, 5, 4, 3, 4} };
		int[] res;
		for(int i=0; i < arrayTest.length; ++i ) {
			res=getMaximumIncreasingSubsequence(arrayTest[i], i, arrayTest[i].length-1-i);
			Assert.assertEquals(i, res[0]);		
			Assert.assertEquals(i, res[1]);
		}
	}

}
