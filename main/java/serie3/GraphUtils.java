package serie3;


public class GraphUtils {
	
	public static boolean isEdgeInAnMST(Vertex[] graph, int origId, int destId){
		Pair [] pair = new Pair[graph.length];
		
		for(int i=0; i<graph.length;i++){
			graph[i].adjacentList = orderEdgesOfVertex(graph[i]);
			pair[i] = new Pair(graph[i].id, graph[i].adjacentList);
		}
		
		return getResponse(pair, origId, destId);
	}

	private static boolean getResponse(Pair[] pair, int origId, int destId) {
		
		for(int i = 0; i < pair.length; ++i){
			buildMinHeap(pair, pair.length);
			int orig = pair[0].getKey();
			int dest = pair[0].getValue().adjacent.id;
		}
		return false;
	}

	private static Edge orderEdgesOfVertex(Vertex vertex) {
		Edge adjacentToVertex = vertex.adjacentList;
		Edge newAdjacent=null;
		Edge current=null;
		double [] weights = new double[20];
		int i=0;
		while(adjacentToVertex!=null){
			weights[i++]=adjacentToVertex.weight;
			adjacentToVertex=adjacentToVertex.next;
		}
		selectionSort(weights);
		
		for(i= 0; i < weights.length; ++i){
			if(i==0) {
				newAdjacent = new Edge(vertex, weights[i]);
				current=newAdjacent;
			}
			else{
				current.next=new Edge(vertex, weights[i]);
				current=current.next;
			}
		}
		
		return newAdjacent;
	}

	private static void selectionSort(double[] weights) {
		int min;
		double v;
		for(int j = 0; j < weights.length-1; j++){
			min=j;
			for(int i = j+1; i <= weights.length-1; ++i){
				if(weights[i]< weights[min]) min=i;
			}
			v=weights[j];
			weights[j]=weights[min];
			weights[min]=v;
		}
		
	}
	
	private static void buildMinHeap(Pair[] list, int size){
		int parentIndex = (size-1)/2;
		
		for(;parentIndex >=0; parentIndex--){
			minHeapFy(list,parentIndex,size);
		}
	}

	

	private static void minHeapFy(Pair[] list, int parentIndex, int size) {
		int leftChild = 2*parentIndex +1;
		int rightChild = 2*parentIndex+2;
		int slower = parentIndex;
		
		if(leftChild < size && list[leftChild].getValue().weight < list[parentIndex].getValue().weight)slower = leftChild;
		if(rightChild < size && list[rightChild].getValue().weight < list[slower].getValue().weight) slower = rightChild;
		if(slower == parentIndex) return;
		
		Pair aux = list[parentIndex];
		list[parentIndex]= list[slower];
		list[slower] = aux;
		
		minHeapFy(list,slower,size);
		
	}
	
}
