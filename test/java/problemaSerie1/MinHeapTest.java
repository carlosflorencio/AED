package problemaSerie1;

import static org.junit.Assert.*;

import org.junit.Test;

public class MinHeapTest {

	@Test
	public void test_constructor() {
		MinHeap<Integer> heap = new MinHeap<Integer>(10);
		
		assertTrue(heap.isEmpty());
		assertEquals(10, heap.getSize());
	}
	
	@Test
	public void test_insertOneElement() throws Exception {
		MinHeap<Integer> heap = new MinHeap<Integer>(10);
		
		assertTrue( heap.add(2) );
		assertEquals(1, heap.getCount());
		assertEquals(10, heap.getSize());
	}
	
	@Test
	public void test_insertMultipleElements() throws Exception {
		MinHeap<Integer> heap = new MinHeap<Integer>(10);
		
		assertTrue( heap.add(11) );
		assertTrue( heap.add(15) );
		assertTrue( heap.add(9) );
		assertTrue( heap.add(10) );
		
		assertEquals(4, heap.getCount());
		assertEquals(10, heap.getSize());
	}
	
	@Test
	public void test_popElements() throws Exception {
		MinHeap<Integer> heap = new MinHeap<Integer>(10);
		
		assertTrue( heap.add(11) );
		assertTrue( heap.add(15) );
		assertTrue( heap.add(9) );
		assertTrue( heap.add(10) );
		
		assertEquals((Integer)9, heap.poll());
		assertEquals((Integer)10, heap.poll());
		assertEquals((Integer)11, heap.poll());
		assertEquals((Integer)15, heap.poll());
		assertEquals(0, heap.getCount());
	}
	
	@Test
	public void test_peekElement() throws Exception {
		MinHeap<Integer> heap = new MinHeap<Integer>(10);
		
		assertTrue( heap.add(17) );
		assertTrue( heap.add(15) );
		
		assertEquals((Integer)15, heap.peek());
		assertEquals(2, heap.getCount());
	}
	
	@Test
	public void test_insertElementWithFullHeap() {
		MinHeap<Integer> heap = new MinHeap<Integer>(3);
		
		assertTrue( heap.add(5) );
		assertTrue( heap.add(6) );
		assertTrue( heap.add(4) );
		
		assertEquals(3, heap.getCount());
		
		assertTrue( heap.add(7) );
		
		assertEquals((Integer)5, heap.poll());
		assertEquals((Integer)6, heap.poll());
		assertEquals((Integer)7, heap.poll());
		assertTrue( heap.isEmpty() );
		
	}
	
	@Test
	public void test_failedInsertElementWithFullHeap() {
		MinHeap<Integer> heap = new MinHeap<Integer>(3);
		
		assertTrue( heap.add(5) );
		assertTrue( heap.add(6) );
		assertTrue( heap.add(4) );
		
		assertEquals(3, heap.getCount());
		
		assertFalse( heap.add(3) );
		
		assertEquals((Integer)4, heap.poll());
		assertEquals((Integer)5, heap.poll());
		assertEquals((Integer)6, heap.poll());
		assertTrue( heap.isEmpty() );
		
	}
	
	@Test
	public void test_insertMultipleElementsWithFullHeap() throws Exception {
		MinHeap<Integer> heap = new MinHeap<Integer>(3);
		
		assertTrue( heap.add(5) );
		assertTrue( heap.add(6) );
		assertTrue( heap.add(4) );
		
		assertEquals(3, heap.getCount());
		
		assertFalse( heap.add(2) );
		assertFalse( heap.add(1) );
		assertFalse( heap.add(4) );
		
		assertTrue( heap.add(5) );
		assertTrue( heap.add(9) );
		
		assertFalse( heap.add(0) );
		
		assertEquals((Integer)5, heap.poll());
		assertEquals((Integer)6, heap.poll());
		assertEquals((Integer)9, heap.poll());
		assertTrue( heap.isEmpty() );
	}

}
