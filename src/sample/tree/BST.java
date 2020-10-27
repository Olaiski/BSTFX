package sample.tree;

import java.util.ArrayList;
import java.util.Iterator;

public class BST<E extends Comparable<E>> implements Tree<E> {


    protected TreeNode<E> root;
    protected int size;

    public BST() {
    }

    public BST(E[] objects) {
        for (E object : objects) add(object);
    }

    @Override
    public boolean search(E e) {
        TreeNode<E> current = root;

        while (current != null) {
            if (e.compareTo(current.element) < 0){
                current = current.left;
            }
            else if (e.compareTo(current.element) > 0) {
                current = current.right;
            }
            else
                return true;
        }
        return false;
    }

    @Override
    public boolean insert(E e) {
        if (root == null) root = createNewNode(e);
        else {
            TreeNode<E> parent = null;
            TreeNode<E> current = root;
            while (current != null)
                if (e.compareTo(current.element) < 0) {
                    parent = current;
                    current = current.left;
                }
                else if (e.compareTo(current.element) > 0) {
                    parent = current;
                    current = current.right;
                }
                else
                    return false;

            if (e.compareTo(parent.element) < 0)
                parent.left = createNewNode(e);
            else
                parent.right = createNewNode(e);
        }
        size++;
        return true;
    }


    @Override
    public void inOrder() {
        inOrder(root);
    }

    protected void inOrder(TreeNode<E> root) {
        if (root == null) return;
        inOrder(root.left);
        System.out.print(root.element + " ");
        inOrder(root.right);
    }

    @Override
    public void postOrder() {
        postOrder(root);
    }

    protected void postOrder(TreeNode<E> root) {
        if (root == null) return;
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.element + " ");
    }

    @Override
    public void preOrder() {
        preOrder(root);
    }

    protected void preOrder(TreeNode<E> root) {
        if (root == null) return;
        System.out.print(root.element + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    private TreeNode<E> createNewNode(E e) {
        return new TreeNode<>(e);
    }

    @Override
    public int getSize() {
        return size;
    }

    public TreeNode<E> getRoot() {
        return root;
    }

    // Returns a path from the root leading to the specified element
    public ArrayList<TreeNode<E>> path(E e) {
        ArrayList<TreeNode<E>> list = new ArrayList<>();
        TreeNode<E> current = root;

        while (root != null) {
            list.add(current);
            if (e.compareTo(current.element) < 0)
                current = current.left;
            else if (e.compareTo(current.element) > 0)
                current = current.right;
            else
                break;
        }

        return list;
    }

    @Override
    public boolean delete(E e) {
        TreeNode<E> parent = null;
        TreeNode<E> current = root;

        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                parent = current;
                current = current.left;
            }
            else if (e.compareTo(current.element) > 0) {
                parent = current;
                current = current.right;
            }
            else
                break;
        }
        if (current == null) // Element is not in the tree
            return false;

        // Case 1: current has no left child
        if (current.left == null) {
            if (parent == null) { // Connect the parent with the right child of the current node
                root = current.right;
            }
            else {
                if (e.compareTo(current.element) < 0)
                    parent.left = current.right;
                else
                    parent.right = current.right;
            }
        }
        else {
            // Case 2: The current node has a left child
            // Locate the rightmost node in th left subtree of the current node and also its parent
            TreeNode<E> parentOfRightMost = current;
            TreeNode<E> rightMost = current.left;

            while (rightMost.right != null) {
                parentOfRightMost = rightMost;
                rightMost = rightMost.right; // Keep going to the right
            }

            // Replace the element in current by the element in the rightmost
            current.element = rightMost.element;

            // Eliminate rightmost node
            if (parentOfRightMost.right == rightMost)
                parentOfRightMost.right = rightMost.left;
            else
                // Special case: parentOfRightMost == current
                parentOfRightMost.left = rightMost.left;
        }

        size--;
        return true;
    }

    public void mysteryPrint(){
        mysteryPrint(root);
    }

    private void mysteryPrint(TreeNode<E> t){
        if (t == null) return;
        System.out.print(t.element + " ");
        mysteryPrint(t.left);
        System.out.print(t.element + " ");
        mysteryPrint(t.right);
        System.out.print(t.element + " ");
    }

    @Override
    public Iterator<E> iterator() {
        return new InorderIterator();
    }

    // Inner class InorderIterator
    private class InorderIterator implements Iterator<E> {
        private ArrayList<E> list = new ArrayList<>();
        private int current = 0;

        public InorderIterator() {
            inOrder();
        }

        private void inOrder() {
            inOrder(root);
        }

        private void inOrder(TreeNode<E> root) {
            if (root == null) return;
            inOrder(root.left);
            list.add(root.element);
            inOrder(root.right);
        }

        @Override
        public boolean hasNext() {
            return current < list.size() ? true : false;
        }

        @Override
        public E next() {
            return list.get(current++);
        }

        @Override
        public void remove() {
            if (current == 0) // next() has not been called yet
                throw new IllegalStateException();

            delete(list.get(--current));
            list.clear(); // Clear the list
            inOrder(); // Rebuild the list
        }
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    // The inner class is static, because it does not access any instance members defined in its outer class
    public static class TreeNode<E> {
        protected E element;
        protected TreeNode<E> left;
        protected TreeNode<E> right;

        public TreeNode(E e) {
            this.element = e;
        }
    }
}
