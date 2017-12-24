package collections;

import java.util.Iterator;

public class LinkedQueue<Item> implements IQueue<Item> {

    // -> [tail -> .. -> .. -> head] ->
    private Node<Item> head;
    private Node<Item> tail;
    private int size=0;

    @Override
    public void enqueue(Item item) {
        if(item==null)
            return;
        if(head==null && tail == null){
            head=tail=new Node<Item>(item);
            size++;
            head.nope=false;
            return;
        }
        tail = new Node(item, tail);
        size++;
    }

    @Override
    public Item dequeue() {


        if(head==tail) {
            if(head!=null) {
                head.nope = true;
                Item item = head.item;
                head=null;
                tail=null;
                size=0;
                return item;
            }
            else return null;
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

        Node curr = tail;
        @Override
        public boolean hasNext() {
            if(head.nope)
                return false;
            if(curr!=null)
                return true;
            return false;
        }

        @Override
        public Item next() {
            if(head.nope)
                return null;
            Item a = (Item) curr.item;
            if(curr.next!=null)
                curr=curr.next;
            else curr=null;
            return a;
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

        System.out.println(arr.dequeue());

        for (int i = 0; i < 20; i++) {
            arr.enqueue(i);
        }


        Iterator<Integer> iterator = arr.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        for (int i = 0; i < 20; i++) {
            System.out.println(arr.dequeue());
        }
        System.out.println(arr.size());
    }
}
