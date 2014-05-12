package problemaSerie1;

import static org.junit.Assert.*;

import org.junit.Test;

public class WordTest {

	@Test
	public void test_constructor() {
		Word w = new Word("ola", 2);
		
		assertEquals(2, w.getCount());
		assertEquals("ola", w.getWord());
	}
	
	@Test
	public void test_compareTo_smaller() throws Exception {
		Word w = new Word("ola", 2);
		Word w1 = new Word("asd", 1);
		
		assertEquals(1, w.compareTo(w1));
	}
	
	@Test
	public void test_compareTo_greater() throws Exception {
		Word w = new Word("ola", 2);
		Word w1 = new Word("asd", 4);
		
		assertEquals(-1, w.compareTo(w1));
	}
	
	@Test
	public void test_compareTo_equal() throws Exception {
		Word w = new Word("ola", 2);
		Word w1 = new Word("asd", 2);
		
		assertEquals(0, w.compareTo(w1));
	}

}
