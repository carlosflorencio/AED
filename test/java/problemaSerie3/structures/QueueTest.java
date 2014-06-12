package problemaSerie3.structures;

import org.junit.Test;

import static org.junit.Assert.*;

public class QueueTest {

    @Test
    public void testEnqueue() throws Exception {
        Queue<Integer> q = new Queue<Integer>();

        assertTrue( q.enqueue(2) );
        assertTrue( q.enqueue(3) );
    }

    @Test
    public void testDequeue() throws Exception {
        Queue<Integer> q = new Queue<Integer>();

        assertTrue( q.enqueue(2) );
        assertTrue( q.enqueue(3) );

        assertEquals((Integer)2, q.dequeue());
        assertEquals((Integer)3, q.dequeue());
        assertTrue( q.isEmpty() );
    }

    @Test
    public void testIsEmpty() throws Exception {
        Queue<Integer> q = new Queue<Integer>();

        assertTrue(q.isEmpty());
    }

    @Test
    public void testSize() throws Exception {
        Queue<Integer> q = new Queue<Integer>();

        assertTrue( q.enqueue(2) );
        assertTrue( q.enqueue(3) );

        assertEquals(2, q.getSize());
    }

    @Test
    public void testContains() throws Exception {
        Queue<Integer> q = new Queue<Integer>();

        assertTrue( q.enqueue(2) );
        assertTrue( q.enqueue(3) );

        assertTrue( q.contains(2) );
    }
}