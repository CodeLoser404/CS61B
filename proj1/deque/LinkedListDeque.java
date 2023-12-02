package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {

    private class Node {
        private T data;
        private Node next;
        private Node prev;

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

    @Override
    public void addFirst(T item) {
        Node temp = new Node(item, head, head.next);
        temp.next.prev = temp;
        head.next = temp;
        ++size;
    }

    @Override
    public void addLast(T item) {
        Node temp = new Node(item, head.prev, head);
        temp.prev.next = temp;
        head.prev = temp;
        ++size;
    }

    @Override
    public T removeFirst() {
        if (size <= 0) {
            return null;
        }
        T item = head.next.data;
        head.next.next.prev = head;
        head.next = head.next.next;
        --size;
        return item;
    }

    @Override
    public T removeLast() {
        if (size <= 0) {
            return null;
        }
        T item = head.prev.data;
        head.prev.prev.next = head;
        head.prev = head.prev.prev;
        --size;
        return item;
    }

    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        Node p = head.next;
        for (; index > 0; --index) {
            p = p.next;
        }
        return p.data;
    }

    @Override
    public int size() {
        return size;
    }

    public T getRecursive(int index) {
        return getRecursiveHelper(head.next, index);
    }

    private T getRecursiveHelper(Node p, int index) {
        if (index == 0) {
            return p.data;
        }
        return getRecursiveHelper(p.next, index - 1);
    }

    @Override
    public void printDeque() {
        for (Node p = head.next; p != head; p = p.next) {
            System.out.print(p.data + " ");
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private Node node;
        private int count;

        LinkedListDequeIterator() {
            count = 0;
            node = head;
        }

        @Override
        public boolean hasNext() {
            return count < size;
        }

        @Override
        public T next() {
            count += 1;
            node = node.next;
            return (T) node.data;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Deque)) {
            return false;
        }
        Deque<T> otherDeque = (Deque<T>) other;

        if (size() != otherDeque.size()) {
            return false;
        }

        for (int i = 0; i < size(); i++) {
            if (!get(i).equals(otherDeque.get(i))) {
                return false;
            }
        }
        return true;
    }

}
