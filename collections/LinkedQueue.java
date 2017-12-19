package collections;

import java.util.Iterator;

public class LinkedQueue<Item> implements IQueue<Item> {

    // -> [tail -> .. -> .. -> head] ->
    private Node<Item> head;
    private Node<Item> tail;
    private int size=0;

    @Override
    public void enqueue(Item item) {
        if(head==null && tail == null){
            head=tail=new Node<Item>(item);
            return;
        }
        tail = new Node(item, tail);
        size++;
    }

    @Override
    public Item dequeue() {
        if(head==tail) {
            head.nope = true;
            return head.item;
        }

        Node node = tail;
        while(node.next!= head)
            node=node.next;
        Item item = head.item;
        head=node;
        size--;
        return item;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<Item> iterator() {
        return new LinkedQueueIterator();
    }

    private class LinkedQueueIterator implements Iterator<Item> {

        @Override
        public boolean hasNext() {
            if(head.nope)
                return false;
            if(head.next!=null)
            return true;
            return false;
        }

        @Override
        public Item next() {
            if(head.nope)
                return null;
            return head.next.item;
        }

    }

    private static class Node<Item> {
        Item item;
        Node<Item> next;
        boolean nope=false;

        public Node(Item item) {
            this.item = item;
        }

        public Node(Item item, Node<Item> next) {
            this.item = item;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        LinkedQueue<Integer> arr = new LinkedQueue<>();
        for (int i = 0; i < 20; i++) {
            arr.enqueue(i);
        }
        for (int i = 0; i < 20; i++) {
            System.out.println(arr.dequeue());
        }
        System.out.println(arr.size());
    }
}