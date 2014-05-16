package serie2;

import java.util.Comparator;

import serie2.structures.MinHeap;


public class ListUtils {
	
	public static <E> Node<E> merge(Node<E>[] lists, Comparator<E> cmp){
		MinHeap<E> heap = new MinHeap<E>(100, cmp);
		int count =0;
		Node<E> head = new Node<E>(null);//sentinel node
		head.previous=head;
		head.next=head;
		Node<E> curr=new Node<E>();
		
		//build min heap with element of E type
		while(count<lists.length){
			for(int i=count; i<lists.length; ++i){
				curr=lists[i];
				if(curr!=null){
					heap.add(curr.value);
					lists[i]=lists[i].next;
				}
				else
					count++;
			}
		}
		
		while(!heap.isEmpty()){
			curr = new Node<E>(heap.poll());//Node with lower element
			//add curr to last added
			head.previous.next=curr;
			head.previous.next.next=head;
			head.previous.next.previous=head.previous;
			head.previous=head.previous.next;
		}
		
		return head;	
	}

	 public static Node<String> interleaved( Node< Node < String >> list ) {
		 	Node<String> head = new Node<String>(null);
	    	head.next=head;
	    	head.previous=head;
	    	Node<String> sublist;
	    	Node<String> aux;
	    	
	    	if(list.next==list) return head; //means empty list
	    	
	    	list=list.next; //clean a first list's element
	    	
	    	while(list.next != list){
	    		
	    		if(list.value.next != list.value){
	    			//add a current sublist to return Node
	    			sublist = list.value.next;
					aux = sublist;
					sublist = sublist.next;
					list.value.next = sublist;
					sublist.previous= list.value;
					head.previous.next=aux;
					head.previous.next.next=head;
					head.previous.next.previous=head.previous;
					head.previous=head.previous.next;
	    		}
	    		if(list.value != null && list.value.next == list.value){
					list.previous.next = list.next;
					list.next.previous = list.previous;
				}
				
				if(list.next.value == null)
					list = list.next.next; //back on list
				else 
					list = list.next; //increasing list element
	    		
	    	}
	  
			return head;
	 }
}
