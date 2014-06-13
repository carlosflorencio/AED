package serie3;

import serie3.structures.MyComparator;

public class GraphUtils {
	
	public static boolean isEdgeInAnMST(Vertex[] graph, int origId, int destId){
		
		MyComparator cmp =  new MyComparator();
		Pair [] pair = new Pair[graph.length];
		
		for(int i=0; i<graph.length;i++){
			orderEdgesOfVertex(graph[i]);
		}
		
		return false;
	}

	private static void orderEdgesOfVertex(Vertex vertex) {
		// TODO Auto-generated method stub
		
	}

	/*private static List<Pair<Integer, Edge>> getOrderList(List<Pair<Integer, Edge>> list, MyComparator cmp) {
		MinHeap<Pair<Integer,Edge>> min = new MinHeap<Pair<Integer,Edge>>(list.size(), cmp);
		
		for(int i=0; i<list.size(); i++){
			min.add(list.get(i));
		}
		
		
	}

	private static Pair[] getPairListOrderByVertexWeight(Vertex[] graph, MyComparator cmp){
		Pair[] list = new Pair[graph.];
		for(int i = 0; i<graph.length; i++){
			Edge curr = graph[i].adjacentList;
			MinHeap<Double> weight = new MinHeap<Double>(2*graph.length, cmp);
			while(curr != null){
				weight.add(curr.weight);
				curr=curr.next;
			}
			
			list.add(new Pair<Integer, Edge>(i, new Edge(graph[i],weight.getLast())));
			while(!weight.isEmpty()){
				list.get(i).getElem().next=new Edge(graph[i],weight.getLast());
			}
		}
		return list;
	}*/
	
	
}
