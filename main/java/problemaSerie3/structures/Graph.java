package problemaSerie3.structures;

public class Graph<D> {

    /*
    |--------------------------------------------------------------------------
    | Private internal classes
    |--------------------------------------------------------------------------
    */
    private static class Vertex<D> {
        public int inDeg, outDeg; //semi-degrees of incidence and emerging
        public D data;
        public Edge adj;
        public boolean visited;
        public int number;

        public Vertex(int number, D data) {
            inDeg = outDeg = 0;
            this.number = number;
            this.data = data;
        }

        private Edge getEdgeToVertex(int number) {
            Edge tmp = this.adj;
            for (; tmp != null; tmp = tmp.next)
                if (tmp.toVertex == number) break;
            return tmp;
        }

        private Edge inList(int pNumber) { //calc the edge to insert after
            Edge tmp = adj, prev = null;
            for (; tmp != null; tmp = tmp.next) {
                if (tmp.toVertex >= pNumber) break;
                prev = tmp;
            }
            if (tmp == null || tmp.toVertex > pNumber) return prev;
            else return null;
        }

    }

    private static class Edge {
        public Edge prev;
        public Edge next;
        public int toVertex;
        public double cost;

        public Edge(double pCost, int toVertex) {
            cost = pCost;
            this.toVertex = toVertex;
        }
    }

    /*
    |--------------------------------------------------------------------------
    | Real class stuff
    |--------------------------------------------------------------------------
    */
    private Vertex<D>[] vertexes;
    private int nVertexes;
    private int edges;
    private boolean digraph;

    public Graph(int nVertexes, boolean directionalEdges) {
        vertexes = new Vertex[nVertexes];
        digraph = directionalEdges;
    }


    /*
    |--------------------------------------------------------------------------
    | Closeness
    |--------------------------------------------------------------------------
    */
    public double closeness(int vertex) {
        if (vertex < 0 || vertex >= vertexes.length) throw new IndexOutOfBoundsException();

        Queue<Vertex<D>> queue = new Queue<Vertex<D>>();

        Vertex<D> v = vertexes[vertex];
        Edge e;
        int[] reach = new int[vertexes.length];

        if(nVertexes == 0 || edges == 0) return -1;

        markVertexesUnvisited();

        queue.enqueue(v);
        while (!queue.isEmpty()) {
            v = queue.dequeue();
            for(e = v.adj; e != null; e = e.next) {
                if(e.toVertex != vertex && !vertexes[e.toVertex].visited) { //vertex to visit that is not the start one
                    vertexes[e.toVertex].visited = true;
                    reach[e.toVertex] = reach[v.number] + 1; //sum distance, -1 to the index because array start's at 0
                    queue.enqueue(vertexes[e.toVertex]);
                }
            }
        }

        int res = 0;
        int connectedVertexes = 0;

        for (Vertex<D> ve : vertexes) {
            if(reach[ve.number] != 0) {
                connectedVertexes++;
                res += reach[ve.number];
            }
        }

        return res / (double)connectedVertexes;
    }

    /*
    |--------------------------------------------------------------------------
    | Transversal (later customization)
    |--------------------------------------------------------------------------
    */
    public String breadthFirst() {
        String str = "BFS: ";

        if (vertexes.length == 0 || edges == 0) return "";

        markVertexesUnvisited();

        for (int i = 0; i < vertexes.length; i++) {
            if(!vertexes[i].visited) str = breathFirstSearch(vertexes[i], str);
        }

        return str + "\n";
    }

    private String breathFirstSearch(Vertex<D> pVert, String str) {
        Queue<Vertex<D>> queue = new Queue<Vertex<D>>();

        pVert.visited = true;
        queue.enqueue(pVert);

        while (!queue.isEmpty()) {
            Vertex<D> v = queue.dequeue();
            str += " " + v.data;
            for (Edge e = v.adj; e != null; e = e.next) {
                if ( !vertexes[e.toVertex].visited) {
                    vertexes[e.toVertex].visited = true;
                    queue.enqueue(vertexes[e.toVertex]);
                }
            }
        }
        return str;
    }

    /*
    |--------------------------------------------------------------------------
    | Inserting and Deletings Vertexes and Edges
    |--------------------------------------------------------------------------
    */
    public boolean insertVertex(D data, int pVNumber) {
        if (pVNumber < 0 || pVNumber >= vertexes.length) throw new IndexOutOfBoundsException();

        if(!hasVertex(pVNumber)) {
            vertexes[pVNumber] = new Vertex<>(pVNumber, data);
            nVertexes++;
            return true;
        }

        return false;
    }


    public boolean insertEdge(int pVNumber1, int pVNumber2, double cost) {
        if (pVNumber1 < 0 || pVNumber1 >= vertexes.length || pVNumber2 < 0 || pVNumber2 >= vertexes.length) throw new IndexOutOfBoundsException();
        if(pVNumber1 == pVNumber2) return false; //self loops not supported.

        if(!hasVertex(pVNumber1) || !hasVertex(pVNumber2)) {
            return false;
        }

        Vertex<D> vertex1 = vertexes[pVNumber1];
        Vertex<D> vertex2 = vertexes[pVNumber2];

        //if(vertex1.getEdgeToVertex(pVNumber2) != null) return false; //we already have that edge -> DISABLED TO BE FASTER!

        inEdge(pVNumber1, pVNumber2, cost);
        if(!digraph) {
            inEdge(pVNumber2, pVNumber1, cost);
        }

        edges++;
        return true;
    }

    private void inEdge(int pV1, int pV2, double cost) {
        Edge ins, tmp;
        ins = new Edge(cost, pV2);

        if(vertexes[pV1].adj == null) {
            vertexes[pV1].adj = ins;
        } else {
            vertexes[pV1].adj.prev = ins;
            ins.next = vertexes[pV1].adj;
            vertexes[pV1].adj = ins;
        }

        /* INSERT NEW EDGE SORTED
        if(vertexes[pV1].adj == null || vertexes[pV1].adj.toVertex > pV2) { //insert @ front
            ins.next = vertexes[pV1].adj;
            vertexes[pV1].adj = ins;
            if(ins.next != null) ins.next.prev = ins;
        } else {
            tmp = vertexes[pV1].inList(pV2);
            ins.next = tmp.next;
            if(tmp.next != null) tmp.next.prev = ins;
            ins.prev = tmp; tmp.next = ins;
        }*/

        vertexes[pV1].outDeg++;
        vertexes[pV2].inDeg++;
    }

    /*
    |--------------------------------------------------------------------------
    | Helpers methods
    |--------------------------------------------------------------------------
    */
    private void markVertexesUnvisited() {
        for (Vertex<D> vertex : vertexes) {
            vertex.visited = false;
        }
    }

    public D getVertexData(int v) {
        if(v < 0 || v > vertexes.length) throw new IndexOutOfBoundsException();

        return vertexes[v].data;
    }

    private boolean hasVertex(int number) {
        return vertexes[number] != null;
    }

    public boolean isEmpty() {
        return nVertexes == 0;
    }

    public int getNVertexes() {
        return nVertexes;
    }

    public int getHeight() {
        return vertexes.length;
    }

    public int getNEdges() {
        return edges;
    }

    public int getIndDeg(int pVert) {
        if(pVert < 0 || pVert > vertexes.length) throw new IndexOutOfBoundsException();

        return vertexes[pVert].inDeg;
    }

    public int getOutDeg(int pVert) {
        if(pVert < 0 || pVert > vertexes.length) throw new IndexOutOfBoundsException();

        return vertexes[pVert].outDeg;
    }

    public double getEdgeCost(int pVert1, int pVert2) {
        if(pVert1 < 0 || pVert1 > vertexes.length || pVert2 < 0 || pVert2 > vertexes.length) throw new IndexOutOfBoundsException();

        if(hasVertex(pVert1) && hasVertex(pVert2)) {
            Vertex<D> v1 = vertexes[pVert1];
            Edge e = v1.getEdgeToVertex(pVert2);
            return e == null ? -1 : e.cost;
        }

        return -1;
    }

    public boolean isSource(int pVert) {
        if(pVert < 0 || pVert > vertexes.length) throw new IndexOutOfBoundsException();

        Vertex<D> vertex = vertexes[pVert];
        return vertex != null && vertex.outDeg != 0 && vertex.inDeg == 0;
    }

    public boolean isSink(int pVert) {
        if(pVert < 0 || pVert > vertexes.length) throw new IndexOutOfBoundsException();

        Vertex<D> vertex = vertexes[pVert];
        return vertex != null && vertex.outDeg == 0 && vertex.inDeg != 0;
    }

    public boolean isDisconnected(int pVert) {
        if(pVert < 0 || pVert > vertexes.length) throw new IndexOutOfBoundsException();

        Vertex<D> vertex = vertexes[pVert];
        return vertex != null && vertex.outDeg == 0 && vertex.inDeg == 0;
    }

    public boolean isDigraph() {
        return digraph;
    }

    public String toString() {
        if (isEmpty()) return (digraph ? "Digraph" : "Graph") + " empty!\n";

        String str = (digraph ? "Digraph" : "Graph") + " with ";
        str += vertexes.length + " vertexes and " + edges + " edges\n";

        for (int i = 0; i < vertexes.length; i++) {
            str += vertexes[i].data + " >";

            for(Edge e = vertexes[i].adj; e != null; e = e.next) {
                if(!digraph && i > e.toVertex) continue;

                str += " " + vertexes[e.toVertex].data + " (" + e.cost + ")";
            }
            str += "\n";
        }

        return str;
    }

}
