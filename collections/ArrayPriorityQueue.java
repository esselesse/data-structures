package collections;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

public class ArrayPriorityQueue<Key extends Comparable<Key>> implements IPriorityQueue<Key> {

    private Key[] elementData;
    private Comparator<Key> comparator;
    private int size=0;
    private static final int DEFAULT_CAPACITY = 10;

    public ArrayPriorityQueue() {
        this.elementData = (Key[]) new Comparable[DEFAULT_CAPACITY];
    }

    public ArrayPriorityQueue(Comparator<Key> comparator) {
        this.comparator = comparator;
        this.elementData = (Key[]) new Comparable[DEFAULT_CAPACITY];
    }

    @Override
    public void add(Key key) {
        if(key==null)
            return;
        this.elementData[size]=key;

        swap(elementData, 0, size);
        this.size++;
        heapify(elementData);

        grow();
    }

    @Override
    public Key peek() {
        if(this.isEmpty())
            return null;

        return elementData[0];
    }

    @Override
    public Key extractMin() {
        if(this.isEmpty())
            return null;
        Key item = elementData[0];
        swap(elementData, 0, size-1);
        elementData[size-1]=null;
        heapify(elementData);
        this.size--;
        shrink();
        return item;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
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
        this.elementData = Arrays.copyOf(this.elementData, newCapacity);
    }


    private boolean greater(int i, int j) {
        if(elementData[j]==null)
            return false;
        return comparator == null
                ? elementData[i].compareTo(elementData[j]) > 0
                : comparator.compare(elementData[i], elementData[j]) > 0
                ;
    }

    @Override
    public Iterator<Key> iterator() {
        return new ArrayPriorityIterator();
    }

    private class ArrayPriorityIterator implements Iterator<Key> {

        private int currentPosition = 0;

        @Override
        public boolean hasNext() {
            return currentPosition < size ;
        }

        @Override
        public Key next()  {
            if(hasNext()) {
                int a = currentPosition;
                currentPosition++;
                return elementData[a];
            }
            else{
                try {
                    throw new NullPointerException();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null; //чтоб жизнь мёдом не казалась.
            }
        }

    }


    private void heapify(Key[] array) {
        for (int i = size-1; i >= 0; i--)
            hippy(array, array.length, i);
    }

    private void hippy(Key[] array, int n, int i) {
        int largest = i;
        int left = 2*i + 1;
        int right = 2*i + 2;

        if (left < n && greater(largest, left))
            largest = left;

        if (right < n && greater(largest, right))
            largest = right;

        if (largest != i)
        {
            swap(array, i, largest);
            hippy(array, n, largest);
        }
    }

    protected void swap(Key[] array, int j, int i) {
        Key temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


    public static void main(String[] args) {

        ArrayPriorityQueue<Integer> apq = new ArrayPriorityQueue<Integer>();

        System.out.println(apq.extractMin());
        apq.extractMin();



            apq.add(5);
            apq.add(3);
            apq.add(8);

        Iterator it = apq.iterator();
        it.hasNext();
        int a= (int) it.next();
                try {
        a= (int) it.next();
        a= (int) it.next();
        a= (int) it.next();
                } catch (NullPointerException exception){};
        apq.add(7);
        apq.add(6);
        apq.add(1);
        apq.add(5);
        apq.add(0);
        apq.add(9);
        apq.add(9);
        apq.add(8);
        apq.add(7);
        apq.add(11);
        apq.add(5);
        apq.add(3);
        apq.add(8);
        apq.add(7);
        apq.add(6);
        apq.add(1);
        apq.add(5);
        apq.add(0);
        apq.add(9);
        apq.add(9);
        apq.add(8);
        apq.add(7);
        apq.add(11);

        System.out.println(apq.peek());

        apq.extractMin();
        apq.extractMin();
        apq.extractMin();
        apq.extractMin();
        apq.extractMin();
        apq.extractMin();
        apq.extractMin();
        apq.extractMin();
        apq.extractMin();
        apq.extractMin();
        apq.extractMin();
        apq.extractMin();
        apq.extractMin();
        apq.extractMin();
        apq.extractMin();
        apq.extractMin();
        apq.extractMin();
        apq.extractMin();
        apq.extractMin();
        apq.extractMin();
        apq.extractMin();
        apq.extractMin();


    }


}
