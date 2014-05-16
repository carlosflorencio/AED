package serie2;

import java.util.Comparator;

import serie2.structures.MinHeap;


public class ListUtils {
	
	/*public static <E> Node<E> merge(Node<E>[] lists, Comparator<E> cmp){
		MinHeap<E> heap = new MinHeap<E>(100, cmp);
		int count =0;
		Node<E> head = new Node<E>(null);
		head.previous=head;
		head.next=head;
		Node<E> curr=new Node<E>();
		
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
			curr = new Node<E>(heap.poll());
			head.previous.next=curr;
			head.previous.next.next=head;
			head.previous.next.previous=head.previous;
			head.previous=head.previous.next;
		}
		
		return head;	
	}*/
	
	public static <E> Node<E> merge(Node<E>[] lists, Comparator<E> cmp){
		int count = 0;
		Node<E> head = new Node<E>(null);
		head.previous=head;
		head.next=head;
		Node<E> curr=null;
		if(lists[0]==null) return head;
		
		while(count<lists.length){
			buildMinHeap(lists, cmp, lists.length-1);
			curr=lists[count];
			if(lists[count].value !=null){
				if(lists[count].next==null){
					count++;
					//if(count<lists.length)
						//lists[0]=lists[count];
				}
				else{
					lists[count]=lists[count].next;
				}
				System.out.println(curr.value);
				head.previous.next=curr;
				head.previous.next.next=head;
				head.previous.next.previous=head.previous;
				head.previous=head.previous.next;
					
			}
			else{
				count++;
				//if(count<lists.length)
					//lists[0]=lists[count];
			}
		}
		
		return head;
	}
	
	
	
	private static <E> void buildMinHeap(Node<E>[] list,Comparator<E> cmp,int size){
		int parentIndex = (size-1)/2;
		
		for(;parentIndex >=0; parentIndex--){
			minHeapify(list,parentIndex,size,cmp);
		}
	}
	
	private static <T> void minHeapify(Node<T>[] list, int i, int n, Comparator<T> cmp){//order to be min-heap
		int left = 2 * i + 1;
	    int right = 2 * i + 2;
	    int lower = i;

	    if( left < n &&(cmp.compare(list[left].value, list[lower].value)<=0))
	        lower = left;
	    if( right < n &&(cmp.compare(list[right].value, list[lower].value)<=0))
	        lower = right;
	    if( lower == i ) return;
	    swap(list,lower,i);
	    minHeapify(list, lower, n, cmp);  
	}

	private static <E> void swap(Node<E>[] list, int idx1, int idx2){
		Node<E> aux = list[idx2];
		list[idx2] = list[idx1];
		list[idx1] = aux;
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
					list = list.next.next;
				else 
					list = list.next;
	    		
	    	}
	  
			return head;
	 }
}
