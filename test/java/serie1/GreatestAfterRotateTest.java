package serie1;



import static org.junit.Assert.*;
import static serie1.Arrays.greatestAfterRotate;

import org.junit.Test;

public class GreatestAfterRotateTest {
	
	@Test
	public void test_getMaximumIncreasingSubsequence_with_one_shifted_positions(){
		int[] array = { 55, 7, 22, 35, 42, 47 };
		assertEquals(55,greatestAfterRotate(array, 0, 5));
		
	}
	
	@Test
	public void test_getMaximumIncreasingSubsequence_with_also_one_shifted_positions(){
		int[] array = { 55, 0, 1, 2, 3, 7, 22, 35, 42, 47, 48, 50, 53};
		assertEquals(55,greatestAfterRotate(array, 0, 12));
		
	}
	
	@Test
	public void test_getMaximumIncreasingSubsequence_with_two_shifted_positions(){
		int[] array = { 35, 42, 5, 15, 27, 29 };
		assertEquals(42,greatestAfterRotate(array, 0, 5));
		
	}
	
	@Test
	public void test_getMaximumIncreasingSubsequence_with_four_shifted_positions(){
		int[] array = { 27, 29, 35, 42, 5, 15 };
		assertEquals(42,greatestAfterRotate(array, 0, 5));
		
	}
	
	@Test
	public void test_getMaximumIncreasingSubsequence_with_also_four_shifted_positions(){
		int[] array = { 55, 57, 59, 60, 1, 7, 22, 35, 42, 47, 48, 50,53};
		assertEquals(60,greatestAfterRotate(array, 0, 12));
		
	}
	

	
	


}
