package bstmap;

import java.util.*;

public class BSTMap<Key extends Comparable<Key>, Value extends Comparable<Value>> implements Map61B<Key, Value> {
    private class BSTNode {
        private Key key;
        private Value val;
        private BSTNode left, right;

        public BSTNode(Key key, Value val) {
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
    public boolean containsKey(Key key) {
        return getHelper(root, key) != null;
    }

    @Override
    public Value get(Key key) {
        if (getHelper(root, key) == null)
            return null;
        return getHelper(root, key).val;
    }

    private BSTNode getHelper(BSTNode root, Key key) {
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
    public void put(Key key, Value value) {
        root = putHelper(root, key, value);
    }

    private BSTNode putHelper(BSTNode root, Key key, Value value) {
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
    public Set<Key> keySet() {
        Set<Key> keys = new HashSet<>();
        keySetHelper(root, keys);
        return keys;
    }

    private void keySetHelper(BSTNode root, Set<Key> keys) {
        if (root == null) return;
        keySetHelper(root.left, keys);
        keys.add(root.key);
        keySetHelper(root.right, keys);
    }

    @Override
    public Value remove(Key key) {
        List<Value> res = new ArrayList<>(1);
        root = removeHelper(root, key, null, res);
        return res.get(0);
    }

    @Override
    public Value remove(Key key, Value value) {
        List<Value> res = new ArrayList<>(1);
        root = removeHelper(root, key, null, res);
        return res.get(0);
    }

    private BSTNode removeHelper(BSTNode root, Key key, Value value, List<Value> res) {
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

    @Override
    public Iterator<Key> iterator() {
        throw new UnsupportedOperationException();
    }
}
