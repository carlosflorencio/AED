package problemaSerie3.structures;

import org.junit.Test;

import static org.junit.Assert.*;

public class GraphTest {

    /*
    |--------------------------------------------------------------------------
    | Insert Vertexes
    |--------------------------------------------------------------------------
    */
    @Test
    public void testInsertVertex() throws Exception {
        Graph<String> g = new Graph<>(2, false);

        assertTrue(g.insertVertex("um", 0));
        assertTrue(g.insertVertex("dois", 1));

        assertEquals(2, g.getNVertexes());
    }

    @Test
    public void testInsertDuplicateVertex() throws Exception {
        Graph<String> g = new Graph<>(2, false);

        assertTrue(g.insertVertex("um", 0));
        assertFalse(g.insertVertex("dois", 0));

        assertEquals(1, g.getNVertexes());
    }

    @Test
    public void testInsertVertexAboveCapacity() throws Exception {
        Graph<String> g = new Graph<>(1, false);

        assertTrue(g.insertVertex("um", 0));
        try {
            g.insertVertex("dois", 1);
            fail();
        } catch (IndexOutOfBoundsException e) {

        }

        assertEquals(1, g.getNVertexes());
    }

    @Test
    public void testInsertVertexNegativeIndex() throws Exception {
        Graph<String> g = new Graph<>(1, false);

        try {
            g.insertVertex("dois", -1);
            fail();
        } catch (IndexOutOfBoundsException e) {

        }

        assertEquals(0, g.getNVertexes());
    }

    /*
    |--------------------------------------------------------------------------
    | Insert Edges
    |--------------------------------------------------------------------------
    */
    @Test
    public void testInsertEdge() throws Exception {
        Graph<String> g = new Graph<>(5, false);

        assertTrue(g.insertVertex("1", 0));
        assertTrue(g.insertVertex("2", 1));
        assertTrue(g.insertVertex("3", 2));
        assertTrue(g.insertVertex("4", 3));
        assertTrue(g.insertVertex("5", 4));

        assertTrue(g.insertEdge(0, 1, 1));
        assertTrue(g.insertEdge(0, 3, 1));
        assertTrue(g.insertEdge(3, 4, 1));
        assertTrue(g.insertEdge(4, 2, 1));
        assertTrue(g.insertEdge(2, 3, 1));
        assertTrue(g.insertEdge(1, 4, 1));
        assertTrue(g.insertEdge(1, 2, 1));

        assertTrue(1 == g.getEdgeCost(0, 1)); //no directional sould have both directions costs
        assertTrue(1 == g.getEdgeCost(1, 0));

        assertTrue(1 == g.getEdgeCost(0, 3)); //no directional sould have both directions costs
        assertTrue(1 == g.getEdgeCost(3, 0));

        assertTrue(1 == g.getEdgeCost(3, 4)); //no directional sould have both directions costs
        assertTrue(1 == g.getEdgeCost(4, 3));

        assertTrue(1 == g.getEdgeCost(4, 2)); //no directional sould have both directions costs
        assertTrue(1 == g.getEdgeCost(2, 4));

        assertTrue(1 == g.getEdgeCost(2, 3)); //no directional sould have both directions costs
        assertTrue(1 == g.getEdgeCost(3, 2));

        assertTrue(1 == g.getEdgeCost(1, 4)); //no directional sould have both directions costs
        assertTrue(1 == g.getEdgeCost(4, 1));

        assertTrue(1 == g.getEdgeCost(1, 2)); //no directional sould have both directions costs
        assertTrue(1 == g.getEdgeCost(2, 1));

        assertEquals(7, g.getNEdges());
    }

    @Test
    public void testInsertDuplicateEdge() throws Exception {
        Graph<String> g = new Graph<>(2, false);

        assertTrue(g.insertVertex("um", 0));
        assertTrue(g.insertVertex("dois", 1));

        assertTrue(g.insertEdge(0, 1, 1));
        assertFalse(g.insertEdge(0, 1, 2));
        assertFalse(g.insertEdge(1, 0, 2));

        assertTrue(1 == g.getEdgeCost(0, 1)); //no directional sould have both directions costs
        assertTrue(1 == g.getEdgeCost(1, 0));

        assertEquals(1, g.getNEdges());
    }

    @Test
    public void testInsertEdgeDirectional() throws Exception {
        Graph<String> g = new Graph<>(2, true);

        assertTrue(g.insertVertex("um", 0));
        assertTrue(g.insertVertex("dois", 1));

        assertTrue(g.insertEdge(0, 1, 1));

        assertTrue(1 == g.getEdgeCost(0, 1)); //no directional sould have both directions costs
        assertTrue(-1 == g.getEdgeCost(1, 0));

        assertEquals(1, g.getNEdges());
    }

    @Test
    public void testInsertDuplicateEdgeDirectional() throws Exception {
        Graph<String> g = new Graph<>(2, true);

        assertTrue(g.insertVertex("um", 0));
        assertTrue(g.insertVertex("dois", 1));

        assertTrue(g.insertEdge(0, 1, 1));
        assertFalse(g.insertEdge(0, 1, 2));
        assertTrue(g.insertEdge(1, 0, 2));

        assertTrue(1 == g.getEdgeCost(0, 1)); //no directional sould have both directions costs
        assertTrue(2 == g.getEdgeCost(1, 0));

        assertEquals(2, g.getNEdges());
    }

    /*
    |--------------------------------------------------------------------------
    | Test getters
    |--------------------------------------------------------------------------
    */
    @Test
    public void testIsEmpty() throws Exception {
        Graph<String> g = new Graph<>(2, false);

        assertTrue(g.insertVertex("um", 0));
        assertTrue(g.insertVertex("dois", 1));
        assertTrue(g.insertEdge(0, 1, 1));

        assertFalse(g.isEmpty());
    }

    @Test
    public void testGetNVertexes() throws Exception {
        Graph<String> g = new Graph<>(5, false);

        assertTrue(g.insertVertex("um", 0));
        assertTrue(g.insertVertex("dois", 1));

        assertEquals(2, g.getNVertexes());
    }

    @Test
    public void testGetNEdges() throws Exception {
        Graph<String> g = new Graph<>(5, false);

        assertTrue(g.insertVertex("um", 0));
        assertTrue(g.insertVertex("dois", 1));
        assertTrue(g.insertEdge(0, 1, 1));

        assertEquals(1, g.getNEdges());
    }

    @Test
    public void testGetIndDeg() throws Exception {
        Graph<String> g = new Graph<>(5, false);

        assertTrue(g.insertVertex("um", 0));
        assertTrue(g.insertVertex("dois", 1));
        assertTrue(g.insertEdge(0, 1, 1));

        assertEquals(1, g.getIndDeg(0));
    }

    @Test
    public void testGetOutDeg() throws Exception {
        Graph<String> g = new Graph<>(5, false);

        assertTrue(g.insertVertex("um", 0));
        assertTrue(g.insertVertex("dois", 1));
        assertTrue(g.insertEdge(0, 1, 1));

        assertEquals(1, g.getOutDeg(1));
    }

    @Test
    public void testGetEdgeCost() throws Exception {
        Graph<String> g = new Graph<>(5, false);

        assertTrue(g.insertVertex("um", 0));
        assertTrue(g.insertVertex("dois", 1));
        assertTrue(g.insertEdge(0, 1, 2.6));

        assertTrue(2.6 == g.getEdgeCost(0, 1));
        assertTrue(2.6 == g.getEdgeCost(1, 0));
    }

    @Test
    public void testGetEdgeCostDirectional() throws Exception {
        Graph<String> g = new Graph<>(5, true);

        assertTrue(g.insertVertex("um", 0));
        assertTrue(g.insertVertex("dois", 1));
        assertTrue(g.insertEdge(0, 1, 2.6));

        assertTrue(2.6 == g.getEdgeCost(0, 1));
        assertTrue(-1 == g.getEdgeCost(1, 0));
    }

    @Test
    public void testIsSource() throws Exception {
        Graph<String> g = new Graph<>(5, true);

        assertTrue(g.insertVertex("um", 0));
        assertTrue(g.insertVertex("dois", 1));
        assertTrue(g.insertEdge(0, 1, 2.6));

        assertTrue(g.isSource(0));
    }

    @Test
    public void testIsSink() throws Exception {
        Graph<String> g = new Graph<>(5, true);

        assertTrue(g.insertVertex("um", 0));
        assertTrue(g.insertVertex("dois", 1));
        assertTrue(g.insertEdge(0, 1, 2.6));

        assertTrue(g.isSink(1));
    }

    @Test
    public void testIsDisconnected() throws Exception {
        Graph<String> g = new Graph<>(3, true);

        assertTrue(g.insertVertex("um", 0));
        assertTrue(g.insertVertex("dois", 1));
        assertTrue(g.insertVertex("tres", 2));

        assertTrue(g.insertEdge(0, 1, 2.6));

        assertTrue(g.isDisconnected(2));
    }

    @Test
    public void testIsDigraph() throws Exception {
        Graph<String> g = new Graph<>(3, true);

        assertTrue(g.isDigraph());
    }
}