package collections;

import java.util.Iterator;

public class TwoStackQueue<Item> implements IQueue<Item> {

    private IStack<Item> inStack = new ArrayStack<Item>();
    private IStack<Item> outStack = new ArrayStack<Item>();


    public TwoStackQueue() {

        /* TODO: implement it */

    }

    @Override
    public void enqueue(Item item) {
        if(item==null){
            return;
        }
        inStack.push(item);
    }

    @Override
    public Item dequeue() {
        if(this.isEmpty())
            return null;
        if (!outStack.isEmpty())
            return outStack.pop();

        while (!inStack.isEmpty())
            outStack.push(inStack.pop());
        return outStack.pop();
    }

    @Override
    public boolean isEmpty() {

        return inStack.isEmpty() && outStack.isEmpty();
    }

    @Override
    public int size() {

        return inStack.size() + outStack.size();
    }

    @Override
    public Iterator<Item> iterator() {
        return new TwoStackQueueIterator();

    }

    private class TwoStackQueueIterator implements Iterator<Item> {

        private int currentPositionIn = inStack.size();
        private int currentPositionOut = outStack.size();

        @Override
        public boolean hasNext() {
            return currentPositionIn+currentPositionOut != 0;
        }

        @Override
        public Item next() {
            if(outStack.isEmpty() && inStack.isEmpty()){
                return null;
            }
            if(outStack.isEmpty()){
                return inStack.iterator().next();
            }
            return outStack.iterator().next();
        }
    }

    public static void main(String[] args) {
        TwoStackQueue<Integer> arr = new TwoStackQueue<>();

        System.out.println(arr.dequeue());

        for (int i = 0; i < 5; i++) {
            arr.enqueue(i);
        }

        System.out.println(arr.dequeue());

        for (int i = 5; i < 20; i++) {
            arr.enqueue(i);
        }
        for (int i = 0; i < 20; i++) {
            System.out.println(arr.dequeue());
        }
    }
}