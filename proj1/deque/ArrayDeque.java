package deque;

public class ArrayDeque<T> {
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

    public void doubleSpace(){
        T[] newdata = (T[]) new Object[2*maxSize];
        int j = 0;
        while(nextLast != nextFirst){
            newdata[j++] = data[nextLast];
            nextLast = plus(nextLast);
        }
        newdata[j] =  data[nextLast];
        maxSize *= 2;
        nextLast = size;
        nextFirst = maxSize - 1;
        data = newdata;
    }

    public void halfSpace(){
        T[] newdata = (T[]) new Object[maxSize/2];
        int j = 0;
        nextFirst = plus(nextFirst);
        while(nextLast != nextFirst){
            newdata[j++] = data[nextFirst];
            nextFirst = plus(nextFirst);
        }
        maxSize /= 2;
        nextLast = size;
        nextFirst = maxSize - 1;
        data = newdata;
    }

    public int plus(int i){
        return (i + 1 + maxSize) % maxSize;
    }

    public int minus(int i){
        return (i - 1 + maxSize) % maxSize;
    }

    public void addFirst(T item) {
        if(size == maxSize){
            doubleSpace();
        }
        data[nextFirst] = item;
        nextFirst = minus(nextFirst);
        ++size;
    }

    public void addLast(T item) {
        if(size == maxSize){
            doubleSpace();
        }
        data[nextLast] = item;
        nextLast = plus(nextLast);
        ++size;
    }

    public T removeFirst() {
        if(maxSize/size >= 4){
            halfSpace();
        }
        --size;
        nextFirst = plus(nextFirst);
        return data[nextFirst];
    }

    public T removeLast() {
        if(maxSize/size >= 4){
            halfSpace();
        }
        --size;
        nextLast = minus(nextLast);
        return data[nextLast];
    }

    public T get(int index) {
        return data[plus(index + nextFirst)];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void printDeque(){
        int i = plus(nextFirst);
        while(i != nextLast){
            System.out.print(data[i] + " ");
            i = plus(i);
        }
    }
}
