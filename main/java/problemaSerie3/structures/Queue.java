package problemaSerie3.structures;

public class Queue<T> {

    private static class QNode<T> {
        public QNode<T> next;
        public T elem;
        public  QNode(T val) {
            elem = val; next = null;
        }
    }

    private QNode<T> head, tail;

    public Queue() {
        head = null; tail = null;
    }

    public boolean enqueue(T pElem) {
        if(pElem == null) return false;
        QNode<T> newnode = new QNode<T>(pElem);

        if(tail == null)
            head = newnode;
        else
            tail.next = newnode;
        tail = newnode;
        return true;
    }

    public T dequeue() {
        if(isEmpty()) return null;

        T elem = head.elem;
        head = head.next;
        if(head == null) tail = null;
        return elem;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public String toString() {
        if(isEmpty()) return "Queue empty\n";
        String str = "Queue with: ";
        for(QNode<T> node = head; node != null; node = node.next)
            str += "\t" + node.elem.toString();
        return str + "\n";
    }

    public int getSize() {
        int i = 0;
        QNode<T> node = head;
        for( ; node != null; node = node.next)
            ++i;
        return i;
    }

    public boolean contains(Object s) {
        QNode<T> node = head;
        for( ; node != null; node = node.next)
            if(node.elem.equals(s)) return true;
        return false;
    }
}
