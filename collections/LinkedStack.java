package collections;

import java.util.Iterator;

public class LinkedStack<Item> implements IStack<Item> {

    private Node<Item> head;
    private int size=0;

    @Override
    public void push(Item item) {
        head = new Node<Item>(item, head);
        size++;
    }

    @Override
    public Item pop() {
        Node node = head;
        head = head.next;
        size--;
        return (Item) node.item;
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
        return new LinkedStackIterator();
    }

    private class LinkedStackIterator implements Iterator<Item> {

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
            Item item = head.item;
            head=head.next;
            return item;
        }

    }

    private static class Node<Item> {
        Item item;
        Node<Item> next;

        public Node(Item item, Node<Item> next) {
            this.item = item;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        LinkedStack<Integer> arr = new LinkedStack<>();
        for (int i = 0; i < 20; i++) {
            arr.push(i);
        }
        for (int i = 0; i < 20; i++) {
            System.out.println(arr.pop());
        }
    }
}
