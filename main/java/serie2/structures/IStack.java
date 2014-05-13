package serie2.structures;

public abstract interface IStack<E> {
	void push(E data);
	E pop();
	void clear();
}
