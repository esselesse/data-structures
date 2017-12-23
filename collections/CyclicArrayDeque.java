package collections;

import java.util.Iterator;

public class CyclicArrayDeque<Item> implements IDeque<Item> {

    private static final int DEFAULT_CAPACITY = 10;
    private Item[] elementData;
    private int write=0;
    private int read=0;
    private int write1=DEFAULT_CAPACITY-1;
    private int read1=0;
    private int size=0;

    public CyclicArrayDeque(){
        this.elementData = (Item[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void pushFront(Item item) {
        if(item==null)
            return;
        grow();
        this.elementData[write1]=item;
        this.size++;


        if(size==1){
            this.read=write1;
            this.read1=write1;
        } else this.read=dec(this.read);
        this.write1=dec(this.write1);
    }

    @Override
    public void pushBack(Item item) {
        if(item==null)
            return;
        grow();
        this.elementData[this.write]=item;
        this.size++;
        if(size==1){
            this.read=write;
            this.read1=write;
        } else this.read1=inc(this.read1);
        this.write=inc(this.write);

    }

    @Override
    public Item popFront() {
        if(this.isEmpty())
            return null;
        shrink();
        Item item = this.elementData[read];
        this.size--;
        this.elementData[this.read] = null;
        this.read=inc(this.read);
        this.write1=inc(this.write1);
        return item;
    }

    @Override
    public Item popBack() {
        if(this.isEmpty())
            return null;
        shrink();
        Item item = this.elementData[read1];
        this.size--;
        this.elementData[(write-1)%this.elementData.length] = null;
        this.write=dec(this.write);
        this.read1=dec(this.read1);
        return item;
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
        this.write1 = newCapacity-1;
        this.write = this.size;
        this.read1 = this.size-1;
        this.elementData = newElementData;
    }

    private int inc(int iter) {
        iter++;
        if(iter>=this.elementData.length)
            iter=0;
        return iter;
    }

    private int dec(int iter) {
        iter--;
        if(iter<=0)
            iter=this.elementData.length-1;
        return iter;
    }

    @Override
    public Iterator<Item> iterator() {
        return new CyclicArrayDequeIterator();
    }

    private class CyclicArrayDequeIterator implements Iterator<Item> {


        int shiftFront=0, shiftBack=0;
        @Override
        public boolean hasNext() {
            return write==write1;
        }

        @Override
        public Item next() {
            if(hasNext()) {
                int iter = read;
                for (int i = 0; i < shiftFront; i++) {
                    iter = inc(iter);
                    if (iter == write)
                        return null;
                }
                shiftFront++;
                return elementData[iter];
            }

            return null;

        }

        public Item nextBack() {
            if(hasNext()) {
                int iter = read1;
                for (int i = 0; i < shiftBack; i++) {
                    iter = dec(iter);
                    if (iter == write1)
                        return null;
                }
                shiftBack++;
                return elementData[iter];
            }

            return null;

        }

    }

    public static void main(String[] args) {
        CyclicArrayDeque<Integer> cad = new CyclicArrayDeque<Integer>();
        cad.pushBack(2);
        cad.pushFront(1);
        cad.pushBack(3);
        cad.pushFront(0);
        cad.pushBack(2);
        cad.pushFront(1);
        cad.pushBack(3);
        cad.pushFront(0);
        cad.pushBack(2);
        cad.pushFront(1);
        cad.pushBack(3);
        cad.pushFront(0);
        cad.pushBack(2);
        cad.pushFront(1);
        cad.pushBack(3);
        cad.pushFront(0);
        cad.pushBack(2);
        cad.pushFront(1);
        cad.pushBack(3);
        cad.pushFront(0);
        cad.pushBack(2);
        cad.pushFront(1);
        cad.pushBack(3);
        cad.pushFront(0);
        cad.popBack();
        cad.popFront();
        cad.popBack();
        cad.popFront();
        cad.popBack();
        cad.popFront();
        cad.popBack();
        cad.popFront();
        cad.popBack();
        cad.popFront();
        cad.popBack();
        cad.popFront();
        cad.popBack();
        cad.popFront();
        cad.popBack();
        cad.popFront();
        cad.popBack();
        cad.popFront();
        cad.popBack();
        cad.popFront();
        cad.popBack();
        cad.popFront();
        cad.popBack();
        cad.popFront();


    }


}
