package serie2.structures;

import java.util.ArrayList;


public class Stack<E> implements IStack<E> {
	ArrayList<E> memory = new ArrayList<E>();

	public E pop() {
	    E e = memory.get(memory.size() - 1);
	    memory.remove(memory.size() - 1);
	    return e;
	  }

	  public void push(E e) {
	    memory.add(e);
	  }

	@Override
	public void clear() {
		memory.clear();
	}
	
	public boolean isEmpty(){
		return memory.isEmpty();
	}

	}
