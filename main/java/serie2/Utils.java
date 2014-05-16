package serie2;

import serie2.structures.Stack;


public class Utils {
	public static final String AUX= "(){}[]";
	
	public static boolean verifyPairing(String s){
		Stack<Character> stk= new Stack<Character>();
		char c;
		for(int i=0; i<s.length(); ++i){
			c=s.charAt(i);
			if(c=='(' || c== '{' || c== '[')
				stk.push(c);
			else {
				if((c==')' || c== '}' || c == ']') && !check(c, stk)) return false;
			}
			
		}
		return stk.isEmpty();
		
	}
	

	private static boolean check(char c, Stack<Character> stk) {
		//stack empty means that bracket was closed without opening
		return !stk.isEmpty() && stk.pop() == AUX.charAt(AUX.indexOf(c) -1);
	}


	public static <E> Node<E>[] shrink(Node<E>[] hashTable){
		Node<E>[] halfHashTable = (Node<E>[]) new Node[hashTable.length/2];
		boolean second =false;
		
		for(int i=0,j=0; i<hashTable.length; ++i, ++j){
			if(i==halfHashTable.length){
				j=0;
				second = true;
			}
			if(second){
				hashTable[i].previous.next = halfHashTable[j].next;
				halfHashTable[j].next.previous = hashTable[i].previous;
				halfHashTable[j].next = hashTable[i].next;
				hashTable[i].next.previous = halfHashTable[j];
			}
			else
				halfHashTable[j] = hashTable[i];
		}
		return halfHashTable;
	}
	

}
