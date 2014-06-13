package serie3;

public class Vertex {
	int id ;
	Edge adjacentList ;

	
	public Vertex(int idx){
		id = idx;
		adjacentList = null;
	}
	
	public void addAdjacent(Edge e){

		if(this.adjacentList == null)
			adjacentList = e;
	
		else adjacentList.addEdge(e);
	}

}
