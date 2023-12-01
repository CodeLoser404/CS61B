package deque;

public class LinkedListDeque<T> {

    public class Node {
        public T data;
        public Node next;
        public Node prev;

        public Node(T item, Node p, Node n) {
            data = item;
            next = n;
            prev = p;
        }
    }

    private Node head;
    private int size;

    public LinkedListDeque() {
        head = new Node(null, null, null);
        head.next = head;
        head.prev = head;
        size = 0;
    }

    public void addFirst(T item) {
        Node temp = new Node(item, head, head.next);
        temp.next.prev = temp;
        head.next = temp;
        ++size;
    }

    public void addLast(T item) {
        Node temp = new Node(item, head.prev, head);
        temp.prev.next = temp;
        head.prev = temp;
        ++size;
    }

    public T removeFirst() {
        if(size <= 0){
            return null;
        }
        T item = head.next.data;
        head.next.next.prev = head;
        head.next = head.next.next;
        --size;
        return item;
    }

    public T removeLast() {
        if(size <= 0){
            return null;
        }
        T item = head.prev.data;
        head.prev.prev.next = head;
        head.prev = head.prev.prev;
        --size;
        return item;
    }

    public T get(int index) {
        if(index >= size){
            return null;
        }
        Node p = head.next;
        for (; index > 0; --index) {
            p = p.next;
        }
        return p.data;
    }

    public int size() {
        return size;
    }

    public T getRecursive(int index) {
        return getRecursiveHelper(head.next, index);
    }

    public T getRecursiveHelper(Node p, int index) {
        if (index == 0) {
            return p.data;
        }
        return getRecursiveHelper(p.next, index - 1);
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void printDeque(){
        for(Node p = head.next; p != head; p = p.next){
            System.out.print(p.data + " ");
        }
    }
}
