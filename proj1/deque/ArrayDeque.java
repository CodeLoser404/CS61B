package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T> {
    private T[] data;
    private int size;
    private int maxSize;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        data = (T[]) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
        maxSize = 8;
    }

    public void doubleSpace() {
        T[] newdata = (T[]) new Object[2 * maxSize];
        int j = 0;
        while (nextLast != nextFirst) {
            newdata[j++] = data[nextLast];
            nextLast = plus(nextLast);
        }
        newdata[j] = data[nextLast];
        maxSize *= 2;
        nextLast = size;
        nextFirst = maxSize - 1;
        data = newdata;
    }

    public void halfSpace() {
        T[] newdata = (T[]) new Object[maxSize / 2];
        int j = 0;
        nextFirst = plus(nextFirst);
        while (nextLast != nextFirst) {
            newdata[j++] = data[nextFirst];
            nextFirst = plus(nextFirst);
        }
        maxSize /= 2;
        nextLast = size;
        nextFirst = maxSize - 1;
        data = newdata;
    }

    public int plus(int i) {
        return (i + 1 + maxSize) % maxSize;
    }

    public int minus(int i) {
        return (i - 1 + maxSize) % maxSize;
    }

    @Override
    public void addFirst(T item) {
        if (size == maxSize) {
            doubleSpace();
        }
        data[nextFirst] = item;
        nextFirst = minus(nextFirst);
        ++size;
    }

    @Override
    public void addLast(T item) {
        if (size == maxSize) {
            doubleSpace();
        }
        data[nextLast] = item;
        nextLast = plus(nextLast);
        ++size;
    }

    @Override
    public T removeFirst() {
        if (size == 0 || maxSize / size >= 4) {
            halfSpace();
        }
        if (size == 0) {
            return null;
        }
        --size;
        nextFirst = plus(nextFirst);
        return data[nextFirst];
    }

    @Override
    public T removeLast() {
        if (size == 0 || maxSize / size >= 4) {
            halfSpace();
        }
        if (size == 0) {
            return null;
        }
        --size;
        nextLast = minus(nextLast);
        return data[nextLast];
    }

    @Override
    public T get(int index) {
        return data[plus(index + nextFirst)];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        int i = plus(nextFirst);
        while (i != nextLast) {
            System.out.print(data[i] + " ");
            i = plus(i);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int count;

        ArrayDequeIterator() {
            count = -1;
        }

        @Override
        public boolean hasNext() {
            return count + 1 < size;
        }

        @Override
        public T next() {
            count += 1;
            return data[plus(nextFirst + count)];
        }
    }

    @Override
    public boolean equals(Object other) {
        if ((!(other instanceof Deque)) || (size() != ((Deque<T>) other).size())) {
            return false;
        }
        T nodeThis = get(0);
        T nodeO = ((Deque<T>) other).get(0);

        for (int i = 1; i < size(); i++) {
            if (!(nodeThis.equals(nodeO))) {
                return false;
            }
            nodeThis = get(i);
            nodeO = ((Deque<T>) other).get(i);
        }
        return true;
    }
}
