package serie2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class ListUtilTest {
	
	/*
	 * For circular lists with sentinel
	 * 
	 */
	
	public static <E> Node<E> emptyListWithSentinel() {
		Node<E> empty= new Node<E>();
		empty.next = empty.previous = empty;
		return empty;
	}

	public static <E> boolean isEmptyListWithSentinel(Node<E> list) {
		return list.next == list && list.previous == list;
	}
	

	public static <E> void addNode(Node<E> x, Node<E> list) {
		x.previous = list.previous; x.next = list;
		list.previous.next = x; list.previous = x;
	}
	
	public static <E> void addNewNode(E v, Node<E> list) {
		Node<E> x = newNode(v);
		x.previous = list.previous; x.next = list;
		list.previous.next = x; list.previous = x;
	}
	
	public static Node<Integer> getList( int start, int length, ArrayList<Node<Integer>> array) {
		Node<Integer> list = ListUtilTest.<Integer>emptyListWithSentinel();
		for (int i= length-1; i>=0; --i) {
			list.next = newNode(i+start, list, list.next);
			list.next.next.previous = list.next;
		}
		for (Node<Integer> current = list.next; current != list; current= current.next) 
			array.add( current );
		return list;
	}
	
	
	/*
	 * For non_circular lists with no sentinel
	 * 
	 */
	
	public static <E> Node<E> emptyListWithoutSentinel() {
		return null;
	}
	public static <E> boolean isEmptyListWithoutSentinel(Node<E> list) {
		return list==null;
	}

	public static  <E> boolean isSorted(Node<E> list,Comparator<E> cmp){		
		Node<E> curr=list.next;
		if(curr==list || curr==list.previous) return true;
		while( curr.next!=list){
		    if(cmp.compare(curr.value, curr.next.value)>0) return false;
			curr=curr.next;
		}
		return true;
	}	
	
	/*
	 * 
	 * Generic Methods
	 * 
	 * 
	 */
		
	public static <E> Node<E> newNode( E v ) { 
		Node<E> result = new Node<E>();
		result.value = v; 
		return result;
	}
	public  static <E> Node<E> newNode(E v, Node<E> p, Node<E> n) {
		Node<E> result = newNode( v );
		result.previous = p;
		result.next = n;
		return result;
	}
}
