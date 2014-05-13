package serie2;

import static serie2.Iterables.getSortedSubsequence;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class GetSortedSubsequenceTest  extends IterablesTest {
		static final Iterable<Integer> SEmpty = Collections.<Integer>emptyList();
		static final Iterable<Integer> SSingle = Collections.singletonList(0);
		static final List<Integer> SIncreasing= unmodifiable( Arrays.asList( 3, 5, 7, 10, 12 ) );
		static final List<Integer> SDecreasing= unmodifiable( Arrays.asList(12, 10, 7, 5, 3 ) );
		static final List<Integer> SEquals= unmodifiable( Arrays.asList( 3, 3, 3, 3, 3 ) );
		static final List<Integer> SSome= unmodifiable( Arrays.asList( 3, 5, 2, 10, 12 ) );
		
	

		
		@Test 
		public void getSortedSubsequence_withEmptySequences(){
			assertIterableEmpty( getSortedSubsequence( SEmpty ) );	
		}

		@Test 
		public void getSortedSubsequence_withOneElementSequence(){
			Iterable<Integer> result=getSortedSubsequence(SSingle);
			assertTrue(result.iterator().hasNext());
			assertIterableEquals(SSingle, result);
			Iterator<Integer> it=result.iterator();
			assertTrue(it.hasNext());
			assertTrue(it.hasNext());
			
		}
		
		@Test 
		public void getSortedSubsequence_withIncreasingSequence(){
			List<Integer> subList=null;
			for(int i=0; i<SIncreasing.size();i++){
				subList=SIncreasing.subList(i, SIncreasing.size());
				assertIterableEquals(subList, getSortedSubsequence(subList));
			}
		}
		
		@Test 
		public void getSortedSubsequence_withDecreasingSequence(){
			List<Integer> subList=null;
			for(int i=0; i<SDecreasing.size();i++){
				subList=SDecreasing.subList(i, SDecreasing.size());
				assertIterableEquals(Collections.singletonList(subList.get(0)), getSortedSubsequence(subList));
			}
		}

		@Test 
		public void getSortedSubsequence_withEqualsSequence(){
			List<Integer> subList=null;
			for(int i=0; i<SEquals.size();i++){
				subList=SEquals.subList(i, SEquals.size());
				assertIterableEquals(subList, getSortedSubsequence(subList));
			}
		}
		
		
		public void getSortedSequence_withSomeIncreasingSequence(){
             List<Integer> increasingSubsequence= unmodifiable( Arrays.asList( 3, 5,10,12) );
             assertIterableEquals(increasingSubsequence, getSortedSubsequence(SSome));
		}
		
		@Test(expected=NoSuchElementException.class)
		public void getSortedSequence_nextInEmptySequence() {
			getSortedSubsequence(SEmpty).iterator().next();
		}
		
		@Rule
		public ExpectedException exception = ExpectedException.none();
		
		@Test 
		public void getValuesBetween_NextWithNoElements(){
			Iterator<Integer> it=  getSortedSubsequence(SSingle).iterator();	
			assertEquals(0, it.next().intValue());
			exception.expect(NoSuchElementException.class);
			exception.expectMessage("getSortedSubsequence:Iterator - no such element");
			it.next();
		}

		@Test 
		public void getValuesBetween_RemoveElements(){
			Iterator<Integer> it= getSortedSubsequence(SSingle).iterator();
			it.next();
			exception.expect(UnsupportedOperationException.class);
			exception.expectMessage("getSortedSubsequence:Iterator - remove not supported");
			it.remove();
		}

	
}
