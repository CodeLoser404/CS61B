package bstmap;

import java.util.*;

public class BSTMap<K extends Comparable<K>, V extends Comparable<V>> implements Map61B<K, V> {
    private class BSTNode {
        private K key;
        private V val;
        private BSTNode left, right;

        public BSTNode(K key, V val) {
            this.key = key;
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

    private BSTNode root;
    private int size;

    public BSTMap() {
        root = null;
        size = 0;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        return getHelper(root, key) != null;
    }

    @Override
    public V get(K key) {
        if (getHelper(root, key) == null)
            return null;
        return getHelper(root, key).val;
    }

    private BSTNode getHelper(BSTNode root, K key) {
        if (root == null || root.key.equals(key)) return root;
        else if (root.key.compareTo(key) > 0) {
            return getHelper(root.left, key);
        } else {
            return getHelper(root.right, key);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        root = putHelper(root, key, value);
    }

    private BSTNode putHelper(BSTNode root, K key, V value) {
        if (root == null) {
            ++size;
            return new BSTNode(key, value);
        }
        if (root.key.compareTo(key) > 0) {
            root.left = putHelper(root.left, key, value);
        } else if (root.key.compareTo(key) < 0) {
            root.right = putHelper(root.right, key, value);
        }
        return root;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        keySetHelper(root, keys);
        return keys;
    }

    private void keySetHelper(BSTNode root, Set<K> keys) {
        if (root == null) return;
        keySetHelper(root.left, keys);
        keys.add(root.key);
        keySetHelper(root.right, keys);
    }

    @Override
    public V remove(K key) {
        List<V> res = new ArrayList<>(1);
        root = removeHelper(root, key, null, res);
        return res.get(0);
    }

    @Override
    public V remove(K key, V value) {
        List<V> res = new ArrayList<>(1);
        root = removeHelper(root, key, null, res);
        return res.get(0);
    }

    private BSTNode removeHelper(BSTNode root, K key, V value, List<V> res) {
        if (root == null) return null;
        else if (root.key.compareTo(key) > 0) {
            root.left = removeHelper(root.left, key, value, res);
        } else if (root.key.compareTo(key) < 0) {
            root.right = removeHelper(root.right, key, value, res);
        } else if (root.left != null && root.right != null) {
            res.add(root.val);
            BSTNode tmp = root.left;
            while (tmp.right != null) tmp = tmp.right;
            root.key = tmp.key;
            root.val = tmp.val;
            root.left = removeHelper(root.left, tmp.key, tmp.val, res);
        } else {
            res.add(root.val);
            --size;
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
        }
        return root;
    }

    public void printInOrder() {
        printInOrderHelper(root);
    }

    private void printInOrderHelper(BSTNode root) {
        if (root == null) return;
        printInOrderHelper(root.left);
        System.out.println(root.key);
        printInOrderHelper(root.right);
    }

    private void KeyInOrder(BSTNode root, List<K> res){
        if (root == null) return;
        printInOrderHelper(root.left);
        res.add(root.key);
        printInOrderHelper(root.right);
    }

    private class BSTMapIter implements Iterator<K>{
        int cur;
        List<K> keys = new ArrayList<K>();
        public BSTMapIter(){
            KeyInOrder(root, keys);
            cur = 0;
        }
        @Override
        public boolean hasNext(){
            return cur == size - 1;
        }
        @Override
        public K next(){
            ++cur;
            return keys.remove(0);
        }
    }

    @Override
    public Iterator<K> iterator() {
        return new BSTMapIter();
    }
}
