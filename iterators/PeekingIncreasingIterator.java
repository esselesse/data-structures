package iterators;

public class PeekingIncreasingIterator extends IncreasingIterator implements IPeekingIterator<Integer> {

    private boolean hasPeeked;
    private Integer peekedElement;

    public PeekingIncreasingIterator(int start, int maxGrowth, int stepLimit) {
        super(start, maxGrowth, stepLimit);
    }

    @Override
    public boolean hasNext() {
        return hasPeeked || super.hasNext();
    }

    @Override
    public Integer next() {
        if (!hasPeeked) {
            return super.next();
        }
        Integer result = peekedElement;
        hasPeeked = false;
        peekedElement = null; //for GC
        return result;
    }

    @Override
    public Integer peek() {
        if (!hasPeeked) {
            peekedElement = super.next();
            hasPeeked = true;
        }
        return peekedElement;
    }

    public int compareTo(PeekingIncreasingIterator iter) {
        if(this.peek()>iter.peek())
            return 1;
        if(this.peek()<iter.peek())
            return -1;
        return 0;
    }
}
