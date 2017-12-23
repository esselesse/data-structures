package collections;

import java.util.Iterator;

public class LinkedDeque<Item> implements IDeque<Item> {

    private Node<Item> head;
    private Node<Item> tail;
    int size=0;
    @Override
    public void pushFront(Item item) {
        if(item==null)
            return ;
        if(tail==null) {
            tail = new Node<Item>(item, null, null);
            head = tail;
        }else {
            head = new Node<Item>(item, head, null);
            head.next.prev = head;
        }
        this.size++;
    }

    @Override
    public void pushBack(Item item) {
        if(item==null)
            return ;
        if(head==null) {
            head = new Node<Item>(item, null, null);
            tail = head;
        }
        else {
            tail = new Node<Item>(item, null, tail);
            tail.prev.next = tail;
        }
        this.size++;
    }

    @Override
    public Item popFront() {
        if(this.isEmpty())
            return null;
        Node node = head;
        head = head.next;
        if(head!=null)
            head.prev=null;
        size--;
        if(size==0)
            tail=head;
        return (Item) node.item;
    }

    @Override
    public Item popBack() {
        if(this.isEmpty())
            return null;
        Node node = tail;
        tail = tail.prev;
        if(tail!=null)
            tail.next=null;
        size--;
        if(size==0)
            head=tail;
        return (Item) node.item;
    }

    @Override
    public boolean isEmpty() {
        return this.size==0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Iterator<Item> iterator() {
        return new LinkedDequeIterator();
    }

    private class LinkedDequeIterator implements Iterator<Item> {
        Node curr = head;
        @Override
        public boolean hasNext() {
            if(head==null)
                return false;
            else return true;
        }

        @Override
        public Item next() {
            if(!hasNext())
                return null;
            Item item = (Item) curr.item;
            curr=curr.next;
            return item;
        }

    }

    private static class Node<Item> {
        Item item;
        Node<Item> next;
        Node<Item> prev;

        public Node(Item item, Node<Item> next, Node<Item> prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

    public static void main(String[] args) {
        LinkedDeque<Integer> ld = new LinkedDeque<Integer>();
        ld.pushFront(1);
        ld.pushFront(0);
        ld.pushBack(2);
        ld.pushBack(3);
        ld.popBack();
        ld.popFront();
        ld.popBack();
        ld.popFront();
        ld.pushFront(0);
        System.out.println(ld);
    }
}
