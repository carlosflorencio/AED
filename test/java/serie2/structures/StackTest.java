package serie2.structures;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StackTest {
	@Before
	  public void setUp() throws Exception {

	  }

	  @Test
	  public void testStackArray() {
	    Stack<Integer> stack = new Stack<Integer>();
	    stack.push(1);
	    stack.push(2);
	    stack.push(3);
	    stack.push(3);
	    stack.push(4);
	    assertTrue(4 == stack.pop());
	    assertTrue(3 == stack.pop());
	    assertTrue(3 == stack.pop());
	    assertTrue(2 == stack.pop());
	    assertTrue(1 == stack.pop());
	  }

	  @Test
	  public void testStackList() {
	    Stack<Integer> stack = new Stack<Integer>();
	    stack.push(1);
	    stack.push(2);
	    stack.push(3);
	    stack.push(3);
	    stack.push(4);
	    assertTrue(4 == stack.pop());
	    assertTrue(3 == stack.pop());
	    assertTrue(3 == stack.pop());
	    assertTrue(2 == stack.pop());
	    assertTrue(1 == stack.pop());
	  }

}
