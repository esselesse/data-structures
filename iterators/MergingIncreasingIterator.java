package iterators;

import java.util.Iterator;

/**
 * Итератор возвращающий последовательность из двух возрастающих итераторов в порядке возрастания
 * first = 1,3,4,5,7
 * second = 0,2,4,6,8
 * result = 0,1,2,3,4,4,5,6,7,8
 *
 * Time = O(k),
 *  k — суммарное количество элементов
 */
public class MergingIncreasingIterator implements Iterator<Integer> {

    private IncreasingIterator first;
    private IncreasingIterator second;
    Integer temp = null, temp1 = null;

    public MergingIncreasingIterator(IncreasingIterator first, IncreasingIterator second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean hasNext() {
        if(!first.hasNext() && !second.hasNext() && temp==null && temp1==null)
            return false;
        return true;
    }

    @Override
    public Integer next() {
        int ttemp;
        int ttemp1;
        if(!this.hasNext())
            return null;
        if(!first.hasNext() && second.hasNext()) {
            if (temp != null && temp1 == null) {
                temp1=second.next();
                ttemp = temp;
                ttemp1 = temp1;
                if(ttemp<ttemp1){
                    temp = null;
                    return ttemp;
                } else {
                    temp1 = null;
                    return ttemp1;
                }
            }
            if (temp == null && temp1 != null) {
                ttemp1 = temp1;
                temp1 = null;
                return ttemp1;
            }
            return second.next();
        }
        if(!second.hasNext() && first.hasNext()) {
            if (temp != null && temp1 == null) {
                ttemp = temp;
                temp = null;
                return ttemp;
            }
            if (temp == null && temp1 != null) {
                temp=first.next();
                ttemp = temp;
                ttemp1 = temp1;
                if(ttemp<ttemp1){
                    temp = null;
                    return ttemp;
                } else {
                    temp1 = null;
                    return ttemp1;
                }
            }
            return first.next();
        }

        if(!first.hasNext() && !second.hasNext()){
            if(temp!=null && temp1 == null) {
                ttemp=temp;
                temp=null;
                return ttemp;
            }
            if(temp==null && temp1 != null) {
                ttemp1=temp1;
                temp1=null;
                return ttemp1;
            }
        }
        if(temp!=null && temp1 == null) {
            temp1=second.next();
        }
        if(temp1!=null && temp == null) {
            temp=first.next();
        }
        if(temp==null && temp1 == null) {
            temp = first.next();
            temp1 = second.next();
        }
        ttemp = temp;
        ttemp1 = temp1;
        if(ttemp<ttemp1){
            temp = null;
            return ttemp;
        } else {
            temp1 = null;
            return ttemp1;
        }
    }

    public static void main(String[] args) {
        IncreasingIterator it1 = new IncreasingIterator(0, 5, 50);
        IncreasingIterator it2 = new IncreasingIterator(6, 7, 39);

        MergingIncreasingIterator mer = new MergingIncreasingIterator(it1, it2);
        while (mer.hasNext()) {
            System.out.println(mer.next());
        }
    }
}
