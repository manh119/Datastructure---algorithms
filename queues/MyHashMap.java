import java.util.ArrayList;
import java.util.List;

class MyHashMap {
    final int SIZE = 1000;
    List<node> buckets[];

    private class node {
        int key;
        int value;

        node(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return this.key;
        }

        public int getValue() {
            return this.value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public String toString() {
            return "[" + this.key + ", " + this.value + "]";
        }
    }

    public MyHashMap() {
        buckets = new ArrayList[SIZE];
        for (int i = 0; i < SIZE; i++) {
            buckets[i] = new ArrayList<>();
        }
    }

    private int hasFuntion(int key) {
        return key % SIZE;
    }

    public void put(int key, int value) {
        int hashValue = hasFuntion(key);
        for (int i = 0; i < buckets[hashValue].size(); i++) {
            if (buckets[hashValue].get(i).getKey() == key) {
                buckets[hashValue].get(i).setValue(value);
                return;
            }
        }
        buckets[hashValue].add(new node(key, value));
    }

    public int get(int key) {
        int hashValue = hasFuntion(key);
        for (int i = 0; i < buckets[hashValue].size(); i++) {
            if (buckets[hashValue].get(i).getKey() == key) {
                return buckets[hashValue].get(i).getValue();
            }
        }
        return -1;
    }

    public void remove(int key) {
        int hashValue = hasFuntion(key);
        for (int i = 0; i < buckets[hashValue].size(); i++) {
            if (buckets[hashValue].get(i).getKey() == key) {
                buckets[hashValue].remove(i);
                return;
            }
        }
    }

    public static void main(String[] args) {
        MyHashMap myHashMap = new MyHashMap();
        myHashMap.put(1, 1); // The map is now [[1,1]]
        myHashMap.put(2, 2); // The map is now [[1,1], [2,2]]
        myHashMap.get(1);    // return 1, The map is now [[1,1], [2,2]]
        myHashMap.get(3);    // return -1 (i.e., not found), The map is now [[1,1], [2,2]]
        myHashMap.put(2, 1); // The map is now [[1,1], [2,1]] (i.e., update the existing value)
        myHashMap.get(2);    // return 1, The map is now [[1,1], [2,1]]
        myHashMap.remove(2); // remove the mapping for 2, The map is now [[1,1]]
        myHashMap.get(2);    // return -1 (i.e., not found), The map is now [[1,1]]
    }

}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */