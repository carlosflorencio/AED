package serie3;

public class Edge {
	Edge  next ;
	Vertex adjacent;
	double weight;
	
	
	public Edge (Vertex v, double w){
		adjacent = v;
		next = null;
		weight = w;

	}
	public void addEdge(Edge e ){
		Edge lastEdge = this.toLastEdge(this);
		e.next=null;
		lastEdge.next = e;
	}
	
	public Edge toLastEdge(Edge le){
        while (le.next != null){
            le=le.next;
        }        
     return le;
    }

}
