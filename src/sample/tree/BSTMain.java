package sample.tree;

import java.util.ArrayList;

public class BSTMain {

    public static void main(String[] args) {

        BST<String> tree = new BST<>();
        tree.insert("George");
        tree.insert("Michael");
        tree.insert("Tom");
        tree.insert("Adam");
        tree.insert("Jones");
        tree.insert("Peter");
        tree.insert("Daniel");

        System.out.print("Inorder (sorted): ");
        tree.inOrder();
        System.out.print("\nPostorder: ");
        tree.postOrder();
        System.out.print("\nPreorder: ");
        tree.preOrder();
        System.out.print("\nThe number of nodes is " + tree.getSize());


        System.out.println("\nIs Peter in the tree? " + tree.search("Peter"));


        System.out.println("\nA path from the root to Peter is: ");
        ArrayList<BST.TreeNode<String>> path = tree.path("Peter");

        for (int i = 0; path != null && i < path.size(); i++)
            System.out.println(path.get(i).element + " ");


        Integer[] numbers = {2, 4, 3, 1, 8, 5, 6, 7};
        BST<Integer> intTree = new BST<>(numbers);
        System.out.println("\nInorder (sorted)");
        intTree.inOrder();


        System.out.println("\n\n");
        BST<Character> charTree = new BST<>();
        charTree.insert('f');
        charTree.insert('c');
        charTree.insert('d');
        charTree.insert('i');
        charTree.insert('g');


        System.out.println();
//        charTree.preOrder();
        charTree.mysteryPrint();


    }
}
