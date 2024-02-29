// /* *****************************************************************************
//  *  Name:
//  *  Date:
//  *  Description:
//  **************************************************************************** */
//
// import java.util.Iterator;
// import java.util.NoSuchElementException;
//
// public class Deque<Item> implements Iterable<Item> {
//     private Node first;
//     private Node last;
//     private int size;
//
//     // construct an empty deque
//     public Deque() {
//         first = null;
//         last = null;
//         size = 0;
//     }
//
//     private class Node {
//         Item item;
//         Node next;
//         Node prev;
//     }
//
//     // is the deque empty?
//     public boolean isEmpty() {
//         if (size == 0) {
//             return true;
//         }
//         return false;
//     }
//
//     // return the number of items on the deque
//     public int size() {
//         return size;
//     }
//
//     // add the item to the front
//     // public void addFirst(Item item) {
//     //     if (item == null) {
//     //         throw new IllegalArgumentException("Item cannot be null");
//     //     }
//     //
//     //     Node temp = new Node();
//     //     temp.item = item;
//     //
//     //     if (size == 0) {
//     //         first = last = temp;
//     //     }
//     //     else {
//     //         first.prev = temp;
//     //         temp.next = first;
//     //         first = temp;
//     //     }
//     //     size++;
//     // }
//     //
//     // // add the item to the back
//     // public void addLast(Item item) {
//     //     if (item == null) {
//     //         throw new IllegalArgumentException("Item cannot be null");
//     //     }
//     //
//     //     Node temp = new Node();
//     //     temp.item = item;
//     //
//     //     if (size == 0) {
//     //         first = last = temp;
//     //     }
//     //     else {
//     //         last.next = temp;
//     //         temp.prev = last;
//     //         last = temp;
//     //     }
//     //     size++;
//     // }
//     public void addFirst(Item item) {
//         if (item == null) {
//             throw new IllegalArgumentException();
//         }
//
//         Node node = new Node();
//         node.item = item;
//
//         if (first == null) {
//             first = node;
//             last = node;
//         }
//         else {
//             node.prev = first;
//             first.next = node;
//             first = node;
//         }
//
//         size++;
//     }
//
//     public void addLast(Item item) {
//         if (item == null) {
//             throw new IllegalArgumentException();
//         }
//
//         Node node = new Node();
//         node.item = item;
//
//         if (last == null) {
//             last = node;
//             first = node;
//         }
//         else {
//             node.next = last;
//             last.prev = node;
//             last = node;
//         }
//
//         size++;
//     }
//
//     public Item removeFirst() {
//         if (size == 0) {
//             throw new NoSuchElementException();
//         }
//
//         Item item = first.item;
//
//         if (size == 1) {
//             first = null;
//             last = null;
//         }
//         else {
//             first = first.prev;
//             first.next = null;
//
//             if (size == 2) {
//                 last = first;
//             }
//         }
//
//         size--;
//         return item;
//     }
//
//     // remove and return the item from the front
//     // public Item removeFirst() {
//     //     if (size == 0) {
//     //         throw new NoSuchElementException("Deque is empty");
//     //     }
//     //     Item item = first.item;
//     //     if (this.size == 1) {
//     //         first = last = null;
//     //     }
//     //     else {
//     //         first = first.next;
//     //     }
//     //     size--;
//     //     return item;
//     // }
//
//     // remove and return the item from the back
//     // public Item removeLast() {
//     //     if (size == 0) {
//     //         throw new NoSuchElementException("Deque is empty");
//     //     }
//     //     Item item = last.item;
//     //     if (this.size == 1) {
//     //         first = last = null;
//     //     }
//     //     else {
//     //         last = last.prev;
//     //     }
//     //     size--;
//     //     return item;
//     // }
//     public Item removeLast() {
//         if (size == 0) {
//             throw new NoSuchElementException();
//         }
//
//         Item item = last.item;
//
//         if (size == 1) {
//             first = null;
//             last = null;
//         }
//         else {
//             last = last.next;
//             last.prev = null;
//
//             if (size == 2) {
//                 first = last;
//             }
//         }
//
//         size--;
//         return item;
//     }
//
//
//     // return an iterator over items in order from front to back
//     public Iterator<Item> iterator() {
//         return new DequeIterator();
//     }
//
//     private class DequeIterator implements Iterator<Item> {
//         private Node current = first;
//
//         public boolean hasNext() {
//             return current != null;
//         }
//
//         public Item next() {
//             if (!hasNext()) {
//                 throw new NoSuchElementException();
//             }
//             Item item = current.item;
//             current = current.next;
//             return item;
//         }
//
//         public void remove() {
//             throw new UnsupportedOperationException();
//         }
//     }
//
//     // unit testing (required)
//
//     public static void main(String[] args) {
//         Deque<Integer> d = new Deque<>();
//         d.addFirst(1);
//         d.addFirst(2);
//         d.addLast(3);
//         d.addLast(4);
//         d.addFirst(5);
//         d.removeFirst();
//         d.addFirst(5);
//         Iterator<Integer> it = d.iterator();
//         while (it.hasNext()) {
//             System.out.println(it.next());
//         }
//
//
//     }
// }

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node head;
    private Node tail;
    private int size = 0;

    private class Node {
        Item item;
        Node prev;
        Node next;
    }

    public Deque() {
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        Node node = new Node();
        node.item = item;

        if (head == null) {
            head = node;
            tail = node;
        }
        else {
            node.prev = head;
            head.next = node;
            head = node;
        }

        size++;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        Node node = new Node();
        node.item = item;

        if (tail == null) {
            tail = node;
            head = node;
        }
        else {
            node.next = tail;
            tail.prev = node;
            tail = node;
        }

        size++;
    }

    public Item removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        Item item = head.item;

        if (size == 1) {
            head = null;
            tail = null;
        }
        else {
            head = head.prev;
            head.next = null;

            if (size == 2) {
                tail = head;
            }
        }

        size--;
        return item;
    }

    public Item removeLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        Item item = tail.item;

        if (size == 1) {
            head = null;
            tail = null;
        }
        else {
            tail = tail.next;
            tail.prev = null;

            if (size == 2) {
                head = tail;
            }
        }

        size--;
        return item;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = head;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            Item item = current.item;
            current = current.prev;
            return item;
        }
    }

    public static void main(String[] args) {
        
    }
}