package problemaSerie3.structures;

import org.junit.Test;

import static org.junit.Assert.*;

public class DigraphTest {

    /*
    |--------------------------------------------------------------------------
    | Test inserts && deletes
    |--------------------------------------------------------------------------
    */
    @Test
    public void testInsertVertex() throws Exception {
        Digraph<String> g = new Digraph<String>(false);

        assertTrue(g.insertVertex("test", 1));
        assertTrue(g.insertVertex("test",2));
        assertTrue( g.insertVertex("test",-3) );
        assertEquals(3, g.getNVertexes());
        assertFalse( g.insertVertex("test",1) );
        assertEquals(3, g.getNVertexes());
    }

    @Test
    public void testDeleteVertex() throws Exception {
        Digraph<String> g = new Digraph<String>(false);

        assertTrue( g.insertVertex("test",1) );
        assertTrue( g.insertVertex("test",2) );
        assertTrue( g.insertVertex("test",-3) );

        assertFalse( g.deleteVertex(4) );

        assertEquals(3, g.getNVertexes());
        assertTrue( g.deleteVertex(2) );
        assertEquals(2, g.getNVertexes());
    }

    @Test
    public void testInsertEdge() throws Exception {
        Digraph<String> g = new Digraph<String>(true);

        assertFalse(g.insertEdge(1, 2, 3));

        assertTrue( g.insertVertex("test",1) );
        assertTrue( g.insertVertex("test",2) );
        assertTrue( g.insertVertex("test",-3) );

        assertTrue( g.insertEdge(1,-3, 2) );
        assertEquals(1, g.getNEdges());

        Digraph<String> g2 = new Digraph<String>(false);

        g2.insertVertex("test",1);
        g2.insertVertex("test",2);
        assertTrue( g2.insertEdge(1, 2, 2) );
        assertFalse(g2.insertEdge(2, 1, 3));
        assertEquals(1, g2.getNEdges());
    }

    @Test
    public void testDeleteEdge() throws Exception {
        Digraph<String> g = new Digraph<String>(true);

        assertFalse(g.deleteEdge(1, 2));

        assertTrue( g.insertVertex("test",1) );
        assertTrue( g.insertVertex("test",2) );
        assertTrue( g.insertVertex("test",-3) );

        assertTrue( g.insertEdge(1,-3, 2) );
        assertTrue( g.insertEdge(1, 2, 1) );
        assertEquals(2, g.getNEdges());
        assertTrue(g.deleteEdge(1, -3));
        assertEquals(1, g.getNEdges());
    }

    @Test
    public void testIsEmpty() throws Exception {
        Digraph<String> g = new Digraph<String>(false);
        assertTrue(g.isEmpty());
    }

    @Test
    public void testGetNVertexes() throws Exception {
        Digraph<String> g = new Digraph<String>(false);

        g.insertVertex("test",1);
        g.insertVertex("test",2);
        g.insertEdge(1, 2, 1);

        assertEquals(2, g.getNVertexes());
    }

    @Test
    public void testGetNEdges() throws Exception {
        Digraph<String> g = new Digraph<String>(false);

        g.insertVertex("test",1);
        g.insertVertex("test",2);
        g.insertEdge(1, 2, 1);

        assertEquals(1, g.getNEdges());
    }

    @Test
    public void testGetIndDeg() throws Exception {
        Digraph<String> g = new Digraph<String>(false);

        g.insertVertex("test",1);
        g.insertVertex("test",2);
        g.insertEdge(1, 2, 1);

        assertEquals(1, g.getIndDeg(1));

        //digraph test
        Digraph<String> d = new Digraph<String>(true);

        d.insertVertex("test",1);
        d.insertVertex("test",2);
        d.insertEdge(1, 2, 1);

        assertEquals(1, d.getIndDeg(2));
        assertEquals(0, d.getIndDeg(1));
    }

    @Test
    public void testGetOutDeg() throws Exception {
        Digraph<String> g = new Digraph<String>(false);

        g.insertVertex("test",1);
        g.insertVertex("test",2);
        g.insertEdge(1, 2, 1);

        assertEquals(1, g.getOutDeg(1));
        assertEquals(1, g.getOutDeg(2));

        //digraph test
        Digraph<String> d = new Digraph<String>(true);

        d.insertVertex("test",1);
        d.insertVertex("test",2);
        d.insertEdge(1, 2, 1);

        assertEquals(1, d.getOutDeg(1));
        assertEquals(0, d.getOutDeg(2));
    }

    @Test
    public void testGetEdgeCost() throws Exception {
        Digraph<String> g = new Digraph<String>(false);

        g.insertVertex("test",1);
        g.insertVertex("test",2);
        g.insertVertex("test",3);
        g.insertEdge(1, 2, 1);
        g.insertEdge(2, 3, 2);

        assertEquals(1, g.getEdgeCost(1, 2));
        assertEquals(2, g.getEdgeCost(2, 3));
    }

    @Test
    public void testIsSource() throws Exception {
        Digraph<String> g = new Digraph<String>(true);

        g.insertVertex("test",1);
        g.insertVertex("test",2);
        g.insertVertex("test",3);
        g.insertEdge(1, 2, 1);
        g.insertEdge(1, 3, 2);

        assertTrue(g.isSource(1));
    }

    @Test
    public void testIsSink() throws Exception {
        Digraph<String> g = new Digraph<String>(true);

        g.insertVertex("test",1);
        g.insertVertex("test",2);
        g.insertVertex("test",3);
        g.insertEdge(1, 2, 1);
        g.insertEdge(1, 3, 2);

        assertTrue(g.isSink(2));
    }

    @Test
    public void testIsDisconnected() throws Exception {
        Digraph<String> g = new Digraph<String>(true);

        g.insertVertex("test",1);
        g.insertVertex("test",2);
        g.insertVertex("test",3);
        g.insertEdge(1, 2, 1);

        assertTrue(g.isDisconnected(3));
    }

    @Test
    public void testEquals() throws Exception {
        Digraph<String> g = new Digraph<String>(true);

        g.insertVertex("test",1);
        g.insertVertex("test",2);
        g.insertEdge(1, 2, 1);

        Digraph<String> g2 = new Digraph<String>(true);

        g2.insertVertex("test",1);
        g2.insertVertex("test",2);
        g2.insertEdge(1, 2, 1);

        assertTrue(g.equals(g2));

        g2.insertEdge(2, 1, 3);
        assertFalse(g.equals(g2));
    }
}