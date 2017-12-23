package collections;

import java.util.Iterator;

public class CyclicArrayQueue<Item> implements IQueue<Item> {

    private static final int DEFAULT_CAPACITY = 10;
    private Item[] elementData;
    private int write=0;
    private int read=0;
    private int size=0;

    public CyclicArrayQueue(){
        this.elementData = (Item[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void enqueue(Item item) {
        if(item==null)
            return;
        grow();
        this.elementData[this.write]=item;
        this.size++;
        this.write=inc(this.write);
    }

    @Override
    public Item dequeue() {
        if(this.isEmpty())
            return null;
        shrink();
        Item item = this.elementData[read];
        this.size--;
        this.elementData[this.read] = null;
        this.read=inc(this.read);
        return item;
    }

    private int inc(int iter) {
        iter++;
        if(iter>=this.elementData.length)
            iter=0;
        return iter;
    }

    @Override
    public boolean isEmpty() {
        return this.size==0;
    }

    @Override
    public int size() {
        return this.size;
    }

    private void grow() {
        if(this.size<this.elementData.length-1)
            return;
        int newSize = (int)(this.elementData.length * 1.5);
        changeCapacity(newSize);
    }

    private void shrink() {
        if(this.size*4>this.elementData.length)
            return;
        if (this.elementData.length*0.5<=DEFAULT_CAPACITY)
            return;
        int newSize = (int)(this.elementData.length * 0.5);
        changeCapacity(newSize);
    }

    private void changeCapacity(int newCapacity) {
        Item[] newElementData = (Item[]) new Object[newCapacity];
        int iter = 0;
        for (int i = this.read; i != this.write ; i=inc(i)) {
            newElementData[iter] = this.elementData[i];
            iter++;
        }
        this.read = 0;
        this.write = this.size;
        this.elementData = newElementData;
    }

    @Override
    public Iterator<Item> iterator() {
        return new CyclicArrayQueueIterator();
    }

    private class CyclicArrayQueueIterator implements Iterator<Item> {

        int shift=0;
        @Override
        public boolean hasNext() {
            return read==write;
        }

        @Override
        public Item next() {
            if(hasNext()) {
                int iter = read;
                for (int i = 0; i < shift; i++) {
                    iter = inc(iter);
                    if (iter == write)
                        return null;
                }
                shift++;
                return elementData[iter];
            }

            return null;
        }

    }

    public static void main(String[] args) {
        CyclicArrayQueue<Integer> queue = new CyclicArrayQueue<>();

        System.out.println(queue.dequeue());
        queue.enqueue(1);
        System.out.println(queue.dequeue());

        for (int i = 0; i < 20; i++) {
            queue.enqueue(i);
        }
        for (int i = 0; i < 25; i++) {
            System.out.println(queue.dequeue());
        }
        for (int i = 0; i < 20; i++) {
            queue.enqueue(i);
        }
        for (int i = 0; i < 25; i++) {
            System.out.println(queue.dequeue());
        }


    }
}
