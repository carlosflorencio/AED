package problemaSerie1;

public class MinHeap<T extends Comparable<T>> {

	private T[] heap;
	private int count;

	@SuppressWarnings("unchecked")
	public MinHeap(int size) {
		this.heap = (T[]) new Comparable[size];
		this.count = 0;
	}

	/*
	|--------------------------------------------------------------------------
	| Add
	|--------------------------------------------------------------------------
	 */
	public boolean add(T elem) {
		
		if(this.count == this.heap.length) { //if heap is full
			return replaceSmallerElement(elem);
		}
		
		int index = this.count;
		this.heap[index] = elem;
		this.count++;
		
		bubbleUp();

		return true;
	}

	/*
	|--------------------------------------------------------------------------
	| Remove
	|--------------------------------------------------------------------------
	 */
	public T peek() {
		if (this.isEmpty()) {
			return null;
		}

		return this.heap[0];
	}

	public T poll() {
		T result = peek();

		// remove the root
		this.count--;
		this.heap[0] = this.heap[this.count];
		this.heap[this.count] = null;

		bubbleDown();

		return result;
	}

	/*
	|--------------------------------------------------------------------------
	| Heapify's
	|--------------------------------------------------------------------------
	 */
	protected void bubbleUp() {
		int index = this.count-1;

		while (hasParent(index) && (parent(index).compareTo(this.heap[index]) > 0)) {
			swap(index, parentIndex(index));
			index = parentIndex(index);
		}
	}

	protected void bubbleDown() {
		int smallerChild = 0;
		for(int i = 0 ; hasLeftChild(i); i = smallerChild) {
			smallerChild = leftIndex(i);
			
			if (hasRightChild(i) && this.heap[leftIndex(i)].compareTo(this.heap[rightIndex(i)]) > 0) { //find smaller child
				smallerChild = rightIndex(i);
			}
			
			if (this.heap[i].compareTo(this.heap[smallerChild]) > 0) { //swap if parent is greater
				swap(i, smallerChild);
			} else {
				break;
			}
			
		}
	}
	
	/*
	|--------------------------------------------------------------------------
	| Helpers?
	|--------------------------------------------------------------------------
	 */
	protected void swap(int idx1, int idx2) {
		T tmp = this.heap[idx1];
		this.heap[idx1] = this.heap[idx2];
		this.heap[idx2] = tmp;
	}
	
	private boolean replaceSmallerElement(T elem) {
		if(elem.compareTo( peek()  ) > 0) { //is this elem is greater than the smaller element in the heap?
			
			this.heap[0] = elem; //replace the greater leaf with the elem
			
			bubbleDown();
			
			return true;
		} else {
			return false;
		}
	}
	
	/*
	|--------------------------------------------------------------------------
	| Heap members
	|--------------------------------------------------------------------------
	 */
	protected boolean hasParent(int i) {
		return i > 0;
	}

	protected int leftIndex(int i) {
		return i * 2 + 1;
	}

	protected int rightIndex(int i) {
		return i * 2 + 2;
	}

	protected boolean hasLeftChild(int i) {
		return leftIndex(i) < this.count;
	}

	protected boolean hasRightChild(int i) {
		return rightIndex(i) < this.count;
	}

	protected T parent(int i) {
		return this.heap[parentIndex(i)];
	}

	protected int parentIndex(int i) {
		return (i-1) / 2;
	}

	/*
	|--------------------------------------------------------------------------
	| Getters
	|--------------------------------------------------------------------------
	 */
	public int getCount() {
		return this.count;
	}

	public int getSize() {
		return this.heap.length;
	}

	public boolean isEmpty() {
		return this.count == 0;
	}

	/*
	|--------------------------------------------------------------------------
	| Print
	|--------------------------------------------------------------------------
	 */
	public void print() {
		for (int i = 0; i < this.heap.length; i++) {
			System.out.print("[" + this.heap[i] + "]");
		}
		System.out.println();
	}

}
