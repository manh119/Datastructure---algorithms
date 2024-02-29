/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] storage;
    private int size;

    // construct an empty randomized queue
    public RandomizedQueue() {
        storage = (Item[]) new Object[1];
        size = 0;
    }


    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        storage[size++] = item;
        resizeIfNeed();
    }

    // remove and return a random item
    public Item dequeue() {
        if (size == 0) {
            throw new NoSuchElementException("Deque is empty");
        }
        int indexRandom = StdRandom.uniform(0, size);
        Item randomItem = storage[indexRandom];
        storage[indexRandom] = storage[--size];
        resizeIfNeed();
        return randomItem;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (size == 0) {
            throw new NoSuchElementException("Deque is empty");
        }
        return storage[StdRandom.uniform(0, size)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        int randomIndexs[] = new int[size];
        int currentIndex = 0;

        public RandomizedQueueIterator() {
            for (int i = 0; i < size; i++) {
                randomIndexs[i] = i;
            }
            StdRandom.shuffle(randomIndexs);
        }

        public boolean hasNext() {
            return currentIndex < size;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = storage[randomIndexs[currentIndex]];
            currentIndex++;
            return item;
        }
    }

    private void resizeIfNeed() {
        if (size == storage.length) {
            resize(storage.length * 2);
        }
        else if (size < storage.length / 4) {
            resize(storage.length / 2);
        }
    }

    private void resize(int newSize) {
        Item[] newStorage = (Item[]) new Object[newSize];
        for (int i = 0; i < size; i++) {
            newStorage[i] = storage[i];
        }
        storage = newStorage;
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        queue.enqueue("D");
        queue.enqueue("E");

        Iterator<String> iterator = queue.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
