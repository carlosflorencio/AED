package problemaSerie3.structures;

public class Digraph<D> {

    /*
    |--------------------------------------------------------------------------
    | Private internal classes
    |--------------------------------------------------------------------------
    */
    private static class GNode<V, E> {
        public int number;
        public GNode<V, E> prev;
        public GNode<V, E> next;
        public GNode<E, V> adj; //adjacency list
        public V elem;
        public boolean visited;

        public GNode(V pVal, int pNumber) {
            elem = pVal;
            number = pNumber;
            prev = next = null;
            adj = null;
            visited = false;
        }

        private GNode<V, E> inList(int pNumber) { //calc the node to insert after
            GNode<V, E> tmp = this, prev = null;
            for (; tmp != null; tmp = tmp.next) {
                if (tmp.number >= pNumber) break;
                prev = tmp;
            }
            if (tmp == null || tmp.number > pNumber) return prev;
            else return null;
        }

        private GNode<V, E> outList(int pNumber) { //calc the node to remove
            GNode<V, E> tmp = this;
            for (; tmp != null; tmp = tmp.next)
                if (tmp.number == pNumber) break;
            return tmp;
        }
    }

    private static class Vertex<D> {
        public int inDeg, outDeg; //semi-degrees of incidence and emerging
        public D data;

        public Vertex(D data) {
            inDeg = outDeg = 0;
            this.data = data;
        }
    }

    private static class Edge {
        public int cost;

        public Edge(int pCost) {
            cost = pCost;
        }
    }

    /*
    |--------------------------------------------------------------------------
    | Real class stuff
    |--------------------------------------------------------------------------
    */
    private GNode<Vertex<D>, Edge> head;
    private boolean digraph;
    private int vertexes;
    private int edges;

    public Digraph(boolean isDigraph) {
        head = null;
        digraph = isDigraph;
        vertexes = 0;
        edges = 0;
    }

    /*
    |--------------------------------------------------------------------------
    | Transversal (later customization)
    |--------------------------------------------------------------------------
    */
    public String breadthFirst() {
        String str = "BFS: ";

        if(vertexes == 0 || edges == 0) return "";

        for(GNode<Vertex<D>, Edge> v = head; v != null; v = v.next)
            v.visited = false;

        for(GNode<Vertex<D>, Edge> v = head; v != null; v = v.next)
            if(!v.visited) str = breathFirstSearch(v, str);
        return str + "\n";
    }

    private String breathFirstSearch(GNode<Vertex<D>, Edge> pVert, String str) {
        Queue<GNode<Vertex<D>, Edge>> queue = new Queue<GNode<Vertex<D>, Edge>>();

        pVert.visited = true;
        queue.enqueue(pVert);

        while (queue.isEmpty() == false) {
            GNode<Vertex<D>, Edge> v = queue.dequeue();
            str += " " + v.number;
            for(GNode<Edge, Vertex<D>> e = v.adj; e != null; e = e.next) {
                if(e.adj.visited == false) {
                    e.adj.visited = true;
                    queue.enqueue(e.adj);
                }
            }
        }
        return  str;
    }


    /*
    |--------------------------------------------------------------------------
    | Inserting and Deletings Vertexes and Edges
    |--------------------------------------------------------------------------
    */
    public boolean insertVertex(D data, int pVNumber) {
        GNode<Vertex<D>, Edge> ins, tmp;
        ins = new GNode<Vertex<D>, Edge>(new Vertex(data), pVNumber);
        if(head == null || head.number > pVNumber) { //insert in head?
            ins.next = head; head = ins;
            if(ins.next != null) ins.next.prev = ins;
        } else {
            if((tmp = head.inList(pVNumber)) == null) return  false; //insert after last vertex to maintain order

            ins.next = tmp.next;
            if(tmp.next != null) tmp.next.prev = ins;
            ins.prev = tmp; tmp.next = ins;
        }
        vertexes++;
        return true;
    }

    public boolean deleteVertex(int pVNumber) {
        GNode<Vertex<D>, Edge> rvert;
        GNode<Edge, Vertex<D>> redge;

        if(vertexes == 0) return false;

        if((rvert = head.outList(pVNumber)) == null) return false; //position to remove the vertex
        while(rvert.adj != null) {
            redge = rvert.adj;
            redge.adj.elem.inDeg--;
            rvert.adj = rvert.adj.next;
            edges--;
        }
        if(rvert == head) { //remove vertex from the head
            if(rvert.next != null) rvert.next.prev = null;
            head = rvert.next;
        } else {
            rvert.prev.next = rvert.next;
            if(rvert.next != null) rvert.next.prev = rvert.prev;
        }

        GNode<Vertex<D>, Edge> node = head;
        while (node != null) { //remove edges
            redge = node.adj;
            if(redge != null && (redge = redge.outList(pVNumber)) != null) {
                if(redge == node.adj) { //remove edge from the head of the adjacency list
                    if(redge.next != null) redge.next.prev = null;
                    node.adj = node.adj.next;
                } else {
                    redge.prev.next = redge.next;
                    if(redge.next != null) redge.next.prev = redge.prev;
                }
                node.elem.outDeg--;
                edges--;
            }
            node = node.next;
        }
        vertexes--;
        return true;
    }

    public boolean insertEdge(int pVNumber1, int pVNumber2, int cost) {
        GNode<Vertex<D>, Edge> v1, v2;
        if(vertexes == 0) return false;
        if(pVNumber1 == pVNumber2) return false; //self loops not supported.

        //check if vertexes exist
        if((v1 = head.outList(pVNumber1)) == null) return false;
        if(v1.adj != null && v1.adj.outList(pVNumber2) != null) return false; //this edge already exists
        if((v2 = head.outList(pVNumber2)) == null) return false;

        inEdge(v1, v2, cost); //insert!
        if(digraph == false)
            inEdge(v2, v1, cost);
        edges++;
        return true;
    }

    private void inEdge(GNode<Vertex<D>, Edge> pV1, GNode<Vertex<D>, Edge> pV2, int cost) {
        GNode<Edge, Vertex<D>> ins, tmp;
        ins = new GNode<Edge, Vertex<D>>(new Edge(cost), pV2.number);
        if(pV1.adj == null || pV1.adj.number > pV2.number) {
            ins.next = pV1.adj; pV1.adj = ins;
            if(ins.next != null) ins.next.prev = ins;
        } else { //insert @ front
            tmp = pV1.adj.inList(pV2.number);
            ins.next = tmp.next;
            if(tmp.next != null) tmp.next.prev = ins;
            ins.prev = tmp; tmp.next = ins;
        }
        ins.adj = pV2; //connect vertexes
        pV1.elem.outDeg++;
        pV2.elem.inDeg++;
    }

    public boolean deleteEdge(int pVNumber1, int pVNumber2) {
        GNode<Vertex<D>, Edge> v1, v2;
        if(vertexes == 0 || edges == 0) return false;
        if(pVNumber1 == pVNumber2) return false; //self loops not supported.

        if((v1 = head.outList(pVNumber1)) == null) return false; //check if vertexes exist
        if(v1.adj == null || v1.adj.outList(pVNumber2) == null) return false;
        if((v2 = head.outList(pVNumber2)) == null) return false;

        outEdge(v1, v2); //remove edge!
        if(digraph == false)
            outEdge(v2, v1);
        edges--;
        return true;
    }

    private void outEdge(GNode<Vertex<D>, Edge> pV1, GNode<Vertex<D>, Edge> pV2) {
        GNode<Edge, Vertex<D>> rem;
        rem = pV1.adj.outList(pV2.number); //position to remove edge
        if(rem == pV1.adj) { //remove head of the list
            if(rem.next != null) rem.next.prev = null;
            pV1.adj = rem.next;
        } else {
            rem.prev.next = rem.next;
            if(rem.next != null) rem.next.prev = rem.prev;
        }
        pV1.elem.outDeg--;
        pV2.elem.inDeg--;
    }


    /*
    |--------------------------------------------------------------------------
    | Helpers methods
    |--------------------------------------------------------------------------
    */
    public boolean isEmpty() {
        return vertexes == 0;
    }

    public GNode<Vertex<D>, Edge> getHead() {
        return head;
    }

    public int getNVertexes() {
        return vertexes;
    }

    public int getNEdges() {
        return edges;
    }

    public int getIndDeg(int pVert) {
        GNode<Vertex<D>, Edge> node = head;
        while (node != null && node.number < pVert) node = node.next;

        if (node != null && node.number == pVert) return node.elem.inDeg;
        else return 0;
    }

    public int getOutDeg(int pVert) {
        GNode<Vertex<D>, Edge> node = head;
        while (node != null && node.number < pVert) node = node.next;

        if (node != null && node.number == pVert) return node.elem.outDeg;
        else return 0;
    }

    public int getEdgeCost(int pVert1, int pVert2) {
        GNode<Vertex<D>, Edge> node = head;
        while (node != null && node.number < pVert1) node = node.next;
        if (node != null && node.number == pVert1) {
            GNode<Edge, Vertex<D>> ladj = node.adj;
            while (ladj != null && ladj.number < pVert2) ladj = ladj.next;
            if (ladj != null && ladj.number == pVert2) return ladj.elem.cost;
            else return 0;
        } else return 0;
    }

    public boolean isSource(int pVert) {
        GNode<Vertex<D>, Edge> node = head;
        while (node != null && node.number < pVert) node = node.next;
        if (node != null && node.number == pVert)
            return node.elem.inDeg == 0 && node.elem.outDeg != 0;
        else return false;
    }

    public boolean isSink(int pVert) {
        GNode<Vertex<D>, Edge> node = head;
        while (node != null && node.number < pVert) node = node.next;
        if (node != null && node.number == pVert)
            return node.elem.inDeg != 0 && node.elem.outDeg == 0;
        else return false;
    }

    public boolean isDisconnected(int pVert) {
        GNode<Vertex<D>, Edge> node = head;
        while (node != null && node.number < pVert) node = node.next;
        if (node != null && node.number == pVert)
            return node.elem.inDeg == 0 && node.elem.outDeg == 0;
        else return false;
    }

    public boolean equals(Digraph pGraph) {
        if (vertexes != pGraph.getNVertexes() || edges != pGraph.getNEdges()) return false;
        if(pGraph.isDigraph() != digraph) return false;

        GNode<Vertex<D>, Edge> thisnode = head, pGraphnode = pGraph.getHead();
        while (thisnode != null && pGraphnode != null) {

            if (thisnode.number != pGraphnode.number) return false;

            GNode<Edge, Vertex<D>> thisadj = thisnode.adj;
            GNode<Edge, Vertex<D>> gadj = pGraphnode.adj;
            while (thisadj != null && gadj != null) {
                if(thisadj.number != gadj.number) return false;
                thisadj = thisadj.next; gadj = gadj.next;
            }
            if(thisadj != null || gadj != null) return false;
            thisnode = thisnode.next; pGraphnode = pGraphnode.next;
        }
        if(thisnode != null || pGraphnode != null) return false;
        else return true;
    }

    public boolean isDigraph() {
        return digraph;
    }

    public String toString() {
        if(isEmpty()) return (digraph ? "Digraph" : "Graph") + " empty!\n";

        String str = (digraph ? "Digraph" : "Graph") + " with ";
        str += vertexes + " vertexes and " + edges + " edges\n";
        for (GNode<Vertex<D>, Edge> v = head; v != null; v = v.next) {
            str += v.number + " >";
            for(GNode<Edge, Vertex<D>> e = v.adj; e != null; e = e.next) {
                if(!digraph && v.number > e.number)
                    continue;
                else
                    str += " " + e.number + " (" + e.elem.cost + ")";
            }
            str += "\n";
        }
        return str;
    }

}
