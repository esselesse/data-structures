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
    private Integer[] temps;
    Integer temp = null;
    Integer it=null;

    private Comparator<PeekingIncreasingIterator> comparator = (p1, p2) -> p1.peek().compareTo(p2.peek());

    public MergingPeekingIncreasingIterator(IPeekingIterator... peekingIterator) {
        iters = new PeekingIncreasingIterator[peekingIterator.length];
        for (int i = 0; i < peekingIterator.length; i++) {
            iters[i] = (PeekingIncreasingIterator) peekingIterator[i];
        }
        temps = new Integer[peekingIterator.length];
    }

    @Override
    public boolean hasNext() {
        for (PeekingIncreasingIterator it: iters) {
            if(it.hasNext())
                return true;
        }
        for (Integer a: temps) {
            if(a!=null)
                return true;
        }
        return false;
    }

    @Override
    public Integer next() {

        if(this.hasNext()) {
            for (int i=0; i<temps.length; i++) {
                if(iters[i].hasNext()&&temps[i]==null)
                    temps[i]=iters[i].next();
            }
            temp = null;
            it=null;
            for (int i=0; i<temps.length; i++) {
                if (temps[i] != null) {
                    if (temp == null) {
                        temp = temps[i];
                        it=i;
                    }
                    else {
                        if(temp.compareTo(temps[i]) >= 0) {
                            temp = temps[i];
                            it = i;
                        }
                    }
                }
            }
            if(temp!=null){
                temps[it]=null;
                return temp;
            }
            return null;
        }
        return null;
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
