import java.util.ArrayList;

class MyHashSet {
    final int SIZE = 1000;
    ArrayList<Integer> buckets[];

    private int hasFunc(int key) {
        return key % SIZE;
    }

    public MyHashSet() {
        buckets = new ArrayList[SIZE];
        for (int i = 0; i < SIZE; i++) {
            buckets[i] = new ArrayList<>();
        }
    }

    public void add(int key) {
        int hashValue = hasFunc(key);
        for (int i = 0; i < buckets[hashValue].size(); i++) {
            if (buckets[hashValue].get(i) == key) {
                return;
            }
        }
        buckets[hashValue].add(key);
    }

    public void remove(int key) {
        int hashValue = hasFunc(key);
        for (int i = 0; i < buckets[hashValue].size(); i++) {
            if (buckets[hashValue].get(i) == key) {
                buckets[hashValue].remove(i);
                return;
            }
        }
    }

    public boolean contains(int key) {
        int hashValue = hasFunc(key);
        for (int i = 0; i < buckets[hashValue].size(); i++) {
            if (buckets[hashValue].get(i) == key) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        MyHashSet myHashSet = new MyHashSet();
        myHashSet.add(1);      // set = [1]
        myHashSet.add(2);      // set = [1, 2]
        myHashSet.contains(1); // return True
        myHashSet.contains(3); // return False, (not found)
        myHashSet.add(2);      // set = [1, 2]
        myHashSet.contains(2); // return True
        myHashSet.remove(2);   // set = [1]
        System.out.println(myHashSet.contains(2)); // return False, (already removed)

    }
}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */