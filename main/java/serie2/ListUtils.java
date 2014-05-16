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
		
		while(count<lists.length){
				bubbleUp(lists, cmp);
				curr=lists[count];
				if(curr==null) return head;
				if(curr.value !=null){
					head.previous.next=curr;
					head.previous.next.next=head;
					head.previous.next.previous=head.previous;
					head.previous=head.previous.next;
					lists[count]=curr.next;
				}
				else
					count++;
				
		}
		
		return head;
	}
	
	private static <E> void bubbleUp(Node<E>[] list, Comparator<E> cmp) {
		
			int index = list.length-1;
			int parentIndex=(index-1)/2;
			if(list[0]!=null &&list[parentIndex].value!=null){
			while(index>0 && (cmp.compare(list[parentIndex].value, list[index].value)> 0)){
				swap(list, index, parentIndex);
				index = parentIndex;
				parentIndex=(index-1)/2;
			}}
	}
	
	private static <E> void swap(Node<E>[] list, int idx1, int idx2){
		E tmp = list[idx1].value;
		list[idx1].value = list[idx2].value;
		list[idx2].value = tmp;
	}


    public static Node<String> interleaved( Node< Node < String >> list ) {
    	Node<String> head = new Node<String>(null);
    	head.next=head;
    	head.previous=head;
    	Node<Node<String>> currentList=list.next;
    	
		if(currentList.value==null) return head;
		
		while(list.next != list){
			Node<String> sublist = currentList.value.next;
			if(sublist!= list.value){
				head.previous.next=sublist;
				head.previous.next.next=head;
				head.previous.next.previous=head.previous;
				head.previous=head.previous.next;
			}
			
			if(list.value != null && list.value.next == list.value){
				list.previous.next = list.next;
				list.next.previous = list.previous;
			}
			
			if(sublist != null){
				list=list.next;
				
			}
			else{
				list=list.next.next;
				
			}
		}
    	return head;
    }

}
