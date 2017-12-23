package iterators;

import java.util.Comparator;
import java.util.Iterator;

/**
 * Итератор возвращающий последовательность из N возрастающих итераторов в порядке возрастания
 * first = 1,3,4,5,7
 * second = 0,2,4,6,8
 * result = 0,1,2,3,4,4,5,6,7,8
 *
 * Time = O(n + k * log n),
 *  n — количество итераторов
 *  k — суммарное количество элементов
 */
public class MergingPeekingIncreasingIterator implements Iterator<Integer> {

    private PeekingIncreasingIterator[] iters;
    Integer it=null;
    int size=0;

    private Comparator<PeekingIncreasingIterator> comparator = (p1, p2) -> p1.peek().compareTo(p2.peek());

    public MergingPeekingIncreasingIterator(IPeekingIterator... peekingIterator) {
        iters = new PeekingIncreasingIterator[peekingIterator.length];
        for (int i = 0; i < peekingIterator.length; i++) {
            iters[i] = (PeekingIncreasingIterator) peekingIterator[i];
            size++;
        }
        heapify(iters);
    }

    private boolean greater(int i, int j) {
        if(iters[j]==null)
            return false;
        return comparator == null
                ? iters[i].compareTo(iters[j]) > 0
                : comparator.compare(iters[i], iters[j]) > 0
                ;
    }

    protected void swap(Iterator<Integer>[] array, int j, int i) {
        Iterator<Integer> temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private void heapify(Iterator<Integer>[] array) {
        for (int i = array.length-1; i >= 0; i--)
            hippy(array, array.length, i);
    }

    private void hippy(Iterator<Integer>[] array, int n, int i) {
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


    @Override
    public boolean hasNext() {
        if(size!=0)
            return true;
        return false;
    }

    @Override
    public Integer next() {
        if(!hasNext())
            return null;
        it = iters[0].next();
        if(iters[0].hasNext()){
            hippy(iters, size, 0);
        } else {
            swap(iters, 0, size-1);
            size--;
            hippy(iters, size, 0);
        }
        return it;
    }

    public static void main(String[] args) {
        PeekingIncreasingIterator it1 = new PeekingIncreasingIterator(1, 100, 100);
        PeekingIncreasingIterator it2 = new PeekingIncreasingIterator(6, 100, 50);
        PeekingIncreasingIterator it3 = new PeekingIncreasingIterator(5, 100, 70);
        PeekingIncreasingIterator it4 = new PeekingIncreasingIterator(4, 100, 30);
        PeekingIncreasingIterator it5 = new PeekingIncreasingIterator(3, 100, 10);
        PeekingIncreasingIterator it6 = new PeekingIncreasingIterator(2, 100, 100);

        MergingPeekingIncreasingIterator mpit = new MergingPeekingIncreasingIterator(it1, it2, it3, it4, it5, it6);

        while (mpit.hasNext()) {
            System.out.println(mpit.next());
        }
    }
}
